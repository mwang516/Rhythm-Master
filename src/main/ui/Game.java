package ui;

import model.Note;
import model.Song;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Represents a game
public class Game {
    public static final int MAX_SCORE = 1000000;
    private GamePanel gp;
    private List<Integer> beatMap;
    private List<Note> activeNotes;
    private List<Note> notesInLane1;
    private List<Note> notesInLane2;
    private List<Note> notesInLane3;
    private List<Note> notesInLane4;
    private List<Note> notesToRemove;
    private boolean isGameOver;
    private int noteCount;
    private double score;
    private double scorePerNote;
    private int iterationAmt;
    private int misses;
    private int perfects;
    private int goods;

    // EFFECTS: creates new game with given song and beatmap
    public Game(Song s) {
        String file = chooseMap(s);
        beatMap = BeatmapLoader.loadListFromFile(file);
        noteCount = countNotes();
        scorePerNote = (double) MAX_SCORE / noteCount;
        isGameOver = false;

        setUp();
    }

    // EFFECTS: helper method to setup and clear all progress
    private void setUp() {
        score = 0;
        misses = 0;
        perfects = 0;
        goods = 0;
        iterationAmt = 0;
        activeNotes = new ArrayList<>();
        notesInLane1 = new ArrayList<>();
        notesInLane2 = new ArrayList<>();
        notesInLane3 = new ArrayList<>();
        notesInLane4 = new ArrayList<>();
        notesToRemove = new ArrayList<>();
    }

    // EFFECTS: update method for the game
    public void update() {
        moveActiveNotes();
        if (!isGameOver) {
            checkAndAddNextNoteEntry();
        }
        removeOutOfBoundsNotes();
        checkGameOver();
        gp.removeAndRepaintNotes(notesToRemove);
    }

    // EFFECTS: selects the appropriate beatmap file based on song
    private String chooseMap(Song s) {
        String name = s.getName();
        if (name == "c.s.q.n.") {
            return "data/csqn.txt";
        } else if (name == "NYA!!!") {
            return "data/nya.txt";
        } else if (name == "On And On!!") {
            return "data/onandon.txt";
        }
        return null;
    }

    // EFFECTS: save the score and return to song selections
    private void saveResultsAndReturn() {

    }

    // EFFECTS: pause the game
    // To be implemented later
    private void pauseGame() {

    }

    // EFFECTS: move current notes
    private void moveActiveNotes() {
        for (Note n : activeNotes) {
            n.move();
        }
    }

    // EFFECTS: adds next note based on iteration amount
    private void checkAndAddNextNoteEntry() {
        iterationAmt++;
        if (iterationAmt % 34 == 0 && !beatMap.isEmpty()) {
            int next = beatMap.get(0);
            if (next == 1) {
                Random random = new Random();
                int lane = random.nextInt(4) + 1;

                Note newNote = new Note(lane);

                activeNotes.add(newNote);
                processLane(newNote);
            }
            beatMap.remove(0);
        }
    }

    // EFFECTS: checks for note in given lane after button is hit
    private void processLane(Note newNote) {
        int lane = newNote.getLane();
        if (lane == 1) {
            notesInLane1.add(newNote);
        } else if (lane == 2) {
            notesInLane2.add(newNote);
        } else if (lane == 3) {
            notesInLane3.add(newNote);
        } else if (lane == 4) {
            notesInLane4.add(newNote);
        }
    }

    // EFFECTS: check if game is over
    private void checkGameOver() {
        isGameOver = activeNotes.isEmpty() && beatMap.isEmpty();
        if (isGameOver) {
            activeNotes = new ArrayList<>();
        }
    }

    // EFFECTS: remove notes that are out of bounds and add to miss
    private void removeOutOfBoundsNotes() {
        for (Note n : activeNotes) {
            if (n.getY() >= GamePanel.HEIGHT) {
                int lane = n.getLane();
                if (lane == 1) {
                    notesInLane1.remove(n);
                } else if (lane == 2) {
                    notesInLane2.remove(n);
                } else if (lane == 3) {
                    notesInLane3.remove(n);
                } else if (lane == 4) {
                    notesInLane4.remove(n);
                }
                notesToRemove.add(n);
                misses++;
            }
        }
    }

    // EFFECTS: process special key inputs
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            pauseGame();
        } else if (keyCode == KeyEvent.VK_B && isGameOver) {
            saveResultsAndReturn();
        } else {
            handleKeyInput(keyCode);
        }
    }

    // EFFECTS: handle the key input
    private void handleKeyInput(int keyCode) {
        if (keyCode == KeyEvent.VK_D) {
            checkForNoteInLane(notesInLane1);
        } else if (keyCode == KeyEvent.VK_G) {
            checkForNoteInLane(notesInLane2);
        } else if (keyCode == KeyEvent.VK_J) {
            checkForNoteInLane(notesInLane3);
        } else if (keyCode == KeyEvent.VK_L) {
            checkForNoteInLane(notesInLane4);
        }
    }

    // EFFECTS: check for note in given lane
    private void checkForNoteInLane(List<Note> notesInLane) {
        if (!notesInLane.isEmpty()) {
            Note note = notesInLane.get(0);
            checkForNote(note, notesInLane);
        }
    }

    // EFFECTS: check the accuracy of note being hit and process score
    private void checkForNote(Note note, List<Note> notesInLane) {
        int diff = Math.abs(GamePanel.JGMT_LN_Y - note.getY());
        if (diff <= 20) {
            score += scorePerNote;
            perfects++;
        } else if (diff <= 40) {
            score += scorePerNote * 0.6;
            goods++;
        } else {
            misses++;
        }

        notesInLane.remove(note);
        notesToRemove.add(note);
    }

    // EFFECTS: counts total amount of notes
    private int countNotes() {
        int noteCount = 0;
        for (Integer i : beatMap) {
            if (i == 1) {
                noteCount++;
            }
        }
        return noteCount;
    }

    // EFFECTS: adds GamePanel
    public void addGamePanel(GamePanel gp) {
        this.gp = gp;
    }

    public List<Note> getNotesToDraw() {
        return activeNotes;
    }

    public double getScore() {
        return score;
    }

    public boolean isOver() {
        return isGameOver;
    }

    public int getMisses() {
        return misses;
    }

    public int getPerfects() {
        return perfects;
    }

    public int getGoods() {
        return goods;
    }
}
