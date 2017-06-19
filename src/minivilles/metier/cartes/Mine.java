package minivilles.metier.cartes;

public class Mine extends Carte {

	public Mine (String identifiant, String nom, String couleur, int declencheur, int cout) {
		super("9", "Mine", "Bleu",9,6);
	}
	
	public void lancerEffet(Metier m) {
		
	}
}
