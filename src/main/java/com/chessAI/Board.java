package com.chessAI;
import com.chessAI.Pieces.*;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    private Piece[][] board = new Piece[8][8];

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

        //not stalemate
        /*board[7][6] = new Rook(false, 0, 0);
        board[1][0] = new Queen(false, 0, 3);
        board[0][5] = new King(false, 0, 4);
        board[6][7] = new Rook(false, 0, 7);

        board[2][7] = new Knight(true, 7, 1);
        board[0][7] = new King(true, 7, 4);
        board[2][6] = new Bishop(true, 7, 2);*/

        //stalemate
        /*board[2][6] = new Rook(false, 0, 0);
        board[1][0] = new Queen(false, 0, 3);
        board[0][5] = new King(false, 0, 4);
        board[6][7] = new Rook(false, 0, 7);

        board[2][7] = new Knight(true, 7, 1);
        board[0][7] = new King(true, 7, 4);*/

        //Pawn promotion test
        /*board[6][0] = new Pawn(false, 0, 0);
        board[0][5] = new King(false, 0, 4);

        board[0][7] = new King(true, 7, 4);
        board[1][2] = new Pawn(true, 0, 0);*/
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
    }

    public void castle(int rX, int rY, int kX, int kY) {
        assert rX == kX;
        Rook rook = (Rook)board[rX][rY].clonePiece();
        rook.canCastle = false;
        rook.hasMoved = true;
        King king = (King) board[kX][kY].clonePiece();
        king.hasMoved = true;

        board[rX][rY] = null;
        board[kX][kY] = null;
        if (rY < kY) {
            //newRy = kY-1
            //newKy = newRy-1
            //same X for both
            int newRy = kY-1;
            int newKy = newRy-1;
            board[rX][newRy] = rook;
            board[kX][newKy] = king;
        } else {
            //newRy = kY-1
            //newKy = newRy-1
            //same X for both
            int newRy = kY+1;
            int newKy = newRy+1;
            board[rX][newRy] = rook;
            board[kX][newKy] = king;
        }
    }

    public List<Board> getPossibleBoards(boolean blackMove) {
        List<Board> boards = new LinkedList<>();
        Piece[][] clonedLayout = getLayoutClone();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Piece p = board[i][j];
                if (p != null && p.isBlack() == blackMove) {
                    p.findAvailPos(clonedLayout, i, j);
                    if (p.getClass().equals(Rook.class)) {
                        Rook rook = (Rook)p;
                        if (rook.canCastle) {
                            Pair<Integer, Integer> pair = getKing(rook.isBlack());
                            Board newBoard = new Board(getLayoutClone());
                            assert pair != null;
                            newBoard.castle(i, j, pair.getKey(), pair.getValue());
                            boards.add(newBoard);
                        }
                    }

                    Vector<Vector<Integer>> availPos = p.availPos;
                    for (Vector<Integer> v : availPos) {
                        int x = v.get(0);
                        int y = v.get(1);

                        Board newBoard = new Board(getLayoutClone());
                        if (p.getClass().equals(Pawn.class) && (x==0 || x == board.length-1)) {
                            newBoard.promotePawn(i, j, x, y, 'Q');
                        } else {
                            newBoard.move(i, j, x, y);
                        }
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

    public Pair<Integer, Integer> getKing(boolean b) {
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
    public boolean containsKing(boolean b){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Piece p = board[i][j];
                if (p != null && p.getClass().equals(King.class) && p.isBlack() == b) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCheck(boolean blackMove){
        List<Board> possibleBoards =getPossibleBoards(!blackMove);

        List<Board> checks = possibleBoards.stream().filter(board -> !board.containsKing(blackMove)).collect(Collectors.toList());

        return !checks.isEmpty();
    }

    public boolean checkMate(boolean blackMove){
        if (containsKing(blackMove) && isCheck(blackMove)) {
            Pair<Integer, Integer> kingPos = getKing(blackMove);
            King k = (King) board[kingPos.getKey()][kingPos.getValue()];
            k.findAvailPos(getLayoutClone(),kingPos.getKey(), kingPos.getValue());
            Vector<Vector<Integer>> availPos = k.availPos;
            for (Vector<Integer> v : availPos) {
                int x = v.get(0);
                int y = v.get(1);
                Board newBoard = new Board(getLayoutClone());
                newBoard.move(kingPos.getKey(), kingPos.getValue(), x, y);
                if(!newBoard.isCheck(blackMove)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public boolean staleMate(boolean blackMove) {
        if (isCheck(blackMove)) {
            return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Piece p = board[i][j];
                if (p != null && p.isBlack() == blackMove) {
                    p.findAvailPos(getLayoutClone(), i, j);
                    Vector<Vector<Integer>> availPos = p.availPos;
                    List<Board> boards = new LinkedList<>();
                    for (Vector<Integer> v : availPos) {
                        int x = v.get(0);
                        int y = v.get(1);
                        Board newBoard = new Board(getLayoutClone());
                        newBoard.move(i, j, x, y);
                        boards.add(newBoard);
                    }
                    for (Board b : boards) {
                        if (!b.isCheck(blackMove)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Selection is the version to promote the pawn to
     */
    public void promotePawn(int x1, int y1, int x2, int y2, char selection) {
        Piece p = board[x1][y1];
        switch (selection) {
            case 'R'://rook
                p = new Rook(p.isBlack(), true);
                break;
            case 'B'://bishop
                p = new Bishop(p.isBlack(), true);
                break;
            case 'N'://knight
                p = new Knight(p.isBlack(), true);
                break;
            default://queen
                p = new Queen(p.isBlack(), true);
                break;
        }
        board[x2][y2] = p;
        board[x1][y1] = null;
        if (!board[x2][y2].hasMoved) {
            board[x2][y2].hasMoved = true;
        }
    }
}
