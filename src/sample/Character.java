package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Character extends Pane {

    ImageView imageView;
    int count = 3;
    int columns = 3;
    int offsetX = 0;
    int offsetY = 0;
    int width = 90;
    int height = 60;
    int score = 0;
    boolean check;
    Rectangle removeRect = null;
    CharacterAnimation animation;

    public Character(ImageView imageView, int width, int height) {
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        this.width = width;
        this.height = height;
        animation = new CharacterAnimation(imageView, Duration.millis(200), count, columns, offsetX, offsetY, width, height);
        getChildren().addAll(imageView);
    }

    public Character(ImageView imageView) {
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(0, 0, 32, 32));
        getChildren().addAll(imageView);
    }

    public Character(int width, int height, ImageView imageView) {
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(0, 0, width, height));
        getChildren().addAll(imageView);
    }

    public void moveX(double x) {
        boolean right = x > 0;
        for (int i = 0; i < Math.abs(x); i++) {
            if (right) this.setTranslateX(this.getTranslateX() + 1);
            else this.setTranslateX(this.getTranslateX() - 1);
            isBonusEat();
            isFinish();
            isEnergy();
        }
    }
    public void moveVanX(double x) {
        boolean right = x > 0;
        for (int i = 0; i < Math.abs(x); i++) {
            if (right) this.setTranslateX(this.getTranslateX() + 1);
            else this.setTranslateX(this.getTranslateX() - 1);
        }
        isPlayer();
    }

    public void finalMoveX(double x) {
        moveX(x);
        if (isWall()) {
            moveX(-1 * x);
        }
        isFinish();
        isMonster();
        isPizza();
    }

    public void moveY(double y) {
        boolean down = y > 0;
        for (int i = 0; i < Math.abs(y); i++) {
            if (down) this.setTranslateY(this.getTranslateY() + 1);
            else this.setTranslateY(this.getTranslateY() - 1);
            isBonusEat();
            isFinish();
            isEnergy();
        }
    }

    public void finalMoveY(double y) {
        moveY(y);
        if (isWall()) {
            moveY(-1 * y);
        }
        isFinish();
        isMonster();
        isPizza();
    }

    public void isBonusEat() {
        Main.bonuses.forEach((rect) -> {
            if (this.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                removeRect = rect;
                score++;
                Main.lives--;
                System.out.println(score);
            }
        });
        Main.bonuses.remove(removeRect);
        Main.root.getChildren().remove(removeRect);
    }

    public boolean isWall() {
        check = true;
        Main.walls.forEach((rect) -> {
            if (this.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                check = false;
            }
        });

        return !check;
    }

    public void isMonster() {
        if (Main.monsters.size() > 0) {
            for (int i = 0; i < Main.monsters.size(); i++) {
                if (this.getBoundsInParent().intersects(Main.monsters.get(i).getBoundsInParent())) {
                    if (Main.monsters.get(i).isVisible()) {
                        Main.lives--;
                        Main.energyLabel2.setText("x" + Main.lives);
                        int index = Main.monsters.indexOf(Main.monsters.get(i));
                        Main.root.getChildren().remove(Main.monsters.get(index));
                        Main.monsters.remove(index);
                        Main.sideSounds.playEffectSound("src/lose_energy.mp3");

                        Main.sideSounds.getMusicPlayer().setVolume(Main.sound.getValue());
                        if (Main.musicStatus) {
                            Main.playlist.getMusicPlayer().play();
                        }
                        System.out.println(Main.lives);
                    }
                }
            }
        }
    }
    public void isPlayer(){
        if (Main.players.size() > 0) {
            for (int i = 0; i < Main.players.size(); i++) {
                if (this.getBoundsInParent().intersects(Main.players.get(i).getBoundsInParent())) {
                    if (Main.players.get(i).isVisible()) {
                        Main.lives--;
                        Main.energyLabel2.setText("x" + Main.lives);
                        int index = Main.players.indexOf(Main.players.get(i));
                        int index1 = Main.players.indexOf(this);
                        Main.root.getChildren().remove(this);
                        Main.players.remove(index);Main.monsters.remove(this);
                        Main.sideSounds.playEffectSound("src/lose_energy.mp3");

                        Main.sideSounds.getMusicPlayer().setVolume(Main.sound.getValue());
                        if (Main.musicStatus) {
                            Main.playlist.getMusicPlayer().play();
                        }
                        System.out.println(Main.lives);
                    }
                }
            }
        }

    }

    public void isEnergy() {
        if (Main.energyBonuses.size() > 0) {
            for (int i = 0; i < Main.energyBonuses.size(); i++) {
                if (this.getBoundsInParent().intersects(Main.energyBonuses.get(i).getBoundsInParent())) {
                    if (Main.energyBonuses.get(i).isVisible()) {
                        Main.lives++;
                        Main.energyLabel2.setText("x" + Main.lives);
                        int index = Main.energyBonuses.indexOf(Main.energyBonuses.get(i));
                        Main.root.getChildren().remove(Main.energyBonuses.get(index));
                        Main.energyBonuses.remove(index);
                        Main.sideSounds.playEffectSound("src/mixkit-melodic-bonus-collect-1938.wav");
                        Main.sideSounds.getMusicPlayer().setVolume(Main.sound.getValue());
                        if (Main.musicStatus) {
                            Main.playlist.getMusicPlayer().play();
                        }
                        System.out.println(Main.lives);
                    }
                }
            }
        }
    }

    public void isPizza() {
        if (Main.pizzas.size() > 0) {
            for (int i = 0; i < Main.pizzas.size(); i++) {
                if (this.getBoundsInParent().intersects(Main.pizzas.get(i).getBoundsInParent())) {
                    int index = Main.pizzas.indexOf(Main.pizzas.get(i));
                    Main.root.getChildren().remove(Main.pizzas.get(index));
                    Main.pizzas.remove(index);
                    Main.sideSounds.playEffectSound("src/for_pizza.mp3");
                    Main.sideSounds.getMusicPlayer().setVolume(Main.sound.getValue());
                    Main.drawCross();
                    if (Main.musicStatus) {
                        Main.playlist.getMusicPlayer().play();
                    }
                }
            }

        }
    }

    public void isFinish() {
        if (this.getBoundsInParent().intersects(Main.houseIm.getBoundsInParent())) {
            Main.finishLevel = true;
        }
    }

}
