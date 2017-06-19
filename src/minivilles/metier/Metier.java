package minivilles.metier;

/**
 * Created by bl160661 on 09/06/17.
 */

import java.util.ArrayList;
import java.util.List;

public class Metier
{
	private ArrayList<Carte> pioche;
	private ArrayList<Joueur> listeJoueur;

	public Metier()
	{
		pioche = new ArrayList<Carte>();
		listeJoueur = new ArrayList<Joueur>();
	}

	public ArrayList<Carte> getlCarte() {
		return pioche;
	}

	public ArrayList<Joueur> getlJoueur() {
		return listeJoueur;
	}

	/* Affiche le plateau */
	public String toString(){
		return "";
	}
}