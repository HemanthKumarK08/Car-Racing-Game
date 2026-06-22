package engine;

import javax.swing.Timer;

public class GameLoop {
    private static final int FRAME_DELAY_MS = 16;
    private final Timer timer;

    public GameLoop(Runnable tickAction) {
        timer = new Timer(FRAME_DELAY_MS, e -> tickAction.run());
    }

    public void start() {
        if (!timer.isRunning()) {
            timer.start();
        }
    }

    public void stop() {
        if (timer.isRunning()) {
            timer.stop();
        }
    }
}
