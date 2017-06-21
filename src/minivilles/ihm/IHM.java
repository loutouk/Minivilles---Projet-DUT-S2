package minivilles.ihm;

import minivilles.Controleur;
import minivilles.metier.Joueur;
import minivilles.metier.cartes.Carte;

import java.util.*;

/**
 * Classe <i>IHM</i>.
 * <p>
 * Instanciée par le contrôleur, elle gère tous les affichages :
 * <p>
 * <ul>
 * <li>Affichage du plateau</li>
 * <li>Affichage du solde de la banque</li>
 * <li>Affichage de la situation des joueurs</li>
 * <li>Affichage des cartes</li>
 * </ul>
 * <p>
 * Elle est aussi chargée de récupérer les différentes saisies des joueurs,
 * notamment dans le menu principal ou lorsqu'ils doivent choisir un autre joueur
 * avec qui échanger des <i>{@link minivilles.metier.cartes.Carte}</i> ou des pièces.
 * <p>
 * L'IHM fait partie du modèle MVC et ses méthodes sont appelées par le {@link minivilles.Controleur},
 * qui fait le pont avec la partie métier.
 *
 * @see minivilles.Controleur
 * @see minivilles.metier.Metier
 * @see minivilles.metier.Joueur
 * @see minivilles.metier.Banque
 */
public abstract class IHM {

	protected Controleur ctrl;


	/**
	 * Instancie une IHM et assigne son contrôleur.
	 *
	 * @param ctrl le contrôleur amené à gérer cette IHM.
	 */
	public IHM(Controleur ctrl) {
		this.ctrl = ctrl;
	}


	public abstract void afficherMenuPrincipal();

	public abstract void afficherMenuAchat(Joueur joueur);

	public abstract void afficherMenuRejouer();


	/**
	 * Retourne le choix de l'utilisateur pour le menu principal.
	 * Si la saisie est incorrecte (<i>NumberFormatException</i> est levée),
	 * la fonction se rappelle elle-même.
	 *
	 * @return le choix de l'utilisateur.
	 */
	public abstract int choixMenu();

	public abstract int choixMenu(int min, int max);

	public abstract String choixStringMenu();

	/**
	 * Retourne le choix de l'utilisateur concernant le nombre de joueurs.
	 * Si la saisie est incorrecte (<i>NumberFormatException</i> est levée),
	 * la fonction se rappelle elle-même.
	 *
	 * @return le choix de l'utilisateur
	 */
	public abstract int choixNbJoueurs();

	public abstract int choixNbDes();

	public abstract int choixDebugDe();

	public abstract String choixIdentifiantCarte();

	public abstract String choixCarteCentreAffaire(String joueur);

	public abstract String choixJoueurCentreAffaire();

	public abstract String choixJoueurChaineTV();

	public abstract int getDe();


	/**
	 * Créé une représentation textuelle du plateau.
	 * On affiche la réserve la banque et les villes des joueurs.
	 */
	public abstract void afficherPlateau();

	public abstract void afficherDebutTour(Joueur j);

	/**
	 * Affiche les cartes ligne par ligne.
	 *
	 * @param listeCartes l'<i>ArrayList</i> de <i>Carte</i> à afficher.
	 */
	public abstract void afficherLigneCarte(ArrayList<Carte> listeCartes);

	public abstract void afficherColonneCarte(ArrayList<Carte> listeCartes);

	public abstract void afficherValeurDes(int de);

	public abstract void afficherBilanTour(Joueur joueur, int piecesAv, int nbDes, int des, List<Carte> cartesLancees);

	public abstract void afficherGagnant(Joueur gagnant);

	public abstract void afficherModeEvaluation();


	public abstract void nettoyerAffichage();


	protected static Map<String, List<Carte>> grouperCartes(ArrayList<Carte> listeCartes) {
		Map<String, List<Carte>> cartes = new HashMap<>();

		// On parcoure toutes les cartes pour les regrouper
		for (Carte c : listeCartes) {
			String iden = c.getIdentifiant();

			if (!cartes.containsKey(iden))
				cartes.put(iden, new ArrayList<Carte>());

			cartes.get(iden).add(c);
		}

		// On retourne un tableau trié
		return new TreeMap<>(cartes);
	}

}
