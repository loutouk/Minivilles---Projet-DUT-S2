package minivilles;

import minivilles.ihm.IHM;
import minivilles.ihm.console.IHMConsole;
import minivilles.ihm.gui.IHMGUI;
import minivilles.metier.Joueur;
import minivilles.metier.Metier;
import minivilles.metier.cartes.Carte;
import minivilles.metier.cartes.monuments.Monument;

import java.util.ArrayList;
import java.util.List;

public class Controleur {

	private static IHM ihm;
	private static Metier metier;

	private boolean debugMode;


	public Controleur() {
		this("console");
	}
	public Controleur(String mode) {
		this(mode, false);
	}
	public Controleur(String mode, boolean debugMode) {
		Controleur.metier = new Metier();

		if (mode.equals("console"))
			Controleur.ihm = new IHMConsole(this);
		else
			Controleur.ihm = new IHMGUI(this);

		this.debugMode = debugMode;
	}


	/**
	 * Accesseur pour récupérer le métier depuis la partie Test du programme
	 *
	 * @return Le metier
	 */
	public Metier getMetier() {
		return Controleur.metier;
	}

	public void lancer() {
		boolean quitter = false;
		int choix;

		if (this.debugMode)
			ihm.afficherModeEvaluation();

		while (!quitter) {
			ihm.afficherMenuPrincipal();

			choix = ihm.choixMenu();

			switch (choix) {
				case 1:
					// En mode évaluation, l'initialisation de la partie se fait autre part.
					if (!this.debugMode) this.initialiserPartie();

					this.lancerPartie();
					break;

				case 2:
					quitter = true;
					break;
			}
		}
	}

	private void initialiserPartie() {
		int nbJoueurs = ihm.choixNbJoueurs();
		metier.initialiserPlateau(nbJoueurs);

		ihm.initialiserCartes(metier.getPioche());
	}

	private void lancerPartie() {
		do {
			// Effet du ParcDattractions : on peut rejouer un tour si le jet de dés est un double
			boolean rejouer;

			Joueur joueur = Controleur.metier.getJoueurCourant();
			int pieceAvant = joueur.getPieces();

			ihm.afficherDebutTour(joueur);
			ihm.afficherPlateau();

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

					ihm.afficherValeurDes(de1 + de2);
					ihm.afficherMenuRejouer();

					if (ihm.choixMenu() == 1) {
						nombreDeCoups++;

						de1 = 0;
						de2 = 0;
					}
				}
			}

			// On lance les effets de toutes les cartes
			List<Carte> cartesLancees = metier.lancerEffets(de1 + de2);

			rejouer = (de1 == de2 && !((Monument) (joueur.rechercherCarte("M3"))).estEnConstruction());


			// Affichage du bilan du tour
			ihm.afficherBilanTour(joueur, pieceAvant, nombreDeDes, de1 + de2, cartesLancees);


			ihm.afficherMenuAchat(joueur);
			int choix = ihm.choixMenu();

			if (choix == 1 || choix == 2) {
				ArrayList<Carte> cartes = (choix == 1) ? metier.getPioche() : joueur.getMonuments();

				ihm.nettoyerAffichage();
				ihm.afficherLigneCarte(cartes);

				Carte carte;
				String choixIden;
				boolean achatTermine = false;

				do {
					choixIden = ihm.choixIdentifiantCarte();
					carte = (choix == 1) ? metier.rechercherCartePioche(choixIden) : joueur.rechercherCarte(choixIden);

					if (choixIden.equals("-1"))
						achatTermine = true;
					else {
						if (choix == 1 && carte != null && metier.acheter(choixIden, metier.getJoueurCourant()))
							achatTermine = true;

						if (choix == 2 && carte instanceof Monument) {
							Monument monument = (Monument) carte;

							if (metier.construireMonument(monument, joueur))
								achatTermine = true;
						}

					}
				}
				while (!achatTermine);
			}

			if (!rejouer) Controleur.metier.changerJoueurCourant();
		}
		while (this.getGagnant() == null);

		Controleur.ihm.afficherGagnant(this.getGagnant());
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

	private int lancerDe() {
		return 1 + (int) (Math.random() * 6);
	}


	public static IHM getIhm() { return ihm; }


	public static void main(String[] a) {
		String mode = "console";

		if (a.length > 0 && a[0].equals("gui")) mode = a[0];

		new Controleur(mode).lancer();
	}
}