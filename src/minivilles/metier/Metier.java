package minivilles.metier;

/**
 * Created by bl160661 on 09/06/17.
 */

import java.util.ArrayList;
import java.util.List;

public class Metier
{
	private ArrayList<Carte> lCarte;
	private ArrayList<Joueur> lJoueur;

	public Reseau()
	{

		lCarte = new ArrayList<Carte>();
		lJoueur = new ArrayList<Joueur>();

	}

	public ArrayList<Carte> getlCarte() {
		return lCarte;
	}

	public ArrayList<Joueur> getlJoueur() {
		return lJoueur;
	}
}