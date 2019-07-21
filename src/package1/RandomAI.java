package package1;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Random;

public class RandomAI implements Player{
	
	private Random gen;
	private char symbol;
	private String name;
	
	public RandomAI(){
		this.gen = new Random();
	}

	@Override
	public boolean isAI() {
		return true;
	}

	@Override
	public Board playMove(Board b) {
		System.out.println(this.name+" is playing...");
		try {
			Robot r1 = new Robot(); r1.delay(1000);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		int moveNum = 1+gen.nextInt(9);
		while (b.getChar(moveNum)!=Driver.emptySpace){
			moveNum = 1+gen.nextInt(9);
		}
		b.setChar(symbol, moveNum);
		return b;
	}

	@Override
	public char getSymbol() {
		return symbol;
	}

	@Override
	public void setSymbol(char input) {
		this.symbol = input;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String input) {
		this.name = input;
	}
}
