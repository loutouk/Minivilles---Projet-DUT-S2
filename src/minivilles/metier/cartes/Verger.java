package minivilles.metier.cartes;

public class Verger extends Carte {

	public Verger (String identifiant, String nom, String couleur, int declencheur, int cout) {
		super("10", "Verger", "Violet", 10, 3);
	}
	
	public void lancerEffet(Metier m) {
		
	}
}
