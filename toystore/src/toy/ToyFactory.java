package toy;

import java.util.Random;

/**
 * A Toy Factory!
 *
 * @author RIT
 * @author Disha Revandkar
 * @author Shreya Pramod
 */
public final class ToyFactory {
    /**
     * Used to generate random numbers.
     */
    private static final Random rand = new Random();

    /**
     * Type code used to indicate that the toy factory should make a scooter.
     */
    private static final int SCOOTER = 1;

    /**
     * Type code used to indicate that the toy factory should make a doll.
     */
    private static final int DOLL = 2;

    /**
     * Type code used to indicate that the toy factory should make an action
     * figure.
     */
    private static final int ACTION_FIGURE = 3;

    /**
     * The type code used to indicate that the toy factory should make an RC
     * car.
     */
    private static final int RC_CAR = 4;

    /**
     * The type code used to indicate that the toy factory should make a
     * robot.
     */
    private static final int ROBOT = 5;

    /**
     * The potential names for scooters.
     */
    private static final String[] SCOOTER_NAMES = {
            "Razor A", "Razor A5", "Mongoose Expo", "ENKEEO Kick", "Micro Maxi",
            "Royal Guard II", "Fuzion X-3", "Fuzion Z250 Pro", "VOKUL S2",
            "Xiaomi Mi", "WonderView"
    };

    /**
     * The potential colors for scooters.
     */
    private static final String[] SCOOTER_COLORS = {
            "Black", "Pink", "Green", "Blue", "Cyan", "Purple", "Gold", "Silver",
            "Red"
    };

    /**
     * The potential names for dolls.
     */
    private static final String[] DOLL_NAMES = {
            "Sally", "Johnny", "Barbie", "Ken", "Julio", "Ralph", "Jane", "Sarah",
            "Susan", "Benedetta", "Edwina", "Gloria", "Henriette", "Laura",
            "Marilee", "Norma", "Barbara Ann", "Barbara Lou", "Dora Lee", "Flossie",
            "Ginger", "Hilda", "Lucia Mary Lee", "Babs", "Betty", "Roger", "George",
            "Robert", "Harry", "Bruce", "Willy", "Johnson", "Pete", "Woody", "Buzz",
            "Larry"
    };

    /**
     * The potential colors for hair.
     */
    private static final String[] HAIR_COLOR = {
            "Blond", "Brown", "Brunette", "Purple", "Pink", "Red", "Black",
            "Ginger", "Green", "Blue"
    };

    /**
     * The potential colors for eyes.
     */
    private static final String[] EYE_COLOR = {
            "Blue", "Green", "Black", "Brown", "Purple", "Hazel"
    };

    /**
     * The potential prefixes for action figures.
     * The name of an action figure is the concatenation of one of those prefixes +
     * a doll's name (e.g. Strong Laura)
     */
    private static final String[] ACTION_FIGURE_PREFIXES = {
            "Action", "Lightning", "Strong", "Fast", "Sneaky", "Super", "Tough",
            "'Lasers'", "Stretch", "G.I.", "Quiet"
    };

    /**
     * The potential names for RC Cars.
     */
    private static final String[] RC_CAR_NAMES = {
            "Fistone Truck", "Gizmovine RC", "SPESXFUN", "Hapinic RC", "Amicool",
            "METAKOO RC", "Hosim RC", "KOOWHEEL Car", "Distianert Truck",
            "JEYPOD IMDEN Car"
    };


    /**
     * The potential names for robots.
     */
    private static final String[] ROBOT_NAMES = {
            "Iron Giant", "Mechagodzilla", "Optimus Prime", "Reginald", "Vector",
            "Robby", "R2-D2", "C3PO", "BB8", "Megatron", "Shockwave", "Soundwave",
            "Bumblebee", "iRobot", "Roomba", "Data"
    };

    /**
     * The potential sounds that a robot may make.
     */
    private static final String[] ROBOT_SOUNDS = {
            "Bleep Blorp", "Death to humans!", "One shall stand, one shall fall.",
            "Beepeedeepee", "Oh dear!", "Starscream, you fool!", "Eject!",
            "*radio playing*", "Bzzzzzt!", "I...am...Suuuuperman.",
            "I am not a gun!"
    };

    /**
     * Set the seed to the random number generator
     *
     * @param seed the seed
     */
    public static void setSeed(int seed) {
        rand.setSeed(seed);
    }

    /**
     * Factory method that makes and returns a random toy.
     *
     * @return A new, randomly selected toy.
     */
    public static IToy makeToy() {
        int type = randomInt(1,5);
        if (type == SCOOTER)
            return randomScooter();
        else if (type == DOLL)
            return randomDoll();
        else if (type == ACTION_FIGURE)
            return randomActionFigure();
        else if (type == RC_CAR)
            return randomRCCar();
        else
            return randomRobot();
    }

    /**
     * Makes and returns a scooter with a random name, MSRP, color, and
     * number of wheels (either 2 or 3).
     * The MSRP must be a value between 39.99 and 160.99
     *
     * @return The newly crafted scooter.
     */
    public static IToy randomScooter() {
        String scooterName = ToyFactory.randomString(ToyFactory.SCOOTER_NAMES);
        Double scooterMSRP = ToyFactory.randomDouble(39.99,160.99);
        String scooterColor = ToyFactory.randomString(ToyFactory.SCOOTER_COLORS);
        int scooterWheels = randomInt(2,3);
        Toy toy = new Scooter(scooterName, scooterMSRP, scooterColor, scooterWheels);
        return toy;
    }

