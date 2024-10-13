package ui;

import java.io.IOException;

import model.PlayerHandler;

public class OpponentTurn implements Runnable{
	
	private PlayerHandler player;

	public	OpponentTurn(PlayerHandler player){
		this.player = player;
	}
	
	@Override
	public void run() {
		
		try {
			String answer = player.bf.readLine();
			System.out.println(answer);
			GameBoard.turn(answer, player);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
