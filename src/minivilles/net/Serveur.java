package minivilles.net;

import minivilles.metier.Metier;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur extends Thread {

	private ServeurThread[] tabClient = new ServeurThread[4];
	private int nbClientsConnected = 0;

	private Socket socket;
	private Metier metier;


	public Serveur(Metier metier) {
		this.metier = metier;
	}


	public void run() {
		try {
			ServerSocket ss = new ServerSocket(55555);

			while (true) {
				this.socket = ss.accept();

				this.tabClient[nbClientsConnected] = new ServeurThread(this, this.socket);
				this.tabClient[nbClientsConnected].start();

				nbClientsConnected++;
			}

		} catch (IOException ioe) {
			System.out.println("Server accept error: " + ioe);
			this.interrupt();
		}

	}

	public Metier getMetier() {
		return metier;
	}

	public int getNbClientsConnected() {
		return this.nbClientsConnected;
	}

	public void setMetier(Metier metier) {
		this.metier = metier;
	}


	public void handle(Metier metier, ServeurThread ID) {
		for (int i = 0; i < this.nbClientsConnected; i++)
			if (this.tabClient[i] != ID)
				this.tabClient[i].send(metier);
	}
}
