import java.util.ArrayList;
// add your own banner here

public class Player {
	
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;

	// you may choose to use more instance variables
		
	public Player(){		
	    hand = new ArrayList<Card>();
        bankroll = 50;
        bet = 0;
	}

	public void addCard(Card c){
	    // add the card c to the player's hand
        hand.add(c);
	}

	public void removeCard(Card c){
	    // remove the card c from the player's hand
        hand.remove(c);
    }

    public void replaceCard(Card c, int index){
	    // remove the card c from the player's hand
        hand.set(index, c);
    }
		
    public void bets(double amt){
        // player makes a bet
        bet = amt;
    }

    public void winnings(double odds){
        //	adjust bankroll if player wins
        bankroll += bet * odds - bet;
    }

    public double getBankroll(){
            // return current balance of bankroll
        return bankroll;
    }

    public String getHandStr(){
        String hand_str = "";
        for(Card c : hand){
            String card_str = c.toString();
            hand_str = hand_str + " " + card_str;
        }
        return hand_str;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

        // you may wish to use more methods here
}


