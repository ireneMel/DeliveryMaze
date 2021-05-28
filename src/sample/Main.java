package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.Event;
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

public class Main extends Application {

    public static boolean fail = false, musicStatus = true, finishLevel = false;
    public static ArrayList<Character> energyBonuses, monsters, pizzas,players;
    public static List<Rectangle> walls;
    public static ArrayList<Rectangle> bonuses = new ArrayList<>();
    private final ArrayList<String> complimentArray = new ArrayList<>();
    private HashMap<KeyCode, Boolean> keys;
    AnimationTimer timer, vanTimer;
    private Timeline timeline, enemyTimeLine, enemy2Timeline, enemy3Timeline, enemy4Timeline, vanTimeLine;
    private Duration time;
    public static Label energyLabel2;
    public static Music playlist = new Music(), sideSounds = new Music();
    static Controller controller = new Controller();
    Label numOfOrder;
    Random r = new Random();
    public static Slider sound = setMusicSliders(1.0, sideSounds);

    ImageView imageView = new ImageView(new Image(new FileInputStream("src/MyCollages__11_-removebg-preview (1).png"))),
            imageEnergy = new ImageView(new Image(new FileInputStream("src/Battery.png"))),
            imageEnergy2 = new ImageView(new Image(new FileInputStream("src/Battery3.png"))),
            pizzaBoxIV1 = new ImageView(new Image(new FileInputStream("src/New_Piskel__2_-removebg-preview2.png"))),
            enemy6I = new ImageView(new Image(new FileInputStream("src/ice-cream-van_3.png")));
    static Character player;
    Character burger = new Character(new ImageView(new Image(new FileInputStream("src/17_burger_napkin.png")))),
            chicken = new Character(new ImageView(new Image(new FileInputStream("src/86_roastedchicken_dish.png")))),
            sushi = new Character(new ImageView(new Image(new FileInputStream("src/98_sushi_dish.png")))),
            chocolate = new Character(new ImageView(new Image(new FileInputStream("src/31_chocolatecake_dish.png")))),
            enemy5 = new Character(new ImageView(new Image(new FileInputStream("src/45_frenchfries_dish.png")))),
            vanLeft = new Character(enemy6I, 86, 97),
            energyDrinkRed = new Character(new ImageView(new Image(new FileInputStream("src/soft_drink_red.png")))),
            energyDrinkBlue = new Character(new ImageView(new Image(new FileInputStream("src/soft_drink_blue.png")))),
            pizza = new Character(70, 30, new ImageView(new Image(new FileInputStream("src/New_Piskel__2_-removebg-preview.png"))));

    Pane pane = new Pane();
    static Pane root;
    Button play = new Button(), instruction = new Button(), compliment = new Button(), music = new Button(), back;
    public static ImageView houseIm, houseIm2;

    private double velocity = 2;

