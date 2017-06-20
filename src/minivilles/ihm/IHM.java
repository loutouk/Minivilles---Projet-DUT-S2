package minivilles.ihm;

import minivilles.*;
import minivilles.metier.cartes.*;

import java.util.ArrayList;
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

	/* Affiche le plateau */
	public String afficherPlateau() {
		String affichage = "";

		// Affichage de la réserve de carte, 5 par 3 à l'horizontal
		// Pour les 15 piles, on affiche la carte supérieure si il en reste au moins une dans la pile

		// debug, il faudra vérfier la présence de cartes dans la pile avant de l'afficher
		ArrayList ligne1 = new ArrayList<Carte>();
		ligne1.add(new ChampsDeBle());
		ligne1.add(new Ferme());
		ligne1.add(new Boulangerie());
		ligne1.add(new Cafe());
		ligne1.add(new Superette());

		ArrayList ligne2 = new ArrayList<Carte>();
		ligne2.add(new Foret());
		ligne2.add(new Stade());
		ligne2.add(new CentreAffaires());
		ligne2.add(new ChaineDeTelevision());
		ligne2.add(new Fromagerie());

		ArrayList ligne3 = new ArrayList<Carte>();
		ligne3.add(new FabriqueMeuble());
		ligne3.add(new Mine());
		ligne3.add(new Restaurant());
		ligne3.add(new Verger());
		ligne3.add(new MarcheDeFruitsEtLegumes());

		affichage += afficherLigneCarte(ligne1);
		affichage += afficherLigneCarte(ligne2);
		affichage += afficherLigneCarte(ligne3);


		// Affichage de la banque


		// Affichage des joueurs 2 par 2 à l'horizontal

		return affichage;


	}


	/* Affiche les cartes sur l'horizontal, 2 par 2 pour rentrer dans la console */
	public String afficherLigneCarte(ArrayList<Carte> listeCartes) {

		int nbCarteParLigne = 2;

		String affichage = "";
		String bord = "--------------------------------";

		for (int i = 0; i <= listeCartes.size() / nbCarteParLigne; i++) {

			ArrayList<Carte> deuxCartes = new ArrayList<>();
			for (int j = 0; j < nbCarteParLigne; j++) {
				if (listeCartes.size() > i * nbCarteParLigne + j)
					deuxCartes.add(listeCartes.get(i * nbCarteParLigne + j));
			}


			for (Carte ignored : deuxCartes) affichage += bord + " ";
			affichage += "\n";


			for (Carte c : deuxCartes)
				affichage += "|" + String.format("%-30s", "Declencheur : "
						+ c.getDeclencheur() + " " + (c.getDeclencheur2() == -1 ? " " : c.getDeclencheur2())) + "|" + " ";
			affichage += "\n";

			for (Carte ignored : deuxCartes) affichage += String.format("%-30s", bord) + " ";
			affichage += "\n";

			for (Carte c : deuxCartes) affichage += "|" + String.format("%-30s", "Nom : " + c.getNom()) + "|" + " ";
			affichage += "\n";

			for (Carte ignored : deuxCartes) affichage += String.format("%-30s", bord) + " ";
			affichage += "\n";

			for (Carte c : deuxCartes)
				affichage += "|" + String.format("%-30s", "Effet : " + c.getTexteEffet().substring(0, 21)) + "|" + " ";
			affichage += "\n";

			for (Carte ignored : deuxCartes) affichage += String.format("%-30s", bord) + " ";
			affichage += "\n";

			for (Carte c : deuxCartes)
				affichage += "|" + String.format("%-30s", "Cout de construction : " + c.getCout()) + "|" + " ";
			affichage += "\n";

			for (Carte ignored : deuxCartes) affichage += String.format("%-30s", bord) + " ";
			affichage += "\n";
		}

		return affichage;
	}

	public void initialiserPlateau(int nbJoueurs) {
		this.ctrl.initialiserPlateau(nbJoueurs);
	}


}
