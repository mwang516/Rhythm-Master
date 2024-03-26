package ui;

import model.AccountsPanel;
import model.Song;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Game application
public class GameApp extends JFrame {
    private AccountsPanel acp;

    // EFFECTS: starts the game application
    public GameApp() {
        super("Rhythm Master");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        acp = new AccountsPanel();
        add(acp);

        pack();
        centreOnScreen();
        setVisible(true);
    }

//    // EFFECTS: calls methods according to command input
//    // returns whether to keep going or not
//    private boolean processCommand(String command) {
//        if (command.equals("1")) {
//            displayCurrentAccount();
//        } else if (command.equals("2")) {
//            viewAllSongs();
//        } else if (command.equals("3")) {
//            playGame();
//        } else if (command.equals("4")) {
//            return selectAccount();
//        } else {
//            System.out.println("Invalid option. Please try again!");
//        }
//        return true;
//    }
//
//    // MODIFIES: this
//    // EFFECTS: initializes Game
//    private void playGame() {
//        String command;
//
//        System.out.println("Coming soon!!\n");
//        System.out.println("[b] Go back");
//        while (true) {
//            command = input.next();
//            if (command.equals("b")) {
//                break;
//            }
//        }
//    }
//
//    // EFFECTS: displays all options for user to choose from
//    private void displayMenu() {
//        System.out.println("Please choose from the following options: ");
//        System.out.println("[1] View my account");
//        System.out.println("[2] View all songs");
//        System.out.println("[3] Play");
//        System.out.println("[4] Log out");
//        System.out.println("[q] Quit");
//    }
//
//    // EFFECTS: display info of currentAccount
//    private void displayCurrentAccount() {
//        String command;
//        System.out.println(currentAccount.displayAccount());
//        System.out.println("[s] Set Favourite Song");
//        System.out.println("[b] Go back");
//        while (true) {
//            command = input.next();
//            if (command.equals("s")) {
//                setFavouriteSong();
//                break;
//            }
//            if (command.equals("b")) {
//                break;
//            }
//        }
//    }

    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }
}