package toy;

/**
 * An abstract class that implements the IToy interface.
 *
 * @author Disha Revandkar
 * @author Shreya Pramod
 */
public abstract class Toy implements IToy{
    private String name;
    private double MSRP;

    public Toy(String name, double MSRP){
        this.name = name;
        this.MSRP = MSRP;
    }

    /**
     * Returns the toy's name.
     *
     * @return The name of the toy.
     */
    @Override
    public int getProductCode() {
        return this.getProductCode();
    }

    /**
     * Returns the toy's name.
     *
     * @return The name of the toy.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the toy's MSRP.
     *
     * @return The toy's MSRP.
     */
    @Override
    public double getMSRP() {
        return MSRP;
    }

    /**
     * The toy's resale value, which is affected by its current condition.
     * Only {@link Condition#MINT mint} condition toys are worth full price.
     *
     * @return The resale value of the toy, based on its MSRP and it's current
     * condition.
     */
    @Override
    public double getResaleValue() {
        return Math.round((MSRP*(getCondition().getMultiplier())) * 100.0) / 100.0;
    }

    /**
     * Returns the current condition of the toy.
     *
     * @return The toy's current condition.
     */
    @Override
    public Condition getCondition() {
        return Condition.get(getDegradationLevel());
    }

    /**
     * Returns the toy's level of degradation.
     *
     * @return the toy's current level of degradation.
     */
    @Override
    public int getDegradationLevel() {
        return this.getDegradationLevel();
    }

    /**
     * Called whenever the toy is played with. This will have the affect of
     * degrading the toy's condition.
     */
    @Override
    public void play() {
        this.play();
    }
}
