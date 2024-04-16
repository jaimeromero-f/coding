import java.util.ArrayList;

public class TesterDeck {

    public static void main(String[] args){

        Deck deck = new Deck();

        System.out.println(deck);

        Card a = deck.deal();
        System.out.println(a);

        Card b = deck.deal();
        System.out.println(b);

        deck.shuffle();

        a = deck.deal();
        System.out.println(a);

        b = deck.deal();
        System.out.println(b);

        System.out.println(deck);


        ArrayList<Card> array = new ArrayList<Card>();

        System.out.println("##################");

        array.add(a);
        array.add(b);

        deck.shuffle();
        System.out.println(deck);

        deck.deleteCards(array);

        System.out.println(deck);


        System.out.println("##############");

        Deck deck2 = new Deck(array);
        System.out.println(deck2);

    }

}