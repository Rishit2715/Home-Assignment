package com.tss.test;

import java.util.Scanner;

import com.tss.model.GameFacade;

public class GameTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		GameFacade game = new GameFacade("Player 1", 'X', "Player 2", 'O');
		boolean gameEnded = false;

		game.displayBoard();

		while (!gameEnded) {
			System.out.println(game.getCurrentPlayer().getName() + "'s turn. Enter row and column (1-3): ");
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;
			gameEnded = game.playTurn(row, col);
		}

		System.out.println("Game Over.");
		scanner.close();
	}
}
