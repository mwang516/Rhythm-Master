package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreTest {
    String name;
    int score;
    double accuracy;
    Score testScore;

    @BeforeEach
    public void runBefore() {
        name = "WATER";
        score = 995000;
        accuracy = 99.50;
        testScore = new Score(name, score, accuracy);
    }

    @Test
    public void ConstructorTest() {
        assertEquals("WATER", testScore.getName());
        assertEquals(995000, testScore.getScore());
        assertEquals(99.50, testScore.getAccuracy());
    }
}
