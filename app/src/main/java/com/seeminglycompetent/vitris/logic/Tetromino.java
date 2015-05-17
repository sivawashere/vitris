package com.seeminglycompetent.vitris.logic;

/**
 *
 * @author Siva Somayyajula
 */

import java.util.Random;

public class Tetromino {
    Shape shape = Shape.NULL;
    int[][] coords = shape.getCoords();
    void setX(int i, int x) {
        coords[i][0] = x;
    }
    void setY(int i, int y) {
        coords[i][1] = y;
    }
    public int getX(int i) {
        return coords[i][0];
    }
    public int getY(int i) {
        return coords[i][1];
    }
    public Shape getShape() {
        return shape;
    }
    public void setShape(Shape shape) {
        coords = shape.getCoords();
        this.shape = shape;
    }
    public void setRandomShape() {
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        setShape(Shape.values()[x]);
    }
    public int minX() {
        int min = coords[0][0];
        for (int[] coord : coords)
            min = Math.min(min, coord[0]);
        return min;
    }
    public int minY() {
        int min = coords[0][1];
        for (int[] coord : coords)
            min = Math.min(min, coord[1]);
        return min;
    }
    public Tetromino leftRotate() {
        if (shape == Shape.O)
            return this;
        Tetromino res = new Tetromino();
        res.shape = shape;
        for (int i = 0; i < coords.length; i++) {
            res.setX(i, getY(i));
            res.setY(i, -getX(i));
        }
        return res;
    }
    public Tetromino rightRotate() {
        if (shape == Shape.O)
            return this;
        Tetromino res = new Tetromino();
        res.shape = shape;
        for (int i = 0; i < coords.length; i++) {
            res.setX(i, -getY(i));
            res.setY(i, getX(i));
        }
        return res;
    }
}
