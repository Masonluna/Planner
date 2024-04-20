package edu.apsu.planner.app;

import edu.apsu.planner.data.MonthInfo;
import edu.apsu.planner.data.Tag;
import edu.apsu.planner.data.Type;
import edu.apsu.planner.view.monthViewUI;
import edu.apsu.planner.view.weekViewUI;
import edu.apsu.planner.view.welcomepageUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.time.Month;

public class PlannerApplication extends Application {
    // Data Instances
    private MonthInfo[] months;
    private final Type[] types = new Type[5];

    // JavaFX Instances
    private Stage stage;
    public Scene welcomeScene;
    public Scene weekViewScene;
    public Scene monthViewScene;
    private monthViewUI monthViewUI;
    private weekViewUI weekViewUI;


    @Override
    public void init() throws Exception {
        super.init();
        months = new MonthInfo[12];
        for (int i = 0; i < months.length; i++) {
            months[i] = new MonthInfo(2024, Month.of(i + 1));
        }

        Rectangle defaultClassSymbol = new Rectangle(20, 20, Color.BLUE);
        Image classSymbolImage =  Type.loadImageIntoLabel("/edu/apsu/planner/symbolPNGResource/icons8-class-64.png");
        Type classType = new Type(Tag.CLASS, classSymbolImage, defaultClassSymbol, true);
        types[0] = classType;

        Rectangle defaultWorkSymbol = new Rectangle(20, 20, Color.FORESTGREEN);
        Image workSymbolImage =  Type.loadImageIntoLabel("/edu/apsu/planner/symbolPNGResource/icons8-work-100.png");
        Type workType = new Type(Tag.WORK, workSymbolImage, defaultWorkSymbol, true);
        types[1] = workType;

        Image assignmentDueSymbolImage =  Type.loadImageIntoLabel("/edu/apsu/planner/symbolPNGResource/icons8-study-100.png");
        Rectangle defaultAssignmentSymbol = new Rectangle(20, 20, Color.PURPLE);
        Type assignmentType = new Type(Tag.ASSIGNMENT, assignmentDueSymbolImage, defaultAssignmentSymbol,true);
        types[2] = assignmentType;

        Rectangle defaultBillSymbol = new Rectangle(20, 20, Color.RED);
        Image billDueSymbolImage =  Type.loadImageIntoLabel("/edu/apsu/planner/symbolPNGResource/icons8-bill-64.png");
        Type billType = new Type(Tag.BILL, billDueSymbolImage, defaultBillSymbol,true);
        types[3] = billType;

        Rectangle defaultCustomEventSymbol = new Rectangle(20, 20, Color.AQUA);
        Image customSymbolImage =  Type.loadImageIntoLabel("/edu/apsu/planner/symbolPNGResource/icons8-important-100.png");
        Type customEventType = new Type(Tag.CUSTOM, customSymbolImage, defaultCustomEventSymbol, true);
        types[4] = customEventType;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = new Stage();
        monthViewUI = new monthViewUI(this, months);
        weekViewUI = new weekViewUI(this, months);
        welcomeScene = new Scene(new welcomepageUI(this));
        weekViewScene = new Scene(weekViewUI);
        monthViewScene = new Scene(monthViewUI);

        stage.setScene(welcomeScene);
        stage.show();
    }

    public void switchScene(Scene scene)
    {
        stage.setScene(scene);
    }

    public void updateUI() {
        monthViewUI.createGridPane(monthViewUI.getMonths()[monthViewUI.getCurrentMonthIndex()]);
        monthViewUI.setRight(monthViewUI.createRightPane());
        weekViewUI.setCenter(weekViewUI.createCenterPane());
    }

    public Stage getStage() {
        return this.stage;
    }

    public Type[] getTypes() {
        return this.types;
    }
}
