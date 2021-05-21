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
    boolean check;
    Rectangle removeRect = null;
    HeroAnimation animation;

    public Hero(ImageView imageView) {
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new HeroAnimation(imageView, Duration.millis(200), count, columns, offsetX, offsetY, width, height);
        getChildren().addAll(imageView);
    }

    public void moveX(int x) {
        boolean right = x > 0;
        for (int i = 0; i < Math.abs(x); i++) {
            // if (!isWall()) {
            if (right) this.setTranslateX(this.getTranslateX() + 1);
            else this.setTranslateX(this.getTranslateX() - 1);
            isBonuseEat();
            // }
        }
    }

    public void finalMoveX(int x) {
        moveX(x);
        if (isWall()) {
            moveX(-1 * x);
        }
    }

    public void moveY(int y) {
        boolean down = y > 0;
        for (int i = 0; i < Math.abs(y); i++) {
            //if (!isWall()) {
            if (down) this.setTranslateY(this.getTranslateY() + 1);
            else this.setTranslateY(this.getTranslateY() - 1);
            isBonuseEat();
            // }
        }
    }

    public void finalMoveY(int y) {
        moveY(y);
        if (isWall()) {
            moveY(-1 * y);
        }
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
}
