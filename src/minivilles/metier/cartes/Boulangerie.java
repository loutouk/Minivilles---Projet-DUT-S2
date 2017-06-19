package minivilles.metier.cartes;

import minivilles.metier.Metier;

public class Boulangerie extends Carte {

	public Boulangerie() {
		super(
				"2-3", "Boulangerie",
				"Vert", 2, 3, 1
		);
	}

	@Override
	public void lancerEffet(Metier metier) {

	}
}
