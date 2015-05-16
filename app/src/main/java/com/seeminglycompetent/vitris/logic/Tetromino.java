package com.seeminglycompetent.vitris.logic;

/**
 * Tetromino
 * @author Siva Somayyajula
 */
public enum Tetromino {
    I (new boolean[][] {
            {false, true, false, false},
            {false, true, false, false},
            {false, true, false, false},
            {false, true, false, false}}),
    J (new boolean[][]{
            {false, false, false, false},
            {false, true,  true,  false},
            {false, true,  false, false},
            {false, true,  false, false}}),
    L (new boolean[][]{
            {false, false, false, false},
            {false, true,  false, false},
            {false, true,  false, false},
            {false, true,  true,  false}}),
    S (new boolean[][]{
            {false, false, false, false},
            {false, true,  false, false},
            {false, true,  true,  false},
            {false, false, true,  false}}),
    Z (new boolean[][]{
            {false, false, false, false},
            {false, false, true,  false},
            {false, true,  true,  false},
            {false, true,  false, false}}),
    T (new boolean[][]{
            {false, false, false, false},
            {false, true,  false, false},
            {false, true,  true,  false},
            {false, true,  false, false}}),
    O (new boolean[][]{
            {false, false, false, false},
            {false, false, false, false},
            {false, true,  true,  false},
            {false, true,  true,  false}});

    boolean[][] data;



    Tetromino(boolean[][] data) {
        this.data = data;
    }

}