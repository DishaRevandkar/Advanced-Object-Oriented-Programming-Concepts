package players;

/**
 * A class to represent a soldier, each of whom has a unique ID.
 *
 * @author Disha Revandkar
 * @author Shreya Pramod
 */
public class Soldier implements Player{

    /**
     * Soldier's id no.
     */
    private int id;

    /**
     * Create a new soldier.
     *
     * @param id the id of the soldier
     */
    public Soldier(int id){
        this.id = id;
    }

    /**
     * If the soldier is triumphant over player, it displays
     * the message, "{soldier} yells, 'Sieg über {player}!'".
     *
     * @param player the player I defeated
     */
    public void victory(Player player){
        System.out.println(this +" yells, 'Sieg über "+player+"'!");
    }

    /**
     * If the soldier losses to player, it displays the message,
     * "{soldier} cries, 'Besiegt von {player}!'".
     *
     * @param player  the player that defeated me
     */
    public void defeat(Player player){
        System.out.println(this +" cries, 'Besiegt von "+player+"'!");
    }

    /**
     * The string representation of a soldier is: "Soldier #n",
     * where n is their id.
     *
     * return the soldier string
     */
    @Override
    public String toString(){
        return ("Soldier #"+this.id);
    }
}
