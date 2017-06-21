package minivilles.ihm;

import minivilles.Controleur;
import minivilles.metier.Joueur;
import minivilles.metier.cartes.Carte;
import minivilles.metier.cartes.monuments.Monument;

import java.util.*;

/**
 * Created by richard on 6/19/17.
 */
public class IHM {
	private Controleur ctrl;

	public IHM(Controleur ctrl) {
		this.ctrl = ctrl;
	}


	public void afficherMenuPrincipal() {
		this.clearConsole();

		this.afficherBoite(
				"menu principal",
				"jouer", "quitter"
		);
	}

	public void afficherMenuAchat(Joueur joueur) {
		this.afficherBoite(
				"menu de construction (" + joueur.getPieces() + " pièces)",
				"Acheter une carte de la réserve",
				"Construire un monument",
				"Passer mon tour"
		);
	}

	public void afficherMenuRejouer() {
		this.afficherBoite("Voulez-vous rejouer ?", "Oui", "Non");
	}


	/**
	 * Retourne le choix de l'utilisateur pour le menu principal.
	 * Si la saisie est incorrecte (<i>NumberFormatException</i> est levée),
	 * la fonction se rappelle elle-même.
	 *
	 * @return le choix de l'utilisateur.
	 */
	public int choixMenu() {
		System.out.print("\n   choix : ");

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
		System.out.println("\n-- Choisissez un nombre de joueurs");
		System.out.print("   > joueurs (entre 2 et 4) : ");

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

	public String choixIdentifiantCarte() {
		System.out.println("\n-- Choisissez la carte à acheter");
		System.out.println("   (tapez -1 pour annuler)");
		System.out.print("   > son identifiant : ");

		Scanner sc = new Scanner(System.in);

		return sc.nextLine();
	}

	public String choixCarteCentreAffaire() {
		System.out.println("\n-- Choisissez la carte à échanger qui ne soit pas violette");
		System.out.println("   (tapez -1 pour annuler)");
		System.out.print("   > son identifiant : ");

		Scanner sc = new Scanner(System.in);

		return sc.nextLine();
	}

	public String choixJoueurCentreAffaire() {
		System.out.println("\n-- Choisissez le joueur avec qui échanger une carte");
		System.out.println("   (tapez -1 pour annuler)");
		System.out.print("   > son identifiant : ");

		Scanner sc = new Scanner(System.in);

		return sc.nextLine();
	}

	public int getDe() {
		System.out.print("\nEntrez un nombre pour le dé : ");

		Scanner sc = new Scanner(System.in);
		int menu = sc.nextInt();

		// On scan dans le vide comme on a change de type
		sc.nextLine();
		return menu;
	}


	/**
	 * Créé une représentation textuelle du plateau.
	 * On affiche la réserve la banque et les villes des joueurs.
	 */
	public void afficherPlateau() {
		this.clearConsole();

		// Affichage de la réserve de carte, 5 par 3 à l'horizontal
		// Pour les 15 piles, on affiche la carte supérieure si il en reste au moins une dans la pile
		ArrayList<Carte> reserve = new ArrayList<>();
		for (Carte c : ctrl.getMetier().getPioche()) {
			if (!c.getIdentifiant().equals("M1") &&
					!c.getIdentifiant().equals("M2") &&
					!c.getIdentifiant().equals("M3") &&
					!c.getIdentifiant().equals("M4")) reserve.add(c);
		}
		this.afficherLigneCarte(reserve);

		// Affichage de la banque
		System.out.println("---------------------------");
		System.out.println("| Solde en banque : " + String.format("%5d", ctrl.getMetier().getBanque().getSolde()) + " |");
		System.out.println("---------------------------");

		this.retarderAffichage(1500);

		// Affichage des joueurs 2 par 2 à l'horizontal
		for (Joueur j : ctrl.getMetier().getListeJoueur()) {
			System.out.println("\n###############");
			System.out.println("# " + String.format("%-11s", "Joueur " + j.getNum()) + " #");
			System.out.println("# " + String.format("%-11s", j.getPieces() + " pièces") + " #");
			System.out.println("###############");

			// Monument
			// Carte
			this.afficherColonneCarte(j.getMain());

			this.retarderAffichage(1000);
		}

		System.out.println("\n\n");
	}

	public void afficherDebutTour(Joueur j) {
		this.afficherBoite("Début du tour du joueur " + j.getNum());
		this.retarderAffichage(2000);
	}

	/**
	 * Affiche les cartes ligne par ligne.
	 *
	 * @param listeCartes l'<i>ArrayList</i> de <i>Carte</i> à afficher.
	 */
	public void afficherLigneCarte(ArrayList<Carte> listeCartes) {
		int nbCarteParLigne = 5;

		String affichage = "";
		String bord = "-----------------------";
		int nbT = bord.length() - 2;

		Map<String, List<Carte>> cartes = IHM.grouperCartes(listeCartes);

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

			for (Carte c : deuxCartes) {
				affichage += "|" + String.format("%-5s", " " + c.getCout());
				String enConstruction = "";

				if (c instanceof Monument && ((Monument) c).estEnConstruction())
					enConstruction = "En construction";

				affichage += String.format("%16s", enConstruction + " ") + "| ";
			}

			affichage += "\n";

			for (Carte ignored : deuxCartes) affichage += bord + " ";
			affichage += "\n";

			for (Carte c : deuxCartes)
				affichage += "  x" + String.format("%-" + nbT + "s", cartes.get(c.getIdentifiant()).size());
			affichage += "\n\n";
		}

		System.out.println(affichage);
	}

