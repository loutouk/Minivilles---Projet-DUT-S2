package minivilles.metier;

import minivilles.metier.cartes.*;
import minivilles.metier.cartes.monuments.*;

import java.util.ArrayList;
import java.util.List;

public class Metier {

	private ArrayList<Carte> pioche;
	private ArrayList<Joueur> listeJoueur;
	private Banque banque;
	private Joueur joueurCourant;


	public Metier() {
		this.pioche = new ArrayList<>();
		this.listeJoueur = new ArrayList<>();
		this.banque = new Banque();

		this.joueurCourant = null;
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
	 * Retourne le joueur par sa place dans le jeu
	 *
	 * @return Le joueur en question
	 */
	public Joueur getJoueur(int indice) {
		if (indice < 0 || indice >= listeJoueur.size()) return null;

		return this.listeJoueur.get(indice);
	}

	/**
	 * Retourne une copie de l'<i>ArrayList</i> qui contient les joueurs,
	 * afin de ne pas modifier les objets <i>Joueur</i>.
	 *
	 * @return une <i>List</i> des joueurs
	 */
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

	/**
	 * Genere les cartes selon le nombre de joueur, ainsi que les joueurs, la banque
	 * Les declencheurs servent d'identifiants de Carte
	 *
	 * @param nbJoueurs le nombre de joueur de la partie
	 */
	public void initialiserPlateau(int nbJoueurs) {
		this.initialiserPlateau(nbJoueurs, false);
	}

	public void initialiserPlateau(int nbJoueurs, boolean sansCarteDepart) {

		/*  Creation des joueurs  */
		for (int cpt = 0; cpt < nbJoueurs; cpt++) listeJoueur.add(new Joueur());
		joueurCourant = listeJoueur.get(0);


		/*  Creation de la pioche de 108 cartes  */

		// Etablissements de departs, 2 sortes pour 8 cartes
		for (int i = 0; i < 4; i++) {
			pioche.add(new ChampsDeBle());
			pioche.add(new Boulangerie());
		}

		// 3 sortes d'établissements spéciaux, pour 12 au total
		for (int i = 0; i < 4; i++) {
			pioche.add(new Stade());
			pioche.add(new ChaineDeTelevision());
			pioche.add(new CentreAffaires());
		}

		// 10 sortes d'établissements de base, pour 60 au total
		for (int i = 0; i < 6; i++) {
			pioche.add(new Ferme());
			pioche.add(new Cafe());
			pioche.add(new Superette());
			pioche.add(new Foret());
			pioche.add(new Fromagerie());
			pioche.add(new FabriqueMeuble());
			pioche.add(new Mine());
			pioche.add(new Restaurant());
			pioche.add(new Verger());
			pioche.add(new MarcheDeFruitsEtLegumes());
		}


		/*  Attributions des cartes  */
		for (Joueur joueur : listeJoueur) {
			// On pioche les cartes de départ
			if (!sansCarteDepart) {
				this.piocher("1", joueur);
				this.piocher("2-3", joueur);
			}

			// On donne les monuments au joueurs
			// 4 sortes de monuments, pour 16 au total
			Carte monument = new Gare();
			monument.setJoueur(joueur);
			joueur.getMain().add(monument);

			monument = new CentreCommercial();
			monument.setJoueur(joueur);
			joueur.getMain().add(monument);

			monument = new ParcDattractions();
			monument.setJoueur(joueur);
			joueur.getMain().add(monument);

			monument = new TourRadio();
			monument.setJoueur(joueur);
			joueur.getMain().add(monument);
		}

	}

	public List<Carte> lancerEffets(int resultatDes) {
		List<Carte> cartes = new ArrayList<>();

		// On lance les effets de toutes les cartes de tous les joueurs
		for (Joueur joueur : this.listeJoueur)
			for (int cpt = 0; cpt < joueur.getMain().size(); cpt++) {
				Carte c = joueur.getMain().get(cpt);

				// Si l'effet a été lancé, on ajoute la carte à la liste des cartes activées
				if (c.testEffet(this, resultatDes))
					cartes.add(c);
			}

		return cartes;
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
			carte.setJoueur(joueur);
			joueur.getMain().add(carte);
			return pioche.remove(carte);
		}

		return false;
	}

	public boolean acheter(String id, Joueur joueur) {
		Carte carte = this.rechercherCartePioche(id);
		if (carte == null) return false;

		if (joueur.getPieces() >= carte.getCout()) {
			joueur.retirerPiece(carte.getCout());
			this.banque.credit(carte.getCout());

			this.piocher(id, joueur);
			return true;
		}

		return false;
	}

	public boolean construireMonument(Monument monument, Joueur joueur) {
		if (monument == null) return false;

		if (joueur.getPieces() >= monument.getCout()) {
			joueur.retirerPiece(monument.getCout());
			monument.construire();

			return true;
		}

		return false;
	}

	public Carte rechercherCartePioche(String id) {
		for (Carte c : this.pioche)
			if (c.getIdentifiant().equals(id))
				return c;

		return null;
	}

}