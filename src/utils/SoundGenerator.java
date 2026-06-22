package utils;

import java.io.*;
import javax.sound.sampled.*;

public class SoundGenerator {

    public static void main(String[] args) throws Exception {
       generateTone("assets/background.wav", 440, 3);
generateTone("assets/crash.wav", 100, 1);    // crash sound
        System.out.println("✅ Sound files created!");
    }

    static void generateTone(String fileName, int freq, int seconds) throws Exception {
        float sampleRate = 44100;
        byte[] buf = new byte[(int) sampleRate * seconds];

        for (int i = 0; i < buf.length; i++) {
            double angle = i / (sampleRate / freq) * 2.0 * Math.PI;
            buf[i] = (byte) (Math.sin(angle) * 127);
        }

        AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, false);
        ByteArrayInputStream bais = new ByteArrayInputStream(buf);
        AudioInputStream ais = new AudioInputStream(bais, format, buf.length);

        File file = new File(fileName);
        AudioSystem.write(ais, AudioFileFormat.Type.WAVE, file);
    }
}