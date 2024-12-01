package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.TreeMap;
import java.util.Vector;

import errors.LoginError;
import errors.RegisterError;

public class Server {
	Vector<String> clients = new Vector<String>();
	TreeMap<String, User> onlineUsers = new TreeMap<String, User>();
	
	public boolean RegisterUser(String i) throws RegisterError {
		if (clients.contains(i)) {
			throw new RegisterError("User is already there");
		} else {
			clients.add(i);
			return true;
		}
	}
	
	public SocketAddress LoginUser(String i, Socket oos) throws LoginError {
		if (clients.contains(i)) {
			onlineUsers.put(i, new User(i, oos, this));
			return null;
		} else {
			throw new LoginError("Don't have");
		}
	}
	
	boolean SendMessage(Message message) {
		Socket s = onlineUsers.get(message.receiver).s;
		synchronized(s) {
			try {
				ObjectOutputStream oos = (ObjectOutputStream) s.getOutputStream();
				oos.writeObject(message);
				return true;
			} catch (IOException e) {
				System.err.println("Error sending message: " + e);
				return false;
			}
		}
	}
}