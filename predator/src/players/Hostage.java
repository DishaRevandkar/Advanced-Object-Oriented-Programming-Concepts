package players;

/**
 * A class to represent a hostage, each of whom has a unique ID.
 *
 * @author Disha Revandkar
 * @author Shreya Pramod
 */
public class Hostage implements Player{

    /**
     * Hostage's id no.
     */
    private int id;

    /**
     * Creates a new hostage.
     *
     * @param id the id of the hostage
     */
    public Hostage(int id){
        this.id = id;
    }

    /**
     * If the hostage is triumphant over player, it displays the
     * message, "{hostage} yells, 'Victory over {player}!'".
     *
     * @param player the player I defeated
     */
    public void victory(Player player){
        System.out.println(this +" yells, 'Victory over "+player+"!");
    }

    /**
     * If the hostage losses to player, it displays the message,
     * "{hostage} cries, 'Defeated by {player}!'".
     *
     * @param player the player that defeated me
     */
    public void defeat(Player player){
        System.out.println(this +" cries, 'Defeated by "+player+"!");
    }

    /**
     * The string representation of a hostage is: "Hostage #n",
     * where n is their id.
     *
     * return the hostage string
     */
    @Override
    public String toString(){
        return ("Hostage #"+this.id);
    }
}
