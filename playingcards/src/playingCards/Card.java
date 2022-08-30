package playingCards;

/**
 * A class which is used to represent playing card with a rank and suit
 *
 * @author Shreya Pramod    sp3045@rit.edu
 * @author Disha Revandkar  dr6742@rit.edu
 */
public class   Card {

    /**
     *The rank of the card.
     */
    private Rank rank;

    /**
     *The suit of the card.
     */
    private Suit suit;

    /**
     *Default constructor which creates a card of the mentioned rank and suit.
     *
     * @param rank Rank of the card to create
     * @param suit Suit of the card to create
     */
    public Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;

    }

    /**
     *This function returns the rank of the card that is called.
     *
     * @return the rank of the card
     */
    public Rank getRank(){
        return this.rank;
    }

    /**
     *This function returns the shortname of the card including the rank and suit.
     *
     * @return the shortname of the card
     */
    public String getShortName(){
        return (" "+this.rank.getShortName()+this.suit.getShortName());
    }

    /**
     *This function returns the suit of the card.
     *
     * @return the suit of the card
     */
    public Suit getSuit(){
        return this.suit;
    }

    /**
     *This function returns the numeric value of the card.
     *
     * @return the value of the rank of the card
     */
    public int getValue(){
        return this.rank.getValue();
    }

    /**
     *This function returns a long name of the card.
     *
     * @return the long name of the card
     */
    public String toString(){
        return this.rank.toString()+" of "+this.suit.toString();
    }
}