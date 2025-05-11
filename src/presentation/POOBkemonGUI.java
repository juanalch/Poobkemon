package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class POOBkemonGUI extends JFrame {
    private static POOBkemonGUI instance;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    // Screens
    private GameCover gameCover;
    private ModalityScreen modalityScreen;
    private ConfigurationScreen configurationScreen;
    private TeamScreen teamScreen;
    private BattleScreen battleScreen;
    
    // Game configuration
    private String currentModality;
    private String currentMode;
    
    private POOBkemonGUI() {
        super("POOBkemon - ¡Conviértete en Maestro!");
        initGUI();
    }
    
    private void initGUI() {
        // Configure main window
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });
        
        // Set minimum size
        setMinimumSize(new Dimension(800, 600));
        
        // Create main panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Initialize screens
        gameCover = new GameCover();
        modalityScreen = new ModalityScreen();
        configurationScreen = new ConfigurationScreen();
        teamScreen = new TeamScreen();
        battleScreen = new BattleScreen();
        
        // Add screens to main panel
        mainPanel.add(gameCover, "GameCover");
        mainPanel.add(modalityScreen, "ModalityScreen");
        mainPanel.add(configurationScreen, "ConfigurationScreen");
        mainPanel.add(teamScreen, "TeamScreen");
        mainPanel.add(battleScreen, "BattleScreen");
        
        // Set up screen transitions
        setupScreenTransitions();
        
        // Add main panel to frame
        add(mainPanel);
        
        // Center and show
        pack();
        setLocationRelativeTo(null);
    }
    
    private void setupScreenTransitions() {
        // Game Cover -> Modality Screen
        gameCover.setContinueAction(e -> showScreen("ModalityScreen"));
        
        // Modality Screen -> Configuration Screen or back
        modalityScreen.setContinueAction(e -> {
            currentModality = modalityScreen.getSelectedModality();
            currentMode = modalityScreen.getSelectedMode();
            if (currentModality != null) {
                showConfigurationScreen();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a game modality", 
                    "Selection Required", JOptionPane.WARNING_MESSAGE);
            }
        });
        modalityScreen.setBackAction(e -> showScreen("GameCover"));
        
        // Configuration Screen -> Team Screen or back
        configurationScreen.setContinueAction(e -> {
            if (validateConfiguration()) {
                showScreen("TeamScreen");
            }
        });
        configurationScreen.setBackAction(e -> showScreen("ModalityScreen"));
        
        // Team Screen -> Battle Screen or back
        teamScreen.setContinueAction(e -> showScreen("BattleScreen"));
        teamScreen.setBackAction(e -> showScreen("ConfigurationScreen"));
    }
    
    public void showConfigurationScreen() {
        configurationScreen.updateForModality(currentModality);
        showScreen("ConfigurationScreen");
    }
    
    private boolean validateConfiguration() {
        if ("Player vs Player".equals(currentModality)) {
            if (configurationScreen.getPlayer1Name() == null || configurationScreen.getPlayer1Name().isEmpty() || 
                configurationScreen.getPlayer2Name() == null || configurationScreen.getPlayer2Name().isEmpty()) {
                JOptionPane.showMessageDialog(this, "¡Nombres requeridos para ambos jugadores en PvP!", 
                    "Error", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } else if ("Player vs Machine".equals(currentModality)) {
            if (configurationScreen.getPlayer1Name() == null || configurationScreen.getPlayer1Name().isEmpty()) {
                JOptionPane.showMessageDialog(this, "¡Nombre requerido para el jugador en PvM!", 
                    "Error", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        // No validation needed for Machine vs Machine
        return true;
    }
    
    private void confirmExit() {
        int option = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to exit the game?", 
            "Exit Confirmation", 
            JOptionPane.YES_NO_OPTION);
            
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    public void showScreen(String screenName) {
        cardLayout.show(mainPanel, screenName);
    }
    
    public static POOBkemonGUI getInstance() {
        if (instance == null) {
            instance = new POOBkemonGUI();
        }
        return instance;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            POOBkemonGUI.getInstance().setVisible(true);
        });
    }

    public String getCurrentModality() {
        return currentModality;
    }

    public String getCurrentMode() {
        return currentMode;
    }
}
