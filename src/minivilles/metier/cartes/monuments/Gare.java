package minivilles.metier.cartes.monuments;

import minivilles.metier.Metier;

/**
 * Classe <i>Gare</i>, qui hérite de {@link minivilles.metier.cartes.Carte}.
 *
 * Cette classe définit la carte <i>Gare</i> ainsi que son effet.
 *
 * @see minivilles.metier.cartes.Carte
 */
public class Gare extends Monument {

	public Gare() {
		super("M1", "Gare", -1, 4);
	}


	public void lancerEffet(Metier metier) {
		// Vous pouvez lancer deux dés
	}
}
