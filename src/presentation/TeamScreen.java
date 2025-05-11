package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import domain.Pokemon;
import domain.PokemonFactory;

public class TeamScreen extends JPanel {
    private JButton continueButton, backButton;
    private List<Pokemon> selectedPokemons = new ArrayList<>();
    private JPanel pokemonPanel;
    private JDialog selectionDialog;
    private boolean isPlayer1Team = true;
    private String currentPlayerName;
    
    public TeamScreen() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Background
        JLabel background = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/team.jpg"));
            background.setIcon(new ImageIcon(icon.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
        } catch (Exception e) {
            background.setBackground(new Color(50, 50, 50));
            background.setOpaque(true);
        }
        background.setLayout(new GridBagLayout());
        
        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        
        // Title
        JLabel titleLabel = new JLabel("Select Your Team");
        titleLabel.setFont(new Font("Pokemon Emerald", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Pokemon selection area
        pokemonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        pokemonPanel.setOpaque(false);
        pokemonPanel.setMaximumSize(new Dimension(600, 400));
        
        // Add empty pokemon slots
        for (int i = 0; i < 6; i++) {
            JButton pokemonSlot = createEmptyPokemonSlot();
            pokemonPanel.add(pokemonSlot);
        }
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        backButton = new JButton("Back");
        continueButton = new JButton("Continue");
        styleButton(backButton);
        styleButton(continueButton);
        buttonPanel.add(backButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(continueButton);
        
        // Add components to content panel
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(pokemonPanel);
        contentPanel.add(Box.createVerticalGlue());
        contentPanel.add(buttonPanel);
        contentPanel.add(Box.createVerticalStrut(20));
        
        // Add to background
        background.add(contentPanel);
        add(background, BorderLayout.CENTER);
        
        // Initialize selection dialog (hidden by default)
        initSelectionDialog();
    }
    
    private JButton createEmptyPokemonSlot() {
        JButton slot = new JButton();
        slot.setPreferredSize(new Dimension(150, 150));
        slot.addActionListener(e -> showPokemonSelection());
        stylePokemonButton(slot);
        return slot;
    }
    
    private void initSelectionDialog() {
        selectionDialog = new JDialog(POOBkemonGUI.getInstance(), "Select Pokémon", true);
        selectionDialog.setLayout(new BorderLayout());
        selectionDialog.setSize(600, 500);
        selectionDialog.setLocationRelativeTo(this);
        
        JPanel pokemonListPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        JScrollPane scrollPane = new JScrollPane(pokemonListPanel);
        
        // Add available pokemons
        List<Pokemon> availablePokemons = PokemonFactory.getAvailablePokemons();
        for (Pokemon pokemon : availablePokemons) {
            JButton pokemonButton = new JButton(pokemon.getName());
            pokemonButton.addActionListener(e -> {
                selectPokemon(pokemon);
                selectionDialog.setVisible(false);
            });
            pokemonListPanel.add(pokemonButton);
        }
        
        selectionDialog.add(scrollPane, BorderLayout.CENTER);
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> selectionDialog.setVisible(false));
        selectionDialog.add(cancelButton, BorderLayout.SOUTH);
    }
    
    private void showPokemonSelection() {
        selectionDialog.setTitle("Select Pokémon for " + currentPlayerName);
        selectionDialog.setVisible(true);
    }
    
    private void selectPokemon(Pokemon pokemon) {
        if (selectedPokemons.size() < 6) {
            selectedPokemons.add(pokemon);
            updatePokemonSlots();
        } else {
            JOptionPane.showMessageDialog(this, "You can only select 6 Pokémon", 
                "Team Full", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void updatePokemonSlots() {
        pokemonPanel.removeAll();
        
        // Add selected pokemons
        for (Pokemon pokemon : selectedPokemons) {
            JButton pokemonButton = new JButton(pokemon.getName());
            pokemonButton.setIcon(getPokemonIcon(pokemon.getName()));
            stylePokemonButton(pokemonButton);
            pokemonPanel.add(pokemonButton);
        }
        
        // Add empty slots for remaining spaces
        for (int i = selectedPokemons.size(); i < 6; i++) {
            JButton emptySlot = createEmptyPokemonSlot();
            pokemonPanel.add(emptySlot);
        }
        
        pokemonPanel.revalidate();
        pokemonPanel.repaint();
    }
    
    private ImageIcon getPokemonIcon(String pokemonName) {
        // Implementación para cargar iconos de Pokémon
        try {
            return new ImageIcon(getClass().getResource("/resources/images/pokemon/" + pokemonName.toLowerCase() + ".png"));
        } catch (Exception e) {
            return null;
        }
    }
    
    private void stylePokemonButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 70, 120));
    }
    
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 70, 70));
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
    }
    public void updateForModality(String modality) {
        // Aquí puedes ajustar la interfaz según la modalidad
        if ("Player vs Machine".equals(modality) && !isPlayer1Team) {
            // Si es el turno de la máquina en PvM, generar equipo automático
            this.selectedPokemons = PokemonFactory.getRandomSurvivalTeam();
            updatePokemonSlots();
            // Deshabilitar la edición del equipo de la máquina
            for (Component comp : pokemonPanel.getComponents()) {
                if (comp instanceof JButton) {
                    comp.setEnabled(false);
                }
            }
        }
        // Puedes añadir más lógica para otras modalidades si es necesario
    }
    
    public void setPlayerSelection(boolean isPlayer1, String playerName) {
        this.isPlayer1Team = isPlayer1;
        this.currentPlayerName = playerName;
        this.selectedPokemons.clear();
        updatePokemonSlots();
        
        // Actualizar el título
        JLabel titleLabel = findTitleLabel();
        if (titleLabel != null) {
            titleLabel.setText("Select Team for " + playerName);
        }
    }
    private JLabel findTitleLabel() {
        for (Component comp : getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                if ("Select Your Team".equals(label.getText())) {
                    return label;
                }
            } else if (comp instanceof Container) {
                JLabel found = findTitleLabelInContainer((Container) comp);
                if (found != null) return found;
            }
        }
        return null;
    }
    
    private JLabel findTitleLabelInContainer(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                if ("Select Your Team".equals(label.getText())) {
                    return label;
                }
            } else if (comp instanceof Container) {
                JLabel found = findTitleLabelInContainer((Container) comp);
                if (found != null) return found;
            }
        }
        return null;
    }
    
    public List<Pokemon> getSelectedPokemons() {
        return new ArrayList<>(selectedPokemons);
    }
    
    public boolean isTeamComplete() {
        return selectedPokemons.size() == 6;
    }
    
    public void setContinueAction(ActionListener listener) {
        continueButton.addActionListener(listener);
    }
    
    public void setBackAction(ActionListener listener) {
        backButton.addActionListener(listener);
    }
    public void setPlayer1Team(boolean value){
        this.isPlayer1Team= value;
    }
}