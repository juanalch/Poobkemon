package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TeamScreen extends JPanel {
    private JButton continueButton, backButton;
    
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
        JPanel pokemonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        pokemonPanel.setOpaque(false);
        pokemonPanel.setMaximumSize(new Dimension(600, 400));
        
        // Add placeholder pokemon slots
        for (int i = 0; i < 6; i++) {
            JButton pokemonSlot = new JButton("Pokemon " + (i+1));
            pokemonSlot.setPreferredSize(new Dimension(150, 150));
            stylePokemonButton(pokemonSlot);
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
    
    public void setContinueAction(ActionListener listener) {
        continueButton.addActionListener(listener);
    }
    
    public void setBackAction(ActionListener listener) {
        backButton.addActionListener(listener);
    }
}