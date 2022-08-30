package battlefield;

import players.*;
import java.util.ArrayList;

/**
 * A class that represents the chopper.
 * The chopper can hold up to 6 passengers aligned in a single columns of seats.
 * There is only one door to the chopper that is accessible by the passengers.
 * When they enter the chopper, the occupy the seat closest to the door and any existing passengers move one seat down.
 *
 * In order to preserve fuel, the chopper will only fly the passengers away to safety if the chopper is full,
 * or it is the last group of people to rescue.
 *
 * @author Shreya Pramod    sp3045@rit.edu
 * @author Disha Revandkar  dr6742@rit.edu
 */

public class Chopper {

    /**
     * Arraylist used to represent the passengers in the Chopper.
     */
    private ArrayList<Player> escapeList;

    /**
     * variable used to store the number of people in the chopper.
     */
    private static int sizeOfEscapeList;

    /**
     * This constructor creates the chopper so that all the passenger seats are empty and none have been rescued yet.
     *
     */
    public Chopper(){
        escapeList = new ArrayList<>();
    }

    /**
     * This function is executed when the chopper is full, or it is the last group of people to be rescued,
     * it will fly away and rescue the passengers. Each passenger exits the chopper in the reverse order they entered it,
     * e.g. the last passenger to enter exits first.
     *
     */
    public void rescuePassengers(){
        if (escapeList.size() == 6 || !escapeList.isEmpty()){
            while (!isEmpty()){
                System.out.println("Chopper transported "+escapeList.get(0)+" to safety!");
                escapeList.remove(0);
                sizeOfEscapeList++;
            }
        }
    }

    /**
     * This function board a passenger onto the chopper. If the chopper is full, it must first fly away and
     * rescue the passengers on it . Otherwise, the passenger boards the chopper and occupies the front seat,
     * making everyone else in the chopper move down a seat.
     *
     */
    public void boardPassenger(Player player){
        if (isFull())
            rescuePassengers();
        escapeList.add(0,player);
        System.out.println(player+" boards the chopper!");
    }

    /**
     * This function checks if the chopper is full or not.
     *
     * @return  Whether the chopper has reached max occupancy or not
     */

    public boolean isFull(){
        return (escapeList.size() == 6);
    }

    /**
     * This function checks if the chopper is empty or not.
     *
     * @return  whether the chopper is empty or not.
     */
    public boolean isEmpty(){
        return (escapeList.size() == 0);
    }

    /**
     * This function returns the total passengers rescued
     *
     * @return  number of rescued passengers
     */
    public int getNumRescued(){
        return sizeOfEscapeList;
    }
}
