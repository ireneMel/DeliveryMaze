package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Main extends Application {

    public static boolean finishLevel = false;
    public static List<Character> energyBonuses = new ArrayList<>();
    public static List<Rectangle> wallsHorizontal = new ArrayList<>();
    static List<Rectangle> lines = new ArrayList<>();
    public static ArrayList<Character> monsters = new ArrayList<>();
    public static ArrayList<Rectangle> bonuses = new ArrayList<>();
    private final ArrayList<String> complimentArray = new ArrayList<>();
    private final HashMap<KeyCode, Boolean> keys = new HashMap<>();
    AnimationTimer timer;
    private Timeline timeline, enemyTimeLine, enemy2Timeline, enemy3Timeline, enemy4Timeline, energyTimeLine;
    private final DoubleProperty timeSeconds = new SimpleDoubleProperty(5.0);
    private Duration time = Duration.minutes(5.0);
    private final Label timerLabel = new Label();
    public static Music playlist = new Music(), sideSounds = new Music();
    FileInputStream inputStreamE = new FileInputStream("src/17_burger_napkin.png"),
            grass = new FileInputStream("src/unnamed.jpg"),
            inputStream = new FileInputStream("src/MyCollages__3_-removebg-preview (1).png"),
            chickenS = new FileInputStream("src/86_roastedchicken_dish.png"),
            sushiS = new FileInputStream("src/98_sushi_dish.png"),
            cakeS = new FileInputStream("src/31_chocolatecake_dish.png"),
            potatoS = new FileInputStream("src/45_frenchfries_dish.png"),
            energyDrink = new FileInputStream("src/soft_drink_red.png"),
            energyDrink1 = new FileInputStream("src/soft_drink_blue.png");
    Image image = new Image(inputStream), grassImage = new Image(grass), imageE = new Image(inputStreamE),
            chicken = new Image(chickenS), sushiI = new Image(sushiS), cakeI = new Image(cakeS), potatoI = new Image(potatoS),
            energyDrinkI = new Image(energyDrink), energyDrinkI1 = new Image(energyDrink1);
    public static FileInputStream houseS;

    static {
        try {
            houseS = new FileInputStream("src/photo_2021-05-25_12-02-30-removebg-preview.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Image house = new Image(houseS);
    public static ImageView houseIm = new ImageView(house);
    ImageView imageView = new ImageView(image), im = new ImageView(imageE), ch = new ImageView(chicken),
            sushi = new ImageView(sushiI), cake = new ImageView(cakeI), potato = new ImageView(potatoI),
            energy = new ImageView(energyDrinkI), energy1 = new ImageView(energyDrinkI1);
    Character player = new Character(imageView, 50, 60);
    Character enemy = new Character(im), enemy2 = new Character(ch), enemy3 = new Character(sushi), enemy4 = new Character(cake),
            enemy5 = new Character(potato), energydrink = new Character(energy), energydrink1 = new Character(energy1);

    Pane pane = new Pane();
    static Pane root = new Pane();
    Button play = new Button(), instruction = new Button(), compliment = new Button(), music = new Button();

    Random random = new Random();
    private int randomNum;
    public static int levelVariable = 1, lives = 3;
    boolean musicStatus = true;

    public Main() throws FileNotFoundException {
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
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
        stage.setScene(new Scene(pane));
        stage.show();

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
        pane.getChildren().addAll(play, name, compliment, instruction, music);
    }

    public void firstLevel(Stage primaryStage) throws FileNotFoundException {
        playlist.getMusicPlayer().stop();
        if (musicStatus) {
            playlist.background("src/Komiku_-_04_-_The_weekly_fair.mp3");
        }
        Label level = new Label("Level " + levelVariable);
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
        BackgroundImage BI = new BackgroundImage(grassImage,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        for (Rectangle a : wallsHorizontal) {
//            ImagePattern ip=new ImagePattern(grassImage);
//            a.setFill(new ImagePattern(grassImage));
            a.setFill(Color.rgb(132, 134, 241));
            a.setStroke(Color.WHITE);
        }
        for (Rectangle l : lines) {
            l.setFill(Color.rgb(132, 134, 241));
            l.setStroke(Color.rgb(132, 134, 241));
        }

        setLayout(player, 850, 600);

        root.getChildren().addAll(player, enemy, enemy2, enemy3, enemy4, energydrink, energydrink1, houseIm);
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!finishLevel) {
                    update();
                } else {
                    timer.stop();
                    playlist.getMusicPlayer().stop();
                    try {
                        betweenLevelScene(primaryStage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                //bonus();
            }
        };
        timer.start();
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);

    }

    private void betweenLevelScene(Stage stage) throws FileNotFoundException {
        playlist.playEffectSound("src/mixkit-ethereal-fairy-win-sound-2019.wav");
        FileInputStream str = new FileInputStream("src/manyPizzas_digital_art_x4.jpg");
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
        Button back = new Button(), nextLevel = new Button(), sound = new Button();
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
        Rectangle l0 = new Rectangle(960, 655, 40, 10);
        mazePane.getChildren().addAll(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, level, l0);
        Collections.addAll(wallsHorizontal, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25);
        Collections.addAll(lines, l0);
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
                                    JOptionPane.showMessageDialog(null, "Loser", "Error", JOptionPane.ERROR_MESSAGE);
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
    }

}
