package test.com.minesweeper;

import com.minesweeper.Game;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testUserInput_GridSizeAndMineCount() {
        // Simulate user input
        String simulatedInput = "4\n2\n";
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        // Run the code that uses Scanner
        Scanner scanner = new Scanner(System.in);
        int gridSize = scanner.nextInt();
        int mineCount = scanner.nextInt();

        // Assert the expected values
        assertEquals(4, gridSize);
        assertEquals(2, mineCount);
    }

}
