package minivilles.metier.cartes;

import minivilles.metier.Metier;

public class ChaineDeTelevision extends Carte {

	public ChaineDeTelevision() {
		super(
				"6:2", "Chaîne de télévision",
				"Spécial", 6, 7
		);
	}

	@Override
	public void lancerEffet(Metier metier) {
		
	}
}
