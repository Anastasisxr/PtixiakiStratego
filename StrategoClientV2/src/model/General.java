package model;

import javax.swing.ImageIcon;

public class General extends Pawn {

	public General() {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 9;
		attack = true;
	}
	
	public General(boolean isRed) {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 9;
		attack = true;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(General.class.getResource("/image/pawns/red/general.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(General.class.getResource("/image/pawns/blue/general.png"));
		}
		pawnLabel.setIcon(icon);
	}
	
	public General(boolean isRed, int row, int col) {
		this.row = row;    // H thesi tou ston katakaoryfo axona
		this.col = col;    // H thesi tou ston orizontio axona
		strength = 9;
		attack = true;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(General.class.getResource("/image/pawns/red/general.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(General.class.getResource("/image/pawns/blue/general.png"));
		}
		pawnLabel.setIcon(icon);
	}
}
