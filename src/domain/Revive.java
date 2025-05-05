package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Revive extends Item {
    public Revive() {
        super("Revive");
    }
    
    @Override
    public void applyEffect(Pokemon target) {
        if (!target.isFainted()) 
            throw new IllegalStateException("Pokemon no debilitado");
        target.setCurrentHealth(target.getHealth() / 2);
    }
    @Override
    public List<Pokemon> getValidTargets(Trainer trainer) {
        return trainer.getPokemonTeam().stream()
            .filter(Pokemon::isFainted)
            .collect(Collectors.toList());
    }
}
