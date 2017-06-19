package minivilles.metier.cartes.monuments;

import minivilles.metier.Metier;

/**
 * Created by richard on 6/19/17.
 */
public class Gare extends Monument {
	public Gare() {
		super("M1", "Gare", "Marron", -1, 4);
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
