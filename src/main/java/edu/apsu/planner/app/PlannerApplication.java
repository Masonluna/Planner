package edu.apsu.planner.app;

import edu.apsu.planner.data.MonthInfo;
import edu.apsu.planner.data.Tag;
import edu.apsu.planner.data.Type;
import edu.apsu.planner.data.User;
import edu.apsu.planner.view.DayFlowPane;
import edu.apsu.planner.view.MonthViewUI;
import edu.apsu.planner.view.WeekViewUI;
import edu.apsu.planner.view.WelcomePageUI;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.time.Month;

public class PlannerApplication extends Application {
    // Data Instances
    private MonthInfo[] months;
    private final Type[] types = new Type[5];
    private User currentUser;

    // JavaFX Instances
    private Stage stage;
    public Scene welcomeScene;
    public Scene weekViewScene;
    public Scene monthViewScene;
    private MonthViewUI monthViewUI;
    private WeekViewUI weekViewUI;
    private WelcomePageUI welcomePageUI;


    @Override
    public void init() throws Exception {
        super.init();
        months = new MonthInfo[12];
        for (int i = 0; i < months.length; i++) {
            months[i] = new MonthInfo(2024, Month.of(i + 1));
        }

        Rectangle defaultClassSymbol = new Rectangle(20, 20, Color.BLUE);
        Image classSymbolImage = Type.loadImageIntoLabel("/edu/apsu/planner/symbolPNGResource/icons8-class-64.png");
        Type classType = new Type(Tag.CLASS, classSymbolImage, defaultClassSymbol, true);
        types[0] = classType;

        Rectangle defaultWorkSymbol = new Rectangle(20, 20, Color.FORESTGREEN);
        Image workSymbolImage = Type.loadImageIntoLabel("/edu/apsu/planner/symbolPNGResource/icons8-work-100.png");
        Type workType = new Type(Tag.WORK, workSymbolImage, defaultWorkSymbol, true);
        types[1] = workType;

        Image assignmentDueSymbolImage = Type.loadImageIntoLabel("/edu/apsu/planner/symbolPNGResource/icons8-study-100.png");
        Rectangle defaultAssignmentSymbol = new Rectangle(20, 20, Color.PURPLE);
        Type assignmentType = new Type(Tag.ASSIGNMENT, assignmentDueSymbolImage, defaultAssignmentSymbol, true);
        types[2] = assignmentType;

        Rectangle defaultBillSymbol = new Rectangle(20, 20, Color.RED);
        Image billDueSymbolImage = Type.loadImageIntoLabel("/edu/apsu/planner/symbolPNGResource/icons8-bill-64.png");
        Type billType = new Type(Tag.BILL, billDueSymbolImage, defaultBillSymbol, true);
        types[3] = billType;

        Rectangle defaultCustomEventSymbol = new Rectangle(20, 20, Color.DARKORANGE);
        Image customSymbolImage = Type.loadImageIntoLabel("/edu/apsu/planner/symbolPNGResource/icons8-important-100.png");
        Type customEventType = new Type(Tag.CUSTOM, customSymbolImage, defaultCustomEventSymbol, true);
        types[4] = customEventType;
    }

    @Override
    public void start(Stage primaryStage) {
        stage = new Stage();
        monthViewUI = new MonthViewUI(this);
        weekViewUI = new WeekViewUI(this);
        welcomePageUI = new WelcomePageUI(this);
        welcomeScene = new Scene(new WelcomePageUI(this));
        weekViewScene = new Scene(weekViewUI);
        monthViewScene = new Scene(monthViewUI);

        stage.setScene(welcomeScene);
        stage.setTitle("Planner Application");
        stage.show();
    }

    public void switchScene(Scene scene) {
        if (scene.equals(welcomeScene)) {
            stage.setMaxWidth(1000);
            stage.setMaxHeight(600);
        } else {
            stage.setMaxHeight(Integer.MAX_VALUE);
            stage.setMaxWidth(Integer.MAX_VALUE);
        }
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setMaximized(scene.equals(weekViewScene) || scene.equals(monthViewScene));
    }

    public void updateUI() {
        monthViewUI.createGridPane(months[monthViewUI.getCurrentMonthIndex()]);
        refreshSelectedDayFlowPane(monthViewUI.getSelectedDayFlowPaneRI(), monthViewUI.getSelectedDayFlowPaneCI());
        monthViewUI.setRight(monthViewUI.createRightPane());
        weekViewUI.setCenter(weekViewUI.createCenterPane());
    }

    private void refreshSelectedDayFlowPane(Integer row, Integer col) {
        DayFlowPane dayFlowPane = null;
        for (Node node : monthViewUI.getMonthViewGridPane().getChildren()) {
            Integer ri = GridPane.getRowIndex(node);
            Integer ci = GridPane.getColumnIndex(node);
            if (node != null && row.equals(ri) && col.equals(ci)) {
                dayFlowPane = (DayFlowPane) node;
            }
        }
        monthViewUI.setSelectedDayFlowPane(dayFlowPane);
    }

    public Stage getStage() {
        return this.stage;
    }

    public MonthViewUI getMonthViewUI() {
        return monthViewUI;
    }

    public WeekViewUI getWeekViewUI() {
        return weekViewUI;
    }

    public Scene getMonthViewScene() {
        return monthViewScene;
    }

    public Scene getWelcomeScene() {
        return welcomeScene;
    }

    public Type[] getTypes() {
        return this.types;
    }

    public void setMonths(MonthInfo[] months) {
        this.months = months;
    }

    public MonthInfo[] getMonths() {
        return months;
    }
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

}
