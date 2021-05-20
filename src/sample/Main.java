package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class Main extends Application {

    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    public static ArrayList<Rectangle> bonuses = new ArrayList<>();
    FileInputStream inputstream = new FileInputStream("src/image_2021-05-20_11-20-44-removebg-preview (1).png");
    Image image = new Image(inputstream);
    ImageView imageView = new ImageView(image);
    Hero player = new Hero(imageView);

    public Main() throws FileNotFoundException {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        FileInputStream inputstream = new FileInputStream("pizza_auto_x2.jpg");
        Image image = new Image(inputstream);
        BackgroundImage myBI = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI));
        pane.setPrefSize(1200, 680);
        Button b = new Button("Play");
        primaryStage.setScene(new Scene(pane));

        pane.getChildren().add(b);
        pane.getChildren();

        primaryStage.show();

        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    firstLevel(primaryStage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void firstLevel(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root,1200,700);
        Pane p=(Pane) scene.lookup("#mazePane");
        player.setMinSize(100,100);
        player.setLayoutX(500);player.setLayoutY(500);
        p.getChildren().addAll(player);
/*        scene.setOnKeyPressed(event->keys.put(event.getCode(),true));
        scene.setOnKeyReleased(event-> {
            keys.put(event.getCode(), false);
        });*/
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                scene.setOnKeyPressed(event->keys.put(event.getCode(),true));
                scene.setOnKeyReleased(event-> {
                    keys.put(event.getCode(), false);
                });
                if (isPressed(KeyCode.UP)) {
                    player.animation.play();
                    player.animation.setOffsetY(155);
                    player.moveY(-50);
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
                }
        else{
            player.animation.stop();
        }
                /* bonus();*/
            }
        };
        timer.start();
        stage.setTitle("Game");
        stage.setScene(scene);
        stage.show();
    }

/*    public void bonus(){
        int random = (int)Math.floor(Math.random()*100);
        int x = (int)Math.floor(Math.random()*600);
        int y = (int)Math.floor(Math.random()*600);
        if(random == 5){
            Rectangle rect = new Rectangle(20,20,Color.RED);
            rect.setX(x);
            rect.setY(y);
            bonuses.add(rect);
            root.getChildren().addAll(rect);
        }
    }*/
    public void update() {
        if (isPressed(KeyCode.UP)) {
            player.animation.play();
            player.animation.setOffsetY(155);
            player.moveY(-50);
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
        }
/*        else{
            player.animation.stop();
        }*/
    }
    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
