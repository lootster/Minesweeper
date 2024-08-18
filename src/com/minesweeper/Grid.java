package com.minesweeper;

import java.util.Random;

public class Grid {
    private Cell[][] cells;
    private int gridSize;
    private int mineCount;

    public void initialize(int gridSize, int mineCount) {
        this.gridSize = gridSize;
        this.mineCount = mineCount;
        cells = new Cell[gridSize][gridSize];

        setUpEmptyCells();
        placeMineRandomly();
        calculateAdjacentMineCounts();
    }

    private void setUpEmptyCells() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                cells[row][col] = new Cell(false);
            }
        }
    }

    private void placeMineRandomly() {
        Random random = new Random();
        int placedMines = 0;
        while (placedMines < mineCount) {
            int row = random.nextInt(gridSize);
            int col = random.nextInt(gridSize);
            if (!cells[row][col].isMine()) {
                System.out.println("Mine set at Row:" + row + " Col:" + col + " (Testing only: comment out for actual game play)");
                cells[row][col] = new Cell(true);
                placedMines++;
            }
        }
    }

    private void calculateAdjacentMineCounts() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (!cells[row][col].isMine()) {
                    int adjacentMines = countAdjacentMines(row, col);
                    cells[row][col].setAdjacentMines(adjacentMines);
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < gridSize && j >= 0 && j < gridSize && cells[i][j].isMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    public Cell getCell(Position position) {
        return cells[position.getRow()][position.getColumn()];
    }

    public int uncoverCell(Position position) {
        Cell cell = getCell(position);

        // Check if the cell has already been revealed
        if (cell.hasBeenRevealed()) {
            System.out.println("Cell already revealed at position: " + position.getRow() + ", " + position.getColumn());
            return cell.getAdjacentMines(); // Return the number of adjacent mines without revealing again
        }

        cell.reveal(); // Reveal cell since it hasn't been revealed yet
        int adjacentMines = cell.getAdjacentMines();

        // If there are no adjacent mines, uncover adjacent cells
        if (adjacentMines == 0)
            uncoverAdjacentCells(position);

        return adjacentMines;
    }

    private void uncoverAdjacentCells(Position position) {
        for (Position adjacentPosition : position.getAdjacentPositions(gridSize)) {
            Cell adjacentCell = getCell(adjacentPosition);

            if (!adjacentCell.hasBeenRevealed() && !adjacentCell.isMine()) {
                int adjacentMines = uncoverCell(adjacentPosition);  // Recursive uncover

                if (adjacentMines == 0)
                    uncoverAdjacentCells(adjacentPosition);  // Recursion continues
            }
        }
    }


    public boolean isGameWon() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                Cell cell = cells[row][col];
                if (!cell.isMine() && !cell.hasBeenRevealed())
                    return false; // Game is not won if any non-mine cell is still covered
            }
        }
        return true; // All non-mine cells are uncovered
    }
}
