package model;

import javax.swing.ImageIcon;

import ui.Waiting;

public class Spy extends Pawn {

	public Spy() {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 1;
		attack = true;
	}
	
	public Spy(boolean isRed) {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 1;
		attack = true;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Spy.class.getResource("/image/pawns/red/spy.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Spy.class.getResource("/image/pawns/blue/spy.png"));
		}
		pawnLabel.setIcon(icon);
	}
	
	public Spy(boolean isRed, int row, int col) {
		this.row = row;    // H thesi tou ston katakaoryfo axona
		this.col = col;    // H thesi tou ston orizontio axona
		strength = 1;
		attack = true;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Spy.class.getResource("/image/pawns/red/spy.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Spy.class.getResource("/image/pawns/blue/spy.png"));
		}
		pawnLabel.setIcon(icon);
	}
	
}
