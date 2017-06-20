package minivilles.ihm;

import minivilles.*;
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

	public int choixMenu() {
		System.out.print("\nVotre choix : ");

		Scanner sc = new Scanner(System.in);
		int menu = sc.nextInt();

		// On scan dans le vide comme on a change de type
		sc.nextLine();
		return menu;
	}

	public int choixNbJoueurs() {
		System.out.print("\nNombre de joueurs : ");

		Scanner sc = new Scanner(System.in);
		int nbJ = sc.nextInt();

		sc.nextLine();
		return nbJ;
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
		affichage += afficherLigneCarte(ligne3);


		// Affichage de la banque


		// Affichage des joueurs 2 par 2 à l'horizontal

		return affichage;
	}


	/* Affiche les cartes sur l'horizontal, 2 par 2 pour rentrer dans la console */
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

	public void initialiserPlateau(int nbJoueurs) {
		this.ctrl.initialiserPlateau(nbJoueurs);
	}


	private static String centrerText(String text, int len) {
		String out = String.format("%" + len + "s%s%" + len + "s", "", text, "");
		float mid = (out.length() / 2);
		float start = mid - (len / 2);
		float end = start + len;

		return out.substring((int) start, (int) end);
	}


}
