package minivilles.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread {

	private Socket socket = null;
	private Client myClient = null;

	private InputStream streamIn;
	private InputStreamReader streamInRead;
	private BufferedReader buffRead;


	public ClientThread(Client myClient, Socket socket) {
		this.socket = socket;
		this.myClient = myClient;

		this.launch();
		this.start();
	}


	public void run() {

		while (true) {
			try {
				String msg = buffRead.readLine();
				if (msg == null) {
					myClient.stop();
				}
				myClient.handle(msg);
			} catch (IOException ioe) {
				System.out.println("Server accept error: " + ioe);
			}
		}
	}

	public void launch() {
		try {
			streamIn = this.socket.getInputStream();
			streamInRead = new InputStreamReader(streamIn);
			buffRead = new BufferedReader(streamInRead);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
