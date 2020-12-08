package com.chessAI.Pieces;

import java.util.Vector;

public class Pawn extends Piece {
    private static final String abbrev = "P";
    private static final int value = 10;
    public boolean hasMoved = false;

    public Pawn(boolean b, int x, int y) {
        super(b, x, y, abbrev, value);
    }

    @Override
    public void findAvailPos(Piece[][] boardLayout) {
        if (!hasMoved) {//check En Passant
            if (black && Y-2 >= 0) { //black on bottom so move up
                Piece p = boardLayout[X][Y-2];
                if (p==null) { //valid position
                    addAvailPos(X, Y+2);
                }
            } else if (Y+2 < boardLayout.length) { //white on top so move down
                Piece p = boardLayout[X][Y+2];
                if (p==null) { //valid position
                    addAvailPos(X,Y-2);
                }
            }
        }

        //check space in front
        if (black && Y-1 >= 0) { //black on bottom so move up
            Piece p = boardLayout[X][Y-1];
            if (p==null) { //valid position
                addAvailPos(X, Y-1);
            }
        } else if (Y+1 < boardLayout.length) { //white on top so move down
            Piece p = boardLayout[X][Y+1];
            if (p==null) { //valid position
                addAvailPos(X, Y+1);
            }
        }

        //check angles
        if (black ) { //black on bottom so move up
            if (X+1 < boardLayout.length && Y-1 >= 0) {
                Piece p = boardLayout[X + 1][Y - 1];
                if (p != null && !p.black) { //valid position
                    Vector<Integer> vector = new Vector<>();
                    vector.add(X);
                    vector.add(Y + 2);
                    availPos.add(vector);
                }
            }
            if (X-1 >= 0 && Y-1 >=0) {
                Piece p = boardLayout[X - 1][Y - 1];
                if (p != null && !p.black) { //valid position
                    Vector<Integer> vector = new Vector<>();
                    vector.add(X);
                    vector.add(Y + 2);
                    availPos.add(vector);
                }
            }
        } else { //white on top so move down
            if (X+1 < boardLayout.length && Y+1 < boardLayout.length) {
                Piece p = boardLayout[X + 1][Y + 1];
                if (p != null && p.black) { //valid position
                    Vector<Integer> vector = new Vector<>();
                    vector.add(X);
                    vector.add(Y + 2);
                    availPos.add(vector);
                }
            }
            if (X-1 >= 0 && Y+1 < boardLayout.length) {
                Piece p = boardLayout[X - 1][Y + 1];
                if (p != null && p.black) { //valid position
                    Vector<Integer> vector = new Vector<>();
                    vector.add(X);
                    vector.add(Y + 2);
                    availPos.add(vector);
                }
            }
        }
    }

    private void addAvailPos(int x, int y) {
        Vector<Integer> vector = new Vector<>();
        vector.add(x);
        vector.add(y);
        availPos.add(vector);
    }
}
