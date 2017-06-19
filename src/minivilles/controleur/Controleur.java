package minivilles.controleur;

import java.util.List;

public class Controleur
{
	private IHM ihm;
	private Metier metier;

	public Controleur()
	{
		this.metier = new Metier();
		this.ihm = new IHM(this);
	}

	public void lancer()
	{
		this.ihm.menu();
	}

	public List<Carte> getlCarte()
	{
		return this.metier.getlCarte();
	}

	public List<Joueur> getlJoueur()
	{
		return this.metier.getlJoueur();x
	}

	public static void main(String[] a)
	{
		new Controleur().lancer();
	}
}