package minivilles.controleur;
import minivilles.ihm.IHM;
import minivilles.metier.*;

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

	public String afficherPlateau()
	{
		return this.metier.toString();
	}

	public static void main(String[] a)
	{
		new Controleur().lancer();
	}
}