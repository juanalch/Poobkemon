package domain;

public class BattleAction {
    // Enum interno para tipos de acciones
    public enum ActionType {
        MOVE,   // Usar un movimiento
        SWITCH, // Cambiar Pokémon
        ITEM,   // Usar un ítem
        FLEE    // Huir de la batalla
    }

    // Campos
    private final ActionType actionType;
    private Movement move;          // Solo para MOVE
    private Pokemon newPokemon;     // Solo para SWITCH
    private Item item;              // Solo para ITEM
    private Pokemon target;         // Solo para ITEM

    // Constructor privado
    private BattleAction(ActionType type) {
        this.actionType = type;
    }

    // ---- Factory methods ----
    public static BattleAction createMoveAction(Movement move) {
        BattleAction action = new BattleAction(ActionType.MOVE);
        action.move = move;
        return action;
    }

    public static BattleAction createSwitchAction(Pokemon newPokemon) {
        BattleAction action = new BattleAction(ActionType.SWITCH);
        action.newPokemon = newPokemon;
        return action;
    }

    public static BattleAction createItemAction(Item item, Pokemon target) {
        BattleAction action = new BattleAction(ActionType.ITEM);
        action.item = item;
        action.target = target;
        return action;
    }

    public static BattleAction createFleeAction() {
        return new BattleAction(ActionType.FLEE);
    }

    // ---- Getters ----
    public ActionType getActionType() {
        return actionType;
    }

    public Movement getMove() {
        if (actionType != ActionType.MOVE) {
            throw new IllegalStateException("Esta acción no es de tipo MOVE");
        }
        return move;
    }

    public Pokemon getNewPokemon() {
        if (actionType != ActionType.SWITCH) {
            throw new IllegalStateException("Esta acción no es de tipo SWITCH");
        }
        return newPokemon;
    }

    public Item getItem() {
        if (actionType != ActionType.ITEM) {
            throw new IllegalStateException("Esta acción no es de tipo ITEM");
        }
        return item;
    }

    public Pokemon getTarget() {
        if (actionType != ActionType.ITEM) {
            throw new IllegalStateException("Esta acción no es de tipo ITEM");
        }
        return target;
    }
}

