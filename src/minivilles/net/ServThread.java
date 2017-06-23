package minivilles.net;

import minivilles.Controleur;

import java.io.*;
import java.net.Socket;


public class ServThread extends Thread {
	private Socket socket;
	private Serveur myServ;

	private InputStream streamIn;
	private InputStreamReader streamInRead;
	private BufferedReader buffRead;

	private OutputStream streamOut;
	private PrintWriter printWrite;

	private String username = null;


	public ServThread(Serveur myServ, Socket socket) {
		this.socket = socket;
		this.myServ = myServ;
		this.launch();
	}


	public void run() {
		printWrite.println("Tu es bien connecté [" + this.socket.getRemoteSocketAddress() + "]");

		while (socket != null) {

			try {

				ObjectInputStream ois;
				ois = new ObjectInputStream(socket.getInputStream());
				Controleur ctrl = (Controleur) ois.readObject();
				if (ctrl != null) myServ.handle(ctrl, this);
				System.out.println(ctrl);

			} catch (IOException ioe) {
				System.out.println("Server accept error: " + ioe);
			} catch (ClassNotFoundException ce) {
				System.out.println("Class not found : " + ce);
			}
		}

	}

	public void command(String msg) {
		String[] command = msg.split(" ", 2);

		switch (command[0]) {
			case "!username":
				this.username = command[1];
		}
	}

	public void send(Controleur ctrl) {

		try {
			ObjectOutputStream oos;
			oos = new ObjectOutputStream(streamOut);
			oos.writeObject(ctrl);
		} catch (IOException ioe) {
			System.out.println("Server accept error: " + ioe);
			stop();
		}
	}

	public void launch() {
		try {
			streamIn = this.socket.getInputStream();
			streamInRead = new InputStreamReader(streamIn);
			buffRead = new BufferedReader(streamInRead);

			streamOut = this.socket.getOutputStream();
			printWrite = new PrintWriter(streamOut, true);
		} catch (IOException ioe) {
			System.out.println("Server accept error: " + ioe);
			stop();
		}

	}
}
