package simulation;
import java.util.LinkedHashSet;

class Jukebox<Song> extends LinkedHashSet<Song> {

    private Song last = null;
    private int lastIndex = 0;

    public Song getLast() {
        return last;
    }

}