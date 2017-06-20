package minivilles.metier.cartes;

import minivilles.metier.Metier;

public class Ferme extends Carte {

	public Ferme () {
		super("2", "Ferme", "Bleu", 2, 1);
	}

	@Override
	public void lancerEffet(Metier metier) {
		int gain = 1;
		this.getJoueur().addPiece(gain);
		metier.getBanque().retrait(gain);
	}


}
