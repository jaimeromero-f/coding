import java.util.Collections;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
// add your own banner here

public class Game {
	
	private Player p;
	private Deck cards;
	private Scanner scan;
	// you'll probably need some more here
	
	
	public Game(String[] testHand){
		// This constructor is to help test your code.
		// use the contents of testHand to
		// make a hand for the player
		// use the following encoding for cards
		// c = clubs
		// d = diamonds
		// h = hearts
		// s = spades
		// 1-13 correspond to ace-king
		// example: s1 = ace of spades
		// example: testhand = {s1, s13, s12, s11, s10} = royal flush
        p = new Player();
		scan = new Scanner(System.in);

        
        ArrayList<Card> hand = new ArrayList<Card>();

        for(int i=0; i<5; i++){
            //substring to find suit and rank
            String letter = testHand[i].substring(0,1);
            String number = testHand[i].substring(1);

            int rank = Integer.parseInt(number);
            int suit;

            //encode according to the conventions
            if(letter.equals("c")){
                suit= 4;
            } else if(letter.equals("d")){
                suit=2;
            } else if(letter.equals("h")){
                suit=3;
            } else if(letter.equals("s")){
                suit=1;
            } else {
                suit=0;
            }

            //create cards
            Card temp = new Card(suit, rank);
            p.addCard(temp);
            hand.add(temp);
        }

        //remove cards in the deck using a method in the deck class
        cards = new Deck(hand);

        //shuffle cards for posterior draws
        cards.shuffle();
		
	}
	
	public Game(){
		// This no-argument constructor is to actually play a normal game
		p = new Player();
		cards = new Deck();
		scan = new Scanner(System.in);

        //shuffle the deck
		cards.shuffle();

		//get hand
		Card card1 = cards.deal();
		p.addCard(card1);

		Card card2 = cards.deal();
		p.addCard(card2);

		Card card3 = cards.deal();
		p.addCard(card3);

		Card card4 = cards.deal();
		p.addCard(card4);

		Card card5 = cards.deal();
		p.addCard(card5);
	}
	
	public void play(){
		// this method should play the game
        boolean play = true;

		System.out.println("Welcome to the game!");

        while(play && p.getBankroll() > 0){
		    System.out.println("Your current bankroll is: " + p.getBankroll());

            //bet

            boolean valid = false;

            while(!valid){
                System.out.print("Enter your bets, a number between 1 and 5: ");
                double bet = scan.nextDouble();

                if(bet<=p.getBankroll() && bet<6 && bet>0){
                    p.bets(bet);
                    valid = true;
                } else {
                    System.out.println("Invalid bet, try again.");
                }
            }


            //print cards and create while loop to change cards
		    System.out.println("Your cards are:" + p.getHandStr());

		    System.out.print("Please enter the number of cards you want to change"+
            " (e.g. 3). Enter 0 if you don't want to change any cards: ");
	        int number = scan.nextInt();
        
		    int[] indexes = new int[number];	
	        int ind = 0;
		    while(number>0){
		        System.out.print("Please enter the index (options: 1,2,3,4,5) of "+
                "the " + (ind+1) + " card you'd like to change. ");
		        int i = scan.nextInt();
		        indexes[ind] = i;
		        ind++;
		        number--;
		   }

            //check for repeated values!
		    boolean check = checkArray(indexes);

		    while(check){
                check = false;
                System.out.println("You have entered repeated numbers!");
                System.out.print("Please enter the number of cards you want "+
                "to change (e.g. 3): ");
	            number = scan.nextInt();
                indexes = new int[number];	
	            ind = 0;
                while(number>0){
		            System.out.print("Please enter the index (1,2,3,4,5) of "+
                    "the " + (ind+1) + 
		            " card you'd like to change. ");
		            int i = scan.nextInt();
		            indexes[ind] = i;
		            ind++;
		            number--;
		        }

                check = checkArray(indexes);
            }

            //replace once the cards are not repeated
            for(int i=0; i<indexes.length; i++){
                Card temp = cards.deal();
                p.replaceCard(temp, (indexes[i]-1));
            }
        
            //print final cards and the cards in order
            System.out.println("Your final cards are:" + p.getHandStr());
            System.out.println("Your ordered cards are:" + order(p.getHand()));

            String result = checkHand(p.getHand());
            System.out.println(result);

            //updated bankroll
            System.out.println("Your updated bankroll is " + p.getBankroll());

            System.out.print("Would you like to play again? 1 for yes / 0 for "+
            "no (or any other number): ");
            //play again, shuffle and give new cards!
            int playint = scan.nextInt();
            if(playint==1){
                play=true;
                cards = new Deck();
                cards.shuffle();

                p.replaceCard(cards.deal(),1 );
                p.replaceCard(cards.deal(),2 );
                p.replaceCard(cards.deal(),3 );
                p.replaceCard(cards.deal(),4 );
                p.replaceCard(cards.deal(),0 );

            } else {
                play=false;
            }

        }

        System.out.println("Thank you for playing! Your final Bankroll is: " + 
        p.getBankroll());
        System.out.println("Have a good day :) ");


	}
	
    //method to order the cards in the game, from high to low rank
    public String order(ArrayList<Card> hand){
		// this method should take an ArrayList of cards
		// as input and then determine what evaluates to and
		// return that as a String

		Card[] c = new Card[hand.size()];
		hand.toArray(c);
        Arrays.sort(c, (a,b) -> a.getRank() - b.getRank());


        String s = "";
        for(Card card : c){
            String temp = card.toString();
            s = s + " " + temp;

        }

        return s;
    }

