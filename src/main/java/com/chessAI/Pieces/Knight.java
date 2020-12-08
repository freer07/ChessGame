package com.chessAI.Pieces;

public class Knight extends Piece {
    private static final String abbrev = "N";
    private static final int value = 30;

    public Knight(boolean b, int x, int y) {
        super(b, x, y, abbrev, value);
    }
}
