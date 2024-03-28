package ui;

import model.Account;
import model.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameAppPanel extends JPanel {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final int LBL_HEIGHT = 100;
    private Account acc;
    private Song song1;
    private Song song2;
    private Song song3;
    private ArrayList<Song> allSongs;
    private GameApp gameApp;

    public GameAppPanel(Account acc, GameApp gameApp) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new FlowLayout(FlowLayout.CENTER));

        createButtons();

        initSongs();
        this.acc = acc;
        this.gameApp = gameApp;
        displayAccount();
    }

    private void createButtons() {
        createAccountButton();
        createPlayButton();
        createLogOutButton();
    }

    private void createLogOutButton() {
        JButton logoutButton = new JButton("Logout");

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameApp.createNewAccountsPanel();
            }
        });
        add(logoutButton);
    }

    private void createPlayButton() {
        JButton playButton = new JButton("Play");

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySongs();
            }
        });
        add(playButton);
    }

    private void displaySongs() {
        updateGameAppPanel();
        JLabel chooseSongLabel = new JLabel("Choose A Song to Play: ");
        chooseSongLabel.setPreferredSize(new Dimension(WIDTH, LBL_HEIGHT));
        chooseSongLabel.setFont(new Font("Georgia", Font.BOLD, 24));
        chooseSongLabel.setOpaque(true);
        chooseSongLabel.setHorizontalAlignment(SwingConstants.CENTER);
        chooseSongLabel.setBackground(new Color(238, 179, 246));

        add(chooseSongLabel);
        addSongLabels();
        revalidate();
        repaint();
    }

    private void addSongLabels() {
        int labelHeight = (HEIGHT - LBL_HEIGHT) / (2 * allSongs.size());
        for (Song s : allSongs) {
            JButton songLabel = new JButton(s.getName() + " - " + s.getArtist() + "; BPM: " + s.getBpm());
            songLabel.setPreferredSize(new Dimension(WIDTH, labelHeight));
            songLabel.setFont(new Font("Georgia", Font.BOLD, 18));
            songLabel.setOpaque(true);
            songLabel.setHorizontalAlignment(SwingConstants.LEFT);
            songLabel.setBackground(new Color(212, 205, 213));
            songLabel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameApp.createNewGame(acc, s);
                }
            });
            add(songLabel);
        }
    }

    private void createAccountButton() {
        JButton accountButton = new JButton("Account");

        accountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAccount();
            }
        });
        add(accountButton);
    }

    private void displayAccount() {
        updateGameAppPanel();
        addNameLabel();
        addAgeLabel();
        addFavSongLabel();
        addBioLabel();

        revalidate();
        repaint();
    }

    private void addNameLabel() {
        JLabel nameLabel = new JLabel("\tName: " + acc.getName());
        nameLabel.setPreferredSize(new Dimension(WIDTH, LBL_HEIGHT));
        nameLabel.setFont(new Font("Georgia", Font.ITALIC, 24));
        nameLabel.setOpaque(true);
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        nameLabel.setBackground(new Color(246, 246, 179));
        add(nameLabel);
    }

    private void addAgeLabel() {
        JLabel ageLabel = new JLabel("\tAge: " + acc.getAge());
        ageLabel.setPreferredSize(new Dimension(WIDTH, LBL_HEIGHT));
        ageLabel.setFont(new Font("Georgia", Font.ITALIC, 24));
        ageLabel.setOpaque(true);
        ageLabel.setHorizontalAlignment(SwingConstants.LEFT);
        ageLabel.setBackground(new Color(194, 246, 179));
        add(ageLabel);
    }

    private void addFavSongLabel() {
        JLabel favSongLabel = new JLabel("\tFavourite Song: " + acc.getFavouriteSong());
        favSongLabel.setPreferredSize(new Dimension(WIDTH, LBL_HEIGHT));
        favSongLabel.setFont(new Font("Georgia", Font.ITALIC, 24));
        favSongLabel.setOpaque(true);
        favSongLabel.setHorizontalAlignment(SwingConstants.LEFT);
        favSongLabel.setBackground(new Color(179, 246, 222));
        add(favSongLabel);
    }

    private void addBioLabel() {
        JLabel bioLabel = new JLabel("<html><font face=\"Georgia\" size=\"6\" style=\"font-style:italic;\">"
                + "Bio: </font>"
                + "<font face=\"Georgia\" size=\"4\">" + acc.getBio() + "</font></html>");
        bioLabel.setPreferredSize(new Dimension(WIDTH, LBL_HEIGHT));
        bioLabel.setFont(new Font("Georgia", Font.ITALIC, 24));
        bioLabel.setOpaque(true);
        bioLabel.setHorizontalAlignment(SwingConstants.LEFT);
        bioLabel.setBackground(new Color(179, 201, 246));
        add(bioLabel);
    }

    // EFFECTS: update accountLog and display new AccountsPanel
    private void updateGameAppPanel() {
        removeAll();
        createButtons();
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
