import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * This JUnit test verifies that solution of the Gameboard is not shown the user.
 * @author 100619029
 *
 */
class GameBoardSimilarWithSolutionBoardTest {

	@Test
	void test() {
		GameBoard board1 = new GameBoard(9);
		int arr1[][] = board1.getGameBoard();
		int arr1Sol[][] = board1.getSolutionGameBoard();
		
		boolean flag = true;
		for(int x = 0; x < arr1.length; x++) {
			for(int y = 0; y < arr1[x].length; y++) {
				if(arr1[x][y] != arr1Sol[x][y] && arr1[x][y] != 0) {
					flag = false;
					break;
				}
			}
		}
		assertEquals(true, flag);
	}
}
