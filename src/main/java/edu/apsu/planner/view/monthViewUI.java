package edu.apsu.planner.view;

import edu.apsu.planner.AddEventHandler;
import edu.apsu.planner.DayHBox;
import edu.apsu.planner.data.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;

public class monthViewUI extends Application {

    // Size Constants
    private final int GRID_PANE_NODE_HEIGHT = 100;
    private final int GRID_PANE_NODE_WIDTH = 130;
    private final int INSET_SIZE = 15;


    // Data instances
    private final LocalDate date = LocalDate.now();
    private MonthInfo[] months;
    private int currentMonthIndex;

    // FX Node Instances
    private Label monthLabel;
    private GridPane monthViewGridPane;
    private Label dayLabel;
    private DayHBox selectedDayHBox;
    private DayInfo selectedDayInfo;
    private GridPane gridPaneDetailView;

    private Type[] types = new Type[5];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        // Set up MonthInfo data
        months = new MonthInfo[12];
        for (int i = 0; i < months.length; i++) {
            months[i] = new MonthInfo(2024, Month.of(i + 1));
        }

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #B7B7B7;");
        root.setPrefSize(1375, 720);


        root.setTop(creatMenuBar());
        root.setLeft(createLeftPane());
        root.setCenter(createCenterPane());
        root.setRight(createRightPane());


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

        MenuItem addSchedule = new MenuItem("Add  Schedule");
        addSchedule.setOnAction(event->{
            choiceBoxDemo.popUp();
        });
       // MenuItem addWorkSchedule = new MenuItem("Add Work Schedule");
       // MenuItem addCustomSchedule = new MenuItem("Add Custom Schedule");
        SeparatorMenuItem separatorMenuItem2 = new SeparatorMenuItem();
        //MenuItem addAssignmentDueDate = new MenuItem("Add Assignment Due Date");
        // MenuItem addBillDueDate = new MenuItem("Add Bill Due Date");
        MenuItem addCustomEvent = new MenuItem("Add Event");
        AddEventHandler addEventHandler = new AddEventHandler(months, types, this);
        addCustomEvent.setOnAction(addEventHandler);
        addMenu.getItems().addAll(
                addSchedule,
                //addWorkSchedule,
               // addCustomSchedule,
                separatorMenuItem2,
               // addAssignmentDueDate,
                //addBillDueDate,
                addCustomEvent);

        Menu insertMenu = new Menu("Insert");

        Menu insertSymbol = new Menu("Insert Symbol");

        MenuItem classSymbol = new MenuItem("Class symbol");
        classSymbol.setOnAction(e->{

        });
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

        Menu viewMenu = new Menu("View");
        MenuItem weekViewMenu = new MenuItem("Week View");
        MenuItem monthViewMenu = new MenuItem("Month View");
        viewMenu.getItems().addAll(weekViewMenu, monthViewMenu);


        menuBar.getMenus().addAll(fileMenu, addMenu, insertMenu, viewMenu);

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
        Rectangle defaultClassSymbol = new Rectangle(10, 10, Color.BLUE);
       Image classSymbolImage =  Type.loadImageIntoLabel("/edu/apsu/planner/symbolPNGResource/icons8-class-64.png");
        Type classType = new Type(Tag.CLASS, classSymbolImage, defaultClassSymbol, true);
        types[0] = classType;
        classFilter.selectedProperty().bindBidirectional(classType.isVisibleProperty());
        classFilter.setOnMouseClicked(
                e -> createGridPane(months[currentMonthIndex])
        );

        CheckBox workFilter = new CheckBox("Work Schedule");
        workFilter.setStyle("-fx-color: pink;");
        workFilter.setFont(font2);
        workFilter.setSelected(true);
        Rectangle defaultWorkSymbol = new Rectangle(10, 10, Color.FORESTGREEN);
        Image workSymbolImage =  Type.loadImageIntoLabel("/edu/apsu/planner/symbolPNGResource/icons8-work-100.png");
        Type workType = new Type(Tag.WORK, workSymbolImage, defaultWorkSymbol, true);
        types[1] = workType;
        workFilter.selectedProperty().bindBidirectional(workType.isVisibleProperty());
        workFilter.setOnMouseClicked(
                e -> createGridPane(months[currentMonthIndex])
        );


