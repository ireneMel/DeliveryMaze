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

import static sample.Main.*;

/**
 * contains methods for optimizing code and adding some objects onto screen
 */
public class Controller {
    /**
     * set layout and font for labels
     */
    protected void setLabel(Label label, double layoutX, double layoutY, int fontSize, FontWeight fontWeigh) {
        label.setFont(Font.font("Bauhaus 93", fontWeigh, fontSize));
        label.setTextFill(Color.BLACK);
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
    }

    /**
     * set layout of label
     */
    protected void setLabel(Label label, double layoutX, double layoutY) {
        label.setFont(Font.font("Bauhaus 93", FontWeight.BOLD, 60));
        label.setTextFill(Color.WHITE);
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
    }

    /**
     * set all parameters for timelines
     */
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

    /**
     * set parameters of button
     */
    protected void setButton(Button b, double width, double height, double xLayout, double yLayout) {
        b.setPrefSize(width, height);
        b.setLayoutX(xLayout);
        b.setLayoutY(yLayout);
    }

    /**
     * set characters layouts
     */
    protected void setLayout(Character ch, double x, double y) {
        ch.setLayoutX(x);
        ch.setLayoutY(y);
    }

    /**
     * create first level maze
     */
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
                r34 = new Rectangle(131, 590, 301, 20);
        mazePane.getChildren().addAll(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34);
        Collections.addAll(Main.walls, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34);

