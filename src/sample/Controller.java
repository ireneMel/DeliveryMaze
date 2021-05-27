package sample;

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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static sample.Main.playlist;

public class Controller {

    protected void setLabel(Label label, double layoutX, double layoutY, int fontSize, FontWeight fontweigth) {
        label.setFont(Font.font("Bauhaus 93", fontweigth, fontSize));
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
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
        Rectangle r1 = new Rectangle(786, 519, 40, 155),
                r2 = new Rectangle(622, 519, 164, 40),
                r3 = new Rectangle(786, 128, 40, 295),
                r4 = new Rectangle(622, 128, 164, 40),
                r5 = new Rectangle(582, 40, 40, 200),
                r6 = new Rectangle(408, 350, 378, 40),
                r7 = new Rectangle(408, 390, 40, 175),
                r8 = new Rectangle(259, 525, 149, 40),
                r9 = new Rectangle(259, 141, 40, 384),
                r10 = new Rectangle(128, 445, 131, 40),
                r11 = new Rectangle(582, 475, 40, 84),
                r12 = new Rectangle(128, 485, 40, 91),
                r13 = new Rectangle(14, 305, 138, 40),
                r14 = new Rectangle(172, 199, 87, 40),
                r15 = new Rectangle(0, 108, 110, 40),
                r16 = new Rectangle(468, 160, 40, 190),
                r17 = new Rectangle(299, 265, 54, 40),
                r18 = new Rectangle(351, 40, 40, 92),
                r19 = new Rectangle(907, 188, 53, 40),
                r20 = new Rectangle(826, 383, 47, 40),
                r21 = new Rectangle(500, 683, 40, 48),
                r22 = new Rectangle(0, 0, 40, 700),
                r23 = new Rectangle(960, 0, 40, 700),
                r24 = new Rectangle(0, 0, 1000, 40),
                r25 = new Rectangle(0, 660, 1000, 40);

        mazePane.getChildren().addAll(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25);
        Collections.addAll(Main.walls, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25);

        for (Rectangle a : Main.walls) {
            a.setFill(Color.rgb(133, 147, 208));
        }
    }
    protected void addRectanglesSecondLevel(Pane mazePane){
        Rectangle r1=new Rectangle(0,0,30,700),
                r2=new Rectangle(30,0,940,30),
                r3=new Rectangle(30,670,940,30),
                r4=new Rectangle(970,0,30,700),
                r5=new Rectangle(30,0,940,30),
                r6=new Rectangle(30,93,200,30),
                //r7=new Rectangle(30,540,200,30),
                r8=new Rectangle(30,219,70,30),
                r9=new Rectangle(100,186,30,63),
                r10=new Rectangle(30,358,760,30),
                r11=new Rectangle(30,520,37,30),
                r12=new Rectangle(132,569,30,106),
                r13=new Rectangle(162,569,68,30),
                r14=new Rectangle(200,463,30,106),
                r15=new Rectangle(132,463,68,30),
                r16=new Rectangle(327,327,30,280),
                r17=new Rectangle(357,577,200,30),
                r18=new Rectangle(557,562,30,45),
                r19=new Rectangle(427,484,30,30),
                r20=new Rectangle(427,463,271,30),
                r21=new Rectangle(557,324,30,53),
                r22=new Rectangle(668,462,30,145),
                r23=new Rectangle(668,577,131,30),
                r24=new Rectangle(799,419,30,188),
                r25=new Rectangle(775,419,114,30),
                r26=new Rectangle(760,372,30,78),
                r27=new Rectangle(908,554,91,30),
                r28=new Rectangle(786,256,207,30),
                r29=new Rectangle(756,208,30,78),
                r30=new Rectangle(545,204,240,30),
                r31=new Rectangle(639,112,30,145),
                r32=new Rectangle(639,108,225,30),
                r33=new Rectangle(849,108,30,63),
                r35=new Rectangle(209,93,30,198),
                r36=new Rectangle(209,203,200,30),
                r37=new Rectangle(389,92,30,167),
                r38=new Rectangle(389,93,143,30)

        ;
        mazePane.getChildren().addAll(r1, r2, r3, r4, r5, r6, r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r35,r36,r37,r38);
        Collections.addAll(Main.walls, r1, r2, r3, r4, r5, r6, r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r35,r36,r37,r38);
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

    protected void setOnComplimentButton(ArrayList<String> complimentArray){
        Random random = new Random();
        int randomNum;
        randomNum = random.nextInt(complimentArray.size());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Important message");
        alert.setHeaderText("Here is a message for you");
        alert.setContentText(complimentArray.get(randomNum));
        alert.showAndWait();
    }

}
