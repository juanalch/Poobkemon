
package domain;


import java.util.*;
import java.util.stream.Collectors;

public class Person extends Trainer {
    

    public Person(String name, String color) {
        super(name, color, true);
    }
    
    
    @Override
    public boolean isHuman(){
        return true;
    }

    @Override
public void decideAction(Battle battle) {
    // Obtener el Pokémon activo del jugador
    Pokemon activePokemon = getActivePokemon();
    
    // Simular interfaz de usuario (ej: botones en GUI o entrada por consola)
    // 1. Mostrar opciones al jugador: [Atacar] [Cambiar Pokémon] [Usar Ítem] [Huir]
    // 2. Capturar la elección del jugador (depende de la implementación de la UI)
    int chosenOption = 0; // Mock: Reemplazar con lógica real de UI
    
    switch (chosenOption) {
        case 0: // Atacar
            handleAttackAction();
            break;
        case 1: // Cambiar Pokémon
            handleSwitchAction();
            break;
        case 2: // Usar Ítem
            handleItemAction();
            break;
        case 3: // Huir
            battle.queueAction(BattleAction.createFleeAction());
            break;
        default:
            throw new GameException("Acción no válida");
    }
}
private BattleAction handleAttackAction() {
        Pokemon attacker = getActivePokemon();
        List<Movement> moves = getUsableMoves(attacker);
        
        if (moves.isEmpty()) {
            throw new GameException("¡No quedan movimientos disponibles!");
        }
        
        Movement move = selectMove(moves);
        return BattleAction.createMoveAction(move);
    }

    private List<Movement> getUsableMoves(Pokemon pokemon) {
        return pokemon.getMovements().stream()
            .filter(m -> m.getCurrentPP() > 0)
            .collect(Collectors.toList());
    }

    public Movement selectMove(List<Movement> moves) {
        // Implementar selector de movimientos en UI
        return moves.get(0); // Mock
    }

    // ================== MANEJO DE CAMBIO ==================
    
    private BattleAction handleSwitchAction() {
        List<Pokemon> available = getSwitchablePokemon();
        
        if (available.isEmpty()) {
            throw new GameException("¡No hay Pokémon disponibles para cambiar!");
        }
        
        Pokemon target = selectPokemon(available);
        return BattleAction.createSwitchAction(target);
    }

    private List<Pokemon> getSwitchablePokemon() {
        return getPokemonTeam().stream()
            .filter(p -> !p.isFainted())
            .filter(p -> p != getActivePokemon())
            .collect(Collectors.toList());
    }

    public Pokemon selectPokemon(List<Pokemon> available) {
        // Implementar selector de Pokémon en UI
        return available.get(0); // Mock
    }

    // ================== MANEJO DE ÍTEMS ==================
    
    private BattleAction handleItemAction() {
        List<Item> usableItems = filterUsableItems();
        
        if (usableItems.isEmpty()) {
            throw new GameException("¡No hay ítems utilizables!");
        }
        
        Item item = selectItem(usableItems);
        Pokemon target = selectItemTarget(item);
        return BattleAction.createItemAction(item, target);
    }

    private List<Item> filterUsableItems() {
        return getItems().stream()
            .filter(this::validateItemUsage)
            .collect(Collectors.toList());
    }

    private boolean validateItemUsage(Item item) {
        if (item instanceof Revive) {
            return hasFaintedPokemon();
        }
        return true;
    }

    private boolean hasFaintedPokemon() {
        return getPokemonTeam().stream()
            .anyMatch(Pokemon::isFainted);
    }

    public Item selectItem(List<Item> items) {
        // Implementar selector de ítems en UI
        return items.get(0); // Mock
    }

    public Pokemon selectItemTarget(Item item) {
        if (item instanceof Revive) {
            return selectFaintedPokemon();
        }
        return getActivePokemon(); // Pociones aplican al activo por defecto
    }

    private Pokemon selectFaintedPokemon() {
        List<Pokemon> fainted = getPokemonTeam().stream()
            .filter(Pokemon::isFainted)
            .collect(Collectors.toList());
        
        return selectPokemon(fainted);
    }

    // ================== VALIDACIONES ==================
    
    @Override
    public void addItem(Item item) {
        validateItemLimits(item);
        super.addItem(item);
    }

    private void validateItemLimits(Item item) {
        long count = getItems().stream()
            .filter(i -> i.getClass().equals(item.getClass()))
            .count();
        
        if (item instanceof Potion && count >= 2) {
            throw new GameException("¡Límite de pociones alcanzado (máx 2)!");
        }
        
        if (item instanceof Revive && count >= 1) {
            throw new GameException("¡Solo se permite un Revive!");
        }
    }

}