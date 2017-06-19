package minivilles.metier;

/**
 * Created by richard on 6/19/17.
 */
public class Carte {

	private String identifiant;
	private String nom;

	private String texteEffet;
	private String couleur;
	private String couleurEffet;

	private int declencheur;
	private int declencheur2;
	private int cout;

	public Carte(String identifiant, String nom, String couleur, int declencheur) {
		this(identifiant, nom, couleur, declencheur, -1, 0);
	}

	public Carte(String identifiant, String nom, String couleur, int declencheur, int cout) {
		this(identifiant, nom, couleur, declencheur, -1, cout);
	}

	public Carte(String identifiant, String nom, String couleur, int declencheur, int declencheur2, int cout) {
		this.identifiant = identifiant;

		this.nom = nom;
		initTexteEffet();

		this.couleur = couleur.toUpperCase();
		initCouleurEffet();

		this.declencheur = declencheur;
		this.declencheur2 = declencheur2;
		this.cout = cout;
	}

	public String getIdentifiant() {
		return this.identifiant;
	}

	public String getNom() {
		return this.nom;
	}

	public String getTexteEffet() {
		return this.texteEffet;
	}

	public String getCouleur() {
		return this.couleur;
	}

	public int getDeclencheur2() {
		return this.declencheur2;
	}

	public String getCouleurEffet() {
		return this.couleurEffet;
	}

	public int getDeclencheur() {
		return this.declencheur;
	}

	public int getCout() {
		return this.cout;
	}

	public void initCouleurEffet() {
		switch (this.couleur) {
			case "ROUGE":
				if (this.nom.equals("Café"))
					this.couleurEffet = this.texteEffet = "Recevez 1 pièce du joueur qui a lancé les dés.";
				else this.couleurEffet = this.texteEffet = "Recevez 2 pièces du joueur qui a lancé les dés.";
				break;
			case "VERT":
				this.couleurEffet = "Pendant votre tour uniquement.";
				break;
			case "BLEU":
				this.couleurEffet = "Pendant le tour de nimporte quel joueur.";
				break;
			case "VIOLET":
				this.couleurEffet = "Pendant votre tour uniquement.";
				break;
		}
	}

	public void initTexteEffet() {
		if (this.nom.equals("Champs de blé") || this.nom.equals("Ferme") || this.nom.equals("Forêt") ||
				this.nom.equals("Boulangerie"))
			this.texteEffet = "Recevez 1 pièce de la banque";

		if (this.nom.equals("Café"))
			this.texteEffet = "Recevez 1 pièce du joueur qui a lancé les dés.";

		if (this.nom.equals("Supérette") || this.nom.equals("Verger"))
			this.texteEffet = "Recevez 3 pièces de la banque.";

		if (this.nom.equals("Stade"))
			this.texteEffet = "Recevez 2 pièces de la part de chaque autre joueur.";

		if (this.nom.equals("Chaîne de télévision"))
			this.texteEffet = "Recevez 5 pièces du joueur de votre choix.";

		if (this.nom.equals("Centre d'affaires"))
			this.texteEffet = "Vous pouvez échanger avec le joueur de votre choix un établissement qui ne soit pas du type (violet).";

		if (this.nom.equals("Fromagerie"))
			this.texteEffet = "Recevez 3 pièces de la banque pour chaque établissement de type ferme que vous possédez.";

		if (this.nom.equals("Fabrique de meubles"))
			this.texteEffet = "Recevez 3 pièces de la banque pour chaque établissement de type (forêt ou mine) que vous possédez.";

		if (this.nom.equals("Mine"))
			this.texteEffet = "Recevez 5 pièces de la banque.";

		if (this.nom.equals("Marché de fruits et légumes"))
			this.texteEffet = "Recevez 2 pièces de la banque pour chaque établissement de type (verger/champs de blé) que vous possédez.";
	}

	@Override
	public String toString() {
		String retS = "";
		int largeur = 30;
		if (this.couleurEffet.length() < this.texteEffet.length()) {
			largeur = this.texteEffet.length() + 5;
		} else {
			largeur = this.couleurEffet.length() + 5;
		}

		retS += String.format("%0" + largeur + "d", 0).replace("0", "=");
		retS += "\n";
		retS += "|" + String.format("%" + (largeur - 2) + "s", this.declencheur) + "|";
		retS += "\n";
		retS += "|" + String.format("%" + -(largeur - 2) + "s", this.nom) + "|";
		retS += "\n";

		for (int i = 0; i < 3; i++) {
			retS += "|" + String.format("%0" + (largeur - 2) + "d", 0).replace("0", " ") + "|";
			retS += "\n";
		}
		retS += "|" + String.format("%" + (largeur - 2) + "s", this.couleurEffet) + "|";

		retS += "\n";

		retS += "|" + String.format("%" + (largeur - 2) + "s", this.texteEffet) + "|";

		retS += "\n";

		retS += "|" + String.format("%" + -(largeur - 2) + "s", "Coût de construction : " + this.cout) + "|";

		retS += "\n";

		retS += String.format("%0" + largeur + "d", 0).replace("0", "=");

		return retS;
	}
}
