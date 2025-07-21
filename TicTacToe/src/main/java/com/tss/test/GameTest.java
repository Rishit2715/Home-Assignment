package com.tss.test;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.tss.model.GameFacade;

public class GameTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameFacade game = new GameFacade("Player 1", 'X', "Player 2", 'O');

        System.out.println("Welcome to Tic-Tac-Toe!");
        game.displayBoard();

        while (true) {
            System.out.println("\n" + game.getCurrentPlayer().getName() + "'s turn (" +
                    game.getCurrentPlayer().getSymbol() + ").");

            int row = -1, col = -1;
            boolean validInput = false;

            while (!validInput) {
                try {
                    System.out.print("Enter row (1-3): ");
                    row = scanner.nextInt() - 1;
                    System.out.print("Enter column (1-3): ");
                    col = scanner.nextInt() - 1;

                    if (row < 0 || row > 2 || col < 0 || col > 2) {
                        System.out.println("Invalid input! Row and column must be between 1 and 3.");
                    } else {
                        validInput = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter numeric values (1-3).");
                    scanner.next(); 
                }
            }

            boolean gameEnded = game.playTurn(row, col);

            if (gameEnded) {
                System.out.println("Game Over!");
                break;
            }
        }

        scanner.close();
    }
}
