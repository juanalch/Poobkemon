package domain;

public class Physical extends Movement {
    
    // Constructor directo con enum MovementEffect
    public Physical(String name, String type, int power, int accuracy, 
                   int pp, int priority) {
        super(name, type, power, accuracy, pp, priority);
        this.movementClass = "PHYSICAL";
    }
}
