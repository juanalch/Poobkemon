package presentation;

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.awt.image.BufferedImage;

public class GameCover extends JPanel { 
private JButton continueBtn; private BufferedImage backgroundImage; private final double btnXRatio = 0.45; 
// Ajusta este valor (0.0 a 1.0) 
private final double btnYRatio = 0.72; // Ajusta este valor (0.0 a 1.0)

public GameCover() {
    setLayout(new BorderLayout());
    loadBackgroundImage();
    initComponents();
    addComponentListener(new ComponentAdapter() {
        @Override
        public void componentResized(ComponentEvent e) {
            updateButtonPosition();
        }
    });
}

private void loadBackgroundImage() {
    try {
        backgroundImage = javax.imageio.ImageIO.read(
            getClass().getResource("/resources/images/gameCover.jpg")
        );
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
            "Error al cargar la imagen de portada",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
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

    continueBtn = new JButton();
    continueBtn.setOpaque(false);
    continueBtn.setContentAreaFilled(false);
    continueBtn.setBorderPainted(false);
    overlayPanel.add(continueBtn);

    updateButtonPosition();
}

private void updateButtonPosition() {
    // Tamaño del botón (ajusta según el área del texto "Continue")
    int btnWidth = (int) (getWidth() * 0.15); // 15% del ancho de la pantalla
    int btnHeight = (int) (getHeight() * 0.07); // 7% del alto de la pantalla

    // Posición basada en ratios (ajusta btnXRatio y btnYRatio)
    int x = (int) (getWidth() * btnXRatio-5);
    int y = (int) (getHeight() * btnYRatio-25);

    continueBtn.setBounds(x, y, btnWidth, btnHeight);
}

public void setContinueAction(ActionListener listener) {
    continueBtn.addActionListener(listener);
}
}