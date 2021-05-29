package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static sample.Main.finishLevel;
import static sample.Main.playlist;

public class Controller {

    protected void setLabel(Label label, double layoutX, double layoutY, int fontSize, FontWeight fontWeigh) {
        label.setFont(Font.font("Bauhaus 93", fontWeigh, fontSize));
        label.setTextFill(Color.BLACK);
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
    }

    protected void setLabel(Label label, double layoutX, double layoutY) {
        label.setFont(Font.font("Bauhaus 93", FontWeight.BOLD, 60));
        label.setTextFill(Color.WHITE);
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
    }

    protected Timeline setOneTimeLine(Character character, double duration, Timeline tl) {
        return new Timeline(
                new KeyFrame(Duration.seconds(duration),
                        actionEvent -> {
                            if (!finishLevel) {
                                if (character != null) character.setVisible(!character.isVisible());
                                else tl.stop();
                            } else {
                                if (tl != null) tl.stop();
                            }
                        }));
    }

    protected void setButton(Button b, double width, double height, double xLayout, double yLayout) {
        b.setPrefSize(width, height);
        b.setLayoutX(xLayout);
        b.setLayoutY(yLayout);
    }

    protected void setLayout(Character ch, double x, double y) {
        ch.setLayoutX(x);
        ch.setLayoutY(y);
    }

    protected void addRectanglesFirstLevel(Pane mazePane) {
        Rectangle r1 = new Rectangle(0, 0, 1000, 20),
                r2 = new Rectangle(0, 0, 20, 700),
                r3 = new Rectangle(0, 680, 1000, 20),
                r4 = new Rectangle(980, 0, 20, 700),
                r5 = new Rectangle(98, 120, 118, 20),
                r6 = new Rectangle(98, 120, 20, 140),
                r7 = new Rectangle(294, 14, 20, 233),
                r8 = new Rectangle(392, 120, 118, 20),
                r9 = new Rectangle(490, 120, 20, 140),
                r10 = new Rectangle(588, 0, 20, 140),
                r11 = new Rectangle(685, 130, 216, 20),
                r12 = new Rectangle(784, 144, 20, 233),
                r13 = new Rectangle(98, 240, 314, 20),
                r14 = new Rectangle(216, 240, 20, 140),
                r15 = new Rectangle(392, 240, 20, 140),
                r16 = new Rectangle(490, 240, 118, 20),
                r17 = new Rectangle(588, 240, 20, 140),
                r18 = new Rectangle(882, 250, 118, 20),
                r19 = new Rectangle(882, 250, 20, 140),
                r20 = new Rectangle(0, 380, 118, 20),
                r21 = new Rectangle(314, 360, 98, 20),
                r22 = new Rectangle(314, 360, 20, 140),
                r23 = new Rectangle(490, 360, 20, 140),
                r24 = new Rectangle(588, 360, 216, 20),
                r25 = new Rectangle(686, 360, 20, 140),
                r26 = new Rectangle(98, 480, 236, 20),
                r27 = new Rectangle(412, 465, 20, 140),
                r28 = new Rectangle(490, 480, 216, 20),
                r29 = new Rectangle(784, 480, 216, 20),
                r30 = new Rectangle(784, 480, 20, 110),
                r31 = new Rectangle(784, 570, 93, 20),
                r32 = new Rectangle(588, 590, 20, 110),
                r33 = new Rectangle(131, 590, 20, 110),
                r34= new Rectangle(131,590,301,20);
        mazePane.getChildren().addAll(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25,r26,r27,r28,r29,r30,r31,r32,r33,r34);
        Collections.addAll(Main.walls, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25,r26,r27,r28,r29,r30,r31,r32,r33,r34);

        for (Rectangle a : Main.walls) {
            a.setFill(Color.rgb(133, 147, 208));
        }
    }

