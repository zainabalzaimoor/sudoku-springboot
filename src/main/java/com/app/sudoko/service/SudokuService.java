package com.app.sudoko.service;

import com.app.sudoko.*; // Import your Sudoku, Cell, and Solver classes
import com.app.sudoko.exception.InvalidCharacterException;
import com.app.sudoko.exception.SudokuFileNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SudokuService {
    public boolean solveAndSave(String puzzleName) throws SudokuFileNotFoundException, InvalidCharacterException {
        Sudoku game = new Sudoku(puzzleName);
        game.read();
        SudokuSolver solver = new SudokuSolver(game.getBoard());
        if (solver.solve()) {
            game.saveSolution(puzzleName.replace(".txt",".solution.txt"));
            return true;
        }
        return false;
    }
}