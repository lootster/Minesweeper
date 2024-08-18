# Minesweeper Game

Welcome to the Minesweeper game! This guide will help you set up and run the game on both Windows and Linux platforms.

## Overview

This is a simple command-line implementation of the classic Minesweeper game. The game is written in Java and is designed to be easy to run on any system that supports Java.

### Design Summary

- **Grid Layout**: The game grid is represented as a 2D array of cells. Each cell can either be a mine or a non-mine.
- **Game Logic**: The game involves uncovering cells. If a cell contains a mine, the game ends. If a cell has no adjacent mines, the game automatically uncovers adjacent cells until it reaches cells with adjacent mines.
- **Win Condition**: The game is won when all non-mine cells are uncovered.
- **Assumptions**:
    - The grid size and the number of mines are configurable.
    - User input is expected in a specific format, such as "A1" for row A, column 1.

## Requirements

To run the Minesweeper game, you need to have the following installed on your system:

- **Java Development Kit (JDK)**: Version 8 or higher.
- **Operating System**: Windows or Linux.

## Setup Instructions

### Windows

2. **Clone the Project**:
    - Download the project files or clone the repository using Git:
      ```bash
      git clone https://github.com/lootster/Minesweeper.git
      ```

3. **Navigate to the Project Directory**:
    - Open Command Prompt and navigate to the directory where the project is located:
      ```bash
      cd path\to\minesweeper
      ```

4. **Compile the Java Code**:
    - Compile the Java files using the following command:
      ```bash
      javac -d bin src/com/minesweeper/*.java
      ```

5. **Run the Game**:
    - Once compiled, you can run the game with:
      ```bash
      java -cp bin com.minesweeper.MineSweeper
      ```

### Linux

2. **Clone the Project**:
    - Download the project files or clone the repository using Git:
      ```bash
      git clone https://github.com/lootster/Minesweeper.git
      ```

3. **Navigate to the Project Directory**:
    - Use the terminal to navigate to the project directory:
      ```bash
      cd path/to/minesweeper
      ```

4. **Compile the Java Code**:
    - Compile the Java files using:
      ```bash
      javac -d bin src/com/minesweeper/*.java
      ```

5. **Run the Game**:
    - Run the game using the following command:
      ```bash
      java -cp bin com.minesweeper.MineSweeper
      ```

## Playing the Game

- After starting the game, you will be prompted to enter the grid size and the number of mines.
- To uncover a cell, enter the position (e.g., `A1` for row A, column 1).
- The game will inform you if you hit a mine, and the grid will update after each move.

## Conclusion

Thank you for trying out the Minesweeper game! If you encounter any issues or have suggestions, feel free to contribute or reach out.

