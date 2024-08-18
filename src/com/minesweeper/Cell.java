package com.minesweeper;

/**
 * Represents a single cell on the Minesweeper grid.
 */
public class Cell {

    private boolean isMine;
    private boolean isRevealed;
    private int adjacentMines;

    /**
     * Constructs a Cell with a specified mine status.
     *
     * @param isMine  true if the cell contains a mine, false otherwise
     */
    public Cell(boolean isMine) {
        this.isMine = isMine;
        this.isRevealed = false;
    }

    public boolean isMine() {
        return isMine;
    }

    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }

    public boolean hasBeenRevealed() {
        return isRevealed;
    }

    public void reveal() {
        if (!isRevealed) {
            isRevealed = true;
        }
    }

}
