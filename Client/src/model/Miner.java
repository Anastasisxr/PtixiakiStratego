package model;

import javax.swing.ImageIcon;

public class Miner extends Pawn {

	public Miner() {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 3;
		attack = true;
	}
	
	public Miner(boolean isRed) {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 3;
		attack = true;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Miner.class.getResource("/image/pawns/red/miner.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Miner.class.getResource("/image/pawns/blue/miner.png"));
		}
		pawnLabel.setIcon(icon);
	}
	
	public Miner(boolean isRed, int col, int row) {
		this.row = row;    // H thesi tou ston katakaoryfo axona
		this.col = col;    // H thesi tou ston orizontio axona
		strength = 3;
		attack = true;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Miner.class.getResource("/image/pawns/red/miner.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Miner.class.getResource("/image/pawns/blue/miner.png"));
		}
		pawnLabel.setIcon(icon);
	}
}
