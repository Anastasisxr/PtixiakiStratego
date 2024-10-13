package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

	public static void main(String[] args) throws IOException {
		
		ServerSocket ss = new ServerSocket(4999);
		
		Server server = new Server(ss);
		
		server.startServer();

	}

}
