package edu.apsu.planner.view;

import edu.apsu.planner.app.PlannerApplication;
import edu.apsu.planner.data.DayInfo;
import edu.apsu.planner.data.PlannerEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class DetailViewVBox extends VBox {
    public PlannerApplication app;
    public double heightMultiplier;

    public DetailViewVBox(PlannerApplication app, DayInfo day) {
        super();

        this.app = app;
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(5, 10, 5, 10));
        this.setSpacing(5);
        this.setPrefHeight(600);

        for (PlannerEvent event : day.getEvents()) {
            Paint paint = null;
            switch(event.getTag())
            {
                case CLASS -> paint = Color.BLUE;
                case WORK -> paint = Color.FORESTGREEN;
                case ASSIGNMENT -> paint = Color.PURPLE;
                case BILL -> paint = Color.RED;
                case CUSTOM -> paint = Color.DARKORANGE;
            }
            BorderStroke borderStroke = new BorderStroke(paint, BorderStrokeStyle.SOLID,
                    new CornerRadii(5), BorderWidths.DEFAULT);
            heightMultiplier = getHeightMultiplier(event);
            Label eventLabel;
            if (heightMultiplier < 2)
                eventLabel = new Label(event.toString());
            else
                eventLabel = new Label(event.toStringWithDescription());
            eventLabel.setPrefWidth(130);
            eventLabel.setWrapText(true);
            eventLabel.setPadding(new Insets(10, 5, 10, 5));
            eventLabel.setBorder(new Border(borderStroke));
            if (heightMultiplier > 0)
                eventLabel.setPrefHeight(60 * heightMultiplier);
            else
                eventLabel.setPrefHeight(60);
            if (app.getTypes()[event.getTag().getValue()].isVisible())
                this.getChildren().add(eventLabel);
        }

    }

    private double getHeightMultiplier(PlannerEvent event) {
        double heightMultiplier;
        int endingMinute = event.getEndingMinute();
        System.out.println("Ending Minute: " + endingMinute);
        if (event.getEndingMinute() == 0) {
            endingMinute = 60;
        }
        double minutePercentage = (endingMinute - event.getStartingMinute()) / 60.0;
        System.out.println(minutePercentage);
        if (minutePercentage == 1)
            minutePercentage = 0;
        if (event.getStartingAmOrPm().equals(event.getEndingAmOrPm())) {
            System.out.println("First if statement");
            if (event.getStartingHour() == event.getEndingHour()) {
                heightMultiplier = 0;
            }
            else if (event.getStartingHour() == 12) {
                heightMultiplier = event.getEndingHour();
            } else
                heightMultiplier = event.getEndingHour() - event.getStartingHour() + minutePercentage;
        } else {
            int preNoonDuration;
            int postNoonDuration;
            if (event.getStartingHour() == 12)
                preNoonDuration = 12;
            else
                preNoonDuration = 12 - event.getStartingHour();
            if (event.getEndingHour() == 12)
                postNoonDuration = 0;
            else
                postNoonDuration = event.getEndingHour();
            if (minutePercentage != 1.0) {
                heightMultiplier = preNoonDuration + postNoonDuration + minutePercentage;
            } else
                heightMultiplier = preNoonDuration + postNoonDuration + minutePercentage;
        }
        return heightMultiplier;
    }
}
