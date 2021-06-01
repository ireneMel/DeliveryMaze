package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main extends Application {

    public static boolean fail = false, musicStatus = true, finishLevel = false;
    public static ArrayList<Character> energyBonuses, monsters, pizzas, players;
    public static List<Rectangle> walls;
    public static ArrayList<Rectangle> bonuses = new ArrayList<>();
    private final ArrayList<String> complimentArray = new ArrayList<>();
    private HashMap<KeyCode, Boolean> keys;
    AnimationTimer timer, vanTimer, vanTimer2, vanTimer3, deliveryEnemy1Timer, deliveryEnemy2Timer, deliveryEnemy3Timer;
    private Timeline timeline, chickenAndBurgerTimeLine, chocolateTineLine, sushiTimeline, enemy4Timeline,
            burritoTimeLine, bagelTimeline, cheesePuffTimeline, hotDogTimeLine, nachoTimeLine,
            pancakesTimeLine, sandwichTimeLine, tacoTimeLine, baconDishTimeLine;
    private Duration time;
    public static Label energyLabel2, numberPizza;
    public static Music playlist = new Music(), sideSounds = new Music();
    static Controller controller = new Controller();
    Label numOfOrder = new Label();
    Random r = new Random();
    public static Slider sound = setMusicSliders(1.0, sideSounds);
    Slider musicSlider;
    double x = 0.7, x2 = -1.5, x3 = 1.0, property = 1, property2 = -1, property3 = 1;
    boolean up, right, left, down, up2, right2, left2, down2, up3, right3, left3, down3;
    ImageView imageView = new ImageView(new Image(new FileInputStream("src/MyCollages__8_-removebg-preview (1) (3).png"))),
            imageViewEnemy1 = new ImageView(new Image(new FileInputStream("src/person_delivery (1).png"))),
            imageViewEnemy2 = new ImageView(new Image(new FileInputStream("src/person_delivery (1).png"))),
            imageViewEnemy3 = new ImageView(new Image(new FileInputStream("src/person_delivery (1).png"))),
            imageEnergy = new ImageView(new Image(new FileInputStream("src/Battery.png"))),
            imageEnergy2 = new ImageView(new Image(new FileInputStream("src/Battery3.png"))),
            imageEnergy3 = new ImageView(new Image(new FileInputStream("src/Battery4.png"))),
            imageEnergy4 = new ImageView(new Image(new FileInputStream("src/Battery5.png"))),
            imageEnergy5 = new ImageView(new Image(new FileInputStream("src/Battery6.png"))),
            pizzaBoxIV1 = new ImageView(new Image(new FileInputStream("src/New_Piskel__2_-removebg-preview2.png"))),
            enemy6I = new ImageView(new Image(new FileInputStream("src/van3.png"))),
            enemy6I4 = new ImageView(new Image(new FileInputStream("src/ice-cream-van_33.png"))),
            enemy6I2 = new ImageView(new Image(new FileInputStream("src/ice-cream-van_3.png"))),
            enemy6I3 = new ImageView(new Image(new FileInputStream("src/ice-cream-van_32.png")));
    static Character player, deliveryEnemy1, deliveryEnemy2, deliveryEnemy3;
    Character burger = new Character(new ImageView(new Image(new FileInputStream("src/17_burger_napkin.png")))),
            chicken = new Character(new ImageView(new Image(new FileInputStream("src/86_roastedchicken_dish.png")))),
            sushi = new Character(new ImageView(new Image(new FileInputStream("src/98_sushi_dish.png")))),
            chocolate = new Character(new ImageView(new Image(new FileInputStream("src/31_chocolatecake_dish.png")))),
            enemy5 = new Character(new ImageView(new Image(new FileInputStream("src/45_frenchfries_dish.png")))),
            applePie = new Character(new ImageView(new Image(new FileInputStream("src/06_apple_pie_dish.png")))),
            baconDish = new Character(new ImageView(new Image(new FileInputStream("src/14_bacon_dish.png")))),
            burrito = new Character(new ImageView(new Image(new FileInputStream("src/19_burrito_dish.png")))),
            bagel = new Character(new ImageView(new Image(new FileInputStream("src/21_bagel_dish.png")))),
            cheeseCake = new Character(new ImageView(new Image(new FileInputStream("src/23_cheesecake_dish.png")))),
            cheesePuff = new Character(new ImageView(new Image(new FileInputStream("src/25_cheesepuff_bowl.png")))),
            hotDog = new Character(new ImageView(new Image(new FileInputStream("src/56_hotdog_dish.png")))),
            nacho = new Character(new ImageView(new Image(new FileInputStream("src/72_nacho_dish.png")))),
            pudding = new Character(new ImageView(new Image(new FileInputStream("src/76_pudding_dish.png")))),
            pancakes = new Character(new ImageView(new Image(new FileInputStream("src/80_pancakes_dish.png")))),
            sandwich = new Character(new ImageView(new Image(new FileInputStream("src/93_sandwich_dish.png")))),
            taco = new Character(new ImageView(new Image(new FileInputStream("src/100_taco_dish.png")))),
            energyDrinkRed = new Character(new ImageView(new Image(new FileInputStream("src/soft_drink_red.png")))),
            energyDrinkBlue = new Character(new ImageView(new Image(new FileInputStream("src/soft_drink_blue.png")))),
            energyDrinkYellow = new Character(new ImageView(new Image(new FileInputStream("src/soft_drink_yellow.png")))),
            energyDrinkGreen = new Character(new ImageView(new Image(new FileInputStream("src/soft_drink_green.png")))),
            van = new Character(enemy6I, 1, 1),
            van2 = new Character(enemy6I2, 1, 1),
            van3 = new Character(enemy6I3, 1, 1),
            pizza1 = new Character(70, 30, new ImageView(new Image(new FileInputStream("src/PizzaIm3.png")))),
            pizza = new Character(70, 30, new ImageView(new Image(new FileInputStream("src/New_Piskel__2_-removebg-preview.png"))));

    Pane pane = new Pane();
    static Pane root;
    Button play = new Button(), instruction = new Button(), compliment = new Button(), music = new Button(), back;
    public static ImageView houseIm, houseIm2, houseIm3, houseIm4, houseIm5;
    DoubleProperty timeSeconds;
    private double velocity = 2;

    static {
        try {
            houseIm = new ImageView(new Image(new FileInputStream("src/photo_2021-05-25_12-02-30-removebg-preview.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    Scene scene;
    public static int levelVariable = 1, lives = 3, countPizzas=0;

    public Main() throws FileNotFoundException {
    }

    private void initialize() {
        monsters = new ArrayList<>();
        walls = new ArrayList<>();
        energyBonuses = new ArrayList<>();
        pizzas = new ArrayList<>();
        players = new ArrayList<>();
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        sideSounds.background("src/silent.wav");
        sideSounds.setVolume(0.2);

        setUpStartWindow();
        play.setOnAction(actionEvent -> {
            try {
                levelVariable=6;
                countPizzas=5;
                finalStage(stage);
                //firstLevel(stage);
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
        sideSounds.setVolume(0.2);
        fail = false;
        finishLevel = false;
        root = new Pane();
        initialize();
        player = new Character(imageView, 41, 54);
        Rectangle settings;
        if (levelVariable != 5) {
            settings = new Rectangle(300, 700);
            settings.setLayoutX(1000);
        } else {
            settings = new Rectangle(300, 900);
            settings.setLayoutX(1200);
        }
        settings.setFill(color);
        keys = new HashMap<>();
        numOfOrder.setText("O.N. " + (r.nextInt(9999999) + 1000000));
        Rectangle rec;
        if (levelVariable != 5) rec = new Rectangle(1050, 550, 100, 100);
        else rec = new Rectangle(1250, 550, 100, 100);
        rec.setFill(new ImagePattern(new Image(new FileInputStream("src/pause.png"))));
        rec.setOnMouseClicked(mouseEvent -> pauseWindow(primaryStage, color));
        if (levelVariable == 5) root.setPrefSize(1400, 900);
        else root.setPrefSize(1200, 700);
        if (levelVariable == 5) mazePane.setPrefSize(1200, 900);
        else mazePane.setPrefSize(1000, 700);
        setLabelsForSideMenu(mazePane, settings);
        mazePane.getChildren().add(rec);
        mazePane.setBackground(new Background(new BackgroundFill(Color.rgb(113, 217, 140), CornerRadii.EMPTY, Insets.EMPTY)));
        primaryStage.setTitle("Delivery Maze");
        playlist.background(path);
        playlist.getMusicPlayer().play();
        if(musicSlider!=null) playlist.setVolume(musicSlider.getValue());
        else if (!musicStatus) playlist.setVolume(0);
        else playlist.setVolume(0.2);
    }

    private void firstLevel(Stage primaryStage) throws FileNotFoundException {
        velocity = 2;
        houseIm = new ImageView(new Image(new FileInputStream("src/photo_2021-05-25_12-02-30-removebg-preview.png")));
        Pane mazePane = new Pane();
        levelVariable = 1;
        settingsForLevels(primaryStage, mazePane, Color.rgb(230, 222, 202), "src/Farming-By-Moonlight.mp3");
        setUpTimeLines(mazePane);
        setUpCharacters(mazePane);
        controller.setLayout(player, 118, 170);
        settingsForLevels2(primaryStage);
        controller.addRectanglesFirstLevel(mazePane);
        root.getChildren().addAll(player, bagel, chicken, pancakes, chocolate, energyDrinkRed, energyDrinkYellow, houseIm, imageEnergy, pizza);
    }

    private void secondLevel(Stage primaryStage) throws FileNotFoundException {
        houseIm2 = new ImageView(new Image(new FileInputStream("src/IMG_20210527_181324.png")));
        Pane mazePane = new Pane();
        van = new Character(enemy6I, 53, 63);
        van2 = new Character(enemy6I2, 53, 63);
        houseIm = houseIm2;
        settingsForLevels(primaryStage, mazePane, Color.rgb(245, 210, 227), "src/Wavecont-Inspire-2-Full-Lenght.mp3");
        setUpTimeLines(mazePane);
        setUpCharacters2(mazePane);
        controller.setLayout(player, 915, 40);
        settingsForLevels2(primaryStage);
        x = 0.4;
        property2 = -1;
        property = 1;
        //  startVanTimers();
        vanTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (van.isWall()) {
                    x = -1 * x;
                    if (property > 0) property = -1;
                    else property = 1;
                    van.setScaleX(property);
                }
                van.animation.play();
                van.animation.setOffsetY(0);
                van.moveEnemyX(x);
            }
        };
        vanTimer.start();

        van2.setScaleX(property2);
        vanTimer2 = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (van2.isWall()) {
                    x2 = -1 * x2;
                    if (property2 > 0) property2 = -1;
                    else property2 = 1;
                    van2.setScaleX(property2);
                }
                van2.animation.play();
                van2.animation.setOffsetY(0);
                van2.moveEnemyX(x2);
            }
        };
        vanTimer2.start();
        controller.addRectanglesSecondLevel(mazePane);
        root.getChildren().addAll(player, burger, burrito, baconDish, sushi, cheeseCake, energyDrinkRed, energyDrinkBlue, houseIm2, imageEnergy2, pizza, van, van2);
    }

    private void thirdLevel(Stage primaryStage) throws FileNotFoundException {
        houseIm3 = new ImageView(new Image(new FileInputStream("src/IMG_20210529_160339.png")));
        Pane mazePane = new Pane();
        van = new Character(enemy6I, 53, 63);
        van2 = new Character(enemy6I2, 53, 60);
        van3 = new Character(enemy6I3, 53, 60);
        houseIm = houseIm3;
        settingsForLevels(primaryStage, mazePane, Color.rgb(118, 176, 186), "src/Wavecont-Motivate-Full-Length.mp3");
        setUpTimeLines(mazePane);
        setUpCharacters3(mazePane);
        controller.setLayout(player, 915, 40);
        settingsForLevels2(primaryStage);


        vanTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (van.isWall()) {
                    x = -1 * x;
                    if (property > 0) property = -1;
                    else property = 1;
                    van.setScaleX(property);
                }
                van.animation.play();
                van.animation.setOffsetY(0);
                van.moveEnemyX(x);
            }
        };
        vanTimer.start();
        van2.setScaleX(property2);
        vanTimer2 = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (van2.isWall()) {
                    x2 = -1 * x2;
                    if (property2 > 0) property2 = -1;
                    else property2 = 1;
                    van2.setScaleX(property2);
                }
                van2.animation.play();
                van2.animation.setOffsetY(0);
                van2.moveEnemyX(x2);
            }
        };
        vanTimer2.start();


        van3.setScaleX(property3);
        vanTimer3 = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (van3.isWall()) {
                    x3 = -1 * x3;
                    if (property3 > 0) property3 = -1;
                    else property3 = 1;
                    van3.setScaleX(property3);
                }
                van3.animation.play();
                van3.animation.setOffsetY(0);
                van3.moveEnemyX(x3);
            }
        };
        vanTimer3.start();
        controller.addRectanglesThirdLevel(mazePane);
        root.getChildren().addAll(player, energyDrinkGreen, energyDrinkYellow, energyDrinkRed, houseIm3, imageEnergy3, sandwich, pizza, cheeseCake, taco, applePie, enemy5, van, van2, van3);
    }

    private void fourthLevel(Stage primaryStage) throws FileNotFoundException {
        houseIm4 = new ImageView(new Image(new FileInputStream("src/house.png")));
        deliveryEnemy1 = new Character(imageViewEnemy1, 41, 54);
        deliveryEnemy2 = new Character(imageViewEnemy2, 41, 54);
        deliveryEnemy3 = new Character(imageViewEnemy3, 41, 54);
        Pane mazePane = new Pane();
        houseIm = houseIm4;
        settingsForLevels(primaryStage, mazePane, Color.rgb(196, 232, 184), "src/keys-of-moon-the-success.mp3");
        setUpTimeLines(mazePane);
        setUpCharacters4(mazePane);
        controller.setLayout(player, 674, 278);
        settingsForLevels2(primaryStage);
        up = false;
        down = true;
        left = false;
        right = false;
        deliveryEnemy1Timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (up) {
                    if (deliveryEnemy1.isExactWall(walls.get(0))) {
                        up = false;
                        down = true;
                    } else {
                        deliveryEnemy1.animation.play();
                        deliveryEnemy1.animation.setOffsetY(162);
                        deliveryEnemy1.moveEnemyY(-1.5);
                    }
                } else if (down) {
                    if (deliveryEnemy1.isExactWall(walls.get(49))) {
                        down = false;
                        left = true;
                    } else {
                        deliveryEnemy1.animation.play();
                        deliveryEnemy1.animation.setOffsetY(0);
                        deliveryEnemy1.moveEnemyY(1.5);
                    }
                } else if (right) {
                    if (deliveryEnemy1.isExactWall(walls.get(14))) {
                        right = false;
                        up = true;
                    } else {
                        deliveryEnemy1.animation.play();
                        deliveryEnemy1.animation.setOffsetY(108);
                        deliveryEnemy1.moveEnemyX(1.5);
                    }
                } else if (left) {
                    if (deliveryEnemy1.isExactWall(walls.get(2))) {
                        left = false;
                        right = true;
                    } else {
                        deliveryEnemy1.animation.play();
                        deliveryEnemy1.animation.setOffsetY(54);
                        deliveryEnemy1.moveEnemyX(-1.5);
                    }
                } else deliveryEnemy1.animation.stop();

            }
        };
        deliveryEnemy1Timer.start();
        up2 = false;
        down2 = false;
        left2 = false;
        right2 = true;
        deliveryEnemy2Timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (up2) {
                    if (deliveryEnemy2.isExactWall(walls.get(0))) {
                        up2 = false;
                        down2 = true;
                    } else {
                        deliveryEnemy2.animation.play();
                        deliveryEnemy2.animation.setOffsetY(162);
                        deliveryEnemy2.moveEnemyY(-2);
                    }
                } else if (down2) {
                    if (deliveryEnemy2.isExactWall(walls.get(5))) {
                        down2 = false;
                        left2 = true;
                    } else {
                        deliveryEnemy2.animation.play();
                        deliveryEnemy2.animation.setOffsetY(0);
                        deliveryEnemy2.moveEnemyY(2);
                    }
                } else if (right2) {
                    if (deliveryEnemy2.isExactWall(walls.get(4))) {
                        right2 = false;
                        up2 = true;
                    } else {
                        deliveryEnemy2.animation.play();
                        deliveryEnemy2.animation.setOffsetY(108);
                        deliveryEnemy2.moveEnemyX(2);
                    }
                } else if (left2) {
                    if (deliveryEnemy2.isExactWall(walls.get(24))) {
                        left2 = false;
                        right2 = true;
                    } else {
                        deliveryEnemy2.animation.play();
                        deliveryEnemy2.animation.setOffsetY(54);
                        deliveryEnemy2.moveEnemyX(-2);
                    }
                } else deliveryEnemy2.animation.stop();

            }
        };
        deliveryEnemy2Timer.start();
        up3 = false;
        down3 = false;
        left3 = true;
        right3 = false;
        deliveryEnemy3Timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (up3) {
                    if (deliveryEnemy3.isExactWall(walls.get(13))) {
                        up3 = false;
                        down3 = true;
                    } else {
                        deliveryEnemy3.animation.play();
                        deliveryEnemy3.animation.setOffsetY(162);
                        deliveryEnemy3.moveEnemyY(-1.2);
                    }
                } else if (down3) {
                    if (deliveryEnemy3.isExactWall(walls.get(38))) {
                        down3 = false;
                        right3 = true;
                    } else {
                        deliveryEnemy3.animation.play();
                        deliveryEnemy3.animation.setOffsetY(0);
                        deliveryEnemy3.moveEnemyY(1.2);
                    }
                } else if (right3) {
                    if (deliveryEnemy3.isExactWall(walls.get(9))) {
                        right3 = false;
                        left3 = true;
                    } else {
                        deliveryEnemy3.animation.play();
                        deliveryEnemy3.animation.setOffsetY(108);
                        deliveryEnemy3.moveEnemyX(1.2);
                    }
                } else if (left3) {
                    if (deliveryEnemy3.isExactWall(walls.get(33))) {
                        left3 = false;
                        up3 = true;
                    } else {
                        deliveryEnemy3.animation.play();
                        deliveryEnemy3.animation.setOffsetY(54);
                        deliveryEnemy3.moveEnemyX(-1.2);
                    }
                } else deliveryEnemy3.animation.stop();

            }
        };
        deliveryEnemy3Timer.start();
        controller.addRectanglesFourthLevel(mazePane);
        root.getChildren().addAll(player, energyDrinkGreen, nacho, pudding, hotDog, energyDrinkYellow, houseIm4, imageEnergy4, sandwich, pizza, pizza1, taco, applePie, enemy5, deliveryEnemy1, deliveryEnemy2, deliveryEnemy3);
    }

    private void fifthLevel(Stage primaryStage) throws FileNotFoundException {
        houseIm5 = new ImageView(new Image(new FileInputStream("src/house_blue.png")));
        Pane mazePane = new Pane();
        deliveryEnemy1 = new Character(imageViewEnemy1, 41, 54);
        deliveryEnemy2 = new Character(imageViewEnemy2, 41, 54);
        deliveryEnemy3 = new Character(imageViewEnemy3, 41, 54);
        van = new Character(enemy6I4, 53, 60);
        van2 = new Character(enemy6I2, 53, 60);
        van3 = new Character(enemy6I3, 53, 60);
        houseIm = houseIm5;
        settingsForLevels(primaryStage, mazePane, Color.rgb(147, 205, 221), "src/keys-of-moon-cheer-up.mp3");
        setUpTimeLines(mazePane);
        setUpCharacters5(mazePane);
        controller.setLayout(player, 1127, 29);
        settingsForLevels2(primaryStage);
        levelVariable++;
        up = false;
        down = true;
        left = false;
        right = false;
        deliveryEnemy1Timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (up) {
                    if (deliveryEnemy1.isExactWall(walls.get(27))) {
                        up = false;
                        down = true;
                    } else {
                        deliveryEnemy1.animation.play();
                        deliveryEnemy1.animation.setOffsetY(162);
                        deliveryEnemy1.moveEnemyY(-1.5);
                    }
                } else if (down) {
                    if (deliveryEnemy1.isExactWall(walls.get(38))) {
                        down = false;
                        right = true;
                    } else {
                        deliveryEnemy1.animation.play();
                        deliveryEnemy1.animation.setOffsetY(0);
                        deliveryEnemy1.moveEnemyY(1.5);
                    }
                } else if (right) {
                    if (deliveryEnemy1.isExactWall(walls.get(48))) {
                        right = false;
                        left = true;
                    } else {
                        deliveryEnemy1.animation.play();
                        deliveryEnemy1.animation.setOffsetY(108);
                        deliveryEnemy1.moveEnemyX(1.5);
                    }
                } else if (left) {
                    if (deliveryEnemy1.isExactWall(walls.get(37))) {
                        left = false;
                        up = true;
                    } else {
                        deliveryEnemy1.animation.play();
                        deliveryEnemy1.animation.setOffsetY(54);
                        deliveryEnemy1.moveEnemyX(-1.5);
                    }
                } else deliveryEnemy1.animation.stop();

            }
        };
        deliveryEnemy1Timer.start();
        up2 = false;
        down2 = false;
        left2 = false;
        right2 = true;
        deliveryEnemy2Timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (up2) {
                    if (deliveryEnemy2.isExactWall(walls.get(63))) {
                        up2 = false;
                        right2 = true;
                    }else if(deliveryEnemy2.isExactWall(walls.get(0))) {
                        up2 = false;
                        down2 = true;
                    }
                    else {
                        deliveryEnemy2.animation.play();
                        deliveryEnemy2.animation.setOffsetY(162);
                        deliveryEnemy2.moveEnemyY(-2);
                    }
                } else if (down2) {
                    if (deliveryEnemy2.isExactWall(walls.get(72))||deliveryEnemy2.isExactWall(walls.get(73))) {
                        down2 = false;
                        left2 = true;
                    } else {
                        deliveryEnemy2.animation.play();
                        deliveryEnemy2.animation.setOffsetY(0);
                        deliveryEnemy2.moveEnemyY(2);
                    }
                } else if (right2) {
                    if (deliveryEnemy2.isExactWall(walls.get(71))||deliveryEnemy2.isExactWall(walls.get(92))){
                        right2 = false;
                        up2 = true;
                    }
                    else {
                        deliveryEnemy2.animation.play();
                        deliveryEnemy2.animation.setOffsetY(108);
                        deliveryEnemy2.moveEnemyX(2);
                    }
                } else if (left2) {
                    if (deliveryEnemy2.isExactWall(walls.get(68))) {
                        left2 = false;
                        down2 = true;
                    }
                    else if (deliveryEnemy2.isExactWall(walls.get(65))) {
                        left2 = false;
                        right2 = true;
                    } else {
                        deliveryEnemy2.animation.play();
                        deliveryEnemy2.animation.setOffsetY(54);
                        deliveryEnemy2.moveEnemyX(-2);
                    }
                } else deliveryEnemy2.animation.stop();

            }
        };
        deliveryEnemy2Timer.start();

        up3 = false;
        down3 = false;
        left3 = true;
        right3 = false;
        deliveryEnemy3Timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (up3) {
                    if (deliveryEnemy3.isExactWall(walls.get(70))) {
                        up3 = false;
                        right3 = true;
                    } else {
                        deliveryEnemy3.animation.play();
                        deliveryEnemy3.animation.setOffsetY(162);
                        deliveryEnemy3.moveEnemyY(-1.2);
                    }
                } else if (down3) {
                    if (deliveryEnemy3.isExactWall(walls.get(2))) {
                        down3 = false;
                        left3 = true;
                    } else {
                        deliveryEnemy3.animation.play();
                        deliveryEnemy3.animation.setOffsetY(0);
                        deliveryEnemy3.moveEnemyY(1.2);
                    }
                } else if (right3) {
                    if (deliveryEnemy3.isExactWall(walls.get(76))) {
                        right3 = false;
                        up3 = true;
                    }
                    else if (deliveryEnemy3.isExactWall(walls.get(80))) {
                        right3 = false;
                        left3 = true;
                    }
                    else {
                        deliveryEnemy3.animation.play();
                        deliveryEnemy3.animation.setOffsetY(108);
                        deliveryEnemy3.moveEnemyX(1.2);
                    }
                } else if (left3) {
                    if (deliveryEnemy3.isExactWall(walls.get(69))) {
                        left3 = false;
                        down3 = true;
                    }
                    else if (deliveryEnemy3.isExactWall(walls.get(47))) {
                        left3 = false;
                        right3 = true;
                    }
                    else {
                        deliveryEnemy3.animation.play();
                        deliveryEnemy3.animation.setOffsetY(54);
                        deliveryEnemy3.moveEnemyX(-1.2);
                    }
                } else deliveryEnemy3.animation.stop();

            }
        };
        deliveryEnemy3Timer.start();


        vanTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (van.isWall()) {
                    x = -1 * x;
                    if (property > 0) property = -1;
                    else property = 1;
                    van.setScaleX(property);
                }
                van.animation.play();
                van.animation.setOffsetY(0);
                van.moveEnemyX(x);
            }
        };
        vanTimer.start();
        van2.setScaleX(property2);
        vanTimer2 = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (van2.isWall()) {
                    x2 = -1 * x2;
                    if (property2 > 0) property2 = -1;
                    else property2 = 1;
                    van2.setScaleX(property2);
                }
                van2.animation.play();
                van2.animation.setOffsetY(0);
                van2.moveEnemyX(x2);
            }
        };
        vanTimer2.start();


        van3.setScaleX(property3);
        vanTimer3 = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (van3.isWall()) {
                    x3 = -1 * x3;
                    if (property3 > 0) property3 = -1;
                    else property3 = 1;
                    van3.setScaleX(property3);
                }
                van3.animation.play();
                van3.animation.setOffsetY(0);
                van3.moveEnemyX(x3);
            }
        };
        vanTimer3.start();
        controller.addRectanglesFifthLevel(mazePane);
        root.getChildren().addAll(player, energyDrinkRed, pizza1, baconDish, cheesePuff, chocolate, energyDrinkYellow, imageEnergy5, houseIm5, burger, pizza, cheeseCake, van, van2, van3,deliveryEnemy1, deliveryEnemy2, deliveryEnemy3);
        primaryStage.setHeight(935);
        primaryStage.setWidth(1420);
    }

    private void settingsForLevels2(Stage primaryStage) {
        players.add(player);
        Scene sceneFirstLevel = new Scene(root);
        sceneFirstLevel.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        sceneFirstLevel.setOnKeyReleased(event -> keys.put(event.getCode(), false));
        generateTimer(primaryStage);
        timer.start();
        primaryStage.setScene(sceneFirstLevel);
    }

    private void setUpCharacters(Pane mazePane) {
        controller.setLayout(burger, 500, 500);
        Collections.addAll(monsters, pancakes, chicken, bagel, chocolate);
        chicken.setVisible(false);
        controller.setLayout(chicken, 500, 564);
        controller.setLayout(pancakes, 157, 414);
        controller.setLayout(chocolate, 533, 181);
        controller.setLayout(bagel, 830, 201);
        controller.setLayout(energyDrinkRed, 345, 295);
        controller.setLayout(energyDrinkYellow, 820, 520);
        controller.setLayout(pizza, 166, 630);
        pizzas.add(pizza);
        houseIm.setX(650);
        houseIm.setLayoutY(250);
        houseIm.setFitWidth(100);
        houseIm.setFitHeight(100);
        Collections.addAll(energyBonuses, energyDrinkRed, energyDrinkYellow);
        root.getChildren().add(mazePane);
    }

    private void setUpCharacters2(Pane mazePane) {
        controller.setLayout(sushi, 720, 340);
        Collections.addAll(monsters, burger, burrito, sushi, cheeseCake, baconDish);
        chicken.setVisible(false);
        controller.setLayout(baconDish, 40, 590);
        controller.setLayout(cheeseCake, 340, 150);
        controller.setLayout(burger, 920, 630);
        controller.setLayout(burrito, 420, 630);
        controller.setLayout(energyDrinkRed, 814, 260);
        controller.setLayout(energyDrinkBlue, 40, 43);
        controller.setLayout(pizza, 120, 330);
        controller.setLayout(van, 410, 414);
        controller.setLayout(van2, 110, 130);
        pizzas.add(pizza);
        van.al.add(player);
        van2.al.add(player);
        houseIm2.setX(836);
        houseIm2.setLayoutY(425);
        houseIm2.setFitWidth(100);
        houseIm2.setFitHeight(100);
        Collections.addAll(energyBonuses, energyDrinkRed, energyDrinkBlue);
        sushi.setVisible(false);
        root.getChildren().add(mazePane);
    }

    private void setUpCharacters3(Pane mazePane) {
        controller.setLayout(taco, 362, 125);
        Collections.addAll(monsters, cheeseCake, taco, applePie, enemy5, sandwich);
        taco.setVisible(false);
        cheeseCake.setVisible(false);
        enemy5.setVisible(false);
        controller.setLayout(applePie, 395, 350);
        controller.setLayout(cheeseCake, 580, 530);
        controller.setLayout(enemy5, 780, 530);
        controller.setLayout(energyDrinkYellow, 873, 363);
        controller.setLayout(energyDrinkGreen, 322, 277);
        controller.setLayout(energyDrinkRed, 684, 378);
        controller.setLayout(pizza, 90, 205);
        controller.setLayout(van, 170, 519);
        controller.setLayout(van2, 308, 353);
        controller.setLayout(van3, 30, 104);
        van.al.add(player);
        van2.al.add(player);
        van3.al.add(player);
        controller.setLayout(sandwich, 655, 125);
        pizzas.add(pizza);
        houseIm3.setX(860);
        houseIm3.setLayoutY(583);
        houseIm3.setFitWidth(100);
        houseIm3.setFitHeight(100);
        Collections.addAll(energyBonuses, energyDrinkGreen, energyDrinkYellow, energyDrinkRed);
        sushi.setVisible(false);
        root.getChildren().add(mazePane);
    }

    private void setUpCharacters4(Pane mazePane) {
        controller.setLayout(taco, 423, 360);
        Collections.addAll(monsters, pudding, nacho, taco, applePie, enemy5, sandwich, hotDog);
        enemy5.setVisible(false);
        hotDog.setVisible(false);
        controller.setLayout(applePie, 390, 637);
        controller.setLayout(pudding, 915, 125);
        controller.setLayout(nacho, 30, 540);
        controller.setLayout(hotDog, 607, 372);
        controller.setLayout(enemy5, 750, 637);
        controller.setLayout(energyDrinkYellow, 833, 33);
        controller.setLayout(energyDrinkGreen, 30, 637);
        controller.setLayout(pizza, 900, 637);
        controller.setLayout(pizza1, 170, 40);
        controller.setLayout(sandwich, 475, 125);
        pizzas.add(pizza);
        pizzas.add(pizza1);
        houseIm4.setX(30);
        houseIm4.setLayoutY(30);
        houseIm4.setFitWidth(100);
        houseIm4.setFitHeight(100);
        controller.setLayout(deliveryEnemy1, 235, 35);
        controller.setLayout(deliveryEnemy2, 235, 536);
        controller.setLayout(deliveryEnemy3, 925, 450);
        deliveryEnemy1.al.add(player);
        deliveryEnemy2.al.add(player);
        deliveryEnemy3.al.add(player);
        Collections.addAll(energyBonuses, energyDrinkGreen, energyDrinkYellow);
        sushi.setVisible(false);
        root.getChildren().add(mazePane);
    }

    private void setUpCharacters5(Pane mazePane) {
        Collections.addAll(monsters, cheeseCake, baconDish, cheesePuff, chocolate, burger);
        cheeseCake.setVisible(false);
        chocolate.setVisible(false);
        controller.setLayout(baconDish, 67, 265);
        controller.setLayout(cheeseCake, 41, 659);
        controller.setLayout(cheesePuff, 631, 637);
        controller.setLayout(chocolate, 1112, 596);
        controller.setLayout(burger, 558, 285);
        controller.setLayout(energyDrinkYellow, 803, 306);
        controller.setLayout(energyDrinkRed, 120, 575);
        controller.setLayout(pizza, 512, 47);
        controller.setLayout(pizza1, 313, 834);
        controller.setLayout(van, 30, 819);
        controller.setLayout(van2, 1118, 375);
        controller.setLayout(van3, 422, 22);
        van.al.add(player);
        van2.al.add(player);
        van3.al.add(player);
        pizzas.add(pizza);
        pizzas.add(pizza1);
        houseIm5.setX(242);
        houseIm5.setLayoutY(263);
        houseIm5.setFitWidth(100);
        houseIm5.setFitHeight(100);
        controller.setLayout(deliveryEnemy1, 375, 254);
        controller.setLayout(deliveryEnemy2, 726, 212);
        controller.setLayout(deliveryEnemy3, 1010, 703);
        deliveryEnemy1.al.add(player);
        deliveryEnemy2.al.add(player);
        deliveryEnemy3.al.add(player);
        Collections.addAll(energyBonuses, energyDrinkRed, energyDrinkYellow);
        root.getChildren().add(mazePane);
    }

    private void setUpTimeLines(Pane mazePane) {
        stopTimeLines();
        if (levelVariable == 1) timeSeconds = new SimpleDoubleProperty(2.50);
        else if (levelVariable == 2) timeSeconds = new SimpleDoubleProperty(3.00);
        else if (levelVariable == 3) timeSeconds = new SimpleDoubleProperty(4.00);
        else timeSeconds = new SimpleDoubleProperty(5.00);
        if (levelVariable == 1) time = Duration.minutes(2.50);
        else if (levelVariable == 2) time = Duration.minutes(3.00);
        else if (levelVariable == 3) time = Duration.minutes(4.00);
        else time = Duration.minutes(5.00);
        Label timerLabel = new Label();
        if (levelVariable != 5) controller.setLabel(timerLabel, 1130, 445, 30, FontWeight.BOLD);
        else controller.setLabel(timerLabel, 1330, 445, 30, FontWeight.BOLD);
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
        chickenAndBurgerTimeLine = new Timeline(
                new KeyFrame(Duration.seconds(10),
                        actionEvent -> {
                            if (!finishLevel) {
                                if (burger != null) burger.setVisible(!burger.isVisible());
                                if (chicken != null) chicken.setVisible(!chicken.isVisible());
                                if (applePie != null) applePie.setVisible(!applePie.isVisible());
                                if (enemy5 != null) enemy5.setVisible(!enemy5.isVisible());
                                if (pudding != null) pudding.setVisible(!pudding.isVisible());
                            } else chickenAndBurgerTimeLine.stop();
                        }));
        baconDishTimeLine = controller.setOneTimeLine(baconDish, 9, baconDishTimeLine);
        cheesePuffTimeline = controller.setOneTimeLine(cheesePuff, 8, cheesePuffTimeline);
        nachoTimeLine = controller.setOneTimeLine(nacho, 8, nachoTimeLine);
        hotDogTimeLine = controller.setOneTimeLine(hotDog, 6, hotDogTimeLine);
        chocolateTineLine = controller.setOneTimeLine(chocolate, 7, chocolateTineLine);
        enemy4Timeline = controller.setOneTimeLine(cheeseCake, 7, enemy4Timeline);
        sushiTimeline = controller.setOneTimeLine(sushi, 12, sushiTimeline);
        tacoTimeLine = controller.setOneTimeLine(taco, 13, tacoTimeLine);
        sandwichTimeLine = controller.setOneTimeLine(sandwich, 5, sandwichTimeLine);
        burritoTimeLine = controller.setOneTimeLine(burrito, 4, burritoTimeLine);
        pancakesTimeLine = controller.setOneTimeLine(pancakes, 9, pancakesTimeLine);
        bagelTimeline = controller.setOneTimeLine(bagel, 6, bagelTimeline);
        baconDishTimeLine.setCycleCount(Timeline.INDEFINITE);
        burritoTimeLine.setCycleCount(Timeline.INDEFINITE);
        nachoTimeLine.setCycleCount(Timeline.INDEFINITE);
        hotDogTimeLine.setCycleCount(Timeline.INDEFINITE);
        enemy4Timeline.setCycleCount(Timeline.INDEFINITE);
        bagelTimeline.setCycleCount(Timeline.INDEFINITE);
        pancakesTimeLine.setCycleCount(Timeline.INDEFINITE);
        chickenAndBurgerTimeLine.setCycleCount(Timeline.INDEFINITE);
        chocolateTineLine.setCycleCount(Timeline.INDEFINITE);
        sandwichTimeLine.setCycleCount(Timeline.INDEFINITE);
        tacoTimeLine.setCycleCount(Timeline.INDEFINITE);
        sushiTimeline.setCycleCount(Timeline.INDEFINITE);
        cheesePuffTimeline.setCycleCount(Timeline.INDEFINITE);
        startTimeLines();
    }

    private void setLabelsForSideMenu(Pane mazePane, Rectangle settings) {
        if (levelVariable != 5) {
            controller.setLabel(numOfOrder, 1043, 140, 17, FontWeight.NORMAL);
            Label data = new Label(new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime()));
            controller.setLabel(data, 1063, 50, 13, FontWeight.BOLD);
            Label str = new Label("-------------------------------------------------");
            controller.setLabel(str, 1000, 160, 25, FontWeight.BOLD);
            Label energyLabel = new Label("   ..................");
            controller.setLabel(energyLabel, 1035, 195, 15, FontWeight.BOLD);
            Label pizzaLabel = new Label("   ..............");
            controller.setLabel(pizzaLabel, 1060, 260, 15, FontWeight.BOLD);
            energyLabel2 = new Label("x" + lives);
            controller.setLabel(energyLabel2, 1160, 192, 25, FontWeight.NORMAL);
            Rectangle check = new Rectangle(1160, 250, 25, 25);
            Rectangle check2;
            check.setFill(Color.TRANSPARENT);
            check.setStroke(Color.BLACK);
            if(levelVariable==4){
                check2 = new Rectangle(1130, 250, 25, 25);
                check2.setFill(Color.TRANSPARENT);check2.setStroke(Color.BLACK);
                mazePane.getChildren().add(check2);
                pizzaLabel.setText("   .........");
            }
            pizzaBoxIV1.setLayoutY(250);
            pizzaBoxIV1.setLayoutX(1005);
            pizzaBoxIV1.setFitWidth(65);
            numberPizza = new Label("Total pizza count: " + countPizzas + "/7");
            controller.setLabel(numberPizza, 1014, 370, 17, FontWeight.NORMAL);
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
        } else {
            controller.setLabel(numOfOrder, 1243, 140, 17, FontWeight.NORMAL);
            Label data = new Label(new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime()));
            controller.setLabel(data, 1263, 50, 13, FontWeight.BOLD);
            Label str = new Label("-------------------------------------------------");
            controller.setLabel(str, 1200, 160, 25, FontWeight.BOLD);
            Label energyLabel = new Label("   ..................");
            controller.setLabel(energyLabel, 1235, 195, 15, FontWeight.BOLD);
            Label pizzaLabel = new Label("   .........");

            controller.setLabel(pizzaLabel, 1260, 260, 15, FontWeight.BOLD);
            energyLabel2 = new Label("x" + lives);
            controller.setLabel(energyLabel2, 1360, 192, 25, FontWeight.NORMAL);
            Rectangle check = new Rectangle(1360, 250, 25, 25);
            check.setFill(Color.TRANSPARENT);
            check.setStroke(Color.BLACK);
            Rectangle check2 = new Rectangle(1330, 250, 25, 25);
            check2.setFill(Color.TRANSPARENT);check2.setStroke(Color.BLACK);
            pizzaBoxIV1.setLayoutY(250);
            pizzaBoxIV1.setLayoutX(1205);
            pizzaBoxIV1.setFitWidth(65);
            Label str1 = new Label("-------------------------------------------------");
            controller.setLabel(str1, 1200, 500, 25, FontWeight.BOLD);
            Label str2 = new Label("-------------------------------------------------");
            controller.setLabel(str2, 1200, 400, 25, FontWeight.BOLD);
            Label timeLeft = new Label("Time left ");
            controller.setLabel(timeLeft, 1205, 450, 25, FontWeight.NORMAL);
            Label timeStr = new Label("      ...");
            controller.setLabel(timeStr, 1285, 455, 15, FontWeight.BOLD);
            Label level = new Label("Level " + Main.levelVariable);
            controller.setLabel(level, 1212, 80, 60, FontWeight.NORMAL);
            mazePane.getChildren().addAll(settings, level, data, numOfOrder, str, str1, str2, energyLabel, energyLabel2, timeLeft, timeStr, pizzaBoxIV1, pizzaLabel, check,check2);
        }
        if (levelVariable == 1) {
            setUpImageEnergy(imageEnergy);
        } else if (levelVariable == 2) {
            setUpImageEnergy(imageEnergy2);
        } else if (levelVariable == 3) {
            setUpImageEnergy(imageEnergy3);
        } else if (levelVariable == 4) {
            setUpImageEnergy(imageEnergy4);
        } else if (levelVariable == 5) {
            setUpImageEnergy(imageEnergy5);
        }
    }

    FileChooser fileChooser;

    private void finalStage(Stage stage) throws FileNotFoundException {
        Scene win, fail;
        Pane failPane = new Pane(), winPane = new Pane(),wholeWinPane=new Pane();
        Label l1 = new Label("Thank you for trial!");
        Label l2 = new Label("Unfortunately, you didn't pick enough pizza");
        Label l3 = new Label("We can`t offer you this job");
        Label l4 = new Label("You can try again");
        Label l5 = new Label("Good luck!");
        Rectangle rec = new Rectangle(127, 79, 984, 484);
        rec.setFill(Color.rgb(250, 219, 4));
        controller.setLabel(l1, 394, 79, 50, FontWeight.NORMAL);
        controller.setLabel(l2, 127, 158, 50, FontWeight.NORMAL);
        controller.setLabel(l3, 311, 242, 50, FontWeight.NORMAL);
        controller.setLabel(l4, 413, 318, 50, FontWeight.NORMAL);
        controller.setLabel(l5, 486, 397, 50, FontWeight.NORMAL);
        playlist.getMusicPlayer().stop();
        if(countPizzas<5) controller.middleScene("src/finalFail1.jpg", "src/simple-fanfare-horn-2-sound-effect-32891846.mp3", failPane);
        else {
            controller.middleScene("src/simple-fanfare-horn-2-sound-effect-32891846.mp3");
            BackgroundFill myBF = new BackgroundFill(Color.rgb(165,102,5), new CornerRadii(1), //203,253,142
                    new Insets(0.0,0.0,0.0,0.0));// or null for the padding
            wholeWinPane.setBackground(new Background(myBF));
        }
        Button homeButton = new Button();
        try {
            controller.setBackgroundForButton("src/home.png", homeButton);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Button shareButton = new Button();
        try {
            controller.setBackgroundForButton("src/share.png", shareButton);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        homeButton.setOnAction(actionEvent -> {
            lives = 3;
            try {
                setUpStartWindow();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            stage.setScene(scene);
        });
        shareButton.setOnAction(actionEvent -> {
            if (fileChooser == null) {
                fileChooser = new FileChooser();
                String currentDir = System.getProperty("user.dir") + File.separator;
                File f = new File(currentDir);
                fileChooser.setInitialDirectory(f);
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image", "*.png"));
                fileChooser.setTitle("Оберiть папку для збереження");
            }
            WritableImage image = winPane.snapshot(new SnapshotParameters(), null);
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                   controller. popUpWindow("Your image was successfully saved", "It`s path: " + file.getPath(), Alert.AlertType.INFORMATION);
                } catch (Exception e) {
                    controller.popUpWindow("Your image was successfully saved", "It`s path: " + file.getPath(), Alert.AlertType.INFORMATION);
                }
            }
        });

        controller.setButton(shareButton,70,70,929,625);
        if(countPizzas<5) controller.setButton(homeButton, 70, 70, 575, 468);
        else controller.setButton(homeButton, 70, 70, 222, 625);
        failPane.getChildren().addAll(rec, l1, l2, l3, l4, l5, homeButton);
        fail = new Scene(failPane);
        controller.middleScene("src/shareScreenshootPane.jpg",  winPane);
        winPane.setPrefSize(777,409);
        winPane.setLayoutX(222);winPane.setLayoutY(205);
        stage.setWidth(1220);
        stage.setHeight(735);
        Label l11=new Label("Congratulations!");
        Label l12=new Label("You are the best candidate! You are hired");
        Label l13=new Label("Successfully  completed 5 levels");
        Label l14=new Label("Therefore is hired for a job");
        controller.setLabel(l11, 419, 50, 50, FontWeight.NORMAL);
        controller.setLabel(l12, 148, 118, 50, FontWeight.NORMAL);
        l11.setTextFill(Color.WHITE);
        l12.setTextFill(Color.WHITE);
        controller.setLabel(l13, 69, 85, 45, FontWeight.NORMAL);
        controller.setLabel(l14, 74, 230, 53, FontWeight.NORMAL);
        winPane.getChildren().addAll(l13,l14);
        wholeWinPane.setPrefSize(1220,735);
        wholeWinPane.getChildren().addAll(winPane,homeButton,l11,l12,shareButton);
        win=new Scene(wholeWinPane);
        if (countPizzas < 5) {
            stage.setScene(fail);
        } else {
            stage.setScene(win);
        }
    }

    private void setUpImageEnergy(ImageView im) {
            im.setFitWidth(40);
            im.setFitHeight(50);
            if (levelVariable != 5) im.setLayoutX(1005);
            else im.setLayoutX(1205);
            im.setLayoutY(185);
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
            playlist.getMusicPlayer().setVolume(0.2);
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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("If you return to main menu, your progress will not be saved");
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                lives = 3;
                try {
                    setUpStartWindow();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                stage.setScene(scene);
            }

        });
    }

    private void startTimeLines() {
        baconDishTimeLine.play();
        chickenAndBurgerTimeLine.play();
        enemy4Timeline.play();
        burritoTimeLine.play();
        tacoTimeLine.play();
        chocolateTineLine.play();
        sushiTimeline.play();
        sandwichTimeLine.play();
        pancakesTimeLine.play();
        bagelTimeline.play();
        nachoTimeLine.play();
        hotDogTimeLine.play();
        cheesePuffTimeline.play();
    }

    private void stopTimeLines() {
        if (chickenAndBurgerTimeLine != null) chickenAndBurgerTimeLine.stop();
        if (chocolateTineLine != null) chocolateTineLine.stop();
        if (sushiTimeline != null) sushiTimeline.stop();
        if (enemy4Timeline != null) enemy4Timeline.stop();
        if (sandwichTimeLine != null) sandwichTimeLine.stop();
        if (tacoTimeLine != null) tacoTimeLine.stop();
        if (deliveryEnemy1Timer != null) deliveryEnemy1Timer.stop();
        if (deliveryEnemy2Timer != null) deliveryEnemy2Timer.stop();
        if (deliveryEnemy3Timer != null) deliveryEnemy3Timer.stop();
        if (vanTimer != null) vanTimer.stop();
        if (vanTimer2 != null) vanTimer2.stop();
        if (vanTimer3 != null) vanTimer3.stop();
        if (baconDishTimeLine != null) baconDishTimeLine.stop();
        if (burritoTimeLine != null) burritoTimeLine.stop();
        if (bagelTimeline != null) bagelTimeline.stop();
        if (pancakesTimeLine != null) pancakesTimeLine.stop();
        if (nachoTimeLine != null) nachoTimeLine.stop();
        if (hotDogTimeLine != null) hotDogTimeLine.stop();
        if (cheesePuffTimeline != null) cheesePuffTimeline.stop();
    }

    private void pauseWindow(Stage primaryStage, Color color) {
        timeline.stop();
        stopTimeLines();
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

        musicSlider = setMusicSliders(playlist.getMusicPlayer().getVolume(), playlist);
        if (sideSounds.getMusicPlayer() != null)
            sound = setMusicSliders(sideSounds.getMusicPlayer().getVolume(), sideSounds);
        else sound = setMusicSliders(1.0, sideSounds);

        musicSlider.valueProperty().addListener(observable -> {
            playlist.musicPlayer.setVolume(musicSlider.getValue());
            musicStatus = musicSlider.getValue() != 0;
        });

        sound.valueProperty().addListener(observable -> {
            if (sideSounds.musicPlayer != null)
                sideSounds.musicPlayer.setVolume(sound.getValue());
            else {
                sideSounds.musicPlayer.setVolume(0.2);
            }
        });
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

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("If you return to main menu, your progress will not be saved");
            alert.setContentText("Are you sure?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                pauseStage.close();
                lives = 3;
                try {
                    setUpStartWindow();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                primaryStage.setScene(scene);
            }
        });

        playButton.setOnAction(actionEvent -> {
            pauseStage.close();
            timeline.play();
            if (vanTimer != null)
                vanTimer.start();
            if (vanTimer2 != null)
                vanTimer2.start();
            if (vanTimer3 != null)
                vanTimer3.start();
            if (deliveryEnemy1Timer != null)
                deliveryEnemy1Timer.start();
            if (deliveryEnemy2Timer != null)
                deliveryEnemy2Timer.start();
            if (deliveryEnemy3Timer != null)
                deliveryEnemy3Timer.start();
            startTimeLines();
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

    private void generateTimer(Stage primaryStage) {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lives == 0) fail = true;
                if (!finishLevel && !fail) {
                    update();
                } else if (finishLevel) {
                    if (vanTimer != null)
                        vanTimer.stop();
                    if (vanTimer2 != null)
                        vanTimer2.stop();
                    if (vanTimer3 != null)
                        vanTimer3.stop();
                    timer.stop();
                    player.animation.stop();
                    van.animation.stop();
                    stopTimeLines();
                    if (levelVariable != 6) {
                        try {
                            betweenLevelScene(primaryStage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            finalStage(primaryStage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (vanTimer != null)
                        vanTimer.stop();
                    if (vanTimer2 != null)
                        vanTimer2.stop();
                    if (vanTimer3 != null)
                        vanTimer3.stop();
                    timer.stop();
                    player.animation.stop();
                    van.animation.stop();
                    try {
                        failScene(primaryStage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    public static void drawCross() {
        Rectangle h,v;
        if(levelVariable!=5) {
             h = new Rectangle(1160, 260, 25, 5);
                    v = new Rectangle(1170, 250, 5, 25);
        }else{
             h = new Rectangle(1360, 260, 25, 5);
                    v = new Rectangle(1370, 250, 5, 25);
        }
        h.setFill(Color.DARKGREEN);
        v.setFill(Color.DARKGREEN);
        root.getChildren().addAll(h, v);
    }
    public static void drawCross2() {
        Rectangle h, v;
        if (levelVariable != 5 && levelVariable != 6) {
            h = new Rectangle(1130, 260, 25, 5);
            v = new Rectangle(1140, 250, 5, 25);
        }else{
            h = new Rectangle(1330, 260, 25, 5);
            v = new Rectangle(1340, 250, 5, 25);
        }
        h.setFill(Color.DARKGREEN);
        v.setFill(Color.DARKGREEN);
        root.getChildren().addAll(h, v);
    }

    private void setUpStartWindow() throws FileNotFoundException {
        if (playlist.getMusicPlayer() != null) playlist.getMusicPlayer().stop();
        pane.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream("src/photo_2021-05-24_15-17-36_auto_x2.jpg")),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        pane.setPrefSize(1200, 700);
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
        //playlist.setVolume(0.2);
        if(musicSlider!=null) playlist.setVolume(musicSlider.getValue());
        else if (musicStatus) playlist.setVolume(0.2);
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
        controller.setButton(back, 100, 100, 530, 450);
        controller.setBackgroundForButton("src/home.png", back);
        pane.getChildren().addAll(fail1, fail2, back);
        Scene scene = new Scene(pane);
        stage.setWidth(1220);
        stage.setHeight(735);
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
                try {
                    thirdLevel(stage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (levelVariable == 4) {
                try {
                    fourthLevel(stage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (levelVariable == 5) {
                try {
                    fifthLevel(stage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        pane.getChildren().addAll(win1, win2, back, nextLevel, sound);
        Scene scene = new Scene(pane);
        stage.setWidth(1220);
        stage.setHeight(735);
        stage.setScene(scene);
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
            player.animation.setOffsetY(162);
            player.finalMoveY(-velocity);
        } else if (isPressed(KeyCode.DOWN)) {
            player.animation.play();
            player.animation.setOffsetY(0);
            player.finalMoveY(velocity);
        } else if (isPressed(KeyCode.RIGHT)) {
            player.animation.play();
            player.animation.setOffsetY(108);
            player.finalMoveX(velocity);
        } else if (isPressed(KeyCode.LEFT)) {
            player.animation.play();
            player.animation.setOffsetY(54);
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

}
