package domain;
import java.util.*;

public abstract class Machine extends Trainer {
    
    public Machine(String name, String color) {
        super(name, color, false);
    }

    // Método auxiliar común para todas las máquinas
    protected Optional<Movement> findMovementWithEffect(Pokemon pokemon, Class<?> effectType) {
        return pokemon.getMovements().stream()
            .filter(m -> m.getEffect() != null)
            .filter(m -> m.getEffect().getClass().equals(effectType))
            .findFirst();
    }

    @Override
    public boolean isHuman(){
        return false;
    }
}
