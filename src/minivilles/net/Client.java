package minivilles.net;

import minivilles.Controleur;
import minivilles.metier.Metier;

import java.io.IOException;
import java.net.Socket;

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

	public boolean isNouvelleMajServeur() {
		boolean maj = this.nouvelleMajServeur;

		this.nouvelleMajServeur = false;
		return maj;
	}

	public void envoiMetier(Metier metier) {
		this.thread.envoiMetier(metier);
	}


	public static Client nouveauClient(Controleur ctrl, String ip) {
		try {
			return new Client(ctrl, ip);
		} catch (IOException ignored) {
			return null;
		}
	}

}
