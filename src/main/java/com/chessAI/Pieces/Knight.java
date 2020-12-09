package com.chessAI.Pieces;

public class Knight extends Piece {
    private static final String abbrev = "N";
    private static final int value = 30;

    public Knight(boolean b, int x, int y) {
        super(b, abbrev, value);
    }

    public Knight(boolean black, boolean hasMoved) {
        super(black, abbrev, value, hasMoved);
    }

    @Override
    public void findAvailPos(Piece[][] boardLayout, int X, int Y) {
        super.findAvailPos(boardLayout, X, Y);
        //X+2 Y+1
        if (X+2 < boardLayout.length && Y+1 < boardLayout.length) {
            Piece p = boardLayout[X+2][Y+1];
            if (isValid(p)) {
                addAvailPos(X+2, Y+1);
            }
        }

        //X+2 Y-1
        if (X+2 < boardLayout.length && Y-1 >= 0) {
            Piece p = boardLayout[X+2][Y-1];
            if (isValid(p)) {
                addAvailPos(X+2, Y-1);
            }
        }

        //X-2 Y+1
        if (X-2 >= 0 && Y+1 < boardLayout.length) {
            Piece p = boardLayout[X-2][Y+1];
            if (isValid(p)) {
                addAvailPos(X-2, Y+1);
            }
        }

        //X-2 Y-1
        if (X-2 >= 0 && Y-1 >= 0) {
            Piece p = boardLayout[X-2][Y-1];
            if (isValid(p)) {
                addAvailPos(X-2, Y-1);
            }
        }

        //Y+2 X+1
        if (Y+2 < boardLayout.length && X+1 < boardLayout.length) {
            Piece p = boardLayout[X+1][Y+2];
            if (isValid(p)) {
                addAvailPos(X+1, Y+2);
            }
        }

        //Y+2 X-1
        if (Y+2 < boardLayout.length && X-1 >= 0) {
            Piece p = boardLayout[X-1][Y+2];
            if (isValid(p)) {
                addAvailPos(X-1, Y+2);
            }
        }

        //Y-2 X+1
        if (Y-2 >= 0 && X+1 < boardLayout.length) {
            Piece p = boardLayout[X+1][Y-2];
            if (isValid(p)) {
                addAvailPos(X+1, Y-2);
            }
        }

        //Y-2 X-1
        if (Y-2 >= 0 && X-1 >= 0) {
            Piece p = boardLayout[X-1][Y-2];
            if (isValid(p)) {
                addAvailPos(X-1, Y-2);
            }
        }
    }
    @Override
    public Piece clonePiece(){
        Piece newPiece;

        newPiece = new Knight(this.black, this.hasMoved);

        return newPiece;
    }

    private boolean isValid(Piece p) {
        return p == null || (p.black != black);
    }
}
