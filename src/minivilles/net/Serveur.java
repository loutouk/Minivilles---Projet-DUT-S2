package minivilles.ihm.gui;

import minivilles.ihm.IHM;
import minivilles.metier.Joueur;
import minivilles.metier.cartes.Carte;
import minivilles.metier.cartes.monuments.Monument;
import java.net.*;
import java.io.*;
import minivilles.Controleur;

public class Serveur extends Thread
{
	private ServThread[] tabClient = new ServThread[4];
	private int nbClientsConnected = 0;
	private OutputStream streamOut;
	private Socket socket;
	private InputStream streamIn;
	private PrintWriter out;

	public void run()
	{
		try
		{
			ServerSocket ss = new ServerSocket(55555);
			while(true)
			{
				this.socket = ss.accept();
				out = new PrintWriter(this.socket.getOutputStream());
				this.tabClient[nbClientsConnected] = new ServThread(this, socket);
				out.println("Vous étes bien connecté au serveur de jeu");
				out.flush();
				System.out.println("connecté");
				this.tabClient[nbClientsConnected].start();
				nbClientsConnected++;
			}
		}
		catch(IOException ioe)
        {
            System.out.println("Server accept error: " + ioe); stop();
        }
        
	}

	public void handle(Controleur ctrl, ServThread ID)
	{
		for(int i = 0; i<this.nbClientsConnected; i++)
		{
			if(this.tabClient[i] != ID) this.tabClient[i].send(ctrl);
		}
	}


	public static void main(String[] a)
	{
		new Serveur().start();
	}
}
