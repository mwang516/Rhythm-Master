package ui;

import model.Account;
import model.Song;

public class GameApp {
    private Account currentAccount;
    private Song song1;
    private Song song2;
    private Song song3;

    // EFFECTS: starts the game
    public GameApp() {
        runGame();
    }

    // MODIFIES: this
    // EFFECTS: process user inputs
    private void runGame() {
        initSongs();
    }

    // MODIFIES: this
    // EFFECTS: create song instances
    private void initSongs() {
        song1 = new Song("c.s.q.n.", "Aoi", 2, 4, 174);
        // FIGURE OUT HOW TO REPRESENT THE LENGTH WITH MIN:SEC FORMAT (2:04 instead of 2:4)
    }

    // EFFECTS: prompts user to login with name and password
    private void login() {

    }

    // MODIFIES: this
    // EFFECTS: prompts user to make an account
    private void makeAccount() {

    }

    // EFFECTS: displays all songs
    private void viewAllSongs() {

    }


}
