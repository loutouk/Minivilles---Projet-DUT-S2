package minivilles.metier.cartes;

import minivilles.metier.Metier;

public class Ferme extends Carte {

	public Ferme (String identifiant, String nom, String couleur, int declencheur, int cout) {
		super("2", "Ferme", "Bleu", 2, 1);
	}

	@Override
	public void lancerEffet(Metier metier) {

	}


}
