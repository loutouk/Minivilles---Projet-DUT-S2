package minivilles.metier.cartes;

import minivilles.metier.Metier;

public class Foret extends Carte {

	public Foret() {
		super("5", "Forêt", "bleu", 5, 3);
	}

	@Override
	public void lancerEffet(Metier metier) {
		int gain = 1;
		this.getJoueur().addPiece(gain);
		metier.getBanque().retrait(gain);
	}
}
