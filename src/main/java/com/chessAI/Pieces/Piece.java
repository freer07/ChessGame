package com.chessAI.Pieces;

public class Piece {
    boolean black;
    int row;
    int column;
    boolean isActive;

    public Piece(boolean b, int x, int y) {
        black = b;
        row = x;
        column = y;
        isActive = true;
    }
}
