package ui;

import model.Note;
import model.Song;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public Game(Song s) {
        String file = chooseMap(s);
        beatMap = BeatmapLoader.loadListFromFile(file);
        noteCount = countNotes();
        scorePerNote = (double) MAX_SCORE / noteCount;
        isGameOver = false;

        setUp();
    }

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

    public void update() {
        moveActiveNotes();
        if (!isGameOver) {
            checkAndAddNextNoteEntry();
        }
        removeOutOfBoundsNotes();
        checkGameOver();
        gp.removeAndRepaintNotes(notesToRemove);
    }


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

    private void moveActiveNotes() {
        for (Note n : activeNotes) {
            n.move();
        }
    }

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
                System.out.println("note added");
            }
            beatMap.remove(0);
            System.out.println("beatmap.remove worked");
        }
    }

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

    private void checkGameOver() {
        isGameOver = activeNotes.isEmpty() && beatMap.isEmpty();
        if (isGameOver) {
            activeNotes = new ArrayList<>();
        }
    }

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

    private void checkForNoteInLane(List<Note> notesInLane) {
        if (!notesInLane.isEmpty()) {
            Note note = notesInLane.get(0);
            checkForNote(note, notesInLane);
        }
    }

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
        System.out.println("note removed");
    }

    private int countNotes() {
        int noteCount = 0;
        for (Integer i : beatMap) {
            if (i == 1) {
                noteCount++;
            }
        }
        return noteCount;
    }

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

    public List<Note> getNotesInLane1() {
        return notesInLane1;
    }

    public List<Note> getNotesInLane2() {
        return notesInLane2;
    }

    public List<Note> getNotesInLane3() {
        return notesInLane3;
    }

    public List<Note> getNotesInLane4() {
        return notesInLane4;
    }
}
