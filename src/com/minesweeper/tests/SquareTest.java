package com.minesweeper.tests;

import com.minesweeper.grid.Square;
import org.junit.Test;
import static org.junit.Assert.*;

public class SquareTest {
    @Test
    public void testUncoverMine() {
        Square square = new Square();
        square.setMine(true);
        square.uncover();
        assertTrue(square.isUncovered());
        assertTrue(square.hasMine());
    }
}

