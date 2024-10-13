package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PlayerDetails {

	private int playerId;
	private Socket s;
	private InputStreamReader in;
	private BufferedReader bf;
	private PrintWriter pr;
	private boolean ready;
	private boolean oppQuit;
	
	public PlayerDetails(int playerId, Socket s, InputStreamReader in, BufferedReader bf, PrintWriter pr) {
		this.playerId = playerId;
		this.s = s;
		this.in = in;
		this.bf = bf;
		this.pr = pr;
		this.ready = false;
		this.oppQuit = false;
		
	}

	public boolean isOppQuit() {
		return oppQuit;
	}

	public void setOppQuit(boolean oppQuit) {
		this.oppQuit = oppQuit;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public InputStreamReader getIn() {
		return in;
	}

	public void setIn(InputStreamReader in) {
		this.in = in;
	}

	public BufferedReader getBf() {
		return bf;
	}

	public void setBf(BufferedReader bf) {
		this.bf = bf;
	}

	public PrintWriter getPr() {
		return pr;
	}

	public void setPr(PrintWriter pr) {
		this.pr = pr;
	}
	
}
