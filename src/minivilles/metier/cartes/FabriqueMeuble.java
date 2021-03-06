package minivilles.metier.cartes;

import minivilles.metier.Metier;

import java.util.ArrayList;

/**
 * @see minivilles.metier.cartes.Carte
 * @author Richard Blondel
 * @author Valentin Dulong
 * @author Maxime Malgorn
 * @author Louis Boursier
 * @version 1.0
 */

public class FabriqueMeuble extends Carte {

	public FabriqueMeuble() {
		super("8", "Fabrique de meubles", "bleu", 8, 3);
	}

	@Override
	public void lancerEffet(Metier metier) {
		ArrayList<Carte> arCartes = metier.getJoueurCourant().getMain();
		int gain = 3;
		int cpt = 0;
		for (Carte c : arCartes) {
			if (c.getNom().equals("Forêt") || c.getNom().equals("Mine")) cpt++;
		}
		metier.getJoueurCourant().addPiece(cpt * gain);
		metier.getBanque().retrait(cpt * gain);
	}
}
