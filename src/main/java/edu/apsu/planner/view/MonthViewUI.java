package edu.apsu.planner.view;

import edu.apsu.planner.app.PlannerApplication;
import edu.apsu.planner.handler.AddEventHandler;
import edu.apsu.planner.data.*;
import edu.apsu.planner.handler.AddScheduleHandler;
import edu.apsu.planner.handler.FileEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

public class MonthViewUI extends BorderPane {

    // Size Constants
    private final int GRID_PANE_NODE_HEIGHT = 100;
    private final int GRID_PANE_NODE_WIDTH = 130;
    private final int INSET_SIZE = 15;


    // Data instances
    private final LocalDate date = LocalDate.now();
    private int currentMonthIndex;

    // FX Node Instances
    private Label monthLabel;
    private GridPane monthViewGridPane;
    private Label dayLabel;
    private DayFlowPane selectedDayFlowPane;
    private Integer selectedDayFlowPaneRI;
    private Integer selectedDayFlowPaneCI;
    private DayInfo selectedDayInfo;

    private final PlannerApplication app;

    public MonthViewUI(PlannerApplication app) {
        // Set up MonthInfo data
        this.app = app;

        this.setStyle("-fx-background-color: #B7B7B7;");
        this.setPrefSize(1375, 720);


        this.setTop(createMenuBar());
        this.setLeft(createLeftPane());
        this.setCenter(createCenterPane());
        initSelectedDayFlowPane();

        this.setRight(createRightPane());
    }


    public MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #B7B7B7;");

        FileEventHandler fileEventHandler = new FileEventHandler(this.app);
        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New");
        newMenuItem.setOnAction(fileEventHandler);
        MenuItem saveMenuItem = new MenuItem("Save");
        saveMenuItem.setOnAction(fileEventHandler);
        MenuItem openMenuItem = new MenuItem("Open");
        openMenuItem.setOnAction(fileEventHandler);
        MenuItem savePDFMenuItem = new MenuItem("Export to PDF");
        //savePDFMenuItem.setOnAction(e->{
        //    saveAsPDF();
        //});
        savePDFMenuItem.setOnAction(fileEventHandler);
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(fileEventHandler);
        fileMenu.getItems().addAll(
                newMenuItem,
                new SeparatorMenuItem(),
                saveMenuItem,
                openMenuItem,
                savePDFMenuItem,
                new SeparatorMenuItem(),
                exitMenuItem);

        Menu addMenu = new Menu("Add");

        MenuItem addSchedule = new MenuItem("Add Schedule");
        AddScheduleHandler addScheduleHandler = new AddScheduleHandler(app);
        addSchedule.setOnAction(addScheduleHandler);
        SeparatorMenuItem separatorMenuItem2 = new SeparatorMenuItem();
        MenuItem addCustomEvent = new MenuItem("Add Event");
        AddEventHandler addEventHandler = new AddEventHandler(app);
        addCustomEvent.setOnAction(addEventHandler);
        addMenu.getItems().addAll(
                addSchedule,
                separatorMenuItem2,
                addCustomEvent
        );

