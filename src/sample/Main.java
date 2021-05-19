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
import java.io.IOException;

public class Main extends Application {

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
        //p.getChildren();
        stage.setScene(new Scene(root, 1200,700));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
