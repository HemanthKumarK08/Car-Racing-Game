package ui;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
    public static final String MENU_SCREEN = "menu";
    public static final String GAME_SCREEN = "game";
    public static final String GAME_OVER_SCREEN = "game_over";

    private final CardLayout cardLayout;
    private final JPanel rootPanel;
    private final MenuPanel menuPanel;
    private final GamePanel gamePanel;
    private final GameOverPanel gameOverPanel;

    public GameFrame() {
        setTitle("Advanced 2D Car Racing Game");
        setSize(480, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        rootPanel = new JPanel(cardLayout);

        gameOverPanel = new GameOverPanel(this);
        gamePanel = new GamePanel(this);
        menuPanel = new MenuPanel(this);

        rootPanel.add(menuPanel, MENU_SCREEN);
        rootPanel.add(gamePanel, GAME_SCREEN);
        rootPanel.add(gameOverPanel, GAME_OVER_SCREEN);

        add(rootPanel);
        showMenu();
    }

    public void showMenu() {
        gamePanel.stopGame();
        cardLayout.show(rootPanel, MENU_SCREEN);
        menuPanel.refreshHighScore();
        menuPanel.requestFocusInWindow();
    }

    public void startGame() {
        cardLayout.show(rootPanel, GAME_SCREEN);
        gamePanel.startNewGame();
        gamePanel.requestFocusInWindow();
    }

    public void showGameOver(int finalScore, int highScore) {
        gamePanel.stopGame();
        gameOverPanel.setScores(finalScore, highScore);
        cardLayout.show(rootPanel, GAME_OVER_SCREEN);
        gameOverPanel.requestFocusInWindow();
    }
}
