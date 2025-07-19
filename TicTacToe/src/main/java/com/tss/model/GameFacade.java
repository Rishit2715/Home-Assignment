package com.tss.model;

public class GameFacade {
	private Board board;
	private GameWinner winner;
	private Player player1, player2;
	private Player currentPlayer;

	public GameFacade(String name1, char symbol1, String name2, char symbol2) {
		board = new Board();
		winner = new GameWinner();
		player1 = new Player(name1, symbol1);
		player2 = new Player(name2, symbol2);
		currentPlayer = player1;
	}

	public void displayBoard() {
		board.display();
	}

	public boolean playTurn(int row, int col) {
		if (!board.placeMark(row, col, currentPlayer.getSymbol())) {
			System.out.println("Invalid move. Try again.");
			return false;
		}

		board.display();

		if (winner.checkWinner(board, currentPlayer.getSymbol())) {
			System.out.println(currentPlayer.getName() + " wins!");
			return true;
		}

		if (board.isFull()) {
			System.out.println("Game is a draw!");
			return true;
		}

		switchPlayer();
		return false;
	}

	private void switchPlayer() {
		currentPlayer = (currentPlayer == player1) ? player2 : player1;
	}

	public void resetGame() {
		board.reset();
		currentPlayer = player1;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}
}
