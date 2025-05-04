package presentation;

import domain.POOBkemon;
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

	public POOBkemonGUI() {
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

        // Ejemplo de transición a BattleScreen (implementar después)
        JOptionPane.showMessageDialog(this, "¡Batalla iniciada!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
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
