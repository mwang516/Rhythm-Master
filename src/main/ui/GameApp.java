package ui;

import model.Account;
import model.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// Game application
public class GameApp extends JFrame {
    private static final int INTERVAL = 5;
    private AccountsPanel acp;
    private GameAppPanel gpp;
    private ScorePanel sp;
    private GamePanel gp;
    private Game game;
    private Timer timer;

    // EFFECTS: starts the game application
    public GameApp() {
        super("Rhythm Master");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        acp = new AccountsPanel(this);
        add(acp);
        addKeyListener(new KeyHandler());

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

    // EFFECTS: creates a new GamePanel and Game with given song
    public void createNewGame(Account acc, Song s) {
        game = new Game(s);
        gp = new GamePanel(acc, s, game);
        sp = new ScorePanel(game);
        game.addGamePanel(gp);

        getContentPane().removeAll();
        getContentPane().add(gp);
        getContentPane().add(sp);
        revalidate();
        repaint();
        addTimer();
    }

    private void addTimer() {
        timer = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                game.update();
                gp.paintComponent(getGraphics());
                sp.update();
            }
        });

        timer.start();
    }

    /*
     * A key handler to respond to key events
     */
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.keyPressed(e.getKeyCode());
        }
    }

    // EFFECTS: helper method for setting the location of panel at center of screen
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }
}