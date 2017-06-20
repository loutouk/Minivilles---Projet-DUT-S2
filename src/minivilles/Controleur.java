package minivilles;

/**
 * Created by Louis on 19/06/2017.
 */

import minivilles.ihm.IHM;
import minivilles.metier.Joueur;
import minivilles.metier.Metier;
import minivilles.metier.cartes.Carte;
import minivilles.metier.cartes.monuments.Monument;

import java.util.ArrayList;

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
		while (true) {

			// Effet du ParcDattractions : on peut rejouer un tour si le jet de dés est un double
			boolean rejouer = false;

			Joueur joueur = this.metier.getJoueurCourant();

			this.ihm.afficherPlateau();

			System.out.println("Joueur #" + this.metier.getJoueurCourant().getNum());


			// Effet du monument Gare : deux jet de dés
            int nombreDeCoups = 1;
			int nombreDeDes = ((Monument)(joueur.rechercherCarte("M1"))).estEnConstruction() ? 1 : 2;
            int de1 = 0;
            int de2 = 0;

			for(int compteur=0 ; compteur<nombreDeCoups*nombreDeDes ; compteur++){
				int de = this.lancerDe();

				if(de1 == 0){
					de1 = de;
				}else if(de2 == 0){
					de2 = de;
					if(!rejouer) rejouer = (de1 == de2 && ! ((Monument)(joueur.rechercherCarte("M3"))).estEnConstruction());
				}


				System.out.println("valeur du dé = " + de);
				// On lance les effets de toutes les cartes
				this.metier.lancerEffets(de, -2);

				// Effet du monument Tour : on peut choisir de relancer les dés
				if(!((Monument)(joueur.rechercherCarte("M4"))).estEnConstruction()
                        && nombreDeCoups==1
                        && (compteur-1)%nombreDeDes==0){
					this.ihm.afficherMenuRejouer();
					int resultat = ihm.choixMenu();
					if(resultat == 1){
					    nombreDeCoups++;
					    de1=0;
					    de2=0;
                    }
				}
			}


			this.ihm.afficherMenuAchat();
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

			if(!rejouer) this.metier.changerJoueurCourant();
		}
	}

	private int lancerDe() {
		return (int) (Math.random() * 5) + 1;
	}

	public void initialiserPlateau(int nbJoueurs) {
		this.metier.initialiserPlateau(nbJoueurs);
	}


	public static void main(String[] a) {
		new Controleur().lancer();
	}
}