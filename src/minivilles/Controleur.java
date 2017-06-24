package minivilles;

import minivilles.ihm.IHM;
import minivilles.ihm.console.IHMConsole;
import minivilles.ihm.gui.IHMGUI;
import minivilles.metier.Joueur;
import minivilles.metier.Metier;
import minivilles.metier.cartes.Carte;
import minivilles.metier.cartes.monuments.Monument;
import minivilles.net.Client;
import minivilles.net.Serveur;
import minivilles.utilitaire.Sauvegarde;

import java.util.ArrayList;
import java.util.List;

public class Controleur {

	private static IHM ihm;
	private static Metier metier;

	// Réseau
	private Serveur serveur;
	private Client client;
	private int monNumJoueur;

	private boolean debugMode;
	private boolean netMode;


	public Controleur() {
		this("console");
	}
	public Controleur(String mode) {
		this(mode, false);
	}
	public Controleur(String mode, boolean debugMode) {
		this(mode, debugMode, false);
	}
	public Controleur(String mode, boolean debugMode, boolean netMode) {
		Controleur.metier = new Metier();

		if (mode.equals("console"))
			Controleur.ihm = new IHMConsole(this);
		else{
			Controleur.ihm = new IHMGUI(this);
		}

		this.debugMode = debugMode;
		this.netMode = netMode;
	}



	/**
	 * Accesseur pour récupérer le métier depuis la partie Test du programme
	 *
	 * @return Le metier
	 */
	public Metier getMetier() {
		return Controleur.metier;
	}

	public Joueur getGagnant() {
		for (Joueur joueur : Controleur.metier.getJoueurs()) {
			int nbMon = 0;

			for (Carte monument : joueur.getMonuments())
				if (!((Monument) monument).estEnConstruction())
					nbMon++;

			if (nbMon == 4) return joueur;
		}

		return null;
	}


	public void lancer() {
		boolean quitter = false;
		int choix = 1;

		// On lance les modes de jeu spéciaux
		if (this.debugMode)
			ihm.afficherModeEvaluation();
		if (this.netMode)
			this.initialiserReseau();

		while (!quitter) {
			if (!this.netMode)
				choix = ihm.choixMenuPrincipal();

			switch (choix) {
				case 1:
					// En mode évaluation, l'initialisation de la partie se fait autre part.
					if (!this.debugMode) this.initialiserPartie();

					// On attends que la connexion réseau soit établie
					this.connexionReseau();

					this.lancerPartie();
					break;

				case 2:
					quitter = true;
					break;
			}
		}
	}


	private void initialiserReseau() {
		boolean estServeur = ihm.choixEstServeur();
		String hote = "localhost";

		if (estServeur) {
			// Démarrage du serveur, si le joueur veut en créer un
			this.serveur = new Serveur(metier);
			this.serveur.start();

			// On attends que le serveur se lance
			while (!this.serveur.estLance()) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			hote = ihm.choixServeurHote();
		}

		// Démarrage du client et connexion au serveur
		do {
			this.client = Client.nouveauClient(this, hote);

			if (this.client == null) {
				ihm.afficherErreur("Aucune partie n'est lancée à l'adresse " + hote + " !");

				hote = ihm.choixServeurHote();
			}
		}
		while (this.client == null);
	}

	private void connexionReseau() {
		// Si le mode réseau est actif et que le serveur est hébergé ici,
		// on attends que tous les joueurs se connectent.
		if (this.netMode && this.serveur != null) {
			ihm.afficherAttenteReseau("En attente de joueurs...");

			while (this.serveur.getNbClientsConnected() < metier.getJoueurs().size()) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			ihm.finAttenteReseau();
			this.client.envoiMetier(metier);
		}

		// Si le mode réseau est actif et que le jeu est connecté
		// sur un serveur, on attends que ce dernier envoie le métier.
		if (this.netMode && this.serveur == null) {
			while (metier.getJoueurCourant() == null) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			ihm.majTotale(metier);
		}
	}

