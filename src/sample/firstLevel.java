package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class firstLevel {

 /*   static List<Rectangle> lines = new ArrayList<>();

    public void firstLevel(Stage primaryStage) throws FileNotFoundException {
        Main.fail = false;
        Main.lives = 3;
        root = new Pane();


        Main.playlist.getMusicPlayer().stop();
        if (Main.musicStatus) {
            Main.playlist.background("src/Komiku_-_04_-_The_weekly_fair.mp3");
        }

        Label level = new Label("Level " + Main.levelVariable);
        level.setTextFill(Color.rgb(132, 134, 241));
        level.setFont(Font.font("Berlin Sans FB Demi", FontWeight.BOLD, 60));
        level.setLayoutX(1010);
        level.setLayoutY(50);
        root.setPrefSize(1200, 700);
        Pane mazePane = new Pane();
        mazePane.setPrefSize(1000, 700);
        addRectangles(mazePane, level);
        FileInputStream inputStreamE = new FileInputStream("src/grass_auto_x2.jpg");
        Image groundImage = new Image(inputStreamE);
        BackgroundImage myBI = new BackgroundImage(groundImage,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        mazePane.setBackground(new Background(myBI));

        setUpTimeLines(mazePane);
        setUpCharacters(mazePane);

        for (Rectangle a : Main.wallsHorizontal) {
            a.setFill(Color.rgb(132, 134, 241));
            a.setStroke(Color.WHITE);
        }
        for (Rectangle l : lines) {
            l.setFill(Color.rgb(132, 134, 241));
        }

        player = new Character(imageView, 50, 60);
        setLayout(player, 850, 600);
        keys = new HashMap<>();
        root.getChildren().addAll(player, enemy, enemy2, enemy3, enemy4, energydrink, energydrink1, houseIm);

        sceneFirstLevel = new Scene(root);

        sceneFirstLevel.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        sceneFirstLevel.setOnKeyReleased(event -> keys.put(event.getCode(), false));
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lives == 0) {
                    fail = true;
                }
                if (!finishLevel && !fail) {
                    update();
                } else if (finishLevel) {
                    timer.stop();
                    playlist.getMusicPlayer().stop();
                    try {
                        betweenLevelScene(primaryStage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if (fail) {
                    timer.stop();
                    playlist.getMusicPlayer().stop();
                    try {
                        failScene(primaryStage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                //bonus();
            }
        };
        timer.start();
        primaryStage.setTitle("Delivery Maze");

    }

    private void addRectangles(Pane mazePane, Label level) {
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
        Rectangle l0 = new Rectangle(962, 655, 36.8, 10),
                l1 = new Rectangle(788, 655, 37, 10),
                l2 = new Rectangle(781, 521, 10, 37),
                l3 = new Rectangle(617, 521, 10, 37),
                l4 = new Rectangle(955, 190, 10, 37),
                l5 = new Rectangle(962, 35, 36.8, 10),
                l6 = new Rectangle(584, 35, 37, 10),
                l7 = new Rectangle(617, 130, 10, 37),
                l8 = new Rectangle(781, 130, 10, 37),
                l9 = new Rectangle(821, 385, 10, 37),
                l10 = new Rectangle(781, 352, 10, 37),
                l11 = new Rectangle(470, 345, 37, 10),
                l12 = new Rectangle(410, 385, 37, 10),
                l13 = new Rectangle(403, 527, 10, 37),
                l14 = new Rectangle(261, 520, 37, 10),
                l15 = new Rectangle(254, 201, 10, 37),
                l16 = new Rectangle(294, 267, 10, 37),
                l17 = new Rectangle(254, 447, 10, 37),
                l18 = new Rectangle(130, 480, 37, 10),
                l19 = new Rectangle(353, 35, 37, 10),
                l20 = new Rectangle(2, 35, 37, 10),
                l21 = new Rectangle(35, 307, 10, 37),
                l22 = new Rectangle(35, 110, 10, 37),
                l23 = new Rectangle(2, 655, 37, 10);
        mazePane.getChildren().addAll(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, level, l0, l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, l17, l18, l19, l20, l21, l22, l23);
        Collections.addAll(wallsHorizontal, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25);
        Collections.addAll(lines, l0, l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, l17, l18, l19, l20, l21, l22, l23);
    }

    private void setUpTimeLines(Pane mazePane) {
        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 4em;");
        timerLabel.setTextFill(Color.rgb(75, 124, 23));
        timeline = new Timeline(
                new KeyFrame(Duration.minutes(0.01),
                        t -> {
                            if (!finishLevel) {
                                Duration duration = ((KeyFrame) t.getSource()).getTime();
                                time = time.subtract(duration);
                                if (time.lessThan(Duration.minutes(0.5))) {
                                    timerLabel.setTextFill(Color.RED);
                                }
                                timeSeconds.set(time.toMinutes());
                                if (time.equals(Duration.ZERO)) {
                                    timeline.stop();
                                    fail = true;
                                }
                            } else timeline.stop();
                        })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        timerLabel.setLayoutX(1100);
        timerLabel.setLayoutY(500);
        mazePane.getChildren().add(timerLabel);
        enemyTimeLine = new Timeline(
                new KeyFrame(Duration.seconds(10),
                        actionEvent -> {
                            if (!finishLevel) {
                                if (enemy != null) {
                                    enemy.setVisible(!enemy.isVisible());
                                }
                                if (enemy2 != null) {
                                    enemy2.setVisible(!enemy2.isVisible());
                                }
                            } else enemyTimeLine.stop();
                        }));
        enemy2Timeline = new Timeline(
                new KeyFrame(Duration.seconds(7),
                        actionEvent -> {
                            if (!finishLevel) {
                                if (enemy4 != null) {
                                    enemy4.setVisible(!enemy4.isVisible());
                                } else enemy2Timeline.stop();
                            } else enemy2Timeline.stop();
                        }));
        enemy3Timeline = new Timeline(
                new KeyFrame(Duration.seconds(12),
                        actionEvent -> {
                            if (!finishLevel) {
                                if (enemy3 != null) {
                                    enemy3.setVisible(!enemy3.isVisible());
                                } else enemy3Timeline.stop();
                            } else enemy3Timeline.stop();
                        }));
        enemyTimeLine.setCycleCount(Timeline.INDEFINITE);
        enemy2Timeline.setCycleCount(Timeline.INDEFINITE);
        enemy3Timeline.setCycleCount(Timeline.INDEFINITE);
        enemyTimeLine.play();
        enemy2Timeline.play();
        enemy3Timeline.play();
    }*/
}
