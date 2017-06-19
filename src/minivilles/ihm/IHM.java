package minivilles.ihm;
import minivilles.controleur.*;
import minivilles.metier.*;

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
			System.out.println("1.\tAfficher plateau");
			System.out.println("2.\tQuitter");

			choix = sc.nextLine();

			switch (choix)
			{
				case "1":
					this.afficherPlateau();
					break;
				case "2":
					quitter = true;
					break;
				default:
					System.out.println("Choix invalide");
					break;
			}

		}
		return 0;
	}

	public void afficherPlateau()
	{
		System.out.println(this.ctrl.afficherPlateau());
	}

}
