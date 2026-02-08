package com.app.sudoko.controller;

import com.app.sudoko.exception.InvalidCharacterException;
import com.app.sudoko.exception.SudokuFileNotFoundException;
import com.app.sudoko.service.SudokuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sudoku")
public class SudokuController {

    @Autowired
    private SudokuService sudokuService;

    @PostMapping("/solve")
    public ResponseEntity<?> solve(@RequestParam String puzzleName) throws SudokuFileNotFoundException, InvalidCharacterException {
        boolean success = sudokuService.solveAndSave(puzzleName);

        if (success) {
            return ResponseEntity.ok("Solution saved to puzzles/" +
                    puzzleName.replace(".txt", ".solution.txt"));
        }
        return ResponseEntity.badRequest().body("Could not solve the puzzle.");
    }
}