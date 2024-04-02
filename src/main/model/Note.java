package model;

// Represents a note with y coordinate and the lane it's in
public class Note {
    public static final int DY = 2;
    private int ycoord;
    private int lane;

    // EFFECTS: lane is [1,4]; the number represents the lane the note is in
    public Note(int lane) {
        this.lane = lane;
        this.ycoord = 0;
    }

    public void move() {
        this.ycoord += DY;
    }

    public int getY() {
        return ycoord;
    }

    public int getLane() {
        return lane;
    }
}
