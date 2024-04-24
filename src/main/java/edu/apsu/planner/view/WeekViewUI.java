package edu.apsu.planner.view;

import edu.apsu.planner.app.PlannerApplication;
import edu.apsu.planner.data.DayInfo;
import edu.apsu.planner.handler.AddEventHandler;
import edu.apsu.planner.handler.AddScheduleHandler;
import edu.apsu.planner.handler.FileEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class WeekViewUI extends BorderPane {
    LocalDate currentSunday;

    private final PlannerApplication app;
    private final DayInfo[] currentWeek = new DayInfo[7];
    private GridPane weekViewGridPane;
    private Label weekLabel;


    public WeekViewUI(PlannerApplication app) {
        super();
        this.app = app;

        currentSunday = LocalDate.now();
        while (currentSunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
            currentSunday = currentSunday.minusDays(1);
        }


        this.setStyle("-fx-background-color: #B7B7B7;");
        //this.setPadding(new Insets(15));
        this.setPrefSize(1375, 720);

        this.setTop(createMenuBar());
        this.setLeft(createLeftPane());
        this.setCenter(createCenterPane());
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
        addSchedule.setOnAction(new AddScheduleHandler(this.app));
        SeparatorMenuItem separatorMenuItem2 = new SeparatorMenuItem();
        MenuItem addCustomEvent = new MenuItem("Add Event");
        addCustomEvent.setOnAction(new AddEventHandler(this.app));
        addMenu.getItems().addAll(addSchedule, separatorMenuItem2, addCustomEvent);
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
        insertSymbol.getItems().addAll(classSymbol, workSymbol, assignmentSymbol, billSymbol, customSymbol);
        insertMenu.getItems().add(insertSymbol);

        Menu viewMenu = new Menu("View");
        MenuItem weekViewMenu = new MenuItem("Week View");

        MenuItem monthViewMenu = new MenuItem("Month View");
        monthViewMenu.setOnAction(e -> app.switchScene(app.monthViewScene));
        viewMenu.getItems().addAll(weekViewMenu, monthViewMenu);

        menuBar.getMenus().addAll(fileMenu, addMenu, insertMenu, viewMenu);

        return menuBar;
    }

    public VBox createLeftPane() {

        VBox leftPaneVBox = new VBox();
        int INSET_SIZE = 15;
        leftPaneVBox.setPadding(new Insets(INSET_SIZE));
        leftPaneVBox.setSpacing(10);
        leftPaneVBox.setPadding(new Insets(15));

        Label filterLabel = new Label("Filter");
        Font filterFont = new Font("Arial", 40);

        filterLabel.setFont(filterFont);

        Font font = new Font("Arial", 14);


        CheckBox classFilter = new CheckBox("Class Schedule");
        classFilter.setFont(font);
        classFilter.setStyle("-fx-color: pink;");
        classFilter.setSelected(true);
        classFilter.selectedProperty().bindBidirectional(app.getTypes()[0].isVisibleProperty());
        classFilter.setOnMouseClicked(
                e -> app.updateUI()
        );

        CheckBox workFilter = new CheckBox("Work Schedule");
        workFilter.setStyle("-fx-color: pink;");
        workFilter.setFont(font);
        workFilter.setSelected(true);
        workFilter.selectedProperty().bindBidirectional(app.getTypes()[1].isVisibleProperty());
        workFilter.setOnMouseClicked(
                e -> app.updateUI()
        );


        CheckBox assignmentFilter = new CheckBox("Assignments Due");
        assignmentFilter.setFont(font);
        assignmentFilter.setStyle("-fx-color: pink;");
        assignmentFilter.setSelected(true);
        assignmentFilter.selectedProperty().bindBidirectional(app.getTypes()[2].isVisibleProperty());
        assignmentFilter.setOnMouseClicked(
                e -> app.updateUI()
        );


        CheckBox billFilter = new CheckBox("Bill Due");
        billFilter.setFont(font);
        billFilter.setStyle("-fx-color: pink;");
        billFilter.setSelected(true);
        billFilter.selectedProperty().bindBidirectional(app.getTypes()[3].isVisibleProperty());
        billFilter.setOnMouseClicked(
                e -> app.updateUI()
        );


        CheckBox customEventFilter = new CheckBox("Custom Events");
        customEventFilter.setFont(font);
        customEventFilter.setStyle("-fx-color: pink;");
        customEventFilter.setSelected(true);
        customEventFilter.selectedProperty().bindBidirectional(app.getTypes()[4].isVisibleProperty());
        customEventFilter.setOnMouseClicked(
                e -> app.updateUI()
        );


        leftPaneVBox.getChildren().addAll(filterLabel, classFilter, workFilter, assignmentFilter, billFilter, customEventFilter);

        return leftPaneVBox;

    }

    public VBox createCenterPane() {

        VBox centerPaneVBox = new VBox();
        centerPaneVBox.setSpacing(10);
        centerPaneVBox.setPadding(new Insets(10));

        HBox labelAndControl = new HBox();
        labelAndControl.setSpacing(10);
        labelAndControl.setAlignment(Pos.CENTER);

        Font font1 = new Font("Arial", 40);
        Font font2 = new Font("Arial", 45);


        Button leftArrowButton = new Button("<");
        leftArrowButton.setFont(font2);
        leftArrowButton.setPrefSize(150, 200);
        leftArrowButton.setStyle("-fx-background-color: pink;");
        leftArrowButton.setOnAction(e -> {
            currentSunday = currentSunday.minusDays(7);
            currentSunday = LocalDate.of(2024, currentSunday.getMonth(), currentSunday.getDayOfMonth());
            this.setCenter(createCenterPane());
        });

        weekLabel = new Label();
        weekLabel.setPrefSize(700, 100);
        weekLabel.setAlignment(Pos.CENTER);
        weekLabel.setFont(font1);
        Button rightArrowButton = new Button(">");
        rightArrowButton.setStyle("-fx-background-color: pink;");

        rightArrowButton.setFont(font2);
        rightArrowButton.setPrefSize(150, 180);
        rightArrowButton.setOnAction(e -> {
            currentSunday = currentSunday.plusDays(7);
            currentSunday = LocalDate.of(2024, currentSunday.getMonth(), currentSunday.getDayOfMonth());
            this.setCenter(createCenterPane());
        });

        labelAndControl.getChildren().addAll(leftArrowButton, weekLabel, rightArrowButton);
        createGridPane();
        centerPaneVBox.getChildren().addAll(labelAndControl, weekViewGridPane);

        return centerPaneVBox;
    }

    public void createGridPane() {
        Font font = new Font("Arial", 22);
        weekViewGridPane = new GridPane();
        weekViewGridPane.setGridLinesVisible(false);
        weekViewGridPane.setGridLinesVisible(true);
        weekViewGridPane.setPadding(new Insets(10));
        weekViewGridPane.setPrefSize(1050, 1050);
        weekViewGridPane.setAlignment(Pos.CENTER);

        Label sundayLabel = new Label("Sunday");
        sundayLabel.setPrefSize(150, 75);
        sundayLabel.setAlignment(Pos.CENTER);
        sundayLabel.setFont(font);
        Label mondayLabel = new Label("Monday");
        mondayLabel.setPrefSize(150, 75);
        mondayLabel.setAlignment(Pos.CENTER);
        mondayLabel.setFont(font);
        Label tuesdayLabel = new Label("Tuesday");
        tuesdayLabel.setPrefSize(150, 75);
        tuesdayLabel.setAlignment(Pos.CENTER);
        tuesdayLabel.setFont(font);
        Label wednesdayLabel = new Label("Wednesday");
        wednesdayLabel.setPrefSize(150, 75);
        wednesdayLabel.setAlignment(Pos.CENTER);
        wednesdayLabel.setFont(font);
        Label thursdayLabel = new Label("Thursday");
        thursdayLabel.setPrefSize(150, 75);
        thursdayLabel.setAlignment(Pos.CENTER);
        thursdayLabel.setFont(font);
        Label fridayLabel = new Label("Friday");
        fridayLabel.setPrefSize(150, 75);
        fridayLabel.setAlignment(Pos.CENTER);
        fridayLabel.setFont(font);
        Label saturdayLabel = new Label("Saturday");
        saturdayLabel.setPrefSize(150, 75);
        saturdayLabel.setAlignment(Pos.CENTER);
        saturdayLabel.setFont(font);

        weekViewGridPane.add(sundayLabel, 0, 0);
        weekViewGridPane.add(mondayLabel, 1, 0);
        weekViewGridPane.add(tuesdayLabel, 2, 0);
        weekViewGridPane.add(wednesdayLabel, 3, 0);
        weekViewGridPane.add(thursdayLabel, 4, 0);
        weekViewGridPane.add(fridayLabel, 5, 0);
        weekViewGridPane.add(saturdayLabel, 6, 0);
        drawDetailViews();

    }

    public void drawDetailViews() {
        LocalDate currentDate = LocalDate.of(currentSunday.getYear(), currentSunday.getMonth(), currentSunday.getDayOfMonth());
        for (int i = 0; i < 7; i++) {
            DayInfo currentDay = app.getMonths()[currentDate.getMonthValue() - 1].getDayOf(currentDate.getDayOfMonth());
            currentWeek[i] = currentDay;

            weekViewGridPane.add(new DetailViewVBox(app, currentDay), i, 1);
            if (currentDate.plusDays(1).getYear() > currentDate.getYear()) {
                currentDate = LocalDate.of(2024, Month.JANUARY, 1);
            } else {
                currentDate = currentDate.plusDays(1);
            }
        }
        weekLabel.setText(currentWeek[0].getWeekViewString() + " - " + currentWeek[6].getWeekViewString());

    }
}
