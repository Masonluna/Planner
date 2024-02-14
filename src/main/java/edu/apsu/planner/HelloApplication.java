package edu.apsu.planner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        Label helloLabel = new Label("Hello World!");
        helloLabel.setFont(Font.font("Arial", 36));
        root.getChildren().add(helloLabel);

        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Hello Git!!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}