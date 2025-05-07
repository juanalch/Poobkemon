package test;
import domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
 
class POOBkemonTest {
    private Trainer trainer1;
    private Trainer trainer2;
    private POOBkemon game;
    @BeforeEach
    void setUp() {
        trainer1 = new Person("Ash", "Red");
        trainer2 = new Person("Misty", "Blue");
        // Configuración básica de Pokémon e ítems
        Pokemon charizard = PokemonFactory.getPokemon("Charizard");
        Pokemon blastoise = PokemonFactory.getPokemon("Blastoise");
        trainer1.addPokemon(charizard);
        trainer2.addPokemon(blastoise);
        trainer1.addItem(new Normal()); // Potion
        trainer2.addItem(new Super());  // SuperPotion
    }
    @Test
    void testPOOBkemonCreation() {
        // Prueba 1: Creación correcta del juego
        game = new POOBkemon("NORMAL", "PvP", trainer1, trainer2);
        assertNotNull(game, "El juego debería crearse correctamente");
        assertEquals("NORMAL", game.getGameMode().name(), "Debería estar en modo NORMAL");
        assertEquals("PvP", game.getModality().name(), "Debería estar en modalidad PvP");
    }
    @Test
    void testAvailablePokemons() {
        // Prueba 2: Listado de Pokémon disponibles
        game = new POOBkemon("NORMAL", "PvP", trainer1, trainer2);
        List<Pokemon> availablePokemons = game.getAvailablePokemons();
        assertFalse(availablePokemons.isEmpty(), "Debería haber Pokémon disponibles");
        assertTrue(availablePokemons.size() >= 2, "Debería haber al menos 2 Pokémon");
    }
    @Test
    void testAvailableItems() {
        // Prueba 3: Listado de ítems disponibles
        game = new POOBkemon("NORMAL", "PvP", trainer1, trainer2);
        List<Item> availableItems = game.getAvailableItems();
        assertFalse(availableItems.isEmpty(), "Debería haber ítems disponibles");
        assertTrue(availableItems.size() >= 2, "Debería haber al menos 2 ítems");
    }
    @Test
    void testSelectTrainerPokemons() {
        // Prueba 4: Asignación de Pokémon a entrenador
        game = new POOBkemon("NORMAL", "PvP", trainer1, trainer2);
        List<String> pokemonNames = List.of("Charizard", "Venusaur");
        game.selectTrainerPokemons(trainer1, pokemonNames);
        assertEquals(2, trainer1.getPokemonTeam().size(), "Debería tener 2 Pokémon");
        assertEquals("Charizard", trainer1.getPokemonTeam().get(0).getName());
        assertEquals("Venusaur", trainer1.getPokemonTeam().get(1).getName());
    }
    @Test
    void testSelectTrainerItems() {
        // Prueba 5: Asignación de ítems a entrenador
        game = new POOBkemon("NORMAL", "PvP", trainer1, trainer2);
        List<String> itemNames = List.of("Potion", "SuperPotion");
        game.selectTrainerItems(trainer1, itemNames);
        assertEquals(2, trainer1.getItems().size(), "Debería tener 2 ítems");
        assertEquals("Potion", trainer1.getItems().get(0).getName());
        assertEquals("SuperPotion", trainer1.getItems().get(1).getName());
    }
    @Test
    void testSurvivalModeSetup() {
        // Prueba 6: Configuración del modo supervivencia
        game = new POOBkemon("SURVIVAL", "PvP", trainer1, trainer2);
        game.selectTrainerPokemons(trainer1, List.of()); // Lista vacía para modo supervivencia
        assertEquals(6, trainer1.getPokemonTeam().size(), "Debería tener 6 Pokémon aleatorios");
        assertTrue(trainer1.getItems().isEmpty(), "No debería tener ítems en modo supervivencia");
    }
    @Test
    void testStartGame() {
        // Prueba 7: Inicio del juego
        game = new POOBkemon("NORMAL", "PvP", trainer1, trainer2);
        assertDoesNotThrow(() -> game.startGame(), "El juego debería iniciar sin excepciones");
    }
    @Test
    void testGameOverConditions() {
        // Prueba 8: Condiciones de fin de juego
        game = new POOBkemon("NORMAL", "PvP", trainer1, trainer2);
        // Debilitar todos los Pokémon de un entrenador
        trainer1.getPokemonTeam().forEach(p -> p.takeDamage(p.getCurrentHealth()));
        assertTrue(game.isGameOver(), "El juego debería terminar cuando un entrenador pierde todos sus Pokémon");
    }
    @Test
    void testModalityValidation() {
        // Prueba 9: Validación de modalidades
        // PvP con dos humanos
        assertDoesNotThrow(() -> new POOBkemon("NORMAL", "PvP", trainer1, trainer2));
        // PvM con humano vs máquina
        Trainer machine = new AttackingTrainer();
        assertDoesNotThrow(() -> new POOBkemon("NORMAL", "PvM", trainer1, machine));
        // MvM con dos máquinas
        Trainer machine2 = new DefensiveTrainer();
        assertDoesNotThrow(() -> new POOBkemon("NORMAL", "MvM", machine, machine2));
        // Combinaciones inválidas deberían lanzar excepción
        assertThrows(IllegalArgumentException.class, 
            () -> new POOBkemon("NORMAL", "PvP", trainer1, machine));
    }
    @Test
    void testSaveAndLoadGame() {
        // Prueba 10: Persistencia del juego (métodos placeholder)
        game = new POOBkemon("NORMAL", "PvP", trainer1, trainer2);
        assertDoesNotThrow(() -> game.saveGame(), "Debería poder 'guardar' el juego");
        // Nota: La implementación real de carga necesitaría pruebas más complejas
    }
}