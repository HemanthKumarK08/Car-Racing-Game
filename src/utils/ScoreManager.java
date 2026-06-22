package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ScoreManager {
    private static final String HIGH_SCORE_FILE_PATH = "src/data/highscores.txt";

    private ScoreManager() {
    }

    public static int readHighScore() {
        ensureHighScoreFileExists();
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORE_FILE_PATH))) {
            String line = reader.readLine();
            if (line == null || line.trim().isEmpty()) {
                return 0;
            }
            return Integer.parseInt(line.trim());
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
    }

    public static int saveAndReturnHighScore(int score) {
        int currentHigh = readHighScore();
        int updatedHigh = Math.max(currentHigh, score);
        if (updatedHigh != currentHigh) {
            writeHighScore(updatedHigh);
        }
        return updatedHigh;
    }

    private static void writeHighScore(int highScore) {
        ensureHighScoreFileExists();
        try (FileWriter writer = new FileWriter(HIGH_SCORE_FILE_PATH, false)) {
            writer.write(String.valueOf(highScore));
        } catch (IOException e) {
            // Keep game flow uninterrupted if file writing fails.
        }
    }

    private static void ensureHighScoreFileExists() {
        File file = new File(HIGH_SCORE_FILE_PATH);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("0");
            } catch (IOException e) {
                // Ignore initialization failures, caller already has fallback behavior.
            }
        }
    }
}
