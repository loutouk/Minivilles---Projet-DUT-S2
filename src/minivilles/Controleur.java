package minivilles;

/**
 * Created by Louis on 19/06/2017.
 */

import minivilles.metier.*;
import minivilles.ihm.*;

public class Controleur {
	private IHM ihm;
	private Metier metier;

	public Controleur() {
		this.metier = new Metier();
		this.ihm = new IHM(this);
	}


	/**
	 * Accesseur pour récupérer le métier depuis la partie Test du programme
	 *
	 * @return Le metier
	 */
	public Metier getMetier() {
		return this.metier;
	}

	public void lancer() {
		this.ihm.menu();
	}

	public String afficherPlateau() {
		return this.metier.toString();
	}

	public void initialiserPlateau(int nbJoueurs) {
		this.metier.initialiserPlateau(nbJoueurs);
	}

	public static void main(String[] a) {
		new Controleur().lancer();
	}
}