package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Factory class to create predefined Pokemon instances with their stats and moves.
 */
public class PokemonFactory {

    // Fire Type Pokemons
    public static Pokemon createCharmander() {
        Pokemon charmander = new Pokemon("Charmander", "Fire", null, 39, 52, 43, 65, 60, 50);
        charmander.addMovement(new Movement("Flamethrower", "Fire", 90, 100, 15, 0));
        charmander.addMovement(new Movement("Scratch", "Normal", 40, 100, 35, 0));
        charmander.addMovement(new Movement("Ember", "Fire", 40, 100, 25, 0));
        charmander.addMovement(new Movement("Growl", "Normal", 0, 100, 40, 0));
        return charmander;
    }

    public static Pokemon createVulpix() {
        Pokemon vulpix = new Pokemon("Vulpix", "Fire", null, 38, 41, 40, 65, 50, 50);
        vulpix.addMovement(new Movement("Ember", "Fire", 40, 100, 25, 0));
        vulpix.addMovement(new Movement("Quick Attack", "Normal", 40, 100, 30, 0));
        vulpix.addMovement(new Movement("Confuse Ray", "Ghost", 0, 100, 10, 0));
        vulpix.addMovement(new Movement("Tail Whip", "Normal", 0, 100, 30, 0));
        return vulpix;
    }

    // Water Type Pokemons
    public static Pokemon createSquirtle() {
        Pokemon squirtle = new Pokemon("Squirtle", "Water", null, 44, 48, 65, 43, 50, 64);
        squirtle.addMovement(new Movement("Water Gun", "Water", 40, 100, 25, 0));
        squirtle.addMovement(new Movement("Tackle", "Normal", 40, 100, 35, 0));
        squirtle.addMovement(new Movement("Bubble", "Water", 40, 100, 30, 0));
        squirtle.addMovement(new Movement("Withdraw", "Normal", 0, 100, 40, 0));
        return squirtle;
    }

    public static Pokemon createPsyduck() {
        Pokemon psyduck = new Pokemon("Psyduck", "Water", null, 50, 52, 48, 55, 60, 50);
        psyduck.addMovement(new Movement("Confusion", "Psychic", 50, 100, 25, 0));
        psyduck.addMovement(new Movement("Scratch", "Normal", 40, 100, 35, 0));
        psyduck.addMovement(new Movement("Water Gun", "Water", 40, 100, 25, 0));
        psyduck.addMovement(new Movement("Disable", "Normal", 0, 100, 20, 0));
        return psyduck;
    }

    // Grass Type Pokemons
    public static Pokemon createBulbasaur() {
        Pokemon bulbasaur = new Pokemon("Bulbasaur", "Grass", "Poison", 45, 49, 49, 45, 65, 65);
        bulbasaur.addMovement(new Movement("Vine Whip", "Grass", 45, 100, 25, 0));
        bulbasaur.addMovement(new Movement("Tackle", "Normal", 40, 100, 35, 0));
        bulbasaur.addMovement(new Movement("Leech Seed", "Grass", 0, 90, 10, 0));
        bulbasaur.addMovement(new Movement("Growl", "Normal", 0, 100, 40, 0));
        return bulbasaur;
    }

    public static Pokemon createOddish() {
        Pokemon oddish = new Pokemon("Oddish", "Grass", "Poison", 45, 50, 55, 30, 75, 65);
        oddish.addMovement(new Movement("Absorb", "Grass", 20, 100, 25, 0));
        oddish.addMovement(new Movement("Acid", "Poison", 40, 100, 30, 0));
        oddish.addMovement(new Movement("Sleep Powder", "Grass", 0, 75, 15, 0));
        oddish.addMovement(new Movement("Stun Spore", "Grass", 0, 75, 30, 0));
        return oddish;
    }