        CheckBox assignmentFilter = new CheckBox("Assignments Due");
        assignmentFilter.setFont(font2);
        assignmentFilter.setStyle("-fx-color: pink;");
        assignmentFilter.setSelected(true);
        Rectangle defaultAssignmentSymbol = new Rectangle(10, 10, Color.PURPLE);
        Image assignmentDueSymbolImage =  Type.loadImageIntoLabel("/edu/apsu/planner/symbolPNGResource/icons8-study-100.png");
        Type assignmentType = new Type(Tag.ASSIGNMENT, assignmentDueSymbolImage, defaultAssignmentSymbol,true);
        types[2] = assignmentType;
        assignmentFilter.selectedProperty().bindBidirectional(assignmentType.isVisibleProperty());
        assignmentFilter.setOnMouseClicked(
                e -> createGridPane(months[currentMonthIndex])
        );


        CheckBox billFilter = new CheckBox("Bill Due");
        billFilter.setFont(font2);
        billFilter.setStyle("-fx-color: pink;");
        billFilter.setSelected(true);
        Rectangle defaultBillSymbol = new Rectangle(10, 10, Color.RED);
        Image billDueSymbolImage =  Type.loadImageIntoLabel("/edu/apsu/planner/symbolPNGResource/icons8-bill-64.png");
        Type billType = new Type(Tag.BILL, billDueSymbolImage, defaultBillSymbol,true);
        types[3] = billType;
        billFilter.selectedProperty().bindBidirectional(billType.isVisibleProperty());
        billFilter.setOnMouseClicked(
                e -> createGridPane(months[currentMonthIndex])
        );


        CheckBox customEventFilter = new CheckBox("Custom Events");
        customEventFilter.setFont(font2);
        customEventFilter.setStyle("-fx-color: pink;");
        customEventFilter.setSelected(true);
        Rectangle defaultCustomEventSymbol = new Rectangle(10, 10, Color.AQUA);
        Image customSymbolImage =  Type.loadImageIntoLabel("/edu/apsu/planner/symbolPNGResource/icons8-important-100.png");
        Type customEventType = new Type(Tag.CUSTOM, customSymbolImage, defaultCustomEventSymbol, true);
        types[4] = customEventType;
        customEventFilter.selectedProperty().bindBidirectional(customEventType.isVisibleProperty());
        customEventFilter.setOnMouseClicked(
                e -> createGridPane(months[currentMonthIndex])
        );




        leftPaneVBox.getChildren().addAll(filterLabel, classFilter, workFilter, assignmentFilter, billFilter, customEventFilter);

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
        monthLabel = new Label(months[date.getMonthValue() - 1].toString());
        currentMonthIndex = date.getMonthValue() - 1;
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
        createGridPane(months[currentMonthIndex]);
        centerPaneVBox.getChildren().addAll(labelAndControl, monthViewGridPane);


