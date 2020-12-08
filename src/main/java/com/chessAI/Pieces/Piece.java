package com.chessAI.Pieces;

import java.util.Vector;

public class Piece {
    boolean black;
    int X;
    int Y;
    public boolean isActive;
    public String abbrev;
    public int value;
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
    }
}
