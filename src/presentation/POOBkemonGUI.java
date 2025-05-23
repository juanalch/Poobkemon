package presentation;

import javax.swing.*;
import domain.Pokemon;

import java.util.ArrayList;
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
    // Nuevas variables para manejar la selección de equipos
    private boolean isPlayer1Turn = true;
    private ArrayList<Pokemon> player1Team;
    private ArrayList<Pokemon> player2Team;
    
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
        configurationScreen.setContinueAction(e -> {
            if (validateConfiguration()) {
                isPlayer1Turn = true; // Resetear al jugador 1
                teamScreen.setPlayerSelection(true, 
                    "Player vs Player".equals(currentModality) ? 
                    configurationScreen.getPlayer1Name() : "Your Team");
                teamScreen.updateForModality(currentModality);
                showScreen("TeamScreen");
            }
        });
        
        // Team Screen -> Battle Screen or back
        teamScreen.setContinueAction(e -> {
            if (teamScreen.isTeamComplete()) {
                if (isPlayer1Turn && "Player vs Player".equals(currentModality)) {
                    // Guardar equipo del jugador 1 y preparar selección para jugador 2
                    player1Team = new ArrayList<>(teamScreen.getSelectedPokemons());
                    isPlayer1Turn = false;
                    teamScreen.setPlayerSelection(false, configurationScreen.getPlayer2Name());
                    teamScreen.updateForModality(currentModality);
                    showScreen("TeamScreen");
                } else {
                    // Guardar equipo del jugador 2 o de la máquina y comenzar batalla
                    player2Team = new ArrayList<>(teamScreen.getSelectedPokemons());
                    startBattle();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select 6 Pokémon", 
                    "Incomplete Team", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        teamScreen.setBackAction(e -> {
            if (isPlayer1Turn || !"Player vs Player".equals(currentModality)) {
                showScreen("ConfigurationScreen");
            } else {
                // Volver a la selección del jugador 1 en PvP
                isPlayer1Turn = true;
                teamScreen.setPlayerSelection(true, configurationScreen.getPlayer1Name());
                teamScreen.updateForModality(currentModality);
            }
        });
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
    private void showConfigurationScreen() {
        configurationScreen.updateForModality(currentModality);
        isPlayer1Turn = true; // Resetear para nueva configuración
        showScreen("ConfigurationScreen");
    }
    
    private void startBattle() {
        // Configurar los equipos según la modalidad
        if ("Player vs Player".equals(currentModality)) {
            // Configurar equipos para PvP
        } else if ("Player vs Machine".equals(currentModality)) {
            // Configurar equipo para PvM
        } else {
            // Configurar equipos para MvM
        }
        showScreen("BattleScreen");
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
