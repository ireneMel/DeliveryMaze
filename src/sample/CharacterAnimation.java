package sample;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Class helps to create a sprite animation
 */
public class CharacterAnimation extends Transition {
    private final ImageView imageView;
    private final int count;
    private final int columns;
    private int offsetX;
    private int offsetY;
    private final int width;
    private final int height;

    /**
     * constructor set all parameters of an image
     * @param imageView
     * @param duration
     * @param count
     * @param columns
     * @param offsetX
     * @param offsetY
     * @param width
     * @param height
     */
    public CharacterAnimation(ImageView imageView,
                              Duration duration,
                              int count, int columns,
                              int offsetX, int offsetY,
                              int width, int height) {
        this.imageView = imageView;
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        setCycleDuration(duration);
        setCycleCount(Animation.INDEFINITE);
        setInterpolator(Interpolator.LINEAR);
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
    }

    /**
     * set an image on the correct row of animation
     */
    public void setOffsetY(int y){
        this.offsetY = y;
    }

    /**
     * set sprites
     */
    protected void interpolate(double frac) {
        final int index = Math.min((int)Math.floor(count*frac), count-1);
        final int x = (index%columns)*width+offsetX;
        final int y = (index/columns)*height+offsetY;
        imageView.setViewport(new Rectangle2D(x, y, width, height));
    }
}
