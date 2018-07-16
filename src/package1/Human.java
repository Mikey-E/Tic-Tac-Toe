package package1;

import java.util.Scanner;

public class Human implements Player{
	
	private Scanner scanner;
	private char symbol;
	private String name;
	
	public Human(){
		this.scanner = new Scanner(System.in);
	}

	@Override
	public boolean isAI() {
		return false;
	}

	@Override
	public Board playMove(Board b) {
//		b.printBoard();
		System.out.println("What spot would you like to play? (1-9)");
		int moveNumber = scanner.nextInt();
		while ((moveNumber > 9)||(moveNumber < 1)||(b.getChar(moveNumber)!=Driver.emptySpace)){
			System.out.println("you need to play a number from 1-9, and it must be an open space.");
			moveNumber = scanner.nextInt();
		}
		b.setChar(symbol, moveNumber);
		return b;
	}

	@Override
	public char getSymbol() {
		return this.symbol;
	}
	
	public void setSymbol(char input){this.symbol = input;}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public void setName(String input) {
		// TODO Auto-generated method stub
		this.name = input;
	}
	
}
