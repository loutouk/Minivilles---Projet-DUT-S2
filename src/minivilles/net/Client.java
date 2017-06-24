package minivilles.net;

import minivilles.Controleur;
import minivilles.metier.Metier;

import java.io.IOException;
import java.net.Socket;

/**
 * Client.java
 * Chaque joueur possède son instance de Client pour communiquer avec le serveur
 * @see Serveur.java
 * @author Richard Blondel
 * @author Valentin Dulong
 * @author Maxime Malgorn
 * @author Louis Boursier
 * @version 1.0
 */

public class Client {

	private Socket socket;
	private ClientThread thread;

	private Controleur ctrl;

	boolean nouvelleMajServeur;


	private Client(Controleur ctrl, String ip) throws IOException {
		this.ctrl = ctrl;
		this.socket = new Socket(ip, 55555);
		this.thread = new ClientThread(this, socket);
	}


	public Controleur getControleur() {
		return ctrl;
	}

	public void resetMajsServeur() {
		this.nouvelleMajServeur = false;
	}

	public boolean isNouvelleMajServeur() {
		boolean maj = this.nouvelleMajServeur;

		this.nouvelleMajServeur = false;
		return maj;
	}

	public void envoiMetier(Metier metier) {
		this.thread.envoiMetier(metier);
	}


	public void fermerConnexion() {
		try {
			this.socket.close();
		} catch (IOException ignored) {
		}
	}

	public static Client nouveauClient(Controleur ctrl, String ip) {
		try {
			return new Client(ctrl, ip);
		} catch (IOException ignored) {
			return null;
		}
	}

}
