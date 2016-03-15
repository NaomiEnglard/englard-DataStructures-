package blob;

public class Position {

	
	private int row;
	private int col;
	
	public Position(int row, int column){
		this.row = row;
		this.col = column;
		
	}

	@Override
	public String toString() {
		return "Position [row=" + row + ", col=" + col + "]";
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
}
