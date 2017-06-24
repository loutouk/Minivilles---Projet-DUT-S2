package minivilles.metier.cartes.monuments;

import minivilles.metier.Metier;
import minivilles.metier.cartes.Carte;

/**
 * Monument.java
 * @author Richard Blondel
 * @author Valentin Dulong
 * @author Maxime Malgorn
 * @author Louis Boursier
 * @version 1.0
 * Classe <i>Monument</i> qui hérite de {@link minivilles.metier.cartes.Carte}.
 * <p>
 * Un monument possède toutes les caractéristiques d'une carte, il possède en
 * plus un booléen qui indique s'il est en construction ou pas.
 *
 * @see minivilles.metier.cartes.Carte
 */
public abstract class Monument extends Carte {

	private boolean enConstruction = true;


	public Monument(String identifiant, String nom, int declencheur, int cout) {
		super(identifiant, nom, "Marron", declencheur, cout);
	}


	public boolean estEnConstruction() {
		return this.enConstruction;
	}

	public void construire() {
		this.enConstruction = false;
	}


	// L'effet des monuments est lancé dans la boucle de jeu
	// (ou s'applique à d'autres cartes), donc pas besoin de
	// définir un comportement d'effet ici.
	public void lancerEffet(Metier metier) {
	}

}
