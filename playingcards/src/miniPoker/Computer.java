package miniPoker;

import playingCards.Card;
import playingCards.Rank;

/**
 * A class representing a Computer Player for a 2-player poker game.
 *
 * @author Shreya Pramod    sp3045@rit.edu
 * @author Disha Revandkar  dr6742@rit.edu
 */
public class Computer {
    /**
     * Creating an instance of type PokerHand
     */
    PokerHand pokerHand;

    /**
     * Default constructor for Computer which creates a new instance of PokerHand class.
     */
    public Computer(){
        pokerHand = new PokerHand();
    }

    /**
     * This function comes up with a single integer that represents the current value of the hand.
     *
     * @return an integer with the present value of the hand.
     */
    public int getValue(){
        return pokerHand.getValue();
    }

    /**
     * This function clears out all the hands of the computer.
     */
    public void newHand(){
        pokerHand.deck = new Card[2];
    }

    /**
     * This function prints the current hand in a "nice" format.
     */
    public void printHand(){
        System.out.println("==============  House Cards ========");
        pokerHand.printHand();
    }

    /**
     * The function determines if the computer should stand or not. Computer stands if it has greater than 50% chance of winning.
     *
     * @return true   Computer stands
     * @return false  Computer folds
     */
    public boolean stand(){
        int index = 0;
        int minCombinationValue = Rank.QUEEN.getValue() * 14 + Rank.JACK.getValue();
        if ((pokerHand.deck[index].getSuit().getShortName() == pokerHand.deck[index+1].getSuit().getShortName())||
                (pokerHand.deck[index].getRank().getShortName() == pokerHand.deck[index+1].getRank().getShortName()))
            return true;
        else {
            if (pokerHand.getValue()>=minCombinationValue)
                return true;
            else
                return false;
        }
    }

    /**
     * This function adds a card to the Computer's hand.
     *
     * @param card the card to add to the Computer's hand.
     */
    public void takeCard(Card card) {
        pokerHand.addCard(card);
    }
}