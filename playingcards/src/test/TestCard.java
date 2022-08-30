package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import playingCards.Card;
import playingCards.Rank;
import playingCards.Suit;

/**
 * A test class for the Card class
 *
 * @author RIT CS
 */
public class TestCard {

    @Test
    public void testInit(){
        Card c;

        // create all 52 unique cards in the deck and test the accessors
        for ( Rank r : Rank.values() ) {
            for ( Suit s : Suit.values() ) {
                c = new Card( r, s );

                Assertions.assertEquals(r, c.getRank(),
                        "Error: getRank is incorrect");

                Assertions.assertEquals(s, c.getSuit(),
                        "Error: getSuit is incorrect");

                Assertions.assertEquals(c.getValue(), r.getValue(),
                        "Error: getValue is incorrect");
            }
        }
    }

    @Test
    public void testName(){
        // create an Ace of Clubs and make sure its short name is " AC"
        Card c = new Card( Rank.ACE, Suit.CLUBS );
        Assertions.assertEquals(c.getShortName(), " AC",
                "Error getShortName is incorrect");

        // create a Three of Spades and make sure its toString is
        // "THREE of SPADES"
        c = new Card( Rank.THREE, Suit.SPADES );
        Assertions.assertEquals(c.toString(), "THREE of SPADES",
                "Error toString is incorrect");
    }
}
