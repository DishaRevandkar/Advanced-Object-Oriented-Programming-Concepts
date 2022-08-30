package simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * The main simulation class Worldclass is used to randomly play songs for 50000 simulations until a duplicate song
 * is found.
 *
 * @author Shreya Pramod    sp3045@rit.edu
 * @author Disha Revandkar  dr6742@rit.edu
 */
public class WorldDance {

    /**
     * the single instance storing the total number of simulations
     */
    private static final int SIMULATION_COUNT = 50000;

    /**
     * the single instance of the random number generator
     */
    private static final Random rand = new Random();

    /**
     * the string value which stores the fileName containing the songs
     */
    private static String fileName;

    /**
     * a hashSet storing the list of songs from the songs file
     */
    private static LinkedHashSet<Song> listOfEntry = new LinkedHashSet<>();

    /**
     * Create the Worlddance environment. This constructor is responsible for seeding the random number generator
     * and initializes the filename as provided by the user.
     *
     * @param filename the file containing all the songs
     * @param seed     the seed for the random number generator
     */
    public WorldDance(String filename, long seed) throws FileNotFoundException {
        setSeed(seed);
        this.fileName = filename;
    }

    /**
     * Set the seed to the pseudorandom number generator
     *
     * @param seed the seed
     */
    public static void setSeed(long seed) {
        rand.setSeed(seed);
    }

    /**
     * Generate a random integer between min and max inclusive.
     *
     * @param min the smallest value allowed.
     * @param max the largest value allowed.
     * @return A random integer
     */
    public static int nextInt(int min, int max) {
        return rand.nextInt(min, max + 1);
    }

    /**
     * This function runs the simulation 50000 times. It takes the songs from the jukebox and plays random songs until a
     * duplicate song is found. Once a duplicate song is found, the currently running simulation stops and the next simulation
     * starts.
     *
     * @param jukeBox the arraylist containing the list of songs from the file of songs provided
     */
    private void runSimulation(ArrayList<Song> jukeBox) {
        System.out.println("Running the simulation. Jukebox starts rockin'...");
        int songCountPerSim = 0, count = 0;
        Map<Song, Integer> songsPlayedInfo = new HashMap<>();
        Set<Song> songPlayed = new HashSet<>();
        List<Integer> avgSong = new ArrayList<>();
        System.out.println("\tPrinting first 5 songs played...");
        int startTime = (int) System.currentTimeMillis();
        while (count < SIMULATION_COUNT) {
            boolean playSongs = true;
            int songInfo = rand.nextInt(0, jukeBox.size());
            while (playSongs) {
                while (!songPlayed.contains(jukeBox.get(songInfo))) {
                    songsPlayedInfo.put(jukeBox.get(songInfo), jukeBox.get(songInfo).getTimesPlayed());
                    songPlayed.add(jukeBox.get(songInfo));

                    firstFiveSongs(songCountPerSim, jukeBox, songInfo);
                    songCountPerSim++;

                    songInfo = rand.nextInt(0, jukeBox.size());
                }
                playSongs = false;
            }
            avgSong.add(songPlayed.size());
            songPlayed = new HashSet<>();
            count++;
        }

        int endTime = (int) System.currentTimeMillis();
        System.out.println("\tSimulation took " + (endTime - startTime) / 1000 + " second/s");

        printStatistics(songsPlayedInfo, avgSong);
    }

    /**
     * Displays the starting five songs when the simulation starts
     *
     * @param songCountPerSim number of songs played in the current simulation
     * @param jukeBox         the arraylist containing the list of songs
     * @param songInfo        the random number indicating the song to be played from the list of songs in the arraylist
     */
    private void firstFiveSongs(int songCountPerSim, ArrayList<Song> jukeBox, int songInfo) {
        if (songCountPerSim < 5) {
            System.out.println("\t\tArtist: " + jukeBox.get(songInfo).getArtist() + ", Title: " + jukeBox.get(songInfo).getTitle());
        }
    }

