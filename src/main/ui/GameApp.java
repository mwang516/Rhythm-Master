package ui;

import model.Account;
import model.Song;

import javax.swing.*;
import java.awt.*;

// Game application
public class GameApp extends JFrame {
    private AccountsPanel acp;
    private GameAppPanel gpp;
    private GamePanel gp;

    // EFFECTS: starts the game application
    public GameApp() {
        super("Rhythm Master");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        acp = new AccountsPanel(this);
        add(acp);

        pack();
        centreOnScreen();
        setVisible(true);
    }

    // EFFECTS: initialize new AccountsPanel after logging out
    public void createNewAccountsPanel() {
        acp = new AccountsPanel(this);

        getContentPane().removeAll();
        getContentPane().add(acp);
        revalidate();
        repaint();
    }

    // EFFECTS: initialize new GameAppPanel after authentication
    public void createNewGameApp(Account acc, AccountsPanel acsp) {
        acp = acsp;
        gpp = new GameAppPanel(acc, this);
        getContentPane().removeAll();
        getContentPane().add(gpp);
        revalidate();
        repaint();
    }

    public void createNewGame(Account acc, Song s) {
        gp = new GamePanel(acc, s);

        getContentPane().removeAll();
        getContentPane().add(gp);
        revalidate();
        repaint();
    }

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