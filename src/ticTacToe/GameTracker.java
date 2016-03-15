package ticTacToe;

public class GameTracker {

	// each spot on the board corresponds to a number btw 1 and 9
	// 1 2 3
	// 4 5 6
	// 7 8 9
	private String[][] gameBoard;
	private String whoseTurn;

	public GameTracker() {
		gameBoard = new String[3][3];
		// set up emty board
		for (int r = 0; r < gameBoard.length; r++) {
			for (int c = 0; c < gameBoard[r].length; c++) {
				gameBoard[r][c] = " ";
			}
		}
		whoseTurn = "x"; // x goes first
	}

	// move
	public boolean isValidMove(int spot) {
		// if the row and column is blank than its a valid move
		switch (spot) {
		case 1:
			return (gameBoard[0][0].equals(" "));
		case 2:
			return (gameBoard[0][1].equals(" "));
		case 3:
			return (gameBoard[0][2].equals(" "));
		case 4:
			return (gameBoard[1][0].equals(" "));
		case 5:
			return (gameBoard[1][1].equals(" "));
		case 6:
			return (gameBoard[1][2].equals(" "));
		case 7:
			return (gameBoard[2][0].equals(" "));
		case 8:
			return (gameBoard[2][1].equals(" "));
		case 9:
			return (gameBoard[2][2].equals(" "));

		}
		return false; // not valid since not a spot
	}

	// is the board full
	public boolean isFull() {
		// if all spots r != " "
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if (gameBoard[r][c].equals(" ")) {
					return false;
				}
			}
		}
		return true; // if it did not have an empty spot then its full
	}

	// who is the winner, if no one won return N
	public String winner() {
		// check if any of the rows have all the same letter
		for (int r = 0; r < gameBoard.length; r++) {
			// cheack if all three rows have are the same, but not empty
			if (!gameBoard[r][0].equals(" ")
					&& gameBoard[r][0].equals(gameBoard[r][1])
					&& gameBoard[r][0].equals(gameBoard[r][2])) {
				return gameBoard[r][0];
			}
		}
		// any colum has all the same x
		for (int c = 0; c < gameBoard.length; c++) {
			// check if any row has all the same markers, and is not empty
			if (!gameBoard[0][c].equals(" ")
					&& gameBoard[0][c].equals(gameBoard[1][c])
					&& gameBoard[0][c].equals(gameBoard[2][c])) {
				return gameBoard[0][c];
			}

		}
		// check if diagnals have same values
		if (gameBoard[1][1].equals(" ")) { // if the middle value is not filled
											// then
											// both diagnals can not be filled
											// with
											// the same marker
			return " ";
		}
		// diagnal 1
		if (gameBoard[0][0].equals(gameBoard[1][1])
				&& gameBoard[0][0].equals(gameBoard[2][2])) {
			return gameBoard[0][0];

		}
		// diagnal 2
		if (gameBoard[0][2].equals(gameBoard[1][1])
				&& gameBoard[0][2].equals(gameBoard[2][0])) {
			return gameBoard[0][2];
		}
		// if winner did not become true then return winner here
		return " ";
	}

	// add move - only call after u make sure its valid
	public void addMove(int spot) {
		switch (spot) {
		case 1:
			gameBoard[0][0] = this.whoseTurn;
			break;
		case 2:
			gameBoard[0][1] = this.whoseTurn;
			break;
		case 3:
			gameBoard[0][2] = this.whoseTurn;
			break;
		case 4:
			gameBoard[1][0] = this.whoseTurn;
			break;
		case 5:
			gameBoard[1][1] = this.whoseTurn;
			break;
		case 6:
			gameBoard[1][2] = this.whoseTurn;
			break;
		case 7:
			gameBoard[2][0] = this.whoseTurn;
			break;
		case 8:
			gameBoard[2][1] = this.whoseTurn;
			break;
		case 9:
			gameBoard[2][2] = this.whoseTurn;
			break;

		}
		// after a move is made change whose turn in it
		if (whoseTurn.equals("x")) {
			whoseTurn = "y";
		} else {
			whoseTurn = "x";
		}
	}

	public String getWhoseTurn() {
		return whoseTurn;
	}

	/*
	 * show the board with an empty board next to it to show number of each
	 * location
	 */
	public String displayBoard() {
		StringBuilder build = new StringBuilder();
		for (int r = 0; r < gameBoard.length; r++) {
			build.append("| ");
			for (int c = 0; c < gameBoard[r].length; c++)
				build.append(gameBoard[r][c] + " | ");
			if (r == 0) {
				build.append("\t\t 1 | 2 | 3 |");
			} else if (r == 1) {
				build.append("\t\t 4 | 5 | 6 |");
			} else {
				build.append("\t\t 7 | 8 | 9 |");
			}
			build.append("\n_____________");
			build.append("\t\t __________ \n");
		}

		return build.toString();
	}
}
