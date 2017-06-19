package minivilles.metier.cartes;

import minivilles.metier.Metier;

public class Restaurant extends Carte {

	public Restaurant() {
		super(
				"9-10", "Restaurant",
				"Rouge", 9, 10, 3
		);
	}

	@Override
	public void lancerEffet(Metier metier) {

	}
}
