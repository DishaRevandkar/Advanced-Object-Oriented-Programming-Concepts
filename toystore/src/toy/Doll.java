package toy;

/**
 * A class to represent the Doll toy.
 *
 * @author Disha Revandkar
 * @author Shreya Pramod
 */
public class Doll extends Toy{

    /**
     * Doll's hair color.
     */
    private String hairColor;

    /**
     * Doll's eye color.
     */
    private String eyeColor;

    /**
     * The 7-digit product code always starts with a ”3” and is automatically assigned
     * when the doll is manufactured.
     */
    private int productCode = 3000000;

    /**
     * Each time the play method is called its degradation level is increased by 17%
     */
    private int degradationLevel;

    /**
     * Counter for the productCode.
     */
    private static int count = 0;

    /**
     * Battery level of doll.
     */
    int batteryLevel = 100;

    /**
     * Creates a new doll.
     *
     * @param dollName     name of the doll
     * @param dollMSRP     MSRP value of the doll
     * @param dollHair     hair color of the doll
     * @param dollEyeColor eye color of the doll
     */
    public Doll(String dollName, double dollMSRP, String dollHair, String dollEyeColor) {
        super(dollName,dollMSRP);
        hairColor = dollHair;
        eyeColor = dollEyeColor;
        this.productCode+= count;
        count++;
    }

    /**
     * Returns the Doll toy's product code.
     *
     * @return The Doll toy's product code.
     */
    @Override
    public int getProductCode() {
        return this.productCode;
    }

    /**
     * Returns the Doll toy's name.
     *
     * @return The name of the Doll toy.
     */
    @Override
    public String toString(){
        return (this.getName()+" [product code="+getProductCode()+", MSRP="+String.format("%.2f", this.getMSRP())+", degradation level="+this.getDegradationLevel()
                +"%, condition="+this.getCondition()+", resale value="+String.format("%.2f", this.getResaleValue())+", hair color="+hairColor+", eye color="+eyeColor
                +"]");
    }

    /**
     * Returns the Doll toy's level of degradation.
     * Each time the play method is called Doll's degradation level is increased by 17%
     *
     * @return the Doll toy's current level of degradation.
     */
    public int getDegradationLevel() {
       return this.degradationLevel;
    }

    /**
     * Called whenever the toy Doll is played with. This will have the affect of
     * degrading the toy's condition.
     */
    @Override
    public void play() {
        if (batteryLevel-this.degradationLevel>=17)
            this.degradationLevel+=17;
        else
            this.degradationLevel = batteryLevel;
        System.out.println("After play, "+this.getName()+"'s condition is "+Condition.get(getDegradationLevel()));
    }
}
