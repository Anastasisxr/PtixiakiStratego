package model;

import javax.swing.ImageIcon;

public class Sergeant extends Pawn {

	public Sergeant() {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 4;
		attack = true;
	}
	
	public Sergeant(boolean isRed) {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 4;
		attack = true;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Sergeant.class.getResource("/image/pawns/red/sergeant.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Sergeant.class.getResource("/image/pawns/blue/sergeant.png"));
		}
		pawnLabel.setIcon(icon);
	}
	
	public Sergeant(boolean isRed, int row, int col) {
		this.row = row;    // H thesi tou ston katakaoryfo axona
		this.col = col;    // H thesi tou ston orizontio axona
		strength = 4;
		attack = true;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Sergeant.class.getResource("/image/pawns/red/sergeant.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Sergeant.class.getResource("/image/pawns/blue/sergeant.png"));
		}
		pawnLabel.setIcon(icon);
	}
	
}
