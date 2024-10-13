package model;

import javax.swing.ImageIcon;

public class Flag extends Pawn {

	public Flag() {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = -1;
		attack = false;
	}
	
	public Flag(boolean isRed) {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 0;
		attack = false;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Flag.class.getResource("/image/pawns/red/flag.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Flag.class.getResource("/image/pawns/blue/flag.png"));
		}
		pawnLabel.setIcon(icon);
	}
	
	public Flag(boolean isRed, int row, int col) {
		this.row = row;    // H thesi tou ston katakaoryfo axona
		this.col = col;    // H thesi tou ston orizontio axona
		strength = 0;
		attack = false;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Flag.class.getResource("/image/pawns/red/flag.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Flag.class.getResource("/image/pawns/blue/flag.png"));
		}
		pawnLabel.setIcon(icon);
	}
}
