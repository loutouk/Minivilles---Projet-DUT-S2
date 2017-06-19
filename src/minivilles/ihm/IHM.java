package minivilles.ihm;

import minivilles.*;

import java.util.Scanner;

/**
 * Created by richard on 6/19/17.
 */


public class IHM {
	private Controleur ctrl;

	public IHM(Controleur ctrl) {
		this.ctrl = ctrl;
	}

	public int menu() {
		Scanner sc = new Scanner(System.in);
		boolean quitter = false;
		String choix;
		int nbJoueurs;
		while (!quitter) {
			System.out.println("1.\tJouer");
			System.out.println("2.\tQuitter");

			choix = sc.nextLine();

			switch (choix) {
				case "1":
					System.out.println("Choisissez un nombre de joueurs entre 2 et 4");
					try {
						nbJoueurs = sc.nextInt();
						// On scan dans le vide comme on a change de type
						sc.nextLine();
						if (nbJoueurs >= 2 && nbJoueurs <= 4) this.initialiserPlateau(nbJoueurs);
					} catch (Exception e) {
						System.out.println("Veuillez entrez un nombre valide");
					}
					break;

				case "2":
					quitter = true;
					break;

				default:
					System.out.println("Choix invalide");
					break;
			}

		}
		return 0;
	}


	public void initialiserPlateau(int nbJoueurs) {
		this.ctrl.initialiserPlateau(nbJoueurs);
	}

	public void afficherPlateau() {
		System.out.println(this.ctrl.afficherPlateau());
	}

}
