package minivilles.net;

import minivilles.metier.Metier;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ServeurThread extends Thread {

	private Socket socket;
	private Serveur server;

	private ObjectOutputStream out;
	private ObjectInputStream in;


	ServeurThread(Serveur server, Socket socket) {
		this.socket = socket;
		this.server = server;

		this.launch();
	}


	public void run() {
		// Le client vient de se connecter, on lui envoie le métier du serveur.
		// (et son numéro de joueur de la partie)
		if (this.server.getMetier() != null)
			this.send(this.server.getMetier());

		this.send(this.server.getNbClientsConnected());

		// On attends qu'il envoie son propre métier
		while (this.socket != null) {
			try {
				Metier metier = (Metier) this.in.readUnshared();

				if (metier != null) {
					this.server.setMetier(metier);
					server.handle(metier, this);
				}
			} catch (IOException ioe) {
				System.out.println("Server read error: " + ioe);

				this.socket = null;
				this.interrupt();
			} catch (ClassNotFoundException ce) {
				System.out.println("Class not found : " + ce);
			}
		}

	}

	void send(Object obj) {
		try {
			this.out.writeUnshared(obj);
			this.out.reset();
		} catch (IOException ioe) {
			System.out.println("Server write error: " + ioe);
			stop();
		}
	}

	private void launch() {
		try {
			this.out = new ObjectOutputStream(this.socket.getOutputStream());
			this.in = new ObjectInputStream(this.socket.getInputStream());
		} catch (IOException ioe) {
			System.out.println("Server launch error: " + ioe);
			this.interrupt();
		}

	}
}
