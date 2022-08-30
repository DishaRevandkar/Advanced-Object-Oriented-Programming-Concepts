package miniPoker;

import playingCards.Card;
import java.util.Scanner;

/**
 * A class representing a Human Player for a 2-player poker game.
 *
 * @author Shreya Pramod    sp3045@rit.edu
 * @author Disha Revandkar  dr6742@rit.edu
 */
public class Human {

    /**
     * The Scanner object which takes in the user input.
     */
    private Scanner in;

    /**
     * Creating an instance of type PokerHand
     */
    PokerHand pokerHand;

    /**
     * Default constructor for Human which stores the user input and creates a new instance of PokerHand class.
     */
    public Human(Scanner in){
        this.in = in;
        pokerHand = new PokerHand();
    }

    /**
     * The function compares the Human hand with the respective Computers hand.
     *
     * @param   player  the player hand value to be compared with the Computer hand value
     *
     * @return -1 human hand value is less than the Computer hand value
     * @return  0 human hand value is equal to the Computer hand value
     * @return  1 human hand value is greater than the Computer hand value
     */
    public int compareTo(Computer player){
        if (this.getValue()< player.getValue())
            return -1;
        else if (this.getValue() == player.getValue())
            return 0;
        else
            return 1;
    }

    /**
     * This function clears out all the hands of the Human.
     */
    public void newHand(){
        pokerHand.deck = new Card[2];
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
     * This function prompts the player with a message if they want to stand("Y") or fold("N").
     *
     * @return true  Player wants to stand
     * @return false Player wants to fold
     */
    public boolean stand(){
        char standValue;
        System.out.println("Do you want to stand (y/n)? ");

        standValue = this.in.next().toLowerCase().charAt(0);

        if (standValue == 'y')
            return true;
        else
            return false;
    }

    /**
     * This function prints the current hand in a "nice" format.
     */
    public void printHand(){
        System.out.println("==============  Your Cards  ========");
        pokerHand.printHand();
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