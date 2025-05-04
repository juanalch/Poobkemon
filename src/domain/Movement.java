package domain;

public class Movement implements Cloneable{
    protected String name;
    protected String type;
    protected String movementClass;
    protected int power;
    protected int accuracy;
    protected int currentPP;
    protected int maxPP;
    protected int priority;
    protected MovementEffect effect;  // Para manejar efectos secundarios

    public Movement(String name, String type, int power, int accuracy, 
                   int pp, int priority) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.maxPP = pp;
        this.currentPP = pp;
        this.priority = priority;
        
    }
	
    @Override
    public Movement clone() {
        try {
            return (Movement) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Nunca ocurrirá
        }
    }

	
    // Getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getCurrentPP() {
        return currentPP;
    }

    public int getMaxPP() {
        return maxPP;
    }

    public int getPriority() {
        return priority;
    }

    public MovementEffect getEffect() {
        return effect;
    }

    // Métodos para manejar PP
    public void reducePP(int amount) {
        this.currentPP = Math.max(0, this.currentPP - amount);
    }

    public void restorePP() {
        this.currentPP = this.maxPP;
    }

    public void restorePP(int amount) {
        this.currentPP = Math.min(this.maxPP, this.currentPP + amount);
    }

    // Método para aplicar el movimiento
    public void apply(Pokemon attacker, Pokemon defender) {
        if (this.currentPP <= 0) {
            throw new IllegalStateException("No quedan PP para este movimiento");
        }
        
        this.reducePP(1);
        
        // Aplicar efecto si existe
        if (effect != null && checkAccuracy()) {
            effect.apply(attacker, defender);
        }
    }

    // Verificar precisión del movimiento
    protected boolean checkAccuracy() {
        if (accuracy == 100) return true;
        return (Math.random() * 100) <= accuracy;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movement movement = (Movement) obj;
        return name.equals(movement.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
