package com.chessAI.Pieces;

public class Rook extends Piece {
    private static final String abbrev = "R";
    private static final int value = 50;

    public Rook(boolean b, int x, int y) {
        super(b, x, y, abbrev, value);
    }
}
