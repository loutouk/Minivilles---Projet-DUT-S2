package minivilles.metier.cartes;

import minivilles.metier.Metier;
import minivilles.metier.cartes.monuments.Monument;

public class Boulangerie extends Carte {

	public Boulangerie() {
		super(
				"2-3", "Boulangerie",
				"Vert", 2, 3, 1
		);
	}

	/**
	 * Déclenche l'effet de la boulangerie :
	 * le joueur reçoit une pièce que l'on retire de la banque.
	 *
	 * @param metier instance de la classe Metier qui permet d'avoir
	 *               accès à la banque, la pioche, les joueurs courants ...
	 */
	@Override
	public void lancerEffet(Metier metier) {
		int gain = 1;

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
