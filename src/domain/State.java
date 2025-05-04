package domain;

public class State extends Movement {
    
    // Constructor directo con enum MovementEffect
    public State(String name, String type, int power, int accuracy, 
                int pp, int priority, MovementEffect effect) {
        super(name, type, power, accuracy, pp, priority, effect);
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
