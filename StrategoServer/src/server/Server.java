package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private ServerSocket ss;
	
	
	public Server(ServerSocket ss) {
		this.ss = ss;
		DbHandler.connect();
	}
	
	public void startServer() {
			
			try {
				while(!ss.isClosed()) {
					Socket s = ss.accept();
					System.out.println("Client Connected");
					
					ClientHandler clientHandler= new ClientHandler(s);
					Thread thread = new Thread(clientHandler);
					thread.start();
					
				}
			}catch(IOException e) {
				closeServerSocket();
			}
			
		}
		
		public void closeServerSocket() {
			try {
				if(ss != null) {
					ss.close();
					DbHandler.disconnect();
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}

}
