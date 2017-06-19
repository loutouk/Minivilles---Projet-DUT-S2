package minivilles.tests;

import minivilles.metier.Carte;
import minivilles.metier.Joueur;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TestFichier {

	private static List<Joueur> joueurs;


	public static void main(String[] args) {
		joueurs = new ArrayList<>();

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

		try {
			Scanner sc = new Scanner(new FileReader(f));

			while (sc.hasNextLine()) {
				String ligne = sc.nextLine();
				if (ligne.isEmpty()) continue;

				// Nouveau joueur dans le fichier
				if (ligne.charAt(0) == '#') {
					if (ligne.matches("^#J[0-9]#$"))
						joueurs.add(new Joueur());

					continue;
				}


				Joueur joueur = joueurs.get(joueurs.size() - 1);

				// On ajoute les pièces au joueur
				if (ligne.startsWith("pieces:")) {
					joueur.addPiece(Integer.valueOf(ligne.split(":")[1].trim()));
					continue;
				}


				if (ligne.startsWith("cartes:")) continue;

				if (ligne.startsWith("monuments:")) {
					String[] mSplit = ligne.split(":")[1].trim().split(",");

					for (String monument : mSplit)
						joueur.addCarte(new Carte("Carte de test", "test", "", 2, 1, "M" + monument));

					continue;
				}

				// On ajoute la carte correspondante au dernier joueur enregistré
				String[] parts  = ligne.trim().replaceFirst("-", "").replaceAll(" +", "").split(":");
				System.out.println(Arrays.toString(parts));
			}

			System.out.println(Arrays.deepToString(joueurs.toArray(new Joueur[0])));

			sc.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
