package com.chessAI.Pieces;

public class King extends Piece {
    private static final String abbrev = "K";
    private static final int value = 1000000;
    public boolean hasMoved = false;
    public boolean inCheck = false;

    public King(boolean b, int x, int y) {
        super(b, x, y, abbrev, value);
    }

    @Override
    public void findAvailPos(Piece[][] boardLayout) {
        super.findAvailPos(boardLayout);

        //X+1 Y+1
        if (X+1 < boardLayout.length && Y+1 < boardLayout.length) {
            Piece pos = boardLayout[X+1][Y+1];
            if (pos == null || pos.black != black) { //if empty or enemy then valid
                addAvailPos(X+1, Y+1);
            }
        }

        //X-1 Y-1
        if (X-1 >= 0 && Y-1 >= 0) {
            Piece pos = boardLayout[X-1][Y-1];
            if (pos == null || pos.black != black) { //if empty or enemy then valid
                addAvailPos(X-1, Y-1);
            }
        }

        //X Y+1
        if (Y+1 < boardLayout.length) {
            Piece pos = boardLayout[X][Y+1];
            if (pos == null || pos.black != black) { //if empty or enemy then valid
                addAvailPos(X, Y+1);
            }
        }

        //X+1 Y
        if (X+1 < boardLayout.length) {
            Piece pos = boardLayout[X+1][Y];
            if (pos == null || pos.black != black) { //if empty or enemy then valid
                addAvailPos(X+1, Y);
            }
        }

        //X Y-1
        if (Y-1 >= 0) {
            Piece pos = boardLayout[X][Y-1];
            if (pos == null || pos.black != black) { //if empty or enemy then valid
                addAvailPos(X, Y-1);
            }
        }

        //X-1 Y
        if (X-1 >= 0) {
            Piece pos = boardLayout[X-1][Y];
            if (pos == null || pos.black != black) { //if empty or enemy then valid
                addAvailPos(X-1, Y);
            }
        }

        //X-1 Y+1
        if (X-1 >= 0 && Y+1 < boardLayout.length) {
            Piece pos = boardLayout[X-1][Y+1];
            if (pos == null || pos.black != black) { //if empty or enemy then valid
                addAvailPos(X-1, Y+1);
            }
        }

        //X+1 Y-1
        if (Y-1 >= 0 && X+1 < boardLayout.length) {
            Piece pos = boardLayout[X+1][Y-1];
            if (pos == null || pos.black != black) { //if empty or enemy then valid
                addAvailPos(X+1, Y-1);
            }
        }
    }
}
