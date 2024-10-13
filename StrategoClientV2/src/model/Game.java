package model;

public class Game {

	private PlayerHandler player;
	
	public Game(PlayerHandler player) {
		this.player = player;
	};
	
	public void GameStart(String color){

		if(color.equals("red")) {
			Player redPlayer = new Player(true, player);
			redPlayer.Start();
			
		}else if(color.equals("blue")) {
			Player bluePlayer = new Player(false, player);
			bluePlayer.Start();
		}
	}
}
