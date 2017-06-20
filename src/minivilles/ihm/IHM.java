package minivilles.ihm;

import minivilles.*;
import minivilles.metier.cartes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
		ArrayList<Carte> ligne1 = new ArrayList<>();
		ligne1.add(new ChampsDeBle());
		ligne1.add(new Ferme());
		ligne1.add(new Boulangerie());
		ligne1.add(new Cafe());
		ligne1.add(new Superette());

		ArrayList<Carte> ligne2 = new ArrayList<>();
		ligne2.add(new Foret());
		ligne2.add(new Stade());
		ligne2.add(new CentreAffaires());
		ligne2.add(new ChaineDeTelevision());
		ligne2.add(new Fromagerie());

		ArrayList<Carte> ligne3 = new ArrayList<>();
		ligne3.add(new FabriqueMeuble());
		ligne3.add(new Mine());
		ligne3.add(new Restaurant());
		ligne3.add(new Verger());
		ligne3.add(new MarcheDeFruitsEtLegumes());

		affichage += afficherLigneCarte(ligne1);
		affichage += afficherLigneCarte(ligne2);
		affichage +=  afficherLigneCarte(ligne3);


		// Affichage de la banque



		// Affichage des joueurs 2 par 2 à l'horizontal

		return affichage;
	}


	/* Affiche les cartes sur l'horizontal, 2 par 2 pour rentrer dans la console */
	public String afficherLigneCarte(ArrayList<Carte> listeCartes) {
		int nbCarteParLigne = 3;

		String affichage = "";
		String bord = "-----------------------";
		int nbT = bord.length() - 2;

		for (int i = 0 ; i <= listeCartes.size() / nbCarteParLigne ; i++){

			ArrayList<Carte>        deuxCartes = new ArrayList<>();
			ArrayList<List<String>> effets     = new ArrayList<>();


			for (int j = 0 ; j < nbCarteParLigne ; j++)
				if (listeCartes.size() > i * nbCarteParLigne + j)
					deuxCartes.add(listeCartes.get(i * nbCarteParLigne + j));

			if (deuxCartes.size() == 0) continue;


			for(Carte c : deuxCartes) {
				StringBuilder sb = new StringBuilder(c.getTexteEffet());

				int j = 0;
				while (j + nbT < sb.length() && (j = sb.lastIndexOf(" ", j + nbT)) != -1)
					sb.replace(j, j + 1, "\n");

				effets.add(Arrays.asList(sb.toString().split("\n")));
			}

			for(Carte ignored : deuxCartes) affichage += bord + " ";
			affichage += "\n";

			for(Carte c : deuxCartes) {
				affichage += "|" + IHM.centrerText(
						c.getDeclencheur() + (c.getDeclencheur2() == -1 ? "" : "-" + c.getDeclencheur2()),
						nbT
				) + "| ";
			}
			affichage+="\n";

			for(Carte c : deuxCartes) {
				affichage += "|" + IHM.centrerText(
						c.getNom(),
						nbT
				) + "| ";
			}
			affichage+="\n";


			for(Carte ignored : deuxCartes) affichage += bord + " ";
			affichage+="\n";



			for (int l = 0; l < 7; l++) {
				int k = 0;
				for(Carte ignored : deuxCartes) {
					if (effets.get(k).size() <= l)
						affichage += "|" + String.format("%" + nbT + "s", " ") + "| ";
					else
						affichage += "|" + String.format("%-" + nbT + "s", effets.get(k).get(l)) + "| ";
					k++;
				}

				affichage += "\n";
			}


			for(Carte ignored : deuxCartes) affichage += "|" + String.format("%" + nbT + "s", " ") + "| ";
			affichage += "\n";

			for(Carte c : deuxCartes) affichage += "|" + String.format("%-" + nbT + "s", " " + c.getCout()) + "|" + " ";
			affichage += "\n";

			for(Carte ignored : deuxCartes) affichage += bord + " ";
			affichage += "\n\n";
		}

		return affichage;
	}

	public void initialiserPlateau(int nbJoueurs) {
		this.ctrl.initialiserPlateau(nbJoueurs);
	}



	private static String centrerText(String text, int len){
		String out = String.format("%"+len+"s%s%"+len+"s", "",text,"");
		float mid = (out.length() / 2);
		float start = mid - (len / 2);
		float end = start + len;

		return out.substring((int)start, (int)end);
	}


}
