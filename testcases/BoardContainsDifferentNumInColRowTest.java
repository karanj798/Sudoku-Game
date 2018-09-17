import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This JUnit test verifies that each column and row in the Gameboard has no duplicates.  
 * @author 100619029
 *
 */
class BoardContainsDifferentNumInColRowTest {

	@Test
	void test() {
		GameBoard board1 = new GameBoard(9);
		int arr1Sol[][] = board1.getSolutionGameBoard();
		int checkNum = 0;
		boolean flag = false;
		for(int x = 0; x < arr1Sol.length; x++) {
			for(int y = 0; y < arr1Sol[x].length; y++) {
				checkNum = arr1Sol[x][y];
				flag = board1.isNextNumLegal(arr1Sol, x, y, checkNum);
			}
		}
		
		assertEquals(false, flag);
	}

}
