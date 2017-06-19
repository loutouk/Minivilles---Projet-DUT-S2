package minivilles.metier.cartes;

/**
 * Created by richard on 6/19/17.
 */
public abstract class Monument extends Carte {
	boolean estVisible = false;

	public Monument(String identifiant, String nom, String couleur, int declencheur, int cout) {
		super(identifiant, nom, couleur, declencheur, cout);
	}

	public boolean estVisible() {
		return this.estVisible;
	}

	public abstract boolean lancerEffet();
}