        for (Rectangle a : Main.walls) {
            a.setFill(Color.rgb(133, 147, 208));
        }
    }

    /**
     * create second level maze
     */
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

    /**
     * create fourth level maze
     */
    protected void addRectanglesFourthLevel(Pane mazePane) {
        Rectangle r1 = new Rectangle(0, 0, 1000, 20),
                r2 = new Rectangle(0, 0, 20, 700),
                r3 = new Rectangle(0, 680, 1000, 20),
                r4 = new Rectangle(980, 0, 20, 700),
                r5 = new Rectangle(144, 0, 20, 187),
                r6 = new Rectangle(216, 85, 20, 105),
                r8 = new Rectangle(288, 0, 20, 275),
                r9 = new Rectangle(360, 0, 20, 105),
                r10 = new Rectangle(432, 170, 20, 169),
                r11 = new Rectangle(72, 255, 20, 95),
                r12 = new Rectangle(144, 340, 20, 105),
                r13 = new Rectangle(288, 340, 20, 187),
                r14 = new Rectangle(360, 285, 20, 131),
                r15 = new Rectangle(72, 425, 20, 105),
                r17 = new Rectangle(216, 510, 20, 187),
                r18 = new Rectangle(360, 511, 20, 187),
                r19 = new Rectangle(432, 404, 20, 206),
                r20 = new Rectangle(500, 285, 20, 137),
                r16 = new Rectangle(576, 0, 20, 105),
                r21 = new Rectangle(576, 342, 20, 187),
                r22 = new Rectangle(648, 0, 20, 347),
                r23 = new Rectangle(648, 425, 20, 105),
                r24 = new Rectangle(720, 172, 20, 177),
                r25 = new Rectangle(723, 425, 20, 275),
                r26 = new Rectangle(792, 0, 20, 105),
                r27 = new Rectangle(792, 175, 20, 341),
                r28 = new Rectangle(874, 85, 20, 105),
                r31 = new Rectangle(874, 274, 20, 169),
                r34 = new Rectangle(792, 85, 92, 20),
                r33 = new Rectangle(792, 511, 102, 20),
                r29 = new Rectangle(795, 595, 199, 20),
                r30 = new Rectangle(946, 70, 54, 20),
                r35 = new Rectangle(720, 170, 92, 20),
                r36 = new Rectangle(648, 85, 92, 20),
                r37 = new Rectangle(648, 337, 92, 20),
                r38 = new Rectangle(648, 424, 92, 20),
                r39 = new Rectangle(576, 255, 92, 20),
                r40 = new Rectangle(432, 85, 164, 20),
                r41 = new Rectangle(360, 170, 236, 20),
                r42 = new Rectangle(360, 285, 157, 20),
                r44 = new Rectangle(432, 594, 236, 20),
                r45 = new Rectangle(144, 85, 92, 20),
                r47 = new Rectangle(0, 170, 92, 20),
                r48 = new Rectangle(144, 255, 157, 20),
                r49 = new Rectangle(0, 340, 92, 20),
                r50 = new Rectangle(216, 340, 92, 20),
                r51 = new Rectangle(72, 425, 164, 20),
                r52 = new Rectangle(72, 510, 164, 20),
                r53 = new Rectangle(0, 595, 164, 20),
                r54 = new Rectangle(504, 510, 92, 20),
                r56 = new Rectangle(360, 511, 20, 187),
                r57 = new Rectangle(288, 595, 92, 20),
                r58 = new Rectangle(874, 426, 112, 20),
                r55 = new Rectangle(874, 274, 54, 20);
        mazePane.getChildren().addAll(r1, r54, r19, r56, r57, r55, r58, r2, r3, r53, r4, r5, r34, r6, r8, r9, r10, r11, r44, r12, r13, r14, r15, r16, r17, r18, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r33, r36, r35, r37, r38, r39, r40, r41, r42, r45, r47, r48, r49, r50, r51, r52);
        Collections.addAll(Main.walls, r1, r54, r2, r19, r56, r57, r55, r58, r3, r4, r53, r5, r6, r34, r8, r9, r10, r11, r12, r44, r13, r14, r15, r16, r17, r18, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r33, r36, r35, r37, r38, r39, r40, r41, r42, r45, r47, r48, r49, r50, r51, r52);
        for (Rectangle a : Main.walls) {
            a.setFill(Color.rgb(74, 137, 55));
        }
    }

    /**
     * create fifth level maze
     */
    protected void addRectanglesFifthLevel(Pane mazePane) {
        Rectangle r1 = new Rectangle(0, 0, 1200, 20),
                r2 = new Rectangle(0, 0, 20, 900),
                r3 = new Rectangle(0, 880, 1200, 20),
                r4 = new Rectangle(1180, 0, 20, 900),
                r5 = new Rectangle(20, 229, 53, 20),
                r6 = new Rectangle(20, 458, 126, 20),
                r7 = new Rectangle(20, 797, 53, 20),
                r8 = new Rectangle(73, 83, 20, 83),
                r9 = new Rectangle(73, 395, 20, 83),
                r10 = new Rectangle(83, 541, 20, 83),
                r11 = new Rectangle(83, 706, 20, 48),
                r12 = new Rectangle(93, 146, 126, 20),
                r13 = new Rectangle(93, 312, 53, 20),
                r14 = new Rectangle(83, 541, 193, 20),
                r15 = new Rectangle(83, 623, 126, 20),
                r16 = new Rectangle(83, 734, 53, 20),
                r17 = new Rectangle(146, 20, 20, 83),
                r18 = new Rectangle(146, 166, 20, 229),
                r19 = new Rectangle(136, 643, 20, 20),
                r20 = new Rectangle(136, 734, 20, 83),
                r21 = new Rectangle(146, 375, 219, 20),
                r22 = new Rectangle(156, 797, 73, 20),
                r23 = new Rectangle(219, 83, 20, 83),
                r24 = new Rectangle(219, 229, 20, 83),
                r25 = new Rectangle(219, 395, 20, 146),
                r26 = new Rectangle(209, 623, 20, 194),
                r27 = new Rectangle(239, 83, 189, 20),
                r28 = new Rectangle(239, 229, 189, 20),
                r29 = new Rectangle(219, 458, 144, 20),
                r30 = new Rectangle(229, 706, 73, 20),
                r31 = new Rectangle(292, 166, 20, 83),
                r32 = new Rectangle(345, 312, 20, 83),
                r33 = new Rectangle(282, 623, 20, 103),
                r34 = new Rectangle(282, 797, 20, 103),
                r35 = new Rectangle(282, 623, 73, 20),
                r36 = new Rectangle(302, 797, 126, 20),
                r37 = new Rectangle(365, 83, 20, 83),
                r38 = new Rectangle(355, 581, 20, 62),
                r39 = new Rectangle(375, 623, 189, 20),
                r40 = new Rectangle(355, 706, 266, 20),
                r41 = new Rectangle(302, 797, 126, 20),
                r42 = new Rectangle(428, 725, 20, 92),
                r43 = new Rectangle(481, 20, 20, 146),
                r44 = new Rectangle(428, 166, 126, 20),
                r45 = new Rectangle(428, 166, 20, 352),
                r46 = new Rectangle(448, 415, 46, 20),
                r47 = new Rectangle(446, 498, 274, 20),
                r48 = new Rectangle(511, 783, 20, 103),
                r49 = new Rectangle(544, 581, 20, 61),
                r50 = new Rectangle(554, 332, 20, 83),
                r51 = new Rectangle(501, 332, 63, 20),
                r52 = new Rectangle(501, 249, 20, 83),
                r53 = new Rectangle(554, 103, 20, 83),
                r54 = new Rectangle(554, 395, 166, 20),
                r55 = new Rectangle(627, 498, 20, 103),
                r56 = new Rectangle(607, 706, 20, 103),
                r57 = new Rectangle(627, 20, 20, 302),
                r58 = new Rectangle(574, 249, 73, 20),
                r59 = new Rectangle(627, 498, 20, 103),
                r60 = new Rectangle(627, 581, 166, 20),
                r61 = new Rectangle(700, 581, 20, 145),
                r62 = new Rectangle(627, 789, 166, 20),
                r63 = new Rectangle(700, 83, 20, 40),
                r64 = new Rectangle(700, 103, 292, 20),
                r65 = new Rectangle(700, 186, 73, 20),
                r66 = new Rectangle(700, 186, 20, 229),
                r67 = new Rectangle(773, 437, 20, 146),
                r68 = new Rectangle(773, 437, 166, 20),
                r69 = new Rectangle(773, 123, 20, 83),
                r70 = new Rectangle(793, 664, 20, 145),
                r71 = new Rectangle(793, 664, 292, 20),
                r72 = new Rectangle(846, 188, 20, 83),
                r73 = new Rectangle(846, 188, 219, 20),
                r74 = new Rectangle(793, 271, 73, 20),
                r75 = new Rectangle(773, 271, 20, 83),
                r76 = new Rectangle(773, 354, 345, 20),
                r77 = new Rectangle(866, 797, 20, 103),
                r78 = new Rectangle(866, 779, 73, 20),
                r79 = new Rectangle(972, 860, 20, 40),
                r80 = new Rectangle(1009, 779, 76, 20),
                r81 = new Rectangle(1065, 664, 20, 123),
                r82 = new Rectangle(1108, 860, 20, 40),
                r83 = new Rectangle(1138, 766, 62, 20),
                r84 = new Rectangle(846, 518, 20, 146),
                r85 = new Rectangle(919, 437, 20, 146),
                r86 = new Rectangle(919, 480, 73, 20),
                r87 = new Rectangle(919, 563, 166, 20),
                r88 = new Rectangle(1065, 437, 20, 146),
                r89 = new Rectangle(1065, 437, 62, 20),
                r90 = new Rectangle(1138, 520, 62, 20),
                r91 = new Rectangle(919, 271, 20, 83),
                r92 = new Rectangle(992, 271, 73, 20),
                r93 = new Rectangle(1045, 103, 20, 168),
                r94 = new Rectangle(1127, 271, 73, 20),
                r95 = new Rectangle(1127, 186, 73, 20),
                r96 = new Rectangle(1045, 103, 73, 20),
                r97 = new Rectangle(1098, 83, 20, 40),
                r98 = new Rectangle(846, 0, 20, 40),
                r99 = new Rectangle(972, 83, 20, 40);
        mazePane.getChildren().addAll(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r98, r99);
        Collections.addAll(Main.walls, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r98, r99);
        for (Rectangle a : Main.walls) {
            a.setFill(Color.rgb(26, 132, 167));
        }
        System.out.println(walls.indexOf(r38));
    }

    /**
     * method generates a message for compliment windo
     */
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

    /**
     * method set image as a background for butto
     */
    protected void setBackgroundForButton(String path, Button button) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        Image im = new Image(fis);
        BackgroundImage bi = new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(button.getWidth(), button.getHeight(), true, true, true, false));
        Background bg = new Background(bi);
        button.setBackground(bg);
    }

    /**
     * helper method
     * create pop up window
     */
    protected void popUpWindow(String header, String content, Alert.AlertType alert) {
        Alert errorAlert = new Alert(alert);
        errorAlert.setHeaderText(header);
        errorAlert.setContentText(content);
        errorAlert.showAndWait();
    }

    /**
     * set background and music for middle scenes
     */
    protected void middleScene(String pathImg, String pathMusic, Pane pane) throws FileNotFoundException {
        playlist.getMusicPlayer().stop();
        playlist.playEffectSound(pathMusic);
        playlist.getMusicPlayer().play();
        playlist.setVolume(0.3);
        middleScene(pathImg,pane);
    }
    /**
     * set background for middle scenes
     */
    protected void middleScene(String pathImg, Pane pane) throws FileNotFoundException {
        FileInputStream str = new FileInputStream(pathImg);
        Image image = new Image(str);
        BackgroundImage myBI = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI));
        if(levelVariable!=6) pane.setPrefSize(1200, 700);
    }
    /**
     * set background and music for middle scenes
     */
    protected void middleScene(String pathMusic) throws FileNotFoundException {
        playlist.getMusicPlayer().stop();
        playlist.playEffectSound(pathMusic);
        playlist.getMusicPlayer().play();
        playlist.setVolume(0.3);
    }

    /**
     * set music images for sliders on pause window
     */
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

    /**
     * set compliment windo
     */
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

    /**
     * create third level maze
     */
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
                r13 = new Rectangle(531, 83, 73, 20),
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
                r37 = new Rectangle(14, 498, 69, 20),
                r38 = new Rectangle(146, 498, 20, 103),
                r39 = new Rectangle(146, 498, 531, 20),
                r40 = new Rectangle(511, 415, 20, 176),
                r41 = new Rectangle(748, 498, 20, 103),
                r42 = new Rectangle(748, 498, 242, 20),
                r43 = new Rectangle(73, 581, 93, 20),
                r45 = new Rectangle(219, 583, 20, 103),
                r46 = new Rectangle(312, 591, 219, 20),
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
