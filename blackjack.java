/**
* Course title:  		Introduction to Java Programming
* Course code: 			COMP 30580
* Reader:        		Mr. Mark Scanlon B.A., M.Sc.
* Teaching Assistant:	Rahul Mangulkar et al., rahul.mangulkar@ucdconnect.ie
* Student:				Kevin Mc Inerney (B.A.) (Psych.) (Int.), kevinmcinerney8@gmail.com, +353 857591164
* Exercise:				Assignment 2, Q4
* Date:					28/11/2013
* Description:			See Q4
*
*/	
import java.util.Scanner;
public class testBlackJack {
	/** Main Method */
	public static void main(String[] args) {
				
		/* Determines if player wants to play again in do-while loop */
		Character playAgainChar = new Character('a'); 
		
		do{
			/* Get the users desired stake */
			System.out.println("\n\t\t\t\tThe BANK:  $" +  blackjack.bank);
			System.out.println("\n\t\tWhat stakes would like to play for?");
			System.out.println("============================================================\n");
			Scanner input = new Scanner(System.in);
			blackjack.stakes = input.nextInt();
			
			/* Create new deck */
			Deck deck = new Deck();
			
			/* Set up a game */
			blackjack.setupNewGame(deck);
	
			/* Deal hands to both the dealer and player */
			int [] dealerHand = (Deck.dealHand());	
			int [] playerHand = (Deck.dealHand());
			
			int showPlayerCards = 2; /* reveal two of the players cards from his potential hand 0f ten cards */
			int showDealerCards = 0; /* reveal none of the dealers cards for now */
			Character hitOrStandChar = new Character('a');
			do {
				/* Show one of the dealers cards, calculate the score. and print the hand */
				System.out.println("\n\t\t\t\tThe Dealer:  Score: " + blackjack.calculateWinnings(dealerHand, 1));
				System.out.println("============================================================\n");
				blackjack.printHand(dealerHand, 1);
				
				/* Show one of the players cards, calculate the score. and print the hand */
				System.out.println("\n\t\t\t\tThe Player:  Score: " +  blackjack.calculateWinnings(playerHand, showPlayerCards));
				System.out.println("============================================================\n");
				blackjack.printHand(playerHand, showPlayerCards);
				
				/* Ask the player if he/she wants to hit or stand */
				System.out.println("\n============================================================\n");
				System.out.println("\n\t\t\t\tHit <h>   or   Stand <s>");
				String hitOrStand = input.next();
				
				/* Convert string to single char object */
				hitOrStandChar = hitOrStand.charAt(0);
				hitOrStandChar = Character.toLowerCase(hitOrStandChar);
				
				/* Determine if player has hit or not */
				if (hitOrStandChar.equals('h')){
					showPlayerCards +=  blackjack.hit();
				}
			/* Repeat, while the player wants to hit and their score is below 21 */
			} while(hitOrStandChar.equals('h') &&  blackjack.calculateWinnings(playerHand, showPlayerCards) < 21);
			
			/* While The dealers score is less than seventeen, keep dealing him cards */
			while ( blackjack.calculateWinnings(dealerHand, showDealerCards) < 17){
				showDealerCards++;
			}
			
			/* Show all the dealers cards, calculate the score. and print the hand */
			System.out.println("\t\t\t\tThe Dealer:  Score: " +  blackjack.calculateWinnings(dealerHand, showDealerCards));
			System.out.println("============================================================\n");
			 blackjack.printHand(dealerHand, showDealerCards);
			
			/* Show all of the players cards, calculate the score. and print the hand */
			System.out.println("\n\t\t\t\tThe Player:  Score "  +  blackjack.calculateWinnings(playerHand, showPlayerCards));
			System.out.println("============================================================\n");
			 blackjack.printHand(playerHand, showPlayerCards);
			
			/* Calculate whose won/lost/drawn, print a message, and adjust the bank accordingly */
			 blackjack.finishDealersPlay(dealerHand, playerHand, showPlayerCards, showDealerCards);
			
			/* Ask player if they want to play again */
			System.out.println("============================================================\n");
			System.out.println("\n\t\t\t\t NEW GAME?     <y>      <n>");
			String playAgain = input.next();
			
			/* Convert string to char object */
			playAgainChar = playAgain.charAt(0);
			playAgainChar = Character.toLowerCase(playAgainChar);
			
			/* Let the player know they're out of bank */
			if ( blackjack.bank <= 0){
				System.out.println("\n\t\t\t\tYour luck's out Pal...Take a hike\n\n");
				System.out.println("\n\t\t\t\tWe don't want your type smelling out the joint!");
			}
		/* Play again if the Player wants to, and if they have some bank */ 	
		}while(playAgainChar.equals('y') &&  blackjack.bank > 0);
	}
}

class Deck {
	public static int count;  /* Will count the cards being drawn */
	public static int[] deck; /* Initialises a deck object */
	
