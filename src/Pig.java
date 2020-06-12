
import java.util.Scanner;
public class Pig {
	/*
	 *  Main method - I want to print out the general informations for players about the game
	 * Giving the players the chooses to play with other player or play with computer or let computer plays with yourself. ( switch statement ) 
	 * If the input from player is invalid ( out of selections ) print out the input is wrong ( option to quit )
	 * define the player is computer or human by boolean values ( true, false )
	 */
	public static void main(String[] args) 
	{
		Scanner in = new Scanner (System.in);
		System.out.println("The Game of Pig");
		System.out.println("--------------------------------------\n1. Human vs. Human \n2. Human vs. Computer \n3. Computer vs. Computer\n");
		System.out.println("What kind of game do you want to play?");
		int choice = in.nextInt();
		switch (choice)
		{
		case 1 : playGame(true,true); break;
		case 2 : playGame(true,false); break;
		case 3 : playGame(false,false); break; 
		default : System.out.print("Your choice is not even exist!");
		}
	}
	/* 
	 * PlayGame method -  I set up the function to calculate the scores for two players. The scores begin from 0, and which one go more and equal than 100 first is the winner.
	 * The score need to depend on the playTurn method to return the score for each player's turn and check the score if it more and equal than 100 or not
	 * if it is not, switch turn to other player.
	 * I use while loop because can not predict how many turn player take to win and if statement, switch statement to check the score and switch turn. 
	 */
	public static void playGame( boolean player1, boolean player2 )
	{
		int score1 = 0;
		int score2 = 0;
		int turn  = 1 ;
		while ( score1 < 100 && score2 <100  )
		{
			System.out.println("player1's score : " + score1);
			System.out.println("player2's score : " + score2);
			switch ( turn )
			{
			case 1 : score1 = playTurn( player1, turn, score1 ); turn = 2; break;
			case 2 : score2 = playTurn( player2, turn, score2 ); turn = 1; break;
			}
		}
		System.out.println("player1's score : " + score1);
		System.out.println("player2's score : " + score2);
		if (score1 >= 100 ) System.out.println("Player1 is a winner!" );
		if (score2 >= 100 ) System.out.println("Player2 is a winner!");
	}
/*
 * PlayTurn method - scores start with 0 in both player. The random number will run from 1 to 6 in each turn player role a dice.
 * Firstly, print out the player's name turn ( Human or Computer )
 * I use system.out.format with %s to take the String ( playerName) and %d for int and double type ( int number )
 * Each roll's turn, print out the number player rolled in that turn. If it is not equal to 1, set and add the turn's score into the total score
 * If it is equal to 1, print out Turn over !( This must be depend on the getDicision part so that I use while loop throught the sistuation that is not a get dicision part.
 */
	public static int playTurn( boolean player, int number, int totalScore )
	{
		int turnScore = 0;
		int dice = (int)(Math.random() * 6 + 1 );

		String playerName;
		if(player) 
			playerName = "Human"; 
		else 
			playerName = "Computer";
		System.out.println();
		System.out.printf("Player %1d's Turn (%s) \n",number,playerName);
		System.out.println("Rolled a " + dice );
		if (dice != 1)
			turnScore += dice; 
		else
		{
			System.out.println("Turn over ");
			return totalScore;
		}
		while (!getDecision(player,turnScore,totalScore))
		{
			dice = (int)(Math.random() * 6 + 1 );
			System.out.println("Rolled a " + dice );
			if (dice == 1)
			{
				System.out.println("Turn over ");
				System.out.println();
				return totalScore;
			}
			turnScore += dice;
		}

		return totalScore + turnScore;
	}
/*
 * GetDecision method - set the new scanner object  to read the input. This part player decide to roll or hold. I just count the first character they type in by using the new value which is char first and set it equal to charAt(0). 
 * So that even they type something weird, it does not count.
 * If player choose hold, set turn over by set get decision to true, the new score will be added in and print out two player's score, start the new turn with other player. 
 * If they choose roll,  begin the new roll and print out the number of dice
 *  Optimal strategies take the opponent's score into account as well. In this case, before returning, the method prints Computer player holds or Computer player rolls, as appropriate.
 */  
	public static boolean getDecision( boolean player, int turnScore, int totalScore )
	{

		System.out.println("Turn score : " + turnScore);
		boolean Decision = player;
		if(player)
		{
			Scanner in = new Scanner(System.in);
			System.out.print("Roll or Hold(r or h)");
			char first = in.next().charAt(0);
			switch(first)
			{
			case 'h': 
				System.out.println("Human player holds"); 
				Decision = true;
				System.out.println("Turn over");
				System.out.println();
				break;
			case 'r': 
				System.out.println("Human player rolls");
				Decision = false;
				break;
			}
		}
		else
		{
			if((turnScore + totalScore >= 100) || turnScore >= 20 )
			{
				System.out.println("Computer player holds");
				Decision = true;
				System.out.println("Turn over");
				System.out.println();
			}
			else
			{
				System.out.println("Computer player rolls");
				Decision = false;
			}
		}
		return Decision;
	}
}
