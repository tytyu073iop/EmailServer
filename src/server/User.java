package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.*;
import server.*;

public class User {
	public String user;
	Socket s;
	Server server;
	
	public User(String i, Socket s, Server ser) {
		user = i;
		this.s = s;
		server = ser;
		userMessageReciver.start();
	}
	
	Thread userMessageReciver = new Thread() {
		public void run() {
			while (true) {
				try {
					InputStream is = s.getInputStream();
					ObjectInputStream ois = new ObjectInputStream(is);
					Message m = (Message) ois.readObject();
					server.SendMessage(m);
				} catch (IOException e) {
					System.err.print("Error reciving message from " + user + " eroor:" + e);
				} catch (ClassNotFoundException e) {
					System.err.print("Дебил. Не то отправил: " + e);
				}
			}
		}
	};
}
