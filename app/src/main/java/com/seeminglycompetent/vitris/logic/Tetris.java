package com.seeminglycompetent.vitris.logic;

/**
 * Main game logic
 * @author Siva Somayyajula
 */

import java.util.TimerTask;

public class Tetris extends TimerTask {
    final int R = 10;
    final int C = 22;
    int linesRemoved = 0, curX = 0, curY = 0;
    boolean fallingFinished = false, started = false, paused = false;
    Tetromino curPiece = new Tetromino();
    Shape[] well = new Shape[R * C];
    TetrisTimer timer = new TetrisTimer(this);
    public Tetris() {
        timer.start();
        clearBoard();
    }
    @Override public void run() {
        if (fallingFinished) {
            fallingFinished = false;
            newPiece();
        } else {
            oneLineDown();
        }
    }
    void start() {
        if (paused)
            return;
        started = true;
        fallingFinished = false;
        linesRemoved = 0;
        clearBoard();
        newPiece();
        timer.start();
    }
    void pause()  {
        if (!started)
            return;
        paused = !paused;
        if (paused) {
            timer.stop();
            // Tell the user that the game is paused
        } else {
            timer.start();
            // Show the user how many lines they cleared
        }
        // Refresh UI
    }
    Shape shapeAt(int x, int y) {
        return well[(y * C) + x];
    }
    void dropDown() {
        for (int newY = curY; newY > 0; newY--) {
            if (!tryMove(curPiece, curX, newY - 1))
                break;
        }
        pieceDropped();
    }
    void oneLineDown() {
        if (!tryMove(curPiece, curX, curY - 1))
            pieceDropped();
    }
    void clearBoard() {
        for (int i = 0; i < R * C; i++)
            well[i] = Shape.NULL;
    }
    void pieceDropped() {
        for (int i = 0; i < 4; ++i) {
            int x = curX + curPiece.getX(i);
            int y = curY - curPiece.getY(i);
            well[(y * C) + x] = curPiece.getShape();
        }
        removeFullLines();
        if (!fallingFinished)
            newPiece();
    }
    void newPiece() {
        curPiece.setRandomShape();
        curX = C / 2 + 1;
        curY = R - 1 + curPiece.minY();
        if (!tryMove(curPiece, curX, curY)) {
            curPiece.setShape(Shape.NULL);
            timer.stop();
            started = false;
            // Show the user that the game is over
        }
    }
    boolean tryMove(Tetromino newPiece, int newX, int newY) {
        for (int i = 0; i < 4; i++) {
            int x = newX + newPiece.getX(i);
            int y = newY - newPiece.getY(i);
            if (x < 0 || x >= C || y < 0 || y >= R)
                return false;
            if (shapeAt(x, y) != Shape.NULL)
                return false;
        }
        curPiece = newPiece;
        curX = newX;
        curY = newY;
        // Refresh UI
        return true;
    }
    void removeFullLines() {
        int fullLines = 0;
        for (int i = R - 1; i >= 0; --i) {
            boolean full = true;
            for (int j = 0; j < C; ++j) {
                if (shapeAt(j, i) == Shape.NULL) {
                    full = false;
                    break;
                }
            }
            if (full) {
                fullLines++;
                for (int k = i; k < R - 1; ++k) {
                    for (int j = 0; j < C; ++j)
                        well[(k * C) + j] = shapeAt(j, k + 1);
                }
            }
        }
        if (fullLines > 0) {
            linesRemoved += fullLines;
            // Show user how many lines they cleared
            fallingFinished = true;
            curPiece.setShape(Shape.NULL);
            // Refresh UI
        }
    }
}
