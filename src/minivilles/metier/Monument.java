package minivilles.metier;

import minivilles.metier.cartes.Carte;

/**
 * Created by richard on 6/19/17.
 */
public class Monument extends Carte {
	boolean estVisible = false;

	public Monument(String identifiant, String nom, String couleur, int declencheur, int cout) {
		super(identifiant, nom, couleur, declencheur, cout);
	}

	public boolean estVisible() {
		return this.estVisible;
	}
}
