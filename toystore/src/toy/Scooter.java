package toy;

/**
 * A class to represent the Scooter toy.
 *
 * @author Disha Revandkar
 * @author Shreya Pramod
 */
public class Scooter extends Toy {

    /**
     * Color of the Scooter.
     */
    private String color;

    /**
     * No of wheels of the Scooter.
     */
    private int wheels;

    /**
     * The 7-digit product code always starts with a ”9” and is automatically assigned
     * when the RC Car is manufactured.
     */
    private int productCode = 9000000;

    /**
     * Counter for the productCode.
     */
    private static int count = 0;

    /**
     * Each time the play method is called its degradation level is increased by 5%
     */
    private int degradationLevel;

    /**
     * Total miles covered by the Scooter.
     */
    private int odometerVal=0;

    /**
     * Creates a new doll.
     *
     * @param scooterName    name of the Scooter
     * @param scooterMSRP    MSRP value of the Scooter
     * @param scooterColor   Color of the Scooter
     * @param scooterWheels  no. of wheels the Scooter
     */
    public Scooter(String scooterName, Double scooterMSRP, String scooterColor, int scooterWheels) {
        super(scooterName,scooterMSRP);
        color = scooterColor;
        wheels = scooterWheels;
        this.productCode+= count;
        count++;
    }

    /**
     * Returns the Scooter toy's product code.
     *
     * @return The Scooter toy's product code.
     */
    public int getProductCode() {
        return this.productCode;
    }

    /**
     * Returns the Scooter toy's name.
     *
     * @return The name of the Scooter toy.
     */
    @Override
    public String toString(){
        return (this.getName()+" [product code="+getProductCode()+", MSRP="+String.format("%.2f", this.getMSRP())+", degradation level="+this.getDegradationLevel()
                +"%, condition="+this.getCondition()+", resale value="+String.format("%.2f", this.getResaleValue())+", color="+this.color+", wheels="+this.wheels
                +", odometer="+this.odometerVal+"]");
    }

    /**
     * Returns the Scooter toy's level of degradation.
     * Each time the play method is called Doll's degradation level is increased by 5%
     *
     * @return the Scooter toy's current level of degradation.
     */
    public int getDegradationLevel() {
        return this.degradationLevel;
    }

    /**
     * Called whenever the toy Scooter is played with. This will have the affect of
     * degrading the toy's condition.
     */
    @Override
    public void play() {
        this.odometerVal+=2;
        if (100-this.degradationLevel>=5)
            this.degradationLevel+=5;
        else
            this.degradationLevel = 100;
        System.out.println("After play, "+this.getName()+"'s condition is "+Condition.get(getDegradationLevel()));
    }
}

