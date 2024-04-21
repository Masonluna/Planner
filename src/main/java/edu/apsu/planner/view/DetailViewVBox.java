package edu.apsu.planner.view;

import edu.apsu.planner.app.PlannerApplication;
import edu.apsu.planner.data.DayInfo;
import edu.apsu.planner.data.PlannerEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class DetailViewVBox extends VBox {
    PlannerApplication app;

    public DetailViewVBox(PlannerApplication app, DayInfo day) {
        super();

        this.app = app;
        this.setPadding(new Insets(5, 10, 5, 10));
        this.setPrefHeight(620);

        for (PlannerEvent event : day.getEvents()) {
            Paint paint = null;
            switch(event.getTag())
            {
                case CLASS -> paint = Color.BLUE;
                case WORK -> paint = Color.FORESTGREEN;
                case ASSIGNMENT -> paint = Color.PURPLE;
                case BILL -> paint = Color.RED;
                case CUSTOM -> paint = Color.AQUA;
            }
            BorderStroke borderStroke = new BorderStroke(paint, BorderStrokeStyle.SOLID,
                    new CornerRadii(5), BorderWidths.DEFAULT);
            int heightMultiplier = getHeightMultiplier(event);
            Label eventLabel = new Label(event.toString());
            eventLabel.setPrefWidth(130);
            eventLabel.setPadding(new Insets(10, 5, 10, 5));
            eventLabel.setBorder(new Border(borderStroke));
            if (heightMultiplier > 0)
                eventLabel.setPrefHeight(30 * heightMultiplier);
            else
                eventLabel.setPrefHeight(50);
            if (app.getTypes()[event.getTag().getValue()].isVisible())
                this.getChildren().add(eventLabel);
        }

    }

    private int getHeightMultiplier(PlannerEvent event) {
        int heightMultiplier;
        if (event.getStartingAmOrPm().equals(event.getEndingAmOrPm())) {
            if (event.getStartingHour() == event.getEndingHour()) {
                heightMultiplier = 0;
            }
            else if (event.getStartingHour() == 12) {
                heightMultiplier = event.getEndingHour();
            } else
                heightMultiplier = event.getEndingHour() - event.getStartingHour();
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
            heightMultiplier = preNoonDuration + postNoonDuration;
        }
        return heightMultiplier;
    }
}
