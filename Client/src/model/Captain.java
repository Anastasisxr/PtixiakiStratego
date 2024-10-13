package model;

import javax.swing.ImageIcon;

public class Captain extends Pawn {

	public Captain() {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 6;
		attack = true;
	}
	
	public Captain(boolean isRed) {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 6;
		attack = true;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Captain.class.getResource("/image/pawns/red/captain.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Captain.class.getResource("/image/pawns/blue/captain.png"));
		}
		pawnLabel.setIcon(icon);
	}
	
	public Captain(boolean isRed, int row, int col) {
		this.row = row;    // H thesi tou ston katakaoryfo axona
		this.col = col;    // H thesi tou ston orizontio axona
		strength = 6;
		attack = true;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Captain.class.getResource("/image/pawns/red/captain.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Captain.class.getResource("/image/pawns/blue/captain.png"));
		}
		pawnLabel.setIcon(icon);
	}
}
