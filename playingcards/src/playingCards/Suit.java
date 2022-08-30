package playingCards;

/**
 * An enumeration representing the suits in a normal poker deck
 *
 * @author RIT CS
 */
public enum Suit {
    HEARTS('H'),
    SPADES('S'),
    DIAMONDS('D'),
    CLUBS('C');

    /**
     * the short name for the suit, e.g. 'H' for hearts
     */
    private final char shortName;

    /**
     * initialize the suit enums
     *
     * @param shortName short name for the suit
     */
    Suit(char shortName) {
        this.shortName = shortName;
    }

    /**
     * accessor for the name
     *
     * @return a char with the short name for this suit
     */
    public char getShortName() {
        return shortName;
    }
}