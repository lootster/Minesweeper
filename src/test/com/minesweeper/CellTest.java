package test.com.minesweeper;

import com.minesweeper.Cell;
import org.junit.Test;
import static org.junit.Assert.*;

public class CellTest {

    @Test
    public void testCellInitialization() {
        // Create a Cell that is a mine
        Cell mineCell = new Cell(true);
        // Create a Cell that is not a mine
        Cell emptyCell = new Cell(false);

        // Test initial states
        assertTrue(mineCell.isMine()); // Should be a mine
        assertFalse(emptyCell.isMine()); // Should not be a mine
        assertFalse(mineCell.hasBeenRevealed()); // Should not be revealed initially
        assertFalse(emptyCell.hasBeenRevealed()); // Should not be revealed initially
        assertEquals(0, mineCell.getAdjacentMines()); // Adjacent mines should be 0 initially
        assertEquals(0, emptyCell.getAdjacentMines()); // Adjacent mines should be 0 initially
    }

    @Test
    public void testSetAndGetAdjacentMines() {
        Cell cell = new Cell(false); // Create a non-mine cell
        cell.setAdjacentMines(3); // Set 3 adjacent mines

        assertEquals(3, cell.getAdjacentMines()); // Check if the adjacent mines count is set correctly
    }

    @Test
    public void testCellReveal() {
        Cell cell = new Cell(false); // Create a non-mine cell
        assertFalse(cell.hasBeenRevealed());  // Initially, the cell should not be revealed

        cell.reveal();  // Reveal the cell
        assertTrue(cell.hasBeenRevealed()); // Check if the cell is revealed after calling reveal()
    }

    @Test
    public void testRevealAlreadyRevealedCell() {
        Cell cell = new Cell(false); // Create a non-mine cell

        cell.reveal(); // Reveal the cell once
        assertTrue(cell.hasBeenRevealed());

        cell.reveal(); // Reveal the cell again
        assertTrue(cell.hasBeenRevealed()); // Ensure the cell's revealed status doesn't change unexpectedly
    }
}
