package com.seeminglycompetent.vitris.logic;

/**
 * Shape of each Tetromino
 * @author Siva Somayyajula
 */
public enum Shape {
    NULL (new int[][] {{0,   0}, {0,  0}, {0,  0}, {0,  0}}),
    Z    (new int[][] {{0,  -1}, {0,  0}, {-1, 0}, {-1, 1}}),
    S    (new int[][] {{0,  -1}, {0,  0}, {1,  0}, {1,  1}}),
    I    (new int[][] {{0,  -1}, {0,  0}, {0,  1}, {0,  2}}),
    T    (new int[][] {{-1,  0}, {0,  0}, {1,  0}, {0,  1}}),
    O    (new int[][] {{0,   0}, {1,  0}, {0,  1}, {1,  1}}),
    L    (new int[][] {{-1, -1}, {0, -1}, {0,  0}, {0,  1}}),
    J    (new int[][] {{1,  -1}, {0, -1}, {0,  0}, {0,  1}});
    int[][] coords;
    Shape(int[][] coords) {
        this.coords = coords;
    }
    public int[][] getCoords() {
        return coords;
    }
}