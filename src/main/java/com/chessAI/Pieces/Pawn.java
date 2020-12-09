package com.chessAI.Pieces;

public class Pawn extends Piece {
    private static final String abbrev = "P";
    private static final int value = 10;
    //public boolean hasMoved = false;

    public Pawn(boolean b, int x, int y) {
        super(b, abbrev, value);
    }

    public Pawn(boolean b, boolean hM) {
        super(b, abbrev, value, hM);
    }

    @Override
    public void findAvailPos(Piece[][] boardLayout, int X, int Y) {
        super.findAvailPos(boardLayout, X, Y);
        if (!hasMoved) {//check En Passant
            if (this.black && X -2 >= 0) { //black on bottom so move up
                Piece p = boardLayout[X -2][Y];
                if (p==null && boardLayout[X-1][Y] == null) { //valid position
                    addAvailPos(X -2, Y);
                }
            } else if (X +2 < boardLayout.length) { //white on top so move down
                Piece p = boardLayout[X +2][Y];
                if (p==null && boardLayout[X+1][Y] == null) { //valid position
                    addAvailPos(X +2, Y);
                }
            }
        }

        //check space in front
        if (this.black && X -1 >= 0) { //black on bottom so move up
            Piece p = boardLayout[X -1][Y];
            if (p==null) { //valid position
                addAvailPos(X -1, Y);
            }
        } else if (!black && X +1 < boardLayout.length) { //white on top so move down
            Piece p = boardLayout[X +1][Y];
            if (p==null) { //valid position
                addAvailPos(X +1, Y);
            }
        }

        //check angles
        if (this.black) { //black on bottom so move up
            if (X -1 >= 0 && Y -1 >= 0) {
                Piece p = boardLayout[X - 1][Y - 1];
                if (p != null && !p.black) { //valid position
                    addAvailPos(X -1, Y -1);
                }
            }
            if (X -1 >= 0 && Y +1 < boardLayout.length) {
                Piece p = boardLayout[X - 1][Y + 1];
                if (p != null && !p.black) { //valid position
                    addAvailPos(X -1, Y +1);
                }
            }
        } else { //white on top so move down
            if (X +1 < boardLayout.length && Y +1 < boardLayout.length) {
                Piece p = boardLayout[X + 1][Y + 1];
                if (p != null && p.black) { //valid position
                    addAvailPos(X +1, Y +1);
                }
            }
            if (X +1 < boardLayout.length && Y -1 >= 0) {
                Piece p = boardLayout[X + 1][Y - 1];
                if (p != null && p.black) { //valid position
                    addAvailPos(X +1, Y -1);
                }
            }
        }
    }
    @Override
    public Piece clonePiece(){
        Piece newPiece;

        newPiece = new Pawn(this.black, this.hasMoved);

        return newPiece;
    }
}
