package com.chessAI;
import com.chessAI.Pieces.*;

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
            board[0][8+i] = new Pawn(false, 0, 8+i);
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
    }
    public show (char x, int y){
        ArrayList<Piece> tile = new ArrayList<Piece>;
        prettyPrint()
    }
    public prettyPrint
}
