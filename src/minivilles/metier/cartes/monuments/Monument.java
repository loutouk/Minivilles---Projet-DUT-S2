package minivilles.metier.cartes.monuments;

import minivilles.metier.cartes.Carte;

/**
 * Classe <i>Monument</i> qui hérite de {@link minivilles.metier.cartes.Carte}.
 *
 * Un monument possède toutes les caractéristiques d'une carte, il possède en
 * plus un booléen qui indique si il est en construction ou pas.
 *
 * @see minivilles.metier.cartes.Carte
 */
public abstract class Monument extends Carte {
	boolean enConstruction = true;

	public Monument(String identifiant, String nom, int declencheur, int cout) {
		super(identifiant, nom, "Marron", declencheur, cout);
	}

	public boolean estEnConstruction() {
		return this.enConstruction;
	}

	public void construire() {
		this.enConstruction = false;
	}
}
