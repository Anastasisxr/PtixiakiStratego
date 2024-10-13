package model;

import javax.swing.ImageIcon;

public class Colonel extends Pawn {

	public Colonel() {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 8;
		attack = true;
	}
	
	public Colonel(boolean isRed) {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 8;
		attack = true;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Colonel.class.getResource("/image/pawns/red/colonel.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Colonel.class.getResource("/image/pawns/blue/colonel.png"));
		}
		pawnLabel.setIcon(icon);
	}
	
	public Colonel(boolean isRed, int row, int col) {
		this.row = row;    // H thesi tou ston katakaoryfo axona
		this.col = col;    // H thesi tou ston orizontio axona
		strength = 8;
		attack = true;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Colonel.class.getResource("/image/pawns/red/colonel.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Colonel.class.getResource("/image/pawns/blue/colonel.png"));
		}
		pawnLabel.setIcon(icon);
	}
}
