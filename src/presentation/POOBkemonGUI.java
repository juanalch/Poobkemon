package presentation;

import domain.*;
import domain.MovementFactory.MachineFactory;

import javax.swing.*;
import java.awt.*;

public class POOBkemonGUI extends JFrame{

	private static POOBkemonGUI instance;
	private POOBkemon game;
	private JFrame currentScreen;
	private GameCover gameCover;
	private ModalityScreen modalityScreen;
	private ConfigurationScreen configurationScreen;

	// Configuración actual
    private String currentModality;
    private String currentMode;

	private POOBkemonGUI() {
        super("POOBkemon - ¡Conviértete en Maestro!"); // Título de la ventana
        initGUI();
    }

    private void initGUI() {
        // Configuración básica de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null); // Centrar en pantalla

        // Iniciar con la pantalla de portada
        showGameCover();
    }

	public void showGameCover() {
		getContentPane().removeAll(); // Limpiar pantalla anterior
        GameCover coverScreen = new GameCover();
        coverScreen.setNextButtonListener(e -> showModalityScreen());
        add(coverScreen); // Añadir al contentPane
        revalidate();
        repaint();
    }

	public void showModalityScreen() {
		modalityScreen = new ModalityScreen();
        modalityScreen.setContinueButtonListener(e -> {
            currentModality = modalityScreen.getSelectedModality();
            currentMode = modalityScreen.getSelectedMode();

            if (currentModality == null) {
                JOptionPane.showMessageDialog(this, "¡Selecciona una modalidad!", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            showConfigurationScreen();
        });
        setContentPane(modalityScreen);
        revalidate();
	}

	public void showConfigurationScreen() {
		configurationScreen = new ConfigurationScreen(currentModality, currentMode);
        configurationScreen.setStartBattleListener(e -> startBattle());
        setContentPane(configurationScreen);
        revalidate();
    }
	

	public void startBattle() {

        
		// Validar datos básicos
        if (configurationScreen.getPlayer1Name().isEmpty()) {
            JOptionPane.showMessageDialog(this, "¡El Jugador 1 necesita un nombre!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Recoger datos de la pantalla de configuración
        String player1Name = configurationScreen.getPlayer1Name();
        String player2Name = configurationScreen.getPlayer2Name();
        Color player1Color = configurationScreen.getPlayer1Color();
        Color player2Color = configurationScreen.getPlayer2Color();

        // TODO: Conectar con el dominio para crear la partida
        Trainer trainer1 = createTrainer(
            configurationScreen.getPlayer1Name(),
            configurationScreen.getPlayer1Color(),
            true
        );
        
        Trainer trainer2 = createTrainer(
            configurationScreen.getPlayer2Name(),
            configurationScreen.getPlayer2Color(),
            currentModality.equals("PvP")
        );
        
        // Configurar equipos
        configureTeams(trainer1, trainer2);
        
        // Iniciar batalla
        game = new POOBkemon(
            currentModality.toUpperCase(),
            currentMode.toUpperCase(),
            trainer1,
            trainer2
        );
        
        showBattleScreen();
        // Ejemplo de transición a BattleScreen (implementar después)
        JOptionPane.showMessageDialog(this, "¡Batalla iniciada!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private Trainer createTrainer(String name, Color color, boolean isHuman) {
        return isHuman ? 
            new Person(name, color.toString()) :
            MachineFactory.createMachine(name); // Implementar factory para máquinas
    }

    private void configureTeams(Trainer t1, Trainer t2) {
        if (currentMode.equals("SURVIVAL")) {
            t1.getPokemonTeam().addAll(PokemonFactory.getRandomSurvivalTeam());
            t2.getPokemonTeam().addAll(PokemonFactory.getRandomSurvivalTeam());
        } else {
            // Lógica para selección manual (implementar)
        }
    }

    private void showBattleScreen() {
        getContentPane().removeAll();
        BattleScreen battleScreen = new BattleScreen(game.getBattle());
        add(battleScreen);
        revalidate();
        repaint();
        
        // Iniciar ciclo de batalla
        new Thread(() -> {
            while (!game.isGameOver()) {
                SwingUtilities.invokeLater(() -> {
                    battleScreen.updateBattleState();
                });
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            }
        }).start();
    }

    public static POOBkemonGUI getInstance() {
        if (instance == null) {
            instance = new POOBkemonGUI();
        }
        return instance;
    }

    public static void main(String[] args) {
        // Ejecutar en el hilo de Swing
        SwingUtilities.invokeLater(() -> {
            POOBkemonGUI gui = new POOBkemonGUI();
            gui.setVisible(true);
        });
	}

	public void closeCurrentScreen() {

	}

}