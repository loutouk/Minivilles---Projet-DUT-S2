package minivilles.metier.cartes;

import minivilles.metier.Metier;

public class Verger extends Carte {

	public Verger (String identifiant, String nom, String couleur, int declencheur, int cout) {
		super("10", "Verger", "Violet", 10, 3);
	}

	@Override
	public void lancerEffet(Metier metier) {

	}
}
