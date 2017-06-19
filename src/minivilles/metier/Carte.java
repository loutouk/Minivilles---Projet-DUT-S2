package minivilles.metier;

/**
 * Created by richard on 6/19/17.
 */

public class Carte {
	String nom;

	int declencheur;
	int cout;

	public Carte(String nom, int declencheur, int cout) {
		this.nom = nom;
		this.declencheur = declencheur;
		this.cout = cout;
	}
}
