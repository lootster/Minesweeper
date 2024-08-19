package com.minesweeper;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Game class handles the overall flow of the Minesweeper game.
 * It manages the grid, processes user input, and controls the game loop.
 */
public class Game {
    private Grid grid;
    private int gridSize;
    private int mineCount;
    private boolean isGameOver;
    private boolean isFirstDisplay = true;  // Flag to track first display

    // Entry point for starting the game
    public void start() {
        initializeGame();
        runGameLoop();
        endGame();
    }

    // Initialize game settings
    private void initializeGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWelcome to Minesweeper!");

        gridSize = promptForGridSize(scanner);
        mineCount = promptForMineCount(scanner);

        grid = new Grid();
        grid.initialize(gridSize, mineCount);
        isGameOver = false;
    }

    // Main game loop
    private void runGameLoop() {
        Scanner scanner = new Scanner(System.in);

        while (!isGameOver) {
            displayGrid();
            processUserMove(scanner);
        }
    }

    // End the game
    private void endGame() {
        System.out.println("Press any key to exit...");
        new Scanner(System.in).nextLine(); // Wait for the user to press a key
    }

    // Prompt the user for the grid size
    private int promptForGridSize(Scanner scanner) {
        int gridSize;
        var maxGridSize = 10;

        do {
            gridSize = getValidIntegerInput(scanner, "Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
            if (gridSize > maxGridSize) {
                System.out.println("Invalid input. The grid size cannot exceed 10. Please try again.");
            }
        } while (gridSize > maxGridSize);

        return gridSize;
    }

    // Prompt the user for the number of mines
    private int promptForMineCount(Scanner scanner) {
        int totalSquares = gridSize * gridSize;
        int maxMines = (int) (totalSquares * 0.35);
        int mineCount;

        do {
            mineCount = getValidIntegerInput(scanner, "Enter the number of mines to place on the grid (maximum is 35% of the total squares): ");
            if (mineCount > maxMines) {
                System.out.println("Invalid input. The number of mines cannot exceed " + maxMines + ". Please try again.");
            }
        } while (mineCount > maxMines);

        return mineCount;
    }

    // Process the user's move
    private void processUserMove(Scanner scanner) {
        System.out.print("Select a square to reveal (e.g. A1): ");
        String input = scanner.nextLine();

        try {
            Position position = parseInput(input);
            Cell cell = grid.getCell(position);

            if (cell.isMine()) {
                displayGameOver();
            } else {
                int adjacentMines = grid.uncoverCell(position);
                System.out.println("This square contains " + adjacentMines + " adjacent mines.");
                checkForWinCondition();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    // Check if the user has won the game
    private void checkForWinCondition() {
        if (grid.isGameWon()) {
            displayGameWon();
        }
    }

    // Get a valid integer input from the user
    private int getValidIntegerInput(Scanner scanner, String prompt) {
        int value = -1;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt);
            try {
                value = scanner.nextInt();
                valid = true; // Break the loop if the input is valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

        scanner.nextLine(); // Consume the newline character
        return value;
    }

    // Display game won message
    private void displayGameWon() {
        System.out.println("Congratulations, you have won the game!");
        isGameOver = true;
    }

    // Display game over message
    private void displayGameOver() {
        System.out.println("Oh no you detonated a mine! Game over.");
        isGameOver = true;
    }

    // Parse user input to convert it into a Position object
    public Position parseInput(String input) {
        if (input.length() < 2) {
            throw new IllegalArgumentException("Invalid input length");
        }

        char rowChar = input.toUpperCase().charAt(0);
        int row = rowChar - 'A'; // Convert 'A' to 0, 'B' to 1, etc.
        int col;

        try {
            col = Integer.parseInt(input.substring(1)) - 1; // Convert "1" to 0, "2" to 1, etc.
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid column number");
        }

        Position position = new Position(row, col);
        if (!position.isValid(gridSize)) {
            throw new IllegalArgumentException("Position out of bounds");
        }

        return position;
    }

    // Display the current state of the grid
    private void displayGrid() {
        displayMinefieldMsg();
        displayColumnHeaders();
        displayGridRows();
    }

    private void displayMinefieldMsg() {
        if (isFirstDisplay) {
            System.out.println("Here is your minefield:");
            isFirstDisplay = false;
        } else {
            System.out.println("Here is your updated minefield:");
        }
    }

    private void displayColumnHeaders() {
        System.out.print("  ");
        for (int col = 1; col <= gridSize; col++) {
            System.out.print(col + " ");
        }
        System.out.println();
    }

    private void displayGridRows() {
        for (int row = 0; row < gridSize; row++) {
            System.out.print((char) ('A' + row) + " ");
            for (int col = 0; col < gridSize; col++) {
                renderCell(row, col);
            }
            System.out.println();
        }
    }

    private void renderCell(int row, int col) {
        Cell cell = grid.getCell(new Position(row, col));
        if (cell.hasBeenRevealed()) {
            int adjacentMines = cell.getAdjacentMines();
            System.out.print(adjacentMines > 0 ? adjacentMines + " " : "0 ");
        } else {
            System.out.print("_ ");
        }
    }
}
