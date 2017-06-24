package minivilles.metier.cartes;

import minivilles.metier.Metier;
import minivilles.metier.cartes.monuments.Monument;

/**
 * @see minivilles.metier.cartes.Carte
 * @author Richard Blondel
 * @author Valentin Dulong
 * @author Maxime Malgorn
 * @author Louis Boursier
 * @version 1.0
 */

public class Cafe extends Carte {

	public Cafe() {
		super("3", "Café", "Bleu", 3, 2);
	}

	public void lancerEffet(Metier metier) {
		int don = 1;

		// Effet du monument CentreCommercial : 1 piece de plus
		Carte carte = super.getJoueur().rechercherCarte("M2");

		if (carte != null) {
			Monument monument = (Monument) carte;
			if (!monument.estEnConstruction()) don++;
		}

		if (metier.getJoueurCourant().getPieces() >= don) {
			metier.getJoueurCourant().retirerPiece(don);
			this.getJoueur().addPiece(don);
		}

	}
}
