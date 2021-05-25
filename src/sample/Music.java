package sample;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class Music {

    public MediaPlayer musicPlayer;

    public MediaPlayer getMusicPlayer() {
        return musicPlayer;
    }

    public void background(String path) {
        Media hit = new Media(Paths.get(path).toUri().toString());
        musicPlayer = new MediaPlayer(hit);
        setPlayCount(MediaPlayer.INDEFINITE);
        musicPlayer.play();
    }

    public void setPlayCount(int count) {
        musicPlayer.setCycleCount(count);
    }

    public void playEffectSound(String path){
        Media hit = new Media(Paths.get(path).toUri().toString());
        musicPlayer = new MediaPlayer(hit);
        musicPlayer.play();
    }

    public void setVolume(double v) {
        musicPlayer.setVolume(v);
    }



}