        Menu insertMenu = new Menu("Insert");
        Menu insertSymbol = new Menu("Insert Symbol");
        MenuItem classSymbol = new MenuItem("Class symbol");
        classSymbol.setOnAction(e -> {
            if (!app.getTypes()[0].isSymbolVisible()) {
                app.getTypes()[0].setSymbolVisible(true);
                app.updateUI();
            } else {
                app.getTypes()[0].setSymbolVisible(false);
                app.updateUI();
            }
        });
        MenuItem workSymbol = new MenuItem("Work symbol");
        workSymbol.setOnAction(e -> {
            if (!app.getTypes()[1].isSymbolVisible()) {
                app.getTypes()[1].setSymbolVisible(true);
                app.updateUI();
            } else {
                app.getTypes()[1].setSymbolVisible(false);
                app.updateUI();
            }
        });
        MenuItem assignmentSymbol = new MenuItem("Assignment symbol");
        assignmentSymbol.setOnAction(e -> {
            if (!app.getTypes()[2].isSymbolVisible()) {
                app.getTypes()[2].setSymbolVisible(true);
                app.updateUI();
            } else {
                app.getTypes()[2].setSymbolVisible(false);
                app.updateUI();
            }
        });
        MenuItem billSymbol = new MenuItem("Bill symbol");
        billSymbol.setOnAction(e -> {
            if (!app.getTypes()[3].isSymbolVisible()) {
                app.getTypes()[3].setSymbolVisible(true);
                app.updateUI();
            } else {
                app.getTypes()[3].setSymbolVisible(false);
                app.updateUI();
            }
        });
        MenuItem customSymbol = new MenuItem("Custom symbol");
        customSymbol.setOnAction(e -> {
            if (!app.getTypes()[4].isSymbolVisible()) {
                app.getTypes()[4].setSymbolVisible(true);
                app.updateUI();
            } else {
                app.getTypes()[4].setSymbolVisible(false);
                app.updateUI();
            }
        });
        insertSymbol.getItems().addAll(
                classSymbol,
                workSymbol,
                assignmentSymbol,
                billSymbol,
                customSymbol);
        insertMenu.getItems().add(insertSymbol);

        Menu viewMenu = new Menu("View");
        MenuItem weekViewMenu = new MenuItem("Week View");
        weekViewMenu.setOnAction(e -> app.switchScene(app.weekViewScene));
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


        filterLabel.setFont(filterFont);

        Font font2 = new Font("Arial", 14);


        CheckBox classFilter = new CheckBox("Class Schedule");
        classFilter.setFont(font2);
        classFilter.setStyle("-fx-color: pink;");
        classFilter.setSelected(true);
        classFilter.selectedProperty().bindBidirectional(app.getTypes()[0].isVisibleProperty());
        classFilter.setOnMouseClicked(e -> app.updateUI());

        CheckBox workFilter = new CheckBox("Work Schedule");
        workFilter.setStyle("-fx-color: pink;");
        workFilter.setFont(font2);
        workFilter.setSelected(true);
        workFilter.selectedProperty().bindBidirectional(app.getTypes()[1].isVisibleProperty());
        workFilter.setOnMouseClicked(e -> app.updateUI());


        CheckBox assignmentFilter = new CheckBox("Assignments Due");
        assignmentFilter.setFont(font2);
        assignmentFilter.setStyle("-fx-color: pink;");
        assignmentFilter.setSelected(true);
        assignmentFilter.selectedProperty().bindBidirectional(app.getTypes()[2].isVisibleProperty());
        assignmentFilter.setOnMouseClicked(e -> app.updateUI());


        CheckBox billFilter = new CheckBox("Bill Due");
        billFilter.setFont(font2);
        billFilter.setStyle("-fx-color: pink;");
        billFilter.setSelected(true);
        billFilter.selectedProperty().bindBidirectional(app.getTypes()[3].isVisibleProperty());
        billFilter.setOnMouseClicked(e -> app.updateUI());


        CheckBox customEventFilter = new CheckBox("Custom Events");
        customEventFilter.setFont(font2);
        customEventFilter.setStyle("-fx-color: pink;");
        customEventFilter.setSelected(true);
        customEventFilter.selectedProperty().bindBidirectional(app.getTypes()[4].isVisibleProperty());
        customEventFilter.setOnMouseClicked(e -> app.updateUI());


        leftPaneVBox.getChildren().addAll(filterLabel, classFilter, workFilter, assignmentFilter, billFilter, customEventFilter);

