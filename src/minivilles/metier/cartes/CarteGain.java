package minivilles.metier.cartes;

import minivilles.metier.Metier;

/**
 * @see minivilles.metier.cartes.Carte
 * @author Richard Blondel
 * @author Valentin Dulong
 * @author Maxime Malgorn
 * @author Louis Boursier
 * @version 1.0
 */

public class CarteGain extends Carte {

	private int gain;

	public CarteGain(String identifiant, String nom, int declencheur, int cout, int gain) {
		super(identifiant, nom, "bleu", declencheur, cout);

		this.gain = gain;
	}


	@Override
	public void lancerEffet(Metier metier) {
		if (metier.getBanque().retrait(this.gain))
			this.getJoueur().addPiece(this.gain);
	}

}
