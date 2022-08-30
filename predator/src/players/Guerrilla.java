package players;

/**
 * A class to represent a guerrilla, each whom has a unique ID.
 *
 * @author Disha Revandkar
 * @author Shreya Pramod
 */

public class Guerrilla implements Player{
    /**
     * The chance the guerrilla has to defeat a soldier. If a die
     * roll of 1-100 is less than or equal to this, the guerrilla
     * defeats the soldier, otherwise the guerrilla loses.
     */
    public final static int CHANCE_TO_BEAT_SOLDIER = 20;

    /**
     * Guerrilla's id no.
     */
    private int id;

    /**
     * Creates a new guerrilla.
     *
     * @param id the id of the guerrilla
     */
    public Guerrilla(int id){
        this.id = id;
    }

    /**
     * If the guerrilla is triumphant over player, it displays the message,
     * "{guerrilla} yells, 'Victoria sobre {player}!'".
     *
     * @param player the player I defeated
     */
    public void victory(Player player){
        System.out.println(this+ " yells, 'Victoria sobre "+ player +"'!");
    }

    /**
     * If the guerrilla losses to player, it displays the message,
     * "{guerrilla} cries, 'Derrotado por {player}!'".
     *
     * @param player player that defeated me
     */
    public void defeat(Player player){
        System.out.println(this+ " cries, 'Derrotado por "+ player +"'!");
    }

    /**
     * The string representation of a guerrilla is: "Guerrilla #n",
     * where n is their id.
     *
     * @return Guerilla String
     */
    @Override
    public String toString(){
        return ("Guerrilla #"+this.id);
    }
}