    /**
     * The functions displays all the statistic as required.
     *
     * @param songsPlayedInfo a hashmap containing information about the number of times a particular song was played
     * @param avgSong         a list containing the total number of times each song was played during the simulation
     */
    private void printStatistics(Map<Song, Integer> songsPlayedInfo, List<Integer> avgSong) {
        int averageSong = 0, totalSongs = 0;
        String commonArtist = "";
        System.out.println("Displaying simulation statistics:");
        System.out.println("\tNumber of simulations run: " + SIMULATION_COUNT);

        for (int val : songsPlayedInfo.values())
            totalSongs += val;
        System.out.println("\tTotal number of songs played: " + totalSongs);

        for (int i = 0; i < avgSong.size(); i++)
            averageSong += avgSong.get(i);

        averageSong = (int) (averageSong / avgSong.size());
        System.out.println("\tAverage number of songs played per simulation to get duplicate: " + averageSong);

        int maxValueInMap = (Collections.max(songsPlayedInfo.values()));  // This will return max value in the Hashmap
        for (Map.Entry<Song, Integer> entry : songsPlayedInfo.entrySet()) {  // Itrate through hashmap
            if (entry.getValue() == maxValueInMap) {
                commonArtist = entry.getKey().getArtist();
                System.out.println("\tMost played song: " + '"' + entry.getKey().getTitle() + '"' + " by " + '"'
                        + entry.getKey().getArtist() + '"');   // Print the key with max value
                break;
            }
        }

        maxPlayedSong(commonArtist, songsPlayedInfo);
    }

    /**
     * This functions gives the information regarding the most frequently played song in the simulation and also prints in
     * alphabetical order the songs that are played by the same artist.
     *
     * @param commonArtist    the artist that plays the frequently played song
     * @param songsPlayedInfo the hashmap containing information regarding songs and the number of times it was played
     */
    private void maxPlayedSong(String commonArtist, Map<Song, Integer> songsPlayedInfo) {
        System.out.println("\tAll songs alphabetically by " + '"' + commonArtist + '"' + ":");
        TreeMap<String, Integer> commonArtistSongs = new TreeMap<>();
        for (Map.Entry<Song, Integer> entry : songsPlayedInfo.entrySet()) {
            if (entry.getKey().getArtist().equals(commonArtist)) {
                commonArtistSongs.put(entry.getKey().getTitle(), songsPlayedInfo.get(entry.getKey()));
            }
        }
        for (String val : commonArtistSongs.keySet())
            System.out.println("\t\t" + '"' + val + '"' + " with " + commonArtistSongs.get(val) + " plays");
    }

    /**
     * Prints the details about the jukebox
     *
     * @param jukeBox     the arraylist containing all the songs
     * @param listOfEntry hashset used to store the songs from the file in order to avoid duplicates
     */
    private void printJukeBoxDetails(ArrayList<Song> jukeBox, LinkedHashSet<Song> listOfEntry) {
        System.out.println("\tJukebox is loaded with " + listOfEntry.size() + " songs");
        System.out.println("\tFirst song in jukebox: Artist: " + jukeBox.get(0).getArtist() + ", Title: " + jukeBox.get(0).getTitle());
        System.out.println("\tLast song in jukebox: Artist: " + jukeBox.get(jukeBox.size() - 1).getArtist() + ", Title: " + jukeBox.get(jukeBox.size() - 1).getTitle());

    }

    /**
     * Function that loads the songs from the file to the jukebox
     *
     * @param fileName the string value containing the filename that has the list of songs
     * @return An arraylist containing the list of all songs without any duplicates
     */
    private ArrayList<Song> loadJukeBox(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        String[] songInfo;

        System.out.println("Loading the jukebox with songs:");
        System.out.println("\tReading songs from " + fileName + " into jukebox...");

        while (sc.hasNextLine()) {
            String songVal = sc.nextLine();
            songInfo = songVal.split("<SEP>", 4);
            Song song = new Song(songInfo[2], songInfo[3]);
            if (listOfEntry.contains(song))
                continue;
            listOfEntry.add(song);
        }

        ArrayList<Song> jukeBox = new ArrayList<>(listOfEntry);
        printJukeBoxDetails(jukeBox, listOfEntry);
        sc.close();

        return jukeBox;
    }

    /**
     * The main method which creates an instance of worldDance and calls the function to start the simulation.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.out.println("Usage: WorldDance #_filename #_seed");
            System.exit(0);
        }
            ArrayList<Song> jukeBox;
            WorldDance wdObject = new WorldDance(args[0], Integer.parseInt(args[1]));
            jukeBox = wdObject.loadJukeBox(fileName);
            wdObject.runSimulation(jukeBox);
    }
}