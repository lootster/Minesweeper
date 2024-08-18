package test.com.minesweeper;

import com.minesweeper.Grid;
import com.minesweeper.Position;
import org.junit.Test;
import static org.junit.Assert.*;

public class GridTest {

    @Test
    public void testGridInitialization() {
        Grid grid = new Grid();
        grid.initialize(4, 3); // Initialize a 4x4 grid with 3 mines

        int mineCount = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Position position = new Position(row, col);
                if (grid.getCell(position).isMine()) {
                    mineCount++;
                }
            }
        }

        assertEquals(3, mineCount); // Ensure that exactly 3 mines were placed
    }

    @Test
    public void testGetCell() {
        Grid grid = new Grid();
        grid.initialize(4, 2); // Initialize a 4x4 grid with 2 mines

        // Test getting a cell at a valid position
        Position position = new Position(2, 2);
        assertNotNull(grid.getCell(position)); // Ensure the cell is not null

        // Test getting a cell at an edge position
        Position edgePosition = new Position(0, 3);
        assertNotNull(grid.getCell(edgePosition)); // Ensure the cell is not null

        // Test getting a cell at a corner position
        Position cornerPosition = new Position(3, 3);
        assertNotNull(grid.getCell(cornerPosition)); // Ensure the cell is not null
    }

    @Test
    public void testUncoverCell() {
        Grid grid = new Grid();
        grid.initialize(4, 2); // Initialize a 4x4 grid with 2 mines

        Position position = new Position(1, 1);
        int result = grid.uncoverCell(position);

        assertTrue(grid.getCell(position).hasBeenRevealed()); // The cell should be uncovered
        assertTrue(result >= 0); // The result should be non-negative (number of adjacent mines)
    }

    @Test
    public void testWinCondition() {
        Grid grid = new Grid();
        grid.initialize(3, 1); // Initialize a 3x3 grid with 1 mine

        // Uncover all non-mine cells
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Position position = new Position(row, col);
                if (!grid.getCell(position).isMine()) {
                    grid.uncoverCell(position);
                }
            }
        }

        assertTrue(grid.isGameWon()); // The game should be won
    }
}

