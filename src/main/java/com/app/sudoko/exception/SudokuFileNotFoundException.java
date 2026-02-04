package com.app.sudoko.exception;

import java.io.FileNotFoundException;

public class SudokuFileNotFoundException extends FileNotFoundException {
    public SudokuFileNotFoundException(String message) {
        super(message);
    }
}
