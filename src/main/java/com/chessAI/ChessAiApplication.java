package com.chessAI;

import com.chessAI.BoardTree.BoardNode;

import java.util.*;

/*import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication*/
public class ChessAiApplication {
	Board board;
	private static final boolean printAllBoards = false;

	public ChessAiApplication(int depthOfSearch) {
		board = new Board();
		Scanner scanner = new Scanner(System.in);//used for reading the user input

		//User is White and Computer is black!!!
		while (true) {
			board.show();
			System.out.print("Select Piece: ");
			int xStart, yStart, xEnd, yEnd;
			xStart = scanner.nextInt();
			yStart = scanner.next().charAt(0) - 65;
			System.out.print("Select Position: ");
			xEnd = scanner.nextInt();
			yEnd = scanner.next().charAt(0) - 65;
			board.move(xStart - 1, yStart, xEnd - 1, yEnd);
			board.show();

			System.out.println("Making Tree...");
			BoardNode treeRoot = buildTree(board, depthOfSearch);

			board = new Board(treeRoot.bestChild.getBoard().getLayoutClone());
		}

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
	private BoardNode buildTree(Board board, int maxDepth) {
		BoardNode root = new BoardNode(board);

		//Set True because black will move first
		makeChildren(root, 0, maxDepth, true);
		return root;
	}

	private int makeChildren(BoardNode node, int depth, int maxDepth, boolean blackMove) {
		if (depth < maxDepth) {
			node.addChildren(blackMove);

			//print boards of children for testing
			if (printAllBoards) {
				node.getChildren().forEach(boardNode -> {
					boardNode.getBoard().show();
					System.out.println("Black Val: " + boardNode.getBlackVal() + " White Val: " + boardNode.getWhiteVal());
					System.out.println();
				});
			}
			List<Integer> values = new LinkedList<>();
			node.getChildren().forEach(boardNode -> {
				values.add(makeChildren(boardNode, depth+1, maxDepth, !blackMove));
			});
			if (blackMove) {
				node.value = Collections.max(values);
				node.bestChild = node.getChildren().stream().filter(n -> n.value == node.value).findAny().get();
			} else { // it will be white's turn and they will pick the least
				node.value = Collections.min(values);
				node.bestChild = node.getChildren().stream().filter(n -> n.value == node.value).findAny().get();
			}
		} else {
			//We are maximizing the black score since the PC is black
			node.value = node.getBlackVal() - node.getWhiteVal();
		}
		return node.value;
	}

	/*public static void main(String[] args) {
		SpringApplication.run(ChessAiApplication.class, args);
	}*/

	public static void main(String[] args) {new ChessAiApplication(Integer.parseInt(args[0]));}

}
