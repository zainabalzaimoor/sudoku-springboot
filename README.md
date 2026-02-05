# 🧩 Sudoku Challenge

**Play Sudoku in your console!** Solve puzzles, get hints, auto-solve, and track your score.

---

## 🎮 Features

- **Difficulty Levels:** 🟢 Beginner | 🟡 Easy | 🟠 Medium | 🔴 Hard  
- **Hints:** Fill a correct cell (−50 points per hint) 💡  
- **Auto-solve:** Complete puzzle instantly 🎉  
- **Timer & Score:** Track your time ⏱️ and performance 🏆  
- **Interactive Board:** Clean console display with row/column guide  
- **Input Validation:** Prevent invalid moves ⚠️  

---

## 🕹 How to Play

1. Run the game
2. Choose a puzzle (1–4)
3. Commands:
row col value → place a number (e.g., 1 3 9)
hint           → fill one correct cell
solve          → auto-solve puzzle
exit           → quit game

---

## 📊 Scoring

- Base: 1000 points  
- Time penalty: −1 point/sec ⏱️  
- Hint penalty: −50 points per hint 💡  

---

## 📂 Puzzle Files

- Stored in: `src/main/resources/puzzles/`  
- Examples: `puzzle1.txt`, `puzzle2.txt`, `puzzle3.txt`, `puzzle4.txt`  
- Solutions saved automatically as: `*.solution.txt`  

---

## 🎨 Example Board
======== Puzzle 1 ========
1 2 3 4 5 6 7 8 9
+-------+-------+-------+
1| 5 3 . | . 7 . | . . . |
2| 6 . . | 1 9 5 | . . . |
3| . 9 8 | . . . | . 6 . |
+-------+-------+-------+
4| 8 . . | . 6 . | . . 3 |
5| 4 . . | 8 . 3 | . . 1 |
6| 7 . . | . 2 . | . . 6 |
+-------+-------+-------+
7| . 6 . | . . . | 2 8 . |
8| . . . | 4 1 9 | . . 5 |
9| . . . | . 8 . | . 7 9 |
+-------+-------+-------+

---

✅ **Have fun and enjoy solving!**


