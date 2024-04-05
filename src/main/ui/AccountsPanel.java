package ui;

import model.Account;
import model.AccountLog;
import model.EventLog;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the panel in which accounts are displayed
public class AccountsPanel extends JPanel {
    private static final String WELCOME = "Welcome to Rhythm Master! Please log in or create an account.";
    private static final String JSON_STORE = "./data/accountlog.json";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final int LBL_WIDTH = 400;
    private static final int LBL_HEIGHT = 50;
    private static final int BTN_WIDTH = 180;
    private static final int BTN_HEIGHT = 135;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private AccountLog accountLog;
    private JLabel welcomelbl;
    private JPanel buttonPanel;
    private GameApp gameApp;

    // EFFECTS: create a new AccountsPanel with welcome label and accounts
    public AccountsPanel(GameApp gameApp) {
        this.gameApp = gameApp;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        init();
    }

    // EFFECTS: add and display buttons
    private void addButtons() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 4));

        addAccountButtons();

        JButton createAccButton = newAccButton();

        buttonPanel.add(createAccButton);
        add(buttonPanel);
        addSaveAndLoadButtons();
    }

    // EFFECTS: display a button to create new account
    private JButton newAccButton() {
        JButton createAccButton = new JButton("Add New Account");
        createAccButton.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        createAccButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewAccount();
            }
        });
        return createAccButton;
    }

    // EFFECTS: creates new account
    private void createNewAccount() {
        JTextField nameField = new JTextField(20);
        JTextField ageField = new JTextField(5);
        JTextArea bioArea = new JTextArea(5, 20);
        JTextField passwordField = new JTextField(20);
        JScrollPane scrollPane = new JScrollPane(bioArea);

        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.setPreferredSize(new Dimension(400, 300));
        setUpNewAccountPanel(nameField, ageField, passwordField, scrollPane, panel);

        int result = JOptionPane.showConfirmDialog(null, panel, "Create New Account",
                JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String bio = bioArea.getText();
            String password = passwordField.getText();

            Account newAccount = new Account(name, age, bio, password, "none");
            accountLog.addAccount(newAccount);
            JOptionPane.showMessageDialog(AccountsPanel.this, "Welcome!");

            saveProgress();
            gameApp.createNewGameApp(newAccount, AccountsPanel.this);
        }
    }

    // EFFECTS: helper to set up new account being created
    private void setUpNewAccountPanel(JTextField nameField, JTextField ageField,
                                      JTextField passwordField, JScrollPane scrollPane, JPanel panel) {
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Bio:"));
        panel.add(scrollPane);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
    }

    // EFFECTS: display all accounts
    private void addAccountButtons() {
        for (Account acc : accountLog.getAccounts()) {
            JButton button = new JButton("Username: " + acc.getName());
            button.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String password = retrievePasswordFromUser();

                    if (authenticate(password)) {
                        JOptionPane.showMessageDialog(AccountsPanel.this, "Welcome!");
                        saveProgress();
                        gameApp.createNewGameApp(acc, AccountsPanel.this);
                    } else {
                        JOptionPane.showMessageDialog(AccountsPanel.this, "Wrong password!");
                    }
                }

                private boolean authenticate(String password) {
                    return password != null && password.equals(acc.getPassword());
                }
            });

            buttonPanel.add(button);
        }
    }

    // EFFECTS: display a button to quit or save
    private void addSaveAndLoadButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveQuitButton = newSaveButton();
        JButton loadButton = newLoadButton();

        buttonPanel.add(saveQuitButton);
        buttonPanel.add(loadButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // EFFECTS: display a load button
    private JButton newLoadButton() {
        JButton loadButton = new JButton("Load Previous Progress");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(AccountsPanel.this,
                        "Load progress?",
                        "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    updateAccountsPanel();
                }
            }
        });
        return loadButton;
    }

    // EFFECTS: helper method to create a save or quit button
    private JButton newSaveButton() {
        JButton saveQuitButton = new JButton("Save/Quit");
        saveQuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(AccountsPanel.this,
                        "Do you want to save before quitting?",
                        "Save or Quit", JOptionPane.YES_NO_CANCEL_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    saveProgress();
                    printLog();
                    System.exit(0);
                } else if (option == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
            }
        });
        return saveQuitButton;
    }

    // EFFECTS: prints log to console
    private void printLog() {
        EventLog el = EventLog.getInstance();
        for (Event e : el) {
            System.out.println(e.toString() + "\n");
        }
    }

    // EFFECTS: save progress to destination
    private void saveProgress() {
        try {
            jsonWriter.open();
            jsonWriter.write(accountLog);
            jsonWriter.close();
            System.out.println("Saved the progress to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: read progress from destination
    private AccountLog readProgress() {
        try {
            AccountLog updatedAccountLog = jsonReader.read();
            System.out.println("Loaded progress from " + JSON_STORE);
            return updatedAccountLog;
        } catch (IOException e) {
            System.out.println("Unable to load from file: " + JSON_STORE);
            return null;
        }
    }

    // EFFECTS: helper to read user input for password
    private String retrievePasswordFromUser() {
        JPasswordField passwordField = new JPasswordField();
        int option = JOptionPane.showConfirmDialog(AccountsPanel.this, passwordField,
                "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            return new String(passwordField.getPassword());
        } else {
            return null;
        }
    }

    // EFFECTS: update accountLog and display new AccountsPanel
    private void updateAccountsPanel() {
        accountLog = readProgress();
        removeAll();
        add(welcomelbl);
        addButtons();
        revalidate();
        repaint();
    }

    // EFFECTS: initialize accountLog and data writer/readers
    private void init() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        welcomelbl = new JLabel(WELCOME);
        welcomelbl.setPreferredSize(new Dimension(WIDTH, LBL_HEIGHT));
        welcomelbl.setOpaque(true);
        welcomelbl.setHorizontalAlignment(SwingConstants.CENTER);
        welcomelbl.setBackground(new Color(0xb4b4b4));
        updateAccountsPanel();
    }
}
