package minivilles.ihm;
import minivilles.controleur.Controleur;
import minivilles.metier.Joueur;
import minivilles.metier.Carte;

/**
 * Created by richard on 6/19/17.
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class IHM
{
	private Controleur ctrl;

	public IHM ( Controleur ctrl )
	{
		this.ctrl = ctrl;
	}

	public int menu()
	{
		Scanner sc = new Scanner(System.in);
		boolean quitter = false;
		String choix;
		while(!quitter)
		{
			System.out.println("1.\tLister joueur");
			System.out.println("2.\tLister carte");
			System.out.println("3.\tJouer");
			System.out.println("4.\tQuitter");

			choix = sc.nextLine();

			switch (choix)
			{
				case "1":
					this.listerJoueur();
					break;
				case "2":
					this.listerCarte();
					break;
				case "3":
					break;
				default:
					System.out.println("Choix invalide");
					break;
			}

		}
		return 0;
	}

	public void listerJoueur()
	{
		System.out.println ( "\nListe joueur\n" );
		for(Joueur j : this.ctrl.getlJoueur()) System.out.print(j);
		System.out.print( "\n" );
	}

	public void listerCarte()
	{
		System.out.println ( "\nListe carte\n" );
		for(Carte c : this.ctrl.getlCarte()) System.out.print(c);
		System.out.print( "\n" );
	}

}
