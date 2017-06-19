package minivilles.metier.cartes;

import minivilles.metier.Metier;

public class Mine extends Carte {

	public Mine (String identifiant, String nom, String couleur, int declencheur, int cout) {
		super("9", "Mine", "Bleu",9,6);
	}

	@Override
	public void lancerEffet(Metier metier) {

	}


}
