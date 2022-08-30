package world;
import bee.Worker;

/**
 *The field of flowers that are ripe for the worker bees to gather the nectar and pollen resources.
 * The bees can arrive in any order and they are immediately allowed to start gathering, as long as there is a free flower.
 * Otherwise the bee must wait until a flower becomes free.
 *
 * @author Shreya Pramod sp3045@rit.edu
 * @author Disha Revandkar dr6742@rit.edu
 */
public class FlowerField {

    /** the maximum workers allowed in the flowerfield */
    public static final int MAX_WORKERS = 10;

    /** counter to check for the current number of bees that entered the field */
    public int no_bee;

    /**
     * Create the flower field and initializing the initial number of bees to 0.
     *
     */
    public FlowerField(){
        this.no_bee = 0;
    }

    /**
     *When a worker bee requests entry in to the field, you should first display a message:
     *
     * *FF* {bee} enters field
     *
     * There is only one condition that would cause a bee to have to wait - if there are no flowers because all the other bees are
     * gathering from them.
     *
     */
    public synchronized void enterField(Worker worker){
        System.out.println("*FF* "+worker+" enters field");
        while (no_bee >= MAX_WORKERS) {
            try {
                this.wait();
            }catch (InterruptedException e){
                System.out.println(e);
            }
        }
        no_bee++;
    }

    /**
     When a worker bee is done gathering from a flower, it uses this routine to indicate they are leaving,
     and to notify a single bees that may be waiting that they should wake up and check that there is indeed a free flower now.
     *
     * @param worker the worker bee leaving the field
     */
    public synchronized void exitField(Worker worker){
        System.out.println("*FF* "+worker+" leaves field");
        no_bee--;
        this.notifyAll();
    }
}