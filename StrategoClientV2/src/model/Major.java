package model;

import javax.swing.ImageIcon;

public class Major extends Pawn {

	public Major() {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 7;
		attack = true;
	}
	
	public Major(boolean isRed) {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 7;
		attack = true;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Major.class.getResource("/image/pawns/red/major.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Major.class.getResource("/image/pawns/blue/major.png"));
		}
		pawnLabel.setIcon(icon);
	}
	
	public Major(boolean isRed, int row, int col) {
		this.row = row;    // H thesi tou ston katakaoryfo axona
		this.col = col;    // H thesi tou ston orizontio axona
		strength = 7;
		attack = true;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Major.class.getResource("/image/pawns/red/major.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Major.class.getResource("/image/pawns/blue/major.png"));
		}
		pawnLabel.setIcon(icon);
	}
}
