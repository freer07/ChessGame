package com.chessAI.Pieces;

import java.util.Vector;

public class Piece {
    boolean black;
    int X;
    int Y;
    public boolean isActive;
    public String abbrev;
    int value;
    public Vector<Vector<Integer>> availPos = new Vector<>();

    public Piece(boolean b, int x, int y, String a, int v) {
        black = b;
        X = x;
        Y = y;
        isActive = true;
        abbrev = a;
        value = v;
    }

    public void findAvailPos(Piece[][] boardLayout) {
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
        X = x;
        Y = y;
    }
}
