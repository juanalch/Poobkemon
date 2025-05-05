package domain;

import java.util.List;

public abstract class Item {
    protected final String name;
    
    public Item(String name) {
        this.name = name;
    }
    
    public abstract void applyEffect(Pokemon target);
    
    public String getName() {
        return name;
    }
    public abstract List<Pokemon> getValidTargets(Trainer trainer);
}
    

