package com.chessAI;

import com.chessAI.BoardTree.BoardNode;

/*import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication*/
public class ChessAiApplication {
	Board board;
	private static final boolean printAllBoards = true;

	public ChessAiApplication(int depthOfSearch) {
		board = new Board();

		//User is White and Computer is black!!!

		BoardNode treeRoot = buildTree(board, depthOfSearch, true);
		System.out.println("Made Tree!!");
		/*
		System.out.println("---Original---");
		System.out.println();

		BoardNode root = new BoardNode(board);
		System.out.println("Black Val: " + root.getBlackVal() + " White Val: " + root.getWhiteVal());
		System.out.println();


		root.addChildren();
		System.out.println();
		System.out.println("---Children---" + root.getChildren().size());
		root.getChildren().forEach(boardNode -> {
			boardNode.getBoard().show();
			System.out.println("Black Val: " + boardNode.getBlackVal() + " White Val: " + boardNode.getWhiteVal());
			System.out.println();
		});*/
	}

	/**
	 * This method builds the decision tree for the game
	 * @param board the current game layout
	 * @return root of the tree
	 */
	private BoardNode buildTree(Board board, int maxDepth, boolean blackMove) {
		BoardNode root = new BoardNode(board);
		makeChildren(root, 0, maxDepth, blackMove);

		return root;
	}

	private void makeChildren(BoardNode node, int depth, int maxDepth, boolean blackMove) {

		if (depth < maxDepth) {
			node.addChildren(blackMove);

			//print boards of children
			if (printAllBoards) {
				node.getChildren().forEach(boardNode -> {
					boardNode.getBoard().show();
					System.out.println("Black Val: " + boardNode.getBlackVal() + " White Val: " + boardNode.getWhiteVal());
					System.out.println();
				});
			}

			node.getChildren().forEach(boardNode -> makeChildren(boardNode, depth+1, maxDepth, !blackMove));
		}
	}

	/*public static void main(String[] args) {
		SpringApplication.run(ChessAiApplication.class, args);
	}*/

	public static void main(String[] args) {new ChessAiApplication(Integer.parseInt(args[0]));}

}
