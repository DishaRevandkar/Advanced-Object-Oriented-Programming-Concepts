package players;

/**
 * An interface to represent a player in the game. A player is any entity
 * that can come into conflict with another player, where battle yields
 * one victor and one loser.
 *
 * @author Disha Revandkar
 * @author Shreya Pramod
 */
public interface Player {

    /**
     * when this player defeats another player.
     *
     * @param player the player I defeated
     */
    void victory (Player player);

    /**
     * when this player loses to another player.
     *
     * @param player the player I defeated
     */
    void defeat (Player player);

}
