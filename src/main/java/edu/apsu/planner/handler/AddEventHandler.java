package edu.apsu.planner.handler;

import edu.apsu.planner.data.DayInfo;
import edu.apsu.planner.data.MonthInfo;
import edu.apsu.planner.data.PlannerEvent;
import edu.apsu.planner.data.Type;
import edu.apsu.planner.view.monthViewUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.time.Month;
import java.time.YearMonth;

public class AddEventHandler implements EventHandler<ActionEvent> {

    private MonthInfo[] months;
    private Type[] types;

    private TextField titleOfEventTF;
    private TextArea descriptionOfEventTA;
    private ChoiceBox<String> tagChoiceBox;
    private ChoiceBox<Month> monthChoiceBox;
    private ChoiceBox<Integer> dayChoiceBox;
    private ChoiceBox<Integer> hoursChoiceBox;
    private ChoiceBox<Integer> minChoiceBox;
    public Stage popupStage;
    private monthViewUI app;
    public AddEventHandler(MonthInfo[] months, Type[] types, monthViewUI app) {
        super();
        this.months = months;
        this.types = types;
        this.app = app;
    }
    @Override
    public void handle(ActionEvent event) {
        Stage primaryStage = new Stage();
        Popup popup = new Popup();


        BorderPane root = new BorderPane();
        root.setPrefSize(500,300);
        root.setStyle("-fx-background-color: #B7B7B7;");

        HBox popUpContainerHBox = new HBox();
        VBox leftSideVBox = new VBox();
        leftSideVBox.setPadding(new Insets(10));
        leftSideVBox.setSpacing(10);
        leftSideVBox.setPrefSize(250,150);

        titleOfEventTF = new TextField();
        titleOfEventTF.setStyle("-fx-control-inner-background: pink;");
        titleOfEventTF.setPrefSize(125, 50);
        Font font = new Font("Arial", 18);
        titleOfEventTF.setFont(font);
        titleOfEventTF.setPromptText("Enter title of your event");

        descriptionOfEventTA = new TextArea();
        descriptionOfEventTA.setFont(font);
        descriptionOfEventTA.setStyle("-fx-control-inner-background: pink;");
        descriptionOfEventTA.setPromptText("Enter a decription of event here");
        Button okayButton = new Button("Add to Planner");
        okayButton.setStyle("-fx-color: pink;");
        okayButton.setFont(Font.font("Arial", 18));
        okayButton.setAlignment(Pos.CENTER);
        okayButton.setOnAction(e -> addPlannerEvent());

        leftSideVBox.getChildren().addAll(titleOfEventTF, descriptionOfEventTA, okayButton);

        VBox rightSideVBox = new VBox();
        rightSideVBox.setPadding(new Insets(10));
        rightSideVBox.setSpacing(10);
        rightSideVBox.setPrefSize(250,150);

        HBox tagChoiceBoxContainer = new HBox();
        tagChoiceBoxContainer.setPadding(new Insets(10));
        tagChoiceBoxContainer.setSpacing(10);
        tagChoiceBox = new ChoiceBox<>();
        tagChoiceBox.setStyle("-fx-color: pink;");
        tagChoiceBox.getItems().addAll("Assignment Due Date", "Bill Due Date", "Custom Event");
        tagChoiceBox.setValue("Custom Event");
        tagChoiceBoxContainer.getChildren().add(tagChoiceBox);

        Label dateLbl = new Label("Date and Category: ");
        dateLbl.setFont(font);
        HBox dayContainer = new HBox();
        dayContainer.setPadding(new Insets(10));
        dayContainer.setSpacing(10);
        monthChoiceBox = new ChoiceBox<>();
        monthChoiceBox.getItems().addAll(Month.values());
        monthChoiceBox.setValue(Month.APRIL);
        monthChoiceBox.setStyle("-fx-color: pink;");
        dayChoiceBox = new ChoiceBox<>();
        monthChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int daysInMonth = YearMonth.of(YearMonth.now().getYear(), newValue).lengthOfMonth();
                dayChoiceBox.getItems().clear();
                for (int i = 1; i <= daysInMonth; i++) {
                    dayChoiceBox.getItems().add(i);
                }
                dayChoiceBox.setValue(1); // Set default day value
            }
        });
        dayChoiceBox.setValue(1);
        dayChoiceBox.setStyle("-fx-color: pink;");
        dayContainer.getChildren().addAll(monthChoiceBox, dayChoiceBox);

        Label startTimeLbl = new Label("Start time:");
        startTimeLbl.setFont(font);
        HBox startTimeContainer = new HBox();
        startTimeContainer.setPadding(new Insets(10));
        startTimeContainer.setSpacing(10);
        hoursChoiceBox = new ChoiceBox<>();
        hoursChoiceBox.setStyle("-fx-color: pink;");
        hoursChoiceBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        hoursChoiceBox.setValue(12);
        Label colonLbl = new Label(" : ");
        colonLbl.setFont(Font.font("Arial",24));
        minChoiceBox = new ChoiceBox<>();
        minChoiceBox.setStyle("-fx-color: pink;");
        minChoiceBox.getItems().addAll(00,05,10,15,20,25,30,35,40,45,50,55);
        minChoiceBox.setValue(00);
        ChoiceBox<String> amPmChoiceBox = new ChoiceBox<>();
        amPmChoiceBox.setStyle("-fx-color: pink;");
        amPmChoiceBox.getItems().addAll("AM", "PM");
        amPmChoiceBox.setValue("AM");
        startTimeContainer.getChildren().addAll(hoursChoiceBox, colonLbl, minChoiceBox, amPmChoiceBox);

        Label endTimeLbl = new Label("End time:");
        endTimeLbl.setFont(font);
        HBox endTimeContainer = new HBox();
        endTimeContainer.setPadding(new Insets(10));
        endTimeContainer.setSpacing(10);
        startTimeContainer.setSpacing(10);

        ChoiceBox<Integer> endHoursChoiceBox = new ChoiceBox<>();
        endHoursChoiceBox.setStyle("-fx-color: pink;");
        endHoursChoiceBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        endHoursChoiceBox.setValue(12);
        Label endColonLbl = new Label(" : ");
        endColonLbl.setFont(Font.font("Arial",24));
        ChoiceBox<Integer> endMinChoiceBox = new ChoiceBox<>();
        endMinChoiceBox.setStyle("-fx-color: pink;");
        endMinChoiceBox.getItems().addAll(0, 5,10,15,20,25,30,35,40,45,50,55);
        endMinChoiceBox.setValue(0);
        ChoiceBox<String> endAmPmChoiceBox = new ChoiceBox<>();
        endAmPmChoiceBox.setStyle("-fx-color: pink;");
        endAmPmChoiceBox.getItems().addAll("AM", "PM");
        endAmPmChoiceBox.setValue("AM");
        startTimeContainer.getChildren().addAll(endHoursChoiceBox, endColonLbl, endMinChoiceBox, endAmPmChoiceBox);
        endTimeContainer.getChildren().addAll(hoursChoiceBox, colonLbl, minChoiceBox, amPmChoiceBox);



        rightSideVBox.getChildren().addAll(dateLbl, tagChoiceBoxContainer ,dayContainer, startTimeLbl, startTimeContainer, endTimeLbl, endTimeContainer);

        popUpContainerHBox.getChildren().addAll(leftSideVBox, rightSideVBox);
        root.setCenter(popUpContainerHBox);



        primaryStage.setTitle("Add Event");

        //  popup.getContent().add(root);
        popup.setAutoHide(true);


        //Scene scene = new Scene(rootMain);

        // primaryStage.setScene(scene);
        //
        // primaryStage.show();

        popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(root));

        // Show the popup
        popupStage.show();

    }

    private void addPlannerEvent() {

        String eventName = titleOfEventTF.getText().trim();
        String eventDescription = descriptionOfEventTA.getText().trim();
        String time = hoursChoiceBox.getSelectionModel().getSelectedItem().toString() + ":" +
                minChoiceBox.getSelectionModel().getSelectedItem().toString();
        Month chosenMonth = monthChoiceBox.getSelectionModel().getSelectedItem();
        int chosenDay = dayChoiceBox.getSelectionModel().getSelectedItem();

        PlannerEvent plannerEvent = getPlannerEvent(eventName, eventDescription, time);

        DayInfo dayInfo = months[chosenMonth.getValue() - 1].getDayOf(chosenDay);
        dayInfo.getEvents().add(plannerEvent);


        System.out.println("Added Planner Event:");
        for (PlannerEvent ev : dayInfo.getEvents()) {
            System.out.println(ev.toString());
            System.out.println(ev.getTime());
        }
        popupStage.close();
        app.createGridPane(app.getMonths()[app.getCurrentMonthIndex()]);
    }

    private PlannerEvent getPlannerEvent(String eventName, String eventDescription, String time) {
        String tagChoice = tagChoiceBox.getSelectionModel().getSelectedItem();
        Type type = null;
        switch(tagChoice){
            case "Assignment Due Date":
                type = types[2];
                break;
            case "Bill Due Date":
                type = types[3];
                break;
            case "Custom Event":
                type = types[4];
                break;
        }
        return new PlannerEvent(eventName, eventDescription, time, type);
    }


}
