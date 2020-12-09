package com.chessAI.Pieces;

public class Bishop extends Piece {
    private static final String abbrev = "B";
    private static final int value = 30;

    public Bishop(boolean b, int x, int y) {
        super(b, abbrev, value);
    }

    public Bishop(boolean black, boolean hasMoved) {
        super(black, abbrev, value, hasMoved);
    }

    @Override
    public void findAvailPos(Piece[][] boardLayout, int X, int Y) {
        super.findAvailPos(boardLayout, X, Y);

        //X+1 Y+1
        for (int i = 1; i < boardLayout.length; i++) {
            if (X +i < boardLayout.length && Y +i < boardLayout.length) {
                Piece pos = boardLayout[X +i][Y +i];
                if (pos == null) { //if empty then valid
                    addAvailPos(X +i, Y +i);
                } else if (pos.black != black) { //contains enemy piece then valid
                    addAvailPos(X +i, Y +i);
                    break;
                } else { //contains own teammate then stop loop
                    break;
                }
            } else {
                break;
            }
        }

        //X-1 Y-1
        for (int i = 1; i < boardLayout.length; i++) {
            if (X -i >= 0 && Y -i >= 0) {
                Piece pos = boardLayout[X -i][Y -i];
                if (pos == null) { //if empty then valid
                    addAvailPos(X -i, Y -i);
                } else if (pos.black != black) { //contains enemy piece then valid
                    addAvailPos(X -i, Y -i);
                    break;
                } else { //contains own teammate then stop loop
                    break;
                }
            } else {
                break;
            }
        }

        //X+1 Y-1
        for (int i = 1; i < boardLayout.length; i++) {
            if (X +i < boardLayout.length && Y -i >= 0) {
                Piece pos = boardLayout[X +i][Y -i];
                if (pos == null) { //if empty then valid
                    addAvailPos(X +i, Y -i);
                } else if (pos.black != black) { //contains enemy piece then valid
                    addAvailPos(X +i, Y -i);
                    break;
                } else { //contains own teammate then stop loop
                    break;
                }
            } else {
                break;
            }
        }

        //X-1 Y+1
        for (int i = 1; i < boardLayout.length; i++) {
            if (X -i >= 0 && Y +i < boardLayout.length) {
                Piece pos = boardLayout[X -i][Y +i];
                if (pos == null) { //if empty then valid
                    addAvailPos(X -i, Y +i);
                } else if (pos.black != black) { //contains enemy piece then valid
                    addAvailPos(X -i, Y +i);
                    break;
                } else { //contains own teammate then stop loop
                    break;
                }
            } else {
                break;
            }
        }
    }
}
