package minivilles.ihm.gui;

import minivilles.ihm.IHM;
import minivilles.Controleur;
import minivilles.metier.Joueur;
import minivilles.metier.cartes.Carte;
import minivilles.metier.cartes.monuments.Monument;
import java.net.*;
import java.io.*;
import java.util.*;

public class Client
{
	private Socket socket;
	private ClientThread thread;
	private InputStream streamIn;
	private InputStreamReader streamInRead;
	private BufferedReader buffRead;

	private OutputStream streamOut;
	private PrintWriter printWrite;

	private Scanner sc = new Scanner(System.in);

	public Client(String ip)
	{
		try
		{
			socket = new Socket(ip,55555);
			buffRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			streamOut = this.socket.getOutputStream();
			printWrite = new PrintWriter(streamOut, true);
			Controleur ctrl = new Controleur("gui");
			System.out.print("Your username : ");
			String username = sc.nextLine();
			while(username.equals(""))
			{
				System.out.print("Your username : ");
				username = sc.nextLine();
			}
			printWrite.println("!username "+username);


			thread = new ClientThread(this, socket);
			this.run();
		}
		catch(IOException ioe)
        {
            System.out.println("Server accept error: " + ioe);
        }

	}

	public void run()
	{
		while(true)
		{
			String input = sc.nextLine();
			if(!input.equals("")) printWrite.println(input);
		}
	}

	public void handle(String msg)
	{
		System.out.println(msg);
	}

	public void stop()
	{
		System.out.println("Vous avez été déconnecté");
		System.exit(0);
	}

	public static void main(String[] a)
	{
		new Client(a[0]);
	}
}
