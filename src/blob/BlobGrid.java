package blob;

import java.util.Random;
import java.util.Stack;

public class BlobGrid {

	private boolean[][] grid;
	private boolean[][] visited;
	private Integer counter;
	private Stack<Position> blobStack;

	public BlobGrid(int rows, int columns, int percent) {
		// set up a randomly filled grid that is the size of the rows and
		// columns
		grid = new boolean[rows][columns];
		visited = new boolean[rows][columns];
		blobStack = new Stack<Position>();
		Random rand = new Random();
		int randomNumber;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				randomNumber = rand.nextInt(100);
				if (randomNumber > percent) {
					grid[r][c] = true;

				} else {
					grid[r][c] = false;

				}
				visited[r][c] = false; // nothing was ever visited
			}
		}
		counter = null;

	}

	private void countBlobs() {

		counter = 0;
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				if (grid[row][col] && !visited[row][col]) { // if there is a
															// marker in the
															// spot
					visited[row][col] = true;
					counter++;
					checkAround(row, col);
					while(!blobStack.isEmpty()) {
						Position pos = blobStack.peek();
						blobStack.pop();
						checkAround(pos.getRow(), pos.getCol());
					}
				}
			}
		}
	}

	private void checkAround(int r, int c) {
		// above
		if ((r - 1) >= 0) {
			if (grid[r - 1][c] && !visited[r - 1][c]) {
				visited[r-1][c] = true;
				Position p = new Position(r - 1, c);
				blobStack.push(p);
			}
		}

		// below
		if ((r + 1) < grid.length) {
			if (grid[r + 1][c] && !visited[r + 1][c]) {
				visited[r+1][c] = true;
				Position p = new Position(r + 1, c);
				blobStack.push(p);
			}
		}
		// left
		if ((c - 1) >= 0) {
			if (grid[r][c - 1] && !visited[r][c - 1]) {
				visited[r][c -1] = true;
				Position p = new Position(r, c - 1);
				blobStack.push(p);
			}
		}
		// right
		if ((c + 1) < grid[r].length) {
			if (grid[r][c + 1] && !visited[r][c + 1]) {
				visited[r][c +1] = true;
				Position p = new Position(r, c + 1);
				blobStack.push(p);
			}
		}
	}

	public int numberOfBlobs() {
		// if never counted number of blobs yet count then reutrn
		if (this.counter == null) {
			countBlobs();
		}
		return counter;
	}

	public String toString() {
		StringBuilder boardDisplay = new StringBuilder();
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[r].length; c++) {
				if (grid[r][c]) {
					boardDisplay.append("X");
				} else {
					boardDisplay.append("-");
				}
			}
			boardDisplay.append("\n");
		}
		return boardDisplay.toString();
	}

	/*public static void main(String args[]) {
		BlobGrid blob = new BlobGrid(8,6, 70);
	
		System.out.println(blob.toString());
		System.out.println(blob.numberOfBlobs());
		

	}
	*/

}
