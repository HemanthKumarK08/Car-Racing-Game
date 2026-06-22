package ui;

import engine.CollisionManager;
import engine.DifficultyManager;
import engine.GameLoop;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import model.Obstacle;
import model.PlayerCar;
import utils.ScoreManager;
import utils.SoundManager;

public class GamePanel extends JPanel {
    private static final int PANEL_WIDTH = 500;
    private static final int PANEL_HEIGHT = 700;
    private static final int ROAD_LEFT = 100;
    private static final int ROAD_WIDTH = 300;
    private static final int ROAD_RIGHT = ROAD_LEFT + ROAD_WIDTH;
    private static final int LANE_COUNT = 3;
    private static final int OBSTACLE_COUNT = 4;

    private final GameFrame frame;
    private final Random random;
    private final GameLoop loop;
    private final DifficultyManager difficultyManager;
    private final SoundManager soundManager;

    // CHANGE 1: Road background image
    private Image roadImage;

    // CHANGE 5: Speed up effect flag
    private boolean showSpeedText = false;
    private int showSpeedTextTimer = 0;

    private PlayerCar playerCar;
    private List<Obstacle> obstacles;
    private int score;
    private int roadLineOffset;
    private boolean isPaused;
    private boolean running;
    private int crashTimer = 0;
    private boolean gameOver;

    public GamePanel(GameFrame frame) {
        this.frame = frame;
        this.random = new Random();
        this.difficultyManager = new DifficultyManager();
        this.soundManager = new SoundManager();
        this.loop = new GameLoop(this::onTick);

        // CHANGE 1: Load road background image
roadImage = new ImageIcon("assets/road.jpg").getImage();        setFocusable(true);
        setBackground(new Color(10, 100, 10));

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                handleKeyReleased(e);
            }
        });
    }

    public void startNewGame() {
        score = 0;
        roadLineOffset = 0;
        isPaused = false;
        running = true;
        gameOver = false;
        showSpeedText = false;
        showSpeedTextTimer = 0;

        difficultyManager.reset();
        playerCar = new PlayerCar(PANEL_WIDTH / 2 - 20, PANEL_HEIGHT - 130, ROAD_LEFT, ROAD_RIGHT);
        obstacles = new ArrayList<>();

        int laneWidth = ROAD_WIDTH / LANE_COUNT;
        for (int i = 0; i < OBSTACLE_COUNT; i++) {
            int lane = random.nextInt(LANE_COUNT);
            int obstacleX = ROAD_LEFT + lane * laneWidth + (laneWidth / 2 - 20);
            int obstacleY = -i * 180;
            obstacles.add(new Obstacle(obstacleX, obstacleY, 40, 80));
        }

        soundManager.playBackgroundLoop();
        loop.start();
        repaint();
    }

    public void stopGame() {
        running = false;
        loop.stop();
        soundManager.stopBackgroundLoop();
    }

    private void onTick() {
        if (!running || isPaused) {
            repaint();
            return;
        }
        updateGame();
        repaint();
    }

    private void updateGame() {
        double previousSpeed = difficultyManager.getCurrentSpeed();

        playerCar.update(ROAD_LEFT, ROAD_RIGHT);
        updateRoadAnimation();
        difficultyManager.update();

        // CHANGE 5: Detect speed increase and trigger speed up effect
        double currentSpeed = difficultyManager.getCurrentSpeed();
        if (currentSpeed > previousSpeed + 0.5) {
            showSpeedText = true;
            showSpeedTextTimer = 60; // show for ~60 ticks
        }
        if (showSpeedTextTimer > 0) {
            showSpeedTextTimer--;
            if (showSpeedTextTimer == 0) {
                showSpeedText = false;
            }
        }

        int speed = (int) Math.round(currentSpeed);
        soundManager.adjustEngineSpeed(speed);
        int laneWidth = ROAD_WIDTH / LANE_COUNT;

        for (Obstacle obstacle : obstacles) {
            obstacle.update(speed);
            if (obstacle.getY() > PANEL_HEIGHT) {
                int lane = random.nextInt(LANE_COUNT);
                int obstacleX = ROAD_LEFT + lane * laneWidth + (laneWidth / 2 - obstacle.getWidth() / 2);
                obstacle.respawn(obstacleX, -120 - random.nextInt(240));
                score += 10;
            }
        }

        score++;

        if (CollisionManager.hasCollision(playerCar, obstacles)) {
    running = false;
    gameOver = true;

    crashTimer = 20; // 💥 trigger crash effect

    loop.stop();
    soundManager.stopBackgroundLoop();
    soundManager.playCrashEffect();

    ScoreManager.saveAndReturnHighScore(score);
}
    }

    private void updateRoadAnimation() {
        roadLineOffset += 8;
        if (roadLineOffset >= 80) {
            roadLineOffset = 0;
        }
    }

    private void handleKeyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
            playerCar.setMoveLeft(true);
        } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
            playerCar.setMoveRight(true);
        } else if (keyCode == KeyEvent.VK_P) {
            isPaused = !isPaused;
        } else if (keyCode == KeyEvent.VK_ESCAPE) {
            frame.showMenu();
        } else if (keyCode == KeyEvent.VK_R && gameOver) {
            // Restart on R when game is over
            startNewGame();
        }
    }

    private void handleKeyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
            playerCar.setMoveLeft(false);
        } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
            playerCar.setMoveRight(false);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // CHANGE 1: Draw road background image instead of green + black
        g2d.drawImage(roadImage, 0, 0, getWidth(), getHeight(), null);

        // CHANGE 2: Lane drawing removed (road image already has lanes)

        drawHud(g2d);

        if (playerCar != null) {
            playerCar.draw(g2d);
        }
        if (obstacles != null) {
            for (Obstacle obstacle : obstacles) {
                obstacle.draw(g2d);
            }
        }

        // CHANGE 5: Speed up effect overlay
        if (showSpeedText) {
            g2d.setFont(new Font("Arial", Font.BOLD, 40));
            g2d.setColor(Color.ORANGE);
            g2d.drawString("SPEED UP!", getWidth() / 4, getHeight() / 2);
        }
        // 💥 CRASH FLASH EFFECT
