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
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Board playMove(Board b) {
		System.out.println(this.name+" is playing...");
		try {
			Robot r1 = new Robot(); r1.delay(1000);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
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
		// TODO Auto-generated method stub
		this.symbol = input;
	}

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
