package edu.apsu.planner.view;

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

public class weekViewUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #B7B7B7;");
        root.setPadding(new Insets(15));
        root.setPrefSize(1250, 600);

        root.setTop(creatMenuBar());
        root.setLeft(createLeftPane());
        root.setCenter(createCenterPane());

        Scene scene = new Scene(root);
        stage.setTitle("Planer Application");
        stage.setScene(scene);
        stage.show();

    }


    public MenuBar creatMenuBar(){
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #B7B7B7;");


        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New");
        // MenuItem openMenuItem = new MenuItem("Open");
        MenuItem savePDFMenuItem = new MenuItem("Save to PDF");
        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(actionEvent ->
                Platform.exit()
        );
        fileMenu.getItems().addAll(newMenuItem,savePDFMenuItem, separatorMenuItem,exitMenuItem);

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
        MenuItem studySymbol = new MenuItem("Study symbol");
        MenuItem billSymbol = new MenuItem("Bill symbol");
        MenuItem importSymbol = new MenuItem("Important symbol");
        MenuItem churchSymbol = new MenuItem("Church symbol");
        insertSymbol.getItems().addAll(classSymbol, workSymbol,studySymbol,billSymbol,importSymbol,churchSymbol);
        insertMenu.getItems().add(insertSymbol);

        Menu viewMenu = new Menu("View");
        MenuItem weekViewMenu = new MenuItem("Week View");
        MenuItem monthViewMenu = new MenuItem("Month View");
        viewMenu.getItems().addAll(weekViewMenu, monthViewMenu);

        menuBar.getMenus().addAll(fileMenu,addMenu, insertMenu, viewMenu);

        return menuBar;
    }

    public VBox createLeftPane(){

        VBox leftPaneVBox = new VBox();
        leftPaneVBox.setSpacing(10);


        Label filterLabel = new Label("Filter");
        Font filterFont = new Font("Arial", 40);

        Font font = new Font("Arial", 24);
        filterLabel.setFont(filterFont);

        Font font2 = new Font("Arial", 14);


        CheckBox classFilter = new CheckBox("Class Schedule");
        classFilter.setFont(font2);
        classFilter.setStyle("-fx-color: pink;");

        CheckBox workFilter = new CheckBox("Work Schedule");
        workFilter.setStyle("-fx-color: pink;");
        workFilter.setFont(font2);

        CheckBox customSchedFilter = new CheckBox("Custom Schedule");
        customSchedFilter.setFont(font2);
        customSchedFilter.setStyle("-fx-color: pink;");


        CheckBox assignmentFilter = new CheckBox("Assignments Due");
        assignmentFilter.setFont(font2);
        assignmentFilter.setStyle("-fx-color: pink;");


        CheckBox billFilter = new CheckBox("Bill Due");
        billFilter.setFont(font2);
        billFilter.setStyle("-fx-color: pink;");


        CheckBox customEventFilter = new CheckBox("Custom Events");
        customEventFilter.setFont(font2);
        customEventFilter.setStyle("-fx-color: pink;");


        leftPaneVBox.getChildren().addAll(filterLabel,classFilter,workFilter,customSchedFilter,assignmentFilter,billFilter,customEventFilter);

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
