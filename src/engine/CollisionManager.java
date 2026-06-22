package engine;

import java.util.List;

import model.Obstacle;
import model.PlayerCar;

public class CollisionManager {
    private CollisionManager() {
    }

    public static boolean hasCollision(PlayerCar playerCar, List<Obstacle> obstacles) {
        for (Obstacle obstacle : obstacles) {
            if (playerCar.getBounds().intersects(obstacle.getBounds())) {
                return true;
            }
        }
        return false;
    }
}
