package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class ConfigurationScreen extends JPanel {
    private JTextField player1Field, player2Field;
    private JButton player1ColorBtn, player2ColorBtn;
    private JButton team1Btn, team2Btn;
    private JButton continueBtn, backBtn;
    private Color player1Color = Color.RED;
    private Color player2Color = Color.BLUE;
    private BufferedImage backgroundImage;
    private String currentModality;

    public ConfigurationScreen() {
        loadBackgroundImage();
        initComponents();
    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/resources/images/configuration.jpg"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading background image", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateForModality(String modality) {
        this.currentModality = modality;
        
        player1Field.setVisible(true);
        player1ColorBtn.setVisible(true);
        team1Btn.setVisible(true);
        player2Field.setVisible(true);
        player2ColorBtn.setVisible(true);
        team2Btn.setVisible(true);

        if ("Player vs Machine".equals(modality)) {
            player2Field.setVisible(false);
            player2ColorBtn.setVisible(false);
            team2Btn.setVisible(false);
        } else if ("Machine vs Machine".equals(modality)) {
            POOBkemonGUI.getInstance().showScreen("TeamScreen");
            return;
        }
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel overlayPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        overlayPanel.setLayout(null);
        add(overlayPanel, BorderLayout.CENTER);

        player1Field = createTextField(200, 300, 50, 20, overlayPanel);
        player1ColorBtn = createColorButton(200, 380, 50, 20, overlayPanel, true);
        team1Btn = createInvisibleButton(180, 450, 100, 30, overlayPanel, "Team 1");

        player2Field = createTextField(280, 300, 50, 20, overlayPanel);
        player2ColorBtn = createColorButton(280, 380, 50, 20, overlayPanel, false);
        team2Btn = createInvisibleButton(260, 450, 100, 30, overlayPanel, "Team 2");

        continueBtn = createInvisibleButton(660, 560, 120, 40, overlayPanel, "continue");
        backBtn = createInvisibleButton(0, 540, 80, 40, overlayPanel, "back");

        // Action listeners for buttons
        team1Btn.addActionListener(e -> POOBkemonGUI.getInstance().showScreen("TeamScreen"));
        team2Btn.addActionListener(e -> POOBkemonGUI.getInstance().showScreen("TeamScreen"));
        
        continueBtn.addActionListener(e -> {
            if ("Player vs Player".equals(currentModality)) {
                POOBkemonGUI.getInstance().showScreen("TeamScreen");
            } else {
                POOBkemonGUI.getInstance().showScreen("BattleScreen");
            }
        });
    }

    private JTextField createTextField(int x, int y, int width, int height, JPanel parent) {
        JTextField field = new JTextField();
        field.setBounds(x, y, width, height);
        parent.add(field);
        return field;
    }

    private JButton createColorButton(int x, int y, int width, int height, JPanel parent, boolean isPlayer1) {
        JButton button = new JButton();
        button.setBounds(x, y, width, height);
        button.setBackground(isPlayer1 ? player1Color : player2Color);
        button.addActionListener(e -> chooseColor(isPlayer1));
        parent.add(button);
        return button;
    }

    private JButton createInvisibleButton(int x, int y, int width, int height, JPanel parent, String text) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        parent.add(button);
        return button;
    }

    private void chooseColor(boolean isPlayer1) {
        Color newColor = JColorChooser.showDialog(this, 
            "Choose Color", 
            isPlayer1 ? player1Color : player2Color);
        
        if (newColor != null) {
            if (isPlayer1) {
                player1Color = newColor;
                player1ColorBtn.setBackground(newColor);
            } else {
                player2Color = newColor;
                player2ColorBtn.setBackground(newColor);
            }
        }
    }

    public String getPlayer1Name() {
        return player1Field.getText().trim();
    }

    public String getPlayer2Name() {
        return player2Field.getText().trim();
    }

    public Color getPlayer1Color() {
        return player1Color;
    }

    public Color getPlayer2Color() {
        return player2Color;
    }

    public void setContinueAction(ActionListener listener) {
        continueBtn.addActionListener(listener);
    }

    public void setBackAction(ActionListener listener) {
        backBtn.addActionListener(listener);
    }

    public String getModality() {
        return currentModality;
    }
}
