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

public class MarcheDeFruitsEtLegumes extends Carte {

	public MarcheDeFruitsEtLegumes() {
		super(
				"11-12", "Marché de fruits et légumes",
				"Vert", 11, 12, 2
		);
	}

	@Override
	public void lancerEffet(Metier metier) {
		ArrayList<Carte> arCartes = metier.getJoueurCourant().getMain();
		int cpt = 0;
		int gain = 2;

		for (Carte c : arCartes)
			if (c.getNom().equals("Champs de blé") || c.getNom().equals("Verger"))
				cpt++;

		if (metier.getBanque().retrait(cpt * gain))
			metier.getJoueurCourant().addPiece(cpt * gain);
	}
}
