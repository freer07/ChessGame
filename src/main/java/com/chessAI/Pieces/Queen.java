package com.chessAI.Pieces;

public class Queen extends Piece {
    private static final String abbrev = "Q";
    private static final int value = 90;

    public Queen(boolean b, int x, int y) {
        super(b, x, y, abbrev, value);
    }

    @Override
    public void findAvailPos(Piece[][] boardLayout) {
        super.findAvailPos(boardLayout);

        //Check Angle Moves
        //X+1 Y+1
        for (int i = 1; i < boardLayout.length; i++) {
            if (X+i < boardLayout.length && Y+i < boardLayout.length) {
                Piece pos = boardLayout[X+i][Y+i];
                if (pos == null) { //if empty then valid
                    addAvailPos(X+i, Y+i);
                } else if (pos.black != black) { //contains enemy piece then valid
                    addAvailPos(X+i, Y+i);
                } else { //contains own teammate then stop loop
                    break;
                }
            } else {
                break;
            }
        }

        //X-1 Y-1
        for (int i = 1; i < boardLayout.length; i++) {
            if (X-i >= 0 && Y-i >= 0) {
                Piece pos = boardLayout[X-i][Y-i];
                if (pos == null) { //if empty then valid
                    addAvailPos(X-i, Y-i);
                } else if (pos.black != black) { //contains enemy piece then valid
                    addAvailPos(X-i, Y-i);
                } else { //contains own teammate then stop loop
                    break;
                }
            } else {
                break;
            }
        }

        //X+1 Y-1
        for (int i = 1; i < boardLayout.length; i++) {
            if (X+i < boardLayout.length && Y-i >= 0) {
                Piece pos = boardLayout[X+i][Y-i];
                if (pos == null) { //if empty then valid
                    addAvailPos(X+i, Y-i);
                } else if (pos.black != black) { //contains enemy piece then valid
                    addAvailPos(X+i, Y-i);
                } else { //contains own teammate then stop loop
                    break;
                }
            } else {
                break;
            }
        }

        //X-1 Y+1
        for (int i = 1; i < boardLayout.length; i++) {
            if (X-i >= 0 && Y+i < boardLayout.length) {
                Piece pos = boardLayout[X-i][Y+i];
                if (pos == null) { //if empty then valid
                    addAvailPos(X-i, Y+i);
                } else if (pos.black != black) { //contains enemy piece then valid
                    addAvailPos(X-i, Y+i);
                } else { //contains own teammate then stop loop
                    break;
                }
            } else {
                break;
            }
        }


        //Check straight moves
        //X+1 ... <boardLayout.length
        for (int i = X; i < boardLayout.length; i++) {
            Piece pos = boardLayout[i][Y];
            if (pos == null) { //if empty then valid
                addAvailPos(i, Y);
            } else if (pos.black != black) { //contains enemy piece then valid
                addAvailPos(i, Y);
            } else { //contains own teammate then stop loop
                break;
            }
        }

        //Y+1 ... <boardLayout.length
        for (int i = Y; i < boardLayout.length; i++) {
            Piece pos = boardLayout[X][i];
            if (pos == null) { //if empty then valid
                addAvailPos(X, i);
            } else if (pos.black != black) { //contains enemy piece then valid
                addAvailPos(X, i);
            } else { //contains own teammate then stop loop
                break;
            }
        }

        //X-1 ... <boardLayout.length
        for (int i = X; i >= 0; i--) {
            Piece pos = boardLayout[i][Y];
            if (pos == null) { //if empty then valid
                addAvailPos(i, Y);
            } else if (pos.black != black) { //contains enemy piece then valid
                addAvailPos(i, Y);
            } else { //contains own teammate then stop loop
                break;
            }
        }

        //Y-1 ... <boardLayout.length
        for (int i = Y; i >= 0; i--) {
            Piece pos = boardLayout[X][i];
            if (pos == null) { //if empty then valid
                addAvailPos(X, i);
            } else if (pos.black != black) { //contains enemy piece then valid
                addAvailPos(X, i);
            } else { //contains own teammate then stop loop
                break;
            }
        }
    }
}
