public class TesterPlayer {

    public static void main(String[] args){

        Player jaime = new Player();

        Card a = new Card(1,13);
        Card b = new Card(2,3);


        jaime.addCard(a);
        jaime.addCard(b);
        
        String myhand = jaime.getHandStr();
        System.out.println(myhand);

        double bank = jaime.getBankroll();
        System.out.println(bank);

        jaime.removeCard(a);
        myhand = jaime.getHandStr();
        System.out.println(myhand);




    }

}