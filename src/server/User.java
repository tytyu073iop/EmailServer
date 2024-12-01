package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import server.*;

public class User {
	public String user;
	Socket s;
	Server server;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	
	public User(String i, Socket s, Server ser, ObjectInputStream ois, ObjectOutputStream oos) {
		user = i;
		this.s = s;
		server = ser;
		this.ois = ois;
		this.oos = oos;
		userMessageReciver.start();
	}
	
	Thread userMessageReciver = new Thread() {
		public void run() {
			while (true) {
				try {
					Message m = (Message) ois.readObject();
					server.SendMessage(m);
				} catch (EOFException e) {
					e.printStackTrace();
					break;
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
	};
}
