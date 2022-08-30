package players;

/**
 * A class to represent the predator.
 *
 * @author Disha Revandkar
 * @author Shreya Pramod
 */

public class Predator implements Player{

    /**
     * The chance the predator defeats a soldier.If a die roll of
     * 1-100 is less than or equal to this, the predator defeats the
     * soldier, otherwise the predator loses.
     */
    public final static int CHANCE_TO_BEAT_SOLDIER = 50;

    /**
     * The chance the predator defeats a hostage.  If a die roll of
     * 1-100 is less than or equal to this, the predator defeats the
     * hostage, otherwise the predator loses.
     */
    public final static int CHANCE_TO_BEAT_HOSTAGE = 75;

    public Predator(){
    }

    /**
     * If the predator wins, they display the message, "The predator
     * yells out in triumphant victory over {player}!".
     *
     * @param player the player that loss to the predator
     */
    public void victory(Player player) {
        System.out.println("The predator yells out in triumphant victory over "+player);
    }

    /**
     * If the predator loses, the display the message, "The predator
     * cries out in glorious defeat to {player}!".
     *
     * @param player  the player that defeated the predator
     */
    public void defeat(Player player) {
        System.out.println("The predator cries out in glorious defeat to "+player);
    }

    /**
     * The string representation of the predator is simply, "Predator".
     *
     * @return the predator string
     */
    @Override
    public String toString(){
        return "Predator";
    }
}
