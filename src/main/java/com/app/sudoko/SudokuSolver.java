package com.app.sudoko;
public class SudokuSolver {

    private final SudokuCell[][] board;

    public SudokuSolver(SudokuCell[][] board) {
        this.board = board;
    }

    /**
     * Solves the Sudoku puzzle using backtracking
     *
     * @return true if solved successfully, false if no solution exists
     */
    public boolean solve() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col].getValue() == 0) { // empty cell
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(row, col, num)) {
                            board[row][col].setValue(num); // place number

                            if (solve()) { // recursive call
                                return true;
                            } else {
                                board[row][col].setValue(0); // backtrack
                            }
                        }
                    }
                    return false; // no valid number found, trigger backtrack
                }
            }
        }
        return true; // all cells filled
    }

    /**
     * Checks if placing a number at (row, col) is valid
     */
    boolean isValid(int row, int col, int num) {
        // Check row
        for (int c = 0; c < 9; c++) {
            if (board[row][c].getValue() == num) return false;
        }

        // Check column
        for (int r = 0; r < 9; r++) {
            if (board[r][col].getValue() == num) return false;
        }

        // Check 3x3 sub-grid
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (board[r][c].getValue() == num) return false;
            }
        }

        return true;
    }
    public int getHintValue(int row, int col) {
        // 1. We don't want to solve the REAL board, so we work on a copy
        // (This assumes you pass a copy to the solver constructor)
        if (solve()) {
            return board[row][col].getValue();
        }
        return 0; // Return 0 if the puzzle is unsolvable
    }
}
