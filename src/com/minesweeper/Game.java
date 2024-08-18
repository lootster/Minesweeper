package com.minesweeper;

import java.util.Scanner;

public class Game {
    private Grid grid;
    private int gridSize;
    private int mineCount;
    private boolean isGameOver;
    private boolean isFirstDisplay = true;  // Flag to track first display

    public void setupGame(int gridSize, int mineCount) {
        this.gridSize = gridSize;
        this.mineCount = mineCount;
        grid = new Grid();
        grid.initialize(gridSize, mineCount);
        isGameOver = false;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWelcome to Minesweeper!");

        // Get grid size from user
        System.out.print("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
        gridSize = scanner.nextInt();

        // Calculate the maximum number of mines allowed
        int totalSquares = gridSize * gridSize;
        int maxMines = (int) (totalSquares * 0.35);

        // Get the number of mines from user with validation
        do {
            System.out.print("Enter the number of mines to place on the grid (maximum is 35% of the total squares): ");
            mineCount = scanner.nextInt();

            if(mineCount > maxMines) {
                System.out.println("Invalid input. The number of mines cannot exceed " + maxMines + ". Please try again.");
            }
        } while (mineCount > maxMines);

        scanner.nextLine(); // Consume newline

        // Initialize the grid
        grid = new Grid();
        grid.initialize(gridSize, mineCount);
        isGameOver = false;

        // Main game loop
        while (!isGameOver) {
            displayGrid();
            System.out.print("Select a square to reveal (e.g. A1): ");
            String input = scanner.nextLine();

            try {
                Position position = parseInput(input);
                Cell cell = grid.getCell(position);

                if (cell.isMine())
                    displayGameOver();
                else {
                    int adjacentMines = cell.getAdjacentMines();
                    System.out.println("This square contains " + adjacentMines + " adjacent mines.");

                    grid.uncoverCell(position);

                    if (grid.isGameWon())
                        displayGameWon();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }

        System.out.println("Press any key to exit...");
        scanner.nextLine(); // Wait for the user to press a key
    }

    private void displayGameWon() {
        System.out.println("Congratulations, you have won the game!");
        isGameOver = true;
    }

    private void displayGameOver() {
        System.out.println("Oh no you detonated a mine! Game over.");
        isGameOver = true;
    }

    public Position parseInput(String input) {
        if (input.length() < 2) {
            throw new IllegalArgumentException("Invalid input length");
        }

        // Extract the "row" and "column" from the input
        char rowChar = input.toUpperCase().charAt(0);
        int row = rowChar - 'A'; // Convert 'A' to 0, 'B' to 1, etc.
        int col;

        try {
            col = Integer.parseInt(input.substring(1)) - 1; // Convert "1" to 0, "2" to 1, etc.
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid column number");
        }

        // Check if the calculated row and column are within bounds
        Position position = new Position(row, col);
        if (!position.isValid(gridSize)) {
            throw new IllegalArgumentException("Position out of bounds");
        }

        return position;
    }

    // Rendering of the Minefield
    private void displayGrid() {
        displayMinefieldMsg();
        displayColumnHeaders();
        displayGridRowWithLabels();
    }

    private void displayColumnHeaders() {
        System.out.print("  ");  // Space for first row of column labels
        for (int col = 1; col <= gridSize; col++) {
            System.out.print(col + " ");
        }
        System.out.println();
    }

    private void displayGridRowWithLabels() {
        for (int row = 0; row < gridSize; row++) {
            System.out.print((char) ('A' + row) + " ");  // Print row label
            for (int col = 0; col < gridSize; col++) {
                renderCell(row, col);
            }
            System.out.println();  // Move to the next line after each row is printed
        }
    }

    private void renderCell(int row, int col) {
        Cell cell = grid.getCell(new Position(row, col));
        if (cell.hasBeenRevealed()) {
            int adjacentMines = cell.getAdjacentMines();
            if (adjacentMines > 0) {
                System.out.print(adjacentMines + " ");
            } else {
                System.out.print("0 "); // No adjacent mines
            }
        } else {
            System.out.print("_ ");
        }
    }

    private void displayMinefieldMsg() {
        if (isFirstDisplay) {
            System.out.println("Here is your minefield:");
            isFirstDisplay = false;
        } else {
            System.out.println("Here is your updated minefield:");
        }
    }
}
