package domain;

public abstract class Trainer {

	protected String name;

	protected String color;

	protected String type;

	private Pokemon[] pokemon;

	private Item item;

	public void _(String name, String color, String type) {

	}

	public void changePokemon(Pokemon pookemon) {

	}

	public void makeMovement(String nameMovement) {

	}

	public void useItem(Item item) {

	}

	public ArrayList getTeam() {
		return null;
	}

	public ArrayList getItems() {
		return null;
	}

}
