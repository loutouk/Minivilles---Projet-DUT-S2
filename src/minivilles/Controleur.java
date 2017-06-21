package minivilles;

import minivilles.ihm.IHM;
import minivilles.metier.Joueur;
import minivilles.metier.Metier;
import minivilles.metier.cartes.Carte;
import minivilles.metier.cartes.monuments.Monument;

import java.util.ArrayList;
import java.util.List;

public class Controleur {

	private IHM ihm;
	private Metier metier;

	public Controleur() {
		this.metier = new Metier();
		this.ihm = new IHM(this);
	}


	/**
	 * Accesseur pour récupérer le métier depuis la partie Test du programme
	 *
	 * @return Le metier
	 */
	public Metier getMetier() {
		return this.metier;
	}

	public IHM getIhm() {
		return ihm;
	}

	public void lancer() {
		boolean quitter = false;
		int choix;

		while (!quitter) {
			this.ihm.afficherMenuPrincipal();

			choix = this.ihm.choixMenu();

			switch (choix) {
				case 1:
					this.initialiserPartie();
					this.lancerPartie();
					break;

				case 2:
					quitter = true;
					break;

				default:
					System.out.println("Choix invalide");
					break;
			}
		}
	}

	private void initialiserPartie() {
		int nbJoueurs = this.ihm.choixNbJoueurs();

		if (nbJoueurs >= 2 && nbJoueurs <= 4)
			this.initialiserPlateau(nbJoueurs);
		else {
			System.out.println("   --> Veuillez entrez un nombre valide.\n");
			this.initialiserPartie();
		}
	}

	private void lancerPartie() {
		do {
			// Effet du ParcDattractions : on peut rejouer un tour si le jet de dés est un double
			boolean rejouer;

			Joueur joueur = this.metier.getJoueurCourant();
			int pieceAvant = joueur.getPieces();

			this.ihm.afficherDebutTour(joueur);
			this.ihm.afficherPlateau();

			// Effet du monument Gare : deux jet de dés
			int nombreDeCoups = 1;
			int nombreDeDes = ((Monument) (joueur.rechercherCarte("M1"))).estEnConstruction() ? 1 : 2;
			int de1 = 0;
			int de2 = 0;

			for (int compteur = 0; compteur < nombreDeCoups * nombreDeDes; compteur++) {
				int de = this.lancerDe();

				if (de1 == 0) de1 = de;
				else de2 = de;

				// Effet du monument Tour : on peut choisir de relancer les dés
				if (!((Monument) (joueur.rechercherCarte("M4"))).estEnConstruction() &&
						nombreDeCoups == 1 &&
						(compteur - 1) % nombreDeDes == 0) {

					this.ihm.afficherValeurDes(de1 + de2);
					this.ihm.afficherMenuRejouer();

					if (ihm.choixMenu() == 1) {
						nombreDeCoups++;

						de1 = 0;
						de2 = 0;
					}
				}
			}

			// On lance les effets de toutes les cartes
			List<Carte> cartesLancees = this.metier.lancerEffets(de1 + de2);

			rejouer = (de1 == de2 && !((Monument) (joueur.rechercherCarte("M3"))).estEnConstruction());


			// Affichage du bilan du tour
			this.ihm.afficherBilanTour(pieceAvant, joueur.getPieces(), de1 + de2, cartesLancees);


			this.ihm.afficherMenuAchat(joueur);
			int choix = this.ihm.choixMenu();

			if (choix == 1 || choix == 2) {
				ArrayList<Carte> cartes = (choix == 1) ? this.metier.getPioche() : joueur.getMonuments();

				this.ihm.clearConsole();
				this.ihm.afficherLigneCarte(cartes);

				Carte carte;
				String choixIden;
				boolean achatTermine = false;

				do {
					choixIden = this.ihm.choixIdentifiantCarte();
					carte = (choix == 1) ? this.metier.rechercherCartePioche(choixIden) : joueur.rechercherCarte(choixIden);

					if (choixIden.equals("-1"))
						achatTermine = true;
					else {
						if (choix == 1 && carte != null && this.metier.acheter(choixIden, this.metier.getJoueurCourant()))
							achatTermine = true;

						if (choix == 2 && carte instanceof Monument) {
							Monument monument = (Monument) carte;

							if (this.metier.construireMonument(monument, joueur))
								achatTermine = true;
						}

					}
				}
				while (!achatTermine);
			}

			if (!rejouer) this.metier.changerJoueurCourant();
		}
		while (this.getGagnant() == null);

		this.ihm.afficherGagnant(this.getGagnant());
	}

	public Joueur getGagnant() {
		for (Joueur joueur : this.metier.getJoueurs()) {
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

	public void initialiserPlateau(int nbJoueurs) {
		this.metier.initialiserPlateau(nbJoueurs);
	}


	public static void main(String[] a) {
		new Controleur().lancer();
	}
}