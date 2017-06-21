package minivilles.metier.cartes.monuments;

import minivilles.metier.Metier;

/**
 * Classe <i>CentreCommercial</i>, qui hérite de {@link minivilles.metier.cartes.monuments.Monument}.
 *
 * Cette classe définit la carte <i>CentreCommercial</i> ainsi que son effet.
 *
 * @see minivilles.metier.cartes.Carte
 * @see minivilles.metier.cartes.monuments.Monument
 */
public class CentreCommercial extends Monument {

	public CentreCommercial() {
		super("M2", "Centre commercial", -1, 10);
	}


	public void lancerEffet(Metier metier) {
		// Les cafés, restaurants et supérettes, boulangeries rapportent une pièce de plus
	}
}
