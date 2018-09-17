import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * This JUnit tests verifies that the generated board doesn't contain any duplicates. 
 * @author 100619029
 *
 */
class BoardContainsDifferentNumInInteralSquare {

	@Test
	void test() {
		GameBoard board1 = new GameBoard(9);
		int arr1Sol[][] = board1.getSolutionGameBoard();
        int sum = 0;
        for (int x = 0; x < arr1Sol.length; x += 3) {
            for (int y = 0; y < arr1Sol[x].length; y += 3) {
                sum += isPossibleInBlock(arr1Sol, x, y, arr1Sol[x][y]);
            }
        }
        // Calculating actual sum.
        int actualSum = 0, numOfSquares = 9;
        for (int i = 1; i <= numOfSquares; i++) {
        	actualSum += i;
        }
        actualSum *= numOfSquares;
        
        assertEquals(actualSum, sum);
    }
 
    private static int isPossibleInBlock(int[][] game, int x, int y, int number) {
        int x1 = 0;
        int y1 = 0;
        int sum = 0;
       
        if (x < 3)
            x1 = 0;
        else if (x < 6)
            x1 = 3;
        else
            x1 = 6;
 
        if (y < 3)
            y1 = 0;
        else if (y < 6)
            y1 = 3;
        else
            y1 = 6;
       
        for (int yy = y1; yy < y1 + 3; yy++) {
            for (int xx = x1; xx < x1 + 3; xx++) {
                sum += game[xx][yy];
            }
        }
        return sum;
    }
}
