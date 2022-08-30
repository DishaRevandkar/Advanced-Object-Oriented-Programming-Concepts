package bee;
import world.BeeHive;

/**
 * The male drone bee has a tough life.  His only job is to mate with the queen
 * by entering the queen's chamber.
 *
 * @author RIT CS
 * @author Shreya Pramod sp3045@rit.edu
 * @author Disha Revandkar dr6742@rit.edu
 */
public class Drone extends Bee {

    //QueensChamber queensChamber;
    public static boolean droneMated = false;
    /**
     * When the drone is created they should retrieve the queen's
     * chamber from the bee hive and initially the drone has not mated.
     *
     * @param beeHive the bee hive
     */
    protected Drone(BeeHive beeHive){
        super(Role.DRONE, beeHive);
    }

    public void setMated(){
        //beeHive.getQueensChamber();
        droneMated = true;
    }

    /**
     * When the drone runs, they check if the bee hive is active.  If so,
     * they perform their sole task of entering the queen's chamber.
     * If they return from the chamber, it can mean only one of two
     * things.  If they mated with the queen, they sleep for the
     * required mating time, and then perish (the beehive should be
     * notified of this tragic event).  You should display a message:<br>
     * <br>
     * <tt>*D* {bee} has perished!</tt><br>
     * <br>
     * <br>
     * Otherwise if the drone has not mated it means they survived the
     * simulation and they should end their run without any
     * sleeping.
     */
    public void run() {
        // TODO YOUR CODE HERE
        if(beeHive.isActive()) {
            beeHive.getQueensChamber().enterChamber(this);
            if (!this.isAlive()) {
                if (droneMated) {
                    try {
                        this.sleep(Queen.SLEEP_TIME_MS);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    beeHive.beePerished(this);
                    System.out.println("*D* " + this + " has perished!");
                } else {
                    try {
                        this.join();
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
            }
        }
    }
}