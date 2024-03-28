package ui;

import model.Account;
import model.Song;

import javax.swing.*;

public class GamePanel extends JPanel {
    private Account acc;
    private Song currentSong;

    public GamePanel(Account currentAcc, Song currentSong) {
        this.acc = currentAcc;
        this.currentSong = currentSong;
    }
}
