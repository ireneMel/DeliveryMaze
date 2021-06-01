package sample;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

/**
 * Class contains all methods for work with music
 */
public class Music {

    public MediaPlayer musicPlayer;

    public MediaPlayer getMusicPlayer() {
        return musicPlayer;
    }

    /**
     * play background music without interruptions
     * @param path
     */
    public void background(String path) {
        Media hit = new Media(Paths.get(path).toUri().toString());
        musicPlayer = new MediaPlayer(hit);
        setPlayCount(MediaPlayer.INDEFINITE);
        musicPlayer.play();
    }

    /**
     * make a cycle from melody
     * @param count
     */
    public void setPlayCount(int count) {
        musicPlayer.setCycleCount(count);
    }

    /**
     * play a sound one time without cycle
     * @param path
     */
    public void playEffectSound(String path){
        Media hit = new Media(Paths.get(path).toUri().toString());
        musicPlayer = new MediaPlayer(hit);
        musicPlayer.play();
    }

    /**
     * set volume for music
     * @param v
     */
    public void setVolume(double v) {
        musicPlayer.setVolume(v);
    }



}
