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

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Main extends Application {
    boolean finishLevel = false;

    public static List<Rectangle> wallsHorizontal = new ArrayList<>();
    public static ArrayList<Enemy> monsters = new ArrayList<>();
    public static ArrayList<Rectangle> bonuses = new ArrayList<>();
    private final ArrayList<String> complimentArray = new ArrayList<>();
    private final HashMap<KeyCode, Boolean> keys = new HashMap<>();

    private Timeline timeline, enemyTimeLine;
    private final DoubleProperty timeSeconds = new SimpleDoubleProperty(5.0);
    private Duration time = Duration.minutes(5.0);
    private final Label timerLabel = new Label();
    FileInputStream chickenS = new FileInputStream("src/86_roastedchicken_dish.png");
    Image chicken=new Image(chickenS);
    ImageView ch=new ImageView(chicken);
    Enemy enemy2=new Enemy(ch);
    FileInputStream inputStreamE = new FileInputStream("src/17_burger_napkin.png"), grass = new FileInputStream("src/photo_2021-05-24_12-42-00.jpg"), inputStream = new FileInputStream("src/hero.png");
    Image image = new Image(inputStream), grassImage = new Image(grass), imageE = new Image(inputStreamE);
    ImageView imageView = new ImageView(image), im = new ImageView(imageE);
    Hero player = new Hero(imageView, 90, 60);
    Enemy enemy = new Enemy(im);

    Pane pane = new Pane();
    static Pane root = new Pane();
    Button play = new Button(), instruction = new Button(), compliment = new Button(), music = new Button();

    Random random = new Random();
    private int randomNum;
    public static int levelVariable = 1, lives = 3;

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

        stage.setTitle("Delivery Maze");
        stage.setScene(new Scene(pane));
        stage.show();

    }

    private void setUpStartWindow() throws FileNotFoundException {
        String bip = "src/data/fjordmusic.mp3";
        // Media hit= new Media
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

        pane.getChildren().addAll(play, name, compliment, instruction, music);
    }

    public void firstLevel(Stage primaryStage) throws FileNotFoundException {
        Label level = new Label("Level " + levelVariable);
        level.setFont(Font.font("Berlin Sans FB Demi", FontWeight.BOLD, 60));
        level.setLayoutX(1010);
        level.setLayoutY(50);
        root.setPrefSize(1200, 700);
        Pane mazePane = new Pane();
        mazePane.setPrefSize(1000, 700);
        addRectangles(mazePane, level);
        Image groundImage = new Image("https://static.wikia.nocookie.net/oxygen-not-included/images/0/04/%D0%97%D0%B5%D0%BC%D0%BB%D1%8F.png/revision/latest?cb=20200301161904&path-prefix=ru");
        BackgroundImage myBI = new BackgroundImage(groundImage,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        mazePane.setBackground(new Background(myBI));

        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 4em;");
        timerLabel.setTextFill(Color.rgb(75, 124, 23));
        timeline = new Timeline(
                new KeyFrame(Duration.minutes(0.01),
                        t -> {
                            Duration duration = ((KeyFrame) t.getSource()).getTime();
                            time = time.subtract(duration);
                            if (time.equals(Duration.seconds(10))) {
                                timerLabel.setTextFill(Color.RED);
                            }
                            timeSeconds.set(time.toMinutes());
                            if (time.equals(Duration.ZERO)) {
                                timeline.stop();
                                JOptionPane.showMessageDialog(null, "Loser", "Error", JOptionPane.ERROR_MESSAGE);
                            }
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
                            if (enemy != null) {
                                enemy.setVisible(!enemy.isVisible());
                            } //else enemyTimeLine.stop();
                            if (enemy2 != null) {
                                enemy2.setVisible(!enemy2.isVisible());
                            } //else enemyTimeLine.stop();
                        })
        );
        enemyTimeLine.setCycleCount(Timeline.INDEFINITE);
        enemyTimeLine.play();
        enemy.setLayoutY(500);
        enemy.setLayoutX(500);
        monsters.add(enemy);
        enemy2.setVisible(false);
        enemy2.setLayoutX(200);enemy2.setLayoutY(200);monsters.add(enemy2);

        root.getChildren().add(mazePane);

        for (Rectangle a : wallsHorizontal) {
            a.setFill(new ImagePattern(grassImage, 0, 0, 1, 1, true));
            // a.setFill(Color.rgb(4, 124, 94));
        }
        player.setLayoutX(850);
        player.setLayoutY(600);
        //player.vi
        root.getChildren().addAll(player);
        root.getChildren().addAll(enemy,enemy2);
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                //bonus();
            }
        };
        timer.start();
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);

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

    private void addRectangles(Pane mazePane, Label level){
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
                r14 = new Rectangle(152, 199, 107, 40),
                r15 = new Rectangle(0, 108, 130, 40),
                r16 = new Rectangle(468, 160, 40, 190),
                r17 = new Rectangle(273, 265, 130, 40),
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

}
