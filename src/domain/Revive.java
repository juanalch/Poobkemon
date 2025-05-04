package domain;

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
    
}
