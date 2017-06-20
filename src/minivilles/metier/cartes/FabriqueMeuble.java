package minivilles.metier.cartes;

import minivilles.metier.Metier;

public class FabriqueMeuble extends Carte {

	public FabriqueMeuble() {
		super("8", "Fabrique de meubles", "bleu", 8, 3);
	}

	@Override
	public void lancerEffet(Metier metier) {
		int gain = 3;
		for (Carte c : arCartes) {
			if(c.getNom().equals("Forêt") || c.getNom().equals("Mine")) cpt++;
		}
		metier.getJoueurCourant().addPiece(cpt*gain);
		metier.getBanque().retrait(cpt*gain);
	}
}
