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
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Pane pane = new Pane();
        FileInputStream inputstream = new FileInputStream("pizza_auto_x2.jpg");
        Image image = new Image(inputstream);
        BackgroundImage myBI = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI));
        pane.setPrefSize(1200, 680);
        Button b = new Button("Play");


        pane.getChildren().add(b);
        pane.getChildren();
        primaryStage.setScene(new Scene(root, 1200,700));
        primaryStage.show();

        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.setScene(startGame());
            }
        });
    }

    private Scene startGame(){
        Pane p = new Pane();
        Pane maze = new Pane();

        p.getChildren().add(maze);


        //p.getChildren();
        return new Scene(p);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
