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
        
        configureTeams(trainer1, trainer2);
        
        game = new POOBkemon(
            currentModality.toUpperCase(),
            currentMode.toUpperCase(),
            trainer1,
            trainer2
        );
        
        showBattleScreen();
    }

    private Trainer createTrainer(String name, Color color, boolean isHuman) {
        return isHuman ? 
            new Person(name, color.toString()) :
            MachineFactory.createMachine(name); // Implementar factory para máquinas
    }

    private void configureTeams(Trainer t1, Trainer t2) {
        if (currentMode.equalsIgnoreCase("SURVIVAL")) {
            t1.getPokemonTeam().addAll(PokemonFactory.getRandomSurvivalTeam());
            t2.getPokemonTeam().addAll(PokemonFactory.getRandomSurvivalTeam());
        } else {
            // Modo normal: 6 Pokémon iniciales
            List<Pokemon> baseTeam = PokemonFactory.getAllPokemons().subList(0, 6);
            t1.getPokemonTeam().addAll(baseTeam);
            t2.getPokemonTeam().addAll(baseTeam);
        }
        
        // Establecer Pokémon activo inicial
        t1.getPokemonTeam().forEach(p -> p.setActive(true));
        t2.getPokemonTeam().forEach(p -> p.setActive(true));
    }

    private void showBattleScreen() {
        getContentPane().removeAll();
        BattleScreen battleScreen = new BattleScreen(game.getBattle());
        add(battleScreen);
        revalidate();
        repaint();

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (!game.isGameOver()) {
                    Thread.sleep(1000);
                    publish();
                }
                return null;
            }

            @Override
            protected void process(List<Void> chunks) {
                battleScreen.updateBattleState();
                if (game.isGameOver()) {
                    battleScreen.showEndGameDialog(game.getBattle().getResultMessage());
                }
            }
        }.execute();
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
