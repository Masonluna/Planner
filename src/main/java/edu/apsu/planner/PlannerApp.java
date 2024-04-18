package edu.apsu.planner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PlannerApp extends Application {
    public Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        stage = new Stage();
        //stage.setScene();
        stage.setTitle("Planer Application");
        stage.show();
    }

    public void switchScene(Scene scene){
        stage.setScene(scene);
    }
}
