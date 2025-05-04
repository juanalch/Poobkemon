package domain;

import java.util.*;



public class MovementFactory {
    private static final Map<String, Movement> movementCatalog = new HashMap<>();

    static {
        initializeMovements();
    }

    private static void initializeMovements() {
        // ===== MOVIMIENTOS FÍSICOS =====
        addMovement(new Physical("Lanzallamas", "FIRE", 90, 100, 15, 0));
        addMovement(new Physical("Garra Dragón", "DRAGON", 80, 100, 10, 0));
        addMovement(new Physical("Terremoto", "GROUND", 100, 100, 10, 0));
        addMovement(new Physical("Megacuerno", "BUG", 120, 85, 10, 0));
        addMovement(new Physical("Avalancha", "ROCK", 75, 90, 10, 0));

        // ===== MOVIMIENTOS ESPECIALES =====
        addMovement(new Special("Hidrobomba", "WATER", 110, 80, 5, 0));
        addMovement(new Special("Rayo Solar", "GRASS", 120, 100, 10, 0));
        addMovement(new Special("Psíquico", "PSYCHIC", 90, 100, 10, 0));
        addMovement(new Special("Bola Sombra", "GHOST", 80, 100, 15, 0));
        addMovement(new Special("Trueno", "ELECTRIC", 110, 70, 10, 0));

        // ===== MOVIMIENTOS DE ESTADO =====
        addMovement(new Status("Danza Espada", "NORMAL",0,  100, 20, 0)); // Aumenta ataque
        addMovement(new Status("Danza Lluvia", "WATER", 0, 100, 5, 0));   // Cambia clima
        addMovement(new Status("Tinieblas", "DARK",0, 100, 15, 0));      // Reduce precisión
        addMovement(new Status("Defensa Férrea", "STEEL",0, 100, 15, 0)); // Aumenta defensa
        addMovement(new Status("Viento Feérico", "FAIRY",0, 100, 10, 0)); // Reduce ataque especial

        // ===== MOVIMIENTOS ÚNICOS =====
        addMovement(new Physical("Giro Fuego", "FIRE", 60, 100, 15, 0));
        addMovement(new Special("Ventisca", "ICE", 110, 70, 5, 0));
        addMovement(new Physical("Puño Dinámico", "FIGHTING", 100, 50, 5, 0));
        addMovement(new Status("Tóxico", "POISON", 0,90, 10, 0));         // Envenena
        addMovement(new Special("Onda Voltio", "ELECTRIC", 60, 100, 20, 1)); // Prioridad alta
    }

    private static void addMovement(Movement movement) {
        movementCatalog.put(movement.getName().toUpperCase(), movement);
    }

    // ===== MÉTODO PRINCIPAL =====
    public static Movement getMovement(String name) {
        Movement original = movementCatalog.get(name.toUpperCase());
        if (original == null) return null;
        
        return original.clone(); // Devuelve una copia independiente
    }

    // ===== MÉTODOS DE CONSULTA =====
    public static boolean exists(String name) {
        return movementCatalog.containsKey(name.toUpperCase());
    }

    public static List<String> getAllMovementNames() {
        return new ArrayList<>(movementCatalog.keySet());
    }
}
