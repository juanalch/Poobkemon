package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Pantalla inicial del juego con un GIF de fondo y botón "Next".
 */
public class GameCover extends JPanel {

    private JButton nextButton;

    public GameCover() {
        initComponents();
    }

    private void initComponents() {
        // Configuración del panel principal
        setLayout(new OverlayLayout(this)); // Permite superponer componentes
        
        // Cargar GIF de fondo (ruta personalizable)
        
    JLabel backgroundLabel = new JLabel();
    try {
        // Cargar la imagen original
        ImageIcon originalIcon = new ImageIcon(getClass().getClassLoader().getResource("resources/images/game_cover.gif"));
        // Escalar al tamaño deseado (800x600)
        Image scaledImage = originalIcon.getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        backgroundLabel.setIcon(scaledIcon);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al cargar el GIF", "Error", JOptionPane.ERROR_MESSAGE);
    }
        backgroundLabel.setAlignmentX(0.5f);
        backgroundLabel.setAlignmentY(0.5f);

        // Panel para el botón (transparente y centrado)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 250)); // Ajusta el margen superior
        
        // Botón "Next" con estilo
        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Pokemon Emerald", Font.BOLD, 24)); // Fuente estilo Pokémon
        nextButton.setForeground(Color.WHITE);
        nextButton.setBackground(new Color(0, 150, 0)); // Verde esmeralda
        nextButton.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30)); // Padding
        nextButton.setFocusPainted(false);
        
        buttonPanel.add(nextButton);

        // Añadir componentes al panel principal
        add(buttonPanel);
        add(backgroundLabel);
    }

    /**
     * Permite a la clase principal (POOBkemonGUI) manejar el evento del botón.
     */
    public void setNextButtonListener(ActionListener listener) {
        nextButton.addActionListener(listener);
    }

    // Tamaño recomendado para la pantalla
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600); // Resolución base
    }
}
