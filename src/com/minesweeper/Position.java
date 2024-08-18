package com.minesweeper;

import java.util.ArrayList;
import java.util.List;

public class Position {
    private int row;
    private int column;

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

    public boolean isValid(int gridSize) {
        return row >= 0 && row < gridSize && column >= 0 && column < gridSize;
    }

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
