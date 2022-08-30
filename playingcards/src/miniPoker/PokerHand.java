package miniPoker;

import playingCards.Card;
import java.util.List;
import static miniPoker.MiniPoker.MAX_CARDS;

/**
 * A class which is used to encapsulate a hand of cards for a 2-card poker game.
 *
 * @author Shreya Pramod    sp3045@rit.edu
 * @author Disha Revandkar  dr6742@rit.edu
 */
public class PokerHand {

    /**
     * A card array used to depict the cards available to a player.
     */
    Card[] deck;

    /**
     * the number of the current round.
     */
    private int hand = 0;

    /**
     * A list of string items depicting the ranks of the cards from high to low.
     */
    private List<String> cardRanks = List.of("A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2");

    /**
     * Default constructor of PokerHand which creates a new deck of 2 cards for each player.
     */
    public PokerHand(){
        this.deck = new Card[MAX_CARDS];
    }

    /**
     * This function adds a card to the hand.
     *
     * @param card the card to be added to the hand.
     */
    public void addCard(Card card){
        int index = 0;
        if (this.hand == 0 && index < 2) {
            this.deck[index] = new Card(card.getRank(), card.getSuit());
            this.hand++;
        } else {
            index++;
            if (this.hand == 1 && index < 2) {
                this.deck[index] = new Card(card.getRank(), card.getSuit());
                this.hand =0;
            }
        }
    }

    /**
     * This function returns a numeric value to represent the current value in hand.
     *
     * @return an integer value representing the current value of the hand.
     */
    public int getValue(){
        int cardIndex = 0;
        if (this.deck[cardIndex] == null && this.deck[cardIndex+1] == null)
            return 0;

        /**
         * formula for computing the value for a "pair" hand.
         */
        else if (this.deck[cardIndex].getRank() == this.deck[cardIndex + 1].getRank()) {
            return ((this.deck[cardIndex].getValue()*14) + this.deck[cardIndex+1].getValue() + 1000);
        }

        /**
         * formula for computing the value for a "flush" hand.
         */
        else if (this.deck[cardIndex].getSuit() == this.deck[cardIndex + 1].getSuit()){
            if (this.cardRanks.indexOf(this.deck[cardIndex].getRank().getShortName())<this.cardRanks.indexOf(this.deck[cardIndex+1].getRank().getShortName()))
                return ((this.deck[cardIndex].getValue()*14) + this.deck[cardIndex+1].getValue() + 500);
            else
                return ((this.deck[cardIndex+1].getValue()*14) + this.deck[cardIndex].getValue() + 500);
        }

        /**
         * formula for computing the value for a "high card" hand.
         */
        else{
            if (this.cardRanks.indexOf(this.deck[cardIndex].getRank().getShortName())<this.cardRanks.indexOf(this.deck[cardIndex+1].getRank().getShortName()))
                return ((this.deck[cardIndex].getValue()*14) + this.deck[cardIndex+1].getValue());
            else
                return ((this.deck[cardIndex+1].getValue()*14) + this.deck[cardIndex].getValue());
        }
    }

    /**
     * This function prints the current hand in a "nice" format.
     */
    public void printHand(){
        System.out.print(" ----| ");
        System.out.print(this.deck[0].getRank().getShortName()+this.deck[0].getSuit().getShortName());
        System.out.println("     |");

        System.out.print("| ");
        System.out.print(this.deck[1].getRank().getShortName()+this.deck[1].getSuit().getShortName());
        System.out.println(" |        |");
        System.out.print("-----------------------------------------------");
    }

    /**
     * This function prints the current hand in an "ugly" format.
     */
    @Override
    public String toString() {
        return ("" + deck[0] + " " + deck[1]);
    }
}