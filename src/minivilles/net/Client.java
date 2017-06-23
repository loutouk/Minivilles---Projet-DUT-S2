package minivilles.ihm.gui;

import minivilles.ihm.IHM;
import minivilles.Controleur;
import minivilles.metier.Metier;
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
	private ObjectOutputStream oos;
	private Controleur ctrl;

	private OutputStream streamOut;
	private PrintWriter printWrite;

	private Scanner sc = new Scanner(System.in);

	public Client(String ip)
	{
		try
		{
			socket = new Socket(ip,55555);
			
			buffRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println(buffRead.readLine());
			streamOut = this.socket.getOutputStream();
			printWrite = new PrintWriter(streamOut, true);
			Controleur ctrl = new Controleur("gui");


			thread = new ClientThread(this, socket);
			this.run();
		}
		catch(IOException ioe)
        {
            System.out.println("Server accept error: " + ioe);
        }
       // catch(ClassNotFoundException ce) { System.out.println("Class not found : " + ce);}

	}

	public void run()
	{
		while(true)
		{
			try
			{
				// 1 - On lit le controleur venant du serveur
				// Si il est null, c'est que 'lon est le premier joueur
				ObjectOutputStream oos;
				oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(ctrl);
				
				/*ObjectInputStream ois;
				ois = new ObjectInputStream(socket.getInputStream());
				ctrl = (Controleur) ois.readObject();*/
			}
			//catch(ClassNotFoundException ce) { System.out.println("Class not found : " + ce);}
			catch(IOException ioe)
        {
            System.out.println("Server accept error: " + ioe);
        }
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
