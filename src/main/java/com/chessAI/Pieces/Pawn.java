package com.chessAI.Pieces;

public class Pawn extends Piece {
    private static final String abbrev = "P";
    private static final int value = 10;

    public Pawn(boolean b, int x, int y) {
        super(b, x, y, abbrev, value);
    }
}
