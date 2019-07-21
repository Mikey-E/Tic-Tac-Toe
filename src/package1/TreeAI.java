package package1;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Random;

public class TreeAI implements Player{
	
	private Random gen;
	private char symbol;
	private String name;
	
	public TreeAI(){
		this.gen = new Random();
	}

	@Override
	public boolean isAI() {
		return true;
	}

	private Board[] getSuccessors(Board b, char c){
		int openSpaces = 0;
		ArrayList<Integer> openIndexes = new ArrayList<Integer>();
		for (int i = 1; i <= 9; i++){
			if (b.getChar(i)==Driver.emptySpace){
				openSpaces++;
				openIndexes.add(i);
			}
		}
		Board[] bArray = new Board[openSpaces];
		for (Integer j: openIndexes){
			Board newB = new Board();
			b.copyBoard(newB);
			newB.setChar(c, j);
			bArray[openSpaces-1] = newB;
			openSpaces--;
		}
		return bArray;
	}
	
	private int score(Board b, char nextPlayer, char notNextPlayer, int offset){
		if (Driver.whoWon(b)==symbol){return 100 - offset;}//100 is arbitrary
		if (Driver.whoWon(b)==Driver.tieChar){return 0;}
		if (Driver.whoWon(b)==Driver.emptySpace){
			Board[] bArray = getSuccessors(b, nextPlayer);
			int value = 0;
			if (nextPlayer==symbol){value = -200;}
			if (notNextPlayer==symbol){value = 200;}
			int boardScore;//from score
			for (int i = 0; i < bArray.length; i++){
				boardScore = score(bArray[i], notNextPlayer, nextPlayer, offset+1);
				if (nextPlayer==symbol){
					if (boardScore>value){
						value = boardScore;
					}
				}
				if (notNextPlayer==symbol){
					if (boardScore<value){
						value = boardScore;
					}
				}
				
			}
			return value;
		}else{
			return -100 + offset;//-100 arbitrary, opponent won
		}
	}
	
	private int discoverMovedSpot(Board oldBoard, Board newBoard){
		for (int i = 1; i <= 9; i++){
			if (oldBoard.getChar(i)!=newBoard.getChar(i)){
				return i;
			}
		}
		System.out.println("attempting to discover moved spot from same boards");
		return 0; //should never be reached.
	}
	
	private int optimalSpot(Board b){
		char opponentChar = symbol;
		for (int i = 1; i <= 9; i++){
			if ((b.getChar(i)!=symbol)&&(b.getChar(i)!=Driver.emptySpace)){
				opponentChar = b.getChar(i);
				break;
			}
		}
		if (opponentChar == symbol){return 5;}
		Board[] bArray = getSuccessors(b, symbol);
		int[] scores = new int[bArray.length];
		int index = 0;
		for (Board board: bArray){
			scores[index] = score(board, opponentChar, symbol, 0);
			index++;
		}
		int max = -1000;//arbitrary, should be negative enough
		int maxIndex = 0;
		for (int j = 0; j < scores.length; j++){
			if (scores[j] > max){
				max = scores[j];
				maxIndex = j;
			}
		}
		return discoverMovedSpot(b, bArray[maxIndex]);
	}
	@Override
	public Board playMove(Board b) {
		System.out.println(this.name+" is playing...");
		try {
			Robot r1 = new Robot(); r1.delay(1000);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		b.setChar(symbol, optimalSpot(b));
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
