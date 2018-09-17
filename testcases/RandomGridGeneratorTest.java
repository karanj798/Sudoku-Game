import static org.junit.Assert.assertEquals;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
/**
 * This JUnit test verifies that random boards are generated every time.
 * @author 100619029
 *
 */
class RandomGridGeneratorTest {

	@Test
	void test() {
		GameBoard board1 = new GameBoard(9);
		GameBoard board2 = new GameBoard(9);
		
		int arr1[][] = board1.getGameBoard();
		int arr2[][] = board2.getGameBoard();
		
		boolean flag = Arrays.deepEquals(arr1, arr2); 
		
		assertEquals(false, flag);
	}

}
