package model;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Pawn {
	
	public static final int BOMBS = 6;
	public static final int MARSHAL = 1;
	public static final int GENERAL = 1;
	public static final int COLONEL = 2;
	public static final int MAJOR = 3;
	public static final int CAPTAIN = 4;
	public static final int LIEUTENANT = 4;
	public static final int SERGEANT = 4;
	public static final int MINER = 5;
	public static final int SCOUT = 8;
	public static final int SPY = 1;
	public static final int FLAG = 1;
	
	public static int tempRow = -1;
	public static int tempCol = -1;
	public static boolean selected = false;
	
	protected ImageIcon icon;  
	protected int row;   
	protected int col;  
	protected boolean isRed; 
	protected boolean oppPawn = false;
	protected int strength; 
	protected boolean attack;
	protected JLabel pawnLabel = new JLabel("");
	
	public Pawn() {
			pawnLabel.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
						System.out.println("Str: "+strength);
						tempRow = row;
						tempCol = col;
						selected = true;
				}

				@Override
				public void mousePressed(MouseEvent e) {
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					
				}
				
			});
	}
	
	public Pawn(boolean color, Boolean oppPawn) {
		isRed = color;
		this.oppPawn = oppPawn;
		
		if(isRed) {
			icon = new ImageIcon(Pawn.class.getResource("/image/pawns/red/hidden.png"));
		}else {
			icon = new ImageIcon(Pawn.class.getResource("/image/pawns/blue/hidden.png"));
		}
		pawnLabel.setIcon(icon);
	}
	
	public Pawn(int strength, boolean isRed, int row, int col) {
		this.strength = strength;
		this.isRed = isRed;
		this.row = row;
		this.col = col;
		
		switch(strength) {
			case 11:
				if(isRed) {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/red/bomb.png"));
				}else {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/blue/bomb.png"));
				}
				pawnLabel.setIcon(icon);
				break;
				
			case 10:
				if(isRed) {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/red/marshal.png"));
				}else {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/blue/marshal.png"));
				}
				pawnLabel.setIcon(icon);
				break;
	
			case 9:
				if(isRed) {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/red/general.png"));
				}else {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/blue/general.png"));
				}
				pawnLabel.setIcon(icon);
				break;
	
			case 8:
				if(isRed) {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/red/colonel.png"));
				}else {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/blue/colonel.png"));
				}
				pawnLabel.setIcon(icon);
				break;
	
			case 7:
				if(isRed) {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/red/major.png"));
				}else {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/blue/major.png"));
				}
				pawnLabel.setIcon(icon);
				break;
	
			case 6:
				if(isRed) {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/red/captain.png"));
				}else {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/blue/captain.png"));
				}
				pawnLabel.setIcon(icon);
				break;
	
			case 5:
				if(isRed) {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/red/lieutenant.png"));
				}else {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/blue/lieutenant.png"));
				}
				pawnLabel.setIcon(icon);
				break;
	
			case 4:
				if(isRed) {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/red/sergeant.png"));
				}else {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/blue/sergeant.png"));
				}
				pawnLabel.setIcon(icon);
				break;
	
			case 3:
				if(isRed) {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/red/miner.png"));
				}else {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/blue/miner.png"));
				}
				pawnLabel.setIcon(icon);
				break;
	
			case 2:
				if(isRed) {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/red/scout.png"));
				}else {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/blue/scout.png"));
				}
				pawnLabel.setIcon(icon);
				break;
	
			case 1:
				if(isRed) {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/red/spy.png"));
				}else {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/blue/spy.png"));
				}
				pawnLabel.setIcon(icon);
				break;
	
			case -1:
				if(isRed) {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/red/flag.png"));
				}else {
					icon = new ImageIcon(Pawn.class.getResource("/image/pawns/blue/flag.png"));
				}
				pawnLabel.setIcon(icon);
				break;

		}
	}


	public boolean move(int tempRow, int tempCol, int[][] myPawns, int[][] oppPawns) {
		if(attack) {
			int x = tempRow/90;
			int y = tempCol/90;
			int difX = x-row;
			int difY = y-col;
			
			if(!(x == 2 && y == 4 || x == 3 && y == 4 ||
			     x == 2 && y == 5 || x == 3 && y == 5 ||
			     x == 6 && y == 4 || x == 7 && y == 4 ||
			     x == 6 && y == 5 || x == 7 && y == 5)) {		
				if(((difX == 0 || difX == 1 || difX == -1)&& difY == 0)||
				   ((difY == 0 || difY == 1 || difY == -1)&& difX == 0))	  
					return true;		
			}	
		}
		return false;
	}

	public ImageIcon getPawnIcon() {
		return icon;
	}

	public boolean isOppPawn() {
		return oppPawn;
	}

	public void setOppPawn(boolean oppPawn) {
		this.oppPawn = oppPawn;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public boolean isRed() {
		return isRed;
	}

	public void setRed(boolean isRed) {
		this.isRed = isRed;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public boolean isAttack() {
		return attack;
	}

	public void setAttack(boolean attack) {
		this.attack = attack;
	}

	public static int getBombs() {
		return BOMBS;
	}

	public JLabel getPawnLabel() {
		return pawnLabel;
	}

	public void setPawnLabel(JLabel pawnLabel) {
		this.pawnLabel = pawnLabel;
	}
	
	
	
}
