package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Potion extends Item {

    protected final int healingPower;
    
    public Potion(String name, int healingPower) {
        super(name);
        this.healingPower = healingPower;
    }
    
    @Override
    public void applyEffect(Pokemon target) {
        if (target.isFainted()) {
            throw new IllegalStateException("No se puede usar en Pokémon debilitados");
        }
        target.heal(healingPower);
    }
    @Override
    public List<Pokemon> getValidTargets(Trainer trainer) {
        return trainer.getPokemonTeam().stream()
            .filter(p -> !p.isFainted() && p.getCurrentHealth() < p.getHealth())
            .collect(Collectors.toList());
    }
}
