package com.chessAI;
import com.chessAI.Pieces.*;

import java.util.ArrayList;

public class Board {
    private static Piece[][] board = new Piece[8][8];
    public Board(){
        startBoard();
    }
    public void startBoard() {
        //White Pieces Row 0
        board[0][0] = new Rook(false, 0, 0);
        board[0][1] = new Knight(false, 0, 1);
        board[0][2] = new Bishop(false, 0, 2);
        board[0][3] = new Queen(false, 0, 3);
        board[0][4] = new King(false, 0, 4);
        board[0][5] = new Bishop(false, 0, 5);
        board[0][6] = new Knight(false, 0, 6);
        board[0][7] = new Rook(false, 0, 7);
        //White Pieces Row 1
        for(int i = 0; i<8; i++) {
            board[1][i] = new Pawn(false, 0, i);
        }
        //Black Pieces Row 0
        board[7][0] = new Rook(true, 7, 0);
        board[7][1] = new Knight(true, 7, 1);
        board[7][2] = new Bishop(true, 7, 2);
        board[7][3] = new Queen(true, 7, 3);
        board[7][4] = new King(true, 7, 4);
        board[7][5] = new Bishop(true, 7, 5);
        board[7][6] = new Knight(true, 7, 6);
        board[7][7] = new Rook(true, 7, 7);
        //Black Pieces Row 1
        for(int i = 0; i<8; i++) {
            board[6][i] = new Pawn(false, 0, i);
        }
    }
    public void show() {
        System.out.println("+---+---+---+---+---+---+---+---+");
        System.out.print("|");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Piece p = board[i][j];
                if (p != null) {
                    System.out.print(" " + p.abbrev + " |");
                } else {
                    System.out.print("   |");
                }
            }
            System.out.println();
            System.out.println("+---+---+---+---+---+---+---+---+");
        }
    }
}
