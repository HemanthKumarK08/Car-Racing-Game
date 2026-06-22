package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class PlayerCar {
    private int x;
    private int y;
    private final int width;
    private final int height;
    private int speed;
    private boolean moveLeft;
    private boolean moveRight;

    // CHANGE: Car image replacing rectangle drawing
    private Image carImage;

    public PlayerCar(int x, int y, int leftBoundary, int rightBoundary) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 80;
        this.speed = 8;

        // CHANGE: Load player car image
carImage = new ImageIcon("assets/player.png").getImage();        clampWithinRoad(leftBoundary, rightBoundary);
    }

    public void update(int leftBoundary, int rightBoundary) {
        if (moveLeft) {
            x -= speed;
        }
        if (moveRight) {
            x += speed;
        }
        clampWithinRoad(leftBoundary, rightBoundary);
    }

    private void clampWithinRoad(int leftBoundary, int rightBoundary) {
        if (x < leftBoundary) {
            x = leftBoundary;
        }
        if (x + width > rightBoundary) {
            x = rightBoundary - width;
        }
    }

    public void draw(Graphics2D g2d) {
        // CHANGE: Draw image instead of colored rectangles
        g2d.drawImage(carImage, x, y, width, height, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(
    x + 5,
    y + 10,
    width - 10,
    height - 20
);
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }
}