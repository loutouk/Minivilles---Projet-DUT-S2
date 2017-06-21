package minivilles.metier.cartes;

import minivilles.metier.Metier;
import minivilles.metier.*;

public class ChaineDeTelevision extends Carte {

	public ChaineDeTelevision() {
		super(
				"6:2", "Chaîne de télévision",
				"Spécial", 6, 7
		);
	}

	@Override
	public void lancerEffet(Metier metier) {
		int choixJoueur      = 0;
		int nbJoueur	      = metier.getListeJoueur().size();
		int don              = 5;
		Joueur joueurCourant = this.getJoueur();
		Joueur joueurCible   = null;
		
		
		while(choixJoueur < 1 || choixJoueur > nbJoueur) {
			choixJoueur = Integer.parseInt(metier.getIhm().choixJoueurChaineTV());
		}
		
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
