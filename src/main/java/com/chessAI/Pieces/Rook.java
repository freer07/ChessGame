package com.chessAI.Pieces;

public class Rook extends Piece {
    private static final String abbrev = "R";
    private static final int value = 50;
    public boolean isOriginal = true;
    public boolean hasMoved = false;
    public boolean canCastle = true;

    public Rook(boolean b, int x, int y) {
        super(b, x, y, abbrev, value);
    }

    //This constructor is used when the Rook is not an original piece (castling not valid)
    public Rook(boolean b, int x, int y, boolean o) {
        super(b, x, y, abbrev, value);
        isOriginal = o;
    }

    @Override
    public void findAvailPos(Piece[][] boardLayout) {
        super.findAvailPos(boardLayout);
        //Find king for castling
        King k = getKing(boardLayout);

        //if K is null then game should be over...
        boolean origPos = k!=null && !k.hasMoved && !k.inCheck && !hasMoved;

        //X+1 ... <boardLayout.length
        for (int i = X; i < boardLayout.length; i++) {
            Piece pos = boardLayout[i][Y];
            if (pos == null) { //if empty then valid
                addAvailPos(i, Y);
            } else if (pos.black != black) { //contains enemy piece then valid
                addAvailPos(i, Y);
            } else if(origPos && pos.equals(k)) { //contains king then check castling
                canCastle = true;
            } else { //contains own teammate then stop loop
                canCastle = false;
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
            } else if(origPos && pos.equals(k)) { //contains king then check castling
                canCastle = true;
            } else { //contains own teammate then stop loop
                canCastle = false;
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
            } else if(origPos && pos.equals(k)) { //contains king then check castling
                canCastle = true;
            } else { //contains own teammate then stop loop
                canCastle = false;
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
            } else if(origPos && pos.equals(k)) { //contains king then check castling
                canCastle = true;
            } else { //contains own teammate then stop loop
                canCastle = false;
                break;
            }
        }
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
