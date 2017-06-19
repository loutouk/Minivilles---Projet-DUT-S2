package minivilles.metier;

/**
 * Created by richard on 6/19/17.
 */
public class Carte {
	private String nom;
	private String texteEffet;
	private String couleur;

	private int declencheur;
	private int cout;

	public Carte(String nom, String texteEffet, String couleur, int declencheur, int cout) {
		this.nom = nom;
		this.texteEffet = texteEffet;
		this.couleur = couleur;

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

		int largeur = 30;

		retS += String.format("%0" + largeur + "d", 0).replace("0", "=");
		retS += "\n";
		retS += "|" + String.format("%" + (largeur-2) + "s", this.declencheur) + "|";
		retS += "\n";
		retS += "|" + String.format("%" + -(largeur-2) + "s", this.nom) + "|";
		retS += "\n";

		for (int i = 0; i < 3; i++) {
			retS += "|" + String.format("%0" + (largeur-2) + "d", 0).replace("0", " ") + "|";
			retS += "\n";
		}

		if (this.texteEffet.length() >= (largeur-2)) {
			int decoupages = this.texteEffet.length() / largeur-2;

			for (int i = 0; i < this.texteEffet.length() % largeur-2; i++) {
				String s = this.texteEffet.substring(i/decoupages, i+1/decoupages);

				retS += "|" + String.format("%" + -(largeur-2) + "s", s) + "|";
			}
		} else {
			retS += "|" + String.format("%" + (largeur-2) + "s", this.texteEffet) + "|";
		}

		retS += "\n";

		retS += "|" + String.format("%" + (largeur-2) + "s", "Coût de construction : " + this.cout) + "|";

		retS += "\n";

		retS += String.format("%0" + largeur + "d", 0).replace("0", "=");

		return retS;
	}

	public static void main(String[] argv) {
		System.out.println(new Carte("Fromagerie", "Donne du fromage qoifbsuyfsdftbsdufnsdfnisdfby_sdbf", 4, 1).toString());
	}
}