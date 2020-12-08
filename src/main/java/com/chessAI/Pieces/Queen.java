package com.chessAI.Pieces;

public class Queen extends Piece {
    private static final String abbrev = "Q";
    private static final int value = 90;

    public Queen(boolean b, int x, int y) {
        super(b, x, y, abbrev, value);
    }
}
