package com.chessAI.Pieces;

public class Piece {
    boolean white;
    int row;
    int column;
    boolean isActive;

    public Piece(boolean b, int x, int y) {
        white = b;
        row = x;
        column = y;
        isActive = true;
    }
}
