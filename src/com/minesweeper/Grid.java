package com.minesweeper;

import java.util.Random;

/**
 * The Grid class represents the game board for Minesweeper.
 * It handles the setup of the grid, placement of mines, calculation of adjacent mine counts,
 * and uncovering of cells.
 */
public class Grid {
    private Cell[][] cells;
    private int gridSize;
    private int mineCount;

    /**
     * Initializes the grid with the specified size and number of mines.
     *
     * @param gridSize   the size of the grid (e.g., 4 for a 4x4 grid)
     * @param mineCount  the number of mines to place on the grid
     */
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

    /**
     * Counts the number of mines adjacent to the specified cell.
     *
     * @param row  the row index of the cell
     * @param col  the column index of the cell
     * @return     the number of adjacent mines
     */
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

    /**
     * Retrieves the cell at the specified position on the grid.
     *
     * @param position  the position of the cell
     * @return          the Cell object at the specified position
     */
    public Cell getCell(Position position) {
        return cells[position.getRow()][position.getColumn()];
    }

    /**
     * Uncovers the cell at the specified position and returns the number of adjacent mines.
     * If the cell has no adjacent mines, it recursively uncovers adjacent cells.
     *
     * @param position  the position of the cell to uncover
     * @return          the number of adjacent mines
     */
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

    /**
     * Recursively uncovers adjacent cells if they have no adjacent mines.
     *
     * @param position  the position of the cell to start uncovering from
     */
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

    /**
     * Checks whether the game has been won by uncovering all non-mine cells.
     *
     * @return  true if all non-mine cells are uncovered, false otherwise
     */
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
