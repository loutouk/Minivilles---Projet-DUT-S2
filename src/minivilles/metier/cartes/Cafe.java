package minivilles.metier.cartes;

import minivilles.metier.Joueur;
import minivilles.metier.Metier;
import minivilles.metier.cartes.monuments.Monument;

public class Cafe extends Carte {

	public Cafe () {
		super("3", "Café", "Bleu", 3, 2);
	}
	
	public void lancerEffet(Metier metier) {
		int don = 1;

		// Effet du monument CentreCommercial : 1 piece de plus
		Carte carte = super.getJoueur().rechercherCarte("M2");
		if(carte!=null) {
			Monument monument = (Monument) carte;
			if(!monument.estEnConstruction()) don++;
		}

		for(Joueur j : metier.getListeJoueur()) {
			for (Carte c : j.getMain()) {
				if(c.getNom().equals("Café")) {
					metier.getJoueurCourant().retirerPiece(don);
					j.addPiece(don);
				}
			}
		}
	}
}
