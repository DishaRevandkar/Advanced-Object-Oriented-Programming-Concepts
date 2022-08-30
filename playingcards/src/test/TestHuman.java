package test;

import miniPoker.Computer;
import miniPoker.Human;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import playingCards.Card;
import playingCards.Rank;
import playingCards.Suit;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * A test class for the human player
 *
 * @author RIT CS
 */
public class TestHuman {

    /**
     * Deck of cards with a fix set of cards
     */
    private static Card[] deck;
    /**
     * Array of poker hands sorted in descending order
     * by the hand's value.
     * pick[0] is the highest hand, pick[10] is the lowest hand.
     */
    private static int[][] pick;

    @BeforeAll
    public static void init() {
        // creating a deck of cards with a fixed set of cards
        deck = new Card[ 8 ];
        deck[ 0 ] = new Card( Rank.ACE, Suit.CLUBS );
        deck[ 1 ] = new Card( Rank.ACE, Suit.SPADES );
        deck[ 2 ] = new Card( Rank.KING, Suit.SPADES );
        deck[ 3 ] = new Card( Rank.QUEEN, Suit.SPADES );
        deck[ 4 ] = new Card( Rank.QUEEN, Suit.CLUBS );
        deck[ 5 ] = new Card( Rank.DEUCE, Suit.HEARTS );
        deck[ 6 ] = new Card( Rank.DEUCE, Suit.DIAMONDS );
        deck[ 7 ] = new Card( Rank.DEUCE, Suit.SPADES );

        // creating an array of poker hands in descending order
        // by the value, pick[0] is the highest hand, pick[10] is the lowest hand
        pick = new int[][]{
                { 0, 1 }, { 3, 4 }, { 5, 6 }, // hands of pairs
                { 1, 2 }, { 1, 3 }, { 1, 7 }, { 2, 3 }, // hands of flush
                { 0, 2 }, { 0, 3 }, { 0, 5 }, { 4, 5 } // hands of high cards
        };
    }

    @Test
    public void testStand() {
        // redirecting the System.in to read from this stream
        // mocking user input
        System.setIn(new ByteArrayInputStream("n\ny\n".getBytes()));
        Human h = new Human( new Scanner( System.in ) );
        Computer c = new Computer();

        h.takeCard( deck[ 0 ] );
        h.takeCard( deck[ 1 ] );

        // first call, "user" enters no
        Assertions.assertFalse(h.stand(), "ERROR stand with NO answer");
        // second call, "user" enters yes
        Assertions.assertTrue(h.stand(), "ERROR stand with YES answer");
    }

    @Test
    public void testCompare(){
        Human h = new Human( new Scanner( System.in ) );
        Computer c = new Computer();
        // pick for computer hands
        int cPick[] = { 0, 0, 3, 2, 5, 5, 10, 0, 8, 8, 0 };

        for ( int i = 0; i < pick.length; i++ ) {
            // dealing cards to the human player
            for ( int j = 0; j < 2; j++ ) {
                h.takeCard( deck[ pick[ i ][ j ] ] );
            }
            System.out.println( "Human Value = " + h.getValue() );

            //dealing cards to the computer player
            int k = cPick[ i ];
            for ( int j = 0; j < 2; j++ ) {
                c.takeCard( deck[ pick[ k ][ j ] ] );
            }

            System.out.println( "Computer Value = " + c.getValue() );

            Assertions.assertFalse(i == k && h.compareTo( c ) != 0, "ERROR Expected a tie");
            Assertions.assertFalse(i < k && h.compareTo( c ) <= 0, "ERROR Human should win");
            Assertions.assertFalse(i > k && h.compareTo( c ) >= 0, "ERROR Computer should win");

            h.newHand();
            c.newHand();
            System.out.println();
        }
    }

    @Test
    public void testNewHand() {
        Computer c = new Computer();
        // adding the first hand to the computer
        c.takeCard(deck[0]);
        c.takeCard(deck[1]);
        c.printHand();
        Assertions.assertTrue(c.getValue() > 0);
        // creating a new hand
        c.newHand();
        Assertions.assertTrue(c.getValue() == 0);
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
            Assertions.assertTrue(c.getValue() < last);
            last = c.getValue();
        }
    }
}
