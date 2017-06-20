package minivilles.metier.cartes.monuments;

import minivilles.metier.Metier;

/**
 * Created by richard on 6/19/17.
 */
public class TourRadio extends Monument {
	public TourRadio() {
		super("M4", "Tour radio", "Marron", -1, 22);
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
