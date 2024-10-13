package model;

import javax.swing.ImageIcon;

import ui.Board;

public class Bomb extends Pawn {

	public Bomb() {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 11;
		attack = false;
	}
	
	public Bomb(boolean isRed) {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 11;
		attack = false;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Bomb.class.getResource("/image/pawns/red/bomb.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Bomb.class.getResource("/image/pawns/blue/bomb.png"));
		}
		pawnLabel.setIcon(icon);
	}
	
	public Bomb(boolean isRed, int row, int col) {
		this.row= row;    // H thesi tou ston katakaoryfo axona
		this.col= col;    // H thesi tou ston orizontio axona
		strength = 11;
		attack = false;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Bomb.class.getResource("/image/red/bomb.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Bomb.class.getResource("/image/blue/bomb.png"));
		}
		pawnLabel.setIcon(icon);
	}
	
}
