# 🧩 Sudoku Challenge (CLI & REST API)

**Play Sudoku in your console or solve puzzles via REST API!** This Spring Boot application features a backtracking algorithm to solve puzzles and a fully interactive scoring system.

---

## 🎮 Features

- **Dual Modes:** Play interactively in the terminal (`Main.java`) or use the Spring Boot REST API.
- **Difficulty Levels:** 🟢 Beginner | 🟡 Easy | 🟠 Medium | 🔴 Hard  
- **Hints:** Fill a correct cell (−50 points per hint) 💡  
- **Auto-solve:** Complete puzzle instantly using a Backtracking algorithm 🎉  
- **Timer & Score:** Track your time ⏱️ and performance 🏆  
- **Interactive Board:** Clean console display with row/column guides.
- **File Persistence:** Automatically reads puzzles and saves formatted solutions to `.txt` files.

---

## 🕹 How to Play (Console)

1. Run the `Main.java` file.
2. Choose a puzzle (1–4).
3. **Commands:**
   - `row col value` → Place a number (e.g., `1 3 9`)
   - `hint` → Fill one correct cell
   - `solve` → Auto-solve the puzzle
   - `exit` → Quit game

---

## 🌐 REST API Usage

You can interact with the solver programmatically while the Spring Boot app is running:

- **Solve Puzzle:** - **Endpoint:** `POST /api/sudoku/solve`
  - **Param:** `puzzleName=puzzle1.txt`
  - **Result:** Solves the board and generates a `.solution.txt` file.

---

## 📊 Scoring

- **Base:** 1000 points  
- **Time penalty:** −1 point/sec ⏱️  
- **Hint penalty:** −50 points per hint 💡  

---

## 📂 Puzzle Files

- **Storage Path:** `src/main/resources/puzzles/`  
- **Format:** Supports the standard grid format with `|`, `+`, and `-` borders.
- **Solutions:** Saved automatically as `[filename].solution.txt`.

---

## 🎨 Example Board

======== Puzzle 1 ========
    1 2 3   4 5 6   7 8 9
  +-------+-------+-------+
1 | 5 3 . | . 7 . | . . . |
2 | 6 . . | 1 9 5 | . . . |
3 | . 9 8 | . . . | . 6 . |
  +-------+-------+-------+
4 | 8 . . | . 6 . | . . 3 |
5 | 4 . . | 8 . 3 | . . 1 |
6 | 7 . . | . 2 . | . . 6 |
  +-------+-------+-------+
7 | . 6 . | . . . | 2 8 . |
8 | . . . | 4 1 9 | . . 5 |
9 | . . . | . 8 . | . 7 9 |
  +-------+-------+-------+

---

✅ **Have fun and enjoy solving!**
