package test.com.minesweeper;

import com.minesweeper.Position;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class PositionTest {

    @Test
    public void testPositionInitialization() {
        Position position = new Position(2, 3);
        assertEquals(2, position.getRow());
        assertEquals(3, position.getColumn());
    }

    @Test
    public void testPositionValidityWithinBounds() {
        Position position = new Position(2, 3);
        assertTrue(position.isValid(4)); // (2, 3) is within a 4x4 grid
    }

    @Test
    public void testPositionValidityOutOfBounds() {
        Position position = new Position(5, 5);
        assertFalse(position.isValid(4)); // (5, 5) is outside a 4x4 grid
    }

    @Test
    public void testAdjacentPositions() {
        Position position = new Position(1, 1);
        List<Position> adjacentPositions = position.getAdjacentPositions(3);

        // Expected adjacent positions in a 3x3 grid for position (1, 1)
        Position[] expectedPositions = {
            new Position(0, 0), new Position(0, 1), new Position(0, 2),
            new Position(1, 0), /* (1, 1) itself is excluded */ new Position(1, 2),
            new Position(2, 0), new Position(2, 1), new Position(2, 2)
        };

        assertEquals(8, adjacentPositions.size());
        for (Position expected : expectedPositions) {
            assertTrue(adjacentPositions.contains(expected));
        }
    }

    @Test
    public void testEqualsAndHashCode() {
        Position position1 = new Position(2, 3);
        Position position2 = new Position(2, 3);
        Position position3 = new Position(3, 2);

        assertEquals(position1, position2); // Should be equal
        assertNotEquals(position1, position3); // Should not be equal
        assertEquals(position1.hashCode(), position2.hashCode()); // Hash codes should be the same for equal positions
        assertNotEquals(position1.hashCode(), position3.hashCode()); // Hash codes should differ for different positions
    }
}
