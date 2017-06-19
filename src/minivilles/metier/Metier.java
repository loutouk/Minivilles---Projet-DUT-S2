package minivilles.metier;

import java.util.ArrayList;
import minivilles.metier.*;

/**
 * Created by bl160661 on 09/06/17.
 */

public class Metier {

	private ArrayList<Carte> pioche;
	private ArrayList<Joueur> listeJoueur;
	private Banque banque;


	public Metier() {
		pioche = new ArrayList<Carte>();
		listeJoueur = new ArrayList<Joueur>();
	}

	/* Genere les cartes selon le nombre de joueur, ainsi que les joueurs, la banque */
	public void initialiserPlateau(int nbJoueurs) {

		// Creation des joueurs
		for(int cpt=0 ; cpt<nbJoueurs ; cpt++) listeJoueur.add(new Joueur());

		// Creation de la pioche
		// Cartes de bases
		// Cartes des joueurs
		switch (nbJoueurs){
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

	/* L'id peut etre l'dientifiant ou le nom de la carte */
	public boolean piocher(String id, Joueur joueur){

		Carte carte = null;

		// On recupere la carte correspondant au nom
		for(Carte c : pioche) if(c.getNom().equals(id)||c.getIdentifiant().equals(id)) carte = c;

		if(pioche.contains(carte)) {
			joueur.getArCartes().add(carte);
			return pioche.remove(carte);
		}

		return false;

	}

	/* Affiche le plateau */
	public String toString() {
		return "";
	}
}