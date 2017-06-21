package minivilles.metier.cartes;

import minivilles.metier.Metier;

public class Mine extends Carte {

	public Mine() {
		super("9", "Mine", "Bleu", 9, 6);
	}

	@Override
	public void lancerEffet(Metier metier) {
		int gain = 5;
		this.getJoueur().addPiece(gain);
		metier.getBanque().retrait(gain);
	}
}
