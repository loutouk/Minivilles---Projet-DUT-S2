package minivilles.metier.cartes;

public class Cafe extends Carte {

	public Cafe (String identifiant, String nom, String couleur, int declencheur, int cout) {
		super("3", "Café", "Bleu", 3, 2);
	}
	
	public void lancerEffet(Metier m) {
		
	}
}
