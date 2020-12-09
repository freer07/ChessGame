package com.chessAI.Pieces;

public class Queen extends Piece {
    private static final String abbrev = "Q";
    private static final int value = 90;

    public Queen(boolean b, int x, int y) {
        super(b, abbrev, value);
    }

    public Queen(boolean black, boolean hasMoved) {
        super(black, abbrev, value, hasMoved);
    }

    @Override
    public void findAvailPos(Piece[][] boardLayout, int X, int Y) {
        super.findAvailPos(boardLayout, X, Y);

        //Check Angle Moves
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


        //Check straight moves
        //X+1 ... <boardLayout.length
        for (int i = X+1; i < boardLayout.length; i++) {
            Piece pos = boardLayout[i][Y];
            if (pos == null) { //if empty then valid
                addAvailPos(i, Y);
            } else if (pos.black != black) { //contains enemy piece then valid
                addAvailPos(i, Y);
                break;
            } else { //contains own teammate then stop loop
                break;
            }
        }

        //Y+1 ... <boardLayout.length
        for (int i = Y+1; i < boardLayout.length; i++) {
            Piece pos = boardLayout[X][i];
            if (pos == null) { //if empty then valid
                addAvailPos(X, i);
            } else if (pos.black != black) { //contains enemy piece then valid
                addAvailPos(X, i);
                break;
            } else { //contains own teammate then stop loop
                break;
            }
        }

        //X-1 ... <boardLayout.length
        for (int i = X-1; i >= 0; i--) {
            Piece pos = boardLayout[i][Y];
            if (pos == null) { //if empty then valid
                addAvailPos(i, Y);
            } else if (pos.black != black) { //contains enemy piece then valid
                addAvailPos(i, Y);
                break;
            } else { //contains own teammate then stop loop
                break;
            }
        }

        //Y-1 ... <boardLayout.length
        for (int i = Y-1; i >= 0; i--) {
            Piece pos = boardLayout[X][i];
            if (pos == null) { //if empty then valid
                addAvailPos(X, i);
            } else if (pos.black != black) { //contains enemy piece then valid
                addAvailPos(X, i);
                break;
            } else { //contains own teammate then stop loop
                break;
            }
        }
    }
    @Override
    public Piece clonePiece(){
        Piece newPiece;

        newPiece = new Queen(this.black, this.hasMoved);

        return newPiece;
    }
}
