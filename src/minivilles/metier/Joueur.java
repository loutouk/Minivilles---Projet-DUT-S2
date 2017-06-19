package minivilles.metier;

import java.util.*;
import minivilles.metier.*;

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

	public void setPiece(int nb) { this.piece = nb; }
	public void retirerPiece(int nb) { 
		if (this.piece < nb) this.piece -= nb; 
		else this.piece = 0;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("Joueur (pieces=" + this.piece + ")\n");

		for (Carte carte : this.arCarte)
			str.append(carte.toString()).append("\n");

		return str.toString();
	}
}
