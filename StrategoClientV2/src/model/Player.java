package model;

import java.util.ArrayList;

import ui.BuildBoard;


public class Player {

	private boolean isRed;
	private ArrayList<Pawn> pawns = new ArrayList<Pawn>();
	private PlayerHandler player;
	
	public Player(boolean isRed, PlayerHandler player) {
		this.isRed = isRed;
		this.player = player;
	}
	
	public void Start(){
		BuildBoard board = new BuildBoard(isRed, pawns, player);
	}
}
