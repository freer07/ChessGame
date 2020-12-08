package com.chessAI.Pieces;

public class Bishop extends Piece {
    private static final String abbrev = "B";
    private static final int value = 30;

    public Bishop(boolean b, int x, int y) {
        super(b, x, y, abbrev, value);
    }
}
