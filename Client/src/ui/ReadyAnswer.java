package ui;

import java.io.IOException;
import java.util.ArrayList;

import model.Pawn;
import model.PlayerHandler;

public class ReadyAnswer implements Runnable{

	private PlayerHandler player;
	private ArrayList<Pawn> pawns;
	
	public ReadyAnswer(PlayerHandler player, ArrayList<Pawn> pawns) {
		this.player = player;
		this.pawns = pawns;
	}
	
	@Override
	public void run() {
		
		try {
			String answer = player.bf.readLine();
			System.out.println(answer);
			if(answer.equals("start")) {
				BuildBoard.start(answer, player, pawns);
			}else if(answer.equals("wait")) {
				BuildBoard.wait(answer, player, pawns);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void stop() {
		System.out.println("Thread stop");
	}

}
