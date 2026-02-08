package com.app.sudoko;

import com.app.sudoko.exception.InvalidCharacterException;
import com.app.sudoko.exception.SudokuFileNotFoundException;

import java.util.Scanner;


public class Main {
    private static Sudoku game;
    private static long startTime;
    private static int hintsUsed = 0;
    private static String currentLevelName;

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            printWelcome();

            int choice = readDifficulty(scanner);
            String fileName = "puzzle" + choice + ".txt";
            currentLevelName = "Puzzle " + choice;

            game = new Sudoku(fileName);
            game.read();

            startTime = System.currentTimeMillis();
            playGame(scanner);

        } catch (SudokuFileNotFoundException | InvalidCharacterException e) {
            System.err.println("❌ Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("❌ Invalid input. Please restart and choose 1–4.");
        }
    }


    private static void printWelcome() {
        System.out.println("=================================");
        System.out.println("        🧩 SUDOKU CHALLENGE       ");
        System.out.println("=================================");
        System.out.println("Fill the grid so each row, column");
        System.out.println("and 3x3 box contains 1–9 exactly once.");
        System.out.println();
    }

    private static int readDifficulty(Scanner scanner) {
        System.out.println("Choose a puzzle:");
        System.out.println("1️⃣  Puzzle 1 (Beginner)");
        System.out.println("2️⃣  Puzzle 2 (Easy)");
        System.out.println("3️⃣  Puzzle 3 (Medium)");
        System.out.println("4️⃣  Puzzle 4 (Hard)");
        System.out.print("👉 Your choice: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static void playGame(Scanner scanner) {
        boolean running = true;

        while (running) {
            printFormattedBoard();

            System.out.println();
            System.out.println("Commands:");
            System.out.println("  row col value  → place a number (example: 1 3 9)");
            System.out.println("  hint           → fill one correct cell");
            System.out.println("  solve          → auto-solve puzzle");
            System.out.println("  exit           → quit game");
            System.out.print("👉 ");

            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "exit" -> running = false;
                case "hint" -> provideHint();
                case "solve" -> {
                    autoSolveAndSave();
                    running = false;
                }
                default -> handleMove(input);
            }

            if (game.isComplete()) {
                printFormattedBoard();
                calculateFinalScore();
                running = false;
            }
        }
    }

    private static void printFormattedBoard() {
        SudokuCell[][] board = game.getBoard();

        System.out.println();
        System.out.println("======== " + currentLevelName + " ========");
        System.out.println("   1 2 3   4 5 6   7 8 9");
        System.out.println(" +-------+-------+-------+");

        for (int r = 0; r < 9; r++) {
            System.out.print((r + 1) + "| ");

            for (int c = 0; c < 9; c++) {
                int val = board[r][c].getValue();
                System.out.print(val == 0 ? ". " : val + " ");

                if ((c + 1) % 3 == 0) System.out.print("| ");
            }
            System.out.println();

            if ((r + 1) % 3 == 0)
                System.out.println(" +-------+-------+-------+");
        }
    }

    // ================= GAME ACTIONS =================

    private static void handleMove(String input) {
        try {
            String[] parts = input.split("\\s+");
            if (parts.length != 3)
                throw new Exception("Use format: row col value");

            int r = Integer.parseInt(parts[0]) - 1;
            int c = Integer.parseInt(parts[1]) - 1;
            int v = Integer.parseInt(parts[2]);

            if (game.isSafeMove(r, c, v)) {
                game.updateCell(r, c, v);
                System.out.println("✅ Good Move!");
            } else {
                System.out.println("⚠️  Invalid move: rule conflict.");
            }
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void provideHint() {
        SudokuCell[][] copy = game.getBoardCopy();
        SudokuSolver solver = new SudokuSolver(copy);

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (game.getBoard()[r][c].getValue() == 0) {
                    int val = solver.getHintValue(r, c);
                    game.getBoard()[r][c].setValue(val);
                    hintsUsed++;
                    System.out.println("💡 Hint applied! (-50 points)");
                    return;
                }
            }
        }
        System.out.println("No hints available.");
    }

    private static void autoSolveAndSave() {
        SudokuSolver solver = new SudokuSolver(game.getBoard());
        if (solver.solve()) {
            System.out.println("\n🎉 Puzzle solved automatically!");
            printFormattedBoard();
            game.saveSolution(
                    currentLevelName.replace(" ", "").toLowerCase() + ".solution.txt"
            );
        }
    }

    private static void calculateFinalScore() {
        long time = (System.currentTimeMillis() - startTime) / 1000;
        int score = Math.max(0, 1000 - (int) time - (hintsUsed * 50));

        System.out.println();
        System.out.println("=================================");
        System.out.println("🎉 CONGRATULATIONS!");
        System.out.println("⏱️  Time: " + time + " seconds");
        System.out.println("💡 Hints used: " + hintsUsed);
        System.out.println("🏆 Final Score: " + score);
        System.out.println("=================================");
    }
}
