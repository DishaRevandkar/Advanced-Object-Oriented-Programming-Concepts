package toy;

/**
 * A class to represent the Robot toy.
 *
 * @author Disha Revandkar
 * @author Shreya Pramod
 */
public class Robot extends Toy{

    /**
     * Battery type of Robot.
     */
    private BatteryType robotBatteryType;

    /**
     * Number of batteries Robot must have.
     */
    private int robotoBatteries;

    /**
     * Sound Robot must have when played with.
     */
    private String robotSounds;

    /**
     * Counter for the productCode.
     */
    private static int count = 0;

    /**
     * The 7-digit product code always starts with a ”7” and is automatically assigned
     * when the Robot is manufactured.
     */
    private int productCode = 7000000;

    /**
     * Each time the play method is called its degradation level is increased by 20%
     */
    private int degradationLevel;

    /**
     * Battery level of doll.
     */
    int batteryLevel = 100;

    /**
     * Creates a new doll.
     *
     * @param robotName         name of the Robot
     * @param robotMSRP         MSRP value of the Robot
     * @param robotoBatteries   no. of batteries of the Robot
     * @param robotBatteryType  battery type of the Robot
     * @param robotSound        sound of Robot
     */
    public Robot(String robotName, double robotMSRP, BatteryType robotBatteryType, int robotoBatteries, String robotSound) {
        super(robotName,robotMSRP);
        this.robotBatteryType = robotBatteryType;
        this.robotoBatteries = robotoBatteries;
        this.robotSounds = robotSound;
        this.productCode+= count;
        count++;
    }

    /**
     * Returns the Robot toy's product code.
     *
     * @return The Robot toy's product code.
     */
    @Override
    public int getProductCode() {
        return this.productCode;
    }

    /**
     * Returns the Robot toy's name.
     *
     * @return The name of the Robot toy.
     */
    @Override
    public String toString(){
        String MSRPval = String.format("%.2f", this.getMSRP());
        String resaleVal = String.format("%.2f", this.getResaleValue());
        return (this.getName()+" [product code="+getProductCode()+", MSRP="+MSRPval+", degradation level="+this.getDegradationLevel()
                +"%, condition="+this.getCondition()+", resale value="+resaleVal+", battery type="+this.robotBatteryType+", number of batteries="+
                this.robotoBatteries+", battery level="+this.batteryLevel+"%, sound="+this.robotSounds+"]");
    }

    /**
     * Returns the Robot toy's level of degradation.
     * Each time the play method is called Doll's degradation level is increased by 20%
     *
     * @return the Robot toy's current level of degradation.
     */
    public int getDegradationLevel() {
        return this.degradationLevel;
    }

    /**
     * Called whenever the toy Robot is played with. This will have the affect of
     * degrading the toy's condition.
     */
    @Override
    public void play() {
        if (this.batteryLevel == 0) {
            System.out.println("The batteries are dead! Let's replace them...");
            this.batteryLevel = 100;
            this.batteryLevel-=25;
        }
        else if (this.batteryLevel-25<0)
            this.batteryLevel = 0;
        else {
            this.batteryLevel -= 25;
        }
        System.out.println(this.getName()+" goes "+"'"+this.robotSounds+"'");
        if (100-this.degradationLevel>=20)
            this.degradationLevel+=20;
        else
            this.degradationLevel = 100;
        System.out.println("After play, "+this.getName()+"'s condition is "+Condition.get(getDegradationLevel()));
    }
}
