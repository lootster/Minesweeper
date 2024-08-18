package com.minesweeper;

import java.util.ArrayList;
import java.util.List;

/**
 * The Position class represents the coordinates of a cell on the Minesweeper grid.
 */
public class Position {
    private int row;
    private int column;

    /**
     * Constructs a Position with specified row and column.
     *
     * @param row the row index of the position
     * @param column the column index of the position
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    /**
     * Checks if the position is within the valid bounds of the grid.
     *
     * @param gridSize  the size of the grid
     * @return          true if the position is valid, false otherwise
     */
    public boolean isValid(int gridSize) {
        return row >= 0 && row < gridSize && column >= 0 && column < gridSize;
    }

    /**
     * Gets a list of all valid adjacent positions around this position on the grid.
     *
     * @param gridSize  the size of the grid
     * @return          a list of adjacent positions
     */
    public List<Position> getAdjacentPositions(int gridSize) {
        List<Position> adjacentPositions = new ArrayList<>();

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i >= 0 && i < gridSize && j >= 0 && j < gridSize && !(i == row && j == column)) {
                    adjacentPositions.add(new Position(i, j));
                }
            }
        }

        return adjacentPositions;
    }

    // Override equals and hashCode if needed for comparing positions
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return 31 * row + column;
    }
}
