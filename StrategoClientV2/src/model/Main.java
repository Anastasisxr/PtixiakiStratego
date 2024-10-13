package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;

public class Main {

	public static void main(String[] args) {
	
		Socket s;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("address.txt"));
			
			s = new Socket(reader.readLine(), 4999);
			
			PlayerHandler handler = new PlayerHandler(s);
			
			handler.playerLogIn();
		} catch (IOException e) {

			System.out.println("Server Down!!!!");
		}
	}
}