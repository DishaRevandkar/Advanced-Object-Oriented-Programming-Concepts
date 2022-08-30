package toy;

/**
 * A class to represent the RCCar toy.
 *
 * @author Disha Revandkar
 * @author Shreya Pramod
 */
public class RCCar extends Toy{

    /**
     * Battery type of rcCar.
     */
    BatteryType rcCarBatteryType;

    /**
     * The 7-digit product code always starts with a ”6” and is automatically assigned
     * when the RC Car is manufactured.
     */
    private int productCode = 6000000;

    /**
     * Number of batteries RCCar must have.
     */
    private int rcCarBattery;

    /**
     * Speed of RCCar.
     */
    private int rcCarSpeed;

    /**
     * Counter for the productCode.
     */
    private static int count = 0;

    /**
     * Each time the play method is called its degradation level is increased by 15%
     */
    private int degradationLevel;

    /**
     * Battery level of RCCar.
     */
    int batteryLevel = 100;

    /**
     * Creates a new doll.
     *
     * @param rcCarName        name of the RCCar
     * @param rcCarMSRP        MSRP value of the RCCar
     * @param rcCarBattery     no. of batteries of the RCCar
     * @param rcCarBatteryType battery type of the RCCar
     * @param rcCarSpeed       speed of RCCar
     */
    public RCCar(String rcCarName, Double rcCarMSRP, BatteryType rcCarBatteryType, int rcCarBattery, int rcCarSpeed) {
        super(rcCarName,rcCarMSRP);
        this.rcCarBatteryType = rcCarBatteryType;
        this.rcCarBattery = rcCarBattery;
        this.rcCarSpeed = rcCarSpeed;
        this.productCode+= count;
        count++;
    }

    /**
     * Returns the RCCar toy's product code.
     *
     * @return The RCCar toy's product code.
     */
    public int getProductCode() {
        return this.productCode;
    }

    /**
     * Returns the RCCar toy's name.
     *
     * @return The name of the RCCar toy.
     */
    @Override
    public String toString(){
        return (this.getName()+" [product code="+getProductCode()+", MSRP="+String.format("%.2f", this.getMSRP())+", degradation level="+this.getDegradationLevel()
                +"%, condition="+this.getCondition()+", resale value="+String.format("%.2f", this.getResaleValue())+", battery type="+rcCarBatteryType+", number of batteries="+rcCarBattery
                +", battery level="+this.batteryLevel+"%, speed="+this.rcCarSpeed+"]");
    }

    /**
     * Returns the RCCar toy's level of degradation.
     * Each time the play method is called Doll's degradation level is increased by 15%
     *
     * @return the RCCar toy's current level of degradation.
     */
    public int getDegradationLevel() {
        return this.degradationLevel;
    }

    /**
     * Called whenever the toy RCCar is played with. This will have the affect of
     * degrading the toy's condition.
     */
    @Override
    public void play() {
        if (this.batteryLevel == 0) {
            System.out.println("The batteries are dead! Let's replace them...");
            this.batteryLevel = 100;
            this.batteryLevel-=30;
        }
        else if (this.batteryLevel-30<0) {
            this.batteryLevel = 0;
        }
        else {
            this.batteryLevel -= 30;
        }
        System.out.println(this.getName()+" races in circles at "+this.rcCarSpeed+" mph!");
        if (100-this.degradationLevel>=15)
            this.degradationLevel+=15;
        else
            this.degradationLevel = 100;
        System.out.println("After play, "+this.getName()+"'s condition is "+Condition.get(getDegradationLevel()));
    }
}
