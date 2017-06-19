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

	}

	/* Affiche le plateau */
	public String toString() {
		return "";
	}
}