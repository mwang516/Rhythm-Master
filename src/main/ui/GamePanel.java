package ui;

import model.Account;
import model.Note;
import model.Song;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// Represents the panel in which the game is rendered
public class GamePanel extends JPanel {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int LBL_HEIGHT = 100;
    public static final Color LINE_COLOR = new Color(0,0,0);
    public static final Color NOTE_COLOR = new Color(255,255,0);
    public static final int JGMT_LN_X = 50;
    public static final int JGMT_LN_Y = 550;
    public static final int JGMT_LN_WIDTH = 700;
    public static final int JGMT_LN_HEIGHT = 5;
    public static final int GRID_LN_WIDTH = 3;
    public static final int GRID_LN_HEIGHT = 550;
    public static final int LANE_SPACE =  JGMT_LN_WIDTH / 4;
    public static final int NOTE_X = JGMT_LN_X + GRID_LN_WIDTH;
    public static final int NOTE_WIDTH = LANE_SPACE - GRID_LN_WIDTH;
    public static final int NOTE_HEIGHT = 15;
    private Game game;
    private Account acc;
    private Song currentSong;
    private List<Note> notesToDraw;
    private Boolean removeNote;
    private Note noteToBeDeleted;

    // EFFECTS: creates a GamePanel with game g, account currentAcc, and current song currentSong
    public GamePanel(Account currentAcc, Song currentSong, Game g) {
        JOptionPane.showMessageDialog(GamePanel.this, "The controls are: D, G, J, and L");
        this.game = g;
        this.acc = currentAcc;
        this.currentSong = currentSong;
        removeNote = false;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (removeNote) {
            g.setColor(getBackground());
            g.fillRect(NOTE_X + (noteToBeDeleted.getLane() - 1) * LANE_SPACE,
                    noteToBeDeleted.getY() - noteToBeDeleted.DY, NOTE_WIDTH, NOTE_HEIGHT + noteToBeDeleted.DY);
        }
        removeNote = false;

        drawGame(g);

        if (game.isOver()) {
            resultScreen();
        }
    }

    // EFFECTS: draw game
    private void drawGame(Graphics g) {
        drawGridlines(g);
        drawJudgmentLine(g);
        drawNotes(g);
    }

    // EFFECTS: draw notes that are in play
    private void drawNotes(Graphics g) {
        notesToDraw = game.getNotesToDraw();
        Color savedCol = g.getColor();

        for (Note n : notesToDraw) {
            g.setColor(getBackground());
            g.fillRect(NOTE_X + (n.getLane() - 1) * LANE_SPACE,
                    n.getY() - n.DY, NOTE_WIDTH, n.DY);

            g.setColor(NOTE_COLOR);
            g.fillRect(NOTE_X + (n.getLane() - 1) * LANE_SPACE, n.getY(), NOTE_WIDTH, NOTE_HEIGHT);
        }
        g.setColor(savedCol);
    }

    public void removeAndRepaintNotes(List<Note> notes) {
        for (Note note : notes) {
            notesToDraw.remove(note);
            noteToBeDeleted = note;
            removeNote = true;
            repaint();
        }
    }

    // EFFECTS: draw judgement line
    private void drawJudgmentLine(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(LINE_COLOR);
        g.fillRect(JGMT_LN_X, JGMT_LN_Y, JGMT_LN_WIDTH + GRID_LN_WIDTH, JGMT_LN_HEIGHT);
        g.setColor(savedCol);
    }

    // EFFECTS: draw grid lines
    private void drawGridlines(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(LINE_COLOR);
        g.fillRect(JGMT_LN_X, 0, GRID_LN_WIDTH, GRID_LN_HEIGHT);
        g.fillRect(JGMT_LN_X + LANE_SPACE, 0, GRID_LN_WIDTH, GRID_LN_HEIGHT);
        g.fillRect(JGMT_LN_X + 2 * LANE_SPACE, 0, GRID_LN_WIDTH, GRID_LN_HEIGHT);
        g.fillRect(JGMT_LN_X + 3 * LANE_SPACE, 0, GRID_LN_WIDTH, GRID_LN_HEIGHT);
        g.fillRect(JGMT_LN_X + JGMT_LN_WIDTH, 0, GRID_LN_WIDTH, GRID_LN_HEIGHT);
        g.setColor(savedCol);
    }

    // EFFECTS: draw the result screen after game is over
    private void resultScreen() {
        removeAll();
        JLabel scoresLbl = new JLabel("Score: " + Math.round(game.getScore()));
        scoresLbl.setPreferredSize(new Dimension(WIDTH / 2, LBL_HEIGHT));
        scoresLbl.setFont(new Font("Georgia", Font.BOLD, 24));
        JLabel songLbl = new JLabel(currentSong.getName() + " - " + currentSong.getArtist());
        songLbl.setPreferredSize(new Dimension(WIDTH / 2, LBL_HEIGHT));
        songLbl.setFont(new Font("Georgia", Font.BOLD, 24));
        add(scoresLbl);
        add(Box.createHorizontalStrut(10));
        add(songLbl);
        revalidate();
        repaint();
    }
}
