package minivilles.metier;

import minivilles.metier.cartes.Carte;
import minivilles.metier.cartes.monuments.Monument;

import java.util.*;

/**
 * Classe Joueur.
 * Un joueur est caractérisé par un numéro auto-incrémenté, un nombre de pièces, et une main.
 */
public class Joueur {
	private static int autoInc = 0;
	private int num;
	private int pieces;
	private ArrayList<Carte> main;

	/**
	 * On initialise le joueur avec son identifiant unique,
	 * et on met son nombre de pièces à 3.
	 */
	public Joueur() {
		this.num = ++autoInc;
		this.pieces = 20;
		main = new ArrayList<>();
	}

	/**
	 * Retourne l'identifiant du joueur.
	 *
	 * @return l'identifiant du joueur.
	 */
	public int getNum() {
		return this.num;
	}

	/**
	 * Retourne le nombre de pièces du joueur.
	 *
	 * @return le nombre de pièces du joueur.
	 */
	public int getPieces() {
		return this.pieces;
	}

	/**
	 * Retourne la main du joueur.
	 *
	 * @return la main du joueur.
	 */
	public ArrayList<Carte> getMain() {
		return this.main;
	}

	public ArrayList<Carte> getMonuments() {
		ArrayList<Carte> monuments = new ArrayList<>();

		for (Carte c : this.main)
			if (c instanceof Monument)
				monuments.add(c);

		return monuments;
	}

	/**
	 * Ajoute <i>nb</i> pièces au joueur.
	 *
	 * @param nb le nombre de pièces à ajouter
	 */
	public void addPiece(int nb) {
		this.pieces += nb;
	}

	/**
	 * Défini le nombre de pièces du joueur en <i>nb</i>
	 *
	 * @param nb Le nombre de pièces à définir
	 */
	public void setPiece(int nb) {
		this.pieces = nb;
	}

	/**
	 * Ajoute la carte <i>c</i> à la main du joueur.
	 *
	 * @param c la carte à ajouter
	 */
	public void addCarte(Carte c) {
		this.main.add(c);
	}

	/**
	 * Retire <i>nb</i> pièces au joueur.
	 *
	 * @param nb le nombre de pièces à retirer
	 */
	public void retirerPiece(int nb) {
		if (this.pieces > nb) this.pieces -= nb;
		else                  this.pieces = 0;
	}

	/**
	 * Prend la carte d'un joueur pour la donner à un autre
	 *
	 * @param carte     la carte à échanger
	 * @param receveur le joueur qui va recevoir la carte
	 * @return un booléen indiquant la réussite de l'opération
	 */
	public boolean echangerCarte(Carte carte, Joueur receveur) {
		carte.setJoueur(receveur);
		this.main.remove(carte);
		return receveur.main.add(carte);
	}

	/**
	 * Recherche une carte dans la main du joueur
	 *
	 * @param id     l'identifiant de la carte
	 * @return La carte trouvée, ou null
	 */
	public Carte rechercherCarte(String id) {
		Carte recherche = null;
		for(Carte c : main) if(c.getIdentifiant().equals(id)) recherche = c;
		return recherche;
	}
}
