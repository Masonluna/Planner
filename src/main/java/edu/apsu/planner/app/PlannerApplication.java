package edu.apsu.planner.app;

import edu.apsu.planner.data.MonthInfo;
import edu.apsu.planner.view.monthViewUI;
import edu.apsu.planner.view.weekViewUI;
import edu.apsu.planner.view.welcomepageUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.Month;

public class PlannerApplication extends Application {
    // Data Instances
    MonthInfo[] months;

    // JavaFX Instances
    private Stage stage;
    public Scene welcomeScene;
    public Scene weekViewScene;
    public Scene monthViewScene;


    @Override
    public void init() throws Exception {
        super.init();
        months = new MonthInfo[12];
        for (int i = 0; i < months.length; i++) {
            months[i] = new MonthInfo(2024, Month.of(i + 1));
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = new Stage();
        welcomeScene = new Scene(new welcomepageUI(this));
        weekViewScene = new Scene(new weekViewUI(this, months));
        monthViewScene = new Scene(new monthViewUI(this, months));

        stage.setScene(welcomeScene);
        stage.show();
    }

    public void switchScene(Scene scene)
    {
        stage.setScene(scene);
    }

    public Stage getStage() {
        return this.stage;
    }
}
