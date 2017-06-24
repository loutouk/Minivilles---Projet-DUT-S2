package minivilles.net;

import minivilles.metier.Metier;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Serveur.java
 * Une seule instance gérer par le Client qui a configuré la partie. Architecture client - serveur classique
 * @author Richard Blondel
 * @author Valentin Dulong
 * @author Maxime Malgorn
 * @author Louis Boursier
 * @version 1.0
 */

public class Serveur extends Thread {

	private ServerSocket ss;
	private ServeurThread[] tabClient = new ServeurThread[4];
	private int nbClientsConnected = 0;

	private Socket socket;
	private Metier metier;


	public Serveur(Metier metier) {
		this.metier = metier;
	}


	public void run() {
		try {
			this.ss = new ServerSocket(55555);

			while (true) {
				this.socket = this.ss.accept();

				this.tabClient[nbClientsConnected] = new ServeurThread(this, this.socket);
				this.tabClient[nbClientsConnected].start();

				nbClientsConnected++;
			}

		} catch (IOException ioe) {
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

	public boolean estLance() {
		return this.ss != null && !this.ss.isClosed() && this.ss.isBound();
	}


	public void fermer() {
		try {
			for (int i = 0; i < this.nbClientsConnected; i++)
				this.tabClient[i].fermer();

			this.ss.close();
		} catch (IOException ignored) {
		}
	}

	public void handle(Metier metier, ServeurThread ID) {
		for (int i = 0; i < this.nbClientsConnected; i++)
			if (this.tabClient[i] != ID)
				this.tabClient[i].send(metier);
	}
}
