package minivilles.metier;

import minivilles.metier.cartes.*;
import minivilles.metier.cartes.monuments.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Metier.java
 * Classe métier du programme, c'est le moteur qui contient la boucle de jeu.
 * @see minivilles.Controleur
 * @author Richard Blondel
 * @author Valentin Dulong
 * @author Maxime Malgorn
 * @author Louis Boursier
 * @version 1.0
 */

public class Metier implements Serializable {

	private ArrayList<Carte> pioche;
	private ArrayList<Joueur> listeJoueur;
	private Banque banque;
	private Joueur joueurCourant;


	public Metier() {
		this.pioche = new ArrayList<>();
		this.listeJoueur = new ArrayList<>();
		this.banque = new Banque();
		joueurCourant = null;
	}


	/**
	 * Retourne le joueur à qui il appartient de jouer
	 *
	 * @return Le joueur courant
	 */
	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	/**
	 * Retourne la pioche, également appelée la réserve, qui contient les bâtiments
	 *
	 * @return La pioche
	 */
	public ArrayList<Carte> getPioche() {
		return pioche;
	}

	/**
	 * Retourne la liste des joueurs de la partie
	 *
	 * @return Les joueurs
	 */
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
     * @param indice la position (numéro) du joueur, en partant de 0
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

	/**
	 * Si le joueur a créé une nouvelle partie
	 * Créé toutes les cartes du jeu, monuments et bâtiments, ainsi que la banque
	 * Distribue les cartes de base aux joueurs ainsi que leurs monuments, créé la réserve
	 *
	 * @param nbJoueurs le nombre de joueur de la partie
	 * @param sansCarteDepart Utilisé pour le mode de test d'évaluation
	 */
	public void initialiserPlateau(int nbJoueurs, boolean sansCarteDepart) {

		// Creation des joueurs
		for (int cpt = 0; cpt < nbJoueurs; cpt++) listeJoueur.add(new Joueur());
		joueurCourant = listeJoueur.get(0);

		// Creation de la pioche de 108 cartes

		// Etablissements de departs, 2 sortes pour 8 cartes
		for (int i = 0; i < 4; i++) pioche.add(new ChampsDeBle());
		for (int i = 0; i < 4; i++) pioche.add(new Boulangerie());

		// 3 sortes d'établissements spéciaux, pour 12 au total
		for (int i = 0; i < 4; i++) pioche.add(new Stade());
		for (int i = 0; i < 4; i++) pioche.add(new ChaineDeTelevision());
		for (int i = 0; i < 4; i++) pioche.add(new CentreAffaires());

		// 10 sortes d'établissements de base, pour 60 au total
		for (int i = 0; i < 6; i++) pioche.add(new Ferme());
		for (int i = 0; i < 6; i++) pioche.add(new Cafe());
		for (int i = 0; i < 6; i++) pioche.add(new Superette());
		for (int i = 0; i < 6; i++) pioche.add(new Foret());
		for (int i = 0; i < 6; i++) pioche.add(new Fromagerie());
		for (int i = 0; i < 6; i++) pioche.add(new FabriqueMeuble());
		for (int i = 0; i < 6; i++) pioche.add(new Mine());
		for (int i = 0; i < 6; i++) pioche.add(new Restaurant());
		for (int i = 0; i < 6; i++) pioche.add(new Verger());
		for (int i = 0; i < 6; i++) pioche.add(new MarcheDeFruitsEtLegumes());


		// Attributions des cartes
		for (Joueur joueur : listeJoueur) {

			// On pioche les cartes de départ
			if (!sansCarteDepart) {
				piocher("1", joueur);
				piocher("2-3", joueur);
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

	/**
	 * Pour tous les joueurs, appelle une méthode qui va tester si les effets de leurs cartes ont lieux de se déclencher
	 *
	 * @param resultatDes le nombre obtenu aux dés
	 * @return La liste des cartes
	 */
	public List<Carte> lancerEffets(int resultatDes) {
		List<Carte> cartes = new ArrayList<>();

		for (Joueur joueur : this.listeJoueur)
			for (int cpt = 0; cpt < joueur.getMain().size(); cpt++) {
				Carte c = joueur.getMain().get(cpt);

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

	/**
	 * Affecte la carte correspondant à l'identifiant au joueur
	 *
	 * @param id le nom ou bien l'identifiant de la carte
	 * @param joueur le joueur courant
	 * @return Le succès de l'achat
	 */
	public boolean acheter(String id, Joueur joueur) {
		Carte carte = this.rechercherCartePioche(id);
		if (carte == null) return false;

		if (joueur.getPieces() >= carte.getCout()) {
			joueur.retirerPiece(carte.getCout());
			this.piocher(id, joueur);

			return true;
		}

		return false;
	}

	/**
	 * Passe l'état du monument du joueur de l'état en construction à construit
	 *
	 * @param monument la carte de type Monument
	 * @param joueur le joueur courant
	 * @return Le succès de la construction
	 */
	public boolean construireMonument(Monument monument, Joueur joueur) {
		if (monument == null) return false;

		if (joueur.getPieces() >= monument.getCout()) {
			joueur.retirerPiece(monument.getCout());
			monument.construire();

			return true;
		}

		return false;
	}


	/**
	 * Recherche une carte par son nom ou identifiant dans la réserve
	 *
	 * @param rech l'identifiant ou le nom de la carte
	 * @return La carte trouvée, null sinon
	 */
	public Carte rechercherCartePioche(String rech) {
		for (Carte carte : this.pioche)
			if (carte.getIdentifiant().equals(rech) || carte.getNom().equalsIgnoreCase(rech))
				return carte;

		return null;
	}

}
