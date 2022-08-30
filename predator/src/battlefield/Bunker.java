package battlefield;
import players.*;

import java.util.ArrayList;

/**
 * A class that represents the bunker of soldiers.
 *
 * @author Shreya Pramod    sp3045@rit.edu
 * @author Disha Revandkar  dr6742@rit.edu
 */
public class Bunker {

    /**
     * Value to store the number of soldiers
     */
    private int soldiers;

    /**
     * creating an Arraylist to represent the soldiers in the bunker.
     */
    private ArrayList<Soldier> queue;

    /**
     * Unique id representing each soldier.
     */
    private int id=1;

    /**
     * This constructor creates the bunker. Based on the total number of soldiers,
     * each one should be created here and then added to the bunker, with id's ranging from 1-numSoldiers.
     *
     * @param soldier the total number of soldiers that start in the bunker
     */
    public Bunker(int soldier){
        this.soldiers = soldier;
        this.queue = new ArrayList<>();
        while(queue.size()!= this.soldiers){
            queue.add(new Soldier(id));
            id++;
        }
    }

    /**
     * This function check if there are any soldiers left in the bunker.
     *
     * @return whether the bunker has soldiers or not
     */
    public boolean hasSoldiers(){
        return queue.size()!=0;
    }

    /**
     * This function removes the next soldier from the front of the bunker to be deployed on a rescue attempt.
     *
     * @return the soldier at the front of the bunker
     */
    public Soldier deployNextSoldier(){
        return queue.remove(0);
    }

    /**
     * This function adds a new soldier to the end of the bunker.
     *
     * @param soldier the new soldier to add
     */
    public void fortifySoldier(Soldier soldier){
        queue.add(soldier);
    }

    /**
     * This function gets the number of soldiers in the bunker.
     *
     * @return number of soldiers in the bunker
     */
    public int getNumSoldiers(){
        return queue.size();
    }
}
