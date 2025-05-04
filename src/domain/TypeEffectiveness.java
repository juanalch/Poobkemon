package domain;

import java.util.HashMap;
import java.util.Map;

public class TypeEffectiveness {
    // Matriz de efectividad de tipos (x2 = superefectivo, x0.5 = poco efectivo, x0 = inmunidad)
    private static final Map<String, Map<String, Double>> effectivenessMap = new HashMap<>();
    
    static {
        initializeEffectivenessMap();
    }
    
    private static void initializeEffectivenessMap() {
        // Normal
        addTypeEffectiveness("NORMAL", "ROCK", 0.5);
        addTypeEffectiveness("NORMAL", "GHOST", 0.0);
        addTypeEffectiveness("NORMAL", "STEEL", 0.5);
        
        // Fuego
        addTypeEffectiveness("FIRE", "FIRE", 0.5);
        addTypeEffectiveness("FIRE", "WATER", 0.5);
        addTypeEffectiveness("FIRE", "GRASS", 2.0);
        addTypeEffectiveness("FIRE", "ICE", 2.0);
        addTypeEffectiveness("FIRE", "BUG", 2.0);
        addTypeEffectiveness("FIRE", "ROCK", 0.5);
        addTypeEffectiveness("FIRE", "DRAGON", 0.5);
        addTypeEffectiveness("FIRE", "STEEL", 2.0);
        
        // Agua
        addTypeEffectiveness("WATER", "FIRE", 2.0);
        addTypeEffectiveness("WATER", "WATER", 0.5);
        addTypeEffectiveness("WATER", "GRASS", 0.5);
        addTypeEffectiveness("WATER", "GROUND", 2.0);
        addTypeEffectiveness("WATER", "ROCK", 2.0);
        addTypeEffectiveness("WATER", "DRAGON", 0.5);
        
        // Planta
        addTypeEffectiveness("GRASS", "FIRE", 0.5);
        addTypeEffectiveness("GRASS", "WATER", 2.0);
        addTypeEffectiveness("GRASS", "GRASS", 0.5);
        addTypeEffectiveness("GRASS", "POISON", 0.5);
        addTypeEffectiveness("GRASS", "GROUND", 2.0);
        addTypeEffectiveness("GRASS", "FLYING", 0.5);
        addTypeEffectiveness("GRASS", "BUG", 0.5);
        addTypeEffectiveness("GRASS", "ROCK", 2.0);
        addTypeEffectiveness("GRASS", "DRAGON", 0.5);
        addTypeEffectiveness("GRASS", "STEEL", 0.5);
        
        // Eléctrico
        addTypeEffectiveness("ELECTRIC", "WATER", 2.0);
        addTypeEffectiveness("ELECTRIC", "ELECTRIC", 0.5);
        addTypeEffectiveness("ELECTRIC", "GRASS", 0.5);
        addTypeEffectiveness("ELECTRIC", "GROUND", 0.0);
        addTypeEffectiveness("ELECTRIC", "FLYING", 2.0);
        addTypeEffectiveness("ELECTRIC", "DRAGON", 0.5);
        
        // Hielo
        addTypeEffectiveness("ICE", "FIRE", 0.5);
        addTypeEffectiveness("ICE", "WATER", 0.5);
        addTypeEffectiveness("ICE", "GRASS", 2.0);
        addTypeEffectiveness("ICE", "ICE", 0.5);
        addTypeEffectiveness("ICE", "GROUND", 2.0);
        addTypeEffectiveness("ICE", "FLYING", 2.0);
        addTypeEffectiveness("ICE", "DRAGON", 2.0);
        addTypeEffectiveness("ICE", "STEEL", 0.5);
        
        // Lucha
        addTypeEffectiveness("FIGHTING", "NORMAL", 2.0);
        addTypeEffectiveness("FIGHTING", "ICE", 2.0);
        addTypeEffectiveness("FIGHTING", "POISON", 0.5);
        addTypeEffectiveness("FIGHTING", "FLYING", 0.5);
        addTypeEffectiveness("FIGHTING", "PSYCHIC", 0.5);
        addTypeEffectiveness("FIGHTING", "BUG", 0.5);
        addTypeEffectiveness("FIGHTING", "ROCK", 2.0);
        addTypeEffectiveness("FIGHTING", "GHOST", 0.0);
        addTypeEffectiveness("FIGHTING", "DARK", 2.0);
        addTypeEffectiveness("FIGHTING", "STEEL", 2.0);
        addTypeEffectiveness("FIGHTING", "FAIRY", 0.5);
        
        // Veneno
        addTypeEffectiveness("POISON", "GRASS", 2.0);
        addTypeEffectiveness("POISON", "POISON", 0.5);
        addTypeEffectiveness("POISON", "GROUND", 0.5);
        addTypeEffectiveness("POISON", "ROCK", 0.5);
        addTypeEffectiveness("POISON", "GHOST", 0.5);
        addTypeEffectiveness("POISON", "STEEL", 0.0);
        addTypeEffectiveness("POISON", "FAIRY", 2.0);
        
        // Tierra
        addTypeEffectiveness("GROUND", "FIRE", 2.0);
        addTypeEffectiveness("GROUND", "ELECTRIC", 2.0);
        addTypeEffectiveness("GROUND", "GRASS", 0.5);
        addTypeEffectiveness("GROUND", "POISON", 2.0);
        addTypeEffectiveness("GROUND", "FLYING", 0.0);
        addTypeEffectiveness("GROUND", "BUG", 0.5);
        addTypeEffectiveness("GROUND", "ROCK", 2.0);
        addTypeEffectiveness("GROUND", "STEEL", 2.0);
        
        // Volador
        addTypeEffectiveness("FLYING", "ELECTRIC", 0.5);
        addTypeEffectiveness("FLYING", "GRASS", 2.0);
        addTypeEffectiveness("FLYING", "FIGHTING", 2.0);
        addTypeEffectiveness("FLYING", "BUG", 2.0);
        addTypeEffectiveness("FLYING", "ROCK", 0.5);
        addTypeEffectiveness("FLYING", "STEEL", 0.5);
        
        // Psíquico
        addTypeEffectiveness("PSYCHIC", "FIGHTING", 2.0);
        addTypeEffectiveness("PSYCHIC", "POISON", 2.0);
        addTypeEffectiveness("PSYCHIC", "PSYCHIC", 0.5);
        addTypeEffectiveness("PSYCHIC", "DARK", 0.0);
        addTypeEffectiveness("PSYCHIC", "STEEL", 0.5);
        
        // Bicho
        addTypeEffectiveness("BUG", "FIRE", 0.5);
        addTypeEffectiveness("BUG", "GRASS", 2.0);
        addTypeEffectiveness("BUG", "FIGHTING", 0.5);
        addTypeEffectiveness("BUG", "POISON", 0.5);
        addTypeEffectiveness("BUG", "FLYING", 0.5);
        addTypeEffectiveness("BUG", "PSYCHIC", 2.0);
        addTypeEffectiveness("BUG", "GHOST", 0.5);
        addTypeEffectiveness("BUG", "DARK", 2.0);
        addTypeEffectiveness("BUG", "STEEL", 0.5);
        addTypeEffectiveness("BUG", "FAIRY", 0.5);
        
        // Roca
        addTypeEffectiveness("ROCK", "FIRE", 2.0);
        addTypeEffectiveness("ROCK", "ICE", 2.0);
        addTypeEffectiveness("ROCK", "FIGHTING", 0.5);
        addTypeEffectiveness("ROCK", "GROUND", 0.5);
        addTypeEffectiveness("ROCK", "FLYING", 2.0);
        addTypeEffectiveness("ROCK", "BUG", 2.0);
        addTypeEffectiveness("ROCK", "STEEL", 0.5);
        
        // Fantasma
        addTypeEffectiveness("GHOST", "NORMAL", 0.0);
        addTypeEffectiveness("GHOST", "PSYCHIC", 2.0);
        addTypeEffectiveness("GHOST", "GHOST", 2.0);
        addTypeEffectiveness("GHOST", "DARK", 0.5);
        
        // Dragón
        addTypeEffectiveness("DRAGON", "DRAGON", 2.0);
        addTypeEffectiveness("DRAGON", "STEEL", 0.5);
        addTypeEffectiveness("DRAGON", "FAIRY", 0.0);
        
        // Siniestro (Dark)
        addTypeEffectiveness("DARK", "FIGHTING", 0.5);
        addTypeEffectiveness("DARK", "PSYCHIC", 2.0);
        addTypeEffectiveness("DARK", "GHOST", 2.0);
        addTypeEffectiveness("DARK", "DARK", 0.5);
        addTypeEffectiveness("DARK", "FAIRY", 0.5);
        
        // Acero (Steel)
        addTypeEffectiveness("STEEL", "FIRE", 0.5);
        addTypeEffectiveness("STEEL", "WATER", 0.5);
        addTypeEffectiveness("STEEL", "ELECTRIC", 0.5);
        addTypeEffectiveness("STEEL", "ICE", 2.0);
        addTypeEffectiveness("STEEL", "ROCK", 2.0);
        addTypeEffectiveness("STEEL", "STEEL", 0.5);
        addTypeEffectiveness("STEEL", "FAIRY", 2.0);
        
        // Hada (Fairy)
        addTypeEffectiveness("FAIRY", "FIRE", 0.5);
        addTypeEffectiveness("FAIRY", "FIGHTING", 2.0);
        addTypeEffectiveness("FAIRY", "POISON", 0.5);
        addTypeEffectiveness("FAIRY", "DRAGON", 2.0);
        addTypeEffectiveness("FAIRY", "DARK", 2.0);
        addTypeEffectiveness("FAIRY", "STEEL", 0.5);
    }
    
