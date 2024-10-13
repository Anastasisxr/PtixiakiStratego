package model;

import javax.swing.ImageIcon;

public class Marshal extends Pawn {

	public Marshal() {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 10;
		attack = true;
	}
	
	public Marshal(boolean isRed) {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 10;
		attack = true;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Marshal.class.getResource("/image/pawns/red/marshal.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Marshal.class.getResource("/image/pawns/blue/marshal.png"));
		}
		pawnLabel.setIcon(icon);
	}
	
	public Marshal(boolean isRed, int row, int col) {
		this.row = row;    // H thesi tou ston katakaoryfo axona
		this.col = col;    // H thesi tou ston orizontio axona
		strength = 10;
		attack = true;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Marshal.class.getResource("/image/pawns/red/marshal.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Marshal.class.getResource("/image/pawns/blue/marshal.png"));
		}
		pawnLabel.setIcon(icon);
	}

}
