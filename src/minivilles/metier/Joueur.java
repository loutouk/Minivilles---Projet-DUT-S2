package minivilles.metier;

import minivilles.metier.cartes.Carte;
import minivilles.metier.cartes.monuments.Monument;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Joueur.java
 * Un joueur est caractérisé par un numéro auto-incrémenté, un nombre de pièces, et une main.
 * Les joueurs, lorsqu'ils sont amenés à le faire, peuvent piocher dans ce solde,
 * qui est initialisé par défaut à 1000 pièces.
 * @author Richard Blondel
 * @author Valentin Dulong
 * @author Maxime Malgorn
 * @author Louis Boursier
 * @version 1.0
 */

public class Joueur implements Serializable {
	private transient static int autoInc = 0;
	private int num;
	private int pieces;
	private ArrayList<Carte> main;

	/**
	 * On initialise le joueur avec son identifiant unique,
	 * et on met son nombre de pièces à 3.
	 */
	public Joueur() {
		this.num = ++autoInc;
		this.pieces = 1000;
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
		else this.pieces = 0;
	}

	/**
	 * Prend la carte d'un joueur pour la donner à un autre
	 *
	 * @param carteCourant la carte que le joueur qui lance l'échange va perdre
	 * @param carteCible la carte que le joueur cible va perdre
	 * @param cible le joueur qui subit l'échange
	 */
	public void echangerCarte(Carte carteCourant, Carte carteCible, Joueur cible) {
		// Le joueur courant this est le donneur
		carteCible.setJoueur(this);
		carteCourant.setJoueur(cible);
		this.main.remove(carteCourant);
		cible.main.remove(carteCible);
		this.main.add(carteCible);
		cible.main.add(carteCourant);
	}

	/**
	 * Recherche une carte dans la main du joueur
	 * par son identifiant ou son nom.
	 *
	 * @param rech La chaîne à utiliser pour la recherche
	 * @return La carte trouvée, ou null
	 */
	public Carte rechercherCarte(String rech) {
		for (Carte carte : main)
			if (carte.getIdentifiant().equals(rech) || carte.getNom().equalsIgnoreCase(rech))
				return carte;

		return null;
	}
}
