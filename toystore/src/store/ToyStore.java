package store;
import toy.IToy;
import toy.ToyFactory;

/**
 * Runs a simulation during which a toy store is stocked with toys and each
 * customer 1.) purchases a toy, 2.) plays with it (degrading its condition),
 * and 3.) sells it back to the store whereupon it is discarded if broken. The
 * simulation runs until the store is out of stock.
 *
 * @author Disha Revandkar
 * @author Shreya Pramod
 */
public class ToyStore {
    /**
     * The main method. It checks the number of command line arguments,
     * then stocks the toy store and plays the simulation.
     *
     * @param args Specifies a number of toys (integer) for stocking the toy store and
     *             a seed (integer) for the toy's type random number generator
     */
    public static void main(String[] args) {
        if (args.length!=2)
            System.out.println("$ Usage: java ToyStore #_toys #_seed");
        int numToys = Integer.parseInt(args[0]);
        int seed = Integer.parseInt(args[1]);
        startSimulation(numToys,seed);
        System.out.println("The toy store is out of stock! Time to close!");
    }

    /**
     * The startSimulation method runs the entire AI Toy Store Simulation.
     * @param numToys number of toys for stocking the toy store
     * @param seed    a seed for the toy's type random number generator
     */
    public static void startSimulation(int numToys, int seed) {
        AlsToyBarn object = new AlsToyBarn(numToys, seed);
        while(object.availableStock()!=0){
            IToy toy = object.purchaseToy();
            System.out.println("The next customer purchases "+toy+" and begins to play with it...");
            toy.play();
            toy.getResaleValue();
            object.addToy(toy);
            System.out.println("The customer grows bored with the toy and trades it in for $"+String.format("%.2f", toy.getResaleValue()));
        }
    }
}
