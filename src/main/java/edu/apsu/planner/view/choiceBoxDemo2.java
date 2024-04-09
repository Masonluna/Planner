package edu.apsu.planner.view;

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

public class choiceBoxDemo2 {

    public static void main(String[] args) {

    }


    public static void popUp(){
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

        TextField titleOfEventTF = new TextField();
        titleOfEventTF.setStyle("-fx-control-inner-background: pink;");
        titleOfEventTF.setPrefSize(125, 50);
        Font font = new Font("Arial", 18);
        titleOfEventTF.setFont(font);
        titleOfEventTF.setPromptText("Enter title of your event");

        TextArea descriptionOfEventTA = new TextArea();
        descriptionOfEventTA.setFont(font);
        descriptionOfEventTA.setStyle("-fx-control-inner-background: pink;");
        descriptionOfEventTA.setPromptText("Enter a decription of event here");
        Button okayButton = new Button("Add to Planner");
        okayButton.setStyle("-fx-color: pink;");
        okayButton.setFont(Font.font("Arial", 18));
        okayButton.setAlignment(Pos.CENTER);


        leftSideVBox.getChildren().addAll(titleOfEventTF, descriptionOfEventTA, okayButton);

        VBox rightSideVBox = new VBox();
        rightSideVBox.setPadding(new Insets(10));
        rightSideVBox.setSpacing(10);
        rightSideVBox.setPrefSize(250,150);

        HBox tagChoiceBoxContainer = new HBox();
        tagChoiceBoxContainer.setPadding(new Insets(10));
        tagChoiceBoxContainer.setSpacing(10);
        ChoiceBox<String> tagChoiceBox = new ChoiceBox<>();
        tagChoiceBox.setStyle("-fx-color: pink;");
        tagChoiceBox.getItems().addAll("Assignment Due Date", "Bill Due Date", "Custom Event");
        tagChoiceBox.setValue("Custom Event");
        tagChoiceBoxContainer.getChildren().add(tagChoiceBox);

        Label dateLbl = new Label("Date and Category: ");
        dateLbl.setFont(font);
        HBox dayContainer = new HBox();
        dayContainer.setPadding(new Insets(10));
        dayContainer.setSpacing(10);
        ChoiceBox<Month> monthChoiceBox = new ChoiceBox<>();
        monthChoiceBox.getItems().addAll(Month.values());
        monthChoiceBox.setValue(Month.APRIL);
        monthChoiceBox.setStyle("-fx-color: pink;");
        ChoiceBox<Integer> dayChoiceBox = new ChoiceBox<>();
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
        ChoiceBox<Integer> hoursChoiceBox = new ChoiceBox<>();
        hoursChoiceBox.setStyle("-fx-color: pink;");
        hoursChoiceBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        hoursChoiceBox.setValue(12);
        Label colonLbl = new Label(" : ");
        colonLbl.setFont(Font.font("Arial",24));
        ChoiceBox<Integer> minChoiceBox = new ChoiceBox<>();
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



        primaryStage.setTitle("Add Schedule");

      //  popup.getContent().add(root);
        popup.setAutoHide(true);


        //Scene scene = new Scene(rootMain);

       // primaryStage.setScene(scene);
       //
        // primaryStage.show();

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(root));

        // Show the popup
        popupStage.show();

    }
}
