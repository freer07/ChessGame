package com.chessAI;

import com.chessAI.BoardTree.BoardNode;

/*import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication*/
public class ChessAiApplication {
	Board board;

	public ChessAiApplication() {
		board = new Board();
		System.out.println("---Original---");
		System.out.println();


		BoardNode root = new BoardNode(board);
		System.out.println("Black Val: " + root.getBlackVal() + " White Val: " + root.getWhiteVal());
		System.out.println();


		root.addChildren();
		System.out.println();
		System.out.println("---Children---");
		root.getChildren().forEach(boardNode -> {
			boardNode.getBoard().show();
			System.out.println("Black Val: " + boardNode.getBlackVal() + " White Val: " + boardNode.getWhiteVal());
			System.out.println();
		});

	}

	/*public static void main(String[] args) {
		SpringApplication.run(ChessAiApplication.class, args);
	}*/

	public static void main(String[] args) {ChessAiApplication chessAiApplication = new ChessAiApplication();}

}
