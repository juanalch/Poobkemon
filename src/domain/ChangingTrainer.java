package domain;
import java.util.*;

class ChangingTrainer extends Machine {
    
    public ChangingTrainer() {
        super("Adaptive Bot", "Chameleon Green");
    }

    @Override
    public void decideAction(Battle battle) {
        Trainer opponent = battle.getOpponent(this);
        Pokemon enemy = opponent.getActivePokemon();
        
        Optional<Pokemon> bestCounter = getPokemonTeam().stream()
            .filter(p -> !p.isFainted())
            .max(Comparator.comparingDouble(p -> 
                p.getMovements().stream()
                    .mapToDouble(m -> TypeEffectiveness.getEffectiveness(m.getType(), enemy.getType1(), enemy.getType2()))
                    .average().orElse(0)
            ));

        if (bestCounter.isPresent() && bestCounter.get() != getActivePokemon()) {
            battle.queueAction(BattleAction.createSwitchAction(bestCounter.get()));
        } else {
            getActivePokemon().getMovements().stream()
                .filter(m -> m.getCurrentPP() > 0)
                .findFirst()
                .ifPresent(m -> battle.queueAction(BattleAction.createMoveAction(m)));
        }
    }
}