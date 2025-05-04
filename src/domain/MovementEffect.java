package domain;

public enum MovementEffect {
    DEFENSE_UP {
        @Override
        public void apply(Pokemon attacker, Pokemon defender) {
            defender.setDefense((int)(defender.getDefense() * 1.2));
        }
    },
    BURN {
        @Override
        public void apply(Pokemon attacker, Pokemon defender) {
            defender.setStatus(Status.BURNED);
            defender.setAttack((int)(defender.getAttack() * 0.5));
        }
    },
    POISON {
        @Override
        public void apply(Pokemon attacker, Pokemon defender) {
            defender.setStatus(Status.POISONED);
        }
    },
    PARALYZE {
        @Override
        public void apply(Pokemon attacker, Pokemon defender) {
            defender.setStatus(Status.PARALYZED);
            defender.setSpeed((int)(defender.getSpeed() * 0.5));
        }
    },
    
    
    NONE {
        @Override
        public void apply(Pokemon attacker, Pokemon defender) {
            // No hace nada
        }
    };
    
    public abstract void apply(Pokemon attacker, Pokemon defender);
    
    public static MovementEffect fromString(String effectType) {
        if (effectType == null || effectType.isEmpty()) {
            return NONE;
        }
        try {
            return valueOf(effectType.toUpperCase());
        } catch (IllegalArgumentException e) {
            return NONE;
        }
    }
}
