package minivilles.metier.cartes.monuments;

import minivilles.metier.Metier;

/**
 * Created by richard on 6/19/17.
 */
public class CentreCommercial extends Monument {

	public CentreCommercial() {
		super("M2", "Centre commercial", "Marron", -1, 10);
	}


	public void lancerEffet(Metier metier) {
		// Les cafés, restaurants et supérettes, boulangeries rapportent une pièce de plus
	}
}
