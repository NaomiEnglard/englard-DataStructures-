package ticTacToe;

import java.util.Scanner;

public class Game {
	private GameTracker tracker;
	private Player one;
	private Player two;

	public Game() {
		tracker = new GameTracker();
	}

	// make a move
	private void makeMove(int spot) {
		if (tracker.isValidMove(spot)) {
			tracker.addMove(spot);
		} else {
			System.out
					.println("not valid spot, there is already a piece there");
		}
	}

	// set up new game
	private void setUpGame() {
		Scanner input = new Scanner(System.in);
		System.out.print("what is player one's name?");
		String nameA = input.nextLine();
		System.out.print("what is player two's name?");
		String nameB = input.nextLine();
		one = new Player(nameA, "x");
		System.out.println(nameA + " is x");
		two = new Player(nameB, "y");
		System.out.println(nameB + " is y");

	}

	// //play Game
	public void playGame() {
		Scanner input = new Scanner(System.in);
		setUpGame();
		System.out.println("welcome to tic tac toe");
		System.out
				.println("to play take turns choosing a spot on the board to put your marker");
		System.out
				.println("each spot on the board is represetned by a number, write the number to go in that spot");
		System.out.println("1 | 2 | 3 \n4 | 5 | 6 \n7 | 8 | 9");

		do {
			System.out.println(tracker.displayBoard());
			System.out.println("it is " + tracker.getWhoseTurn() + " turn");
			System.out
					.println("enter the number coresponding to the spot you want to go in ");
			int spot = input.nextInt();
			makeMove(spot);
		} while (tracker.isFull() == false && tracker.winner().equals(" "));

		if (tracker.isFull()) {
			System.out.println("the board is full sorry there is no winner");
		} else {
			String winnerName;
			System.out.println("The winner is " + tracker.winner());
			if (one.getSymbol().equals(tracker.winner())) {
				winnerName = one.getName();
			} else {
				winnerName = two.getName();
			}
			System.out.println("congrats " + winnerName);
		}

	}
}
