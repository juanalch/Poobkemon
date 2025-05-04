package domain;
import java.util.*;

class DefensiveTrainer extends Machine {
    
    public DefensiveTrainer() {
        super("Defensive Bot", "Shield Gray");
    }

    @Override
    public void decideAction(Battle battle) {
        Pokemon current = getActivePokemon();
        Optional<Movement> defenseMove = current.getMovements().stream()
            .filter(m -> m.getEffect() == MovementEffect.DEFENSE_UP)
            .filter(m -> m.getCurrentPP() > 0)
            .findFirst();

        if (defenseMove.isPresent()) {
            battle.queueAction(BattleAction.createMoveAction(defenseMove.get()));
        } else {
            getItems().stream()
                .filter(i -> i instanceof Potion)
                .findFirst()
                .ifPresentOrElse(
                    item -> battle.queueAction(BattleAction.createItemAction(item, current)),
                    () -> {
                        Pokemon nextHealthy = getNextHealthyPokemon();
                        if (nextHealthy != null) {
                            battle.queueAction(BattleAction.createSwitchAction(nextHealthy));
                        }
                    }
                );
        }
    }

    private Pokemon getNextHealthyPokemon() {
        return getPokemonTeam().stream()
            .filter(p -> !p.isFainted())
            .findFirst()
            .orElse(null); // Devuelve null si no hay Pokémon sanos
    }
}