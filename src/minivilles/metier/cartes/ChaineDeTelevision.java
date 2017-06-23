package minivilles.metier.cartes;

import minivilles.Controleur;
import minivilles.metier.Joueur;
import minivilles.metier.Metier;

public class ChaineDeTelevision extends Carte {

	public ChaineDeTelevision() {
		super(
				"6:2", "Chaîne de télévision",
				"Violet", 6, 7
		);
	}

	@Override
	public void lancerEffet(Metier metier) {
		int choixJoueur      = 0;
		int nbJoueur	     = metier.getListeJoueur().size();
		int don              = 5;
		Joueur joueurCourant = this.getJoueur();
		Joueur joueurCible;
		
		
		while(choixJoueur != -1 && (choixJoueur < 1 || choixJoueur > nbJoueur)) {
			choixJoueur = Integer.parseInt(
					Controleur.getIhm().choixJoueurChaineTV(metier.getListeJoueur(), metier.getJoueurCourant())
			);
		}
		
		if (choixJoueur != -1) {
			joueurCible = metier.getListeJoueur().get(choixJoueur - 1);

			if(joueurCible.getPieces() >= don) {
				joueurCible.retirerPiece(don);
				joueurCourant.addPiece(don);
			}
			else {
				don = joueurCible.getPieces();
				joueurCible.retirerPiece(don);
				joueurCourant.addPiece(don);
			}
		}
	}
}
