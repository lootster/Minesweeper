package test.com.minesweeper;

import com.minesweeper.Game;
import com.minesweeper.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;

    @Before
    public void setUp() {
        game = new Game();
        game.setupGame(4, 3); // Set up a 4x4 grid with 3 mines for testing
    }

    @Test
    public void testParseInputValid_A1() {
        Position position = game.parseInput("A1");
        assertEquals(new Position(0, 0), position);
    }

    @Test
    public void testParseInputValid_B2() {
        Position position = game.parseInput("B2");
        assertEquals(new Position(1, 1), position);
    }

    @Test
    public void testParseInputValid_D4() {
        Position position = game.parseInput("D4");
        assertEquals(new Position(3, 3), position);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseInputInvalid_RowOutOfBounds() {
        game.parseInput("E1"); // Row 'E' is out of bounds for a 4x4 grid
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseInputInvalid_ColumnOutOfBounds() {
        game.parseInput("A5"); // Column '5' is out of bounds for a 4x4 grid
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseInputInvalid_InvalidFormat() {
        game.parseInput("1A"); // Invalid format, should be letter first
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseInputInvalid_EmptyInput() {
        game.parseInput(""); // Empty input
    }

}
