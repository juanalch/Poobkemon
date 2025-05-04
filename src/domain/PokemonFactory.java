package domain;

import java.util.*;

public class PokemonFactory {
    private static final Map<String, Pokemon> pokemonCatalog = new HashMap<>();

    static {
        initializePokemons();
    }

    private static void initializePokemons() {
        // ---- TIPO FUEGO (PRIMARIO) ----
        addPokemon(
            "Charizard", "FIRE", "FLYING",
            360, 293, 280, 328, 348, 295,
            new String[]{"Lanzallamas", "Garra Dragón", "Tajo Aéreo", "Terremoto"}
        );
        addPokemon(
            "Arcanine", "FIRE", null,
            380, 350, 300, 320, 330, 300,
            new String[]{"Giro Fuego", "Mordisco", "Velocidad Extrema", "Colmillo Ígneo"}
        );

        // ---- TIPO AGUA (PRIMARIO) ----
        addPokemon(
            "Blastoise", "WATER", null,
            362, 291, 328, 280, 295, 339,
            new String[]{"Hidrobomba", "Cabezazo", "Defensa Férrea", "Rayo Hielo"}
        );
        addPokemon(
            "Lapras", "WATER", "ICE",
            400, 250, 300, 280, 320, 350,
            new String[]{"Surf", "Viento Hielo", "Canto Helado", "Hidropulso"}
        );

        // ---- TIPO PLANTA (PRIMARIO) ----
        addPokemon(
            "Venusaur", "GRASS", "POISON",
            364, 289, 291, 284, 328, 328,
            new String[]{"Rayo Solar", "Drenadoras", "Somnífero", "Bomba Lodo"}
        );
        addPokemon(
            "Exeggutor", "GRASS", "PSYCHIC",
            340, 280, 300, 200, 350, 280,
            new String[]{"Latigazo", "Psicorrayo", "Síntesis", "Bomba Germen"}
        );

        // ---- TIPO ELÉCTRICO (PRIMARIO) ----
        addPokemon(
            "Raichu", "ELECTRIC", null,
            324, 306, 229, 350, 306, 284,
            new String[]{"Trueno", "Bola Voltio", "Ataque Rápido", "Onda Voltio"}
        );
        addPokemon(
            "Jolteon", "ELECTRIC", null,
            310, 280, 250, 380, 320, 290,
            new String[]{"Chispazo", "Doble Equipo", "Pin Misil", "Trueno"}
        );

        // ---- TIPO HIELO (PRIMARIO) ----
        addPokemon(
            "Delibird", "ICE", "FLYING",
            294, 229, 207, 273, 251, 207,
            new String[]{"Viento Hielo", "Regalo", "Ataque Ala", "Ráfaga"}
        );
        addPokemon(
            "Glalie", "ICE", null,
            320, 280, 300, 260, 280, 300,
            new String[]{"Rayo Hielo", "Colmillo Hielo", "Ventisca", "Explosión"}
        );

        // ---- TIPO LUCHA (PRIMARIO) ----
        addPokemon(
            "Machamp", "FIGHTING", null,
            384, 394, 284, 229, 251, 295,
            new String[]{"Golpe Karate", "Sumisión", "Patada Salto", "Puño Dinámico"}
        );
        addPokemon(
            "Hitmonlee", "FIGHTING", null,
            350, 400, 250, 300, 200, 350,
            new String[]{"Patada Baja", "Mega Patada", "Amago", "Reversa"}
        );

        // ---- TIPO VENENO (PRIMARIO) ----
        addPokemon(
            "Nidoking", "POISON", "GROUND",
            380, 350, 300, 280, 320, 290,
            new String[]{"Bomba Lodo", "Terremoto", "Cornada", "Tóxico"}
        );
        addPokemon(
            "Weezing", "POISON", null,
            340, 280, 400, 200, 280, 300,
            new String[]{"Bomba Lodo", "Humo", "Explosión", "Tóxico"}
        );

        // ---- TIPO TIERRA (PRIMARIO) ----
        addPokemon(
            "Donphan", "GROUND", null,
            384, 372, 372, 218, 240, 240,
            new String[]{"Terremoto", "Rodillo", "Rueda Fuego", "Giro Bola"}
        );
        addPokemon(
            "Golem", "GROUND", "ROCK",
            380, 400, 450, 180, 250, 300,
            new String[]{"Terremoto", "Lanzarrocas", "Explosión", "Magnitud"}
        );

        // ---- TIPO PSÍQUICO (PRIMARIO) ----
        addPokemon(
            "Gardevoir", "PSYCHIC", "FAIRY",
            340, 251, 251, 284, 383, 361,
            new String[]{"Psíquico", "Voz Cautivadora", "Bola Sombra", "Onda Certera"}
        );
        addPokemon(
            "Alakazam", "PSYCHIC", null,
            320, 250, 200, 400, 450, 300,
            new String[]{"Psíquico", "Premonición", "Bola Sombra", "Onda Mental"}
        );

        // ---- TIPO BICHO (PRIMARIO) ----
        addPokemon(
            "Scizor", "BUG", "STEEL",
            350, 400, 350, 250, 200, 300,
            new String[]{"Tijera X", "Garra Metal", "Ataque Rápido", "Corte Furia"}
        );
        addPokemon(
            "Heracross", "BUG", "FIGHTING",
            380, 420, 300, 280, 150, 350,
            new String[]{"Megacuerno", "Golpe Cuerpo", "Contraataque", "Puño Increíble"}
        );

        // ---- TIPO ROCA (PRIMARIO) ----
        addPokemon(
            "Tyranitar", "ROCK", "DARK",
            404, 403, 350, 243, 317, 328,
            new String[]{"Avalancha", "Garra Umbría", "Terremoto", "Hiperrayo"}
        );
        addPokemon(
            "Sudowoodo", "ROCK", null,
            350, 350, 400, 150, 100, 250,
            new String[]{"Lanzarrocas", "Impresionar", "Danza Espada", "Vendetta"}
        );

        // ---- TIPO FANTASMA (PRIMARIO) ----
        addPokemon(
            "Gengar", "GHOST", "POISON",
            324, 251, 240, 350, 394, 273,
            new String[]{"Bola Sombra", "Hidropulso", "Tinieblas", "Rayo Confuso"}
        );
        addPokemon(
            "Misdreavus", "GHOST", null,
            300, 200, 250, 320, 350, 280,
            new String[]{"Bola Sombra", "Grito Espanto", "Tinieblas", "Psíquico"}
        );

        // ---- TIPO DRAGÓN (PRIMARIO) ----
        addPokemon(
            "Dragonite", "DRAGON", "FLYING",
            386, 403, 317, 284, 328, 328,
            new String[]{"Garra Dragón", "Hiperrayo", "Vuelo", "Torbellino"}
        );
        addPokemon(
            "Salamence", "DRAGON", "FLYING",
            380, 420, 300, 320, 350, 280,
            new String[]{"Garra Dragón", "Lanzallamas", "Vuelo", "Cólera"}
        );

        // ---- TIPO SINIESTRO (PRIMARIO) ----
        addPokemon(
            "Umbreon", "DARK", null,
            350, 250, 350, 280, 200, 400,
            new String[]{"Garra Umbría", "Persecución", "Venganza", "Tinieblas"}
        );
        addPokemon(
            "Houndoom", "DARK", "FIRE",
            360, 350, 250, 330, 400, 280,
            new String[]{"Lanzallamas", "Garra Umbría", "Triturar", "Infierno"}
        );

        // ---- TIPO ACERO (PRIMARIO) ----
        addPokemon(
            "Metagross", "STEEL", "PSYCHIC",
            364, 405, 394, 262, 317, 306,
            new String[]{"Golpe Metal", "Psíquico", "Terremoto", "Meteorobola"}
        );
        addPokemon(
            "Skarmory", "STEEL", "FLYING",
            350, 300, 450, 250, 150, 300,
            new String[]{"Ataque Ala", "Garra Metal", "Viento Plata", "Corte Aéreo"}
        );

        // ---- TIPO HADA (PRIMARIO) ----
        addPokemon(
            "Togetic", "FAIRY", "FLYING",
            314, 196, 295, 196, 284, 339,
            new String[]{"Viento Feérico", "Metronomo", "Danza Lluvia", "Ataque Ala"}
        );
        addPokemon(
            "Clefable", "FAIRY", null,
            360, 250, 280, 200, 350, 350,
            new String[]{"Brillo Mágico", "Meteorobola", "Danza Luz", "Onda Certera"}
        );
    }

