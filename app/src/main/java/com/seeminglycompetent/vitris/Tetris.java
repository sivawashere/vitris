package com.seeminglycompetent.vitris;

/**
 * Main game logic
 * @author Siva Somayyajula
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

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

//    private void drawSquare(Graphics g, int x, int y, Tetromino shape)
//    {
//        Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102),
//                new Color(102, 204, 102), new Color(102, 102, 204),
//                new Color(204, 204, 102), new Color(204, 102, 204),
//                new Color(102, 204, 204), new Color(218, 170, 0)
//        };
//
//
//        Color color = colors[shape.ordinal()];
//
//        g.setColor(color);
//        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);
//
//        g.setColor(color.brighter());
//        g.drawLine(x, y + squareHeight() - 1, x, y);
//        g.drawLine(x, y, x + squareWidth() - 1, y);
//
//        g.setColor(color.darker());
//        g.drawLine(x + 1, y + squareHeight() - 1,
//                x + squareWidth() - 1, y + squareHeight() - 1);
//        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
//                x + squareWidth() - 1, y + 1);
//    }
//


    public void paint(Canvas c)
    {
        //super.paint(c);

        int boardTop = (int) c.getHeight() - 22 * (c.getHeight()/22);


        for (int i = 0; i < 22; ++i) {
            for (int j = 0; j < 22; ++j) {
                Shape shape = shapeAt(j, 22 - i - 1);
                if (shape != Shape.NULL) {
                    DrawView.onDraw(c, 0 + j * (c.getWidth() / 10), boardTop + i * (c.getHeight() / 22));
                }
            }
        }

        if (curPiece.getShape() != Shape.NULL) {
            for (int i = 0; i < 4; ++i) {
                int x = curX + curPiece.getX(i);
                int y = curY - curPiece.getY(i);
                DrawView.onDraw(c, (0+x*c.getWidth()/10), boardTop+(22-y-1)*(c.getHeight()/22));
//                drawSquare(g, 0 + x * squareWidth(),
//                        boardTop + (BoardHeight - y - 1) * squareHeight(),
//                        curPiece.getShape());
            }
        }
    }

    public static class DrawView extends View
    {
        Paint paint = new Paint();
        public DrawView(Context context)
        {
            super(context);
        }

        public static void onDraw(Canvas canvas, int x, int y)
        {
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(3);
            canvas.drawRect(x-1, y-1, x, y, paint);
        }
    }






}
