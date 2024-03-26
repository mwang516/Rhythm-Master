package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameAppPanel extends JPanel {
    private Account acc;
    private Song song1;
    private Song song2;
    private Song song3;
    private ArrayList<Song> allSongs;

    public GameAppPanel(Account acc) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        initSongs();
        this.acc = acc;
        // TODO: transfer over init() to initialize songs
    }

    private void initSongs() {
        song1 = new Song("c.s.q.n.", "Aoi", 2, 4, 174);
        song2 = new Song("NYA!!!", "FLuoRiTe", 2, 36, 154);
        song3 = new Song("On And On!!", "ETIA. feat Jenga", 2, 1, 170);
        allSongs = new ArrayList<>();
        allSongs.add(song1);
        allSongs.add(song2);
        allSongs.add(song3);
    }
}
