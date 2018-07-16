package package1;

public class Board{
	private char[] board;
	
	public Board(){
		this.board = new char[9];
		for (int i = 0; i < 9; i++){
			this.board[i] = Driver.emptySpace;
		}
	}
	
	public int countChar(char c){
		int count = 0;
		for (int i = 0; i < 9; i++){
			if (board[i]==c){count++;}
		}
		return count;
	}
	
	public void setChar(char input, int spot){//1-9 l-r t-d as you would expect
		assert(spot < 10); assert(spot > 0);
//		System.out.println("attempting to setchar with number: " + spot);
		this.board[spot-1] = input;
	}
	
	public char getChar(int spot){//1-9 l-r t-d as you would expect
		assert(spot < 10); assert(spot > 0);
		return this.board[spot-1];
	}
	
	public char[] getBoard(){return this.board;}
	
	public void printBoard(){
		String rowLine = "---------";
		System.out.println(this.board[0] + " | " + this.board[1] + " | " + this.board[2]);
		System.out.println(rowLine);
		System.out.println(this.board[3] + " | " + this.board[4] + " | " + this.board[5]);
		System.out.println(rowLine);
		System.out.println(this.board[6] + " | " + this.board[7] + " | " + this.board[8]);
	}
	
	public void copyBoard(Board targetBoard){
		for (int i = 1; i <= 9; i++){
			targetBoard.setChar(this.getChar(i), i);
		}
	}
}
