package ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// EFFECTS: helper class to load the beatmap from file
public class BeatmapLoader {
    public static List<Integer> loadListFromFile(String filename) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int number = Integer.parseInt(line.trim());
                    numbers.add(number);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format in file: " + filename);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filename);
        }
        return numbers;
    }
}
