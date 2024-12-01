package program;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

import errors.*;
import server.*;

public class Program {

	public static void main(String[] args) {
		
		InetAddress inetAddress;
		try {
			inetAddress = InetAddress.getLocalHost();
			System.out.println("IP Address: " + inetAddress.getHostAddress());
		} catch (UnknownHostException e) {
			System.out.println("inety pizdets: " + e);
		} 
		
		Server s = new Server();
		try {
			int port = 1000;
			ServerSocket ssRegister = new ServerSocket(port);
			System.out.println("Registration open at " + port);
			new Thread() {
				public void run() {
					// register
					while (true) {
						try {
							Socket soc = ssRegister.accept();
							ObjectInputStream oos = new ObjectInputStream(soc.getInputStream());
							try {
								s.RegisterUser((String) oos.readObject());
							} catch (RegisterError e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								soc.getOutputStream().write(0);
								continue;
							}
							System.out.println("Works reg!");
							soc.getOutputStream().write(1);
							soc.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
			}.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			int port = 1001;
			ServerSocket ssLogin = new ServerSocket(port);
			System.out.println("Login open at " + port);
			new Thread() {
				public void run() {
					//Login
					while (true) {
						
						try {
							Socket soc = ssLogin.accept();
							ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
							
							ObjectOutputStream oos = new ObjectOutputStream(soc.getOutputStream());
							try {
								oos.writeObject(s.LoginUser((String) ois.readObject(), soc));
								oos.write(1);
							} catch (LoginError e) {
								oos.write(0);
								e.printStackTrace();
							}
						} catch (IOException e) {
							System.out.println("IO error");
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
			}.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
