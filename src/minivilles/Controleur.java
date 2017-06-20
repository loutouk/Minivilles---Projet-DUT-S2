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
		System.out.println(this.ihm.afficherPlateau());
	}


	/**
	 * Accesseur pour récupérer le métier depuis la partie Test du programme
	 *
	 * @return Le metier
	 */
	public Metier getMetier() {
		return this.metier;
	}

	public IHM getIhm() {
		return ihm;
	}

	public void lancer() {
		boolean quitter = false;
		int choix;
		int nbJoueurs;

		while (!quitter) {
			this.ihm.afficherMenu();

			choix = this.ihm.choixMenu();

			switch (choix) {
				case 1:
					System.out.println("Choisissez un nombre de joueurs entre 2 et 4");
					nbJoueurs = this.ihm.choixNbJoueurs();

					if (nbJoueurs >= 2 && nbJoueurs <= 4)
						this.initialiserPlateau(nbJoueurs);
					else
						System.out.println("Veuillez entrez un nombre valide");

					break;

				case 2:
					quitter = true;
					break;

				default:
					System.out.println("Choix invalide");
					break;
			}

		}
	}

	public void initialiserPlateau(int nbJoueurs) {
		this.metier.initialiserPlateau(nbJoueurs);
	}

	public static void main(String[] a) {
		new Controleur().lancer();
	}
}