    static {
        try {
            houseIm = new ImageView(new Image(new FileInputStream("src/photo_2021-05-25_12-02-30-removebg-preview.png")));
            houseIm2 = new ImageView(new Image(new FileInputStream("src/IMG_20210527_181324.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    Scene scene;
    public static int levelVariable = 1, lives = 3;

    public Main() throws FileNotFoundException {
    }

    private void initialize() {
        monsters = new ArrayList<>();
        walls = new ArrayList<>();
        energyBonuses = new ArrayList<>();
        pizzas = new ArrayList<>();
        players=new ArrayList<>();
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        setUpStartWindow();
        play.setOnAction(actionEvent -> {
            try {
                firstLevel(stage);
                // secondLevel(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        instruction.setOnAction(actionEvent -> {

        });

        music.setOnAction(actionEvent -> changeMusicIcon(music));

        controller.generateCompliment(complimentArray);

        compliment.setOnAction(actionEvent -> controller.setOnComplimentButton(complimentArray));

        stage.setTitle("Delivery Maze by Melnyk Iryna and Zhelizniak Anna");
        scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void settingsForLevels(Stage primaryStage, Pane mazePane, Color color, String path) throws FileNotFoundException {
        playlist.getMusicPlayer().stop();
        fail = false;
        finishLevel = false;
        root = new Pane();
        initialize();
        player = new Character(imageView, 50, 60);
        Rectangle settings = new Rectangle(200, 700);
        settings.setLayoutX(1000);
        settings.setFill(color);
        keys = new HashMap<>();
        Rectangle rec = new Rectangle(1050, 550, 100, 100);
        rec.setFill(new ImagePattern(new Image(new FileInputStream("src/pause.png"))));
        rec.setOnMouseClicked(mouseEvent -> pauseWindow(primaryStage, color));
        root.setPrefSize(1200, 700);
        mazePane.setPrefSize(1000, 700);
        setLabelsForSideMenu(mazePane, settings);
        mazePane.getChildren().add(rec);
        mazePane.setBackground(new Background(new BackgroundFill(Color.rgb(113, 217, 140), CornerRadii.EMPTY, Insets.EMPTY)));
        primaryStage.setTitle("Delivery Maze");
        playlist.background(path);
        if (!musicStatus) playlist.setVolume(0);
        playlist.getMusicPlayer().play();
    }

    private void firstLevel(Stage primaryStage) throws FileNotFoundException {
        velocity=2;
        houseIm = new ImageView(new Image(new FileInputStream("src/photo_2021-05-25_12-02-30-removebg-preview.png")));
        Pane mazePane = new Pane();
        levelVariable = 1;
        settingsForLevels(primaryStage, mazePane, Color.rgb(230, 222, 202), "src/Farming-By-Moonlight.mp3");
        setUpTimeLines(mazePane);
        setUpCharacters(mazePane);
        controller.setLayout(player, 862, 555);players.add(player);
        Scene sceneFirstLevel = new Scene(root);
        sceneFirstLevel.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        sceneFirstLevel.setOnKeyReleased(event -> keys.put(event.getCode(), false));
        generateTimer(primaryStage);
        timer.start();
        // controller.addRectanglesFirstLevel(mazePane);
        root.getChildren().addAll(player, burger, chicken, sushi, chocolate, energyDrinkRed, energyDrinkBlue, houseIm, imageEnergy, pizza);
        primaryStage.setScene(sceneFirstLevel);
    }

    double x = 0.7, property = 1;

    private void secondLevel(Stage primaryStage) throws FileNotFoundException {
        Pane mazePane = new Pane();
        vanLeft = new Character(enemy6I, 86, 97);
        houseIm = houseIm2;
        settingsForLevels(primaryStage, mazePane, Color.rgb(245, 210, 227), "src/Wavecont-Inspire-2-Full-Lenght.mp3");
        setUpTimeLines(mazePane);
        setUpCharacters2(mazePane);
        controller.setLayout(player, 50, 605);players.add(player);
        Scene sceneFirstLevel = new Scene(root);
        sceneFirstLevel.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        sceneFirstLevel.setOnKeyReleased(event -> keys.put(event.getCode(), false));
        numOfOrder.setText("O.N. " + (r.nextInt(9999999) + 1000000));
        generateTimer(primaryStage);

        vanTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (vanLeft.isWall()) {
                    x = -1 * x;
                    if (property > 0) property = -1;
                    else property = 1;
                    vanLeft.setScaleX(property);
                }
                vanLeft.animation.play();
                vanLeft.animation.setOffsetY(0);
                vanLeft.moveVanX(x);
            }
        };
        timer.start();
        vanTimer.start();
        controller.addRectanglesSecondLevel(mazePane);
        root.getChildren().addAll(player, burger, chicken, sushi, chocolate, energyDrinkRed, energyDrinkBlue, houseIm2, imageEnergy2, pizza, vanLeft);
        primaryStage.setScene(sceneFirstLevel);
    }

    private void generateTimer(Stage primaryStage) {
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
                    player.animation.stop();
                    vanLeft.animation.stop();
                    try {
                        betweenLevelScene(primaryStage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    timer.stop();
                    player.animation.stop();
                    vanLeft.animation.stop();
                    try {
                        failScene(primaryStage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                //bonus();
            }
        };
    }

    public static void drawCross() {
        Rectangle h = new Rectangle(1160, 260, 25, 5),
                v = new Rectangle(1170, 250, 5, 25);
        h.setFill(Color.DARKGREEN);
        v.setFill(Color.DARKGREEN);
        root.getChildren().addAll(h, v);
    }

    private void setUpStartWindow() throws FileNotFoundException {
        if (playlist.getMusicPlayer() != null) playlist.getMusicPlayer().stop();
        pane.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream("src/photo_2021-05-24_15-17-36_auto_x2.jpg")),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        pane.setPrefSize(1200, 680);
        Text name = new Text(50, 160, "Delivery maze");
        name.setFont(Font.font("Berlin Sans FB Demi", FontWeight.BOLD, 60));
        name.setFill(Color.WHITE);

        DropShadow is = new DropShadow();
        is.setOffsetY(3.0f);
        name.setEffect(is);
        name.setCache(true);

        controller.setButton(play, 150, 150, 150, 200);
        controller.setButton(instruction, 70, 70, 175, 360);
        controller.setButton(compliment, 70, 70, 175, 470);
        controller.setButton(music, 70, 70, 175, 570);
        if (musicStatus) {
            controller.setBackgroundForButton("src/music_on.png", music);
        } else {
            controller.setBackgroundForButton("src/music_off.png", music);
        }
        controller.setBackgroundForButton("src/cursor_right.png", play);
        controller.setBackgroundForButton("src/info_2.png", instruction);
        controller.setBackgroundForButton("src/unfavourited.png", compliment);
        playlist.background("src/Komiku_-_04_-_Shopping_List.mp3");
        if (musicStatus) playlist.setVolume(1);
        else playlist.setVolume(0);
        playlist.getMusicPlayer().play();
        if (!pane.getChildren().contains(play) && !pane.getChildren().contains(name) && !pane.getChildren().contains(compliment)
                && !pane.getChildren().contains(instruction) && !pane.getChildren().contains(music)) {
            pane.getChildren().addAll(play, name, compliment, instruction, music);
        }
    }

    private void failScene(Stage stage) throws FileNotFoundException {
        Pane pane = new Pane();
        playlist.getMusicPlayer().stop();
        controller.middleScene("src/pizza2.jpg", "src/mixkit-player-losing-or-failing-2042.wav", pane);
        Rectangle r = new Rectangle(1000, 360);
        r.setFill(Color.rgb(185, 185, 185));
        r.setLayoutX(120);
        r.setLayoutY(200);
        pane.getChildren().add(r);
        Label fail1 = new Label("You have failed this level");
        Label fail2 = new Label("There is no chance to get a job here");
        controller.setLabel(fail1, 275, 230);
        controller.setLabel(fail2, 150, 330);
        actionOnBack(stage);
        controller.setButton(back, 100, 100, 530, 450);
        controller.setBackgroundForButton("src/home.png", back);
        pane.getChildren().addAll(fail1, fail2, back);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
    }

    private void betweenLevelScene(Stage stage) throws FileNotFoundException {
        Pane pane = new Pane();
        playlist.getMusicPlayer().stop();
        controller.middleScene("src/pizza1.jpg", "src/mixkit-ethereal-fairy-win-sound-2019.wav", pane);
        Rectangle r = new Rectangle(930, 360);
        r.setFill(Color.rgb(69, 191, 202));
        r.setLayoutX(160);
        r.setLayoutY(200);
        pane.getChildren().add(r);
        Label win1 = new Label("You have finished " + levelVariable + " level");
        levelVariable++;
        Label win2 = new Label("Congratulations!");
        controller.setLabel(win1, 275, 240);
        controller.setLabel(win2, 375, 340);
        Button nextLevel = new Button(), sound = new Button();
        actionOnBack(stage);
        controller.setButton(back, 70, 70, 465, 465);
        controller.setButton(nextLevel, 100, 100, 565, 450);
        controller.setButton(sound, 70, 70, 665, 465);
        controller.setBackgroundForButton("src/home.png", back);
        controller.setBackgroundForButton("src/fast_forward.png", nextLevel);
        if (musicStatus)
            controller.setBackgroundForButton("src/music_on.png", sound);
        else controller.setBackgroundForButton("src/music_off.png", sound);

        sound.setOnAction(actionEvent -> changeMusicIcon(sound));
        nextLevel.setOnAction(actionEvent -> {
            if (levelVariable == 2) {
                try {
                    secondLevel(stage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (levelVariable == 3) {

            } else if (levelVariable == 4) {

            } else if (levelVariable == 5) {

            } else if (levelVariable == 6) {

            }
        });
        pane.getChildren().addAll(win1, win2, back, nextLevel, sound);
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

    public void update() {
        if (isPressed(KeyCode.A)) {
            if (velocity <= 10) velocity += 0.25;
        }
        if (isPressed(KeyCode.S)) {
            velocity = 2;
        }
        if (isPressed(KeyCode.D)) {
            if (velocity > 0.5) velocity -= 0.25;
        }
        if (isPressed(KeyCode.UP)) {
            player.animation.play();
            player.animation.setOffsetY(180);
            player.finalMoveY(-velocity);
        } else if (isPressed(KeyCode.DOWN)) {
            player.animation.play();
            player.animation.setOffsetY(0);
            player.finalMoveY(velocity);
        } else if (isPressed(KeyCode.RIGHT)) {
            player.animation.play();
            player.animation.setOffsetY(120);
            player.finalMoveX(velocity);
        } else if (isPressed(KeyCode.LEFT)) {
            player.animation.play();
            player.animation.setOffsetY(60);
            player.finalMoveX(-velocity);
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

    private void setUpCharacters(Pane mazePane) {
        controller.setLayout(burger, 500, 500);
        Collections.addAll(monsters, burger, chicken, sushi, chocolate);
        chicken.setVisible(false);
        controller.setLayout(chicken, 225, 80);
        controller.setLayout(sushi, 75, 400);
        controller.setLayout(chocolate, 500, 75);
        controller.setLayout(energyDrinkRed, 650, 75);
        controller.setLayout(energyDrinkBlue, 340, 470);
        controller.setLayout(pizza, 700, 600);
        pizzas.add(pizza);
        houseIm.setX(650);
        houseIm.setLayoutY(200);
        houseIm.setFitWidth(100);
        houseIm.setFitHeight(100);
        Collections.addAll(energyBonuses, energyDrinkRed, energyDrinkBlue);
        sushi.setVisible(false);
        root.getChildren().add(mazePane);
    }

    private void setUpCharacters2(Pane mazePane) {
        controller.setLayout(sushi, 500, 510);
        Collections.addAll(monsters, burger, chicken, sushi, chocolate, vanLeft);
        chicken.setVisible(false);
        controller.setLayout(chocolate, 300, 50);
        controller.setLayout(burger, 80, 410);
        controller.setLayout(chicken, 900, 200);
        controller.setLayout(energyDrinkRed, 50, 175);
        controller.setLayout(energyDrinkBlue, 700, 160);
        controller.setLayout(pizza, 710, 530);
        controller.setLayout(vanLeft, 50, 256);
        pizzas.add(pizza);
        houseIm2.setX(265);
        houseIm2.setLayoutY(100);
        houseIm2.setFitWidth(100);
        houseIm2.setFitHeight(100);
        Collections.addAll(energyBonuses, energyDrinkRed, energyDrinkBlue);
        sushi.setVisible(false);
        root.getChildren().add(mazePane);
    }

    private void setUpTimeLines(Pane mazePane) {
        DoubleProperty timeSeconds = new SimpleDoubleProperty(5.0);
        time = Duration.minutes(5.00);
        Label timerLabel = new Label();
        controller.setLabel(timerLabel, 1130, 445, 30, FontWeight.BOLD);
        timerLabel.textProperty().bind(timeSeconds.asString());
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
        mazePane.getChildren().add(timerLabel);
        enemyTimeLine = new Timeline(
                new KeyFrame(Duration.seconds(10),
                        actionEvent -> {
                            if (!finishLevel) {
                                if (burger != null) {
                                    burger.setVisible(!burger.isVisible());
                                }
                                if (chicken != null) {
                                    chicken.setVisible(!chicken.isVisible());
                                }
                            } else enemyTimeLine.stop();
                        }));
        enemy2Timeline = new Timeline(
                new KeyFrame(Duration.seconds(7),
                        actionEvent -> {
                            if (!finishLevel) {
                                if (chocolate != null) {
                                    chocolate.setVisible(!chocolate.isVisible());
                                } else enemy2Timeline.stop();
                            } else enemy2Timeline.stop();
                        }));
        enemy3Timeline = new Timeline(
                new KeyFrame(Duration.seconds(12),
                        actionEvent -> {
                            if (!finishLevel) {
                                if (sushi != null) {
                                    sushi.setVisible(!sushi.isVisible());
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

    private void setLabelsForSideMenu(Pane mazePane, Rectangle settings) {
        numOfOrder = new Label("O.N. " + (r.nextInt(9999999) + 1000000));
        controller.setLabel(numOfOrder, 1043, 140, 17, FontWeight.NORMAL);
        Label data = new Label(new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime()));
        controller.setLabel(data, 1060, 50, 13, FontWeight.BOLD);
        Label str = new Label("-------------------------------------------------");
        controller.setLabel(str, 1000, 160, 25, FontWeight.BOLD);
        Label energyLabel = new Label("   ..................");
        controller.setLabel(energyLabel, 1035, 195, 15, FontWeight.BOLD);
        Label pizzaLabel = new Label("   ..............");
        controller.setLabel(pizzaLabel, 1060, 260, 15, FontWeight.BOLD);
        energyLabel2 = new Label("x" + lives);
        controller.setLabel(energyLabel2, 1160, 192, 25, FontWeight.NORMAL);
        Rectangle check = new Rectangle(1160, 250, 25, 25);
        check.setFill(Color.TRANSPARENT);
        check.setStroke(Color.BLACK);
        if (levelVariable == 1) {
            imageEnergy.setFitWidth(40);
            imageEnergy.setFitHeight(50);
            imageEnergy.setLayoutX(1005);
            imageEnergy.setLayoutY(185);
        } else if (levelVariable == 2) {
            imageEnergy2.setFitWidth(40);
            imageEnergy2.setFitHeight(50);
            imageEnergy2.setLayoutX(1005);
            imageEnergy2.setLayoutY(185);
        }
        pizzaBoxIV1.setLayoutY(250);
        pizzaBoxIV1.setLayoutX(1005);
        pizzaBoxIV1.setFitWidth(65);
        Label str1 = new Label("-------------------------------------------------");
        controller.setLabel(str1, 1000, 500, 25, FontWeight.BOLD);
        Label str2 = new Label("-------------------------------------------------");
        controller.setLabel(str2, 1000, 400, 25, FontWeight.BOLD);
        Label timeLeft = new Label("Time left ");
        controller.setLabel(timeLeft, 1005, 450, 25, FontWeight.NORMAL);
        Label timeStr = new Label("      ...");
        controller.setLabel(timeStr, 1085, 455, 15, FontWeight.BOLD);
        Label level = new Label("Level " + Main.levelVariable);
        controller.setLabel(level, 1012, 80, 60, FontWeight.NORMAL);
        mazePane.getChildren().addAll(settings, level, data, numOfOrder, str, str1, str2, energyLabel, energyLabel2, timeLeft, timeStr, pizzaBoxIV1, pizzaLabel, check);
    }

    private static Slider setMusicSliders(Double getVolume, Music musicList) {
        Slider music = new Slider(0.0, 1.0, getVolume);
        music.setShowTickMarks(true);
        music.setMajorTickUnit(0.2);
        music.setShowTickLabels(true);
        if (musicList.musicPlayer != null)
            music.setValue(musicList.musicPlayer.getVolume());
        else music.setValue(1.0);
        return music;
    }

    private void changeMusicIcon(Button button) {
        musicStatus = !musicStatus;
        if (musicStatus) {
            try {
                controller.setBackgroundForButton("src/music_on.png", button);
            } catch (FileNotFoundException f) {
                f.printStackTrace();
            }
            playlist.getMusicPlayer().setVolume(1);
        } else {
            try {
                controller.setBackgroundForButton("src/music_off.png", button);
            } catch (FileNotFoundException f) {
                f.printStackTrace();
            }
            playlist.getMusicPlayer().setVolume(0);
        }
    }

    private void actionOnBack(Stage stage) throws FileNotFoundException {
        back = new Button();
        controller.setBackgroundForButton("src/home.png", back);
        back.setOnAction(actionEvent -> {
            lives = 3;
            try {
                setUpStartWindow();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            stage.setScene(scene);
        });
    }
    private void pauseWindow(Stage primaryStage, Color color) {
        timeline.stop();
        enemyTimeLine.stop();
        enemy2Timeline.stop();
        enemy3Timeline.stop();
        Pane pane = new Pane();
        timer.stop();
        Button homeButton = new Button(), playButton = new Button(), info = new Button();
        try {
            controller.setBackgroundForButton("src/cursor_right.png", playButton);
            controller.setBackgroundForButton("src/home.png", homeButton);
            controller.setBackgroundForButton("src/info_2.png", info);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        controller.setButton(playButton, 150, 150, 240, 90);
        controller.setButton(homeButton, 70, 70, 190, 127);
        controller.setButton(info, 70, 70, 370, 130);

        Slider musicSlider = setMusicSliders(playlist.getMusicPlayer().getVolume(), playlist);
        if (sideSounds.getMusicPlayer() != null)
            sound = setMusicSliders(sideSounds.getMusicPlayer().getVolume(), sideSounds);
        else sound = setMusicSliders(1.0, sideSounds);

        musicSlider.valueProperty().addListener(observable -> {
            playlist.musicPlayer.setVolume(musicSlider.getValue());
            musicStatus = musicSlider.getValue() != 0;
        });

        sound.valueProperty().addListener(observable -> sideSounds.musicPlayer.setVolume(sound.getValue()));
        musicSlider.setLayoutY(240);
        musicSlider.setLayoutX(235);
        sound.setLayoutY(290);
        sound.setLayoutX(235);

        try {
            controller.configureSlidersImage(pane);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Stage pauseStage = new Stage();
        pauseStage.setMaximized(false);
        pauseStage.setOnCloseRequest(Event::consume);

        homeButton.setOnAction(actionEvent -> {
            pauseStage.close();
            lives = 3;
            try {
                setUpStartWindow();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            primaryStage.setScene(scene);
        });

        playButton.setOnAction(actionEvent -> {
            pauseStage.close();
            timeline.play();
            enemyTimeLine.play();
            enemy2Timeline.play();
            enemy3Timeline.play();
            timer.start();
        });

        pane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setPrefSize(600, 350);
        Label text = new Label("PAUSE");
        controller.setLabel(text, 225, 10, 60, FontWeight.NORMAL);
        text.setTextFill(Color.WHITE);
        pane.getChildren().addAll(text, musicSlider, sound, info, homeButton, playButton);
        Scene scene = new Scene(pane);
        pauseStage.setScene(scene);
        pauseStage.setTitle("Pause");
        pauseStage.setResizable(false);
        pauseStage.show();
    }

}