    //check hand method
	public String checkHand(ArrayList<Card> hand){
		// this method should take an ArrayList of cards
		// as input and then determine what evaluates to and
		// return that as a String

		Card[] c = new Card[hand.size()];
		hand.toArray(c);

		Arrays.sort(c, (a,b) -> a.getRank() - b.getRank());

        //boolean to see which combination of cards it adds up to

		boolean rf = royalFlush(c);
        boolean sf = straightFlush(c);
        boolean foak = fourOfAKind(c);
        boolean fh = fullHouse(c);
        boolean f = flush(c);
        boolean s = straight(c);
        boolean toak = threeOfAKind(c);
        boolean tp = twoPairs(c);
        boolean op = onePair(c);

        //return string and update the winnings

        if(rf){
            p.winnings(250);
            return "Royal Flush. Payout per token: 250";
        } else if(sf){
            p.winnings(50);
            return "Straight Flush. Payout per token: 50";
        } else if(foak){
            p.winnings(25);
            return "Four of a Kind. Payout per token: 25";
        } else if(fh){
            p.winnings(6);
            return "Full House. Payout per token: 6";
        } else if(f){
            p.winnings(5);
            return "Flush. Payout per token: 5";
        } else if(s){
            p.winnings(4);
            return "Straight. Payout per token: 4";
        } else if(toak){
            p.winnings(3);
            return "Three of a Kind. Payout per token: 3";
        } else if(tp){
            p.winnings(2);
            return "Two Pairs. Payout per token: 2";
        } else if(op){
            p.winnings(1);
            return "One Pair. Payout per token: 1";
        } else {
            p.winnings(0);
            return "No pair!";
        }
		
		
	}


    //methods for all combinations

	public boolean royalFlush(Card[] hand){

        int suit = hand[0].getSuit();
        for(int i=1; i<hand.length; i++){
            //equal suits
            if(hand[i].getSuit() != suit){
                return false;
            }
            //check the actual values
            if(hand[i].getRank() < 10 || hand[i].getRank() > 13){
                return false;
            }


        }
        //the first element in the array must be 1 if it reaches this point
        if(hand[0].getRank()!= 1){
            return false;
        }

        return true;

	}

    //straight flush method
	public boolean straightFlush (Card[] hand){
		for(int i=0; i<hand.length-1; i++){
            Card temp1 = hand[i];
            Card temp2 = hand[i+1];

            if(temp1.getSuit() != temp2.getSuit()){
                return false;
            }

            if(temp1.getRank() != temp2.getRank()-1){
                return false;
            }
        }

        return true;
	}

    //four of a kind method
	public boolean fourOfAKind(Card[] hand){
        //count array that counts the repetition of cards
		int[] count = new int[13];
        for (Card c : hand){
            count[c.getRank()-1]++;
        }
        
        boolean result = false;
		
        for(int i:count){
            if(i==4){
                result = true;
                break;
            }
        }

		return result;
	}

    public boolean fullHouse(Card[] hand){

        boolean result = false;
        //make use of the other methods
        boolean result1 = threeOfAKind(hand);
        boolean result2 = onePair(hand);

        if(result1 && result2){
            result=true;
        }

        return result;

    }

    //flush method
    public boolean flush(Card[] hand){

        //equal suits
        if(hand[0].getSuit() == hand[1].getSuit() &&
        hand[0].getSuit() == hand[2].getSuit() &&
        hand[0].getSuit() == hand[3].getSuit() &&
        hand[0].getSuit() == hand[4].getSuit()){
            return true;
        } else {
            return false;
        }
    }

    //NOTE: Straight is longer because it includes 1 as both options for ending and beginning
    //of card combination
    public boolean straight(Card[] hand){
        
        if(hand[0].getRank() != 1){
            for(int i=0; i<hand.length-1; i++){
                Card temp1 = hand[i];
                Card temp2 = hand[i+1];

                if(temp1.getRank() != temp2.getRank()-1){
                    return false;
                }
            }
            return true;

        } else {
            if(hand[4].getRank() != 13){
                return false;
            }

            for(int i=1; i<hand.length-1; i++){
                Card temp1 = hand[i];
                Card temp2 = hand[i+1];

                if(temp1.getRank() != temp2.getRank()-1){
                    return false;
                }
            }
            return true;
        }
    }

    //three of a kind method
    public boolean threeOfAKind(Card[] hand){
        int[] count = new int[13];
        for (Card c : hand){
            count[c.getRank()-1]++;
        }
        
        boolean result = false;
		
        for(int i:count){
            if(i==3){
                result = true;
                break;
            }
        }

		return result;
    }

    //use count array, and find 2 pairs
    public boolean twoPairs(Card[] hand){
        int pairs = 0;

        int[] count = new int[13];
        for (Card c : hand){
            count[c.getRank()-1]++;
        }
        
        boolean result = false;
		
        for(int i:count){
            if(i==2){
                pairs++;
            }
        }

        if(pairs>1){
            result = true;
        }

		return result;
    }

    //use count array and find one pair
    public boolean onePair(Card[] hand){
        int[] count = new int[13];
        for (Card c : hand){
            count[c.getRank()-1]++;
        }
        
        boolean result = false;
		
        for(int i:count){
            if(i==2){
                result = true;
                break;
            }
        }

		return result;
    }

    //method to check for double entries in an array
	public boolean checkArray(int[] arr){
		boolean duplicates = false;

		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr.length; j++){
				if(i!=j && arr[i] == arr[j]){
					duplicates = true;
				}
			}
		}

		return duplicates;
	}

}
