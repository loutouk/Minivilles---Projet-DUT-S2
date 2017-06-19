package minivilles;

/**
 * Created by Louis on 19/06/2017.
 */

import minivilles.ihm.IHM;
import minivilles.metier.Metier;

public class Controleur {
	private IHM ihm;
	private Metier metier;

	public Controleur() {
		this.metier = new Metier();
		this.ihm = new IHM(this);


		/*//DEBUG////////////////////////////////////
		ArrayList<Carte> cartes = new ArrayList<>();
		cartes.add(new Carte("Fromagerie", "Bleu", 4, 1, "1"));
		cartes.add(new Carte("Fromagerie", "Bleu", 5,6, 1, "2"));
		System.out.println(this.metier.afficherLigneCarte(cartes));
		//////////////////////////////////////////*/
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

	public void initialiserPlateau(int nbJoueurs) {
		this.metier.initialiserPlateau(nbJoueurs);
	}

	public static void main(String[] a) {
		new Controleur().lancer();
	}
}