package minivilles.metier.cartes.monuments;

import minivilles.metier.cartes.Carte;

/**
 * Created by richard on 6/19/17.
 */
public abstract class Monument extends Carte {
	boolean enConstruction = true;

	public Monument(String identifiant, String nom, String couleur, int declencheur, int cout) {
		super(identifiant, nom, couleur, declencheur, cout);
	}

	public boolean estEnConstruction() {
		return this.enConstruction;
	}

	public void setEnConstruction() {
		this.enConstruction = false;
	}
}
