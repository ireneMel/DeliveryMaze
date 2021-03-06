package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * contains methods for moving characters and checking for obstacles, pizzas, energies, walls
 */
public class Character extends Pane {

    ImageView imageView;
    int count = 3;
    int columns = 3;
    int offsetX = 0;
    int offsetY = 0;
    int width = 90;
    int height = 60;
    int score = 0;
    ArrayList<Character> al;
    boolean check;
    Rectangle removeRect = null;
    CharacterAnimation animation;

    public Character(ImageView imageView, int width, int height) {
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        this.width = width;
        this.height = height;
        al=new ArrayList<>();
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

    /**
     * move player by x
     * @param x
     */
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

    /**
     * move enemy by x
     * @param x
     */
    public void moveEnemyX(double x) {
        boolean right = x > 0;
        for (int i = 0; i < Math.abs(x); i++) {
            if (right) this.setTranslateX(this.getTranslateX() + 1);
            else this.setTranslateX(this.getTranslateX() - 1);
        }
        isPlayer();
    }

    /**
     * move enemy by y
     * @param y
     */
    public void moveEnemyY(double y) {
        boolean down = y > 0;
        for (int i = 0; i < Math.abs(y); i++) {
            if (down) this.setTranslateY(this.getTranslateY() + 1);
            else this.setTranslateY(this.getTranslateY() - 1);
        }
        isPlayer();
    }

    /**
     * move player by x and check for walls
     * @param x
     */
    public void finalMoveX(double x) {
        moveX(x);
        if (isWall()) {
            moveX(-1 * x);
        }
        isFinish();
        isMonster();
        isPizza();
    }

    /**
     * move player by y
     * @param y
     */
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
    /**
     * move player by y and check for walls
     * @param y
     */
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

    /**
     * check for walls
     * @return
     */
    public boolean isWall() {
        check = true;
        Main.walls.forEach((rect) -> {
            if (this.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                check = false;
            }
        });

        return !check;
    }

    /**
     * check for obstacles
     */
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

    /**
     * check for player (special method used on moving obstacles)
     */
    public void isPlayer(){
        if (this.al.size() > 0) {
            for (int i = 0; i < this.al.size(); i++) {
                if (this.getBoundsInParent().intersects(Main.players.get(i).getBoundsInParent())) {
                    if (this.al.get(i).isVisible()) {
                        Main.lives--;
                        Main.energyLabel2.setText("x" + Main.lives);
                        if(al!=null) {
                            int index = this.al.indexOf(Main.players.get(i));
                            Main.root.getChildren().remove(this);
                            this.al.remove(index);
                            //Main.monsters.remove(this);
                            Main.sideSounds.playEffectSound("src/lose_energy.mp3");
                        }
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

    /**
     * check for energy picture
     */

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
boolean firstTook=false;

    /**
     * check for pizza
     */
    public void isPizza() {
        boolean took=false;
        if (Main.pizzas.size() > 0) {
            for (int i = 0; i < Main.pizzas.size(); i++) {
                if (this.getBoundsInParent().intersects(Main.pizzas.get(i).getBoundsInParent())) {
                    int index = Main.pizzas.indexOf(Main.pizzas.get(i));
                    Main.root.getChildren().remove(Main.pizzas.get(index));
                    if(Main.pizzas.size()==2&&!firstTook){
                        Main.drawCross();firstTook=true;took=true;
                    }
                    if(Main.pizzas.size()==1) Main.drawCross();
                    Main.pizzas.remove(index);
                    Main.sideSounds.playEffectSound("src/for_pizza.mp3");
                    Main.sideSounds.getMusicPlayer().setVolume(Main.sound.getValue());
                    System.out.println(i);

                    if(firstTook&&!took){
                    Main.drawCross2();}
                    Main.countPizzas++;
                    Main.numberPizza.setText("Total pizza count: "+Main.countPizzas+"/7");
                    if (Main.musicStatus) {
                        Main.playlist.getMusicPlayer().play();
                    }
                }
            }

        }
    }

    /**
     * check for finish house
     */
    public void isFinish() {
        if (this.getBoundsInParent().intersects(Main.houseIm.getBoundsInParent())) {
            Main.finishLevel = true;
        }
    }

    /**
     * check for specific wall (only for obstacles)
     * @param rect
     * @return
     */
    public boolean isExactWall(Rectangle rect){
        check = true;
            if (this.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                check = false; }

        return !check;
    }

}
