package minivilles.net;

import minivilles.metier.Metier;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * ClientThread.java
 * @see minivilles.net.Client
 * @author Richard Blondel
 * @author Valentin Dulong
 * @author Maxime Malgorn
 * @author Louis Boursier
 * @version 1.0
 */

public class ClientThread extends Thread {

	private Socket socket;
	private Client client;

	private ObjectOutputStream out;
	private ObjectInputStream in;


	ClientThread(Client myClient, Socket socket) {
		this.socket = socket;
		this.client = myClient;

		this.launch();
		this.start();
	}


	public void run() {
		// On attends que le serveur nous envoie le métier enregistré
		while (this.socket != null) {
			try {
				Object inObj = this.in.readUnshared();

				// Le serveur envoie le numéro de joueur du client
				if (inObj instanceof Integer) {
					this.client.getControleur().setMonNumJoueur((Integer) inObj);
					continue;
				}

				// Le serveur envoie le métier
				if (inObj instanceof Metier) {
					Metier metier = (Metier) inObj;

					this.client.nouvelleMajServeur = true;
					this.client.getControleur().majDepuisServeur(metier);
				}

			} catch (IOException ioe) {
				System.out.println("\nConnexion au serveur perdue !\n");

				this.socket = null;
				this.interrupt();

				System.exit(0);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private void launch() {
		try {
			this.out = new ObjectOutputStream(this.socket.getOutputStream());
			this.in = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void envoiMetier(Metier metier) {
		try {
			this.out.writeUnshared(metier);
			this.out.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
