package domain;

public class ItemFactory {

	public Item createItem(String itemType) {
        return switch (itemType.toLowerCase()) {
            case "potion" -> new Normal();
            case "superpotion" -> new Super();
            case "hyperpotion" -> new Hyper();
            case "revive" -> new Revive();
            default -> throw new IllegalArgumentException("Ítem desconocido: " + itemType);
        };
    }

}
