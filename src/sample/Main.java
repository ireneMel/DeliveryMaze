package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;


public class Main extends Application {
   public static ArrayList<Rectangle> wallsHorizontal = new ArrayList<>();
   public static ArrayList<Rectangle> wallsVertical = new ArrayList<>();
    Pane pane = new Pane();
    Button b;
    private int levelVariable = 1;
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    public static ArrayList<Rectangle> bonuses = new ArrayList<>();
    FileInputStream inputstream;

    {
        try {
            inputstream = new FileInputStream("src/hero.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    Image image = new Image(inputstream);
    ImageView imageView = new ImageView(image);
    Hero player = new Hero(imageView);
    static Pane root = new Pane();

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

    private void startWindow() throws FileNotFoundException {
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

    public void firstLevel(Stage primaryStage) {
        Pane settingsPane = new Pane();
        settingsPane.setPrefSize(200, 700);
        settingsPane.setLayoutX(1000);
        Button pause = new Button("Pause");
        pause.setLayoutX(80);
        pause.setLayoutY(200);
        Label level = new Label("Level " + levelVariable);
        level.setLayoutX(10);
        level.setLayoutY(50);
        settingsPane.getChildren().addAll(pause, level);
        root.setPrefSize(1200, 700);
        Pane mazePane = new Pane();
        mazePane.setPrefSize(1000, 700);
        Polygon mainBorders = new Polygon();
        mainBorders.getPoints().addAll(new Double[]{887.0, -126.0, -113.0, -126.0, -113.0, -95.0, 853.0, -95.0, 853.0, 546.0, -80.0, 546.0, -80.0, -16.0, -113.0, -16.0, -113.0, 574.0, 887.0, 574.0});
        mainBorders.setFill(Color.rgb(4, 124, 94));
        mainBorders.setLayoutX(113);
        mainBorders.setLayoutY(126);
        //walls.add(mainBorders);
        mazePane.getChildren().add(mainBorders);
        Rectangle r1 = new Rectangle(786, 519, 40, 155),
                r2 = new Rectangle(592, 519, 200, 40),
                r3 = new Rectangle(786, 128, 40, 295),
                r4 = new Rectangle(613, 128, 179, 40),
                r5 = new Rectangle(582, 28, 40, 200),
                r6 = new Rectangle(408, 350, 389, 40),
                r7 = new Rectangle(408, 350, 40, 215),
                r8 = new Rectangle(259, 525, 157, 40),
                r9 = new Rectangle(259, 141, 40, 404),
                r10 = new Rectangle(128, 445, 157, 40),
                r11 = new Rectangle(582, 475, 40, 84),
                r12 = new Rectangle(128, 445, 40, 131),
                r13 = new Rectangle(14, 305, 138, 40),
                r14 = new Rectangle(152, 199, 138, 40),
                r15 = new Rectangle(0, 108, 130, 40),
                r16 = new Rectangle(468, 160, 40, 200),
                r17 = new Rectangle(273, 265, 130, 40),
                r18 = new Rectangle(351, 0, 40, 92),
                r19 = new Rectangle(907, 188, 85, 40),
                r20 = new Rectangle(794, 383, 79, 40),
                r21 = new Rectangle(500, 683, 40, 48);
        r1.setFill(Color.rgb(4, 124, 94));
        r2.setFill(Color.rgb(4, 124, 94));
        r3.setFill(Color.rgb(4, 124, 94));
        r4.setFill(Color.rgb(4, 124, 94));
        r5.setFill(Color.rgb(4, 124, 94));
        r6.setFill(Color.rgb(4, 124, 94));
        r7.setFill(Color.rgb(4, 124, 94));
        r8.setFill(Color.rgb(4, 124, 94));
        r9.setFill(Color.rgb(4, 124, 94));
        r10.setFill(Color.rgb(4, 124, 94));
        r11.setFill(Color.rgb(4, 124, 94));
        r12.setFill(Color.rgb(4, 124, 94));
        r13.setFill(Color.rgb(4, 124, 94));
        r14.setFill(Color.rgb(4, 124, 94));
        r15.setFill(Color.rgb(4, 124, 94));
        r16.setFill(Color.rgb(4, 124, 94));
        r17.setFill(Color.rgb(4, 124, 94));
        r18.setFill(Color.rgb(4, 124, 94));
        r19.setFill(Color.rgb(4, 124, 94));
        r20.setFill(Color.rgb(4, 124, 94));
        r21.setFill(Color.rgb(4, 124, 94));
        mazePane.getChildren().addAll(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21);
        wallsHorizontal.add(r1);
        wallsHorizontal.add(r2);
        wallsHorizontal.add(r3);
        wallsHorizontal.add(r4);
        wallsHorizontal.add(r5);
        wallsHorizontal.add(r6);
        wallsHorizontal.add(r7);
        wallsHorizontal.add(r8);
        wallsHorizontal.add(r9);
        wallsHorizontal.add(r10);
        wallsHorizontal.add(r11);
        wallsHorizontal.add(r12);
        wallsHorizontal.add(r13);
        wallsHorizontal.add(r14);
        wallsHorizontal.add(r15);
        wallsHorizontal.add(r16);
        wallsHorizontal.add(r17);
        wallsHorizontal.add(r18);
        wallsHorizontal.add(r19);
        wallsHorizontal.add(r20);
        wallsHorizontal.add(r21);
        root.getChildren().add(mazePane);

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
        // primaryStage.show();

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
//            double pos;
//            int c = 0;
//            for (Rectangle o : wallsHorizontal) {
//                c++;
//                pos = player.getLayoutY() - 2;
//                if (pos == (o.getY() + o.getHeight())) {
//                    System.out.println(pos + " " + (o.getY() + o.getHeight()) + " " + c);
//                    move = false;
//                    break;
//                }
//            }
//            if (move) {
                player.finalMoveY(-2);
                //player.setLayoutY(player.getLayoutY() - 2);
            //}

        } else if (isPressed(KeyCode.DOWN)) {
            player.animation.play();
            player.animation.setOffsetY(0);
            player.moveY(2);
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
