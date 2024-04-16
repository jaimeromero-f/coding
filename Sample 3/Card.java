import java.util.Objects;
// add your own banner here

public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	
	public Card(int s, int r){
		suit = s;
		rank = r;
	}
	
	public int compareTo(Card c){
		return rank - c.rank;
	}
	
	public String toString(){

		String letter = "";

		if(suit == 1){
			letter = "s";
		} else if(suit == 2){
			letter = "d";
		} else if (suit == 3){
			letter = "h";
		} else if (suit == 4){
			letter = "c";
		} else {
			letter = "invalid suit";
		}

		return letter + rank;
	}

	public int getRank(){
		return rank;
	}

	public int getSuit(){
		return suit;
	}

	public boolean equals(Object other){

		if(other==this){
			return true;
		}

		if(!(other instanceof Card)){
			return false;
		}

		Card otherCard = (Card) other;
		return this.suit == otherCard.suit && this.rank == otherCard.rank;

	}

	public int hashCode() {
		return Objects.hash(suit,rank);
	}

	// add some more methods here if needed

}
