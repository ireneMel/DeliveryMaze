package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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


public class Main extends Application {
    boolean finishLevel=false;
    public static List<Rectangle> wallsHorizontal = new ArrayList<>();
    public static ArrayList<Enemy> monsters=new ArrayList<>();
    private Timeline timeline,enemyTimeLine;
    private Label timerLabel = new Label(), splitTimerLabel = new Label();
    private DoubleProperty timeSeconds = new SimpleDoubleProperty(5.0);
    private Duration time = Duration.minutes(5.0);
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
        startWindow();
        b.setOnAction(actionEvent -> {
            try {
                firstLevel(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }

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
        pane.getChildren().add(b);
        pane.getChildren().add(name);
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
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                Duration duration = ((KeyFrame)t.getSource()).getTime();
                                time = time.subtract(duration);
                                timeSeconds.set(time.toMinutes());
                                if(time.equals(Duration.ZERO)){
                                    timeline.stop();
                                    JOptionPane.showMessageDialog(null,"Loser","Error",JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        timerLabel.setLayoutX(1100);timerLabel.setLayoutY(500);
        mazePane.getChildren().add(timerLabel);
        enemyTimeLine=new Timeline(
                new KeyFrame(Duration.seconds(10),
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if(enemy!=null) {
                            if (enemy.isVisible()) {
                                enemy.setVisible(false);
                            } else enemy.setVisible(true);
                        }else enemyTimeLine.stop();
                    }
                })
        );
        enemyTimeLine.setCycleCount(Timeline.INDEFINITE);
        enemyTimeLine.play();


        enemy.setLayoutY(500);enemy.setLayoutX(500);
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

}
