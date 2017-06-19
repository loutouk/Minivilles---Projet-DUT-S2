package minivilles.metier.cartes;

public class Ferme extends Carte {

	public Ferme (String identifiant, String nom, String couleur, int declencheur, int cout) {
		super("2", "Ferme", "Bleu", 2, 1);
	}
	
	public void lancerEffet(Metier m) {
		
	}
}
