package minivilles.metier.cartes;

import minivilles.metier.Joueur;
import minivilles.metier.Metier;

public class Stade extends Carte {

	public Stade() {
		super("6:1", "Stade", "Violet", 6, 6);
	}

	@Override
	public void lancerEffet(Metier metier) {
		int don = 2;

		for (Joueur j : metier.getListeJoueur()) {
			if (j.getPieces() >= don) {
				j.retirerPiece(don);
				metier.getJoueurCourant().addPiece(don);
			} else {
				j.retirerPiece(j.getPieces());
				metier.getJoueurCourant().addPiece(j.getPieces());
			}
		}
	}
}
