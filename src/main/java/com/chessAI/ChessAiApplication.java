package com.chessAI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChessAiApplication {
	Board board;

	public ChessAiApplication() {
		board = new Board();
		board.show();
		board.move(1,2,2,2);
		board.show();
	}

	public static void main(String[] args) {
		SpringApplication.run(ChessAiApplication.class, args);
	}

}
