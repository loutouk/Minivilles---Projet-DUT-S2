package minivilles.metier.cartes;
import minivilles.metier.Metier;

public class CentreAffaires extends Carte {

	public CentreAffaires (String identifiant, String nom, String couleur, int declencheur, int cout) {
		super("6:3", "Centre d'affaires", "Violet", 6, 7);
	}

	@Override
	public void lancerEffet(Metier metier) {

	}


}
