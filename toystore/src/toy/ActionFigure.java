package toy;

/**
 * A class to represent the Action Figure toy.
 *
 * @author Disha Revandkar
 * @author Shreya Pramod
 */
public class ActionFigure extends Toy{

    /**
     * Action Figure's hair color.
     */
    String hairColor;

    /**
     * Action Figure's eye color.
     */
    String eyeColor;

    /**
     * Action Figure may or may not have Kung-Fu GripT
     */
    Boolean kungFuConfig;

    /**
     * The 7-digit product code always starts with a ”5” and is automatically assigned
     * when the action figure is manufactured
     */
    private int productCode = 5000000;

    /**
     * Each time the play method is called its degradation level is increased by 10%.
     */
    private int degradationLevel;

    /**
     * Counter for the productCode.
     */
    private static int count = 0;

    /**
     * Creates a new doll.
     *
     * @param actionFigureName     name of the doll
     * @param actionFigureMSRP    MSRP value of the Action Figure
     * @param actionFigureHair     hair color of the Action Figure
     * @param actionFigureEyeColor eye color of the Action Figure
     */
    public ActionFigure(String actionFigureName, Double actionFigureMSRP, String actionFigureHair, String actionFigureEyeColor, Boolean kungFu) {
        super(actionFigureName,actionFigureMSRP);
        hairColor = actionFigureHair;
        eyeColor = actionFigureEyeColor;
        kungFuConfig = kungFu;
        this.productCode+= count;
        count++;
    }

    /**
     * Returns the Action Figure toy's product code.
     *
     * @return The Action Figure toy's product code.
     */
    @Override
    public int getProductCode() {
        return this.productCode;
    }

    /**
     * Returns the Action Figure toy's name.
     *
     * @return The name of the Action Figure toy.
     */
    @Override
    public String toString(){
        return (this.getName()+" [product code="+getProductCode()+", MSRP="+String.format("%.2f", this.getMSRP())+", degradation level="+this.getDegradationLevel()
                +"%, condition="+this.getCondition()+", resale value="+String.format("%.2f", this.getResaleValue())+", hair color="+hairColor+", eye color="+eyeColor
                +", kung-fu grip="+this.kungFuConfig+"]");
    }

    /**
     * Returns the Action Figure toy's level of degradation.
     * Each time the play method is called Doll's degradation level is increased by 10%.
     *
     * @return the Action Figure toy's current level of degradation.
     */
    public int getDegradationLevel() {
        return this.degradationLevel;
    }

    /**
     * Called whenever the toy Action Figure is played with. This will have the affect of
     * degrading the toy's condition.
     */
    @Override
    public void play() {
        if (100-this.degradationLevel>=10)
            this.degradationLevel+=10;
        else
            this.degradationLevel = 100;
        System.out.println("After play, "+this.getName()+"'s condition is "+Condition.get(getDegradationLevel()));
    }
}
