package domain;

public class Special extends Movement {
    
    // Constructor directo con enum MovementEffect
    public Special(String name, String type, int power, int accuracy, 
                  int pp, int priority, MovementEffect effect) {
        super(name, type, power, accuracy, pp, priority, effect);
        this.movementClass = "SPECIAL";
    }
}