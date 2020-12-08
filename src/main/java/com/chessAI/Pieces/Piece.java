package com.chessAI.Pieces;

public class Piece {
    boolean black;
    int row;
    int column;
    boolean isActive;
    public String abbrev;
    public int value;

    public Piece(boolean b, int x, int y, String a, int v) {
        black = b;
        row = x;
        column = y;
        isActive = true;
        abbrev = a;
        value = v;
    }
}
