package minivilles.metier.cartes.monuments;

import minivilles.metier.Metier;

/**
 * Created by richard on 6/19/17.
 */
public class ParcDattractions extends Monument {
	public ParcDattractions() {
		super("M3", "Parc d'attractions", "Marron", -1, 16);
	}

	public boolean estVisible() {
		return this.estVisible;
	}

	public void setVisible(boolean visibilite) {
		this.estVisible = visibilite;
	}

	public void lancerEffet(Metier metier) {

	}
}
