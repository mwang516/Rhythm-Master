package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NoteTest {
    Note testNote;

    @BeforeEach
    public void runBefore() {
        testNote = new Note(1);
    }

    @Test
    public void testConstructor() {
        assertEquals(1, testNote.getLane());
        assertEquals(0, testNote.getY());
    }

    @Test
    public void testMove() {
        testNote.move();
        assertEquals(2, testNote.getY());
    }
}
