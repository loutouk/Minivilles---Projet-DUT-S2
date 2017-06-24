package minivilles.metier.cartes;

import minivilles.metier.Metier;

import java.util.ArrayList;

/**
 * @see Carte.java
 * @author Richard Blondel
 * @author Valentin Dulong
 * @author Maxime Malgorn
 * @author Louis Boursier
 * @version 1.0
 */

public class Fromagerie extends Carte {

	public Fromagerie() {
		super(
				"7", "Fromagerie",
				"Vert", 7, 5
		);
	}

	@Override
	public void lancerEffet(Metier metier) {
		ArrayList<Carte> arCartes = metier.getJoueurCourant().getMain();
		int cpt = 0;
		int gain = 3;

		for (Carte c : arCartes)
			if (c.getNom().equals("Ferme"))
				cpt++;

		if (metier.getBanque().retrait(cpt * gain))
			metier.getJoueurCourant().addPiece(cpt * gain);
	}
}
