package presentation;

import domain.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class BattleScreen extends JPanel {
    private Battle battle;
    private JLabel[] playerPokemonStats = new JLabel[6];
    private JButton[] moveButtons = new JButton[4];
    private JTextArea battleLog;

    public BattleScreen(Battle battle) {
        this.battle = battle;
        initUI();
        updateBattleState();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        
        // Panel superior: Pokémon del jugador
        JPanel playerPanel = createPlayerPanel(battle.getTrainers().get(0));
        add(playerPanel, BorderLayout.WEST);
        
        // Panel inferior: Movimientos y acciones
        JPanel actionPanel = new JPanel(new GridLayout(2, 1));
        actionPanel.add(createMoveButtons());
        actionPanel.add(createActionButtons());
        add(actionPanel, BorderLayout.SOUTH);
        
        // Registro de batalla
        battleLog = new JTextArea(10, 40);
        add(new JScrollPane(battleLog), BorderLayout.EAST);
    }

    private JPanel createPlayerPanel(Trainer trainer) {
        JPanel panel = new JPanel(new GridLayout(6, 1));
        for (int i = 0; i < 6; i++) {
            playerPokemonStats[i] = new JLabel();
            panel.add(playerPokemonStats[i]);
        }
        return panel;
    }

    private JPanel createMoveButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 4));
        Pokemon active = battle.getCurrentTrainer().getActivePokemon();
        
        for (int i = 0; i < 4; i++) {
            if (i < active.getMovements().size()) {
                Movement move = active.getMovements().get(i);
                JButton btn = new JButton(move.getName() + " (" + move.getCurrentPP() + ")");
                btn.setEnabled(move.getCurrentPP() > 0);
                btn.addActionListener(e -> {
                    battle.queueAction(BattleAction.createMoveAction(move));
                    battle.processActions();
                });
                panel.add(btn);
            } else {
                panel.add(new JLabel(""));
            }
        }
        return panel;
    }

    public void updateBattleState() {
        Trainer current = battle.getCurrentTrainer();
        
        // Actualizar estadísticas Pokémon
        List<Pokemon> team = current.getPokemonTeam();
        for (int i = 0; i < 6; i++) {
            if (i < team.size()) {
                Pokemon p = team.get(i);
                String status = p.getName() + " HP: " + p.getCurrentHealth() + 
                              (p.isFainted() ? " (DEBILITADO)" : "");
                playerPokemonStats[i].setText(status);
            }
        }
        
        // Actualizar botones de movimientos
        Component[] components = ((JPanel)getComponent(2)).getComponents();
        if (components.length > 0 && components[0] instanceof JPanel) {
            JPanel movePanel = (JPanel) components[0];
            movePanel.removeAll();
            movePanel.add(createMoveButtons());
            movePanel.revalidate();
        }
    }

    private JPanel createActionButtons() {
        JPanel panel = new JPanel(new FlowLayout());
        
        JButton switchBtn = new JButton("Cambiar Pokémon");
        switchBtn.addActionListener(e -> showSwitchDialog());
        
        JButton itemBtn = new JButton("Usar Ítem");
        itemBtn.addActionListener(e -> showItemDialog());
        
        JButton fleeBtn = new JButton("Huir");
        fleeBtn.addActionListener(e -> battle.queueAction(BattleAction.createFleeAction()));
        
        panel.add(switchBtn);
        panel.add(itemBtn);
        panel.add(fleeBtn);
        return panel;
    }

    private void showSwitchDialog() {
        // Implementar diálogo para selección de Pokémon
    }
    public void showEndGameDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Batalla Finalizada", JOptionPane.INFORMATION_MESSAGE);
        // Volver al menú principal
        POOBkemonGUI.getInstance().showGameCover();
    }

    private void showItemDialog() {
        Trainer currentTrainer = battle.getCurrentTrainer();
        
        // Crear lista de ítems disponibles
        JComboBox<Item> itemCombo = new JComboBox<>();
        currentTrainer.getItems().forEach(itemCombo::addItem);
        
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Selecciona un ítem:"));
        panel.add(itemCombo);
        
        int result = JOptionPane.showConfirmDialog(
            this,
            panel,
            "Usar Ítem",
            JOptionPane.OK_CANCEL_OPTION
        );
        
        if (result == JOptionPane.OK_OPTION) {
            Item selectedItem = (Item) itemCombo.getSelectedItem();
            if (selectedItem != null) {
                showItemTargetDialog(selectedItem);
            }
        }
    }
    
    private void showItemTargetDialog(Item item) {
        Trainer currentTrainer = battle.getCurrentTrainer();
        List<Pokemon> validTargets = getValidTargets(item);
        
        JComboBox<Pokemon> targetCombo = new JComboBox<>();
        validTargets.forEach(targetCombo::addItem);
        
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Selecciona objetivo:"));
        panel.add(targetCombo);
        
        int result = JOptionPane.showConfirmDialog(
            this,
            panel,
            "Seleccionar Objetivo",
            JOptionPane.OK_CANCEL_OPTION
        );
        
        if (result == JOptionPane.OK_OPTION) {
            Pokemon target = (Pokemon) targetCombo.getSelectedItem();
            battle.queueAction(BattleAction.createItemAction(item, target));
            battle.processActions();
            updateBattleState();
        }
    }
    private List<Pokemon> getValidTargets(Item item) {
    return item.getValidTargets(battle.getCurrentTrainer());
}


    
}