        return centerPaneVBox;
    }

    public void createGridPane(MonthInfo month) {

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

                DayHBox dayHBox = new DayHBox(day);
                dayHBox.setPadding(new Insets(2));
                Label tmp = new Label();
                tmp.setPrefSize(GRID_PANE_NODE_WIDTH, GRID_PANE_NODE_HEIGHT);
                tmp.setPadding(new Insets(5));
                tmp.setFont(font);
                tmp.setText(String.valueOf(day.getDate().getDayOfMonth()));
                tmp.setAlignment(Pos.TOP_LEFT);
                tmp.setUserData(day);

                dayHBox.setOnMouseClicked( event -> {
                        selectedDayHBox = (DayHBox) event.getSource();
                        selectedDayInfo = selectedDayHBox.getDayInfo();
                        dayLabel.setText(selectedDayInfo.toString());
                });
                dayHBox.getChildren().add(tmp);
                HashSet<Type> typesToAdd = findTagsToAdd(day);
                dayHBox.setAlignment(Pos.TOP_LEFT);

                for (Type type : typesToAdd) {
                    if (type.getSymbol() != null) {
                        ImageView imageView = new ImageView(type.getSymbol());
                        // Resize the image (adjust the dimensions as needed)
                        imageView.setFitWidth(40); // Set the width to 50 pixels
                        imageView.setFitHeight(40); // Set the height to 50 pixels
                        dayHBox.getChildren().add(imageView);
                    } else {
                        dayHBox.getChildren().add(type.getDefaultSymbol());
                    }
                }

                // Monday == 1, Sunday == 7. To get Sunday to be at index 0, use the value % 7
                monthViewGridPane.add(dayHBox, day.getDate().getDayOfWeek().getValue() % 7, i + 1);
            }
        }
    }

    /**
     * Takes a day and returns a set of Types whose symbols/default rectangles should be drawn on the given day
     * @param day a given DayInfo instance representing a day of the month
     * @return A HashSet of Tags that have a corresponding event in day and are visible.
     */
    private HashSet<Type> findTagsToAdd(DayInfo day) {
        HashSet<Type> typeHashSet = new HashSet<>();
        for (PlannerEvent event : day.getEvents()) {
            if (event.getType().isVisible())
                typeHashSet.add(event.getType());
        }


        return typeHashSet;
    }

    public VBox createRightPane() {

        VBox rightPaneVBox = new VBox();
        rightPaneVBox.setPadding(new Insets(INSET_SIZE));
        rightPaneVBox.setSpacing(10);
        rightPaneVBox.setPadding(new Insets(10));
        gridPaneDetailView = new GridPane();
        gridPaneDetailView.setGridLinesVisible(true);
        HBox controlHBox = new HBox();
        controlHBox.setAlignment(Pos.CENTER);
        controlHBox.setSpacing(10);
        Button leftArrowButton = new Button("<");
        leftArrowButton.setStyle("-fx-background-color: pink;");
        leftArrowButton.setPrefSize(30, 60);
        Font font2 = new Font("Arial", 20);
        leftArrowButton.setFont(font2);
        leftArrowButton.setOnAction( e -> {
            if (selectedDayInfo.getDate().getDayOfMonth() == 1)
            {
                createGridPane(months[--currentMonthIndex]);
                monthLabel.setText(months[currentMonthIndex].toString());
                selectedDayHBox = (DayHBox) monthViewGridPane.getChildren().get(
                        monthViewGridPane.getChildren().size() - 1
                );
                while (selectedDayHBox == null) {
                    selectedDayHBox = (DayHBox) monthViewGridPane.getChildren().get(
                            monthViewGridPane.getChildren().indexOf(selectedDayHBox) - 1);
                }
            } else {
                selectedDayHBox = (DayHBox) monthViewGridPane.getChildren().get(
                        monthViewGridPane.getChildren().indexOf(selectedDayHBox) - 1);
            }
            selectedDayInfo = selectedDayHBox.getDayInfo();
            dayLabel.setText(selectedDayInfo.toString());
        });

        dayLabel = new Label();
        dayLabel.setAlignment(Pos.CENTER);
        dayLabel.setPrefHeight(GRID_PANE_NODE_HEIGHT);
        Font font = new Font("Arial", 12);
        dayLabel.setFont(font);
        Button rightArrowButton = new Button(">");
        rightArrowButton.setStyle("-fx-background-color: pink;");
        rightArrowButton.setFont(font2);
        rightArrowButton.setPrefSize(30, 60);
        rightArrowButton.setOnAction( e -> {
            if (selectedDayInfo.getDate().getDayOfMonth() == selectedDayInfo.getDate().lengthOfMonth())
            {
                createGridPane(months[++currentMonthIndex]);
                monthLabel.setText(months[currentMonthIndex].toString());
                // 8 is the first child node that could possibly be a DayHBox

                selectedDayHBox = (DayHBox) monthViewGridPane.getChildren().get(8);
                while (selectedDayHBox == null) {
                    selectedDayHBox = (DayHBox) monthViewGridPane.getChildren().get(
                            monthViewGridPane.getChildren().indexOf(selectedDayHBox) + 1);
                }
            } else {
                selectedDayHBox = (DayHBox) monthViewGridPane.getChildren().get(
                        monthViewGridPane.getChildren().indexOf(selectedDayHBox) + 1);
            }
            selectedDayInfo = selectedDayHBox.getDayInfo();
            dayLabel.setText(selectedDayInfo.toString());
        });



        controlHBox.getChildren().addAll(leftArrowButton, dayLabel, rightArrowButton);
        gridPaneDetailView.add(controlHBox, 0, 0);



        rightPaneVBox.getChildren().add(gridPaneDetailView);

        return rightPaneVBox;
    }

    public MonthInfo[] getMonths() {
        return months;
    }

    public int getCurrentMonthIndex() {
        return currentMonthIndex;
    }
}
