package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Main extends Application {
    public static boolean fail = false;
    public static boolean finishLevel = false;
    public static List<Character> energyBonuses = new ArrayList<>();
    public static List<Rectangle> wallsHorizontal = new ArrayList<>();
    public static ArrayList<Character> monsters = new ArrayList<>();
    public static ArrayList<Rectangle> bonuses = new ArrayList<>();
    private final ArrayList<String> complimentArray = new ArrayList<>();
    private HashMap<KeyCode, Boolean> keys;
    AnimationTimer timer;
    private Timeline timeline, enemyTimeLine, enemy2Timeline, enemy3Timeline, enemy4Timeline;
    private DoubleProperty timeSeconds;
    private Duration time;
    private Label timerLabel;
    public static Label energyLabel2;
    public static Music playlist = new Music(), sideSounds = new Music();
    FileInputStream inputStreamE = new FileInputStream("src/17_burger_napkin.png"),
            inputStream = new FileInputStream("src/MyCollages__7_-removebg-preview (1).png"),
            chickenS = new FileInputStream("src/86_roastedchicken_dish.png"),
            sushiS = new FileInputStream("src/98_sushi_dish.png"),
            cakeS = new FileInputStream("src/31_chocolatecake_dish.png"),
            potatoS = new FileInputStream("src/45_frenchfries_dish.png"),
            energyDrink = new FileInputStream("src/soft_drink_red.png"),
            energyDrink1 = new FileInputStream("src/soft_drink_blue.png"),
            battery = new FileInputStream("src/battery3-removebg-preview.png");
    Image enIm = new Image(battery);
    ImageView imageEnergy = new ImageView(enIm);
    Image image = new Image(inputStream), imageE = new Image(inputStreamE),
            chicken = new Image(chickenS), sushiI = new Image(sushiS), cakeI = new Image(cakeS), potatoI = new Image(potatoS),
            energyDrinkI = new Image(energyDrink), energyDrinkI1 = new Image(energyDrink1);
    public static FileInputStream houseS;
    Button back;

    static {
        try {
            houseS = new FileInputStream("src/photo_2021-05-25_12-02-30-removebg-preview.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    Scene scene;
    public static Image house = new Image(houseS);
    public static ImageView houseIm = new ImageView(house);
    ImageView imageView = new ImageView(image), im = new ImageView(imageE), ch = new ImageView(chicken),
            sushi = new ImageView(sushiI), cake = new ImageView(cakeI), potato = new ImageView(potatoI),
            energy = new ImageView(energyDrinkI), energy1 = new ImageView(energyDrinkI1);
    Character player;
    Character enemy = new Character(im), enemy2 = new Character(ch), enemy3 = new Character(sushi), enemy4 = new Character(cake),
            enemy5 = new Character(potato), energydrink = new Character(energy), energydrink1 = new Character(energy1);

    Pane pane = new Pane();
    static Pane root;
    Button play = new Button(), instruction = new Button(), compliment = new Button(), music = new Button();

    Random random = new Random();
    private int randomNum;
    public static int levelVariable = 1, lives = 3;
    static boolean musicStatus = true;

    public Main() throws FileNotFoundException {
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        sideSounds.playEffectSound("src/silent.wav");
        setUpStartWindow();
        play.setOnAction(actionEvent -> {
            try {
                firstLevel(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        instruction.setOnAction(actionEvent -> {

        });

        music.setOnAction(actionEvent -> {
            musicStatus = !musicStatus;
            if (musicStatus) {
                try {
                    setBackgroundForButton("src/music_on.png", music);
                } catch (FileNotFoundException f) {

                }
                playlist.getMusicPlayer().play();
            } else {
                try {
                    setBackgroundForButton("src/music_off.png", music);
                } catch (FileNotFoundException f) {

                }
                playlist.getMusicPlayer().stop();
                playlist.getMusicPlayer().setVolume(0);
            }
        });

        generateCompliment();
        compliment.setOnAction(actionEvent -> {
            randomNum = random.nextInt(complimentArray.size());
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Important message");
            alert.setHeaderText("Here is a message for you");
            alert.setContentText(complimentArray.get(randomNum));
            alert.showAndWait();
        });

        stage.setTitle("Delivery Maze by Melnyk Iryna and Zhelizniak Anna");
        scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void firstLevel(Stage primaryStage) throws FileNotFoundException {
        fail = false;
        finishLevel = false;
        //  lives = 3;
        root = new Pane();
        Main.playlist.getMusicPlayer().stop();
        if (Main.musicStatus) {
            Main.playlist.background("src/Komiku_-_04_-_The_weekly_fair.mp3");
        }

        Pane mazePane = new Pane();
        Rectangle settings = new Rectangle(200, 700);
        settings.setLayoutX(1000);
        settings.setFill(Color.rgb(230, 222, 202));
        Random r = new Random();

        Label numOfOrder = new Label("O.N. " + (r.nextInt(9999999) + 1000000));
        setLabel(numOfOrder, 1040, 140, "Bauhaus 93", 17, FontWeight.NORMAL);

        Label data = new Label(new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime()));
        setLabel(data, 1060, 50, "Bauhaus 93", 13, FontWeight.BOLD);

        Label str = new Label("-------------------------------------------------");
        setLabel(str, 1000, 160, "Bauhaus 93", 25, FontWeight.BOLD);

        Label energyLabel = new Label("   ..................");
        setLabel(energyLabel, 1035, 195, "Bauhaus 93", 15, FontWeight.BOLD);

        energyLabel2 = new Label("x" + lives);
        setLabel(energyLabel2, 1160, 192, "Bauhaus 93", 25, FontWeight.NORMAL);

        imageEnergy.setFitWidth(40);
        imageEnergy.setFitHeight(50);
        imageEnergy.setLayoutX(1005);
        imageEnergy.setLayoutY(185);
        Rectangle rec = new Rectangle(1050, 500, 100, 100);
        rec.setFill(new ImagePattern(new Image(new FileInputStream("src/pause.png"))));

        rec.setOnMouseClicked(mouseEvent -> {
            timeline.stop();
            enemyTimeLine.stop();
            enemy2Timeline.stop();
            enemy3Timeline.stop();
            timer.stop();
            Button homeButton = new Button(), playButton = new Button(), info = new Button();
            try {
                setBackgroundForButton("src/cursor_right.png", playButton);
                setBackgroundForButton("src/home.png", homeButton);
                setBackgroundForButton("src/info_2.png", info);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            setButton(playButton, 150, 150, 240, 90);
            setButton(homeButton, 70, 70, 190, 127);
            setButton(info, 70, 70, 370, 130);
            Slider music = new Slider(0.0, 1.0, playlist.getMusicPlayer().getVolume()),
                    sound = new Slider(0.0, 1.0, 1);
            music.setShowTickMarks(true);
            music.setMajorTickUnit(0.2);
            music.setShowTickLabels(true);
            music.setValue(playlist.musicPlayer.getVolume());
            music.valueProperty().addListener(observable -> {
                playlist.musicPlayer.setVolume(music.getValue());
                if(music.getValue()==0) musicStatus=false;
            });

            sound.setShowTickMarks(true);
            sound.setMajorTickUnit(0.2);
            sound.setShowTickLabels(true);
            sound.setValue(sideSounds.musicPlayer.getVolume());
            sound.valueProperty().addListener(observable -> {
                sideSounds.musicPlayer.setVolume(sound.getValue());
                //if(sound.getValue()==0) sound=false;
            });



            music.setLayoutY(240);
            music.setLayoutX(235);
            sound.setLayoutY(290);
            sound.setLayoutX(235);

            Pane pane = new Pane();

            try {

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

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Stage pauseStage = new Stage();

            homeButton.setOnAction(actionEvent -> {
                pauseStage.close();
                lives = 3;
                primaryStage.setScene(scene);
            } );


            playButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    pauseStage.close();
                    timeline.play();
                    enemyTimeLine.play();
                    enemy2Timeline.play();
                    enemy3Timeline.play();
                    timer.start();
                }
            });
            pane.setBackground(new Background(new BackgroundFill(Color.rgb(230, 222, 202), CornerRadii.EMPTY, Insets.EMPTY)));
            pane.setPrefSize(600, 350);
            Label text = new Label("PAUSE");
            setLabel(text, 225, 10, "Bauhaus 93", 60, FontWeight.NORMAL);
            text.setTextFill(Color.WHITE);
            pane.getChildren().addAll(text, music, sound, info, homeButton, playButton);
            Scene scene = new Scene(pane);
            pauseStage.setScene(scene);
            pauseStage.setTitle("Pause");
            pauseStage.setResizable(false);
            pauseStage.show();
        });

        Label level = new Label("Level " + Main.levelVariable);
        setLabel(level, 1012, 80, "Bauhaus 93", 60, FontWeight.NORMAL);

        root.setPrefSize(1200, 700);

        mazePane.setPrefSize(1000, 700);
        mazePane.getChildren().addAll(settings, energy, data, numOfOrder, str, energyLabel, energyLabel2, rec);
        addRectangles(mazePane, level);
        mazePane.setBackground(new Background(new BackgroundFill(Color.rgb(113, 217, 140), CornerRadii.EMPTY, Insets.EMPTY)));
        setUpTimeLines(mazePane);
        setUpCharacters(mazePane);

        for (Rectangle a : wallsHorizontal) {
            a.setFill(Color.rgb(133, 147, 208));
            // a.setStroke(Color.WHITE);
        }
        player = new Character(imageView, 50, 60);
        setLayout(player, 862, 545);
        keys = new HashMap<>();
        Scene sceneFirstLevel = new Scene(root);

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
                    player.animation.stop();
                    try {
                        betweenLevelScene(primaryStage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    timer.stop();
                    playlist.getMusicPlayer().stop();
                    player.animation.stop();
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
        root.getChildren().addAll(player, enemy, enemy2, enemy3, enemy4, energydrink, energydrink1, houseIm, imageEnergy);
        primaryStage.setTitle("Delivery Maze");
        primaryStage.setScene(sceneFirstLevel);

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
        mazePane.getChildren().addAll(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, level);
        Collections.addAll(wallsHorizontal, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25);
    }

    private void setUpStartWindow() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("src/photo_2021-05-24_15-17-36_auto_x2.jpg");
        Image image = new Image(inputStream);
        BackgroundImage myBI = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI));
        pane.setPrefSize(1200, 680);
        Text name = new Text(50, 160, "Delivery maze");
        name.setFont(Font.font("Berlin Sans FB Demi", FontWeight.BOLD, 60));
        name.setFill(Color.WHITE);
        DropShadow is = new DropShadow();
        is.setOffsetY(3.0f);
        name.setEffect(is);
        name.setCache(true);

        setButton(play, 150, 150, 150, 200);
        setButton(instruction, 70, 70, 175, 360);
        setButton(compliment, 70, 70, 175, 470);
        setButton(music, 70, 70, 175, 570);

        setBackgroundForButton("src/music_on.png", music);
        setBackgroundForButton("src/cursor_right.png", play);
        setBackgroundForButton("src/info_2.png", instruction);
        setBackgroundForButton("src/unfavourited.png", compliment);

        playlist.background("src/Komiku_-_04_-_Shopping_List.mp3");
        if (!pane.getChildren().contains(play) && !pane.getChildren().contains(name) && !pane.getChildren().contains(compliment)
                && !pane.getChildren().contains(instruction) && !pane.getChildren().contains(music)) {
            pane.getChildren().addAll(play, name, compliment, instruction, music);
        }
    }

    private void failScene(Stage stage) throws FileNotFoundException {
        Pane pane = new Pane();
        FileInputStream str = new FileInputStream("src/pizza2.jpg");
        Image image = new Image(str);
        BackgroundImage myBI = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setPrefSize(1200, 700);
        pane.setBackground(new Background(myBI));

        Text text = new Text("You have failed this level");
        Text text1 = new Text("There is no chance to get a job here");
        text.setLayoutY(300);
        text.setLayoutX(275);
        text1.setLayoutX(150);
        text1.setLayoutY(400);
        text.setFont(Font.font("Berlin Sans FB Demi", FontWeight.BOLD, 60));
        text1.setFont(Font.font("Berlin Sans FB Demi", FontWeight.BOLD, 60));
        back = new Button();
        setButton(back, 100, 100, 500, 500);
        back.setOnAction(actionEvent -> {
            lives = 3;
            stage.setScene(scene);
        });

        setBackgroundForButton("src/home.png", back);
        pane.getChildren().addAll(text, text1, back);
        Scene scene = new Scene(pane);
        stage.setScene(scene);

    }

    private void betweenLevelScene(Stage stage) throws FileNotFoundException {
        playlist.playEffectSound("src/mixkit-ethereal-fairy-win-sound-2019.wav");
        FileInputStream str = new FileInputStream("src/pizza1.jpg");
        Image image = new Image(str);
        BackgroundImage myBI = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Pane pane = new Pane();
        pane.setBackground(new Background(myBI));
        pane.setPrefSize(1200, 700);
        Text text = new Text("You have finished " + levelVariable + " level");
        Text text1 = new Text("Congratulations!");
        text.setFont(Font.font("Berlin Sans FB Demi", FontWeight.BOLD, 60));
        text1.setFont(Font.font("Berlin Sans FB Demi", FontWeight.BOLD, 60));
        text.setLayoutY(300);
        text.setLayoutX(275);
        text1.setLayoutX(375);
        text1.setLayoutY(400);
        text.setFill(Color.WHITE);
        text1.setFill(Color.WHITE);
        Button nextLevel = new Button(), sound = new Button();
        back = new Button();
        back.setOnAction(actionEvent -> {
            lives = 3;
            stage.setScene(scene);
        });
        setButton(back, 70, 70, 465, 500);
        setButton(nextLevel, 100, 100, 565, 485);
        setButton(sound, 70, 70, 665, 500);

        setBackgroundForButton("src/home.png", back);
        setBackgroundForButton("src/fast_forward.png", nextLevel);
        setBackgroundForButton("src/music_on.png", sound);

        pane.getChildren().addAll(text, text1, back, nextLevel, sound);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
    }

    public void bonus() {
        int random = (int) Math.floor(Math.random() * 100);
        int x = (int) Math.floor(Math.random() * 600);
        int y = (int) Math.floor(Math.random() * 600);
        if (random == 5) {
            Rectangle rect = new Rectangle(20, 20, Color.RED);
            rect.setX(x);
            rect.setY(y);
            bonuses.add(rect);
            root.getChildren().addAll(rect);
        }
    }

    private void generateCompliment() {
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

    public void update() {
        if (isPressed(KeyCode.UP)) {
            player.animation.play();
            player.animation.setOffsetY(180);
            player.finalMoveY(-2);
        } else if (isPressed(KeyCode.DOWN)) {
            player.animation.play();
            player.animation.setOffsetY(0);
            player.finalMoveY(2);
        } else if (isPressed(KeyCode.RIGHT)) {
            player.animation.play();
            player.animation.setOffsetY(120);
            player.finalMoveX(2);
        } else if (isPressed(KeyCode.LEFT)) {
            player.animation.play();
            player.animation.setOffsetY(60);
            player.finalMoveX(-2);
        } else {
            player.animation.stop();
        }
    }

    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void setBackgroundForButton(String path, Button button) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        Image im = new Image(fis);
        BackgroundImage bi = new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(play.getWidth(), play.getHeight(), true, true, true, false));
        Background bg = new Background(bi);
        button.setBackground(bg);
    }

    private void setButton(Button b, double width, double height, double xLayout, double yLayout) {
        b.setPrefSize(width, height);
        b.setLayoutX(xLayout);
        b.setLayoutY(yLayout);
    }

    private void setLayout(Character ch, double x, double y) {
        ch.setLayoutX(x);
        ch.setLayoutY(y);
    }

    private void setUpCharacters(Pane mazePane) {
        setLayout(enemy, 500, 500);
        Collections.addAll(monsters, enemy, enemy2, enemy3, enemy4);
        enemy2.setVisible(false);
        setLayout(enemy2, 225, 80);
        setLayout(enemy3, 75, 400);
        setLayout(enemy4, 500, 75);
        setLayout(energydrink, 650, 75);
        setLayout(energydrink1, 340, 470);
        houseIm.setX(650);
        houseIm.setLayoutY(200);
        houseIm.setFitWidth(100);
        houseIm.setFitHeight(100);
        Collections.addAll(energyBonuses, energydrink, energydrink1);
        enemy3.setVisible(false);
        root.getChildren().add(mazePane);
    }

    private void setUpTimeLines(Pane mazePane) {
        timeSeconds = new SimpleDoubleProperty(5.0);
        time = Duration.minutes(5.00);
        timerLabel = new Label();
        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.RED);
        timerLabel.setFont(Font.font("Bauhaus 93", FontWeight.BOLD, 30));
        timerLabel.setTextFill(Color.BLACK);
        timeline = new Timeline(
                new KeyFrame(Duration.minutes(0.01),
                        t -> {
                            if (!finishLevel && !fail) {
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
        timerLabel.setLayoutX(1050);
        timerLabel.setLayoutY(450);
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
    }

    private void setLabel(Label label, double layoutX, double layoutY, String font, int fontSize, FontWeight fontweigth) {
        label.setFont(Font.font(font, fontweigth, fontSize));
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
    }

}
