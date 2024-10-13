package server;

public class PieceHelper {
	
	private int row;
	private int col;
	private int rowMove;
	private int colMove;
	private int type;
	
	
	public PieceHelper(int row, int col, int rowMove, int colMove, int type) {
		this.row = row;
		this.col = col;
		this.rowMove = rowMove;
		this.colMove = colMove;
		this.type = type;
	}
	
	public PieceHelper() {
		
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public int getRowMove() {
		return rowMove;
	}

	public void setRowMove(int rowMove) {
		this.rowMove = rowMove;
	}

	public int getColMove() {
		return colMove;
	}

	public void setColMove(int colMove) {
		this.colMove = colMove;
	}
	
}