    // Electric Type Pokemons
    public static Pokemon createPikachu() {
        Pokemon pikachu = new Pokemon("Pikachu", "Electric", null, 35, 55, 40, 90, 50, 50);
        pikachu.addMovement(new Movement("Thunderbolt", "Electric", 90, 100, 15, 0));
        pikachu.addMovement(new Movement("Quick Attack", "Normal", 40, 100, 30, 1));
        pikachu.addMovement(new Movement("Electro Ball", "Electric", 80, 100, 10, 0));
        pikachu.addMovement(new Movement("Growl", "Normal", 0, 100, 40, 0));
        return pikachu;
    }

    public static Pokemon createMagnemite() {
        Pokemon magnemite = new Pokemon("Magnemite", "Electric", "Steel", 25, 35, 70, 45, 95, 55);
        magnemite.addMovement(new Movement("Spark", "Electric", 65, 100, 20, 0));
        magnemite.addMovement(new Movement("Tackle", "Normal", 40, 100, 35, 0));
        magnemite.addMovement(new Movement("Thunder Wave", "Electric", 0, 90, 20, 0));
        magnemite.addMovement(new Movement("Magnet Bomb", "Steel", 60, 100, 20, 0));
        return magnemite;
    }

    // Psychic Type Pokemons
    public static Pokemon createAbra() {
        Pokemon abra = new Pokemon("Abra", "Psychic", null, 25, 20, 15, 90, 105, 55);
        abra.addMovement(new Movement("Teleport", "Psychic", 0, 100, 20, 0));
        abra.addMovement(new Movement("Psybeam", "Psychic", 65, 100, 20, 0));
        abra.addMovement(new Movement("Confusion", "Psychic", 50, 100, 25, 0));
        abra.addMovement(new Movement("Calm Mind", "Psychic", 0, 100, 20, 0));
        return abra;
    }

    public static Pokemon createDrowzee() {
        Pokemon drowzee = new Pokemon("Drowzee", "Psychic", null, 60, 48, 45, 42, 43, 90);
        drowzee.addMovement(new Movement("Confusion", "Psychic", 50, 100, 25, 0));
        drowzee.addMovement(new Movement("Headbutt", "Normal", 70, 100, 15, 0));
        drowzee.addMovement(new Movement("Hypnosis", "Psychic", 0, 60, 20, 0));
        drowzee.addMovement(new Movement("Psywave", "Psychic", 0, 100, 15, 0));
        return drowzee;
    }

    // Rock Type Pokemons
    public static Pokemon createGeodude() {
        Pokemon geodude = new Pokemon("Geodude", "Rock", "Ground", 40, 80, 100, 20, 30, 30);
        geodude.addMovement(new Movement("Rock Throw", "Rock", 50, 90, 15, 0));
        geodude.addMovement(new Movement("Tackle", "Normal", 40, 100, 35, 0));
        geodude.addMovement(new Movement("Magnitude", "Ground", 70, 100, 30, 0));
        geodude.addMovement(new Movement("Defense Curl", "Normal", 0, 100, 40, 0));
        return geodude;
    }

    public static Pokemon createOnix() {
        Pokemon onix = new Pokemon("Onix", "Rock", "Ground", 35, 45, 160, 70, 30, 45);
        onix.addMovement(new Movement("Rock Slide", "Rock", 75, 90, 10, 0));
        onix.addMovement(new Movement("Bind", "Normal", 15, 75, 20, 0));
        onix.addMovement(new Movement("Screech", "Normal", 0, 85, 40, 0));
        onix.addMovement(new Movement("Earthquake", "Ground", 100, 100, 10, 0));
        return onix;
    }

    // Ground Type Pokemons
    public static Pokemon createSandshrew() {
        Pokemon sandshrew = new Pokemon("Sandshrew", "Ground", null, 50, 75, 85, 40, 20, 30);
        sandshrew.addMovement(new Movement("Slash", "Normal", 70, 100, 20, 0));
        sandshrew.addMovement(new Movement("Scratch", "Normal", 40, 100, 35, 0));
        sandshrew.addMovement(new Movement("Sand Attack", "Ground", 0, 100, 15, 0));
        sandshrew.addMovement(new Movement("Poison Sting", "Poison", 15, 100, 35, 0));
        return sandshrew;
    }

