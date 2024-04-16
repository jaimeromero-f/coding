import java.util.Arrays;

public class Tester{


    public static void main(String[] args){

        Card a = new Card(1,13);

        int rank = a.getRank();

        System.out.println(rank);
        System.out.println(a);

        System.out.println("------------");

        Card[] cards = new Card[3];
        cards[0] = new Card(1,12);
        cards[1] = new Card(2,11);
        cards[2] = new Card(3,13);

        Arrays.sort(cards);

        for(Card c : cards){
            System.out.println(c);
        }







    }





}