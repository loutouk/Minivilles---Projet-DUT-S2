package minivilles.metier.cartes;

import minivilles.metier.Metier;

public class Cafe extends Carte {

	public Cafe () {
		super("3", "Café", "Bleu", 3, 2);
	}
	
	public void lancerEffet(Metier metier) {
		int don = 1;

		if(metier.getJoueurCourant().getPieces() >= don) {
			metier.getJoueurCourant().retirerPiece(don);
			this.getJoueur().addPiece(don);
		}
	}
}
