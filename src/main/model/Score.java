package model;

// Represents a score entry with song name, score, and accuracy
public class Score {
    private final String name; // name of the song
    private final int score; // total score out of 1000000
    private final double accuracy; // accuracy represented by a percentage

    public Score(String name, int score, double accuracy) {
        this.name = name;
        this.score = score;
        this.accuracy = accuracy;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public double getAccuracy() {
        return accuracy;
    }
}
