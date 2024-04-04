package edu.apsu.planner.view;

import edu.apsu.planner.data.DayInfo;
import edu.apsu.planner.data.MonthInfo;
import edu.apsu.planner.data.Type;
import edu.apsu.planner.data.WeekInfo;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDate;

public class monthViewUI extends Application {

    // Size Constants
    private final int GRID_PANE_NODE_HEIGHT = 100;
    private final int GRID_PANE_NODE_WIDTH = 130;
    private final int INSET_SIZE = 15;


    // Data instances
    private LocalDate date = LocalDate.now();
    private MonthInfo[] months;
    private int currentMonthIndex;

    // FX Node Instances
    private GridPane monthViewGridPane;
    private Label dayLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        // Set up MonthInfo data
        months = new MonthInfo[12 - date.getMonthValue() + 1];
        for (int i = 0; i < months.length; i++) {
            months[i] = new MonthInfo(2024, date.getMonth().plus(i));
        }

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #B7B7B7;");
        root.setPrefSize(1375, 900);


        root.setTop(creatMenuBar());
        root.setLeft(createLeftPane());
        root.setCenter(createCenterPane());
        root.setRight(createRightPane());

        BorderPane.setAlignment(root.getRight(), Pos.TOP_LEFT);

        Scene scene = new Scene(root);
        stage.setTitle("Planer Application");
        stage.setScene(scene);
        stage.show();


    }

    public MenuBar creatMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #B7B7B7;");


        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem openMenuItem = new MenuItem("Open");
        MenuItem saveMenuItem = new MenuItem("Save");
        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(actionEvent ->
                Platform.exit()
        );
        fileMenu.getItems().addAll(newMenuItem, openMenuItem, saveMenuItem, separatorMenuItem, exitMenuItem);

        Menu addMenu = new Menu("Add");

        MenuItem addClassSchedule = new MenuItem("Add Class Schedule");
        MenuItem addWorkSchedule = new MenuItem("Add Work Schedule");
        MenuItem addCustomSchedule = new MenuItem("Add Custom Schedule");
        SeparatorMenuItem separatorMenuItem2 = new SeparatorMenuItem();
        MenuItem addAssignmentDueDate = new MenuItem("Add Assignment Due Date");
        MenuItem addBillDueDate = new MenuItem("Add Bill Due Date");
        MenuItem addCustomEvent = new MenuItem("Add Custom Event");
        addMenu.getItems().addAll(
                addClassSchedule,
                addWorkSchedule,
                addCustomSchedule,
                separatorMenuItem2,
                addAssignmentDueDate,
                addBillDueDate,
                addCustomEvent);

        Menu insertMenu = new Menu("Insert");

        Menu insertSymbol = new Menu("Insert Symbol");

        MenuItem classSymbol = new MenuItem("Class symbol");
        MenuItem workSymbol = new MenuItem("Work symbol");
        MenuItem studySymbol = new MenuItem("Study symbol");
        MenuItem billSymbol = new MenuItem("Bill symbol");
        MenuItem importSymbol = new MenuItem("Important symbol");
        MenuItem churchSymbol = new MenuItem("Church symbol");
        insertSymbol.getItems().addAll(
                classSymbol,
                workSymbol,
                studySymbol,
                billSymbol,
                importSymbol,
                churchSymbol);
        insertMenu.getItems().add(insertSymbol);

        menuBar.getMenus().addAll(fileMenu, addMenu, insertMenu);

        return menuBar;
    }

    public VBox createLeftPane() {

        VBox leftPaneVBox = new VBox();
        leftPaneVBox.setPadding(new Insets(INSET_SIZE));
        leftPaneVBox.setSpacing(10);


        Label filterLabel = new Label("Filter");
        Font filterFont = new Font("Arial", 40);

        Font font = new Font("Arial", 24);
        filterLabel.setFont(filterFont);

        Font font2 = new Font("Arial", 14);


        CheckBox classFilter = new CheckBox("Class Schedule");
        classFilter.setFont(font2);
        classFilter.setStyle("-fx-color: pink;");
        classFilter.setSelected(true);
        Type classType = new Type("Class Schedule", null, true);
        classFilter.selectedProperty().bindBidirectional(classType.isVisibleProperty());
        classFilter.setOnMouseClicked(
                e -> System.out.println(classType.getName() + " isVisible: " + classType.isVisible())
        );

        CheckBox workFilter = new CheckBox("Work Schedule");
        workFilter.setStyle("-fx-color: pink;");
        workFilter.setFont(font2);
        workFilter.setSelected(true);
        Type workType = new Type("Work Schedule", null, true);
        workFilter.selectedProperty().bindBidirectional(workType.isVisibleProperty());
        workFilter.setOnMouseClicked(
                e -> System.out.println(workType.getName() + " isVisible: " + workType.isVisible())
        );

        CheckBox customSchedFilter = new CheckBox("Custom Schedule");
        customSchedFilter.setFont(font2);
        customSchedFilter.setStyle("-fx-color: pink;");
        customSchedFilter.setSelected(true);
        Type customSchedType= new Type("Custom Schedule", null, true);
        customSchedFilter.selectedProperty().bindBidirectional(customSchedType.isVisibleProperty());
        customSchedFilter.setOnMouseClicked(
                e -> System.out.println(customSchedType.getName() + " isVisible: " + customSchedType.isVisible())
        );


        CheckBox assignmentFilter = new CheckBox("Assignments Due");
        assignmentFilter.setFont(font2);
        assignmentFilter.setStyle("-fx-color: pink;");
        assignmentFilter.setSelected(true);
        Type assignmentType = new Type("Assignments Due", null, true);
        assignmentFilter.selectedProperty().bindBidirectional(assignmentType.isVisibleProperty());
        assignmentFilter.setOnMouseClicked(
                e -> System.out.println(assignmentType.getName() + " isVisible: " + assignmentType.isVisible())
        );


        CheckBox billFilter = new CheckBox("Bill Due");
        billFilter.setFont(font2);
        billFilter.setStyle("-fx-color: pink;");
        billFilter.setSelected(true);
        Type billType = new Type("Bill Due", null, true);
        billFilter.selectedProperty().bindBidirectional(billType.isVisibleProperty());
        billFilter.setOnMouseClicked(
                e -> System.out.println(billType.getName() + " isVisible: " + billType.isVisible())
        );


        CheckBox customEventFilter = new CheckBox("Custom Events");
        customEventFilter.setFont(font2);
        customEventFilter.setStyle("-fx-color: pink;");
        customEventFilter.setSelected(true);
        Type customEventType = new Type("Custom Events", null, true);
        customEventFilter.selectedProperty().bindBidirectional(customEventType.isVisibleProperty());
        customEventFilter.setOnMouseClicked(
                e -> System.out.println(customEventType.getName() + " isVisible: " + customEventType.isVisible())
        );


        leftPaneVBox.getChildren().addAll(filterLabel, classFilter, workFilter, customSchedFilter, assignmentFilter, billFilter, customEventFilter);

        return leftPaneVBox;

    }

    public VBox createCenterPane() {

        // Initialize Layouts
        VBox centerPaneVBox = new VBox();
        HBox labelAndControl = new HBox();
        monthViewGridPane = new GridPane();
        centerPaneVBox.setPadding(new Insets(INSET_SIZE));
        centerPaneVBox.setSpacing(10);
        labelAndControl.setSpacing(10);
        labelAndControl.setMaxWidth(GRID_PANE_NODE_WIDTH * 7);

        // Initialize Fonts
        Font font = new Font("Arial", 22);
        Font font2 = new Font("Arial", 40);
        Font font3 = new Font("Arial", 45);


        // Instantiate monthLabel
        Label monthLabel = new Label(months[0].toString());
        currentMonthIndex = 0;
        monthLabel.setPrefSize(700, 100);
        monthLabel.setAlignment(Pos.CENTER);
        monthLabel.setFont(font2);



        // Instantiate Arrow Buttons
        Button leftArrowButton = new Button("<");
        leftArrowButton.setFont(font3);
        leftArrowButton.setPrefSize(150, 100);
        leftArrowButton.setStyle("-fx-background-color: pink;");
        leftArrowButton.setOnAction(
                e -> {
                    if (currentMonthIndex == 0) {
                        monthLabel.setText(months[months.length - 1].toString());
                        currentMonthIndex = months.length - 1;

                    } else {
                        monthLabel.setText(months[--currentMonthIndex].toString());
                    }
                    createGridPane(months[currentMonthIndex]);
                }
        );


        Button rightArrowButton = new Button(">");
        rightArrowButton.setStyle("-fx-background-color: pink;");
        rightArrowButton.setFont(font3);
        rightArrowButton.setPrefSize(150, 100);
        rightArrowButton.setOnAction(
                e -> {
                    if (currentMonthIndex == months.length - 1) {
                        monthLabel.setText(months[0].toString());
                        currentMonthIndex = 0;
                    } else {
                        monthLabel.setText(months[++currentMonthIndex].toString());
                    }
                    createGridPane(months[currentMonthIndex]);
                }
        );


        // Add nodes and data to layouts
        labelAndControl.getChildren().addAll(leftArrowButton, monthLabel, rightArrowButton);
        createGridPane(months[0]);
        centerPaneVBox.getChildren().addAll(labelAndControl, monthViewGridPane);


        return centerPaneVBox;
    }

    private void createGridPane(MonthInfo month) {
        Font font = new Font("Arial", 22);
        monthViewGridPane.getChildren().clear();
        monthViewGridPane.setGridLinesVisible(false);
        monthViewGridPane.setGridLinesVisible(true);


        // Create labels for days of the week.
        Label sundayLabel = new Label("Sunday");
        sundayLabel.setPrefSize(GRID_PANE_NODE_WIDTH, GRID_PANE_NODE_HEIGHT);
        sundayLabel.setAlignment(Pos.CENTER);
        sundayLabel.setFont(font);
        Label mondayLabel = new Label("Monday");
        mondayLabel.setPrefSize(GRID_PANE_NODE_WIDTH, GRID_PANE_NODE_HEIGHT);
        mondayLabel.setAlignment(Pos.CENTER);
        mondayLabel.setFont(font);
        Label tuesdayLabel = new Label("Tuesday");
        tuesdayLabel.setPrefSize(GRID_PANE_NODE_WIDTH, GRID_PANE_NODE_HEIGHT);
        tuesdayLabel.setAlignment(Pos.CENTER);
        tuesdayLabel.setFont(font);
        Label wednesdayLabel = new Label("Wednesday");
        wednesdayLabel.setPrefSize(GRID_PANE_NODE_WIDTH, GRID_PANE_NODE_HEIGHT);
        wednesdayLabel.setAlignment(Pos.CENTER);
        wednesdayLabel.setAlignment(Pos.CENTER);
        wednesdayLabel.setFont(font);
        Label thursdayLabel = new Label("Thursday");
        thursdayLabel.setPrefSize(GRID_PANE_NODE_WIDTH, GRID_PANE_NODE_HEIGHT);
        thursdayLabel.setAlignment(Pos.CENTER);
        thursdayLabel.setFont(font);
        Label fridayLabel = new Label("Friday");
        fridayLabel.setPrefSize(GRID_PANE_NODE_WIDTH, GRID_PANE_NODE_HEIGHT);
        fridayLabel.setAlignment(Pos.CENTER);
        fridayLabel.setFont(font);
        Label saturdayLabel = new Label("Saturday");
        saturdayLabel.setPrefSize(GRID_PANE_NODE_WIDTH, GRID_PANE_NODE_HEIGHT);
        saturdayLabel.setAlignment(Pos.CENTER);
        saturdayLabel.setFont(font);

        monthViewGridPane.add(sundayLabel, 0, 0);
        monthViewGridPane.add(mondayLabel, 1, 0);
        monthViewGridPane.add(tuesdayLabel, 2, 0);
        monthViewGridPane.add(wednesdayLabel, 3, 0);
        monthViewGridPane.add(thursdayLabel, 4, 0);
        monthViewGridPane.add(fridayLabel, 5, 0);
        monthViewGridPane.add(saturdayLabel, 6, 0);


        // Add each day to the GridPane, and set up Event Handler
        for (int i = 0; i < month.getWeeksList().size(); i++) {
            WeekInfo week = month.getWeek(i);
            for (int j = 0; j < week.getDays().size(); j++) {
                DayInfo day = week.getDays().get(j);

                Label tmp = new Label();
                tmp.setPrefSize(GRID_PANE_NODE_WIDTH, GRID_PANE_NODE_HEIGHT);
                tmp.setPadding(new Insets(5));
                tmp.setFont(font);
                tmp.setText(String.valueOf(day.getDate().getDayOfMonth()));
                tmp.setAlignment(Pos.TOP_LEFT);
                tmp.setUserData(day);

                tmp.setOnMouseClicked(
                        e -> {
                            DayInfo tempDay = (DayInfo)tmp.getUserData();
                            dayLabel.setText(tempDay.toString());
                        }
                );
                // Monday == 1, Sunday == 7. To get Sunday to be at index 0, use the value % 7
                monthViewGridPane.add(tmp, day.getDate().getDayOfWeek().getValue() % 7, i + 1);
            }
        }
    }

    public VBox createRightPane() {

        VBox rightPaneVBox = new VBox();
        rightPaneVBox.setPadding(new Insets(INSET_SIZE));
        rightPaneVBox.setSpacing(10);
        rightPaneVBox.setPadding(new Insets(10));
        GridPane gridPaneDetailView = new GridPane();
        gridPaneDetailView.setGridLinesVisible(true);
        HBox controlHBox = new HBox();
        controlHBox.setSpacing(10);
        Button leftArrowButton = new Button("<");
        leftArrowButton.setStyle("-fx-background-color: pink;");
        leftArrowButton.setPrefSize(30, 60);
        Font font2 = new Font("Arial", 20);
        leftArrowButton.setFont(font2);

        dayLabel = new Label("Sunday 31st");
        dayLabel.setAlignment(Pos.CENTER);
        dayLabel.setPrefHeight(GRID_PANE_NODE_HEIGHT);
        Font font = new Font("Arial", 12);
        dayLabel.setFont(font);
        Button rightArrowButton = new Button(">");
        rightArrowButton.setStyle("-fx-background-color: pink;");
        rightArrowButton.setFont(font2);
        rightArrowButton.setPrefSize(30, 60);


        controlHBox.getChildren().addAll(leftArrowButton, dayLabel, rightArrowButton);
        gridPaneDetailView.add(controlHBox, 0, 0);

        rightPaneVBox.getChildren().add(gridPaneDetailView);

        return rightPaneVBox;
    }
}