        return leftPaneVBox;

    }

    public VBox createCenterPane() {

        // Initialize Layouts
        VBox centerPaneVBox = new VBox();
        HBox labelAndControl = new HBox();
        monthViewGridPane = new GridPane();
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100.0 / 7);
        monthViewGridPane.getColumnConstraints().addAll(
                columnConstraints, columnConstraints, columnConstraints,
                columnConstraints, columnConstraints, columnConstraints, columnConstraints);

        centerPaneVBox.setPadding(new Insets(INSET_SIZE));
        centerPaneVBox.setSpacing(10);
        centerPaneVBox.setAlignment(Pos.CENTER);
        labelAndControl.setSpacing(10);
        labelAndControl.setMaxWidth(GRID_PANE_NODE_WIDTH * 7);
        labelAndControl.setAlignment(Pos.CENTER);

        // Initialize Fonts
        Font font2 = new Font("Arial", 40);
        Font font3 = new Font("Arial", 45);


        // Instantiate monthLabel
        monthLabel = new Label(app.getMonths()[date.getMonthValue() - 1].toString());
        currentMonthIndex = date.getMonthValue() - 1;
        monthLabel.setPrefSize(700, 100);
        monthLabel.setAlignment(Pos.CENTER);
        monthLabel.setFont(font2);


        // Instantiate Arrow Buttons
        Button leftArrowButton = getLeftArrowButton(font3);


        Button rightArrowButton = getRightArrowButton(font3);


        // Add nodes and data to layouts
        labelAndControl.getChildren().addAll(leftArrowButton, monthLabel, rightArrowButton);
        createGridPane(app.getMonths()[currentMonthIndex]);
        centerPaneVBox.getChildren().addAll(labelAndControl, monthViewGridPane);


        return centerPaneVBox;
    }

    private Button getRightArrowButton(Font font3) {
        Button rightArrowButton = new Button(">");
        rightArrowButton.setStyle("-fx-background-color: pink;");
        rightArrowButton.setFont(font3);
        rightArrowButton.setPrefSize(150, 100);
        rightArrowButton.setOnAction(
                e -> {
                    if (currentMonthIndex == app.getMonths().length - 1) {
                        monthLabel.setText(app.getMonths()[0].toString());
                        currentMonthIndex = 0;
                    } else {
                        monthLabel.setText(app.getMonths()[++currentMonthIndex].toString());
                    }
                    createGridPane(app.getMonths()[currentMonthIndex]);
                    initSelectedDayFlowPane();
                    this.setRight(createRightPane());
                }
        );
        return rightArrowButton;
    }

    private Button getLeftArrowButton(Font font3) {
        Button leftArrowButton = new Button("<");
        leftArrowButton.setFont(font3);
        leftArrowButton.setPrefSize(150, 100);
        leftArrowButton.setStyle("-fx-background-color: pink;");
        leftArrowButton.setOnAction(
                e -> {
                    if (currentMonthIndex == 0) {
                        monthLabel.setText(app.getMonths()[app.getMonths().length - 1].toString());
                        currentMonthIndex = app.getMonths().length - 1;

                    } else {
                        monthLabel.setText(app.getMonths()[--currentMonthIndex].toString());
                    }
                    createGridPane(app.getMonths()[currentMonthIndex]);
                    initSelectedDayFlowPane();
                    this.setRight(createRightPane());
                }
        );
        return leftArrowButton;
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

                DayFlowPane dayFlowPane = new DayFlowPane(day);
                dayFlowPane.setPrefSize(GRID_PANE_NODE_WIDTH, GRID_PANE_NODE_HEIGHT);
                dayFlowPane.setAlignment(Pos.TOP_RIGHT);
                dayFlowPane.setPadding(new Insets(2));
                dayFlowPane.setHgap(5);
                dayFlowPane.setVgap(2);
                Label tmp = new Label();
                tmp.setPrefSize(40, 10);
                tmp.setPadding(new Insets(5));
                tmp.setFont(font);
                tmp.setText(String.valueOf(day.getDate().getDayOfMonth()));
                tmp.setAlignment(Pos.TOP_LEFT);
                tmp.setUserData(day);

                dayFlowPane.setOnMouseClicked(event -> {
                    selectedDayFlowPane = (DayFlowPane) event.getSource();
                    selectedDayFlowPaneRI = GridPane.getRowIndex(selectedDayFlowPane);
                    selectedDayFlowPaneCI = GridPane.getColumnIndex(selectedDayFlowPane);
                    selectedDayInfo = selectedDayFlowPane.getDayInfo();
                    dayLabel.setText(selectedDayInfo.toString());
                    this.setRight(createRightPane());
                });
                dayFlowPane.getChildren().add(tmp);
                HashSet<Tag> tagsToAdd = findTagsToAdd(day);
                ArrayList<Type> typesToAdd = new ArrayList<>();
                for (Type type : app.getTypes()) {
                    if (tagsToAdd.contains(type.getTag()) && type.isVisible()) {
                        typesToAdd.add(type);
                    }
                }
                dayFlowPane.setAlignment(Pos.TOP_LEFT);

                for (Type type : typesToAdd) {
                    if (type.isSymbolVisible()) {
                        ImageView imageView = new ImageView(type.getSymbol());
                        // Resize the image (adjust the dimensions as needed)
                        imageView.setFitWidth(40); // Set the width to 40 pixels
                        imageView.setFitHeight(40); // Set the height to 40 pixels
                        dayFlowPane.getChildren().add(imageView);
                    } else {
                        dayFlowPane.getChildren().add(type.copyDefaultSymbol());
                    }
                }

                // Monday == 1, Sunday == 7. To get Sunday to be at index 0, use the value % 7
                monthViewGridPane.add(dayFlowPane, day.getDate().getDayOfWeek().getValue() % 7, i + 1);
            }
        }
    }

    /**
     * Takes a day and returns a set of Types whose symbols/default rectangles should be drawn on the given day
     *
     * @param day a given DayInfo instance representing a day of the month
     * @return A HashSet of Tags that have a corresponding event in day and are visible.
     */
    private HashSet<Tag> findTagsToAdd(DayInfo day) {
        HashSet<Tag> typeHashSet = new HashSet<>();
        for (PlannerEvent event : day.getEvents()) {
            typeHashSet.add(event.getTag());
        }
        return typeHashSet;
    }

    public VBox createRightPane() {
        selectedDayInfo = selectedDayFlowPane.getDayInfo();
        VBox rightPaneVBox = new VBox();
        rightPaneVBox.setPadding(new Insets(INSET_SIZE));
        rightPaneVBox.setSpacing(10);
        HBox controlHBox = new HBox();
        controlHBox.setPrefWidth(200);
        controlHBox.setPrefHeight(100);
        controlHBox.setAlignment(Pos.CENTER);
        controlHBox.setSpacing(10);
        Button leftArrowButton = new Button("<");
        leftArrowButton.setStyle("-fx-background-color: pink;");
        leftArrowButton.setPrefSize(30, 60);
        Font font2 = new Font("Arial", 20);
        leftArrowButton.setFont(font2);
        leftArrowButton.setOnAction(e -> {
            if (selectedDayInfo.getDate().getDayOfMonth() == 1) {
                createGridPane(app.getMonths()[--currentMonthIndex]);
                monthLabel.setText(app.getMonths()[currentMonthIndex].toString());
                selectedDayFlowPane = (DayFlowPane) monthViewGridPane.getChildren().get(
                        monthViewGridPane.getChildren().size() - 1
                );
                selectedDayFlowPaneRI = GridPane.getRowIndex(selectedDayFlowPane);
                selectedDayFlowPaneCI = GridPane.getColumnIndex(selectedDayFlowPane);
                while (selectedDayFlowPane == null) {
                    selectedDayFlowPane = (DayFlowPane) monthViewGridPane.getChildren().get(
                            monthViewGridPane.getChildren().indexOf(selectedDayFlowPane) - 1);
                    selectedDayFlowPaneRI = GridPane.getRowIndex(selectedDayFlowPane);
                    selectedDayFlowPaneCI = GridPane.getColumnIndex(selectedDayFlowPane);
                }
            } else {
                selectedDayFlowPane = (DayFlowPane) monthViewGridPane.getChildren().get(
                        monthViewGridPane.getChildren().indexOf(selectedDayFlowPane) - 1);
                selectedDayFlowPaneRI = GridPane.getRowIndex(selectedDayFlowPane);
                selectedDayFlowPaneCI = GridPane.getColumnIndex(selectedDayFlowPane);
            }
            selectedDayInfo = selectedDayFlowPane.getDayInfo();
            dayLabel.setText(selectedDayInfo.toString());
            app.updateUI();
        });


        dayLabel = new Label(selectedDayInfo.toString());
        dayLabel.setAlignment(Pos.CENTER);
        dayLabel.setPrefHeight(GRID_PANE_NODE_HEIGHT);
        dayLabel.setPrefWidth(100);
        dayLabel.setWrapText(true);
        dayLabel.textAlignmentProperty().set(TextAlignment.CENTER);
        Font font = new Font("Arial", 12);
        dayLabel.setFont(font);
        Button rightArrowButton = getDetailRightArrowButton(font2);

        controlHBox.getChildren().addAll(leftArrowButton, dayLabel, rightArrowButton);
        GridPane detailView = new GridPane();
        detailView.setGridLinesVisible(true);
        //detailView.setPadding(new Insets(5, 10, 5, 10));
        DetailViewVBox detailViewVBox = new DetailViewVBox(app, selectedDayInfo);
        detailView.add(controlHBox, 0, 0);
        detailView.add(detailViewVBox, 0, 1);
        GridPane.setMargin(controlHBox, new Insets(5));

        rightPaneVBox.getChildren().add(detailView);

        return rightPaneVBox;
    }

    private Button getDetailRightArrowButton(Font font2) {
        Button rightArrowButton = new Button(">");
        rightArrowButton.setStyle("-fx-background-color: pink;");
        rightArrowButton.setFont(font2);
        rightArrowButton.setPrefSize(30, 60);
        rightArrowButton.setOnAction(e -> {
            if (selectedDayInfo.getDate().getDayOfMonth() == selectedDayInfo.getDate().lengthOfMonth()) {
                createGridPane(app.getMonths()[++currentMonthIndex]);
                monthLabel.setText(app.getMonths()[currentMonthIndex].toString());
                // 8 is the first child node that could possibly be a DayFlowPane

                initSelectedDayFlowPane();
            } else {
                selectedDayFlowPane = (DayFlowPane) monthViewGridPane.getChildren().get(
                        monthViewGridPane.getChildren().indexOf(selectedDayFlowPane) + 1);
                selectedDayFlowPaneRI = GridPane.getRowIndex(selectedDayFlowPane);
                selectedDayFlowPaneCI = GridPane.getColumnIndex(selectedDayFlowPane);
            }
            selectedDayInfo = selectedDayFlowPane.getDayInfo();
            dayLabel.setText(selectedDayInfo.toString());
            app.updateUI();
        });
        return rightArrowButton;
    }


    private void initSelectedDayFlowPane() {
        selectedDayFlowPane = (DayFlowPane) monthViewGridPane.getChildren().get(8);
        selectedDayFlowPaneRI = GridPane.getRowIndex(selectedDayFlowPane);
        selectedDayFlowPaneCI = GridPane.getColumnIndex(selectedDayFlowPane);
        while (selectedDayFlowPane == null) {
            selectedDayFlowPane = (DayFlowPane) monthViewGridPane.getChildren().get(
                    monthViewGridPane.getChildren().indexOf(selectedDayFlowPane) + 1);
            selectedDayFlowPaneRI = GridPane.getRowIndex(selectedDayFlowPane);
            selectedDayFlowPaneCI = GridPane.getColumnIndex(selectedDayFlowPane);
        }
        selectedDayInfo = selectedDayFlowPane.getDayInfo();
    }

    public int getCurrentMonthIndex() {
        return currentMonthIndex;
    }


    public void setSelectedDayFlowPane(DayFlowPane selectedDayFlowPane) {
        this.selectedDayFlowPane = selectedDayFlowPane;
        selectedDayFlowPaneRI = GridPane.getRowIndex(selectedDayFlowPane);
        selectedDayFlowPaneCI = GridPane.getColumnIndex(selectedDayFlowPane);
    }

    public Integer getSelectedDayFlowPaneRI() {
        return selectedDayFlowPaneRI;
    }

    public Integer getSelectedDayFlowPaneCI() {
        return selectedDayFlowPaneCI;
    }

    public GridPane getMonthViewGridPane() {
        return monthViewGridPane;
    }


}
