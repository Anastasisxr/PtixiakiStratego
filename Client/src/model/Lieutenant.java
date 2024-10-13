package model;

import javax.swing.ImageIcon;

public class Lieutenant extends Pawn {

	public Lieutenant() {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 5;
		attack = true;
	}
	
	public Lieutenant(boolean isRed) {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 5;
		attack = true;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Lieutenant.class.getResource("/image/pawns/red/lieutenant.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Lieutenant.class.getResource("/image/pawns/blue/lieutenant.png"));
		}
		pawnLabel.setIcon(icon);
	}
	
	public Lieutenant(boolean isRed, int row, int col) {
		this.row = row;    // H thesi tou ston katakaoryfo axona
		this.col = col;    // H thesi tou ston orizontio axona
		strength = 5;
		attack = true;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Lieutenant.class.getResource("/image/pawns/red/lieutenant.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Lieutenant.class.getResource("/image/pawns/blue/lieutenant.png"));
		}
		pawnLabel.setIcon(icon);
	}
}