if (crashTimer > 0) {
    g2d.setColor(new Color(255, 0, 0, 120));
    g2d.fillRect(0, 0, getWidth(), getHeight());
    crashTimer--;
}

        // CHANGE 6: Pause overlay
        if (isPaused) {
            g2d.setColor(new Color(0, 0, 0, 150));
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.setFont(new Font("Arial", Font.BOLD, 40));
            g2d.setColor(Color.WHITE);
            g2d.drawString("PAUSED", getWidth() / 3, getHeight() / 2);
        }

        // CHANGE 7: Game over overlay
       if (gameOver) {
    // Dark overlay
    g2d.setColor(new Color(0, 0, 0, 180));
    g2d.fillRect(0, 0, getWidth(), getHeight());

    // GAME OVER
    g2d.setFont(new Font("Arial", Font.BOLD, 50));
    g2d.setColor(Color.RED);
    String over = "GAME OVER";
    int overWidth = g2d.getFontMetrics().stringWidth(over);
    g2d.drawString(over, (getWidth() - overWidth) / 2, getHeight() / 2 - 80);

    // SCORE
    g2d.setFont(new Font("Arial", Font.BOLD, 28));
    g2d.setColor(Color.YELLOW);
    String scoreText = "Score: " + score;
    int scoreWidth = g2d.getFontMetrics().stringWidth(scoreText);
    g2d.drawString(scoreText, (getWidth() - scoreWidth) / 2, getHeight() / 2 - 20);

    // RESTART
    g2d.setFont(new Font("Arial", Font.BOLD, 20));
    g2d.setColor(Color.WHITE);
    String restart = "Press R to Restart";
    int restartWidth = g2d.getFontMetrics().stringWidth(restart);
    g2d.drawString(restart, (getWidth() - restartWidth) / 2, getHeight() / 2 + 40);
}

        g2d.dispose();
    }

private void drawHud(Graphics2D g2d) {
    int speed = (int) Math.round(difficultyManager.getCurrentSpeed());

    // ===== SCORE (TOP CENTER) =====
    g2d.setFont(new Font("Arial", Font.BOLD, 28));
    String scoreText = "Score: " + score;
    int textWidth = g2d.getFontMetrics().stringWidth(scoreText);

    // Shadow effect (for better visibility)
    g2d.setColor(Color.BLACK);
    g2d.drawString(scoreText, (getWidth() - textWidth) / 2 + 2, 42);

    // Main score text
    g2d.setColor(Color.YELLOW);
    g2d.drawString(scoreText, (getWidth() - textWidth) / 2, 40);


    // ===== SPEED (TOP LEFT) =====
    g2d.setFont(new Font("Arial", Font.BOLD, 18));
    g2d.setColor(Color.WHITE);
    g2d.drawString("Speed: " + speed, 20, 70);


    // ===== CONTROLS TEXT =====
    g2d.setFont(new Font("SansSerif", Font.PLAIN, 14));
    g2d.setColor(Color.WHITE);
    g2d.drawString("P: Pause/Resume | ESC: Menu", 20, 90);
}
}