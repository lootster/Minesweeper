package test.com.minesweeper;

import com.minesweeper.Game;
import com.minesweeper.Position;
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

    @Test(expected = IllegalArgumentException.class)
    public void testParseInputInvalid_RowOutOfBounds() {
        // Simulate user input
        String simulatedInput = "E1\n";
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        game.parseInput(userInput); // Row 'E' is out of bounds for a 4x4 grid
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseInputInvalid_ColumnOutOfBounds() {
        // Simulate user input
        String simulatedInput = "A5\n";
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        game.parseInput(userInput); // Column '5' is out of bounds for a 4x4 grid
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseInputInvalid_InvalidFormat() {
        // Simulate user input
        String simulatedInput = "1A\n";
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        game.parseInput(userInput); // Invalid format, should be letter first
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseInputInvalid_EmptyInput() {
        // Simulate user input
        String simulatedInput = "\n";
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        game.parseInput(userInput); // Empty input
    }
}
