package minivilles.metier.cartes;

import minivilles.metier.Metier;
import java.util.ArrayList;
public class Fromagerie extends Carte {

	public Fromagerie() {
		super(
				"7", "Fromagerie",
				"Vert", 7, 5
		);
	}

	@Override
	public void lancerEffet(Metier metier) {
		ArrayList<Carte> arCartes = metier.getJoueurCourant().getMain();
		int cpt = 0;
		int gain = 3;
		for (Carte c : arCartes) {
			if(c.getNom().equals("Ferme")) cpt++;
		}
		metier.getJoueurCourant().addPiece(cpt*gain);
		metier.getBanque().retrait(cpt*gain);
	}
}
