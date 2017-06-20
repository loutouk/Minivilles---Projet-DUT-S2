package minivilles.metier.cartes;

import minivilles.metier.Metier;

public class Superette extends Carte {

	public Superette() {
		super(
				"4", "Supérette",
				"Vert", 4, 2
		);
	}

	@Override
	public void lancerEffet(Metier metier) {
		int gain = 3;
		metier.getJoueurCourant().addPiece(gain);
		metier.getBanque().retrait(gain);
	}
}
