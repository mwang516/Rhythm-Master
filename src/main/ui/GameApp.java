package ui;

import model.Account;
import model.AccountLog;
import model.Song;

import java.util.ArrayList;
import java.util.Scanner;

// Game application
public class GameApp {
    private Account currentAccount;
    private AccountLog accountLog;
    private ArrayList<Song> allSongs;
    private Song song1;
    private Song song2;
    private Song song3;
    private Scanner input;

    // EFFECTS: starts the game application
    public GameApp() {
        runGameApp();
    }

    // MODIFIES: this
    // EFFECTS: process user inputs
    private void runGameApp() {
        boolean keepGoing = true;
        String command;

        init();
        if (currentAccount == null) { // to skip having to make a new account for testing each time
            keepGoing = selectAccount();
        }

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                keepGoing = processCommand(command);
            }
        }

        System.out.println("Thanks for playing!");
    }

    // EFFECTS: calls methods according to command input
    // returns whether to keep going or not
    private boolean processCommand(String command) {
        if (command.equals("1")) {
            displayCurrentAccount();
        } else if (command.equals("2")) {
            viewAllSongs();
        } else if (command.equals("3")) {
            playGame();
        } else if (command.equals("4")) {
            return selectAccount();
        } else {
            System.out.println("Invalid option. Please try again!");
        }
        return true;
    }

    // MODIFIES: this
    // EFFECTS: creates new Account or login to existing ones with valid selection input,
    // return whether account login/creation occurred
    private boolean selectAccount() {
        String command;
        boolean validSelection = false;
        currentAccount = null;

        System.out.println("Welcome to Rhythm Master! Please log in or create an account.");

        while (!validSelection) {
            System.out.println("[1] Select from existing accounts\n[2] Add new account\n[q] Quit");

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("1")) {
                validSelection = login();
            } else if (command.equals("2")) {
                validSelection = makeAccount();
            } else if (command.equals("q")) {
                break;
            } else {
                System.out.println("Invalid selection, please try again.");
            }
        }
        return validSelection;
    }

    // MODIFIES: this
    // EFFECTS: initializes Game, not yet implemented due to limitations
    private void playGame() {
        String command;

        System.out.println("Coming soon!!\n");
        System.out.println("[b] Go back");
        while (true) {
            command = input.next();
            if (command.equals("b")) {
                break;
            }
        }
    }

    // EFFECTS: displays all options for user to choose from
    private void displayMenu() {
        System.out.println("Please choose from the following options: ");
        System.out.println("[1] View my account");
        System.out.println("[2] View all songs");
        System.out.println("[3] Play");
        System.out.println("[4] Log out");
        System.out.println("[q] Quit");
    }

    // EFFECTS: prompts user to login with name and password, set logged in Account to currentAccount;
    // return true if logged in
    private boolean login() {
        String username;
        String password;

        if (accountLog.isEmpty()) {
            System.out.println("No accounts! Please create one.");
            return false;
        }
        displayAccounts();

        System.out.println("Please enter your username:");
        username = input.next();
        for (Account a : accountLog.getAccounts()) {
            if (username.equals(a.getName())) {
                System.out.println("Please enter your password:");
                password = input.next();

                if (password.equals(a.getPassword())) {
                    return passwordMatchLogIn(a);
                }
                System.out.println("Wrong password!");
                return false;
            }
        }
        System.out.println("Invalid username!");
        return false;
    }

    // EFFECTS: sets a to currentAccount and return true
    private boolean passwordMatchLogIn(Account a) {
        System.out.println("Thank you! Logging you in...");
        currentAccount = a;
        return true;
    }

    // EFFECTS: displays name of all accounts
    private void displayAccounts() {
        ArrayList<Account> accs = accountLog.getAccounts();

        System.out.println("Select from the following accounts: ");
        for (Account a : accs) {
            System.out.print("[" + accs.indexOf(a) + "]" + a.getName() + "    ");
        }
        System.out.println("\n");
    }

    // MODIFIES: this
    // EFFECTS: prompts user to make an Account, return true if Account made
    private boolean makeAccount() {
        String name;

        System.out.println("Please enter your username:");
        name = input.next();
        for (Account a : accountLog.getAccounts()) {
            if (name.equals(a.getName())) {
                System.out.println("This username already exists. Please log in or create a different account!");
                return false;
            }
        }
        fillInfo(name);
        return true;
    }

    // MODIFIES: this
    // EFFECTS: prompts user to fill information to create newAccount,
    // set currentAccount to newAccount;
    private void fillInfo(String name) {
        String password;
        String bio;
        int age;

        System.out.println("Please enter your age:");
        age = Integer.parseInt(input.next());
        System.out.println("Please enter your bio (anything you want to say):");
        bio = input.next();
        System.out.println("Please enter your password:");
        password = input.next();
        System.out.println("Thank you, " + name + "! Creating your account...");
        Account newAccount = new Account(name, age, bio, password);
        accountLog.addAccount(newAccount);
        currentAccount = newAccount;
        System.out.println("Welcome!");
    }

    // MODIFIES: this
    // EFFECTS: create song instances, set currentAccount to null, instantiate accounts
    private void init() {
        song1 = new Song("c.s.q.n.", "Aoi", 2, 4, 174);
        song2 = new Song("NYA!!!", "FLuoRiTe", 2, 36, 154);
        song3 = new Song("On And On!!", "ETIA. feat Jenga", 2, 1, 170);
        allSongs = new ArrayList<>();
        allSongs.add(song1);
        allSongs.add(song2);
        allSongs.add(song3);

        input = new Scanner(System.in);
        input.useDelimiter("\n");

        Account testAccount = new Account("Test", 19, "this is a test account", "test123");
        accountLog = new AccountLog();
        currentAccount = testAccount;
        accountLog.addAccount(testAccount);
    }

    // EFFECTS: displays all songs
    private void viewAllSongs() {
        String command;
        for (int i = 0; i < allSongs.size(); i++) {
            System.out.println(allSongs.get(i).displaySong());
        }
        System.out.println("[b] Go back");
        while (true) {
            command = input.next();
            if (command.equals("b")) {
                break;
            }
        }
    }

    // EFFECTS: display info of currentAccount
    private void displayCurrentAccount() {
        String command;
        System.out.println(currentAccount.displayAccount());
        System.out.println("[s] Set Favourite Song");
        System.out.println("[b] Go back");
        while (true) {
            command = input.next();
            if (command.equals("s")) {
                setFavouriteSong();
                break;
            }
            if (command.equals("b")) {
                break;
            }
        }
    }

    // EFFECTS: sets favourite song of currentAccount
    private void setFavouriteSong() {
        int num;
        System.out.println("Select from the following: ");
        for (int i = 0; i < allSongs.size(); i++) {
            System.out.println("[" + i + "]" + allSongs.get(i).getName() + "\n");
        }
        while (true) {
            num = Integer.parseInt(input.next());

            if (0 <= num && num < allSongs.size()) {
                Song favSong = allSongs.get(num);

                currentAccount.setFavouriteSong(favSong);
                System.out.println("Your current favourite song is: " + favSong.getName());
                break;
            } else {
                System.out.println("Invalid option!");
            }
        }
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }
}