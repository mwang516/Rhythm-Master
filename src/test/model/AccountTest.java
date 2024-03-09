package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Account testAccount;
    Song testSong;
    Score testScore;

    @BeforeEach
    public void runBefore() {
        testAccount = new Account("Matthew", 19, "Hello world", "1234","none");
        testSong = new Song("Fractured Angel", "DJ Raisei", 3, 10, 301);
        testScore = new Score("Fractured Angel", 35, 100);
    }

    @Test
    public void ConstructorTest() {
        assertEquals("Matthew", testAccount.getName());
        assertEquals(19, testAccount.getAge());
        assertEquals("Hello world", testAccount.getBio());
        assertEquals("1234", testAccount.getPassword());
        assertEquals("none", testAccount.getFavouriteSong());
    }

    @Test
    public void DisplayAccountTest() {
        assertEquals("Account Information: \n" + "Name: Matthew" +
                        "\n" + "Age: 19" + "\n" + "Favourite song: none" +
                        "\n" + "Bio: \nHello world" + "\n",
                testAccount.displayAccount());
    }

    @Test
    public void SetNameTest() {
        assertEquals("Matthew", testAccount.getName());
        testAccount.setName("Bob");
        assertEquals("Bob", testAccount.getName());
    }

    @Test
    public void AddScoreTest() {
        assertTrue(testAccount.getScores().isEmpty());
        testAccount.addScore(testScore);
        assertFalse(testAccount.getScores().isEmpty());
        assertEquals(testScore, testAccount.getScores().get(0));
    }

    @Test
    public void SetFavouriteSongTest() {
        assertEquals("none", testAccount.getFavouriteSong());
        testAccount.setFavouriteSong(testSong);
        assertEquals("Fractured Angel", testAccount.getFavouriteSong());
    }
}

