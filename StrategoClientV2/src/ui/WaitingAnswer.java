package ui;

import java.io.IOException;
import java.util.ArrayList;

import model.Pawn;
import model.PlayerHandler;

public class WaitingAnswer implements Runnable{

	private PlayerHandler player;
	ArrayList<Pawn> pawns;
	
	public WaitingAnswer(PlayerHandler player) {
		this.player = player;
	}
	
	public WaitingAnswer(PlayerHandler player, ArrayList<Pawn> pawns) {
		this.player = player;
		this.pawns = pawns;
	}

	
	@Override
	public void run() {
		
		try {
			String answer = player.bf.readLine();
			System.out.println(answer);
			
			if(answer.equals("start")) {
				Waiting.answer(answer, player, pawns);
			}else {
				Waiting.answer(answer, player);
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void stop() {
		System.out.println("Thread stop");
	}

}
