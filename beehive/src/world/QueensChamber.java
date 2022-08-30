package world;
import bee.Drone;
import bee.Queen;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *The queen's chamber is where the mating ritual between the queen and her drones is conducted.
 *The drones will enter the chamber in order. If the queen is ready and a drone is in here, the first drone will be summoned and mate
 * with the queen. Otherwise the drone has to wait. After a drone mates they perish, which is why there is no routine for
 * exiting (like with the worker bees and the flower field).
 *
 * @author Shreya Pramod sp3045@rit.edu
 * @author Disha Revandkar dr6742@rit.edu
 */
public class QueensChamber{

    /** the list of drones entering the chamber */
    Queue<Drone> droneList;

    /** drone object that would be summoned for mating */
    Drone drone;

    /** boolean flag to check if queen is ready to mate*/
    public static boolean flag = true;

    /**
     * Create the chamber.
     *
     */
    public QueensChamber(){
        droneList = new ConcurrentLinkedQueue<>();
    }

    /**
     * A drone enters the chamber. The first thing you should display is:
     *
     * *QC* {bee} enters chamber
     *
     * The bees should be stored in some queue like collection. If the queen is ready and this drone is at the front of the collection,
     * they are allowed to mate. Otherwise they must wait.When the drone leaves this method, display the message:
     *
     * *QC* {bee} leaves chamber
     *
     * @param drone the drone who just entered the chamber
     */
    public void enterChamber(Drone drone){
        System.out.println("*QC* "+drone+" enters chamber");
        droneList.add(drone);
        if (flag){
            try {
                drone.sleep(Queen.MATE_TIME_MS);
            }catch (InterruptedException e){
                System.out.println(e);
            }

            //drone.setMated();

            System.out.println("*QC* "+drone+" leaves chamber");
            flag = false;
        }
    }

    /**
     * When the queen is ready, they will summon the next drone from the collection (if at least one is there).
     * The queen will mate with the first drone and display a message:
     *
     * *QC* Queen mates with {bee}
     *
     * It is the job of the queen if mating to notify all of the waiting drones so that the first one can be selected since we can't
     * control which drone will unblock. Doing a notify will lead to deadlock if the drone that unblocks is not the front one.
     */
    public void summonDrone(){
        if (!droneList.isEmpty()){
            drone = droneList.poll();
            System.out.println("*QC* Queen mates with "+drone);
        }
    }

    /**
     * At the end of the simulation the queen uses this routine repeatedly to dismiss all the drones that were waiting to mate.
     *
     */
    public void dismissDrone(){
        for(Drone drone : droneList){
            droneList.poll();
        }
    }

    /**
     * Are there any waiting drones? The queen uses this to check if she can mate, and also in conjunction with dismissDrone().
     *
     * @return true if dronelist has drones
     * @return false if there are no drones in the dronelist
     */
    public boolean hasDrone(){
        if (droneList.size()>1)
            return true;
        return false;
    }
}
