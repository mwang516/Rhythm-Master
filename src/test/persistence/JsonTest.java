package persistence;

import model.Account;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkAccount(String name, int age, String bio, String password, String favSong, Account acc) {
        assertEquals(name, acc.getName());
        assertEquals(age, acc.getAge());
        assertEquals(bio, acc.getBio());
        assertEquals(password, acc.getPassword());
        assertEquals(favSong, acc.getFavouriteSong());
    }
}
