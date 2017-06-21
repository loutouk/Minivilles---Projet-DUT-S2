package minivilles.metier.cartes.monuments;

import minivilles.metier.Metier;

/**
 * Classe <i>ParcDattractions</i>, qui hérite de {@link minivilles.metier.cartes.monuments.Monument}.
 *
 * Cette classe définit la carte <i>ParcDattractions</i> ainsi que son effet.
 *
 * @see minivilles.metier.cartes.Carte
 * @see minivilles.metier.cartes.monuments.Monument
 */
public class ParcDattractions extends Monument {

	public ParcDattractions() {
		super("M3", "Parc d'attractions", -1, 16);
	}


	public void lancerEffet(Metier metier) {
		// Vous rejouer si votre jet de dés est un double
	}
}
