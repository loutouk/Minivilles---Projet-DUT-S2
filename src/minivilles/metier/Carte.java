package minivilles.metier;

/**
 * Created by richard on 6/19/17.
 */
public class Carte {
	private String nom;
	private String texteEffet;
	private String couleur;
	private String couleurEffet;
	private String identifiant;

	private int declencheur;
	private int declencheur2;
	private int cout;

	public Carte(String nom, String couleur, int declencheur, int cout, String identifiant) {
		this(nom,couleur,declencheur,-1,cout, identifiant);
	}
	
	public Carte(String nom, String couleur, int declencheur,int declencheur2, int cout, String identifiant) {
		this.nom = nom;
		initTexteEffet();
		this.couleur = couleur.toUpperCase();
		initCouleurEffet();
		this.declencheur = declencheur;
		this.declencheur2= declencheur2;
		this.cout = cout;
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

	public String getIdentifiant() {
		return identifiant;
	}

	public void initCouleurEffet() {
		switch(this.couleur) {
			case "ROUGE"  :
				if(this.nom.equals("Cafe")) this.couleurEffet = this.texteEffet = "Recevez 1 piece du joueur qui a lance les des.";
				else                        this.couleurEffet = this.texteEffet = "Recevez 2 pieces du joueur qui a lance les des.";
				break;
			case "VERT"   : this.couleurEffet = "Pendant votre tour uniquement.";break;
			case "BLEU"   : this.couleurEffet = "Pendant le tour de nimporte quel joueur.";break;
			case "VIOLET" : this.couleurEffet = "Pendant votre tour uniquement.";break;
		}
	}
	
	public void initTexteEffet() {
		if(this.nom.equals("Champs de ble") || this.nom.equals("Ferme") || this.nom.equals("Foret") ||
		   this.nom.equals("Boulangerie")) 
		   this.texteEffet = "Recevez 1 piece de la banque";
		
		if(this.nom.equals("Cafe")) 
			this.texteEffet = "Recevez 1 piece du joueur qui a lance les des.";
		
		if(this.nom.equals("Superette") || this.nom.equals("Verger"))
			this.texteEffet = "Recevez 3 pieces de la banque.";
		
		if(this.nom.equals("Stade"))
			this.texteEffet = "Recevez 2 pieces de la part de chaque autre joueur.";
		
		if(this.nom.equals("Chaîne de television"))
			this.texteEffet = "Recevez 5 pieces du joueur de votre choix.";
			
		if(this.nom.equals("Centre d'affaires"))
			this.texteEffet = "Vous pouvez echanger avec le joueur de votre choix un etablissement qui ne soit pas du type (violet).";
		
		if(this.nom.equals("Fromagerie"))
			this.texteEffet = "Recevez 3 pieces de la banque pour chaque etablissement de type ferme que vous possedez.";
			
		if(this.nom.equals("Fabrique de meubles"))
			this.texteEffet = "Recevez 3 pieces de la banque pour chaque etablissement de type (foret ou mine) que vous possedez.";
			
		if(this.nom.equals("Mine"))
			this.texteEffet = "Recevez 5 pieces de la banque.";
			
		if(this.nom.equals("Marche de fruits et legumes"))
			this.texteEffet = "Recevez 2 pieces de la banque pour chaque etablissement de type (verger/champs de ble) que vous possedez.";
	}

	@Override
	public String toString() {
	String retS = "";
		int largeur = 30;
		if(this.couleurEffet.length() < this.texteEffet.length()) { largeur = this.texteEffet.length() +5; }
		else { largeur = this.couleurEffet.length() +5;}

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
		retS += "|" + String.format("%" + (largeur-2) + "s", this.couleurEffet) + "|";

		retS += "\n";

		retS += "|" + String.format("%" + (largeur-2) + "s", this.texteEffet) + "|";

		retS += "\n";

		retS += "|" + String.format("%" + -(largeur-2) + "s", "Coût de construction : " + this.cout) + "|";

		retS += "\n";

		retS += String.format("%0" + largeur + "d", 0).replace("0", "=");

		return retS;
	}
	
	public static void main(String[] a) {
		Carte c = new Carte("Ferme","bleu",2,1,"1");
		System.out.println(c);
		Carte c1 = new Carte("Ferme","bleu",2,1,"2");
		System.out.println(c1);
		Carte c2 = new Carte("Marche de fruits et legumes","vert",2,1,"3");
		System.out.println(c2);
		
	}

}
