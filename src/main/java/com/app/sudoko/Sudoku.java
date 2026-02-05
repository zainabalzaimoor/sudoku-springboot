package com.app.sudoko;

import com.app.sudoko.exception.InvalidCharacterException;
import com.app.sudoko.exception.SudokuFileNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Sudoku {
    private final SudokuCell[][] board = new SudokuCell[9][9];
    private final Path path;

    public Sudoku(String filename) {
        this.path = Paths.get("src/main/resources/puzzles/" + filename);
    }

    public SudokuCell[][] getBoard() {
        return board;
    }

    // Read puzzle file and generate Sudoku board
    public void read() throws InvalidCharacterException, SudokuFileNotFoundException {
        if (!Files.exists(path)) {
            throw new SudokuFileNotFoundException("File does not exist: " + path);
        }

        try {
            List<String> lines = Files.readAllLines(path);

            if (lines.size() != 9) {
                throw new InvalidCharacterException("Puzzle must have 9 rows");
            }

            for (int row = 0; row < 9; row++) {
                String line = lines.get(row).trim();
                if (line.length() != 9) {
                    throw new InvalidCharacterException("Each row must have 9 digits");
                }

                for (int col = 0; col < 9; col++) {
                    char c = line.charAt(col);
                    if (!Character.isDigit(c)) {
                        throw new InvalidCharacterException(
                                "Invalid character '" + c + "' at row " + (row + 1) + ", col " + (col + 1)
                        );
                    }
                    int value = Character.getNumericValue(c);
                    boolean fixed = value != 0;
                    board[row][col] = new SudokuCell(value, fixed);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save solution in simple format
    public void saveSolution(String filename) {
        Path solutionPath = Paths.get("src/main/resources/puzzles/", filename);

        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                sb.append(board[row][col].getValue());
            }
            sb.append(System.lineSeparator());
        }
        try {
            Files.writeString(solutionPath, sb.toString());
            System.out.println("Solution saved to " + solutionPath.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to save solution: " + e.getMessage());
        }
    }

    public boolean isSafeMove(int row, int col, int value) {
        // 1. Check Row: Does 'value' already exist in this row?
        for (int i = 0; i < 9; i++) {
            // Skip checking the cell we are currently trying to fill
            if (i != col && board[row][i].getValue() == value) {
                return false;
            }
        }

        // 2. Check Column: Does 'value' already exist in this column?
        for (int i = 0; i < 9; i++) {
            if (i != row && board[i][col].getValue() == value) {
                return false;
            }
        }

        // 3. Check 3x3 Box: Does 'value' already exist in this sub-grid?
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;
        for (int r = boxRowStart; r < boxRowStart + 3; r++) {
            for (int c = boxColStart; c < boxColStart + 3; c++) {
                if (r == row && c == col) continue; // skip current cell
                if (board[r][c].getValue() == value) {
                    return false;
                }
            }
        }

        return true;
    }
    public void updateCell(int row, int col, int value) throws InvalidCharacterException {
        SudokuCell cell = board[row][col];

        // Check if the cell is a starting hint (assuming your SudokuCell has an isFixed property)
        if (cell.isFixed()) {
            throw new InvalidCharacterException("Cannot change a starting hint cell!");
        }

        cell.setValue(value);
    }
    public boolean isComplete() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                // If any cell is still 0, the puzzle isn't finished
                if (board[r][c].getValue() == 0) {
                    return false;
                }
            }
        }
        // If we reach here, all cells are filled
        return true;
    }

    public SudokuCell[][] getBoardCopy() {
        SudokuCell[][] copy = new SudokuCell[9][9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                copy[r][c] = new SudokuCell(board[r][c].getValue(), board[r][c].isFixed());
            }
        }
        return copy;
    }
}
