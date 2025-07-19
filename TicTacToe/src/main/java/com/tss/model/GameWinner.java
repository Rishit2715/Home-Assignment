package com.tss.model;

public class GameWinner {
	public boolean checkWinner(Board board, char symbol) {
		for (int i = 0; i < 3; i++) {
			if ((board.getCell(i, 0) == symbol && board.getCell(i, 1) == symbol && board.getCell(i, 2) == symbol)
					|| (board.getCell(0, i) == symbol && board.getCell(1, i) == symbol
							&& board.getCell(2, i) == symbol))
				return true;
		}

		return (board.getCell(0, 0) == symbol && board.getCell(1, 1) == symbol && board.getCell(2, 2) == symbol)
				|| (board.getCell(0, 2) == symbol && board.getCell(1, 1) == symbol && board.getCell(2, 0) == symbol);
	}
}
