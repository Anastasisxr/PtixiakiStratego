package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import ui.LogIn;

public class PlayerHandler {
	
	int playerId;
	String user;
	String pass;
	int score;
	public Socket s;
	public PrintWriter pr;
	public InputStreamReader in;
	public BufferedReader bf;
	
	public PlayerHandler(Socket s) {
		this.s = s;
		try {
			pr = new PrintWriter(s.getOutputStream());
			in = new InputStreamReader(s.getInputStream());
			bf = new BufferedReader(in);
		} catch (IOException e) {
			closeSocket();
		}
	}
	
	public void playerLogIn() {
		
		new LogIn(this);
	}
	
	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public PrintWriter getPr() {
		return pr;
	}

	public void setPr(PrintWriter pr) {
		this.pr = pr;
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

	public void closeSocket() {
		try {
			if(s != null) {
				s.close();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
