package collection.compare.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardGameMain {

    public static void main(String[] args) {
        List<Card> deck = new ArrayList<>();
        deckInit(deck);
        //deck 초가화

        //deck shuffle
        Collections.shuffle(deck);

        Player p1 = new Player();
        Player p2 = new Player();

        p1.setDeck(deck.subList(0, 5));
        p2.setDeck(deck.subList(5, 10));

        printPlayerCard(p1 , p2);
        printWinPlayer(p1, p2);
    }

    private static void printWinPlayer(Player p1, Player p2) {
        if (p1.getNumberSum() >  p2.getNumberSum()) {
            System.out.println("플레이어1 승리");
        }else if (p1.getNumberSum() < p2.getNumberSum()) {
            System.out.println("플레이어2 승리");
        }else{
            System.out.println("무승부");
        }
    }

    private static void printPlayerCard(Player p1, Player p2) {
        p1.getDeck().sort(null);
        p2.getDeck().sort(null);
        System.out.println("플레이어1의 카드 : " + p1.getDeck() + " , 합계: " + p1.getNumberSum());
        System.out.println("플레이어2의 카드 : " + p2.getDeck() + " , 합계: " + p2.getNumberSum());
    }

    private static void deckInit(List<Card> deck) {
        String[] shapes = { "♠" , "♥", "◆" , "♣" };
        for(int i = 0 ; i < 4 ; i++){
            for( int j = 1; j <= 13 ; j++){
                deck.add(new Card(shapes[i],j));
            }
        }
    }
}
