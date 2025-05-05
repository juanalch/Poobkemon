package domain;
import java.util.*;


public class POOBkemon {
    public enum GameMode { NORMAL, SURVIVAL }
    public enum Modality { PvP, PvM, MvM }
    
    private GameMode gameMode;
    private Modality modality;
    private Battle battle;
    private boolean isPaused;

    // -------------------- Constructor principal --------------------
    public POOBkemon(String gameMode, String modality, Trainer trainer1, Trainer trainer2) {
        this.gameMode = GameMode.valueOf(gameMode.toUpperCase());
        this.modality = Modality.valueOf(modality.toUpperCase());
        validateModality(trainer1, trainer2);
        this.battle = new Battle(trainer1, trainer2);
    }

    // -------------------- Métodos de configuración inicial --------------------
    public List<Pokemon> getAvailablePokemons() {
        return PokemonFactory.getAllPokemons();
    }

    public List<Item> getAvailableItems() {
        return ItemFactory.getBaseItems();
    }

    public void selectTrainerPokemons(Trainer trainer, List<String> pokemonNames) {
        if (gameMode == GameMode.SURVIVAL) {
            // Modo supervivencia: 6 Pokémon aleatorios nivel 100
            List<Pokemon> randomTeam = PokemonFactory.getRandomSurvivalTeam();
            randomTeam.forEach(trainer::addPokemon);
        } else {
            // Modo normal: Selección manual
            pokemonNames.stream()
                .map(PokemonFactory::getPokemon)
                .filter(Objects::nonNull)
                .forEach(trainer::addPokemon);
        }
    }

    public void selectTrainerItems(Trainer trainer, List<String> itemNames) {
        if (gameMode == GameMode.SURVIVAL) return; // En supervivencia no hay ítems
        
        itemNames.stream()
            .map(ItemFactory::createItem)
            .filter(Objects::nonNull)
            .forEach(trainer::addItem);
    }

    // -------------------- Control del juego --------------------
    public void startGame() {
        if (battle == null) throw new IllegalStateException("Batalla no configurada");
        applyModeRules();
        battle.startBattle();
    }

    public void endCurrentGame() {
        if (battle != null) {
            battle.endBattle("Partida finalizada por el usuario");
            battle = null;
        }
    }

    public void resumeGame() {
        if (isPaused && battle != null) {
            isPaused = false;
            battle.startBattle();
        }
    }

    public boolean isGameOver() {
        return battle == null || battle.isBattleEnded();
    }

    // -------------------- Métodos de estado --------------------
    public Trainer getCurrentTurn() {
        return battle != null ? battle.getCurrentTrainer() : null;
    }

    public List<Pokemon> getTrainerTeam(Trainer trainer) {
        return trainer.getPokemonTeam();
    }

    public List<Item> getTrainerItems(Trainer trainer) {
        return trainer.getItems();
    }

    
    public Battle getBattle() {
        return this.battle;
    }

    

    // -------------------- Métodos internos --------------------
    private void validateModality(Trainer t1, Trainer t2) {
        switch (modality) {
            case PvP:
                if (!t1.isHuman() || !t2.isHuman()) 
                    throw new IllegalArgumentException("PvP requiere dos jugadores humanos");
                break;
            case PvM:
                if (!t1.isHuman() || t2.isHuman()) 
                    throw new IllegalArgumentException("PvM requiere un humano vs máquina");
                break;
            case MvM:
                if (t1.isHuman() || t2.isHuman()) 
                    throw new IllegalArgumentException("MvM requiere dos máquinas");
                break;
        }
    }

    private void applyModeRules() {
        if (gameMode == GameMode.SURVIVAL) {
            battle.getTrainers().forEach(trainer -> {
                // Todos los Pokémon a nivel 100 con movimientos predefinidos
                trainer.getPokemonTeam().forEach(pokemon -> {
                    pokemon.setLevel(100);
                    pokemon.restoreAllPP();
                });
            });
        }
    }

    // -------------------- Persistencia (placeholder) --------------------
    public void saveGame() {
        // Lógica para serializar el estado del juego
    }

}