    public static Pokemon createDiglett() {
        Pokemon diglett = new Pokemon("Diglett", "Ground", null, 10, 55, 25, 95, 35, 45);
        diglett.addMovement(new Movement("Dig", "Ground", 80, 100, 10, 0));
        diglett.addMovement(new Movement("Scratch", "Normal", 40, 100, 35, 0));
        diglett.addMovement(new Movement("Sand Attack", "Ground", 0, 100, 15, 0));
        diglett.addMovement(new Movement("Slash", "Normal", 70, 100, 20, 0));
        return diglett;
    }

    // Flying Type Pokemons
    public static Pokemon createPidgey() {
        Pokemon pidgey = new Pokemon("Pidgey", "Normal", "Flying", 40, 45, 40, 56, 35, 35);
        pidgey.addMovement(new Movement("Gust", "Flying", 40, 100, 35, 0));
        pidgey.addMovement(new Movement("Tackle", "Normal", 40, 100, 35, 0));
        pidgey.addMovement(new Movement("Quick Attack", "Normal", 40, 100, 30, 1));
        pidgey.addMovement(new Movement("Sand Attack", "Ground", 0, 100, 15, 0));
        return pidgey;
    }

    public static Pokemon createSpearow() {
        Pokemon spearow = new Pokemon("Spearow", "Normal", "Flying", 40, 60, 30, 70, 31, 31);
        spearow.addMovement(new Movement("Peck", "Flying", 35, 100, 35, 0));
        spearow.addMovement(new Movement("Quick Attack", "Normal", 40, 100, 30, 1));
        spearow.addMovement(new Movement("Fury Attack", "Normal", 15, 85, 20, 0));
        spearow.addMovement(new Movement("Growl", "Normal", 0, 100, 40, 0));
        return spearow;
    }

    // Bug Type Pokemons
    public static Pokemon createCaterpie() {
        Pokemon caterpie = new Pokemon("Caterpie", "Bug", null, 45, 30, 35, 45, 20, 20);
        caterpie.addMovement(new Movement("Tackle", "Normal", 40, 100, 35, 0));
        caterpie.addMovement(new Movement("String Shot", "Bug", 0, 95, 40, 1));
        caterpie.addMovement(new Movement("Bug Bite", "Bug", 60, 100, 20, 0));
        caterpie.addMovement(new Movement("Harden", "Normal", 0, 100, 30, 0));
        return caterpie;
    }

    public static Pokemon createWeedle() {
        Pokemon weedle = new Pokemon("Weedle", "Bug", "Poison", 40, 35, 30, 50, 20, 20);
        weedle.addMovement(new Movement("Poison Sting", "Poison", 15, 100, 35, 0));
        weedle.addMovement(new Movement("String Shot", "Bug", 0, 95, 40, 1));
        weedle.addMovement(new Movement("Bug Bite", "Bug", 60, 100, 20, 0));
        weedle.addMovement(new Movement("Harden", "Normal", 0, 100, 30, 0));
        return weedle;
    }

    // Poison Type Pokemons
    public static Pokemon createEkans() {
        Pokemon ekans = new Pokemon("Ekans", "Poison", null, 35, 60, 44, 55, 40, 54);
        ekans.addMovement(new Movement("Poison Sting", "Poison", 15, 100, 35, 0));
        ekans.addMovement(new Movement("Bite", "Dark", 60, 100, 25, 0));
        ekans.addMovement(new Movement("Acid", "Poison", 40, 100, 30, 0));
        ekans.addMovement(new Movement("Wrap", "Normal", 15, 90, 20, 0));
        return ekans;
    }

