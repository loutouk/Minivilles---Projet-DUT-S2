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
		int don = 2;

		if(metier.getJoueurCourant().getPieces() >= don) {
			metier.getJoueurCourant().retirerPiece(don);
			this.getJoueur().addPiece(don);
		}
		else {
			int argentRetire = metier.getJoueurCourant().getPieces();
			metier.getJoueurCourant().retirerPiece(argentRetire);
			this.getJoueur().addPiece(argentRetire);
		}
	}
}
