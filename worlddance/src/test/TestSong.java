package test;

import simulation.Song;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test framework for the simulation.Song class.
 *
 * @author RIT CS
 */
public class TestSong {
    @Test
    public void testSongAccessors() {
        Song song1 = new Song("Lil Nas X", "Old Town Road");
        assertEquals("Lil Nas X", song1.getArtist());
        assertEquals("Old Town Road", song1.getTitle());

        Song song2 = new Song("Tool", "Pneuma");
        assertEquals("Tool", song2.getArtist());
        assertEquals("Pneuma", song2.getTitle());
    }

    @Test
    public void testSongToString() {
        Song song1 = new Song("Chuck Berry", "Sweet Little Sixteen");
        assertEquals("Artist: Chuck Berry, Song: Sweet Little Sixteen", song1.toString());
    }

    @Test
    public void testSongEquals() {
        Song song1 = new Song("Adele", "Rolling in the Deep");
        Song song2 = new Song("Adele", "Rolling in the Deep");
        Song song3 = new Song("Linkin Park", "Rolling in the Deep");
        Song song4 = new Song("Adele", "Hello");

        assertEquals(song1, song2);
        assertNotEquals(song1, song3);
        assertNotEquals(song1, song4);
    }

    @Test
    public void testSongHashCode() {
        Song song1 = new Song("Phish", "Chalk Dust Torture");
        assertEquals(-201310392, song1.hashCode());
        Song song2 = new Song("Phish", "Chalk Dust Torture");
        assertEquals(-201310392, song2.hashCode());
        Song song3 = new Song("Massive Attack", "Angel");
        assertEquals(1115209893, song3.hashCode());
    }

    @Test
    public void testComparable() {
        Song song1 = new Song("Pinkfong", "Baby Shark");
        Song song2 = new Song("Pinkfong", "Baby Shark");
        Song song3 = new Song("Pink Floyd", "Money");
        Song song4 = new Song("Cardi B", "Money");

        assertTrue(song1.compareTo(song2) == 0);
        assertTrue(song1.compareTo(song3) > 0);
        assertTrue(song4.compareTo(song3) < 0);
    }
}