    // ========== MÉTODOS AUXILIARES ==========
    private static void addPokemon(String name, String type1, String type2, 
                                  int health, int attack, int defense, 
                                  int speed, int specialAttack, int specialDefense,
                                  String[] moveNames) {
        Pokemon pokemon = new Pokemon(
            name, type1, type2, 
            health, attack, defense, 
            speed, specialAttack, specialDefense
        );

        // Asignar movimientos
        for (String moveName : moveNames) {
            Movement move = MovementFactory.getMovement(moveName);
            if (move != null) {
                pokemon.addMovement(move, pokemon.getMovements().size());
            }
        }

        pokemonCatalog.put(name.toUpperCase(), pokemon);
    }

    public static Pokemon getPokemon(String name) {
        Pokemon original = pokemonCatalog.get(name.toUpperCase());
        if (original == null) return null;

        // Clonar incluyendo movimientos
        Pokemon clone = new Pokemon(
            original.getName(),
            original.getType1(),
            original.getType2(),
            original.getHealth(),
            original.getAttack(),
            original.getDefense(),
            original.getSpeed(),
            original.getSpecialAttack(),
            original.getSpecialDefense()
        );

        // Copiar movimientos al clon
        for (Movement move : original.getMovements()) {
            clone.addMovement(move.clone(), clone.getMovements().size());
        }

        return clone;
    }

    public static List<Pokemon> getAllPokemons() {
        return new ArrayList<>(pokemonCatalog.values());
    }

    public static List<Pokemon> getRandomSurvivalTeam() {
        List<Pokemon> allPokemons = new ArrayList<>(pokemonCatalog.values());
        Collections.shuffle(allPokemons);
        return allPokemons.subList(0, Math.min(6, allPokemons.size()));
    }
}