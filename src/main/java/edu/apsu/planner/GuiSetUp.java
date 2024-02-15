package edu.apsu.planner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuiSetUp extends Application {
    //hello

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setPrefSize(1000, 1000);
        root.setTop(createMenuBar());
        root.setCenter(welcomPage());

        Scene scene = new Scene(root);
        stage.setTitle("Planner App");
        stage.setScene(scene);
        stage.show();
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem openMenuItem = new MenuItem("Open");


        //openMenuItem.setOnAction(e -> loadPlanner());
        MenuItem saveMenuItem = new MenuItem("Save");
        //saveMenuItem.setOnAction(e -> savePlanner());
        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(e -> Platform.exit());
        fileMenu.getItems().addAll(openMenuItem, saveMenuItem, separatorMenuItem, exitMenuItem);
        menuBar.getMenus().add(fileMenu);

        Menu addMenu = new Menu("Add Items");
        MenuItem classScheduleMenuItem = new MenuItem("Add Class Schedule");
        MenuItem workScheduleMenuItem = new MenuItem("Add Work Schedule");
        MenuItem customScheduleMenuItem = new MenuItem("Add Custom Schedule");
        SeparatorMenuItem breakSeparator = new SeparatorMenuItem();
        MenuItem assignmentDuteDateMenuItem = new MenuItem("Add Assignment Due Date");
        MenuItem customEvemtMenuItem = new MenuItem("Add Custom Event");
        MenuItem billDueMenuItem = new MenuItem("Add Bill");
        addMenu.getItems().addAll(classScheduleMenuItem, workScheduleMenuItem, customScheduleMenuItem,
                breakSeparator, assignmentDuteDateMenuItem, customEvemtMenuItem, billDueMenuItem);
        menuBar.getMenus().add(addMenu);

        Menu insertMenu = new Menu("Insert");
        MenuItem photoMenuItem = new MenuItem("Insert Photo");
        insertMenu.getItems().add(photoMenuItem);
        menuBar.getMenus().add(insertMenu);

        Menu filterMenu = new Menu("Filter ");
        MenuItem classScheduleShow = new MenuItem("Only show Class Schedule");
        MenuItem workScheduleShow = new MenuItem("Only show Work Schedule");
        MenuItem customScheduleShow = new MenuItem("Only show Custom Schedule");
        SeparatorMenuItem breakSeparator2 = new SeparatorMenuItem();
        MenuItem assignmentDuteDateShow = new MenuItem("Only show Assignment Due Dates");
        MenuItem customEvemtShow = new MenuItem("Only show Custom Events");
        MenuItem billDueShow = new MenuItem("Only show Bills");
        filterMenu.getItems().addAll(classScheduleShow, workScheduleShow, customScheduleShow,
                breakSeparator2, assignmentDuteDateShow, customEvemtShow, billDueShow);
        menuBar.getMenus().add(filterMenu);


        return menuBar;
    }
   /* private void savePlanner(){

    }
    private void loadPlanner(){

    }*/

    private VBox welcomPage() {
        VBox center = new VBox();
        center.setSpacing(10);
        center.setAlignment(Pos.CENTER);
        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);
        Button startWeeklyPlanner = new Button("START on Weekly Planner");
        Button startMonthlyPlanner = new Button("START on Monthly Planner");
        buttons.getChildren().addAll(startWeeklyPlanner, startMonthlyPlanner);
        Label welcome = new Label();
        welcome.setText("Welcome to the Planner Application!");
        center.getChildren().addAll(welcome, buttons);
        return center;
    }
}

