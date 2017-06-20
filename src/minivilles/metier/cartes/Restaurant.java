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

		if (metier.getJoueurCourant().getPieces() >= don) {
			metier.getJoueurCourant().retirerPiece(don);
			this.getJoueur().addPiece(don);
		} else {
			int argentRetire = metier.getJoueurCourant().getPieces();
			metier.getJoueurCourant().retirerPiece(argentRetire);
			this.getJoueur().addPiece(argentRetire);
		}

		// Effet du monument CentreCommercial : 1 piece de plus
		Carte carte = super.getJoueur().rechercherCarte("M2");
		if (carte != null) {
			Monument monument = (Monument) carte;
			if (!monument.estEnConstruction()) don++;
		}

		for (Joueur j : metier.getListeJoueur()) {
			for (Carte c : j.getMain()) {
				if (c.getNom().equals("Restaurant")) {
					metier.getJoueurCourant().retirerPiece(don);
					j.addPiece(don);
				}
			}
		}
	}
}
