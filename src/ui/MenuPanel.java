package ui;

import java.awt.*;
import javax.swing.*;
import utils.ScoreManager;

public class MenuPanel extends JPanel {
    private final GameFrame frame;
    private final JLabel highScoreLabel;

    private Image backgroundImage;

    public MenuPanel(GameFrame frame) {
        this.frame = frame;

        // 🔥 IMPORTANT: Transparent panel (fix fade issue)
        setOpaque(false);

        // Layout
        setLayout(null);

        // 🔥 FIXED PATH (assets inside src)
        backgroundImage = new ImageIcon("assets/menu_bg.png").getImage();

        // ===== TITLE =====
        JLabel title = new JLabel("Advanced 2D Car Racing");
        title.setForeground(Color.green);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setBounds(60, 250, 400, 50);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title);

        // ===== HIGH SCORE =====
        highScoreLabel = new JLabel();
        highScoreLabel.setForeground(Color.white);
        highScoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        highScoreLabel.setBounds(100, 300, 300, 40);
        highScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(highScoreLabel);

        // ===== START BUTTON =====
        // JButton startButton = new JButton("Start Game");
        // startButton.setBounds(170, 400, 160, 50);

        // // Styling
        // startButton.setBackground(new Color(0, 120, 255));
        // startButton.setForeground(Color.WHITE);
        // startButton.setFocusPainted(false);
        // startButton.setBorder(BorderFactory.createEmptyBorder());
        // startButton.setFont(new Font("Arial", Font.BOLD, 18));

        // ===== START BUTTON =====
JButton startButton = new JButton("START GAME");
startButton.setBounds(150, 400, 200, 55);

// Remove default styling (IMPORTANT)
startButton.setFocusPainted(false);
startButton.setBorderPainted(false);
startButton.setContentAreaFilled(false);

// Font
startButton.setFont(new Font("Arial", Font.BOLD, 18));
startButton.setForeground(Color.WHITE);

// 🔥 Custom Rounded Button UI
startButton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Gradient background
        GradientPaint gp = new GradientPaint(
                0, 0, new Color(0, 120, 255),
                0, c.getHeight(), new Color(0, 80, 200)
        );
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30);

        // Glow border
        g2.setColor(new Color(0, 180, 255, 120));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(1, 1, c.getWidth() - 2, c.getHeight() - 2, 30, 30);

        g2.dispose();

        super.paint(g, c);
    }
});

startButton.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        startButton.setForeground(Color.YELLOW);
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
        startButton.setForeground(Color.WHITE);
    }

    public void mousePressed(java.awt.event.MouseEvent evt) {
        startButton.setLocation(startButton.getX(), startButton.getY() + 2);
    }

    public void mouseReleased(java.awt.event.MouseEvent evt) {
        startButton.setLocation(startButton.getX(), startButton.getY() - 2);
    }
});
startButton.addActionListener(e -> {
    frame.startGame();
});add(startButton);

        // Hover effect (🔥 makes it premium)
        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(30, 144, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(0, 120, 255));
            }
        });

startButton.addActionListener(e -> {
    frame.startGame();
});        add(startButton);

        refreshHighScore();
    }

    public void refreshHighScore() {
        int highScore = ScoreManager.readHighScore();
        highScoreLabel.setText("High Score: " + highScore);
    }

    // 🔥 FINAL BACKGROUND DRAW (NO FADE ISSUE)
    @Override
    protected void paintComponent(Graphics g) {
        // Draw image FIRST (no super.paintComponent to avoid white fade)
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

        // 🔥 Dark overlay for better readability
        g.setColor(new Color(0, 0, 0, 120));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}