package minivilles.metier;

/**
 * Created by richard on 6/19/17.
 */
public class Carte {
	private String nom;
	private String texteEffet;

	private int declencheur;
	private int cout;

	public Carte(String nom, String texteEffet, int declencheur, int cout) {
		this.nom = nom;
		this.texteEffet = texteEffet;

		this.declencheur = declencheur;
		this.cout = cout;
	}

	public String getNom() {
		return this.nom;
	}

	public String getTexteEffet() {
		return this.texteEffet;
	}

	public int getDeclencheur() {
		return this.declencheur;
	}

	public int getCout() {
		return this.cout;
	}

	@Override
	public String toString() {
		String retS = "";

		int largeur = 20;

		retS += String.format("%0" + largeur + "d", 0).replace("0", "=");
		retS += "\n";
		retS += "|" + String.format("%" + 18 + "s", this.declencheur) + "|";
		retS += "\n";
		retS += "|" + String.format("%" + -18 + "s", this.nom) + "|";
		retS += "\n";

		for (int i = 0; i < 5; i++) {
			retS += "|" + String.format("%0" + 18 + "d", 0).replace("0", " ") + "|";
			retS += "\n";
		}

		if (this.texteEffet.length() >= 18) {

		} else {
			retS += "|" + String.format("%" + 18 + "s", this.texteEffet) + "|";
		}

		retS += "\n";

		retS += "|" + String.format("%" + 18 + "s", this.cout) + "|";

		retS += "\n";

		retS += String.format("%0" + largeur + "d", 0).replace("0", "=");

		return retS;
	}

	public static void main(String[] argv) {
		System.out.println(new Carte("Fromagerie", "Donne du fromage", 4, 1).toString());
	}
}