    public static Pokemon createNidoranF() {
        Pokemon nidoranF = new Pokemon("Nidoran♀", "Poison", null, 55, 47, 52, 41, 40, 40);
        nidoranF.addMovement(new Movement("Poison Sting", "Poison", 15, 100, 35, 0));
        nidoranF.addMovement(new Movement("Scratch", "Normal", 40, 100, 35, 0));
        nidoranF.addMovement(new Movement("Tail Whip", "Normal", 0, 100, 30, 0));
        nidoranF.addMovement(new Movement("Double Kick", "Fighting", 30, 100, 30, 0));
        return nidoranF;
    }

    // Fighting Type Pokemons
    public static Pokemon createMankey() {
        Pokemon mankey = new Pokemon("Mankey", "Fighting", null, 40, 80, 35, 70, 35, 45);
        mankey.addMovement(new Movement("Low Kick", "Fighting", 50, 100, 20, 0));
        mankey.addMovement(new Movement("Scratch", "Normal", 40, 100, 35, 0));
        mankey.addMovement(new Movement("Karate Chop", "Fighting", 50, 100, 25, 0));
        mankey.addMovement(new Movement("Focus Energy", "Normal", 0, 100, 30, 0));
        return mankey;
    }

    public static Pokemon createMachop() {
        Pokemon machop = new Pokemon("Machop", "Fighting", null, 70, 80, 50, 35, 35, 35);
        machop.addMovement(new Movement("Karate Chop", "Fighting", 50, 100, 25, 0));
        machop.addMovement(new Movement("Low Kick", "Fighting", 50, 100, 20, 0));
        machop.addMovement(new Movement("Leer", "Normal", 0, 100, 30, 0));
        machop.addMovement(new Movement("Focus Energy", "Normal", 0, 100, 30, 0));
        return machop;
    }

    // Ghost Type Pokemons
    public static Pokemon createGastly() {
        Pokemon gastly = new Pokemon("Gastly", "Ghost", "Poison", 30, 35, 30, 80, 100, 35);
        gastly.addMovement(new Movement("Lick", "Ghost", 30, 100, 30, 0));
        gastly.addMovement(new Movement("Confuse Ray", "Ghost", 0, 100, 10, 0));
        gastly.addMovement(new Movement("Night Shade", "Ghost", 0, 100, 15, 0));
        gastly.addMovement(new Movement("Hypnosis", "Psychic", 0, 60, 20, 0));
        return gastly;
    }

    public static Pokemon createHaunter() {
        Pokemon haunter = new Pokemon("Haunter", "Ghost", "Poison", 45, 50, 45, 95, 115, 55);
        haunter.addMovement(new Movement("Shadow Ball", "Ghost", 80, 100, 15, 0));
        haunter.addMovement(new Movement("Lick", "Ghost", 30, 100, 30, 0));
        haunter.addMovement(new Movement("Confuse Ray", "Ghost", 0, 100, 10, 0));
        haunter.addMovement(new Movement("Mean Look", "Normal", 0, 100, 5, 0));
        return haunter;
    }

    // Ice Type Pokemons
    public static Pokemon createSeel() {
        Pokemon seel = new Pokemon("Seel", "Ice", null, 65, 45, 55, 45, 45, 70);
        seel.addMovement(new Movement("Aurora Beam", "Ice", 65, 100, 20, 0));
        seel.addMovement(new Movement("Headbutt", "Normal", 70, 100, 15, 0));
        seel.addMovement(new Movement("Icy Wind", "Ice", 55, 95, 15, 0));
        seel.addMovement(new Movement("Growl", "Normal", 0, 100, 40, 0));
        return seel;
    }

    public static Pokemon createJynx() {
        Pokemon jynx = new Pokemon("Jynx", "Ice", "Psychic", 65, 50, 35, 95, 115, 95);
        jynx.addMovement(new Movement("Ice Punch", "Ice", 75, 100, 15, 0));
        jynx.addMovement(new Movement("Lick", "Ghost", 30, 100, 30, 0));
        jynx.addMovement(new Movement("Lovely Kiss", "Normal", 0, 75, 10, 0));
        jynx.addMovement(new Movement("Powder Snow", "Ice", 40, 100, 25, 0));
        return jynx;
    }

