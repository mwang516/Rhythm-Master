package model;

import java.util.ArrayList;

// Represents an account with name, age, bio, favourite song,
// and scores of past attempts
public class Account {
    private String name; // name of player
    private int age;
    private String bio;
    private String favouriteSong; // name of favourite song
    private ArrayList<Score> scores; // list of all past scores
    private String password;

    // EFFECTS: creates new Account with name, age, bio, and password
    public Account(String name, int age, String bio, String password) {
        this.name = name;
        this.age = age;
        this.bio = bio;
        this.favouriteSong = "none";
        this.scores = new ArrayList<>();
        this.password = password;
    }

    // MODIFIES: this
    // EFFECTS: scoreEntry is added to account's scores for given song
    public void addScore(Score scoreEntry) {
        scores.add(scoreEntry);
    }

    public String displayAccount() {
        return "Account Information: \n" + "Name: " + this.name
                + "\n" + "Age: " + this.age + "\n" + "Favourite song: "
                + this.favouriteSong + "\n" + "Bio: \n" + this.bio + "\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getBio() {
        return bio;
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public String getFavouriteSong() {
        return favouriteSong;
    }

    public void setFavouriteSong(Song favouriteSong) {
        this.favouriteSong = favouriteSong.getName();
    }

    public String getPassword() {
        return password;
    }
}
