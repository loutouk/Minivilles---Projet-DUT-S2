package minivilles.metier;

import java.util.ArrayList;

/**
 * Created by bl160661 on 09/06/17.
 */

public class Metier {

	private ArrayList<Carte> pioche;
	private ArrayList<Joueur> listeJoueur;
	private Banque banque;


	public Metier() {
		this.pioche = new ArrayList<>();
		this.listeJoueur = new ArrayList<>();
	}

	/**
	 * Retourne le joueur par son numéro
	 *
	 * @return Le joueur en question
	 */
	public Joueur getJoueur(int indice) {
		if (indice < 0 || indice >= listeJoueur.size()) return null;

		return this.listeJoueur.get(indice);
	}


	/* Genere les cartes selon le nombre de joueur, ainsi que les joueurs, la banque */
	public void initialiserPlateau(int nbJoueurs) {

		// Creation des joueurs
		for (int cpt = 0; cpt < nbJoueurs; cpt++) listeJoueur.add(new Joueur());

		// Creation de la pioche
		// Cartes de bases
		// Cartes des joueurs
		switch (nbJoueurs) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
		}

		// Creation de la banque
		banque = new Banque();
	}

	/**
	 * Pioche la carte dont on donne l'identifiant, et la place dans la main du joueur passé en paramètre.
	 *
	 * @param id     l'identifiant de la carte
	 * @param joueur le joueur qui va recevoir la carte
	 * @return un booléen indiquant la réussite de l'opération
	 */
	public boolean piocher(String id, Joueur joueur) {
		Carte carte = null;

		// On récupère la carte correspondant au nom
		for (Carte c : pioche) if (c.getNom().equals(id) || c.getIdentifiant().equals(id)) carte = c;

		if (pioche.contains(carte)) {
			joueur.getMain().add(carte);
			return pioche.remove(carte);
		}

		return false;
	}

	/* Affiche le plateau */
	public String toString() {
		return "";
	}

	// push
	/* Affiche les cartes sur l'horizontal */
	public String afficherLigneCarte(ArrayList<Carte> cartes) {

		String affichage = "";
		String bord = "--------------------------------";

		for(Carte c : cartes) affichage += bord + " ";
		affichage+="\n";


		for(Carte c : cartes)  affichage += "|" + String.format("%-30s", "Declencheur : "
				+ c.getDeclencheur() + " " + (c.getDeclencheur2() == -1 ? " " : c.getDeclencheur2()))  + "|"  + " ";
		affichage+="\n";

		for(Carte c : cartes) affichage +=  String.format("%-30s", bord) + " ";
		affichage+="\n";

		for(Carte c : cartes)  affichage += "|" + String.format("%-30s", "Nom : " + c.getNom())+ "|"  + " ";
		affichage+="\n";

		for(Carte c : cartes) affichage +=  String.format("%-30s", bord) + " ";
		affichage+="\n";

		for(Carte c : cartes)  affichage += "|" + String.format("%-30s", "Effet : " + c.getTexteEffet().substring(0,21)) + "|" + " ";
		affichage+="\n";

		for(Carte c : cartes)  affichage += "|" + String.format("%-30s", c.getTexteEffet().substring(21,50)) + "|" + " ";
		affichage+="\n";

		for(Carte c : cartes)  affichage += "|" + String.format("%-30s", c.getTexteEffet().substring(50,70)) + "|" + " ";
		affichage+="\n";

		for(Carte c : cartes) affichage +=  String.format("%-30s", bord) + " ";
		affichage+="\n";

		for(Carte c : cartes) affichage += "|" + String.format("%-30s", "Cout de construction : " + c.getCout()) + "|" + " ";
		affichage+="\n";

		for(Carte c : cartes) affichage +=  String.format("%-30s", bord) + " ";
		affichage+="\n";

		return affichage;
	}
}