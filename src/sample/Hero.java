package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Hero extends Pane {

    ImageView imageView;
    int count = 3;
    int columns = 3;
    int offsetX = 0;
    int offsetY = 0;
    int width = 90;
    int height = 60;
    int score = 0;
    boolean check,checkMonster;
    Enemy remove;
    Rectangle removeRect = null;
    HeroAnimation animation;

    public Hero(ImageView imageView,int width, int height) {
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        this.width=width;
        this.height=height;
        animation = new HeroAnimation(imageView, Duration.millis(200), count, columns, offsetX, offsetY, width, height);
        getChildren().addAll(imageView);
    }

    public void moveX(int x) {
        boolean right = x > 0;
        for (int i = 0; i < Math.abs(x); i++) {
            // if (!isWall()) {
            if (right) this.setTranslateX(this.getTranslateX() + 1);
            else this.setTranslateX(this.getTranslateX() - 1);
            isBonuseEat();isMonster();
            // }
        }
    }

    public void finalMoveX(int x) {
        moveX(x);
        if (isWall()) {
            moveX(-1 * x);
        }
        isMonster();
    }

    public void moveY(int y) {
        boolean down = y > 0;
        for (int i = 0; i < Math.abs(y); i++) {
            //if (!isWall()) {
            if (down) this.setTranslateY(this.getTranslateY() + 1);
            else this.setTranslateY(this.getTranslateY() - 1);
            isBonuseEat();
            isMonster();
            // }
        }
    }

    public void finalMoveY(int y) {
        moveY(y);
        if (isWall()) {
            moveY(-1 * y);
        }
        isMonster();
    }

    public void isBonuseEat() {
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
        //checkMonster=false;
/*        if(Main.monsters.size()!=0) {
            Main.monsters.forEach((enemy) -> {
                if (this.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                    if (enemy.isVisible()) {
                        //checkMonster=true;
                        Main.lives--;
                        int index = Main.monsters.indexOf(enemy);
                        int index1 = Main.root.getChildren().indexOf(Main.monsters.get(index));
                        System.out.println(index + " " + index1);
                        Main.root.getChildren().remove(index1);
                        Main.monsters.remove(index);
                        //System.out.println(Main.lives);
                    }
                }
            });
        }*/
        if (Main.monsters.size() > 0) {
            for (int i = 0; i < Main.monsters.size(); i++) {
                if (this.getBoundsInParent().intersects(Main.monsters.get(i).getBoundsInParent())) {
                    if (Main.monsters.get(i).isVisible()) {
                        //checkMonster=true;
                        Main.lives--;
                        int index = Main.monsters.indexOf(Main.monsters.get(i));
                        int index1 = Main.root.getChildren().indexOf(Main.monsters.get(index));
                        System.out.println(index + " " + index1);
                        Main.root.getChildren().remove(index1);
                        Main.monsters.remove(index);
                        System.out.println(Main.lives);
                    }
                }
            }

        }
    }
}
