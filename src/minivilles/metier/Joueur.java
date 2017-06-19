package minivilles.metier;

import minivilles.metier.Carte;

import java.util.*;

public class Joueur {
	private static int autoInc = 0;
	private int num;
	private int piece;
	private ArrayList<Carte> arCarte = new ArrayList<Carte>();
	
	public Joueur() {
		this.num   = autoInc++;
		this.piece = 3;
	}
	
	public int getNum()                    { return this.num;     }
	public int getPiece()                  { return this.piece;   }
	public ArrayList<Carte>  getArCartes() { return this.arCarte; }
	public void addPiece(int piece)        { this.piece += piece; }
	public void addCarte(Carte c)          { this.arCarte.add(c); }
	
	public void retirerPiece(int nb) { 
		if (this.piece < nb) this.piece -= nb; 
		else this.piece = 0;
	}
	
}
