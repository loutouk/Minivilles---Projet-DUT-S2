package minivilles.metier.cartes;

import minivilles.metier.Metier;

public class ChampsDeBle extends Carte {

	public ChampsDeBle() {
		super("1", "Champs de blé", "bleu", 1, 1);
	}

	@Override
	public void lancerEffet(Metier metier) {
		int gain = 1;

		if (metier.getBanque().retrait(gain))
			this.getJoueur().addPiece(gain);
	}
}
