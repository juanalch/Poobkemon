package domain;
import java.util.*;
public class Pokemon {
    private String name;
    private String type1;
    private String type2;
    private int health;         // Salud máxima (HP)
    private int currentHealth;   // Salud actual
    private int attack;
    private int defense;
    private int speed;
    private int specialAttack;
    private int specialDefense;
    private boolean weak;
    private boolean active;
    private List<Movement> movements; // Array de movimientos (4 máximo como en Pokémon)
    private int level = 100;     // Todos los pokémones son nivel 100 según requisitos

    // Constructor
    public Pokemon(String name, String type1, String type2, int health, int attack, 
                  int defense, int speed, int specialAttack, int specialDefense) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.health = health;
        this.currentHealth = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.movements = new ArrayList<>(4); // Pokémon puede tener hasta 4 movimientos
    }

    // Método para calcular daño (versión simplificada, el atacante es this)
    public int calculateDamage(Movement movement, Pokemon defender) {
        // 1. Determinar si el movimiento es físico o especial
        boolean isPhysical = movement instanceof Physical;
        
        // 2. Obtener estadísticas relevantes
        int attackStat = isPhysical ? this.getAttack() : this.getSpecialAttack();
        int defenseStat = isPhysical ? defender.getDefense() : defender.getSpecialDefense();
        
        // 3. Calcular efectividad de tipos
        double typeEffectiveness = TypeEffectiveness.getEffectiveness(
            movement.getType(), 
            defender.getType1(), 
            defender.getType2()
        );
        
        // 4. Bonificación por STAB (Same-Type Attack Bonus)
        double stab = (movement.getType().equals(this.getType1()) || 
                     (this.getType2() != null && movement.getType().equals(this.getType2()))) 
                     ? 1.5 : 1.0;
        
        // 5. Valor aleatorio entre 0.85 y 1.00
        double randomFactor = 0.85 + (Math.random() * 0.15);
        
        // 6. Fórmula completa de daño
        double damage = ((((2 * this.getLevel() / 5.0 + 2) * 
                         movement.getPower() * 
                         attackStat / defenseStat) / 
                        50.0) + 2) * 
                        stab * 
                        typeEffectiveness * 
                        randomFactor;
        
        // 7. Asegurar mínimo 1 de daño (excepto cuando efectividad es 0)
        if (typeEffectiveness == 0) {
            return 0;
        }
        
        return Math.max(1, (int) Math.round(damage));
    }

    // Método para aplicar el daño al Pokémon
    public void takeDamage(int damage) {
        this.currentHealth -= damage;
        if (this.currentHealth < 0) {
            this.currentHealth = 0;
            this.active = false;
        }
    }

    // Método para usar un movimiento (reduce PP)
    public void useMovement(Movement movement) {
        for (Movement m : movements) {
            if (m != null && m.equals(movement)) {
                m.reducePP(1);
                break;
            }
        }
    }

    // Getters y Setters necesarios
    public String getName() {
        return name;
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }

    public int getHealth() {
        return health;
    }


    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public boolean isWeak() {
        return weak;
    }

    public boolean isActive() {
        return active;
    }

    public List<Movement> getMovements() {
        return movements;
    }

    public int getLevel() {
        return level;
    }

	public int setLevel(int level){
        return this.level = level;
    }

    public void setActive(boolean active) {
        this.active = active;
        if (active) {
            this.currentHealth = health; // Restaurar salud al activar
            this.restoreAllPP();
        }
    }

	public void setDefense(int defense) {
        this.defense = Math.max(0, defense);
    }

    public void setSpeed(int speed) {
        this.speed = Math.max(0, speed);
    }

	

    public void addMovement(Movement movement, int index) {
        if (index >= 0 && index < 4) {
            if (movements.size() > index) {
                movements.set(index, movement);
            } else {
                movements.add(movement);
            }
        }
    }

    // Método para verificar si el Pokémon puede atacar
    public boolean canAttack() {
        for (Movement m : movements) {
            if (m != null && m.getCurrentPP() > 0) {
                return true;
            }
        }
        return false;
    }

    // Método para verificar si el Pokémon está debilitado
    public boolean isFainted() {
        return currentHealth <= 0;
    }

    // Método para restaurar PP de movimientos (podría usarse entre combates)
    public void restoreAllPP() {
        for (Movement m : movements) {
            if (m != null) {
                m.restorePP();
            }
        }
    }

    // Método para restaurar salud (usado con pociones)
    public void heal(int amount) {
        this.currentHealth = Math.min(this.currentHealth + amount, this.health);
    }

	public void setCurrentHealth(int health) {
        this.currentHealth = Math.max(0, Math.min(this.health, health));
    }

	public void setAttack(int attack) {
        this.attack = Math.max(0, attack); // No permitir valores negativos
    }
}
