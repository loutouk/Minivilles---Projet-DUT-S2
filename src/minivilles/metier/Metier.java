package minivilles.metier;

import minivilles.metier.cartes.Carte;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bl160661 on 09/06/17.
 */

public class Metier {

	private ArrayList<Carte> pioche;
	private ArrayList<Joueur> listeJoueur;
	private static Banque banque;


	public Metier() {
		this.pioche = new ArrayList<>();
		this.listeJoueur = new ArrayList<>();
		banque = new Banque();
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

	public List<Joueur> getJoueurs() {
		return new ArrayList<>(this.listeJoueur);
	}


	/** Genere les cartes selon le nombre de joueur, ainsi que les joueurs, la banque
	 * Les declencheurs servent d'identifiants de Carte
	 * @param nbJoueurs     le nombre de joueur de la partie
	 * @return void
	 * */
	public void initialiserPlateau(int nbJoueurs) {

		// Creation des joueurs
		for (int cpt = 0; cpt < nbJoueurs; cpt++) listeJoueur.add(new Joueur());

		// Creation de la pioche de 108 cartes

		// Etablissements de departs, 2 sortes pour 8 cartes


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

	/* Affiche les cartes sur l'horizontal */
	public String afficherLigneCarte(ArrayList<Carte> cartes) {

		String affichage = "";
		String bord = "--------------------------------";

		for(Carte ignored : cartes) affichage += bord + " ";
		affichage+="\n";


		for(Carte c : cartes)  affichage += "|" + String.format("%-30s", "Declencheur : "
				+ c.getDeclencheur() + " " + (c.getDeclencheur2() == -1 ? " " : c.getDeclencheur2()))  + "|"  + " ";
		affichage+="\n";

		for(Carte ignored : cartes) affichage +=  String.format("%-30s", bord) + " ";
		affichage+="\n";

		for(Carte c : cartes)  affichage += "|" + String.format("%-30s", "Nom : " + c.getNom())+ "|"  + " ";
		affichage+="\n";

		for(Carte ignored : cartes) affichage +=  String.format("%-30s", bord) + " ";
		affichage+="\n";

		for(Carte c : cartes)  affichage += "|" + String.format("%-30s", "Effet : " + c.getTexteEffet().substring(0,21)) + "|" + " ";
		affichage+="\n";

		for(Carte c : cartes)  affichage += "|" + String.format("%-30s", c.getTexteEffet().substring(21,50)) + "|" + " ";
		affichage+="\n";

		for(Carte c : cartes)  affichage += "|" + String.format("%-30s", c.getTexteEffet().substring(50,70)) + "|" + " ";
		affichage+="\n";

		for(Carte ignored : cartes) affichage +=  String.format("%-30s", bord) + " ";
		affichage+="\n";

		for(Carte c : cartes) affichage += "|" + String.format("%-30s", "Cout de construction : " + c.getCout()) + "|" + " ";
		affichage+="\n";

		for(Carte ignored : cartes) affichage +=  String.format("%-30s", bord) + " ";
		affichage+="\n";

		return affichage;
	}
}