package edu.apsu.planner.view;

import edu.apsu.planner.app.PlannerApplication;
import edu.apsu.planner.data.MonthInfo;
import edu.apsu.planner.data.Tag;
import edu.apsu.planner.data.Type;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class weekViewUI extends BorderPane {
    private final int INSET_SIZE = 15;
    private Type[] types = new Type[5];
    private MonthInfo[] months;
    PlannerApplication app;


    
    public weekViewUI(PlannerApplication app, MonthInfo[] months) {
        super();
        this.months = months;
        this.app = app;

        this.setStyle("-fx-background-color: #B7B7B7;");
        this.setPadding(new Insets(15));
        this.setPrefSize(1250, 600);

        this.setTop(creatMenuBar());
        this.setLeft(createLeftPane());
        this.setCenter(createCenterPane());
    }


    public MenuBar creatMenuBar(){
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #B7B7B7;");


        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New");
       // newMenuItem.setOnAction(fileEventHandler);
        MenuItem saveMenuItem = new MenuItem("Save");
        // saveMenuItem.setOnAction(fileEventHandler);
        MenuItem openMenuItem = new MenuItem("Open");
       // openMenuItem.setOnAction(fileEventHandler);
        MenuItem savePDFMenuItem = new MenuItem("Export to PDF");
        // savePDFMenuItem.setOnAction(fileEventHandler);
        MenuItem exitMenuItem = new MenuItem("Exit");
        // exitMenuItem.setOnAction(fileEventHandler);
        fileMenu.getItems().addAll(
                newMenuItem,
                new SeparatorMenuItem(),
                saveMenuItem,
                openMenuItem,
                savePDFMenuItem,
                new SeparatorMenuItem(),
                exitMenuItem);

        Menu addMenu = new Menu("Add");

        MenuItem addSchedule = new MenuItem("Add Custom Schedule");
        addSchedule.setOnAction(event->{
            choiceBoxDemo.popUp();
        });
        //MenuItem addWorkSchedule = new MenuItem("Add Work Schedule");
       // MenuItem addCustomSchedule = new MenuItem("Add Custom Schedule");
        SeparatorMenuItem separatorMenuItem2 = new SeparatorMenuItem();
        //MenuItem addAssignmentDueDate = new MenuItem("Add Assignment Due Date");
        //MenuItem addBillDueDate = new MenuItem("Add Bill Due Date");
        MenuItem addCustomEvent = new MenuItem("Add Event");
        addCustomEvent.setOnAction(event->{
            choiceBoxDemo2.popUp();
        });
        addMenu.getItems().addAll(addSchedule, separatorMenuItem2, addCustomEvent);
        //addWorkSchedule,addCustomSchedule, addAssignmentDueDate,addBillDueDate,
        Menu insertMenu = new Menu( "Insert");

        Menu insertSymbol = new Menu("Insert Symbol");

        MenuItem classSymbol = new MenuItem("Class symbol");
        MenuItem workSymbol = new MenuItem("Work symbol");
        MenuItem assignmentSymbol = new MenuItem("Assignment symbol");
        MenuItem billSymbol = new MenuItem("Bill symbol");
        MenuItem customSymbol = new MenuItem("Custom symbol");
        insertSymbol.getItems().addAll(classSymbol, workSymbol,assignmentSymbol,billSymbol,customSymbol);
        insertMenu.getItems().add(insertSymbol);

        Menu viewMenu = new Menu("View");
        MenuItem weekViewMenu = new MenuItem("Week View");

        MenuItem monthViewMenu = new MenuItem("Month View");
        monthViewMenu.setOnAction(e -> {
            app.switchScene(app.monthViewScene);
        });
        viewMenu.getItems().addAll(weekViewMenu, monthViewMenu);

        menuBar.getMenus().addAll(fileMenu,addMenu, insertMenu, viewMenu);

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
      //  classFilter.setOnMouseClicked(
          //      e -> createGridPane(months[currentMonthIndex])
      //  );

        CheckBox workFilter = new CheckBox("Work Schedule");
        workFilter.setStyle("-fx-color: pink;");
        workFilter.setFont(font2);
        workFilter.setSelected(true);
        Rectangle defaultWorkSymbol = new Rectangle(20, 20, Color.FORESTGREEN);
        Image workSymbolImage =  Type.loadImageIntoLabel("/edu/apsu/planner/symbolPNGResource/icons8-work-100.png");
        Type workType = new Type(Tag.WORK, workSymbolImage, defaultWorkSymbol, true);
        types[1] = workType;
        workFilter.selectedProperty().bindBidirectional(workType.isVisibleProperty());
      //  workFilter.setOnMouseClicked(
      //          e -> createGridPane(months[currentMonthIndex])
      //  );


        CheckBox assignmentFilter = new CheckBox("Assignments Due");
        assignmentFilter.setFont(font2);
        assignmentFilter.setStyle("-fx-color: pink;");
        assignmentFilter.setSelected(true);
        Rectangle defaultAssignmentSymbol = new Rectangle(20, 20, Color.PURPLE);
        Image assignmentDueSymbolImage =  Type.loadImageIntoLabel("/edu/apsu/planner/symbolPNGResource/icons8-study-100.png");
        Type assignmentType = new Type(Tag.ASSIGNMENT, assignmentDueSymbolImage, defaultAssignmentSymbol,true);        types[2] = assignmentType;
        assignmentFilter.selectedProperty().bindBidirectional(assignmentType.isVisibleProperty());
       // assignmentFilter.setOnMouseClicked(
       //         e -> createGridPane(months[currentMonthIndex])
      //  );


        CheckBox billFilter = new CheckBox("Bill Due");
        billFilter.setFont(font2);
        billFilter.setStyle("-fx-color: pink;");
        billFilter.setSelected(true);
        Rectangle defaultBillSymbol = new Rectangle(20, 20, Color.RED);
        Image billDueSymbolImage =  Type.loadImageIntoLabel("/edu/apsu/planner/symbolPNGResource/icons8-bill-64.png");
        Type billType = new Type(Tag.BILL, billDueSymbolImage, defaultBillSymbol,true);
        types[3] = billType;
        billFilter.selectedProperty().bindBidirectional(billType.isVisibleProperty());
       // billFilter.setOnMouseClicked(
       //         e -> createGridPane(months[currentMonthIndex])
       // );


        CheckBox customEventFilter = new CheckBox("Custom Events");
        customEventFilter.setFont(font2);
        customEventFilter.setStyle("-fx-color: pink;");
        customEventFilter.setSelected(true);
        Rectangle defaultCustomEventSymbol = new Rectangle(20, 20, Color.AQUA);
        Image customSymbolImage =  Type.loadImageIntoLabel("/edu/apsu/planner/symbolPNGResource/icons8-important-100.png");
        Type customEventType = new Type(Tag.CUSTOM, customSymbolImage, defaultCustomEventSymbol, true);

        types[4] = customEventType;
        customEventFilter.selectedProperty().bindBidirectional(customEventType.isVisibleProperty());
      //  customEventFilter.setOnMouseClicked(
       //         e -> createGridPane(months[currentMonthIndex])
      //  );




        leftPaneVBox.getChildren().addAll(filterLabel, classFilter, workFilter, assignmentFilter, billFilter, customEventFilter);

        return leftPaneVBox;

    }

    public VBox createCenterPane(){

        VBox centerPaneVBox = new VBox();
        centerPaneVBox.setSpacing(10);
        centerPaneVBox.setPadding(new Insets(10));

        HBox labelAndControl = new HBox();
        labelAndControl.setSpacing(10);
        labelAndControl.setAlignment(Pos.CENTER);

        Font font = new Font("Arial", 22);
        Font font2 = new Font("Arial", 40);
        Font font3 = new Font("Arial", 45);


        Button leftArrowButton = new Button("<");
        leftArrowButton.setFont(font3);
        leftArrowButton.setPrefSize(150,200);
        leftArrowButton.setStyle("-fx-background-color: pink;");


        Label weekLabel = new Label("March 31st - April 6th");
        weekLabel.setPrefSize(700,100);
        weekLabel.setAlignment(Pos.CENTER);
        weekLabel.setFont(font2);
        Button rightArrowButton = new Button(">");
        rightArrowButton.setStyle("-fx-background-color: pink;");

        rightArrowButton.setFont(font3);
        rightArrowButton.setPrefSize(150,180);

        labelAndControl.getChildren().addAll(leftArrowButton,weekLabel,rightArrowButton);

        GridPane monthViewGridPane = new GridPane();
        monthViewGridPane.setGridLinesVisible(true);
        monthViewGridPane.setPadding(new Insets(10));
        monthViewGridPane.setPrefSize(1050,1050);

        Label sundayLabel = new Label("Sunday");
        sundayLabel.setPrefSize(150,75);
        sundayLabel.setAlignment(Pos.CENTER);
        sundayLabel.setFont(font);
        Label mondayLabel = new Label("Monday");
        mondayLabel.setPrefSize(150,75);
        mondayLabel.setAlignment(Pos.CENTER);
        mondayLabel.setFont(font);
        Label tuesdayLabel = new Label("Tuesday");
        tuesdayLabel.setPrefSize(150,75);
        tuesdayLabel.setAlignment(Pos.CENTER);
        tuesdayLabel.setFont(font);
        Label wednesdayLabel = new Label("Wednesday");
        wednesdayLabel.setPrefSize(150,75);
        wednesdayLabel.setAlignment(Pos.CENTER);
        wednesdayLabel.setFont(font);
        Label thursdayLabel = new Label("Thursday");
        thursdayLabel.setPrefSize(150,75);
        thursdayLabel.setAlignment(Pos.CENTER);
        thursdayLabel.setFont(font);
        Label fridayLabel = new Label("Friday");
        fridayLabel.setPrefSize(150,75);
        fridayLabel.setAlignment(Pos.CENTER);
        fridayLabel.setFont(font);
        Label saturdayLabel = new Label("Saturday");
        saturdayLabel.setPrefSize(150,75);
        saturdayLabel.setAlignment(Pos.CENTER);
        saturdayLabel.setFont(font);

        monthViewGridPane.add(sundayLabel,0, 0);
        monthViewGridPane.add(mondayLabel, 1, 0);
        monthViewGridPane.add(tuesdayLabel, 2, 0);
        monthViewGridPane.add(wednesdayLabel, 3, 0);
        monthViewGridPane.add(thursdayLabel, 4, 0);
        monthViewGridPane.add(fridayLabel, 5, 0);
        monthViewGridPane.add(saturdayLabel, 6, 0);

        centerPaneVBox.getChildren().addAll(labelAndControl, monthViewGridPane);

        return centerPaneVBox;
    }

}