    // Dragon Type Pokemons
    public static Pokemon createDratini() {
        Pokemon dratini = new Pokemon("Dratini", "Dragon", null, 41, 64, 45, 50, 50, 50);
        dratini.addMovement(new Movement("Dragon Rage", "Dragon", 0, 100, 10, 0));
        dratini.addMovement(new Movement("Wrap", "Normal", 15, 90, 20, 0));
        dratini.addMovement(new Movement("Leer", "Normal", 0, 100, 30, 0));
        dratini.addMovement(new Movement("Thunder Wave", "Electric", 0, 90, 20, 0));
        return dratini;
    }

    public static Pokemon createDragonair() {
        Pokemon dragonair = new Pokemon("Dragonair", "Dragon", null, 61, 84, 65, 70, 70, 70);
        dragonair.addMovement(new Movement("Dragon Breath", "Dragon", 60, 100, 20, 0));
        dragonair.addMovement(new Movement("Thunder Wave", "Electric", 0, 90, 20, 0));
        dragonair.addMovement(new Movement("Slam", "Normal", 80, 75, 20, 0));
        dragonair.addMovement(new Movement("Agility", "Psychic", 0, 100, 30, 0));
        return dragonair;
    }

    // Dark Type Pokemons
    public static Pokemon createUmbreon() {
        Pokemon umbreon = new Pokemon("Umbreon", "Dark", null, 95, 65, 110, 65, 60, 130);
        umbreon.addMovement(new Movement("Faint Attack", "Dark", 60, 100, 20, 0));
        umbreon.addMovement(new Movement("Quick Attack", "Normal", 40, 100, 30, 1));
        umbreon.addMovement(new Movement("Confuse Ray", "Ghost", 0, 100, 10, 0));
        umbreon.addMovement(new Movement("Moonlight", "Fairy", 0, 100, 5, 0));
        return umbreon;
    }

    public static Pokemon createHoundour() {
        Pokemon houndour = new Pokemon("Houndour", "Dark", "Fire", 45, 60, 30, 65, 80, 50);
        houndour.addMovement(new Movement("Bite", "Dark", 60, 100, 25, 0));
        houndour.addMovement(new Movement("Ember", "Fire", 40, 100, 25, 0));
        houndour.addMovement(new Movement("Smog", "Poison", 30, 70, 20, 0));
        houndour.addMovement(new Movement("Howl", "Normal", 0, 100, 40, 0));
        return houndour;
    }

    // Steel Type Pokemons
    public static Pokemon createSteelix() {
        Pokemon steelix = new Pokemon("Steelix", "Steel", "Ground", 75, 85, 200, 30, 55, 65);
        steelix.addMovement(new Movement("Iron Tail", "Steel", 100, 75, 15, 0));
        steelix.addMovement(new Movement("Rock Throw", "Rock", 50, 90, 15, 0));
        steelix.addMovement(new Movement("Harden", "Normal", 0, 100, 30, 0));
        steelix.addMovement(new Movement("Bind", "Normal", 15, 75, 20, 0));
        return steelix;
    }

    public static Pokemon createSkarmory() {
        Pokemon skarmory = new Pokemon("Skarmory", "Steel", "Flying", 65, 80, 140, 70, 40, 70);
        skarmory.addMovement(new Movement("Steel Wing", "Steel", 70, 90, 25, 0));
        skarmory.addMovement(new Movement("Peck", "Flying", 35, 100, 35, 0));
        skarmory.addMovement(new Movement("Sand Attack", "Ground", 0, 100, 15, 0));
        skarmory.addMovement(new Movement("Swift", "Normal", 60, 100, 20, 0));
        return skarmory;
    }

