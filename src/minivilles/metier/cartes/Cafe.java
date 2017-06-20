package minivilles.metier.cartes;

import minivilles.metier.Metier;

public class Cafe extends Carte {

	public Cafe () {
		super("3", "Café", "Bleu", 3, 2);
	}
	
	public void lancerEffet(Metier metier) {
		int don = 1;
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
