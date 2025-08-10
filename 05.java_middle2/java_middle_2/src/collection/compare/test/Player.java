package collection.compare.test;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> deck ;

    public Player() {
        deck = new ArrayList<Card>();
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public int getNumberSum(){
        int sum = 0;
        for (Card card : deck) {
            sum += card.getNumber();
        }
        return sum;
    }
}
