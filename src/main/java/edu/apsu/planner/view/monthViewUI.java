package edu.apsu.planner.view;

import com.itextpdf.text.pdf.PdfDocument;
import edu.apsu.planner.app.PlannerApplication;
import edu.apsu.planner.handler.AddEventHandler;
import edu.apsu.planner.data.*;
import edu.apsu.planner.handler.AddScheduleHandler;
import edu.apsu.planner.handler.FileEventHandler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class monthViewUI extends BorderPane {

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
    private DayFlowPane selectedDayFlowPane;
    private DayInfo selectedDayInfo;
    private GridPane gridPaneDetailView;

    private PlannerApplication app;

    private  Scene scene;
    private final Type[] types = new Type[5];


    public monthViewUI(PlannerApplication app, MonthInfo[] months) {
        // Set up MonthInfo data
        this.app = app;
        this.months = months;

        this.setStyle("-fx-background-color: #B7B7B7;");
        this.setPrefSize(1375, 720);


        this.setTop(creatMenuBar());
        this.setLeft(createLeftPane());
        this.setCenter(createCenterPane());
        this.setRight(createRightPane());
    }

    public MenuBar creatMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #B7B7B7;");

        FileEventHandler fileEventHandler = new FileEventHandler(this, months);
        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New");
        newMenuItem.setOnAction(fileEventHandler);
        MenuItem saveMenuItem = new MenuItem("Save");
        saveMenuItem.setOnAction(fileEventHandler);
        MenuItem openMenuItem = new MenuItem("Open");
        openMenuItem.setOnAction(fileEventHandler);
        MenuItem savePDFMenuItem = new MenuItem("Export to PDF");
        savePDFMenuItem.setOnAction(e->{
            saveAsPDF();
        });
        //savePDFMenuItem.setOnAction(fileEventHandler);
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

        MenuItem addSchedule = new MenuItem("Add  Schedule");
        AddScheduleHandler addScheduleHandler = new AddScheduleHandler(this);
        addSchedule.setOnAction(addScheduleHandler);
        SeparatorMenuItem separatorMenuItem2 = new SeparatorMenuItem();
        MenuItem addCustomEvent = new MenuItem("Add Event");
        AddEventHandler addEventHandler = new AddEventHandler(this);
        addCustomEvent.setOnAction(addEventHandler);
        addMenu.getItems().addAll(
                addSchedule,
                separatorMenuItem2,
                addCustomEvent
        );

        Menu insertMenu = new Menu("Insert");
        Menu insertSymbol = new Menu("Insert Symbol");
        MenuItem classSymbol = new MenuItem("Class symbol");
        classSymbol.setOnAction(e->{
            types[0].setSymbolIsVisible(true);
            createGridPane(months[currentMonthIndex]);
        });
        MenuItem workSymbol = new MenuItem("Work symbol");
        workSymbol.setOnAction(e->{
            types[1].setSymbolIsVisible(true);
            createGridPane(months[currentMonthIndex]);
        });
        MenuItem assignmentSymbol = new MenuItem("Assignment symbol");
        assignmentSymbol.setOnAction(e->{
            types[2].setSymbolIsVisible(true);
            createGridPane(months[currentMonthIndex]);
        });
        MenuItem billSymbol = new MenuItem("Bill symbol");
        billSymbol.setOnAction(e->{
            types[3].setSymbolIsVisible(true);
            createGridPane(months[currentMonthIndex]);
        });
        MenuItem customSymbol = new MenuItem("Custom symbol");
        customSymbol.setOnAction(e->{
            types[4].setSymbolIsVisible(true);
            createGridPane(months[currentMonthIndex]);
        });
       // MenuItem churchSymbol = new MenuItem("Church symbol");
        insertSymbol.getItems().addAll(
                classSymbol,
                workSymbol,
                assignmentSymbol,
                billSymbol,
                customSymbol);
                //churchSymbol);
        insertMenu.getItems().add(insertSymbol);

        Menu viewMenu = new Menu("View");
        MenuItem weekViewMenu = new MenuItem("Week View");
        weekViewMenu.setOnAction(e-> app.switchScene(app.weekViewScene));
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
        Rectangle defaultClassSymbol = new Rectangle(20, 20, Color.BLUE);
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
        Rectangle defaultWorkSymbol = new Rectangle(20, 20, Color.FORESTGREEN);
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
        Rectangle defaultAssignmentSymbol = new Rectangle(20, 20, Color.PURPLE);
        Image assignmentDueSymbolImage =  Type.loadImageIntoLabel("/edu/apsu/planner/symbolPNGResource/icons8-study-100.png");
        Type assignmentType = new Type(Tag.ASSIGNMENT, assignmentDueSymbolImage, defaultAssignmentSymbol,true);        types[2] = assignmentType;
        assignmentFilter.selectedProperty().bindBidirectional(assignmentType.isVisibleProperty());
        assignmentFilter.setOnMouseClicked(
                e -> createGridPane(months[currentMonthIndex])
        );


        CheckBox billFilter = new CheckBox("Bill Due");
        billFilter.setFont(font2);
        billFilter.setStyle("-fx-color: pink;");
        billFilter.setSelected(true);
        Rectangle defaultBillSymbol = new Rectangle(20, 20, Color.RED);
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
        Rectangle defaultCustomEventSymbol = new Rectangle(20, 20, Color.AQUA);
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
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100.0 / 7);
        monthViewGridPane.getColumnConstraints().addAll(
                columnConstraints, columnConstraints, columnConstraints,
                columnConstraints, columnConstraints, columnConstraints, columnConstraints);

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
                        selectedDayInfo = selectedDayFlowPane.getDayInfo();
                        dayLabel.setText(selectedDayInfo.toString());
                });
                dayFlowPane.getChildren().add(tmp);
                HashSet<Tag> tagsToAdd = findTagsToAdd(day);
                ArrayList<Type> typesToAdd = new ArrayList<>();
                for (Type type : types)
                {
                    if (tagsToAdd.contains(type.getTag()) && type.isVisible())
                    {
                        typesToAdd.add(type);
                    }
                }
                dayFlowPane.setAlignment(Pos.TOP_LEFT);

                for (Type type : typesToAdd) {
                    if (type.isSymbolIsVisible()) {
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
        selectedDayFlowPane = (DayFlowPane) monthViewGridPane.getChildren().get(8);
        while (selectedDayFlowPane == null) {
            selectedDayFlowPane = (DayFlowPane) monthViewGridPane.getChildren().get(
                    monthViewGridPane.getChildren().indexOf(selectedDayFlowPane) + 1);
        }
        selectedDayInfo = selectedDayFlowPane.getDayInfo();



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
                selectedDayFlowPane = (DayFlowPane) monthViewGridPane.getChildren().get(
                        monthViewGridPane.getChildren().size() - 1
                );
                while (selectedDayFlowPane == null) {
                    selectedDayFlowPane = (DayFlowPane) monthViewGridPane.getChildren().get(
                            monthViewGridPane.getChildren().indexOf(selectedDayFlowPane) - 1);
                }
            } else {
                selectedDayFlowPane = (DayFlowPane) monthViewGridPane.getChildren().get(
                        monthViewGridPane.getChildren().indexOf(selectedDayFlowPane) - 1);
            }
            selectedDayInfo = selectedDayFlowPane.getDayInfo();
            dayLabel.setText(selectedDayInfo.toString());
        });


        dayLabel = new Label(selectedDayInfo.toString());
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
                // 8 is the first child node that could possibly be a DayFlowPane

                selectedDayFlowPane = (DayFlowPane) monthViewGridPane.getChildren().get(8);
                while (selectedDayFlowPane == null) {
                    selectedDayFlowPane = (DayFlowPane) monthViewGridPane.getChildren().get(
                            monthViewGridPane.getChildren().indexOf(selectedDayFlowPane) + 1);
                }
            } else {
                selectedDayFlowPane = (DayFlowPane) monthViewGridPane.getChildren().get(
                        monthViewGridPane.getChildren().indexOf(selectedDayFlowPane) + 1);
            }
            selectedDayInfo = selectedDayFlowPane.getDayInfo();
            dayLabel.setText(selectedDayInfo.toString());
        });



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

    public void setMonths(MonthInfo[] months) {
        this.months = months;
    }

    public int getCurrentMonthIndex() {
        return currentMonthIndex;
    }

    public Stage getStage() {
        return app.getStage();
    }


   public void saveAsPDF(){//GridPane gridPane) {
       // Take a snapshot of the GridPane
     // WritableImage image = gridPane.snapshot(new SnapshotParameters(), null);

       String fileName = "planner.pdf";
       Document document = new Document();
       try {
           PdfWriter.getInstance(document, new FileOutputStream(fileName));
           document.open();
           Paragraph content = new Paragraph("test");
           document.add(content);
           document.close();
           System.out.println("done");
       } catch (DocumentException e) {
           throw new RuntimeException(e);
       } catch (FileNotFoundException e) {
           throw new RuntimeException(e);
       }


   }






}
