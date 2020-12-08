package com.chessAI.Pieces;

public class King extends Piece {
    private static final String abbrev = "K";
    private static final int value = 1000000;

    public King(boolean b, int x, int y) {
        super(b, x, y, abbrev, value);
    }
}
