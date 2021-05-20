package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {

    @FXML
    private Pane backPane;

    @FXML
    private Pane mazePane;

    Pane pane = new Pane();
    Button b;
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        root.setPrefSize(600,600);
//        root.getChildren().addAll(player);
//
//        Scene scene = new Scene(root);
//        scene.setOnKeyPressed(event->keys.put(event.getCode(),true));
//        scene.setOnKeyReleased(event-> {
//            keys.put(event.getCode(), false);
//        });
//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                update();
//                bonus();
//            }
//        };
//        timer.start();
//        primaryStage.setTitle("Game");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //backPane.setPrefSize(600, 600);
        //root =  (Pane) scene.lookup("#backPane");
        mazePane.getChildren().addAll(player);

        Scene scene = new Scene(backPane);
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                bonus();
            }
        };
        timer.start();
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();
//        Scene sc = new Scene(pane);
//        primaryStage.setScene(sc);
//        startWindow(sc);
//        primaryStage.show();
//
//        b.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                try {
//                    firstLevel(primaryStage);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }


    private void startWindow(Scene scene) throws FileNotFoundException {
        FileInputStream inputstream = new FileInputStream("pizza_auto_x2.jpg");
        Image image = new Image(inputstream);
        BackgroundImage myBI = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI));
        pane.setPrefSize(1200, 680);
        b = new Button("Play");
        Text name = new Text(325, 250, "Delivery maze");
        name.setFont(Font.font("Berlin Sans FB Demi", FontWeight.BOLD, 80));
        // name.setFill(Color.rgb(243,164,139));
        name.setFill(Color.WHITE);

        DropShadow is = new DropShadow();
        is.setOffsetY(3.0f);
        name.setEffect(is);
        name.setCache(true);


        pane.getChildren().add(b);
        pane.getChildren().add(name);
    }

    static Pane p;

    public void firstLevel(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 1200, 700);
        p = (Pane) scene.lookup("#mazePane");

        player.setLayoutX(500);
        player.setLayoutY(500);
        p.getChildren().addAll(player);
//        root.setPrefSize(600,600);
//        root.getChildren().addAll(player);
//        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> {
            keys.put(event.getCode(), false);
        });
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                bonus();
            }
        };
        timer.start();
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        // primaryStage.show();
//
    }
//
//    private void firstLevel(Stage stage) throws IOException {
//
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        Scene scene = new Scene(root, 1200, 700);
//        Pane p = (Pane) scene.lookup("#mazePane");
//        player.setMinSize(100, 100);
//        player.setLayoutX(500);
//        player.setLayoutY(500);
//        p.getChildren().addAll(player);
///*        scene.setOnKeyPressed(event->keys.put(event.getCode(),true));
//        scene.setOnKeyReleased(event-> {
//            keys.put(event.getCode(), false);
//        });*/
//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
//                scene.setOnKeyReleased(event -> {
//                    keys.put(event.getCode(), false);
//                });
//                if (isPressed(KeyCode.UP)) {
//                    player.animation.play();
//                    player.animation.setOffsetY(155);
//                    player.moveY(-50);
//                } else if (isPressed(KeyCode.DOWN)) {
//                    player.animation.play();
//                    player.animation.setOffsetY(0);
//                    player.moveY(2);
//                } else if (isPressed(KeyCode.RIGHT)) {
//                    player.animation.play();
//                    player.animation.setOffsetY(64);
//                    player.moveX(2);
//                } else if (isPressed(KeyCode.LEFT)) {
//                    player.animation.play();
//                    player.animation.setOffsetY(32);
//                    player.moveX(-2);
//                } else {
//                    player.animation.stop();
//                }
//                /* bonus();*/
//            }
//        };
//        timer.start();
//        stage.setTitle("Game");
//        stage.setScene(scene);
//        stage.show();
//    }
//

    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    public static ArrayList<Rectangle> bonuses = new ArrayList<>();
    FileInputStream inputstream;

    {
        try {
            inputstream = new FileInputStream("src/image_2021-05-20_11-20-44-removebg-preview (1).png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    Image image = new Image(inputstream);
    ImageView imageView = new ImageView(image);
    Hero player = new Hero(imageView);
    static Pane root = new Pane();

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
            player.animation.setOffsetY(96);
            player.moveY(-2);
        } else if (isPressed(KeyCode.DOWN)) {
            player.animation.play();
            player.animation.setOffsetY(0);
            player.moveY(2);
        } else if (isPressed(KeyCode.RIGHT)) {
            player.animation.play();
            player.animation.setOffsetY(64);
            player.moveX(2);
        } else if (isPressed(KeyCode.LEFT)) {
            player.animation.play();
            player.animation.setOffsetY(32);
            player.moveX(-2);
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
