package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a song having name, artist, length, and bpm
public class Song {
    private final String name;
    private final String artist;
    private final int lengthMin;
    private final int lengthSec;
    private final int bpm;
    private final String length; // length of song in the form of MIN:SEC
    // each song will eventually have a chart field which will be an ArrayList,
    // representing the notes in the gameplay but can't be currently implemented

    // EFFECTS: creates new Song with name, artist, lengthMin, lengthSec, and bpm
    public Song(String name, String artist, int lengthMin, int lengthSec, int bpm) {
        this.name = name;
        this.artist = artist;
        this.lengthMin = lengthMin;
        this.lengthSec = lengthSec; // FIGURE OUT HOW TO REPRESENT THE LENGTH WITH MIN:SEC FORMAT (2:04 instead of 2:4)
        this.bpm = bpm;
        this.length = String.format("%d:%02d", lengthMin, lengthSec);
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getLength() {
        return length;
    }

    public int getBpm() {
        return bpm;
    }

    public int getLengthMin() {
        return lengthMin;
    }

    public int getLengthSec() {
        return lengthSec;
    }

    // EFFECTS: displays all information about the song
    public String displaySong() {
        return "NAME: " + this.name + "\n" + "ARTIST: " + this.artist + "\n"
                + "BPM: " + this.bpm + "\n" + "LENGTH: " + this.length + "\n";
    }
}
