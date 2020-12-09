package com.chessAI.Pieces;

import java.util.Vector;

public class Piece {
    boolean black;
    public boolean isActive;
    public boolean hasMoved = false;
    public String abbrev;
    int value;
    public Vector<Vector<Integer>> availPos = new Vector<>();

    public Piece(boolean b, String a, int v) {
        black = b;
        isActive = true;
        abbrev = a;
        value = v;
    }

    public Piece(boolean b, String a, int v, boolean hM) {
        black = b;
        isActive = true;
        abbrev = a;
        value = v;
        hasMoved = hM;
    }

    public void findAvailPos(Piece[][] boardLayout, int X, int Y) {
        //...Do Stuff//
        availPos.clear();
    }

    void addAvailPos(int x, int y) {
        Vector<Integer> vector = new Vector<>();
        vector.add(x);
        vector.add(y);
        availPos.add(vector);
    }

    public boolean isBlack() {
        return black;
    }

    public int getValue() {
        return value;
    }

    public void setCorr(int x, int y) {
    }

}
