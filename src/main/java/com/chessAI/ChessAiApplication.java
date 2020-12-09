package com.chessAI;

import com.chessAI.BoardTree.BoardNode;
import com.chessAI.Pieces.Piece;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
			int xStart, yStart, xEnd, yEnd;
			board.show();
			while (true) {
				System.out.print("Select Piece: ");
				xStart = scanner.nextInt() - 1;
				yStart = scanner.next().charAt(0) - 65;
				Piece p = board.getLayoutClone()[xStart][yStart];
				if (p != null) {
					System.out.print("Select Position: ");
					xEnd = scanner.nextInt() - 1;
					yEnd = scanner.next().charAt(0) - 65;
					p.findAvailPos(board.getLayoutClone(), xStart, yStart);
					//Check if the end position is valid
					if (isValid(p.availPos, xEnd, yEnd)) {
						break;
					} else {
						System.out.println("Invalid Position\n");
					}
				} else {
					System.out.println("Invalid Selection\n");
				}
			}

			board.move(xStart, yStart, xEnd, yEnd);
			board.show();

			System.out.println("Making Tree...");
			BoardNode treeRoot = buildTree(new Board(board.getLayoutClone()), depthOfSearch);

			board = new Board(getBestChild(treeRoot).getBoard().getLayoutClone());
		}
	}

	/**
	 * This method builds the decision tree for the game
	 * @param board the current game layout
	 * @return root of the tree
	 */
	private BoardNode buildTree(Board board, int maxDepth) {
		BoardNode root = new BoardNode(board);

		//Set True because black will move first
		makeChildren(root, 0, maxDepth, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
		return root;
	}

	private int makeChildren(BoardNode node, int depth, int maxDepth, boolean blackMove, int alpha, int beta) {
		if (depth < maxDepth) {
			// WE KNOW THIS WORKS
			node.addChildren(blackMove);
			List<BoardNode> children = node.getChildren();
			if (blackMove) {
				int maxEval = Integer.MIN_VALUE;
				for (BoardNode n : children) {
					int eval = makeChildren(n, depth + 1, maxDepth, !blackMove, alpha, beta);
					maxEval = Integer.max(maxEval, eval);
					alpha = Integer.max(alpha, eval);
					if (beta <= alpha) {
						break;
					}
				}
				node.value = maxEval;
				return maxEval;
			} else {
				int minEval = Integer.MAX_VALUE;
				for (BoardNode n : children) {
					int eval = makeChildren(n, depth + 1, maxDepth, !blackMove, alpha, beta);
					minEval = Integer.min(minEval, eval);
					beta = Integer.min(beta, eval);
					if (beta <= alpha) {
						break;
					}
				}
				node.value = minEval;
				return minEval;
			}
		} else {
			//We are maximizing the black score since the PC is black
			node.value = node.getBlackVal() - node.getWhiteVal();
		}
		return node.value;
	}

	private BoardNode getBestChild(BoardNode root) {
		List<BoardNode> children = root.getChildren();
		BoardNode bestChild = null;
		int bestValue = Integer.MIN_VALUE;

		for (BoardNode node : children) {
			if (node.value > bestValue) {
				bestValue = node.value;
				bestChild = node;
			}
		}
		return bestChild;
	}

	private boolean isValid(Vector<Vector<Integer>> positions, int x, int y) {
		for (Vector<Integer> v : positions) {
			if (v.get(0) == x && v.get(1) == y) {
				return true;
			}
		}
		return false;
	}

	/*public static void main(String[] args) {
		SpringApplication.run(ChessAiApplication.class, args);
	}*/

	public static void main(String[] args) {new ChessAiApplication(Integer.parseInt(args[0]));}

}
