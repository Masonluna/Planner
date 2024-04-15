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
        Rectangle defaultClassSymbol = new Rectangle(10, 10, Color.BLUE);
        Type classType = new Type(Tag.CLASS, null, defaultClassSymbol, true);
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
        Type workType = new Type(Tag.WORK, null, defaultWorkSymbol, true);
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
        Type assignmentType = new Type(Tag.ASSIGNMENT, null, defaultAssignmentSymbol,true);
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
        Type billType = new Type(Tag.BILL, null, defaultBillSymbol,true);
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
        Type customEventType = new Type(Tag.CUSTOM, null, defaultCustomEventSymbol, true);
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
        Label monthLabel = new Label(months[date.getMonthValue() - 1].toString());
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
                        dayHBox.getChildren().add(new ImageView(type.getSymbol()));
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
            selectedDayHBox = (DayHBox) monthViewGridPane.getChildren().get(
                    monthViewGridPane.getChildren().indexOf(selectedDayHBox) - 1);
            selectedDayInfo = selectedDayHBox.getDayInfo();
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



        controlHBox.getChildren().addAll(leftArrowButton, dayLabel, rightArrowButton);
        gridPaneDetailView.add(controlHBox, 0, 0);


        HBox sixAmHBox = new HBox();
        sixAmHBox.setPrefSize(200,50);
        Label sixAmLbl = new Label("6AM");
        sixAmHBox.getChildren().addAll(sixAmLbl);
        gridPaneDetailView.add(sixAmHBox, 0,1);

        HBox sevenAmHBox = new HBox();
        sevenAmHBox.setPrefSize(200,50);
        Label sevenAmLbl = new Label("7AM");
        sevenAmHBox.getChildren().addAll(sevenAmLbl);
        gridPaneDetailView.add( sevenAmHBox, 0,2);

        HBox eightAmHBox = new HBox();
        eightAmHBox.setPrefSize(200,50);
        Label eightAmLbl = new Label("8AM");
        eightAmHBox.getChildren().addAll(eightAmLbl);
        gridPaneDetailView.add(eightAmHBox, 0,3);

        HBox nineAmHBox = new HBox();
        nineAmHBox.setPrefSize(200,50);
        Label nineAmLbl = new Label("9AM");
        nineAmHBox.getChildren().addAll(nineAmLbl);
        gridPaneDetailView.add(nineAmHBox, 0,4);

        HBox tenAmHBox = new HBox();
        tenAmHBox.setPrefSize(200,50);
        Label tenAmLbl = new Label("10AM");
        tenAmHBox.getChildren().addAll(tenAmLbl);
        gridPaneDetailView.add(tenAmHBox, 0,5);

        HBox elevenAmHBox = new HBox();
        elevenAmHBox.setPrefSize(200,50);
        Label elevenAmLbl = new Label("11AM");
        elevenAmHBox.getChildren().addAll(elevenAmLbl);
        gridPaneDetailView.add(elevenAmHBox, 0,6);

        HBox  twelvePmHBox = new HBox();
        twelvePmHBox.setPrefSize(200,50);
        Label twelvePmLbl = new Label("12PM");
        twelvePmHBox.getChildren().addAll(twelvePmLbl);
        gridPaneDetailView.add(twelvePmHBox, 0,7);

        HBox onePmHBox = new HBox();
        onePmHBox.setPrefSize(200,50);
        Label onePmLbl = new Label("1PM");
        onePmHBox.getChildren().addAll(onePmLbl);
        gridPaneDetailView.add(onePmHBox, 0,8);

        HBox twoPmHBox = new HBox();
        twoPmHBox.setPrefSize(200,50);
        Label twoPmLbl = new Label("2PM");
        twoPmHBox.getChildren().addAll(twoPmLbl);
        gridPaneDetailView.add(twoPmHBox, 0,9);

        HBox threePmHBox = new HBox();
        threePmHBox.setPrefSize(200,50);
        Label threePmLbl = new Label("3PM");
        threePmHBox.getChildren().addAll(threePmLbl);
        gridPaneDetailView.add(threePmHBox, 0,10);

        HBox fourPmHBox = new HBox();
        fourPmHBox.setPrefSize(200,50);
        Label fourPmLbl = new Label("4PM");
        fourPmHBox.getChildren().addAll(fourPmLbl);
        gridPaneDetailView.add(fourPmHBox, 0,11);

        HBox fivePmHBox = new HBox();
        fivePmHBox.setPrefSize(200,50);
        Label fivePmLbl = new Label("5PM");
        fivePmHBox.getChildren().addAll(fivePmLbl);
        gridPaneDetailView.add(fivePmHBox, 0,12);

        HBox sixPmHBox = new HBox();
        sixPmHBox.setPrefSize(200,50);
        Label sixPmLbl = new Label("6PM");
        sixPmHBox.getChildren().addAll(sixPmLbl);
        gridPaneDetailView.add(sixPmHBox, 0,13);


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
