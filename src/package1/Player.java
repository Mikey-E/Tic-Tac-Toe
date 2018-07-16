package package1;

public interface Player {//to require how a player must play
	boolean isAI();
	Board playMove(Board b);
	char getSymbol();
	void setSymbol(char input);
	String getName();
	void setName(String input);
}