    /**
     * Makes and returns a doll with a random name, MSRP, hair color, and eye
     * color.
     * The MSRP must be a value between 12.99 and 60.00
     *
     * @return The newly crafted doll.
     */
    public static IToy randomDoll() {
        String dollName = ToyFactory.randomString(ToyFactory.DOLL_NAMES);
        Double dollMSRP = ToyFactory.randomDouble(12.99,60.00);
        String dollHair = ToyFactory.randomString(ToyFactory.HAIR_COLOR);
        String dollEyeColor = ToyFactory.randomString(ToyFactory.EYE_COLOR);
        Toy toy = new Doll(dollName, dollMSRP, dollHair, dollEyeColor);
        return toy;
    }

    /**
     * Makes and returns an action figure with a random name, MSRP, hair
     * color, eye color, and Kung-Fu Grip (tm) configuration.
     * The MSRP must be a value between 9.99 and 24.99
     *
     * @return The newly crafted action figure.
     */
    public static IToy randomActionFigure() {
        String actionFigurePrefix = ToyFactory.randomString(ToyFactory.ACTION_FIGURE_PREFIXES);
        String actionFigure = ToyFactory.randomString(ToyFactory.DOLL_NAMES);
        String actionFigureName = actionFigurePrefix+" "+actionFigure;
        Double actionFigureMSRP = ToyFactory.randomDouble(9.99, 24.99);
        String actionFigureHair = ToyFactory.randomString(ToyFactory.HAIR_COLOR);
        String actionFigureEyeColor = ToyFactory.randomString(ToyFactory.EYE_COLOR);
        Boolean kungFu = ToyFactory.randomBoolean();
        Toy toy = new ActionFigure(actionFigureName, actionFigureMSRP, actionFigureHair, actionFigureEyeColor, kungFu);
        return toy;
    }

    /**
     * Makes and returns an RC Car with a random name, MSRP, battery type,
     * number of batteries, and scale speed.
     * The number of batteries must be a random value between 1 and 6.
     * The MSRP must be a random value between 19.99 and 159.99.
     * The scale speed must be a random value between 100 and 300.
     *
     * @return The newly crafted RC Car.
     */
    public static IToy randomRCCar() {
        String RCCarName = ToyFactory.randomString(RC_CAR_NAMES);
        Double RCCarMSRP = ToyFactory.randomDouble(19.99, 159.99);
        BatteryType RCCarBatteryType = ToyFactory.randomBatteryType();
        int RCCarBattery = ToyFactory.randomInt(1,6);
        int RCCarSpeed = ToyFactory.randomInt(100,300);
        Toy toy = new RCCar(RCCarName, RCCarMSRP, RCCarBatteryType, RCCarBattery, RCCarSpeed);
        return toy;
    }

    /**
     * Makes and returns a Robot with a random name, MSRP, battery type,
     * number of batteries, and sound.
     * The number of batteries must be a random value between 1 and 6.
     * The MSRP must be a value between 25.99 and 699.99.
     *
     * @return The newly crafted robot.
     */
    public static IToy randomRobot() {
        String robotName = ToyFactory.randomString(ToyFactory.ROBOT_NAMES);
        double robotMSRP = ToyFactory.randomDouble(25.99, 699.99);
        BatteryType robotBatteryType = ToyFactory.randomBatteryType();
        int robotoBatteries = ToyFactory.randomInt(1,6);
        String robotSound = ToyFactory.randomString(ROBOT_SOUNDS);
        Toy toy = new Robot(robotName, robotMSRP, robotBatteryType, robotoBatteries, robotSound);
        return toy;

    }

    //////////////////////////////////////////////////
    //              UTILITY METHODS                 //
    /////////////////////////////////////////////////

    /**
     * Returns a random integer in the range [min, max]
     *
     * @param min The minimum value.
     * @param max The maximum value.
     * @return A random integer in the range [min, max].
     */
    private static int randomInt(int min, int max) {
        int bound = max - min + 1;
        return rand.nextInt(bound) + min;
    }

    /**
     * Returns a random boolean (true or false).
     *
     * @return A random boolean.
     */
    private static boolean randomBoolean() {
        return rand.nextBoolean();
    }

    /**
     * Returns a random double in the range [min, max].
     *
     * @param min The minimum value.
     * @param max The maximum value.
     * @return A random double in the range [min, max].
     */
    private static double randomDouble(double min, double max) {
        double bound = max - min;
        double temp = rand.nextDouble() * bound + min;
        temp = temp * 100;
        int rounded = (int) temp;
        return rounded / 100.0;
    }

    /**
     * Selects a random value from the specified string array.
     *
     * @param values The array of strings from which to choose a random value.
     * @return The value that has been randomly selected from the array of
     * strings.
     */
    private static String randomString(String[] values) {
        int index = randomInt(0, values.length - 1);
        return values[index];
    }

    /**
     * Returns a randomly selected battery type.
     *
     * @return A battery type.
     */
    private static BatteryType randomBatteryType() {
        int batteries = randomInt(0, 5);
        return BatteryType.values()[batteries];
    }
}
