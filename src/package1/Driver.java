package package1;

import java.util.Random;

public class Driver {
	
	public static final char emptySpace = '*';
	public static final char tieChar = '~';
	
	//return the char that won, or emptySpace if game not done yet, or tieChar if tie
	public static char whoWon(Board board){
		char[] b = board.getBoard();
		//horizontal check
		if ((b[0]==b[1])&&(b[1]==b[2])&&(b[2]!=emptySpace)){return b[2];}
		if ((b[3]==b[4])&&(b[4]==b[5])&&(b[5]!=emptySpace)){return b[5];}
		if ((b[6]==b[7])&&(b[7]==b[8])&&(b[8]!=emptySpace)){return b[8];}
		//vertical check
		if ((b[0]==b[3])&&(b[3]==b[6])&&(b[6]!=emptySpace)){return b[6];}
		if ((b[1]==b[4])&&(b[4]==b[7])&&(b[7]!=emptySpace)){return b[7];}
		if ((b[2]==b[5])&&(b[5]==b[8])&&(b[8]!=emptySpace)){return b[8];}
		//diagonal check
		if ((b[0]==b[4])&&(b[4]==b[8])&&(b[8]!=emptySpace)){return b[8];}
		if ((b[2]==b[4])&&(b[4]==b[6])&&(b[6]!=emptySpace)){return b[6];}
		if (board.countChar(emptySpace)==0){return tieChar;}
		return emptySpace;
	}

	public static void main(String[] args) {
		//create players
		Player p1 = new Human(); p1.setSymbol('H'); p1.setName("Mike");
		Player p2 = new TreeAI(); p2.setSymbol('C'); p2.setName("AI");
		
		if ((p1.getSymbol()==emptySpace)||(p1.getSymbol()==tieChar)||
			(p2.getSymbol()==emptySpace)||(p2.getSymbol()==tieChar)){
			System.out.println("a non-allowed character was chosen, game ending");
			System.exit(0);
		}
		
		//randomize who gets first move
		Random gen = new Random();
		int turn = 1+gen.nextInt(2);
		if (turn == 1){System.out.println(p1.getName()+" goes first.");}
		if (turn == 2){System.out.println(p2.getName()+" goes first.");}
		
		//play
		Board b = new Board();
		while(whoWon(b)==emptySpace){
			b.printBoard();
			if (turn == 1){b = p1.playMove(b);}
			if (turn == 2){b = p2.playMove(b);}
			if (turn == 1){turn++;}else{turn--;}
		}
		b.printBoard();
		if (whoWon(b)!=tieChar) {
			System.out.println(whoWon(b) + " won the game!");
		}else{
			System.out.println("Tie!");
		}
	}
}
