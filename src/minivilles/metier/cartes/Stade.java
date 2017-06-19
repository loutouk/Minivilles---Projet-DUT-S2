package minivilles.metier.cartes;

import minivilles.metier.Metier;

public class Stade extends Carte {

	public Stade (String identifiant, String nom, String couleur, int declencheur, int cout) {
		super("6:1", "Stade", "Violet", 6,6);
	}

	@Override
	public void lancerEffet(Metier metier) {

	}

}
