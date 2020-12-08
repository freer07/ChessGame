package com.chessAI;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class ChessAiApplication {
	Board board;

	public ChessAiApplication(){
	board = new Board();
	board.show(a,1);
	}

	/*public static void main(String[] args) {
		SpringApplication.run(ChessAiApplication.class, args);
	}*/
	public static void main(String[] args) { ChessAiApplication s = new ChessAiApplication(); }


}
