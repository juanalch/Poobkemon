package domain;

import java.util.ArrayList;
import java.util.List;

public class ItemFactory {

	public static Item createItem(String itemType) {
        return switch (itemType.toLowerCase()) {
            case "potion" -> new Normal();
            case "superpotion" -> new Super();
            case "hyperpotion" -> new Hyper();
            case "revive" -> new Revive();
            default -> throw new IllegalArgumentException("Ítem desconocido: " + itemType);
        };
    }

    public static List<Item> getBaseItems() {
        List<Item> baseItems = new ArrayList<>();
        baseItems.add(new Normal());      // Potion
        baseItems.add(new Super());       // SuperPotion
        baseItems.add(new Hyper());       // HyperPotion
        baseItems.add(new Revive());      // Revive
        return baseItems;
    }

    
}
