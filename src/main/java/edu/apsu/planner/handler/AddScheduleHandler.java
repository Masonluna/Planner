package edu.apsu.planner.handler;

import edu.apsu.planner.app.PlannerApplication;
import edu.apsu.planner.data.DayInfo;
import edu.apsu.planner.data.MonthInfo;
import edu.apsu.planner.data.PlannerEvent;
import edu.apsu.planner.data.Tag;
import edu.apsu.planner.view.monthViewUI;
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
import javafx.stage.Stage;

import java.time.Month;
import java.time.YearMonth;

public class AddScheduleHandler implements EventHandler<ActionEvent> {

    private TextField titleOfEventTF;
    private TextArea descriptionOfEventTA;
    private ChoiceBox<String> tagChoiceBox;
    private ChoiceBox<Integer> dayChoiceBox;
    private ChoiceBox<Month> monthChoiceBox;
    private ChoiceBox<Month> monthEndChoiceBox;
    private ChoiceBox<Integer> dayEndChoiceBox;
    private ChoiceBox<Integer> hoursChoiceBox;
    private ChoiceBox<Integer> minChoiceBox;
    private ChoiceBox<String> amPmChoiceBox;
    private ChoiceBox<Integer> endHoursChoiceBox;
    private ChoiceBox<Integer> endMinChoiceBox;
    private ChoiceBox<String> endAmPmChoiceBox;
    private CheckBox[] dayOfWeekCheckBoxes;
    private Stage popupStage;
    private final monthViewUI monthViewUI;
    private final PlannerApplication app;
    public AddScheduleHandler(PlannerApplication app, monthViewUI monthViewUI) {
        super();
        this.app = app;
        this.monthViewUI = monthViewUI;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        BorderPane root = new BorderPane();
        root.setPrefSize(650,300);
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
        titleOfEventTF.setFont(Font.font("Arial", 16));
        titleOfEventTF.setPromptText("Enter title of your event");

        descriptionOfEventTA = new TextArea();
        descriptionOfEventTA.setFont(font);
        descriptionOfEventTA.setStyle("-fx-control-inner-background: pink;");
        descriptionOfEventTA.setPromptText("Enter a description of event here");
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
        tagChoiceBox.getItems().addAll("Class Schedule", "Work Schedule", "Custom Schedule");
        tagChoiceBox.setValue("Class Schedule");
        tagChoiceBoxContainer.getChildren().add(tagChoiceBox);

        Label dateLbl = new Label("Date and Category: ");
        dateLbl.setFont(font);
        VBox endStartContainer = new VBox();
        Label startDateLbl = new Label("Date when schedule starts:");
        startDateLbl.setFont(Font.font("Arial",16));
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
        endStartContainer.getChildren().addAll(startDateLbl, dayContainer);

        VBox endDayContainer1 = new VBox();
        HBox endDayContainer = new HBox();
        Label endDateLbl = new Label("Date when schedule ends:");
        endDateLbl.setFont(Font.font("Arial",16));
        endDayContainer.setPadding(new Insets(10));
        endDayContainer.setSpacing(10);
        monthEndChoiceBox = new ChoiceBox<>();
        monthEndChoiceBox.getItems().addAll(Month.values());
        monthEndChoiceBox.setValue(Month.APRIL);
        monthEndChoiceBox.setStyle("-fx-color: pink;");
        dayEndChoiceBox = new ChoiceBox<>();
        monthEndChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int daysInMonth = YearMonth.of(YearMonth.now().getYear(), newValue).lengthOfMonth();
                dayEndChoiceBox.getItems().clear();
                for (int i = 1; i <= daysInMonth; i++) {
                    dayEndChoiceBox.getItems().add(i);
                }
                dayEndChoiceBox.setValue(1); // Set default day value
            }
        });
        dayEndChoiceBox.setValue(1);
        dayEndChoiceBox.setStyle("-fx-color: pink;");
        endDayContainer.getChildren().addAll(monthEndChoiceBox, dayEndChoiceBox);
        endDayContainer1.getChildren().addAll(endDateLbl, endDayContainer);


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
        amPmChoiceBox = new ChoiceBox<>();
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

        endHoursChoiceBox = new ChoiceBox<>();
        endHoursChoiceBox.setStyle("-fx-color: pink;");
        endHoursChoiceBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        endHoursChoiceBox.setValue(12);
        Label endColonLbl = new Label(" : ");
        endColonLbl.setFont(Font.font("Arial",24));
        endMinChoiceBox = new ChoiceBox<>();
        endMinChoiceBox.setStyle("-fx-color: pink;");
        endMinChoiceBox.getItems().addAll(0, 5,10,15,20,25,30,35,40,45,50,55);
        endMinChoiceBox.setValue(0);
        endAmPmChoiceBox = new ChoiceBox<>();
        endAmPmChoiceBox.setStyle("-fx-color: pink;");
        endAmPmChoiceBox.getItems().addAll("AM", "PM");
        endAmPmChoiceBox.setValue("AM");

        endTimeContainer.getChildren().addAll(endHoursChoiceBox, endColonLbl, endMinChoiceBox, endAmPmChoiceBox);

        VBox rightMostVBox = new VBox();
        rightMostVBox.setPadding(new Insets(10));
        rightMostVBox.setSpacing(10);
        rightMostVBox.setPrefSize(250,200);
        Label dayOFWeekLbl = new Label("Select days which Schedule occurs on:");
        dayOFWeekLbl.setFont(font);
        dayOFWeekLbl.setWrapText(true);

        dayOfWeekCheckBoxes = new CheckBox[7];

        CheckBox sundayCheckBox = new CheckBox("Sunday");
        sundayCheckBox.setFont(font);
        sundayCheckBox.setStyle("-fx-color: pink;");
        dayOfWeekCheckBoxes[0] = sundayCheckBox;

        CheckBox mondayCheckBox = new CheckBox("Monday");
        mondayCheckBox.setFont(font);
        mondayCheckBox.setStyle("-fx-color: pink;");
        dayOfWeekCheckBoxes[1] = mondayCheckBox;

        CheckBox tuesdayCheckBox = new CheckBox("Tuesday");
        tuesdayCheckBox.setFont(font);
        tuesdayCheckBox.setStyle("-fx-color: pink;");
        dayOfWeekCheckBoxes[2] = tuesdayCheckBox;

        CheckBox wednesdayCheckBox = new CheckBox("Wednesday");
        wednesdayCheckBox.setFont(font);
        wednesdayCheckBox.setStyle("-fx-color: pink;");
        dayOfWeekCheckBoxes[3] = wednesdayCheckBox;

        CheckBox thursdayCheckBox = new CheckBox("Thursday");
        thursdayCheckBox.setFont(font);
        thursdayCheckBox.setStyle("-fx-color: pink;");
        dayOfWeekCheckBoxes[4] = thursdayCheckBox;

        CheckBox fridayCheckBox = new CheckBox("Friday");
        fridayCheckBox.setFont(font);
        fridayCheckBox.setStyle("-fx-color: pink;");
        dayOfWeekCheckBoxes[5] = fridayCheckBox;

        CheckBox saturdayCheckBox = new CheckBox("Saturday");
        saturdayCheckBox.setFont(font);
        saturdayCheckBox.setStyle("-fx-color: pink;");
        dayOfWeekCheckBoxes[6] = saturdayCheckBox;



        rightMostVBox.getChildren().addAll(dayOFWeekLbl, sundayCheckBox,mondayCheckBox, tuesdayCheckBox, wednesdayCheckBox, thursdayCheckBox, fridayCheckBox, saturdayCheckBox);



        rightSideVBox.getChildren().addAll(dateLbl, tagChoiceBoxContainer , endStartContainer, endDayContainer1, startTimeLbl, startTimeContainer, endTimeLbl, endTimeContainer);

        popUpContainerHBox.getChildren().addAll(leftSideVBox, rightSideVBox, rightMostVBox);
        root.setCenter(popUpContainerHBox);


        popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(root));

        // Show the popup
        popupStage.show();
    }

    private void addPlannerEvent() {

        String eventName = titleOfEventTF.getText().trim();
        String eventDescription = descriptionOfEventTA.getText().trim();

        int startingHour = hoursChoiceBox.getValue();
        int startingMin = minChoiceBox.getValue();
        String startingAmOrPm = amPmChoiceBox.getValue();

        int endingHour = endHoursChoiceBox.getValue();
        int endingMin = endMinChoiceBox.getValue();
        String endingAmOrPm = endAmPmChoiceBox.getValue();
        Month startMonth = monthChoiceBox.getSelectionModel().getSelectedItem();
        int startDay = dayChoiceBox.getSelectionModel().getSelectedItem();
        Month endMonth = monthEndChoiceBox.getSelectionModel().getSelectedItem();
        int endDay = dayEndChoiceBox.getSelectionModel().getSelectedItem();
        DayInfo currentDayInfo = monthViewUI.getMonths()[startMonth.getValue() - 1].getDayOf(startDay);
        Month currentMonth = startMonth;
        MonthInfo currentMonthInfo = monthViewUI.getMonths()[currentMonth.getValue() - 1];
        DayInfo endDayInfo = monthViewUI.getMonths()[endMonth.getValue() - 1].getDayOf(endDay);
        do {
            System.out.println("in while loop");
            int dayOfWeekValue = currentDayInfo.getDate().getDayOfWeek().getValue() % 7;
            if (dayOfWeekCheckBoxes[dayOfWeekValue].isSelected()) {
                PlannerEvent plannerEvent = getPlannerEvent(eventName, eventDescription, startingHour, startingMin,
                        startingAmOrPm, endingHour, endingMin, endingAmOrPm);
                currentDayInfo.getEvents().add(plannerEvent);
                currentDayInfo.sortEvents();
            }

            //If we're NOT at the end of the month
            if (currentDayInfo.getDate().getDayOfMonth() < currentMonth.length(currentDayInfo.getDate().isLeapYear()))
            {
                currentDayInfo = currentMonthInfo.getDayOf(currentDayInfo.getDate().getDayOfMonth() + 1);
            } else {
                currentMonth = currentMonth.plus(1);
                currentMonthInfo = monthViewUI.getMonths()[currentMonth.getValue() - 1];
                currentDayInfo = currentMonthInfo.getDayOf(1);
            }
        } while (currentDayInfo.getDate().getDayOfYear() !=
                (endDayInfo.getDate().getDayOfYear() + 1) % endDayInfo.getDate().lengthOfYear());
        popupStage.close();
        app.updateUI();

    }


    private PlannerEvent getPlannerEvent(String eventName, String eventDescription, int startingHour, int startingMinute, String startingAmOrPm,
                                         int endingHour, int endingMinute, String endingAmOrPm) {
        String tagChoice = tagChoiceBox.getSelectionModel().getSelectedItem();
        Tag tag = switch (tagChoice) {
            case "Class Schedule" -> Tag.CLASS;
            case "Work Schedule" -> Tag.WORK;
            case "Custom Schedule" -> Tag.CUSTOM;
            default -> null;
        };
        return new PlannerEvent(eventName, eventDescription, startingHour, startingMinute, startingAmOrPm,
                endingHour, endingMinute, endingAmOrPm, tag);
    }

}
