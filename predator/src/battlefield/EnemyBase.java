package battlefield;
import players.*;

import java.util.ArrayList;

/**
 * A class that represents the enemy base.
 * It contains a guard line of guerrillas that are guarding a group of hostages stored in a narrow cave with only an
 * entrance and no way for the hostages to reorder themselves.
 *
 * @author Shreya Pramod    sp3045@rit.edu
 * @author Disha Revandkar  dr6742@rit.edu
 */

public class EnemyBase {

    /**
     * Arraylist representing the Guerillas guarding the enemy base.
     *
     */
    private ArrayList<Guerrilla> queueOfGuerilla;

    /**
     * Arraylist representing the Hostages at the enemy base.
     *
     */
    private ArrayList<Hostage> lineOfHostage;

    /**
     * Unique ID to represent each Guerrilla.
     *
     */
    private int idGuerilla = 1;

    /**
     * Unique ID to represent each Hostage.
     *
     */
    private int idHostage = 1;

    /**
     * variable that stores a dice value which is a random number generated for any player.
     *
     */
    private int dice;

    /**
     * This constructor creates the enemy base with a number of hostages, 1-numHostages,
     * pushed into the cave in order, and a number of guerrillas, 1-numGuerrillas, added to the guard line in order.
     *
     * @param numHostages   the number of hostages to start in enemy base.
     * @param numGuerrillas the number of Guerrillas in the enemy base.
     *
     */
    public EnemyBase(int numHostages, int numGuerrillas){
        this.queueOfGuerilla = new ArrayList<>();;
        this.lineOfHostage = new ArrayList<>();

        while (queueOfGuerilla.size()!=numGuerrillas){
            queueOfGuerilla.add(new Guerrilla(idGuerilla));
            idGuerilla++;
        }

        while (lineOfHostage.size()!=numHostages){
            lineOfHostage.add(0,new Hostage(idHostage));
            idHostage++;
        }
    }

    /**
     * This function adds a guerrilla to the end of the guard line.
     *
     * @param guerrilla the guerrilla to add to the enemy base.
     *
     */
    public void addGuerrilla (Guerrilla guerrilla){
        queueOfGuerilla.add(queueOfGuerilla.size(),guerrilla);
    }

    /**
     * This function adds a hostage to the front of the cave.
     *
     * @param hostage hostage to add to the front of the cave.
     *
     */
    public void addHostage(Hostage hostage){
        lineOfHostage.add(0,hostage);
    }

    /**
     * This function removes a guerrilla from the front of the guard line.
     *
     * @return the guerrilla at the front of the line
     *
     */
    public Guerrilla getGuerrilla(){
        return queueOfGuerilla.remove(0);
    }

    /**
     * This function removes a hostage from the head of the cave.
     *
     * @return the hostage at the head of the cave
     *
     */
    public Hostage getHostage(){
        return lineOfHostage.remove(0);
    }

    /**
     * This function sends a soldier that enters the enemy base and attempts to rescue a hostage.
     * First the soldier must defeat the guerrilla at the front of the guard line, then they rescue one hostage at
     * the front of the cave.
     *
     * @param soldier the rescuing solder
     * @return a hostage if the soldier was successful in defeating the Guerrilla otherwise returns null
     *
     */
    public Hostage rescueHostage(Soldier soldier){
        System.out.println(soldier +" enters the enemy base... ");
        Hostage hostage = getHostage();

        if (queueOfGuerilla.size() == 0)
            return hostage;
        else{
            Guerrilla guerrilla = getGuerrilla();
            dice = Battlefield.nextInt(1,100);

            System.out.println(soldier+" battles "+guerrilla+" who rolls a "+dice);

            // checking if the soldier or Guerrilla wins the fight.
            if(dice > Guerrilla.CHANCE_TO_BEAT_SOLDIER){
                soldier.victory(guerrilla);
                guerrilla.defeat(soldier);
                return hostage;
            }
            else{
                guerrilla.victory(soldier);
                soldier.defeat(guerrilla);

                addHostage(hostage);
                addGuerrilla(guerrilla);
            }
        }
        return null;
    }

    /**
     * This function gets the number of hostages in the cave.
     *
     * @return number of hostages in the enemy base.
     *
     */
    public int getNumHostages(){
        return lineOfHostage.size();
    }

    /**
     * This function gets the number of guerrillas in the guard line.
     *
     * @return number of guerrillas in the enemy base.
     *
     */
    public int getNumGuerrillas(){
        return queueOfGuerilla.size();
    }
}
