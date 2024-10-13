package model;

import javax.swing.ImageIcon;

public class Scout extends Pawn {

	public Scout() {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 2;
		attack = true;
	}
	
	public Scout(boolean isRed) {
		super();
		row=0;    // H thesi tou ston katakaoryfo axona
		col=0;    // H thesi tou ston orizontio axona
		strength = 2;
		attack = true;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Scout.class.getResource("/image/pawns/red/scout.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Scout.class.getResource("/image/pawns/blue/scout.png"));
		}
		pawnLabel.setIcon(icon);
	}
	
	public Scout(boolean isRed, int row, int col) {
		this.row = row;
		this.col = col;
		strength = 2;
		attack = true;
		if(isRed) {
			this.isRed = true;
			icon = new ImageIcon(Scout.class.getResource("/image/pawns/red/scout.png"));
		}else {
			this.isRed = false;
			icon = new ImageIcon(Scout.class.getResource("/image/pawns/blue/scout.png"));
		}
		pawnLabel.setIcon(icon);
	}
	
	public boolean move(int tempRow, int tempCol, int[][] myPawns, int[][] oppPawns) {
		if(attack) {
			int x = tempRow/90;
			int y = tempCol/90;
			int difX = x-row;
			int difY = y-col;
			boolean check = true;
			
			System.out.println("Scout going from Row:"+row+" Col:"+col+" to Row:"+x+" Col:"+y);
			System.out.println("The diffrence is x:"+difX+" y:"+difY);
			
			int[][] temp = new int[10][10];
			
			for(int i = 0; i < 10; i++)
				for(int j = 0; j < 10; j++) {
					if(myPawns[i][j] != 0) {
						temp[i][j] = myPawns[i][j];
					}
					if(oppPawns[i][j] != 0) {
						temp[i][j] = oppPawns[i][j];
					}
					
				}
			
			if(difX < 0) {
				System.out.println("got in to difX<0");
				for(int i = row-1; i>x; i--) {
					if(temp[i][col] != 0) {
						check = false;
					}
				}
			}
			
			if(difX > 0) {
				System.out.println("got in to difX>0");
				for(int i = row+1; i<x; i++) {
					if(temp[i][col] != 0) {
						check = false;
					}
				}			
			}
			
			if(difY < 0) {
				System.out.println("got in to difY<0");
				for(int i = col-1; i>y; i--) {
					if(temp[row][i] != 0) {
						check = false;
					}
				}
			}
			
			if(difY > 0) {
				System.out.println("got in to difY>0");
				for(int i = col+1; i<y; i++) {
					if(temp[row][i] != 0) {
						check = false;
					}
				}
			}
			
			for(int i = 0; i<10; i++) {
				for(int j = 0; j<10; j++) {
					System.out.print("|"+temp[j][i]);
				}
				System.out.print("|");
				System.out.println("|");
			}
			
			
			if(!(x == 2 && y == 4 || x == 3 && y == 4 ||
			     x == 2 && y == 5 || x == 3 && y == 5 ||
			     x == 6 && y == 4 || x == 7 && y == 4 ||
			     x == 6 && y == 5 || x == 7 && y == 5)) {		
				if((difX == 0 || difY == 0) && check) {
					return true;	
				} 	
						
			}	
		}
		return false;
	}
	
}
