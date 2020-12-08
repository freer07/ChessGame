package com.chessAI.BoardTree;

import com.chessAI.Board;
import com.chessAI.Pieces.Piece;

import java.util.LinkedList;
import java.util.List;

public class BoardNode {
    Board board;
    int whiteVal = 0;
    int blackVal = 0;
    BoardNode parent;
    List<BoardNode> children = new LinkedList<>();

    public BoardNode(Board b) {
        this(b,null);
    }

    public BoardNode(Board b, BoardNode p) {
        board = b;
        parent = p;
        findValues();
    }

    private void findValues() {
        Piece[][] layout = board.getLayoutClone();
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[0].length; j++) {
                Piece p = layout[i][j];
                if (p != null) {
                    if (p.isBlack()) {
                        blackVal = blackVal + p.getValue();
                    } else {
                        whiteVal = whiteVal + p.getValue();
                    }
                }
            }
        }
    }

    public void addChildren(boolean blackMove) {
        List<Board> boards = board.getPossibleBoards(blackMove);
        boards.forEach(b -> children.add(new BoardNode(b, this)));
    }

    public int getWhiteVal() {
        return whiteVal;
    }

    public int getBlackVal() {
        return blackVal;
    }

    public List<BoardNode> getChildren() {
        return children;
    }

    public Board getBoard() {
        return board;
    }
}
