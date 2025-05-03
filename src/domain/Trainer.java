package domain;
import java.util.*; 

public abstract class Trainer {

	protected String name;
	protected String color;
	protected String type;
	protected Pokemon[] pokemon;
	protected Item[] item;

	public Trainer(String name, String color, String type) {

	}

	public void changePokemon(Pokemon pookemon) {

	}

	public void makeMovement(String nameMovement) {

	}

	public void useItem(Item item) {

	}

	public ArrayList<Pokemon> getTeam() {
		return null;
	}

	public ArrayList<Item> getItems() {
		return null;
	}

}
