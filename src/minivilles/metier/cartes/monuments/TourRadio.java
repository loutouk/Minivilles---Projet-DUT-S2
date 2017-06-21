package minivilles.metier.cartes.monuments;

import minivilles.metier.Metier;

/**
 * Classe <i>TourRadio</i>, qui hérite de {@link minivilles.metier.cartes.monuments.Monument}.
 * <p>
 * Cette classe définit la carte <i>TourRadio</i> ainsi que son effet.
 *
 * @see minivilles.metier.cartes.Carte
 * @see minivilles.metier.cartes.monuments.Monument
 */
public class TourRadio extends Monument {

	public TourRadio() {
		super("M4", "Tour radio", -1, 22);
	}


	public void lancerEffet(Metier metier) {
		// Une fois par tour vous pouvez choisir de relancer vos dés
	}
}