	private void attenteMajServeur() {
		if (!this.netMode || this.client == null) return;

		this.client.resetMajsServeur();

		while (!this.client.isNouvelleMajServeur()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	private void initialiserPartie() {
		// Si on est connecté à un serveur,
		// ce dernier initialise la partie directement.
		if (this.netMode && this.serveur == null) return;

		boolean chargerPartie = ihm.choixChargerPartie();

		if (chargerPartie) {
			Metier sauvegarde = Sauvegarde.getInstance().charger();

			// Si pas de fichier sauvegarde
			if (sauvegarde == null) {
				chargerPartie = false;
				ihm.afficherErreur("Pas de fichier sauvegarde existant ! Création d'une nouvelle partie...");
			}
			// Chargement réussi, on charge le métier sérialisé
			else {
				metier = sauvegarde;
			}
		}

		if (!chargerPartie)
			metier.initialiserPlateau(ihm.choixNbJoueurs());

		ihm.initialiserPlateau(metier.getPioche(), metier.getJoueurs().size());
	}

	private void lancerPartie() {
		do {
			Joueur joueur = Controleur.metier.getJoueurCourant();
			boolean monTour = (!this.netMode || (this.monNumJoueur == joueur.getNum()));

			ihm.nouveauTour(joueur, monTour);
			ihm.afficherPlateau(metier.getPioche(), metier.getBanque(), metier.getListeJoueur());

			if (monTour) {
				this.jouerTour(joueur);
			} else {
				// Les autres joueurs doivent attendre la fin du tour actuel
				ihm.afficherAttenteReseau("Le joueur " + metier.getJoueurCourant().getNum() + " joue...");
				this.attenteMajServeur();
				ihm.finAttenteReseau();
			}
		}
		while (this.getGagnant() == null);


		// On affiche le gagnant à l'écran
		Controleur.ihm.afficherGagnant(this.getGagnant());

		// On ferme les connexion au réseau car le jeu est terminé
		if ( this.client != null) this.client.fermerConnexion();
		if (this.serveur != null) this.serveur.fermer();
	}

	private void jouerTour(Joueur joueur) {
		// Effet du ParcDattractions : on peut rejouer un tour si le jet de dés est un double
		boolean rejouer;

		int pieceAvant = joueur.getPieces();

		// Effet du monument Gare : deux jet de dés
		int nombreDeCoups = 1;
		int nombreDeDes = ((Monument) (joueur.rechercherCarte("M1"))).estEnConstruction() ? 1 : 2;
		int de1 = 0;
		int de2 = 0;

		// Il peut lancer deux dés, on demande avant au joueur pour être sûr
		if (nombreDeDes > 1) nombreDeDes = ihm.choixNbDes();

		for (int compteur = 0; compteur < nombreDeCoups * nombreDeDes; compteur++) {
			int de;

			// On lance le dé (ou on le défini, via le mode d'évaluation)
			if (!this.debugMode) de = this.lancerDe();
			else de = ihm.choixDebugDe();

			if (de1 == 0) de1 = de;
			else de2 = de;

			// Effet du monument Tour : on peut choisir de relancer les dés
			if (!((Monument) (joueur.rechercherCarte("M4"))).estEnConstruction() &&
					nombreDeCoups == 1 &&
					(compteur - 1) % nombreDeDes == 0) {

				ihm.afficherDes(de1, de2);

				if (ihm.choixRejouerTour() == 1) {
					nombreDeCoups++;

					de1 = 0;
					de2 = 0;
				}
			}
		}

		// On lance les effets de toutes les cartes
		List<Carte> cartesLancees = metier.lancerEffets(de1 + de2);

		rejouer = (de1 == de2 && !((Monument) (joueur.rechercherCarte("M3"))).estEnConstruction());

		// Affichage de l'effet pour rejouer le tour
		if (rejouer) ihm.afficherRejouerEffet();

		// Affichage du bilan du tour
		ihm.afficherBilanTour(joueur, pieceAvant, nombreDeDes, de1, de2, cartesLancees);
		ihm.majPlateau(metier.getListeJoueur());


		// Menu d'achat de batîment
		boolean achatFini = false;

		do {

			int choix = ihm.choixAchatMenu(joueur);

			if (choix == 1 || choix == 2) {
				ArrayList<Carte> cartes = (choix == 1) ? metier.getPioche() : joueur.getMonuments();

				ihm.nettoyerAffichage();
				ihm.afficherLigneCarte(cartes);

				// On ouvre le menu spécifique d'achat de batîment
				if (this.achatBatiment(joueur, choix))
					achatFini = true;
			} else achatFini = true;

		} while (!achatFini);

		// Sauvegarde
		Sauvegarde.getInstance().sauvegarder(metier);

		if (!rejouer) Controleur.metier.changerJoueurCourant();

		// Envoi du métier au serveur pour mettre à jour le jeu des autres joueurs
		if (this.client != null)
			this.client.envoiMetier(Controleur.metier);
	}

	private boolean achatBatiment(Joueur joueur, int choix) {
		Carte carte;
		boolean achatTermine = false;

		do {
			String choixBat;

			// On personnalise la recherche suivant le choix
			if (choix == 1) {
				choixBat = ihm.choixAchatBatiment();
				carte = metier.rechercherCartePioche(choixBat);
			} else {
				choixBat = ihm.choixAchatMonument();
				carte = joueur.rechercherCarte(choixBat);
			}

			if (choixBat.equals("-1"))
				return false;
			else {
				if (carte == null) {
					ihm.afficherErreur("La carte \"" + choixBat + "\" n'existe pas !");
					continue;
				}

				switch (choix) {
					case 1:
						if (metier.acheter(carte.getIdentifiant(), metier.getJoueurCourant()))
							achatTermine = true;
						else
							ihm.afficherErreur("Vous n'avez pas assez de pièces pour acheter ce bâtiment !");

						break;
					case 2:
						if (carte instanceof Monument) {
							Monument monument = (Monument) carte;

							if (!monument.estEnConstruction()) {
								ihm.afficherErreur("Vous possédez déjà ce monument !");
								continue;
							}

							if (metier.construireMonument(monument, joueur))
								achatTermine = true;
							else
								ihm.afficherErreur("Vous n'avez pas assez de pièces pour acheter ce monument !");
						} else {
							ihm.afficherErreur("La carte sélectionnée n'est pas un monument.");
						}

						break;
				}
			}
		}
		while (!achatTermine);

		return true;
	}

	private int lancerDe() {
		return 1 + (int) (Math.random() * 6);
	}

	/**
	 * Appelée lorsque le serveur envoie le métier à tous les joueurs.
	 * Elle remplace le métier courant par celui du serveur.
	 *
	 * @param metierServeur Le métier envoyé par le serveur
	 */
	public void majDepuisServeur(Metier metierServeur) {
		Controleur.metier = metierServeur;
		ihm.majTotale(Controleur.metier);
	}

	/**
	 * Méthode appelée lorsque le serveur envoi le numéro de joueur de ce controleur
	 *
	 * @param numJoueur Le numéro calculé par le serveur
	 */
	public void setMonNumJoueur(int numJoueur) {
		this.monNumJoueur = numJoueur;
	}


	public static IHM getIhm() { return ihm; }


	public static void main(String[] a) {
		String mode = "console";
		boolean netMode = false;

		if (a.length > 0 && a[0].equals("gui")) mode = a[0];
		if (a.length > 1 && a[1].equals("net")) netMode = true;

		new Controleur(mode, false, netMode).lancer();
	}
}