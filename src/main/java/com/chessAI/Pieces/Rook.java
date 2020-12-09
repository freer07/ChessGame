package com.chessAI.Pieces;

public class Rook extends Piece {
    private static final String abbrev = "R";
    private static final int value = 50;
    public boolean isOriginal = true;
    //public boolean hasMoved = false;
    public boolean canCastle = false;

    public Rook(boolean b, int x, int y) {
        super(b, abbrev, value);
    }

    //This constructor is used when the Rook is not an original piece (castling not valid)
    public Rook(boolean b, int x, int y, boolean o) {
        super(b, abbrev, value);
        isOriginal = o;
    }

    public Rook(Pawn r) {
        super(r.black, abbrev, value, r.hasMoved);
    }

    public Rook(boolean black, boolean hasMoved) {
        super(black, abbrev, value, hasMoved);
    }

    @Override
    public void findAvailPos(Piece[][] boardLayout, int X, int Y) {
        super.findAvailPos(boardLayout, X, Y);
        //Find king for castling
        King k = getKing(boardLayout);

        //if K is null then game should be over...
        boolean origPos = k!=null && !k.hasMoved && !k.inCheck && !hasMoved;
        if (!origPos && canCastle) {
            canCastle = false;
        }

        //X+1 ... <boardLayout.length
        for (int i = X+1; i < boardLayout.length; i++) {
            Piece pos = boardLayout[i][Y];
            if (pos == null) { //if empty then valid
                addAvailPos(i, Y);
            } else if (pos.black != black) { //contains enemy piece then valid
                addAvailPos(i, Y);
                break;
            } else if(origPos && pos.equals(k)) { //contains king then check castling
                canCastle = true;
            } else { //contains own teammate then stop loop
                //canCastle = false;
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
            } else if(origPos && pos.equals(k)) { //contains king then check castling
                canCastle = true;
            } else { //contains own teammate then stop loop
                //canCastle = false;
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
            } else if(origPos && pos.equals(k)) { //contains king then check castling
                canCastle = true;
            } else { //contains own teammate then stop loop
                //canCastle = false;
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
            } else if(origPos && pos.equals(k)) { //contains king then check castling
                canCastle = true;
            } else { //contains own teammate then stop loop
                //canCastle = false;
                break;
            }
        }
    }

    @Override
    public Piece clonePiece(){
        Piece newPiece;

        newPiece = new Rook(this.black, this.hasMoved);

        return newPiece;
    }

    private King getKing(Piece[][] boardLayout) {
        for (Piece[] pieces : boardLayout) {
            for (int j = 0; j < boardLayout[0].length; j++) {
                Piece p = pieces[j];
                if (p != null && p.getClass() == King.class && p.black == black) {
                    return (King) p;
                }
            }
        }
        return null;
    }
}
