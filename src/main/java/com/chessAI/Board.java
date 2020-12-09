package com.chessAI;
import com.chessAI.Pieces.*;
import javafx.util.Pair;

import java.util.*;

public class Board {
    private Piece[][] board = new Piece[8][8];
    private final ArrayList<Piece> pieces = new ArrayList<>();

    public Board(){
        startBoard();
    }

    public Board(Piece[][] layout) {
        board = layout;
    }

    private void startBoard() {
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
            board[1][i] = new Pawn(false, 1, i);
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
            board[6][i] = new Pawn(true, 6, i);
        }

        //Add all pieces to list
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != null) {
                    pieces.add(board[i][j]);
                }
            }
        }
    }

    public void show() {
        System.out.println("White == Upper Case  Black == Lower Case");
        System.out.println("\t+---+---+---+---+---+---+---+---+");
        for (int i = 0; i < board.length; i++) {
            System.out.print((i+1) + "\t|");
            for (int j = 0; j < board[0].length; j++) {
                Piece p = board[i][j];
                if (p != null) {
                    String s = p.isBlack() ? p.abbrev.toLowerCase() : p.abbrev;
                    System.out.print(" " + s + " |");
                } else {
                    System.out.print("   |");
                }
            }
            System.out.println();
            System.out.println("\t+---+---+---+---+---+---+---+---+");
        }
        System.out.println("\t  A   B   C   D   E   F   G   H");
    }

    public void move(int x1, int y1, int x2, int y2) {
        board[x2][y2] = board[x1][y1];
        board[x1][y1] = null;
        if (!board[x2][y2].hasMoved) {
            board[x2][y2].hasMoved = true;
        }

        board[x2][y2].setCorr(x2, y2);
    }

    public void castle(int rX, int rY, int kX, int kY) {

    }

    public List<Board> getPossibleBoards(boolean blackMove) {
        List<Board> boards = new LinkedList<>();
        findAllMoves();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Piece p = board[i][j];
                if (p != null && p.isBlack() == blackMove) {
                    if (p.getClass().equals(Rook.class)) {
                        Rook rook = (Rook)p;
                        if (rook.canCastle) {
                            //TODO: add logic for castling
                            /*Pair<Integer, Integer> pair = getKing(rook.isBlack());
                            Board newBoard = new Board(getLayoutClone());
                            assert pair != null;
                            newBoard.castle(i, j, pair.getKey(), pair.getValue());
                            boards.add(newBoard);*/
                        }
                    }

                    Vector<Vector<Integer>> availPos = p.availPos;
                    for (Vector<Integer> v : availPos) {
                        int x = v.get(0);
                        int y = v.get(1);
                        Board newBoard = new Board(getLayoutClone());
                        newBoard.move(i, j, x, y);
                        boards.add(newBoard);
                    }
                }
            }
        }

        return boards;
    }

    private void findAllMoves() {
        //pieces.stream().filter(piece -> piece.isActive).forEach(piece -> piece.findAvailPos(board.clone()));
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Piece p = board[i][j];
                if (p != null) {
                    p.findAvailPos(getLayoutClone(), i, j);
                }
            }
        }
    }

    public Piece[][] getLayoutClone() {
        /*Piece[][] copy = Arrays.stream(board).map(Piece[]::clone).toArray(Piece[][]::new);*/

        Piece[][] copy = new Piece[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Piece p = board[i][j];
                if (p != null) {
                    Piece newP;
                    newP = p.clonePiece();
                    copy[i][j] = newP;
                }
            }
        }
        return copy;
    }

    private Pair<Integer, Integer> getKing(boolean b) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Piece p = board[i][j];
                if (p != null && p.getClass().equals(King.class) && p.isBlack() == b) {
                    return new Pair<>(i,j);
                }
            }
        }
        return null;
    }
}