    // Fairy Type Pokemons
    public static Pokemon createClefairy() {
        Pokemon clefairy = new Pokemon("Clefairy", "Fairy", null, 70, 45, 48, 35, 60, 65);
        clefairy.addMovement(new Movement("Moonblast", "Fairy", 95, 100, 15, 0));
        clefairy.addMovement(new Movement("Pound", "Normal", 40, 100, 35, 0));
        clefairy.addMovement(new Movement("Growl", "Normal", 0, 100, 40, 0));
        clefairy.addMovement(new Movement("Sing", "Normal", 0, 55, 15, 0));
        return clefairy;
    }

    public static Pokemon createJigglypuff() {
        Pokemon jigglypuff = new Pokemon("Jigglypuff", "Normal", "Fairy", 115, 45, 20, 20, 45, 25);
        jigglypuff.addMovement(new Movement("Dazzling Gleam", "Fairy", 80, 100, 10, 0));
        jigglypuff.addMovement(new Movement("Pound", "Normal", 40, 100, 35, 0));
        jigglypuff.addMovement(new Movement("Sing", "Normal", 0, 55, 15, 0));
        jigglypuff.addMovement(new Movement("Defense Curl", "Normal", 0, 100, 40, 0));
        return jigglypuff;
    }

    // Normal Type Pokemons
    public static Pokemon createRattata() {
        Pokemon rattata = new Pokemon("Rattata", "Normal", null, 30, 56, 35, 72, 25, 35);
        rattata.addMovement(new Movement("Tackle", "Normal", 40, 100, 35, 0));
        rattata.addMovement(new Movement("Quick Attack", "Normal", 40, 100, 30, 1));
        rattata.addMovement(new Movement("Tail Whip", "Normal", 0, 100, 30, 0));
        rattata.addMovement(new Movement("Hyper Fang", "Normal", 80, 90, 15, 0));
        return rattata;
    }

    public static Pokemon createEevee() {
        Pokemon eevee = new Pokemon("Eevee", "Normal", null, 55, 55, 50, 55, 45, 65);
        eevee.addMovement(new Movement("Tackle", "Normal", 40, 100, 35, 0));
        eevee.addMovement(new Movement("Quick Attack", "Normal", 40, 100, 30, 1));
        eevee.addMovement(new Movement("Tail Whip", "Normal", 0, 100, 30, 0));
        eevee.addMovement(new Movement("Bite", "Dark", 60, 100, 25, 0));
        return eevee;
    }

    public static List<Pokemon> getRandomSurvivalTeam() {
        List<Pokemon> allPokemons = getAvailablePokemons();
        Collections.shuffle(allPokemons); // Mezcla los Pokémon
        
        List<Pokemon> team = new ArrayList<>(6);
        
        // Selecciona 6 Pokémon únicos aleatoriamente
        for (int i = 0; i < 6 && i < allPokemons.size(); i++) {
            Pokemon original = allPokemons.get(i);
            // Crea una copia del Pokémon con nivel 100
            Pokemon leveledPokemon = createLevel100Copy(original);
            team.add(leveledPokemon);
        }
        
        return team;
    }

    private static Pokemon createLevel100Copy(Pokemon original) {
        // Crea un nuevo Pokémon con los mismos atributos pero nivel 100
        Pokemon copy = new Pokemon(
            original.getName(),
            original.getType1(),
            original.getType2(),
            original.getHealth(),  // PS base (se escalan según nivel)
            original.getAttack(),
            original.getDefense(),
            original.getSpeed(),
            original.getSpecialAttack(),
            original.getSpecialDefense()
        );
        
        // Ajustar stats para nivel 100 (fórmula de escalado)
        scaleStatsToLevel100(copy);
        
        // Copiar movimientos
        original.getMovements().forEach(copy::addMovement);
        
        return copy;
    }

