package minivilles.ihm.console;

import minivilles.Controleur;
import minivilles.ihm.IHM;
import minivilles.metier.Joueur;
import minivilles.metier.cartes.Carte;
import minivilles.metier.cartes.monuments.Monument;

import java.util.*;

public class IHMConsole extends IHM {

	private int nbItemsDernierMenu;


	public IHMConsole(Controleur ctrl) {
		super(ctrl);
	}


	public void afficherMenuPrincipal() {
		this.nettoyerAffichage();

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

	@Override
	public void initialiserCartes(ArrayList<Carte> pioche) {
		// On ne s'en sert pas en mode console.
	}


	/**
	 * Retourne le choix de l'utilisateur pour le menu principal.
	 * Si la saisie est incorrecte (<i>NumberFormatException</i> est levée),
	 * la fonction se rappelle elle-même.
	 *
	 * @return le choix de l'utilisateur.
	 */
	public int choixMenu() {
		return this.choixMenu(1, this.nbItemsDernierMenu);
	}
	public int choixMenu(int min, int max) {
		System.out.print("\n   choix : ");
		CouleurConsole.JAUNE.print();

		Scanner sc = new Scanner(System.in);

		int menu;

		try {
			menu = sc.nextInt();
			CouleurConsole.RESET.print();
		} catch (NumberFormatException | InputMismatchException ignored) {
			this.messageCouleur("Veuillez entrer un nombre valide.", CouleurConsole.ROUGE);

			sc.nextLine();
			return this.choixMenu(min, max);
		}

		if (menu < min || menu > max) {
			this.messageCouleur("Choix invalide", CouleurConsole.ROUGE);

			sc.nextLine();
			return this.choixMenu(min, max);
		}

		// On scan dans le vide comme on a changé de type
		sc.nextLine();
		System.out.println();
		return menu;
	}

	public String choixStringMenu() {
		System.out.print("\n   choix : ");
		CouleurConsole.JAUNE.print();

		Scanner sc = new Scanner(System.in);
		String in = sc.nextLine();

		System.out.println();
		CouleurConsole.RESET.print();

		return in;
	}

	/**
	 * Retourne le choix de l'utilisateur concernant le nombre de joueurs.
	 * Si la saisie est incorrecte (<i>NumberFormatException</i> est levée),
	 * la fonction se rappelle elle-même.
	 *
	 * @return le choix de l'utilisateur
	 */
	public int choixNbJoueurs() {
		this.nettoyerAffichage();

		this.afficherBoite("Combien de joueurs vont jouer ?\n(entre 2 et 4 j.)");
		return this.choixMenu(2, 4);
	}

	public int choixNbDes() {
		this.afficherBoite("Combien de dé(s) voulez-vous lancer ?", "1", "2");
		return this.choixMenu();
	}

	public int choixDebugDe() {
		this.afficherBoite("Mode d'évaluation activé.\nChoisissez la valeur du dé.");
		return this.choixMenu(1, 6);
	}

	public String choixAchatBatiment() {
		this.afficherBoite("Choisissez la carte à acheter\n(tapez -1 pour annuler)");
		return this.choixStringMenu();
	}

	public String choixCarteCentreAffaire(String joueur) {
		System.out.println("\n-- Choisissez la carte " + joueur + " à échanger qui ne soit pas violette");
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

	public String choixJoueurChaineTV() {
		System.out.println("\n-- Choisissez le joueur à qui voler 5 pièces");
		System.out.println("   (tapez -1 pour annuler)");
		System.out.print("   > son identifiant : ");

		Scanner sc = new Scanner(System.in);

		return sc.nextLine();
	}

	public int getDe() {
		System.out.print("\nEntrez un nombre pour le dé : ");

		Scanner sc = new Scanner(System.in);

		int menu = -1;

		try {
			menu = sc.nextInt();
		} catch (NumberFormatException | InputMismatchException ignored) {
			System.out.println("Veuillez entrer un nombre valide.");
		}

		// On scan dans le vide comme on a change de type
		sc.nextLine();
		return menu;
	}


	/**
	 * Créé une représentation textuelle du plateau.
	 * On affiche la réserve la banque et les villes des joueurs.
	 */
	public void afficherPlateau() {
		this.nettoyerAffichage();

		// Affichage de la réserve de carte, 5 par 3 à l'horizontal
		// Pour les 15 piles, on affiche la carte supérieure si il en reste au moins une dans la pile
		ArrayList<Carte> reserve = new ArrayList<>();
		for (Carte c : this.ctrl.getMetier().getPioche()) {
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
		this.nettoyerAffichage();

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
				affichage += "|" + this.centrerText(
						c.getIdentifiant(),
						nbT
				) + "| ";
			}
			affichage += "\n";

			for (Carte c : deuxCartes) {
				affichage += "|" + this.centrerText(
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

		String bord = String.format("%54s", " ").replaceAll(" ", "-");

		System.out.println("\n" + bord);

		for (Map.Entry<String, List<Carte>> entree : cartes.entrySet()) {
			Carte c = entree.getValue().get(0);

			String enCons = (c instanceof Monument && ((Monument) c).estEnConstruction()) ?
					CouleurConsole.ROUGE + "[Inactif]" + CouleurConsole.RESET : "";
			String actif = String.format("%9s", enCons);

			String iden = String.format("%5s", c.getIdentifiant());
			String nom = String.format("%-29.29s", c.getNom());
			String nbCa = CouleurConsole.VIOLET + String.format("%3s", "x" + entree.getValue().size()) + CouleurConsole.RESET;

			System.out.println("| " + iden + ". " + nom + " " + actif + " " + nbCa + " |");
		}

		System.out.println(bord + "\n");
	}

	public void afficherValeurDes(int de1, int de2) {
		this.afficherBoite("Valeurs des dés : " + (de1 + de2));
	}

	public void afficherBilanTour(Joueur joueur, int piecesAv, int nbDes, int des, List<Carte> cartesLancees) {
		System.out.println("-------------------------------");
		System.out.println("|  BILAN DU TOUR (J" + joueur.getNum() + ")         |");
		System.out.println("-------------------------------");

		String labelDe = (nbDes > 1) ? "des dés" : "du dé  ";

		System.out.println("| Valeur " + labelDe + " : " + String.format("%-10d", des) + " |");
		System.out.println("| Pièces avant   : " + String.format("%-10d", piecesAv) + " |");
		System.out.println("|                             |");
		System.out.println("| Batîments activés :         |");

		if (cartesLancees.size() == 0)
			System.out.println("|   aucun                     |");
		else
			for (Carte c : cartesLancees)
				System.out.println("|   - " + String.format("%-18s", c.getNom()) + " (J" + c.getJoueur().getNum() + ") |");

		System.out.println("|                             |");
		System.out.println("| Pièces après   : " + String.format("%-10d", joueur.getPieces()) + " |");
		System.out.println("-------------------------------");


		this.retarderAffichage(2000);
	}

	public void afficherGagnant(Joueur gagnant) {
		System.out.println();
		this.afficherBoite("Le joueur #" + gagnant.getNum() + " gagne !");
		System.out.println();
	}

	public void afficherModeEvaluation() {
		CouleurConsole.VERT.print();

		System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("x  Mode évaluation activé !                                    x");
		System.out.println("x  Ce dernier n'est pas représentatif d'une partie classique.  x");
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");

		CouleurConsole.RESET.print();
	}

	public void afficherErreur(String erreur) {
		System.out.println(CouleurConsole.ROUGE + "   " + erreur + CouleurConsole.RESET + "\n");
	}

	private void afficherBoite(String titre, String... sousItems) {
		int largeur = 0;

		// Gestion de la largeur du titre
		if (titre.contains("\n")) {
			String[] lignes = titre.split("\n");

			for (String ligne : lignes)
				if (largeur < ligne.length())
					largeur = ligne.length();
		} else {
			largeur = titre.length();
		}

		// Gestion de la largeur des items du menu (s'il y en a)
		for (String item : sousItems)
			if (item.length() > largeur)
				largeur = item.length();

		String bord = String.format("%" + (largeur + 8) + "s", " ").replaceAll(" ", "-");

		CouleurConsole.BLEU.print();
		System.out.println("/" + bord + "\\");

		// Gestion du titre multi-lignes
		String[] lignes = (titre.contains("\n")) ? titre.split("\n") : new String[]{ titre };

		for (String ligne : lignes)
			System.out.println("|  " + String.format("%-" + (largeur + 4) + "s", ligne.toUpperCase()) + "  |");


		if (sousItems.length > 0) {
			System.out.println("|" + bord + "|");

			for (int cpt = 0; cpt < sousItems.length; cpt++)
				System.out.println("| " + String.format("%2d", cpt + 1) + ".  " + CouleurConsole.CYAN + String.format("%-" + largeur + "s", this.ucfirst(sousItems[cpt])) + CouleurConsole.BLEU + "  |");

			this.nbItemsDernierMenu = sousItems.length;
		} else {
			this.nbItemsDernierMenu = Integer.MAX_VALUE;
		}


		System.out.println("\\" + bord + "/");
		CouleurConsole.RESET.print();
	}

	private void retarderAffichage(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void nettoyerAffichage() {
		// Clean de la console en fonction du système d'exploitation
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else {
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		} catch (final Exception ignored) {}
	}

	private void messageCouleur(String message, CouleurConsole couleur) {
		couleur.print();
		System.out.println(message);
		CouleurConsole.RESET.print();
	}

	private String centrerText(String text, int len) {
		String out = String.format("%" + len + "s%s%" + len + "s", "", text, "");
		float mid = (out.length() / 2);
		float start = mid - (len / 2);
		float end = start + len;

		return out.substring((int) start, (int) end);
	}

	private String ucfirst(String chaine) {
		return chaine.substring(0, 1).toUpperCase() + chaine.substring(1).toLowerCase();
	}

}
