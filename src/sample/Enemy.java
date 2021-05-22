package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Enemy extends Pane {
    int offsetX = 0;
    int offsetY = 0;
    int width = 90;
    int height = 60;
    ImageView imageView;
    public Enemy(ImageView imageView) {
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        getChildren().addAll(imageView);
    }
}
