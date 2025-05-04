package domain;

public class Status extends Movement {
    
    // Constructor directo con enum MovementEffect
    public Status(String name, String type, int power, int accuracy, 
                int pp, int priority) {
        super(name, type, 0, accuracy, pp, priority);
        this.movementClass = "STATE";
    }

    @Override
    public void apply(Pokemon attacker, Pokemon defender) {
        // Solo aplica efectos, no hace daño
        if (this.currentPP <= 0) {
            throw new IllegalStateException("No quedan PP para este movimiento");
        }
        
        this.reducePP(1);
        
        if (effect != null && checkAccuracy()) {
            effect.apply(attacker, defender);
        }
    }
}
