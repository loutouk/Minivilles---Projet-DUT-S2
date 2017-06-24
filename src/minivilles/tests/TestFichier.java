package minivilles.tests;

import minivilles.Controleur;
import minivilles.ihm.console.CouleurConsole;
import minivilles.metier.Joueur;
import minivilles.metier.cartes.Carte;
import minivilles.metier.cartes.monuments.Monument;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TestFichier {

	private static final String JOUEUR_REGEX = "^#J[0-9]#$";

	private Controleur ctrl;


	public TestFichier() {
		this.initialiserControleur();
	}


	private void initialiserControleur() {
		boolean ok;
		Scanner sc = new Scanner(System.in);

		do {

			CouleurConsole.VERT.print();
			System.out.println("-----------------------------------");
			System.out.println("|     PROGRAMME D'EVALUATION      |");
			System.out.println("-----------------------------------");
			System.out.println("|                                 |");
			System.out.println("|  1. Mode console monoposte      |");
			System.out.println("|  2. Mode GUI     monoposte      |");
			System.out.println("|  3. Mode console en réseau      |");
			System.out.println("|  4. Mode GUI     en réseau      |");
			System.out.println("|                                 |");
			System.out.println("-----------------------------------");
			CouleurConsole.RESET.print();

			System.out.print  ("\n   choix : " + CouleurConsole.JAUNE);
			int choix = sc.nextInt();
			sc.nextLine();

			ok = choix >= 1 && choix <= 4;

			// Création du bon controleur
			if (ok) {
				this.nettoyerAffichage();
				this.ctrl = new Controleur(
						(choix == 2 || choix == 4) ? "gui" : "console",
						true,
						(choix == 3 || choix == 4)
				);
			} else
				System.out.print(CouleurConsole.ROUGE + "   Choix invalide !\n\n");
		}
		while (!ok);
	}

	private void analyserFichier(File f) throws Exception {
		int nbJoueur = this.getNbJoueurs(f);

		if (nbJoueur < 2 || nbJoueur > 4)
			throw new ArrayIndexOutOfBoundsException(
					"Le nombre de joueurs doit être compris entre 2 et 4 !"
			);

		// On initialise tout d'abord le plateau (nb de joueurs)
		// sans donner les cartes de départ.
		this.ctrl.getMetier().initialiserPlateau(nbJoueur, true);

		try {
			Scanner sc = new Scanner(new FileReader(f));
			boolean hashtag = false;
			int nbJ = 0;

			while (sc.hasNextLine()) {
				String ligne = sc.nextLine();
				if (ligne.isEmpty()) continue;

				// Nouveau joueur dans le fichier
				if (ligne.charAt(0) == '#') {
					hashtag = true;

					if (ligne.matches(JOUEUR_REGEX))
						nbJ++;

					continue;
				}

				// Gestion des pièces de la banque
				// (doit se situer en haut du fichier)
				if (!hashtag && ligne.startsWith("banque:")) {
					this.ctrl.getMetier().getBanque().setSolde(Integer.valueOf(ligne.split(":")[1].trim()));
					continue;
				}

				Joueur joueur = this.ctrl.getMetier().getJoueur(nbJ - 1);
				if (joueur == null) continue;

				// On ajoute les pièces au joueur
				if (ligne.startsWith("pieces:")) {
					joueur.setPiece(Integer.valueOf(ligne.split(":")[1].trim()));
					continue;
				}

				if (ligne.startsWith("cartes:")) continue;

				if (ligne.startsWith("monuments:")) {
					String[] mSplit = ligne.split(":")[1].trim().split(",");

					for (String monumentId : mSplit) {
						Monument mon = (Monument) joueur.rechercherCarte("M" + monumentId);
						if (mon == null) continue;

						mon.construire();
					}

					continue;
				}

				// On ajoute la carte correspondante au dernier joueur enregistré
				String[] parts = ligne.trim().replaceFirst("-", "").replaceAll(" +", "").split(":");
				int nbCarte = Integer.parseInt(parts[1]);

				for (int cpt = 0; cpt < nbCarte; cpt++) {
					Carte c = IdentifiantCarte.nouvelleCarte(parts[0]);
					if (c == null) break;

					c.setJoueur(joueur);
					joueur.getMain().add(c);
				}
			}

			sc.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		// On initialise le plateau en IHM
		Controleur.getIhm().initialiserPlateau(
				this.ctrl.getMetier().getPioche(),
				nbJoueur
		);

		this.ctrl.lancer();
	}

	private int getNbJoueurs(File f) {
		int nb = 0;

		try {
			Scanner sc = new Scanner(new FileReader(f));

			while (sc.hasNextLine())
				if (sc.nextLine().matches(JOUEUR_REGEX))
					nb++;

			sc.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return nb;
	}

	private void nettoyerAffichage() {
		// Clean de la console en fonction du système d'exploitation
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else {
				System.out.print("\033[H\033[2J");
			}
		} catch (final Exception ignored) {}
	}



	public static void main(String[] args) throws Exception {
		// Un chemin de fichier doit être passé en paramètre
		if (args.length == 0) {
			System.out.println("Utilisation: TestFichier <fichier à analyser>");
			return;
		}

		File f = new File(args[0]);

		// Le fichier doit exister pour pouvoir l'analyser
		if (!f.exists()) {
			System.out.println("Le fichier " + args[0] + " n'existe pas.");
			return;
		}

		new TestFichier().analyserFichier(f);
	}

}
