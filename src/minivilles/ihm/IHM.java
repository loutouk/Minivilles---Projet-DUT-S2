package minivilles.ihm;

import minivilles.*;
import minivilles.metier.Joueur;
import minivilles.metier.cartes.*;

import java.util.*;

/**
 * Created by richard on 6/19/17.
 */
public class IHM {
	private Controleur ctrl;

	public IHM(Controleur ctrl) {
		this.ctrl = ctrl;
	}


	public void afficherMenu() {
		System.out.println("1.\tJouer");
		System.out.println("2.\tQuitter");
	}

	/**
	 * Retourne le choix de l'utilisateur pour le menu principal.
	 * Si la saisie est incorrecte (<i>NumberFormatException</i> est levée),
	 * la fonction se rappelle elle-même.
	 *
	 * @return le choix de l'utilisateur.
	 */
	public int choixMenu() {
		System.out.print("\nVotre choix : ");

		Scanner sc = new Scanner(System.in);

		int menu = -1;

		try {
			menu = sc.nextInt();
		} catch (NumberFormatException nfe) {
			System.out.println("Veuillez entrer un nombre valide.");
			this.choixMenu();
		}

		// On scan dans le vide comme on a change de type
		sc.nextLine();
		return menu;
	}

	/**
	 * Retourne le choix de l'utilisateur concernant le nombre de joueurs.
	 * Si la saisie est incorrecte (<i>NumberFormatException</i> est levée),
	 * la fonction se rappelle elle-même.
	 *
	 * @return le choix de l'utilisateur
	 */
	public int choixNbJoueurs() {
		System.out.print("\nNombre de joueurs : ");

		Scanner sc = new Scanner(System.in);

		int nbJ = -1;

		try {
			nbJ = sc.nextInt();
		} catch (NumberFormatException nfe) {
			System.out.println("Veuillez entrer un nombre valide.");
			this.choixMenu();
		}

		// On scan dans le vide comme on a change de type
		sc.nextLine();
		return nbJ;
	}


	/**
	 * Créé une représentation textuelle du plateau.
	 * On affiche la réserve la banque et les villes des joueurs.
	 *
	 * @return une représentation textuelle du plateau.
	 */
	public String afficherPlateau() {
		String affichage = "";

		// Affichage de la réserve de carte, 5 par 3 à l'horizontal
		// Pour les 15 piles, on affiche la carte supérieure si il en reste au moins une dans la pile
		ArrayList<Carte> reserve = new ArrayList<>();
	    for(Carte c : ctrl.getMetier().getPioche()){
	        if( ! c.getIdentifiant().equals("M1") &&
                ! c.getIdentifiant().equals("M2") &&
                ! c.getIdentifiant().equals("M3") &&
                ! c.getIdentifiant().equals("M4")   ) reserve.add(c);
        }
		affichage += afficherLigneCarte(reserve);

		// Affichage de la banque
        affichage += "---------------------------\n";
        affichage += "| Solde en banque : " + String.format("%5d",ctrl.getMetier().getBanque().getSolde()) + " |\n";
        affichage += "---------------------------\n";

		// Affichage des joueurs 2 par 2 à l'horizontal
        for(Joueur j : ctrl.getMetier().getListeJoueur()){
            affichage += "\nJoueur " + j.getNum() + "\n";
            // Monument
            // Carte
            affichage += afficherLigneCarte(j.getMain());
            // Argent
            affichage += "Pièces : " + j.getPieces() + "\n";
        }

		return affichage;
	}


	/**
	 * Affiche les cartes ligne par ligne.
	 *
	 * @param listeCartes l'<i>ArrayList</i> de <i>Carte</i> à afficher.
	 * @return l'affichage des cartes ligne par ligne.
	 */
	public String afficherLigneCarte(ArrayList<Carte> listeCartes) {
		int nbCarteParLigne = 5;

		String affichage = "";
		String bord = "-----------------------";
		int nbT = bord.length() - 2;


		Map<String, List<Carte>> cartes = new HashMap<>();

		// Tout d'abord, on regroupe les mêmes cartes
		for (Carte c : listeCartes) {
			String iden = c.getIdentifiant();

			if (!cartes.containsKey(iden))
				cartes.put(iden, new ArrayList<>());

			cartes.get(iden).add(c);
		}


		for (int i = 0; i <= cartes.size() / nbCarteParLigne; i++) {

			ArrayList<Carte> deuxCartes = new ArrayList<>();
			ArrayList<List<String>> effets = new ArrayList<>();


			for (int j = 0; j < nbCarteParLigne; j++)
				if (cartes.size() > i * nbCarteParLigne + j)
					deuxCartes.add(
							((ArrayList<Carte>) cartes.values().toArray()[i * nbCarteParLigne + j]).get(0)
					);

			if (deuxCartes.size() == 0) continue;


			for (Carte c : deuxCartes) {
				StringBuilder sb = new StringBuilder(c.getTexteEffet());

				int j = 0;
				while (j + nbT < sb.length() && (j = sb.lastIndexOf(" ", j + nbT)) != -1)
					sb.replace(j, j + 1, "\n");

				effets.add(Arrays.asList(sb.toString().split("\n")));
			}

			for (Carte ignored : deuxCartes) affichage += bord + " ";
			affichage += "\n";

			for (Carte c : deuxCartes) {
				affichage += "|" + IHM.centrerText(
						c.getIdentifiant(),
						nbT
				) + "| ";
			}
			affichage += "\n";

			for (Carte c : deuxCartes) {
				affichage += "|" + IHM.centrerText(
						c.getNom(),
						nbT
				) + "| ";
			}
			affichage += "\n";


			for (Carte ignored : deuxCartes) affichage += bord + " ";
			affichage += "\n";


			for (int l = 0; l < 5; l++) {
				int k = 0;
				for (Carte ignored : deuxCartes) {
					if (effets.get(k).size() <= l)
						affichage += "|" + String.format("%" + nbT + "s", " ") + "| ";
					else
						affichage += "|" + String.format("%-" + nbT + "s", effets.get(k).get(l)) + "| ";
					k++;
				}

				affichage += "\n";
			}


			for (Carte ignored : deuxCartes) affichage += "|" + String.format("%" + nbT + "s", " ") + "| ";
			affichage += "\n";

			for (Carte c : deuxCartes) affichage += "|" + String.format("%-" + nbT + "s", " " + c.getCout()) + "| ";
			affichage += "\n";

			for (Carte ignored : deuxCartes) affichage += bord + " ";
			affichage += "\n";

			for (Carte c : deuxCartes)
				affichage += "  x" + String.format("%-" + nbT + "s", cartes.get(c.getIdentifiant()).size());
			affichage += "\n\n";
		}

		return affichage;
	}

	private static String centrerText(String text, int len) {
		String out = String.format("%" + len + "s%s%" + len + "s", "", text, "");
		float mid = (out.length() / 2);
		float start = mid - (len / 2);
		float end = start + len;

		return out.substring((int) start, (int) end);
	}


}
