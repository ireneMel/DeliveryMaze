package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
        this.width=width;
        this.height=height;
        animation = new CharacterAnimation(imageView, Duration.millis(200), count, columns, offsetX, offsetY, width, height);
        getChildren().addAll(imageView);
    }

    public Character(ImageView imageView) {
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(0, 0, 50, 60));
        getChildren().addAll(imageView);
    }

    public void moveX(int x) {
        boolean right = x > 0;
        for (int i = 0; i < Math.abs(x); i++) {
            if (right) this.setTranslateX(this.getTranslateX() + 1);
            else this.setTranslateX(this.getTranslateX() - 1);
            isBonusEat();isMonster();isEnergy();isFinish();
        }
    }

    public void finalMoveX(int x) {
        moveX(x);
        if (isWall()) {
            moveX(-1 * x);
        }
        isMonster();isEnergy();isFinish();
    }

    public void moveY(int y) {
        boolean down = y > 0;
        for (int i = 0; i < Math.abs(y); i++) {
            if (down) this.setTranslateY(this.getTranslateY() + 1);
            else this.setTranslateY(this.getTranslateY() - 1);
            isBonusEat();
            isMonster();isEnergy();isFinish();
        }
    }

    public void finalMoveY(int y) {
        moveY(y);
        if (isWall()) {
            moveY(-1 * y);
        }
        isMonster();isEnergy();isFinish();
    }

    public void isBonusEat() {
        Main.bonuses.forEach((rect) -> {
            if (this.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                removeRect = rect;
                score++;
                System.out.println(score);
            }
        });
        Main.bonuses.remove(removeRect);
        Main.root.getChildren().remove(removeRect);
    }

    public boolean isWall() {
        check = true;
        Main.wallsHorizontal.forEach((rect) -> {
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
                        Main.sideSounds.playEffectSound("src/mixkit-ethereal-fairy-win-sound-2019.wav");
                        Main.playlist.getMusicPlayer().play();
                        Main.lives--;
                        int index = Main.monsters.indexOf(Main.monsters.get(i));
                        Main.root.getChildren().remove(Main.monsters.get(index));
                        Main.monsters.remove(index);
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
                        Main.sideSounds.playEffectSound("src/mixkit-melodic-bonus-collect-1938.wav");
                        Main.playlist.getMusicPlayer().play();
                        Main.lives++;
                        int index = Main.energyBonuses.indexOf(Main.energyBonuses.get(i));
                        Main.root.getChildren().remove(Main.energyBonuses.get(index));
                        Main.energyBonuses.remove(index);
                        System.out.println(Main.lives);
                    }
                }
            }

        }
    }

    public void isFinish() {
        if(this.getBoundsInParent().intersects(Main.houseIm.getBoundsInParent())){
            Main.finishLevel=true;
        }
    }
}
