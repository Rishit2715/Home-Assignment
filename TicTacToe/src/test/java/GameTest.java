
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tss.model.GameFacade;

public class GameTest {

	private GameFacade game;

	@BeforeEach
	void init() {
		game = new GameFacade("Player1", 'X', "Player2", 'O');
	}

	@Test
	public void testRowWin() {
		assertEquals(false, game.playTurn(0, 0));
		assertEquals(false, game.playTurn(1, 0));
		assertEquals(false, game.playTurn(0, 1));
		assertEquals(false, game.playTurn(1, 1));
		assertEquals(true, game.playTurn(0, 2));
	}

	@Test
	public void testColumnWin() {
		assertEquals(false, game.playTurn(0, 0));
		assertEquals(false, game.playTurn(0, 1));
		assertEquals(false, game.playTurn(1, 0));
		assertEquals(false, game.playTurn(1, 1));
		assertEquals(true, game.playTurn(2, 0));
	}

	@Test
	public void testDiagonalWin() {
		assertEquals(false, game.playTurn(0, 0));
		assertEquals(false, game.playTurn(0, 1));
		assertEquals(false, game.playTurn(1, 1));
		assertEquals(false, game.playTurn(0, 2));
		assertEquals(true, game.playTurn(2, 2));
	}

	@Test
	public void testDraw() {
		assertEquals(false, game.playTurn(0, 0));
		assertEquals(false, game.playTurn(0, 1));
		assertEquals(false, game.playTurn(0, 2));
		assertEquals(false, game.playTurn(1, 1));
		assertEquals(false, game.playTurn(1, 0));
		assertEquals(false, game.playTurn(1, 2));
		assertEquals(false, game.playTurn(2, 1));
		assertEquals(false, game.playTurn(2, 0));
		assertEquals(true, game.playTurn(2, 2));
	}

	@Test
	public void testInvalidMove() {
		assertEquals(false, game.playTurn(0, 0));
		assertEquals(false, game.playTurn(1, 1));
		assertEquals(false, game.playTurn(0, 0));
	}
}
