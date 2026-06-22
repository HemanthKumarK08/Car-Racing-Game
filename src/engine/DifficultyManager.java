package engine;

public class DifficultyManager {
    private static final double BASE_SPEED = 3.0;
    private static final double MAX_SPEED = 12.0;
    private static final int INCREASE_INTERVAL_TICKS = 180;
    private static final double SPEED_INCREMENT = 0.5;

    private int ticks;
    private double currentSpeed;

    public DifficultyManager() {
        reset();
    }

    public void reset() {
        ticks = 0;
        currentSpeed = BASE_SPEED;
    }

    public void update() {
        ticks++;
        if (ticks % INCREASE_INTERVAL_TICKS == 0 && currentSpeed < MAX_SPEED) {
            currentSpeed += SPEED_INCREMENT;
            if (currentSpeed > MAX_SPEED) {
                currentSpeed = MAX_SPEED;
            }
        }
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }
}