	public void afficherColonneCarte(ArrayList<Carte> listeCartes) {
		Map<String, List<Carte>> cartes = IHM.grouperCartes(listeCartes);

		String bord = String.format("%49s", " ").replaceAll(" ", "-");

		System.out.println("\n" + bord);

		for (Map.Entry<String, List<Carte>> entree : cartes.entrySet()) {
			Carte c = entree.getValue().get(0);

			String enCons = (c instanceof Monument && ((Monument) c).estEnConstruction()) ? "[Inactif]" : "";
			String actif = String.format("%9s", enCons);

			String iden = String.format("%5s", c.getIdentifiant());
			String nom = String.format("%-25s", c.getNom());
			String nbCa = String.format("%3s", "x" + entree.getValue().size());

			System.out.println("| " + iden + ". " + nom + " " + actif + " " + nbCa + " |");
		}

		System.out.println(bord + "\n");
	}

	public void afficherValeurDes(int de) {
		this.afficherBoite("Valeurs des dés : " + de);
	}

	public void afficherBilanTour(int piecesAv, int piecesAp, int des, List<Carte> cartesLancees) {
		System.out.println("-------------------------------");
		System.out.println("|  BILAN DU TOUR              |");
		System.out.println("-------------------------------");
		System.out.println("| Valeur des dés : " + String.format("%-10d", des) + " |");
		System.out.println("| Pièces avant   : " + String.format("%-10d", piecesAv) + " |");
		System.out.println("|                             |");
		System.out.println("| Batîments activés :         |");

		// TODO
		if (cartesLancees.size() == 0)
			System.out.println("|   aucun                     |");
		else
			for (Carte c : cartesLancees)
				System.out.println("|   - " + String.format("%-23s", c.getNom()) + " |");

		System.out.println("|                             |");
		System.out.println("| Pièces après   : " + String.format("%-10d", piecesAp) + " |");
		System.out.println("-------------------------------");


		this.retarderAffichage(2000);
	}

	public void afficherGagnant(Joueur gagnant) {
		System.out.println();
		this.afficherBoite("Le joueur #" + gagnant.getNum() + " gagne !");
		System.out.println();
	}


	private void afficherBoite(String titre, String... sousItems) {
		int largeur = titre.length();

		for (String item : sousItems)
			if (item.length() > largeur)
				largeur = item.length();

		String bord = String.format("%" + (largeur + 8) + "s", " ").replaceAll(" ", "-");

		System.out.println("/" + bord + "\\");
		System.out.println("|  " + String.format("%-" + (largeur + 4) + "s", titre.toUpperCase()) + "  |");

		if (sousItems.length > 0) {
			System.out.println("|" + bord + "|");

			for (int cpt = 0; cpt < sousItems.length; cpt++)
				System.out.println("| " + String.format("%2d", cpt + 1) + ".  " + String.format("%-" + largeur + "s", IHM.ucfirst(sousItems[cpt])) + "  |");
		}

		System.out.println("\\" + bord + "/");
	}

	private void retarderAffichage(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clearConsole() {
		// Clean de la console en fonction du système d'exploitation
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else {
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		} catch (final Exception ignored) {
		}
	}


	private static Map<String, List<Carte>> grouperCartes(ArrayList<Carte> listeCartes) {
		Map<String, List<Carte>> cartes = new HashMap<>();

		// On parcoure toutes les cartes pour les regrouper
		for (Carte c : listeCartes) {
			String iden = c.getIdentifiant();

			if (!cartes.containsKey(iden))
				cartes.put(iden, new ArrayList<Carte>());

			cartes.get(iden).add(c);
		}

		return cartes;
	}

	private static String centrerText(String text, int len) {
		String out = String.format("%" + len + "s%s%" + len + "s", "", text, "");
		float mid = (out.length() / 2);
		float start = mid - (len / 2);
		float end = start + len;

		return out.substring((int) start, (int) end);
	}

	private static String ucfirst(String chaine) {
		return chaine.substring(0, 1).toUpperCase() + chaine.substring(1).toLowerCase();
	}

	public String choixJoueurChaineTV() {
		System.out.println("\n-- Choisissez le joueur à qui voler 5 pièces");
		System.out.println("   (tapez -1 pour annuler)");
		System.out.print("   > son identifiant : ");

		Scanner sc = new Scanner(System.in);

		return sc.nextLine();
	}

}
