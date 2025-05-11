package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ModalityScreen extends JPanel {
    private JButton pvpBtn, pvmBtn, mvmBtn, continueBtn, backBtn;
    private JComboBox<String> modeComboBox;
    private String selectedModality = null;

    public ModalityScreen() {
        setLayout(new BorderLayout());
        initComponents();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateButtonPositions();
            }
        });
    }

    private void initComponents() {
        JPanel overlayPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon background = new ImageIcon(getClass().getResource("/resources/images/mode.jpg"));
                    g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        overlayPanel.setLayout(null);
        add(overlayPanel, BorderLayout.CENTER);

        pvpBtn = createInvisibleButton(overlayPanel);
        pvmBtn = createInvisibleButton(overlayPanel);
        mvmBtn = createInvisibleButton(overlayPanel);
        continueBtn = createInvisibleButton(overlayPanel);
        backBtn = createInvisibleButton(overlayPanel);

        modeComboBox = new JComboBox<>(new String[]{"Normal", "Supervivencia"});
        modeComboBox.setVisible(false);
        overlayPanel.add(modeComboBox);

        pvpBtn.addActionListener(e -> {
            selectedModality = "Player vs Player";
            showModeSelector();
        });
        pvmBtn.addActionListener(e -> selectedModality = "Player vs Machine");
        mvmBtn.addActionListener(e -> selectedModality = "Machine vs Machine");

        updateButtonPositions();
    }

    private JButton createInvisibleButton(JPanel parent) {
        JButton button = new JButton();
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        parent.add(button);
        return button;
    }

    private void updateButtonPositions() {
        double cmToPixelX = getWidth() / 23.0;
        double cmToPixelY = getHeight() / 14.5;

        pvpBtn.setBounds((int) (8.5 * cmToPixelX), (int) (7 * cmToPixelY), (int) ((13.5 - 8.5) * cmToPixelX), (int) ((8 - 7) * cmToPixelY));
        pvmBtn.setBounds((int) (8.5 * cmToPixelX), (int) (9 * cmToPixelY), (int) ((13.5 - 8.5) * cmToPixelX), (int) ((10 - 9) * cmToPixelY));
        mvmBtn.setBounds((int) (8.5 * cmToPixelX), (int) (11 * cmToPixelY), (int) ((13.5 - 8.5) * cmToPixelX), (int) ((12 - 11) * cmToPixelY));
        continueBtn.setBounds((int) (8 * cmToPixelX), (int) (13 * cmToPixelY), (int) ((14 - 8) * cmToPixelX), (int) ((14 - 13) * cmToPixelY));
        backBtn.setBounds((int) (0.5 * cmToPixelX), (int) (13 * cmToPixelY), (int) ((2 - 0.5) * cmToPixelX), (int) ((14 - 13) * cmToPixelY));

        if (modeComboBox.isVisible()) {
            modeComboBox.setBounds((int) (8.5 * cmToPixelX), (int) (8.5 * cmToPixelY), (int) ((13.5 - 8.5) * cmToPixelX), 30);
        }
    }

    private void showModeSelector() {
        modeComboBox.setVisible(true);
        updateButtonPositions();
    }

    public void setContinueAction(ActionListener listener) {
        continueBtn.addActionListener(listener);
    }

    public void setBackAction(ActionListener listener) {
        backBtn.addActionListener(listener);
    }

    public String getSelectedModality() {
        return selectedModality;
    }

    public String getSelectedMode() {
        return modeComboBox.isVisible() ? (String) modeComboBox.getSelectedItem() : "Normal";
    }
}