    protected void addRectanglesSecondLevel(Pane mazePane) {
        Rectangle r1 = new Rectangle(0, 0, 1000, 20),
                r2 = new Rectangle(0, 97, 196, 20),
                r3 = new Rectangle(98, 376, 118, 20),
                r4 = new Rectangle(0, 680, 1000, 20),
                r5 = new Rectangle(196, 194, 276, 20),
                r6 = new Rectangle(294, 306, 294, 20),
                r8 = new Rectangle(196, 486, 111, 20),
                r9 = new Rectangle(198, 583, 196, 20),
                r10 = new Rectangle(389, 97, 200, 20),
                r11 = new Rectangle(294, 306, 294, 20),
                r12 = new Rectangle(389, 386, 118, 20),
                r13 = new Rectangle(389, 486, 209, 20),
                r14 = new Rectangle(578, 194, 118, 20),
                r15 = new Rectangle(578, 583, 314, 20),
                r16 = new Rectangle(676, 97, 118, 20),
                r17 = new Rectangle(676, 386, 314, 20),
                r18 = new Rectangle(774, 306, 118, 20),
                r19 = new Rectangle(0, 0, 20, 700),
                r20 = new Rectangle(98, 196, 20, 409),
                r21 = new Rectangle(196, 194, 20, 200),
                r22 = new Rectangle(196, 486, 20, 117),
                r23 = new Rectangle(291, 0, 20, 214),
                r24 = new Rectangle(291, 306, 20, 200),
                r25 = new Rectangle(389, 386, 20, 117),
                r26 = new Rectangle(477, 493, 20, 200),
                r27 = new Rectangle(578, 97, 20, 312),
                r28 = new Rectangle(676, 197, 20, 401),
                r29 = new Rectangle(774, 106, 20, 214),
                r30 = new Rectangle(774, 406, 20, 117),
                r31 = new Rectangle(872, 10, 20, 312),
                r32 = new Rectangle(980, 0, 20, 700);
        mazePane.getChildren().addAll(r1, r2, r3, r4, r5, r6, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32);
        Collections.addAll(Main.walls, r1, r2, r3, r4, r5, r6, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32);
        for (Rectangle a : Main.walls) {
            a.setFill(Color.rgb(226, 161, 154));
        }
    }

    protected void generateCompliment(ArrayList<String> complimentArray) {
        complimentArray.add("You're someone's reason to smile :)");
        complimentArray.add("You're strong!");
        complimentArray.add("Is that your picture next to \"charming\" in the dictionary?");
        complimentArray.add("You're inspiring :)");
        complimentArray.add("You're always learning new things and trying to better yourself. That's awesome!");
        complimentArray.add("You should be proud of yourself");
        complimentArray.add("You look great today!");
        complimentArray.add("Follow what calls you");
        complimentArray.add("You’re intoxicating when you do what you love");
        complimentArray.add("To be found, stop hiding.");
        complimentArray.add("All you need is love.");
        complimentArray.add("If music be the food of love, play on");
    }

