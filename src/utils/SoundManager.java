package utils;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundManager {

   private static final String BACKGROUND_SOUND_PATH = "assets/background.wav";
private static final String CRASH_SOUND_PATH = "assets/crash.wav";

    private Clip backgroundClip;

    // ===== PLAY BACKGROUND LOOP =====
    public void playBackgroundLoop() {
        stopBackgroundLoop();

        backgroundClip = loadClip(BACKGROUND_SOUND_PATH);

        if (backgroundClip != null) {
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    // ===== STOP BACKGROUND =====
    public void stopBackgroundLoop() {
        if (backgroundClip != null) {
            backgroundClip.stop();
            backgroundClip.close();
            backgroundClip = null;
        }
    }

    // ===== CRASH SOUND =====
    public void playCrashEffect() {
        Clip crashClip = loadClip(CRASH_SOUND_PATH);
        if (crashClip != null) {
            crashClip.start();
        }
    }

    // ===== 🔥 ENGINE SPEED CONTROL =====
    public void adjustEngineSpeed(int speed) {
        try {
            if (backgroundClip != null &&
                backgroundClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {

                FloatControl control =
                    (FloatControl) backgroundClip.getControl(FloatControl.Type.MASTER_GAIN);

                float volume = Math.min(0f, -20f + speed * 2);
                control.setValue(volume);
            }
        } catch (Exception e) {
            // safe ignore
        }
    }

    // ===== LOAD SOUND USING CLASSPATH (FINAL FIX) =====
    private Clip loadClip(String path) {
        try {
            var resource = getClass().getClassLoader().getResource(path);

            if (resource == null) {
                System.out.println("Sound file not found: " + path);
                return null;
            }

            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(resource));
            return clip;

        } catch (Exception e) {
            System.out.println("Error loading sound: " + path);
            return null;
        }
    }
}