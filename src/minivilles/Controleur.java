package minivilles;

/**
 * Created by Louis on 19/06/2017.
 */

import minivilles.ihm.IHM;
import minivilles.metier.Metier;
import minivilles.metier.cartes.Carte;

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

	public IHM getIhm() {
		return ihm;
	}

	public void lancer() {
		boolean quitter = false;
		int choix;

		while (!quitter) {
			this.ihm.afficherMenuPrincipal();

			choix = this.ihm.choixMenu();

			switch (choix) {
				case 1:
					this.initialiserPartie();
					this.lancerPartie();
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

	private void initialiserPartie() {
		int nbJoueurs = this.ihm.choixNbJoueurs();

		if (nbJoueurs >= 2 && nbJoueurs <= 4)
			this.initialiserPlateau(nbJoueurs);
		else {
			System.out.println("   --> Veuillez entrez un nombre valide.\n");
			this.initialiserPartie();
		}
	}

	private void lancerPartie() {
		while (true) {
			this.ihm.afficherPlateau();

			System.out.println("Joueur #" + this.metier.getJoueurCourant().getNum());

			int de = this.lancerDe();

			System.out.println("valeur du dé = " + de);

			// On lance les effets de toutes les cartes
			this.metier.lancerEffets(de, -2);

			this.ihm.afficherMenuAchat();
			int choix = this.ihm.choixMenu();

			switch (choix) {
				case 1:
					this.ihm.clearConsole();
					this.ihm.afficherLigneCarte(this.metier.getPioche());

					Carte carte;
					String choixIden;
					boolean achatTermine = false;

					do {
						choixIden = this.ihm.choixIdentifiantCartePioche();
						carte = this.metier.rechercherCartePioche(choixIden);

						if (choixIden.equals("-1"))
							achatTermine = true;
						else {
							if (carte != null && this.metier.acheter(choixIden, this.metier.getJoueurCourant()))
								achatTermine = true;
						}
					}
					while (!achatTermine);


					break;
			}

			this.metier.changerJoueurCourant();
		}
	}

	private int lancerDe() {
		return (int) (Math.random() * 5) + 1;
	}

	public void initialiserPlateau(int nbJoueurs) {
		this.metier.initialiserPlateau(nbJoueurs);
	}


	public static void main(String[] a) {
		new Controleur().lancer();
	}
}