package test;

import miniPoker.Computer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import playingCards.Card;
import playingCards.Rank;
import playingCards.Suit;

/**
 * A test class for the computer player
 *
 * @author RIT CS
 */
public class TestComputer {

    /**
     * Deck of cards with a fix set of cards
     */
    private static Card[] deck;
    /**
     * Array of poker hands sorted in descending order
     * by the hand's value.
     * pick[0] is the highest hand, pick[13] is the lowest hand.
     */
    private static int[][] pick;

    @BeforeAll
    public static void init() {
        // creating a deck of cards with a fixed set of cards
        deck = new Card[10];
        deck[0] = new Card(Rank.ACE, Suit.CLUBS);
        deck[1] = new Card(Rank.ACE, Suit.SPADES);
        deck[2] = new Card(Rank.KING, Suit.SPADES);
        deck[3] = new Card(Rank.QUEEN, Suit.SPADES);
        deck[4] = new Card(Rank.QUEEN, Suit.CLUBS);
        deck[5] = new Card(Rank.JACK, Suit.HEARTS);
        deck[6] = new Card(Rank.DEUCE, Suit.DIAMONDS);
        deck[7] = new Card(Rank.DEUCE, Suit.SPADES);
        deck[8] = new Card(Rank.TEN, Suit.DIAMONDS);
        deck[9] = new Card(Rank.SIX, Suit.CLUBS);

        // creating an array of poker hands in descending order
        // by the value, pick[0] is the highest hand, pick[13] is the lowest hand
        pick = new int[][]{
                {0, 1}, {3, 4}, {6, 7}, // hands of pairs
                {1, 2}, {1, 3}, {1, 7}, {2, 3}, // hands of flush
                {0, 2}, {2, 4}, {3, 5}, {5, 8}, // hands for high cards
                {8, 9}, {8, 7}, {9, 6}
        };
    }

    @Test
    public void testNewHand() {
        Computer c = new Computer();
        // adding the first hand to the computer
        c.takeCard(deck[0]);
        c.takeCard(deck[1]);
        c.printHand();
        Assertions.assertTrue(c.getValue() > 0, "ERROR value must be greater than 0");
        // creating a new hand
        c.newHand();
        Assertions.assertTrue(c.getValue() == 0, "ERROR hand is empty, value must be 0");
    }

    @Test
    public void testValue() {
        Computer c = new Computer();
        // adding the first hand to the computer
        c.takeCard(deck[0]);
        c.takeCard(deck[1]);
        c.printHand();
        Assertions.assertEquals(1210, c.getValue(),
                "Error: the value is incorrect");

        int last = c.getValue() + 1;
        // pick is in descending order
        // no hand can have a value greater than the previous
        // hand in the array
        for (int i = 0; i < pick.length; i++) {
            c.newHand();
            for (int j = 0; j < 2; j++) {
                c.takeCard(deck[pick[i][j]]);
            }
            c.printHand();
            Assertions.assertTrue(c.getValue() < last, "ERROR value is incorrect");
            last = c.getValue();
        }
    }

    @Test
    public void testStand() {
        Computer c = new Computer();

        int k = 9;    // QJ non-flush is pick[9], so i<9 => stand

        for ( int i = 0; i < pick.length; i++ ) {
            for ( int j = 0; j < 2; j++ ) {
                c.takeCard( deck[ pick[ i ][ j ] ] );
            }
            c.printHand();
            Assertions.assertFalse((i <= k && !c.stand()) || (i > k && c.stand()),
                    "ERROR in stand method");
            c.newHand();
        }
    }
}