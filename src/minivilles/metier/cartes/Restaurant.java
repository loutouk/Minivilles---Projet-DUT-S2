package minivilles.metier.cartes;

public class Restaurant extends Carte {

	public Restaurant() {
		super(
				"9-10", "Restaurant",
				"Rouge", 9, 10, 3
		);
	}

	@Override
	public boolean lancerEffet() {
		return false;
	}
}
