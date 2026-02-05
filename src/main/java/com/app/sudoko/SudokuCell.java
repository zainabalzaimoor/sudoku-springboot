package com.app.sudoko;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SudokuCell {
    private int value;
    private boolean fixed;

    // Method to set a value with validation
    public void setValue(int value) {
        if (fixed) {
            throw new IllegalStateException("Cannot modify a fixed cell.");
        }
        if (value < 0 || value > 9) {
            throw new IllegalArgumentException("Value must be between 0 and 9.");
        }
        this.value = value;
    }

}