	Deck(){
		deck = new int [52];  /* no-arg constructor makes 52 card deck */
		count = 0;            /* Resets each deck object to count 0 */
	}
	
	
	public static int drawCard(){    /* Draws cards in increasing order */
			
			count++;
			return deck[count];
		}
	
	public static void shuffle(){                   /* Shuffles the deck */
		
		for (int i = 0; i < deck.length; i++){      /* Gives deck values 0 -51 */
			deck[i] = i % 52;
		}
		/* Rearranges cards in deck using temp variable */
	    for (int i = 0; i < deck.length; i++) {   
		    int index = (int)(Math.random() * deck.length);
		    int temp = deck[i];
		    deck[i] = deck[index]; 
		    deck[index] = temp; 
	    }
	}
	
	/* Makes an invisible-potential hand of up to ten cards */
	public static int[] dealHand(){  
		
		int [] arrayHand = new int[10];
		
		for (int i = 0; i < 10; i++){
			arrayHand[i] = drawCard();
		}
		return arrayHand;
	}
}
		
class blackjack {
	
	public static void setupNewGame(Deck deck){
		
		Deck.shuffle();
		
		Deck.drawCard();
		
	}
	public static int hit(){
			
			return 1;
		}
	
	public static void printHand(int[] arrayHand, int reveal){
		
		// Arrays for card names
		String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
		String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9",
							"10", "Jack", "Queen", "King"};
		
		String suit = "";
		String rank = "";
		
		/* Print the number of card which have been chosen to be revealed */
		for(int i = 0; i < reveal; i++){
			int suitInt = arrayHand[i] / 13;
			int rankInt = arrayHand[i] % 13;
		
			suit = suits[suitInt];
			rank = ranks[rankInt];
			
			System.out.println((i + 1) + ".) " + rank +" of "+ suit);
		}
	}	
	/* Return scores for as many cards in hand as you want to reveal */
	public static int calculateWinnings(int[] hand, int reveal){
		
		boolean hasAce = false; /* Initially, no aces have been drawn */ 
		int score = 0;
		
		for (int i = 0; i < reveal; i++){
			switch(hand[i] % 13){
			
			case 0:             /* If an ace is drawn, set boolean to true and set score = 11 */
				score += 11;
				hasAce = true;
				break;
			case 1: score += 2; break;
			case 2: score += 3; break;
			case 3: score += 4; break;
			case 4: score += 5; break;
			case 5: score += 6; break;
			case 6: score += 7; break;
			case 7: score += 8; break;
			case 8: score += 9; break;
			case 9: score += 10; break;
			case 10: score += 10; break;
			case 11: score += 10; break;
			case 12: score += 10; break;
			}	
		}
		
		/* If the card drawn was an ace, and the score is now over make the ace worth 1 */
		if (score > 21 && hasAce == true){
			score -= 10; /* Same as making the ace worth 1 */
		}
		return score;
	}
	
	/* Determine the game result, adjust bank, and print message */
	public static int finishDealersPlay(int[] dealerHand, int[] playerHand, int showPlayerCards, int showDealerCards){
		
		int score = -1; /* Unless one of the following is true, the dealer has won */
		
		/* Calculate scores */
		int dealerScore = calculateWinnings(dealerHand, showDealerCards);
		int playerScore = calculateWinnings(playerHand, showPlayerCards);
		
		/* If dealer and player have same score, it's a draw */
		if (calculateWinnings(playerHand, showPlayerCards) == calculateWinnings(dealerHand, showDealerCards)){
			score = 0;
			System.out.println("\n\t\t\t\t ~ ~ ~ DRAW DRAW DRAW ~ ~ ~");
			bank -= (score * stakes);
		}
		
		/* If the player has blackjack, and the dealer doesn't, the player wins */
		if ((calculateWinnings(playerHand, 2) == 21) && (calculateWinnings(dealerHand, 2) != 21) ){
			score = 1;
			System.out.println("\n\t\t\t\t $$$$$$$$$$ ~ WIN WIN WIN ~ $$$$$$$$");
			bank += (score * stakes);
		}
		
		/* If the player has a bigger score than the dealer and the player is below 21, they win */
		if ((playerScore > dealerScore) && (playerScore <= 21)){
				score = 1;	
				System.out.println("\n\t\t\t\t $$$$$$$$$$ ~ WIN WIN WIN ~ $$$$$$$$");
				bank += (score * stakes);
		}
		
		/* If the player is below 21 and the dealer is over, the player wins */
		if ((playerScore <= 21) && (dealerScore > 21)){
			score = 1;
			System.out.println("\n\t\t\t\t $$$$$$$$$$ ~ WIN WIN WIN ~ $$$$$$$$");
			bank += (score * stakes);
		}
		
		/* If the score is still = -1 then you lost */
		if (score == -1){
			System.out.println("\n\t\t\t\t ~ ~ ~ LOSE LOSE LOSE ~ ~ ~");
			bank += (score * stakes);
		}
		return score;
	}
	/* Keep track of bank and stakes */
	public static int bank = 100;
	public static int stakes = 0;
}



