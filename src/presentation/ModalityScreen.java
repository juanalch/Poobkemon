package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

/**
 * Pantalla para seleccionar la modalidad de juego y el modo (si aplica).
 */
public class ModalityScreen extends JPanel {

    private JButton continueButton;
    private JComboBox<String> modeSelector;
    private ButtonGroup modalityGroup;
    private JRadioButton pvpButton, pvmButton, mvmButton;

    public ModalityScreen() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new OverlayLayout(this));

        // Fondo animado
        // En GameCover.java, ModalityScreen.java y ConfigurationScreen.java
    JLabel backgroundLabel = new JLabel();
    try {
        // Cargar la imagen original
        ImageIcon originalIcon = new ImageIcon(getClass().getClassLoader().getResource("resources/images/modality.gif"));
        // Escalar al tamaño deseado (800x600)
        Image scaledImage = originalIcon.getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        backgroundLabel.setIcon(scaledIcon);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al cargar el GIF", "Error", JOptionPane.ERROR_MESSAGE);
    }
        backgroundLabel.setAlignmentX(0.5f);
        backgroundLabel.setAlignmentY(0.5f);

        // Panel de contenido
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0)); // Margen superior

        // Título
        JLabel titleLabel = new JLabel("Selecciona la modalidad");
        titleLabel.setFont(new Font("Pokemon Emerald", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(30));

        // Botones de modalidad
        modalityGroup = new ButtonGroup();
        pvpButton = createRadioButton("Player vs Player");
        pvmButton = createRadioButton("Player vs Machine");
        mvmButton = createRadioButton("Machine vs Machine");

        // Selector de modo (solo para PvP)
        modeSelector = new JComboBox<>(new String[]{"Modo Normal", "Modo Supervivencia"});
        modeSelector.setAlignmentX(Component.CENTER_ALIGNMENT);
        modeSelector.setVisible(false);
        modeSelector.setMaximumSize(new Dimension(200, 30));

        // Configurar eventos
        pvpButton.addItemListener(e -> {
            modeSelector.setVisible(e.getStateChange() == ItemEvent.SELECTED);
            validate();
        });

        // Botón Continuar
        continueButton = new JButton("Continuar");
        styleButton(continueButton);
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ensamblar componentes
        contentPanel.add(pvpButton);
        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(pvmButton);
        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(mvmButton);
        contentPanel.add(Box.createVerticalStrut(30));
        contentPanel.add(modeSelector);
        contentPanel.add(Box.createVerticalStrut(30));
        contentPanel.add(continueButton);

        add(contentPanel);
        add(backgroundLabel);
    }

    private JRadioButton createRadioButton(String text) {
        JRadioButton button = new JRadioButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setOpaque(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        modalityGroup.add(button);
        return button;
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Pokemon Emerald", Font.BOLD, 20));
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(255, 203, 5)); // Amarillo Pokémon
        button.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        button.setFocusPainted(false);
    }

    // Métodos para integración con POOBkemonGUI
    public void setContinueButtonListener(ActionListener listener) {
        continueButton.addActionListener(listener);
    }

    public String getSelectedModality() {
        if (pvpButton.isSelected()) return "PvP";
        if (pvmButton.isSelected()) return "PvM";
        if (mvmButton.isSelected()) return "MvM";
        return null;
    }

    public String getSelectedMode() {
        return modeSelector.isVisible() ? 
            (String) modeSelector.getSelectedItem() : 
            "Modo Normal";
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
}
