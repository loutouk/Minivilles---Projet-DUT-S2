package minivilles.metier.cartes;

import minivilles.metier.Metier;
import minivilles.metier.cartes.monuments.Monument;

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

		// Effet du monument CentreCommercial : 1 piece de plus
		Carte carte = super.getJoueur().rechercherCarte("M2");
		if(carte!=null) {
			Monument monument = (Monument) carte;
			if(!monument.estEnConstruction()) gain++;
		}

		metier.getJoueurCourant().addPiece(gain);
		metier.getBanque().retrait(gain);
	}
}
