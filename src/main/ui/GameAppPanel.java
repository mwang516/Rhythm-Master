package ui;

import model.Account;
import model.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameAppPanel extends JPanel {
    private Account acc;
    private Song song1;
    private Song song2;
    private Song song3;
    private ArrayList<Song> allSongs;
    private GameApp gameApp;

    public GameAppPanel(Account acc, GameApp gameApp) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new FlowLayout(FlowLayout.CENTER));

        createAccountButton();
        createPlayButton();
        createLogOutButton();

        initSongs();
        this.acc = acc;
        this.gameApp = gameApp;
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
                // Action to perform when "Account" button is clicked
                JOptionPane.showMessageDialog(GameAppPanel.this, "Account option selected");
                // You can open a new panel for the account option here
            }
        });
        add(playButton);
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
        add(new JLabel("Name: "));
        add(new JLabel(acc.getName()));
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