    protected void setBackgroundForButton(String path, Button button) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        Image im = new Image(fis);
        BackgroundImage bi = new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(button.getWidth(), button.getHeight(), true, true, true, false));
        Background bg = new Background(bi);
        button.setBackground(bg);
    }

    protected void middleScene(String pathImg, String pathMusic, Pane pane) throws FileNotFoundException {
        playlist.playEffectSound(pathMusic);
        playlist.getMusicPlayer().play();
        FileInputStream str = new FileInputStream(pathImg);
        Image image = new Image(str);
        BackgroundImage myBI = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI));
        pane.setPrefSize(1200, 700);
    }

    protected void configureSlidersImage(Pane pane) throws FileNotFoundException {
        ImageView soundSliderOff = new ImageView(new Image(new FileInputStream("src/sound_off.png"))),
                musicSliderOn = new ImageView(new Image(new FileInputStream("src/music_on.png"))),
                musicSliderOff = new ImageView(new Image(new FileInputStream("src/music_off.png"))),
                soundSliderOn = new ImageView(new Image(new FileInputStream("src/sound_on.png")));
        soundSliderOff.setLayoutX(200);
        musicSliderOff.setLayoutX(200);
        soundSliderOff.setLayoutY(285);
        musicSliderOff.setLayoutY(240);

        soundSliderOn.setLayoutX(380);
        musicSliderOn.setLayoutX(380);
        soundSliderOn.setLayoutY(285);
        musicSliderOn.setLayoutY(240);

        soundSliderOn.setFitHeight(30);
        soundSliderOn.setFitWidth(30);

        soundSliderOff.setFitHeight(30);
        soundSliderOff.setFitWidth(30);

        musicSliderOff.setFitHeight(30);
        musicSliderOff.setFitWidth(30);

        musicSliderOn.setFitHeight(30);
        musicSliderOn.setFitWidth(30);
        pane.getChildren().addAll(musicSliderOff, musicSliderOn, soundSliderOff, soundSliderOn);
    }

    protected void setOnComplimentButton(ArrayList<String> complimentArray) {
        Random random = new Random();
        int randomNum;
        randomNum = random.nextInt(complimentArray.size());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Important message");
        alert.setHeaderText("Here is a message for you");
        alert.setContentText(complimentArray.get(randomNum));
        alert.showAndWait();
    }

    protected void addRectanglesThirdLevel(Pane mazePane) {
        Rectangle r1 = new Rectangle(0, 0, 1000, 20),
                r2 = new Rectangle(0, 0, 20, 700),
                r3 = new Rectangle(0, 680, 1000, 20),
                r4 = new Rectangle(980, 0, 20, 700),
                r5 = new Rectangle(73, 14, 20, 63),
                r6 = new Rectangle(146, 14, 20, 63),
                r7 = new Rectangle(365, 9, 20, 74),
                r8 = new Rectangle(657, 9, 20, 74),
                r9 = new Rectangle(876, 9, 20, 177),
                r10 = new Rectangle(219, 83, 93, 20),
                r11 = new Rectangle(292, 83, 20, 96),
                r12 = new Rectangle(438, 83, 20, 268),
                r13 = new Rectangle(511, 83, 93, 20),
                r14 = new Rectangle(584, 83, 20, 103),
                r15 = new Rectangle(730, 83, 93, 20),
                r16 = new Rectangle(803, 166, 93, 20),
                r17 = new Rectangle(73, 166, 20, 83),
                r18 = new Rectangle(73, 249, 93, 20),
                r19 = new Rectangle(219, 179, 20, 90),
                r20 = new Rectangle(292, 249, 93, 20),
                r21 = new Rectangle(511, 166, 20, 103),
                r22 = new Rectangle(511, 249, 93, 20),
                r23 = new Rectangle(740, 249, 248, 20),
                r24 = new Rectangle(14, 332, 79, 20),
                r25 = new Rectangle(146, 249, 20, 103),
                r26 = new Rectangle(146, 332, 166, 20),
                r27 = new Rectangle(292, 249, 20, 103),
                r28 = new Rectangle(365, 249, 20, 174),
                r29 = new Rectangle(438, 332, 166, 20),
                r30 = new Rectangle(584, 249, 20, 186),
                r31 = new Rectangle(657, 249, 20, 269),
                r32 = new Rectangle(803, 332, 123, 20),
                r33 = new Rectangle(73, 415, 458, 20),
                r35 = new Rectangle(657, 415, 270, 20),
                r36 = new Rectangle(907, 332, 20, 103),
                r37 = new Rectangle(14, 498, 79, 20),
                r38 = new Rectangle(146, 498, 20, 103),
                r39 = new Rectangle(146, 498, 531, 20),
                r40 = new Rectangle(511, 415, 20, 176),
                r41 = new Rectangle(748, 498, 20, 103),
                r42 = new Rectangle(748, 498, 242, 20),
                r43 = new Rectangle(73, 581, 93, 20),
                r45 = new Rectangle(219, 583, 20, 103),
                r46 = new Rectangle(292, 591, 239, 20),
                r47 = new Rectangle(584, 583, 20, 103),
                r48 = new Rectangle(657, 581, 111, 20),
                r49 = new Rectangle(821, 583, 20, 103),
                r50 = new Rectangle(73, 166, 385, 20),
                r51 = new Rectangle(584, 166, 166, 20),
                r52 = new Rectangle(730, 83, 20, 352);
        mazePane.getChildren().addAll(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r36, r35, r37, r38, r39, r40, r41, r42, r43, r45, r46, r47, r48, r49, r50, r51, r52);
        Collections.addAll(Main.walls, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r36, r35, r37, r38, r39, r40, r41, r42, r43, r45, r46, r47, r48, r49, r50, r51, r52);
        for (Rectangle a : Main.walls) {
            a.setFill(Color.rgb(58, 109, 155));
        }
    }

}
