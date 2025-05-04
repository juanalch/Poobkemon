
package domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Person extends Trainer {
    private transient BattleDecisionProvider decisionProvider;

    public Person(String name, String color) {
        super(name, color, true);
    }

    @Override
    public void decideAction(Battle battle) {
        if (decisionProvider == null) {
            throw new IllegalStateException("Proveedor de decisiones no configurado");
        }

        try {
            BattleAction action = decisionProvider.getNextAction();
            validateAction(action);
            battle.queueAction(action);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            battle.queueAction(BattleAction.createFleeAction());
            System.err.println("Interrupción en la toma de decisiones: " + e.getMessage());
        }
    }

    // -------------------- Validaciones mejoradas --------------------
    private void validateAction(BattleAction action) {
        Objects.requireNonNull(action, "Acción no puede ser nula");
        
        switch (action.getActionType()) {
            case MOVE -> validateMove(action.getMove());
            case SWITCH -> validateSwitch(action.getNewPokemon());
            case ITEM -> validateItem(action.getItem(), action.getTarget());
            default -> throw new IllegalArgumentException("Tipo de acción no válido");
        }
    }

    private void validateMove(Movement move) {
        if (!getActivePokemon().getMovements().contains(move)) {
            throw new IllegalArgumentException("Movimiento no aprendido");
        }
        if (move.getCurrentPP() <= 0) {
            throw new IllegalStateException("PP del movimiento agotados");
        }
    }

    private void validateSwitch(Pokemon newPokemon) {
        if (!getPokemonTeam().contains(newPokemon)) {
            throw new IllegalArgumentException("Pokémon no pertenece al equipo");
        }
        if (newPokemon.isFainted()) {
            throw new IllegalStateException("No se puede cambiar a Pokémon debilitado");
        }
    }

    private void validateItem(Item item, Pokemon target) {
        if (!getItems().contains(item)) {
            throw new IllegalArgumentException("Ítem no disponible");
        }
        if (item instanceof Revive) {
            if (!target.isFainted()) {
                throw new IllegalStateException("Revive solo para Pokémon debilitados");
            }
        } else if (target.isFainted()) {
            throw new IllegalStateException("No se puede usar ítem en Pokémon debilitado");
        }
    }

    // -------------------- Gestión de configuración mejorada --------------------
    public void setTeam(List<Pokemon> team) {
        if (team.size() < 2 || team.size() > 6) {
            throw new IllegalArgumentException("El equipo debe tener entre 2 y 6 Pokémon");
        }
        
        this.pokemonTeam.clear();
        this.pokemonTeam.addAll(team);
        this.activePokemon = team.stream()
            .filter(p -> !p.isFainted())
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Equipo debe tener al menos 1 Pokémon sano"));
    }

    public void setItems(List<Item> items) {
        if (items.size() > 4) {
            throw new IllegalArgumentException("Límite de 4 ítems");
        }
        
        this.items.clear();
        this.items.addAll(items);
    }

    // -------------------- Interfaz para UI --------------------
    public interface BattleDecisionProvider {
        BattleAction getNextAction() throws InterruptedException;
    }

    public void setDecisionProvider(BattleDecisionProvider provider) {
        this.decisionProvider = Objects.requireNonNull(provider, "Proveedor no puede ser nulo");
    }

    // -------------------- Métodos auxiliares --------------------
    public List<Pokemon> getUnfaintedTeamMembers() {
        return Collections.unmodifiableList(
            pokemonTeam.stream()
                .filter(p -> !p.isFainted())
                .toList()
        );
    }
}