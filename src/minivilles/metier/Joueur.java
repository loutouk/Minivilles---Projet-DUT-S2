package minivilles.metier;

import java.util.*;

/**
 * Classe Joueur.
 * Un joueur est caractérisé par un numéro auto-incrémenté, un nombre de pièces, et une main.
 */
public class Joueur {
	private static int autoInc = 0;
	private int num;
	private int pieces;
	private ArrayList<Carte> main = new ArrayList<>();

	/**
	 * On initialise le joueur avec son identifiant unique,
	 * et on met son nombre de pièces à 3.
	 */
	public Joueur() {
		this.num = autoInc++;
		this.pieces = 3;
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
		if (this.pieces < nb) this.pieces -= nb;
		else this.pieces = 0;
	}
}
