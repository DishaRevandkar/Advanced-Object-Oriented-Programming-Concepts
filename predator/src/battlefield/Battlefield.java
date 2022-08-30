package battlefield;
import players.*;

import java.util.Random;

/**
 * The main simulation class is run on the command line as:<br>
 * <br>
 * $ java Battlefield #_seed #_hostages #_soldiers #_guerrillas
 *
 * @author Shreya Pramod    sp3045@rit.edu
 * @author Disha Revandkar  dr6742@rit.edu
 */
public class Battlefield {

    /**
     * the single instance of the random number generator
     */
    private static final Random rnd = new Random();

    /**
     * dice value which would be a random integer generated for each player.
     */
    private int dice;

    /**
     * the command line arguments taken for seed number, number of hostages, number of soldiers and number of Guerrillas.
     */
    private static int seedNo, numHostages, numSoldiers, numGuerrillas;

    /**
     * the single instance of Chopper.
     */
    Chopper chopper;

    /**
     * the single instance of Enemy Base.
     */
    EnemyBase eb;

    /**
     * the single instance of Bunker.
     */
    Bunker bunker;

    /**
     * the single instance of Predator.
     */
    Predator predator;

    /**
     * Create the battlefield.  This method is responsible for seeding the
     * random number generator and initializing all the supporting classes
     * in the simulation: Chopper, EnemyBase, Bunker and Predator.
     *
     * @param seed          the seed for the random number generator
     * @param numHostages   number of hostages being held in enemy base at start
     * @param numSoldiers   number of soldiers to rescue the hostages at start
     * @param numGuerrillas number of guerrillas in the enemy base at start
     */
    public Battlefield(int seed, int numHostages, int numSoldiers, int numGuerrillas) {
        setSeed(seed);
        chopper = new Chopper();
        eb = new EnemyBase(numHostages,numGuerrillas);
        bunker = new Bunker(numSoldiers);
        predator = new Predator();
    }

    /**
     * The main method expects there to be four command line arguments:<br>
     * <br>
     * 1: the seed for the random number generator (a positive integer)<br>
     * 2: the number of hostages (a positive integer)<br>
     * 3: the number of soldiers (a positive integer)<br>
     * 4: the number of guerrillas (a positive integer)<br>
     * <br>
     * If all the arguments are supplied it will create the battlefield
     * and then begin the battle.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Usage: Battlefield #_seed #_hostages #_soldiers #_guerrillas");
            System.exit(0);
        }

        else {
            //checking for valid command line arguments
            for (int i = 0; i < args.length; i++) {
                if (!Character.isDigit(args[i].charAt(0)) || Integer.parseInt(args[i]) < 0) {
                    System.out.println("Please enter valid inputs!");
                    System.exit(0);
                }
            }
                seedNo = Integer.parseInt(args[0]);
                numHostages = Integer.parseInt(args[1]);
                numSoldiers = Integer.parseInt(args[2]);
                numGuerrillas = Integer.parseInt(args[3]);

            Battlefield battlefield = new Battlefield(seedNo, numHostages, numSoldiers, numGuerrillas);
            battlefield.runSimulation();
        }
    }

    /**
     * The main battle simulation loop runs here.
     * The simulation runs until either all the hostages have been rescued, or there are no more
     * soldiers left to rescue hostages.
     *
     */
    public void runSimulation() {

        System.out.println("Get to the choppa!");
        while(bunker.hasSoldiers() && eb.getNumHostages()!=0) {
            System.out.println("Statistics: " + eb.getNumHostages() + " hostage/s remain, " + bunker.getNumSoldiers() + " soldier/s " +
                    "remain, " + eb.getNumGuerrillas() + " guerrilla/s remain, "+chopper.getNumRescued()+ " rescued");
            Soldier soldier = bunker.deployNextSoldier();
            Hostage hostage = eb.rescueHostage(soldier);

            // if soldier is successful in rescuing the hostage then getting the hostage out of the enemy base.
            if (hostage != null) {
                System.out.println(hostage + " rescued from enemy base by " + soldier);

                dice = nextInt(1, 100);
                System.out.println(soldier + " encounters the predator who rolls a " + dice);

                // checking if the soldier or predator wins the fight.
                if (dice > Predator.CHANCE_TO_BEAT_SOLDIER) {
                    soldier.victory(predator);
                    predator.defeat(soldier);

                    chopper.boardPassenger(hostage);
                    bunker.fortifySoldier(soldier);
                } else {
                    predator.victory(soldier);
                    soldier.defeat(predator);

                    dice = nextInt(1, 100);
                    System.out.println(hostage + " encounters the predator who rolls a " + dice);

                    // checking if the hostage or predator wins the fight.
                    if (dice <= Predator.CHANCE_TO_BEAT_HOSTAGE) {
                        predator.victory(hostage);
                        hostage.defeat(predator);
                    } else {
                        hostage.victory(predator);
                        predator.defeat(hostage);
                        chopper.boardPassenger(hostage);
                    }
                }
            }
        }

        //checking if the bunker has soldiers still remaining and if yes, rescue the soldiers
        while (bunker.hasSoldiers()!=false) {
            chopper.boardPassenger(bunker.deployNextSoldier());
        }

        //rescue the passengers if there are still any in the chopper.
        if (!chopper.isEmpty()){
            chopper.rescuePassengers();
        }

        System.out.println("Statistics: " + eb.getNumHostages() + " hostage/s remain, " + bunker.getNumSoldiers() + " soldier/s " +
                "remain, " + eb.getNumGuerrillas() + " guerrilla/s remain, "+ chopper.getNumRescued() +" rescued");
    }

    /**
     * Generate a random integer between min and max inclusive.  For example: <br>
     * <br>
     * <tt>Battlefield.nextInt(1, 5): A random number, 1-5</tt><br>
     * <br>
     *
     * @param min the smallest value allowed.
     * @param max the largest value allowed.
     * @return A random integer
     */
    public static int nextInt(int min, int max) {
        return rnd.nextInt(min, max + 1);
    }

    /**
     * Set the seed to the pseudorandom number generator
     *
     * @param seed the seed
     */
    private static void setSeed(int seed) {
        //setting the seed for the pseudorandom generator
        rnd.setSeed(seed);
    }
}