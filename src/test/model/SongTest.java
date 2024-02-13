package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SongTest {
    Song testSong;

    @BeforeEach
    public void runBefore() {
        testSong = new Song("Fractured Angel", "DJ Raisei", 3, 10, 301);
    }

    @Test
    public void ConstructorTest() {
        assertEquals("Fractured Angel", testSong.getName());
        assertEquals("DJ Raisei", testSong.getArtist());
        assertEquals(3, testSong.getLengthMin());
        assertEquals(10, testSong.getLengthSec());
        assertEquals(301, testSong.getBpm());
        assertEquals("3:10", testSong.getLength());
    }

    @Test
    public void DisplaySongTest() {
        assertEquals("Fractured Angel" + "\n" +
                "Artist: DJ Raisei" + "\n" +
                "BPM: 301" + "\n" +
                "Length: 3:10" + "\n", testSong.displaySong());
    }
}
