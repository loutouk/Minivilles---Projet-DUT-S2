package minivilles.metier.cartes;

import minivilles.metier.Joueur;
import minivilles.metier.Metier;
import minivilles.metier.cartes.monuments.Monument;

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

		// Effet du monument CentreCommercial : 1 piece de plus
		Carte carte = super.getJoueur().rechercherCarte("M2");
		if (carte != null) {
			Monument monument = (Monument) carte;
			if (!monument.estEnConstruction()) don++;
		}

		if (metier.getJoueurCourant().getPieces() >= don) {
			metier.getJoueurCourant().retirerPiece(don);
			this.getJoueur().addPiece(don);
		} else {
			int argentRetire = metier.getJoueurCourant().getPieces();
			metier.getJoueurCourant().retirerPiece(argentRetire);
			this.getJoueur().addPiece(argentRetire);
		}

	}
}
