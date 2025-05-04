package domain;
import java.util.*; 

public abstract class Trainer {
    private final String name;
    private final String color;
    private final List<Pokemon> pokemonTeam;
    private final List<Item> items;
    private Pokemon activePokemon;
    private boolean isHuman;

    public Trainer(String name, String color, boolean isHuman) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Nombre inválido");
        if (color == null) throw new IllegalArgumentException("Color inválido");
        
        this.name = name;
        this.color = color;
        this.isHuman = isHuman;
        this.pokemonTeam = new ArrayList<>(6);
        this.items = new ArrayList<>(4); // Máximo 4 ítems
    }

    // Método abstracto para implementación específica de cada tipo de entrenador
    public abstract void decideAction(Battle battle);

    // --- Métodos principales ---
    public void switchPokemon(Pokemon newPokemon) {
        if (!pokemonTeam.contains(newPokemon)) {
            throw new IllegalArgumentException("Pokémon no pertenece al equipo");
        }
        if (newPokemon.isFainted()) {
            throw new IllegalStateException("No se puede cambiar a un Pokémon debilitado");
        }
        this.activePokemon = newPokemon;
    }

    public void useItem(Item item, Pokemon target) {
        if (!items.contains(item)) {
            throw new IllegalArgumentException("El entrenador no posee este ítem");
        }
        item.applyEffect(target);
        items.remove(item);
    }

    public void addPokemon(Pokemon pokemon) {
        if (pokemonTeam.size() >= 6) {
            throw new IllegalStateException("Equipo completo (máximo 6 Pokémon)");
        }
        pokemonTeam.add(pokemon);
        if (activePokemon == null) activePokemon = pokemon;
    }

    public void addItem(Item item) {
        if (items.size() >= 4) {
            throw new IllegalStateException("Inventario lleno (máximo 4 ítems)");
        }
        items.add(item);
    }

    // --- Getters y estado ---
    public Pokemon getActivePokemon() {
        return activePokemon;
    }

    public List<Pokemon> getPokemonTeam() {
        return new ArrayList<>(pokemonTeam);
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    public boolean hasAvailablePokemon() {
        return pokemonTeam.stream().anyMatch(p -> !p.isFainted());
    }

    public boolean isHuman() {
        return isHuman;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    // --- Métodos de batalla ---
    public void notifyPokemonFainted(Pokemon faintedPokemon) {
        if (activePokemon == faintedPokemon) {
            Optional<Pokemon> next = pokemonTeam.stream()
                .filter(p -> !p.isFainted())
                .findFirst();
            
            next.ifPresentOrElse(
                this::switchPokemon,
                () -> { throw new GameException(name + " ha perdido todos sus Pokémon"); }
            );
        }
    }
}
