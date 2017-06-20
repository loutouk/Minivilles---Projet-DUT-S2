package minivilles.metier.cartes;

import minivilles.metier.Metier;

public class Boulangerie extends Carte {

	public Boulangerie() {
		super(
				"2-3", "Boulangerie",
				"Vert", 2, 3, 1
		);
	}

	/**
	 * Déclenche l'effet de la boulangerie :
	 * le joueur reçoit une pièce que l'on retire de la banque.
	 *
	 * @param metier instance de la classe Metier qui permet d'avoir
	 *               accès à la banque, la pioche, les joueurs courants ...
	 */
	@Override
	public void lancerEffet(Metier metier) {
		int gain = 1;
		metier.getJoueurCourant().addPiece(gain);
		metier.getBanque().retrait(gain);
	}
}
