import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
// add your own banner here

public class Deck {
	
	private Card[] cards;
	private int top; // the index of the top of the deck

	// add more instance variables if needed
	
	public Deck(){
		// make a 52 card deck here
		top = 0;
		cards = new Card[52];

		int num = 0;
		for (int i=1; i<5; i++){
			for (int j=1; j<14;j++){
				cards[num] = new Card(i,j);
				num++;
			}
		}
	}
	
	public Deck(ArrayList<Card> hand){
		// make a 52 card deck here
		top = 0;
		ArrayList<Card> cards1 = new ArrayList<Card>();

		for (int i=1; i<5; i++){
			for (int j=1; j<14;j++){
				Card temp = new Card(i,j);
				cards1.add(temp);
			}
		}


        for(int j=0; j<hand.size(); j++){
            Card temp = hand.get(j);
            Card cardtoRemove = new Card(temp.getSuit(), temp.getRank());
            cards1.removeAll(Collections.singleton(cardtoRemove));
        }

		cards = new Card[cards1.size()];

		for(int i=0; i<cards1.size(); i++){
			cards[i] = cards1.get(i);
		}

	}


	public void shuffle(){
		Random rand = new Random();
		for(int i=0; i<1000; i++){
			int one = rand.nextInt(cards.length);
			int two = rand.nextInt(cards.length);
			Card temp = cards[one];
			cards[one] = cards[two];
			cards[two] = temp;
		}

		top = 0; //set top index equals to zero
		
	}
	
	public Card deal(){
		// deal the top card in the deck
		Card top_card = cards[top];
		top++;
		return top_card;
	}

	public String toString(){
		String cards_str = "";
		
		for(Card c : cards){
            String temp = c.toString();
			cards_str = cards_str + " " + temp;
        }

		return cards_str;
	}
	

	public void deleteCards(ArrayList<Card> arr){

		ArrayList<Card> temp_deck = new ArrayList<Card>();

		for (int i=0; i<cards.length; i++){
			Card temp = cards[i];
			temp_deck.add(temp);
		}

		temp_deck.removeAll(arr);

		Card[] cards2 = new Card[temp_deck.size()];

		for(int i=0; i<temp_deck.size(); i++){
			cards2[i] = temp_deck.get(i);
		}

		cards = cards2;

	}


	// add more methods here if needed

}
