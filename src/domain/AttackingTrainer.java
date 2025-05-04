
package domain;
import java.util.*;

class AttackingTrainer extends Machine {
    
    public AttackingTrainer() {
        super("Attacking Bot", "Flame Red");
    }

    @Override
    public void decideAction(Battle battle) {
        Pokemon current = getActivePokemon();
        Trainer opponentTrainer = battle.getOpponent(this);
        Pokemon enemy = opponentTrainer.getActivePokemon();

        Optional<Movement> bestMove = current.getMovements().stream()
            .filter(m -> m.getCurrentPP() > 0)
            .max(Comparator.comparingDouble(m -> {
                double effectiveness = TypeEffectiveness.getEffectiveness(
                    m.getType(), 
                    enemy.getType1(), 
                    enemy.getType2()
                );
                return m.getPower() * effectiveness;
            }));

        if (bestMove.isPresent()) {
            battle.queueAction(BattleAction.createMoveAction(bestMove.get()));
            return;
        }

        Optional<Item> attackItem = getItems().stream()
            .filter(i -> i instanceof Potion)
            .findFirst();

        if (attackItem.isPresent()) {
            battle.queueAction(BattleAction.createItemAction(attackItem.get(), current));
            return;
        }

        Pokemon next = getPokemonTeam().stream()
            .filter(p -> !p.isFainted())
            .findFirst().orElse(null);
            
        if (next != null) {
            battle.queueAction(BattleAction.createSwitchAction(next));
        }
    }
}
