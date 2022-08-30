package simulation;

/**
 * The Song class representing each song in the song file. It contains the title, author and the number of times the song
 * is played during the simulation as the instance fields.
 *
 * @author Shreya Pramod    sp3045@rit.edu
 * @author Disha Revandkar  dr6742@rit.edu
 */
public class Song{

    /**
     * the variable containing the song name
     */
    private String songName;

    /**
     * the variable containing the artist
     */
    private String artist;

    /**
     * the value that stores the number of times a song is played
     */
    private int timesPlayed = 1;

    /**
     * Creates a song and initializes the song name and the artist.
     *
     * @param artist    the value storing the artist name
     * @param songName  the value that stores the song name
     */
    public Song(String artist,String songName){
        this.songName = songName;
        this.artist = artist;
    }

    /**
     * Returns the artist of a particular song
     *
     * @return the artist of the song
     */
    public String getArtist(){
        return this.artist;
    }

    /**
     * Returns the title of a particular song
     *
     * @return the title of the song
     */
    public String getTitle(){
        return this.songName;
    }


    /**
     * The string representation of a song is: "Artist: n, Song: m",
     * where n is the artist name and m is the song name
     *
     * return the song string
     */
    @Override
    public String toString(){
        return ("Artist: "+this.artist+", Song: "+this.songName);
    }

    /**
     * The function compares 2 songs based on the number of times it is played.
     *
     * @return 1 if the calling song is played more than the called song
     * @return 0 if both the songs are played equal number of times
     * @return -1 if the called song is played more than the calling song
     */
    public int compareTo(Song song){
        if (this.getTimesPlayed() > song.getTimesPlayed()) {
            return 1;
        }
        else if (getTimesPlayed() < song.getTimesPlayed()) {
            return -1;
        }
        else {
            return 0;
        }
    }

    /**
     * The function if 2 song objects are equal.
     *
     * @return true  if the song objects are equal
     * @return false if the song objects are not equal
     */
    @Override
    public boolean equals(Object obj){
        if(obj==null)
            return false;
        if(this.getClass()!=obj.getClass())
            return false;

        Song e=(Song)obj;
        return e.songName.equals(this.songName) && e.artist.equals(this.artist);
    }

    /**
     * Returns the hashCode of the Song object
     *
     * @return an integer value containing the hascode of the song name and the hashcode of the song title.
     */
    public int hashCode(){
        return (this.songName.hashCode()+this.artist.hashCode());
    }

    /**
     * Returns the number of times a particular song is played
     *
     * @return the number of times a song is played
     */
    public int getTimesPlayed(){
        return timesPlayed++;
    }
}