import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class GameBoardMatchesGUIBoard {

	@Test
	void test() {
		GameBoard board1 = new GameBoard(9);
		int gameBoard[][] = board1.getGameBoard();
		int guiBoard[][] = board1.getGuiBoard();
		boolean flag = Arrays.deepEquals(gameBoard, guiBoard);		
		assertEquals(true, flag);
	}

}
