package playingCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A class to represent a Deck of playing cards
 *
 * @author Disha Revandkar  dr6742@rit.edu
 * @author Shreya Pramod    sp3045@rit.edu
 */
public class Deck {
    List<Card> cards;
    Random random;

    /**
     * Creates and initializes a new unshuffled deck
     */
    public Deck() {
        // create a fresh deck
        random = new Random();
        cards = new ArrayList<>(52);
        createDeck();
    }

    /**
     * Creates and initializes a new unshuffled deck
     */
    private void createDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card(rank, suit);
                cards.add(card);
            }
        }
    }

    /**
     * Deals the next card from the deck
     *
     * @return the next card off the deck
     */
    public Card dealCard() {
        int index = random.nextInt(cards.size());
        return cards.remove(index);
    }

    /**
     * Shuffles the deck
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Displays the deck
     */
    public void display() {
        for (Card card : cards) {
            System.out.println(card.getShortName());
        }
    }
}

