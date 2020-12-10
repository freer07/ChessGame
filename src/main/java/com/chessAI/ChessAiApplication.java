package com.chessAI;

import com.chessAI.BoardTree.BoardNode;
import com.chessAI.Pieces.Pawn;
import com.chessAI.Pieces.Piece;
import com.chessAI.Pieces.Rook;
import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/*import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication*/
public class ChessAiApplication {
	Board board;
	private static final int depthOfSearch = 4;

	public ChessAiApplication() {
		board = new Board();
		Scanner scanner = new Scanner(System.in);//used for reading the user input

		//User is White and Computer is black!!!
		while (true) {
			int xStart, yStart, xEnd, yEnd;
			boolean validCastle = false;
			boolean pawnPromoted = false;
			board.show();

			if (!board.containsKing(false)) {
				System.out.println("You Lose");
				break;
			} else if (board.checkMate(false)) {
				System.out.println("You're in Checkmate");
				break;
			} if (board.staleMate(false) || board.staleMate(true)) {
				System.out.println("Stalemate!");
				break;
			} else if (board.isCheck(false)) {
				System.out.println("You're in check");
			}

			while (true) {
				System.out.print("Select Piece: ");
				xStart = scanner.nextInt() - 1;
				yStart = scanner.next().charAt(0) - 65;
				Piece[][] layoutClone = board.getLayoutClone();
				Piece p = layoutClone[xStart][yStart];
				if (p != null && !p.isBlack()) {
					System.out.print("Select Position: ");
					xEnd = scanner.nextInt() - 1;
					yEnd = scanner.next().charAt(0) - 65;
					p.findAvailPos(layoutClone, xStart, yStart);

					//Check if the end position is valid
					if (p.getClass() == Rook.class && ((Rook) p).canCastle) {
						Pair<Integer, Integer> pair = board.getKing(p.isBlack());
						int kX, kY;
						kX = pair.getKey();
						kY = pair.getValue();

						//valid castling
						if (xEnd == kX && yEnd == kY) {
							validCastle = true;
							break;
						}
					} else if (isValid(p.availPos, xEnd, yEnd)) {
						if (p.getClass() == Pawn.class && xEnd == layoutClone.length-1) {
							char c;
							System.out.print("Promote Pawn (enter letter ex. Q): ");
							c = scanner.next().charAt(0);

							//do Pawn promotion
							board.promotePawn(xStart, yStart, xEnd, yEnd, c);
							pawnPromoted = true;
						}
						break;
					} else {
						System.out.println("Invalid Position\n");
					}
				} else {
					System.out.println("Invalid Selection\n");
				}
			}

			if (validCastle) {
				board.castle(xStart, yStart, xEnd, yEnd);
			} else if(!pawnPromoted) {
				board.move(xStart, yStart, xEnd, yEnd);
			}
			board.show();


			if (!board.containsKing(true)) {
				System.out.println("You Win!");
				break;
			} else if(board.checkMate(true)) {
				System.out.println("Computer in Checkmate");
				break;
			} else if (board.staleMate(true) || board.staleMate(false)) {
				System.out.println("Stalemate!");
				break;
			} else if (board.isCheck(true)){
				System.out.println("Computer is in check");
			}

			System.out.println("Making Tree...");
			BoardNode treeRoot = buildTree(new Board(board.getLayoutClone()), depthOfSearch);

			board = new Board(getBestChild(treeRoot).getBoard().getLayoutClone());
		}
		scanner.close();
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
			List<BoardNode> iterated = new LinkedList<>();
			if (blackMove) {
				int maxEval = Integer.MIN_VALUE;
				for (BoardNode n : children) {
					int eval = makeChildren(n, depth + 1, maxDepth, !blackMove, alpha, beta);
					maxEval = Integer.max(maxEval, eval);
					alpha = Integer.max(alpha, eval);
					iterated.add(n);
					if (beta <= alpha) {
						children.removeIf(c -> !iterated.contains(c));
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
					iterated.add(n);
					if (beta <= alpha) {
						children.removeIf(c -> !iterated.contains(c));
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

	public static void main(String[] args) {new ChessAiApplication();}

}
