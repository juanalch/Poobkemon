package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class BattleScreen extends JPanel {
    private JButton makeMoveBtn, useItemBtn, changePokemonBtn;
    private JLabel timerLabel;
    private BufferedImage backgroundImage;

    public BattleScreen() {
        setLayout(new BorderLayout());
        initComponents();
        loadBackgroundImage();
    }

    private void initComponents() {
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

        makeMoveBtn = createInvisibleButton(50, 300, 120, 40, overlayPanel);
        useItemBtn = createInvisibleButton(50, 350, 120, 40, overlayPanel);
        changePokemonBtn = createInvisibleButton(50, 400, 120, 40, overlayPanel);

        timerLabel = new JLabel("20", SwingConstants.CENTER);
        timerLabel.setBounds(700, 20, 60, 30);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timerLabel.setForeground(Color.WHITE);
        overlayPanel.add(timerLabel);
    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = javax.imageio.ImageIO.read(
                getClass().getResource("/resources/images/battle.jpg")
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar la imagen de batalla", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private JButton createInvisibleButton(int x, int y, int w, int h, JPanel parent) {
        JButton button = new JButton();
        button.setBounds(x, y, w, h);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        parent.add(button);
        return button;
    }

    public void setMakeMoveAction(ActionListener listener) {
        makeMoveBtn.addActionListener(listener);
    }
}
