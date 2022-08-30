package playingCards;

/**
 * An enumeration representing the ranks in a normal poker deck
 *
 * @author RIT CS
 */
public enum Rank {
    ACE("A", 14),
    KING("K", 13),
    QUEEN("Q", 12),
    JACK("J", 11),
    TEN("10", 10),
    NINE("9", 9),
    EIGHT("8", 8),
    SEVEN("7", 7),
    SIX("6", 6),
    FIVE("5", 5),
    FOUR("4", 4),
    THREE("3", 3),
    DEUCE("2", 2);

    /**
     * the short name for the rank, e.g. " A", for ace
     */
    private final String shortName;
    /**
     * the numeric value of the card
     */
    private final int value;

    /**
     * Initialize the ranks enums
     *
     * @param shortName  short name for the rank
     * @param value the value of the rank
     */
    Rank(String shortName, int value){
        this.shortName = shortName;
        this.value = value;
    }

    /**
     * accessor for the name
     *
     * @return a string with the short name for this rank
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * accessor for the value
     *
     * @return an int for the value of the rank
     */
    public int getValue() {
        return value;
    }
}
