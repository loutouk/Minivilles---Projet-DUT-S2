package minivilles.metier.cartes;

import minivilles.metier.Metier;

public class Verger extends Carte {

	public Verger () {
		super("10", "Verger", "Violet", 10, 3);
	}

	@Override
	public void lancerEffet(Metier metier) {
		int gain = 3;
		this.getJoueur().addPiece(gain);
		metier.banque.retrait(gain);
	}
}
