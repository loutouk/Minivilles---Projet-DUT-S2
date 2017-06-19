package minivilles.tests;

import minivilles.Controleur;
import minivilles.metier.Joueur;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TestFichier {

	private static final String JOUEUR_REGEX = "^#J[0-9]#$";

	private Controleur ctrl;


	public TestFichier() {
		this.ctrl = new Controleur();
	}


	private void analyserFichier(File f) {
		// On initialise tout d'abord le plateau (nb de joueurs)
		this.ctrl.initialiserPlateau(this.getNbJoueurs(f));

		try {
			Scanner sc = new Scanner(new FileReader(f));
			int nbJ = 0;

			while (sc.hasNextLine()) {
				String ligne = sc.nextLine();
				if (ligne.isEmpty()) continue;

				// Nouveau joueur dans le fichier
				if (ligne.charAt(0) == '#')
					if (ligne.matches(JOUEUR_REGEX))
						nbJ++;

				Joueur joueur = this.ctrl.getMetier().getJoueur(nbJ);
				if (joueur == null) continue;

				// On ajoute les pièces au joueur
				if (ligne.startsWith("pieces:")) {
					joueur.setPiece(Integer.valueOf(ligne.split(":")[1].trim()));
					continue;
				}


				if (ligne.startsWith("cartes:")) continue;

				if (ligne.startsWith("monuments:")) {
					String[] mSplit = ligne.split(":")[1].trim().split(",");

					for (String monument : mSplit) {
						// TODO
					}

					continue;
				}

				// On ajoute la carte correspondante au dernier joueur enregistré
				String[] parts = ligne.trim().replaceFirst("-", "").replaceAll(" +", "").split(":");

				// TODO
			}

			System.out.println(this.ctrl.getMetier());

			sc.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
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


	public static void main(String[] args) {
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