    private static void scaleStatsToLevel100(Pokemon pokemon) {
        // Fórmula simplificada para escalar stats a nivel 100
        // PS = (base * 2) + 110 (fórmula aproximada de Pokémon)
        pokemon.setHealth((pokemon.getHealth() * 2) + 110);
        
        // Otras stats: (base * 2) + 5
        pokemon.setAttack((pokemon.getAttack() * 2) + 5);
        pokemon.setDefense((pokemon.getDefense() * 2) + 5);
        pokemon.setSpeed((pokemon.getSpeed() * 2) + 5);
        pokemon.setSpecialAttack((pokemon.getSpecialAttack() * 2) + 5);
        pokemon.setSpecialDefense((pokemon.getSpecialDefense() * 2) + 5);
        
        pokemon.setLevel(100);
        pokemon.setCurrentHealth(pokemon.getHealth()); // PS al máximo
    }

    /**
     * Obtiene un Pokémon por su nombre (ignorando mayúsculas/minúsculas)
     * @param name Nombre del Pokémon a buscar
     * @return Instancia del Pokémon o null si no se encuentra
     */
    public static Pokemon getPokemon(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        
        // Normalizamos el nombre (eliminamos espacios y convertimos a minúsculas)
        String normalizedSearch = name.trim().toLowerCase();
        
        // Buscamos en todos los Pokémon disponibles
        for (Pokemon pokemon : getAvailablePokemons()) {
            if (pokemon.getName().toLowerCase().equals(normalizedSearch)) {
                return createCopy(pokemon); // Devolvemos una copia para evitar modificar el original
            }
        }
        
        return null; // No encontrado
    }

    private static Pokemon createCopy(Pokemon original) {
        Pokemon copy = new Pokemon(
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
        
        // Copiar movimientos
        for (Movement move : original.getMovements()) {
            copy.addMovement(new Movement(
                move.getName(),
                move.getType(),
                move.getPower(),
                move.getAccuracy(),
                move.getMaxPP(),
                move.getPriority()
            ));
        }
        
        return copy;
    }

    /**
     * Returns a list of all available predefined Pokemons.
     * @return list of Pokemon instances
     */
    public static List<Pokemon> getAvailablePokemons() {
        List<Pokemon> pokemons = new ArrayList<>();
        // Fire
        pokemons.add(createCharmander());
        pokemons.add(createVulpix());
        // Water
        pokemons.add(createSquirtle());
        pokemons.add(createPsyduck());
        // Grass
        pokemons.add(createBulbasaur());
        pokemons.add(createOddish());
        // Electric
        pokemons.add(createPikachu());
        pokemons.add(createMagnemite());
        // Psychic
        pokemons.add(createAbra());
        pokemons.add(createDrowzee());
        // Rock
        pokemons.add(createGeodude());
        pokemons.add(createOnix());
        // Ground
        pokemons.add(createSandshrew());
        pokemons.add(createDiglett());
        // Flying
        pokemons.add(createPidgey());
        pokemons.add(createSpearow());
        // Bug
        pokemons.add(createCaterpie());
        pokemons.add(createWeedle());
        // Poison
        pokemons.add(createEkans());
        pokemons.add(createNidoranF());
        // Fighting
        pokemons.add(createMankey());
        pokemons.add(createMachop());
        // Ghost
        pokemons.add(createGastly());
        pokemons.add(createHaunter());
        // Ice
        pokemons.add(createSeel());
        pokemons.add(createJynx());
        // Dragon
        pokemons.add(createDratini());
        pokemons.add(createDragonair());
        // Dark
        pokemons.add(createUmbreon());
        pokemons.add(createHoundour());
        // Steel
        pokemons.add(createSteelix());
        pokemons.add(createSkarmory());
        // Fairy
        pokemons.add(createClefairy());
        pokemons.add(createJigglypuff());
        // Normal
        pokemons.add(createRattata());
        pokemons.add(createEevee());
        
        return pokemons;
    }
}