package minivilles.metier;

import minivilles.metier.cartes.*;

import minivilles.metier.cartes.monuments.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by bl160661 on 09/06/17.
 */

public class Metier {

	private ArrayList<Carte> pioche;
	private ArrayList<Joueur> listeJoueur;
	private Banque banque;
	private Joueur joueurCourant;


	public Metier() {
		this.pioche = new ArrayList<>();
		this.listeJoueur = new ArrayList<>();
		this.banque = new Banque();
		joueurCourant = null;
		this.initialiserPlateau(2);
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public ArrayList<Carte> getPioche() {
		return pioche;
	}

	public ArrayList<Joueur> getListeJoueur() {
		return listeJoueur;
	}

	/**
	 * Retourne la banque du jeu
	 *
	 * @return La banque
	 */
	public Banque getBanque() {
		return this.banque;
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

	/**
	 * Termine le tour du joueur courant et passe au joueur suivant.
	 *
	 * @return l'indice du joueur courant.
	 */
	public int changerJoueurCourant() {
		int indiceJoueurCourant = (this.listeJoueur.indexOf(this.joueurCourant) + 1) % this.listeJoueur.size();

		this.joueurCourant = this.listeJoueur.get(indiceJoueurCourant);

		return indiceJoueurCourant;
	}

	/** Genere les cartes selon le nombre de joueur, ainsi que les joueurs, la banque
	 * Les declencheurs servent d'identifiants de Carte
	 * @param nbJoueurs     le nombre de joueur de la partie
	 * @return void
	 * */
	public void initialiserPlateau(int nbJoueurs) {

		// Creation des joueurs
		for (int cpt = 0; cpt < nbJoueurs; cpt++) listeJoueur.add(new Joueur());
		joueurCourant = listeJoueur.get(0);

		// Creation de la pioche de 108 cartes

        // Etablissements de departs, 2 sortes pour 8 cartes
		for(int i=0 ; i<4 ; i++) pioche.add(new ChampsDeBle());
		for(int i=0 ; i<4 ; i++) pioche.add(new Boulangerie());

		// 3 sortes d'établissements spéciaux, pour 12 au total
		for(int i=0 ; i<4 ; i++) pioche.add(new Stade());
		for(int i=0 ; i<4 ; i++) pioche.add(new ChaineDeTelevision());
		for(int i=0 ; i<4 ; i++) pioche.add(new CentreAffaires());

		// 10 sortes d'établissements de base, pour 60 au total
		for(int i=0 ; i<6 ; i++) pioche.add(new Ferme());
		for(int i=0 ; i<6 ; i++) pioche.add(new Cafe());
		for(int i=0 ; i<6 ; i++) pioche.add(new Superette());
		for(int i=0 ; i<6 ; i++) pioche.add(new Foret());
		for(int i=0 ; i<6 ; i++) pioche.add(new Fromagerie());
		for(int i=0 ; i<6 ; i++) pioche.add(new FabriqueMeuble());
		for(int i=0 ; i<6 ; i++) pioche.add(new Mine());
		for(int i=0 ; i<6 ; i++) pioche.add(new Restaurant());
		for(int i=0 ; i<6 ; i++) pioche.add(new Verger());
		for(int i=0 ; i<6 ; i++) pioche.add(new MarcheDeFruitsEtLegumes());


		// Attributions des cartes
		for(Joueur joueur : listeJoueur){
			piocher("1", joueur);
			piocher("2-3", joueur);
			// On donne les monuments au joueurs
            // 4 sortes de monuments, pour 16 au total
            joueur.getMain().add(new Gare());
            joueur.getMain().add(new CentreCommercial());
            joueur.getMain().add(new ParcDattractions());
            joueur.getMain().add(new TourRadio());
		}

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
		for (Carte c : this.pioche)
			if (c.getNom().equals(id) || c.getIdentifiant().equals(id))
				carte = c;

		if (pioche.contains(carte)) {
			joueur.getMain().add(carte);
			return pioche.remove(carte);
		}

		return false;
	}



}