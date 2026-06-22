package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverPanel extends JPanel {
    private final JLabel finalScoreLabel;
    private final JLabel highScoreLabel;

    public GameOverPanel(GameFrame frame) {
        setLayout(new GridBagLayout());
        setBackground(new Color(25, 25, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets.set(8, 8, 8, 8);

        JLabel title = new JLabel("Game Over");
        title.setForeground(Color.RED);
        title.setFont(new Font("SansSerif", Font.BOLD, 42));
        add(title, gbc);

        gbc.gridy++;
        finalScoreLabel = new JLabel("Final Score: 0");
        finalScoreLabel.setForeground(Color.WHITE);
        finalScoreLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(finalScoreLabel, gbc);

        gbc.gridy++;
        highScoreLabel = new JLabel("High Score: 0");
        highScoreLabel.setForeground(Color.LIGHT_GRAY);
        highScoreLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(highScoreLabel, gbc);

        gbc.gridy++;
        JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        restartButton.addActionListener(e -> frame.startGame());
        add(restartButton, gbc);

        gbc.gridy++;
        JButton menuButton = new JButton("Main Menu");
        menuButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        menuButton.addActionListener(e -> frame.showMenu());
        add(menuButton, gbc);
    }

    public void setScores(int finalScore, int highScore) {
        finalScoreLabel.setText("Final Score: " + finalScore);
        highScoreLabel.setText("High Score: " + highScore);
    }
}
