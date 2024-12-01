package server;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class MessagesGetter extends Thread {
	ServerSocket ss;
	
	public MessagesGetter(ServerSocket s) {
		ss = s;
	}
	
	public void run() {
		while (true) {
			try {
				Socket s = ss.accept();
				InputStream is = s.getInputStream();
				
			} catch (IOException e) {
				System.out.println("lox axaxaxaxa message lost: " + e);
			}
		}
	}
}
