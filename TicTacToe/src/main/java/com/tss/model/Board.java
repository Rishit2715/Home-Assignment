package com.tss.model;

public class Board {
	private char[][] box = new char[3][3];

	public Board() {
		reset();
	}

	public void reset() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				box[i][j] = ' ';
	}

	public boolean placeMark(int row, int col, char symbol) {
		if (row < 0 || row > 2 || col < 0 || col > 2 || box[row][col] != ' ')
			return false;
		box[row][col] = symbol;
		return true;
	}

	public boolean isFull() {
		for (char[] row : box)
			for (char cell : row)
				if (cell == ' ')
					return false;
		return true;
	}

	public void display() {
		System.out.println("-------------");
		for (int i = 0; i < 3; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++)
				System.out.print(box[i][j] + " | ");
			System.out.println("\n-------------");
		}
	}

	public char getCell(int row, int col) {
		return box[row][col];
	}
}
