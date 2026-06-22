package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

public class Obstacle {
    private int x;
    private int y;
    private final int width;
    private final int height;

    // CHANGE: Obstacle image replacing rectangle drawing
    private Image obstacleImage;

    public Obstacle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        // CHANGE: Pick a random enemy car image
        String[] cars = {
"assets/enemy1.png",    "assets/enemy2.png",
    "assets/enemy3.png"
};

        int i = new Random().nextInt(cars.length);
        obstacleImage = new ImageIcon(cars[i]).getImage();
    }

    public void update(int speed) {
        y += speed;
    }

    public void respawn(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    public void draw(Graphics2D g2d) {
        // CHANGE: Draw image instead of blue rectangle + yellow strip
        g2d.drawImage(obstacleImage, x, y, width, height, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(
    x + 5,
    y + 10,
    width - 10,
    height - 20
);
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }
}