    private static void addTypeEffectiveness(String attackType, String defendType, double multiplier) {
        effectivenessMap
            .computeIfAbsent(attackType, k -> new HashMap<>())
            .put(defendType, multiplier);
    }
    
    /**
     * Calcula el multiplicador de efectividad de un ataque considerando ambos tipos del Pokémon defensor
     * @param attackType Tipo del movimiento de ataque
     * @param defenderType1 Primer tipo del Pokémon defensor
     * @param defenderType2 Segundo tipo del Pokémon defensor (puede ser null)
     * @return Multiplicador de daño (ej: 2.0 para superefectivo, 0.5 para poco efectivo, 0 para inmunidad)
     */
    public static double getEffectiveness(String attackType, String defenderType1, String defenderType2) {
        double effectiveness1 = effectivenessMap
            .getOrDefault(attackType, new HashMap<>())
            .getOrDefault(defenderType1, 1.0);
        
        double effectiveness2 = 1.0;
        if (defenderType2 != null && !defenderType2.isEmpty()) {
            effectiveness2 = effectivenessMap
                .getOrDefault(attackType, new HashMap<>())
                .getOrDefault(defenderType2, 1.0);
        }
        
        return effectiveness1 * effectiveness2;
    }
    
    /**
     * Versión simplificada para Pokémon con un solo tipo
     */
    public static double getEffectiveness(String attackType, String defenderType) {
        return getEffectiveness(attackType, defenderType, null);
    }
}
