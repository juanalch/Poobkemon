package domain;
import java.util.*;

class ExpertTrainer extends Machine {
    public ExpertTrainer() {
        super("Expert Bot", "Champion Gold");
    }

    @Override
    public void decideAction(Battle battle) {
        Pokemon enemy = battle.getOpponent(this).getActivePokemon();
        getActivePokemon().getMovements().stream()
            .filter(m -> m.getCurrentPP() > 0)
            .max(Comparator.comparingDouble(m -> 
                TypeEffectiveness.getEffectiveness(m.getType(), enemy.getType1(), enemy.getType2())
            ))
            .ifPresent(m -> battle.queueAction(BattleAction.createMoveAction(m)));
    }
}