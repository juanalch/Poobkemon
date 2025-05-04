package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Pantalla para configurar nombres y colores de entrenadores.
 */
public class ConfigurationScreen extends JPanel {

    // Componentes básicos
    private JTextField player1NameField, player2NameField;
    private JButton player1ColorBtn, player2ColorBtn;
    private JButton startBattleButton;
    private Color player1Color = Color.RED;
    private Color player2Color = Color.BLUE;

    // Configuración recibida
    private String modality;
    private String mode;

    public ConfigurationScreen(String modality, String mode) {
        this.modality = modality;
        this.mode = mode;
        initComponents();
    }

    private void initComponents() {
        setLayout(new OverlayLayout(this));

        // Fondo animado
        // En GameCover.java, ModalityScreen.java y ConfigurationScreen.java
    JLabel backgroundLabel = new JLabel();
    try {
        // Cargar la imagen original
        ImageIcon originalIcon = new ImageIcon(getClass().getClassLoader().getResource("resources/images/jugadores.jpg"));
        // Escalar al tamaño deseado (800x600)
        Image scaledImage = originalIcon.getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        backgroundLabel.setIcon(scaledIcon);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al cargar el GIF", "Error", JOptionPane.ERROR_MESSAGE);
    }

        // Panel de contenido principal
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(100, 20, 20, 20)); // Margen superior aumentado

        // Sección Jugador 1
        if (!modality.equals("MvM")) {
            contentPanel.add(createPlayerSection("Jugador 1", true));
            contentPanel.add(Box.createVerticalStrut(30));
        }

        // Sección Jugador 2 (solo para PvP)
        if (modality.equals("PvP")) {
            contentPanel.add(createPlayerSection("Jugador 2", false));
            contentPanel.add(Box.createVerticalStrut(30));
        }

        // Botón de inicio
        startBattleButton = new JButton("¡Empezar Batalla!");
        styleButton(startBattleButton);
        startBattleButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar botón
        contentPanel.add(startBattleButton);

        add(contentPanel);
        add(backgroundLabel);
    }

    private JPanel createPlayerSection(String title, boolean isPlayer1) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setOpaque(false);
        panel.setMaximumSize(new Dimension(500, 50)); // Limitar ancho

        JLabel titleLabel = new JLabel(title + ":");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);

        JTextField nameField = new JTextField(15);
        nameField.setMaximumSize(new Dimension(200, 30));

        JButton colorBtn = new JButton("🎨");
        colorBtn.addActionListener(e -> chooseColor(isPlayer1));
        colorBtn.setBackground(isPlayer1 ? player1Color : player2Color);
        colorBtn.setFocusPainted(false);

        // Asignar componentes a variables
        if (isPlayer1) {
            player1NameField = nameField;
            player1ColorBtn = colorBtn;
        } else {
            player2NameField = nameField;
            player2ColorBtn = colorBtn;
        }

        panel.add(titleLabel);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(nameField);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(colorBtn);

        return panel;
    }

    private void chooseColor(boolean isPlayer1) {
        Color color = JColorChooser.showDialog(this, "Elige un color", isPlayer1 ? player1Color : player2Color);
        if (color != null) {
            if (isPlayer1) {
                player1Color = color;
                player1ColorBtn.setBackground(color);
            } else {
                player2Color = color;
                player2ColorBtn.setBackground(color);
            }
        }
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 100, 200)); // Azul neutro
        button.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
    }

    // Getters para obtener datos
    public String getPlayer1Name() { return player1NameField.getText(); }
    public String getPlayer2Name() { return modality.equals("PvP") ? player2NameField.getText() : "Máquina"; }
    public Color getPlayer1Color() { return player1Color; }
    public Color getPlayer2Color() { return player2Color; }

    public void setStartBattleListener(ActionListener listener) {
        startBattleButton.addActionListener(listener);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
}
