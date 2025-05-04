package domain;

public class Wrestling extends Movement {
    public Wrestling() {
        super("Forcejeo", "NORMAL", 50, 100, Integer.MAX_VALUE, 0, null);
        this.movementClass = "PHYSICAL";
    }

    @Override
    public void apply(Pokemon attacker, Pokemon defender) {
        // Daño fijo de 50
        defender.takeDamage(50);
        
        // Aplica el efecto de retroceso (ya definido en el enum)
        if (effect != null) {
            effect.apply(attacker, defender);
        }
    }
}
