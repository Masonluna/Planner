package edu.apsu.planner.view;

import edu.apsu.planner.app.PlannerApplication;
import edu.apsu.planner.data.DayInfo;
import edu.apsu.planner.data.PlannerEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class DetailViewTimeGridPane extends GridPane {
    PlannerApplication app;
    private final DayInfo day;
    private final HBox sixAmHBox;
    private final HBox sevenAmHBox;
    private final HBox eightAmHBox;
    private final HBox nineAmHBox;
    private final HBox tenAmHBox;
    private final HBox elevenAmHBox;
    private final HBox twelvePmHBox;
    private final HBox onePmHBox;
    private final HBox twoPmHBox;
    private final HBox threePmHBox;
    private final HBox fourPmHBox;
    private final HBox fivePmHBox;
    private final HBox sixPmHBox;
    public DetailViewTimeGridPane(PlannerApplication app, DayInfo day) {
        super();

        this.day = day;
        this.app = app;
        this.setGridLinesVisible(true);

        sixAmHBox = new HBox();
        sixAmHBox.setPrefSize(200,50);
        Label sixAmLbl = new Label("6AM");
        sixAmLbl.setPadding(new Insets(0, 3, 0, 3));
        sixAmHBox.getChildren().addAll(sixAmLbl);
        this.add(sixAmHBox, 0, 1);

        sevenAmHBox = new HBox();
        sevenAmHBox.setPrefSize(200,50);
        Label sevenAmLbl = new Label("7AM");
        sevenAmLbl.setPadding(new Insets(0, 3, 0, 3));
        sevenAmHBox.getChildren().addAll(sevenAmLbl);
        this.add(sevenAmHBox, 0, 2);

        eightAmHBox = new HBox();
        eightAmHBox.setPrefSize(200,50);
        Label eightAmLbl = new Label("8AM");
        eightAmLbl.setPadding(new Insets(0, 3, 0, 3));
        eightAmHBox.getChildren().addAll(eightAmLbl);
        this.add(eightAmHBox, 0, 3);


        nineAmHBox = new HBox();
        nineAmHBox.setPrefSize(200,50);
        Label nineAmLbl = new Label("9AM");
        nineAmLbl.setPadding(new Insets(0, 3, 0, 3));
        nineAmHBox.getChildren().addAll(nineAmLbl);
        this.add(nineAmHBox, 0, 4);

        tenAmHBox = new HBox();
        tenAmHBox.setPrefSize(200,50);
        Label tenAmLbl = new Label("10AM");
        tenAmLbl.setPadding(new Insets(0, 3, 0, 3));
        tenAmHBox.getChildren().addAll(tenAmLbl);
        this.add(tenAmHBox, 0, 5);

        elevenAmHBox = new HBox();
        elevenAmHBox.setPrefSize(200,50);
        Label elevenAmLbl = new Label("11AM");
        elevenAmLbl.setPadding(new Insets(0, 3, 0, 3));
        elevenAmHBox.getChildren().addAll(elevenAmLbl);
        this.add(elevenAmHBox, 0, 6);

        twelvePmHBox = new HBox();
        twelvePmHBox.setPrefSize(200,50);
        Label twelvePmLbl = new Label("12PM");
        twelvePmLbl.setPadding(new Insets(0, 3, 0, 3));
        twelvePmHBox.getChildren().addAll(twelvePmLbl);
        this.add(twelvePmHBox, 0, 7);

        onePmHBox = new HBox();
        onePmHBox.setPrefSize(200,50);
        Label onePmLbl = new Label("1PM");
        onePmLbl.setPadding(new Insets(0, 3, 0, 3));
        onePmHBox.getChildren().addAll(onePmLbl);
        this.add(onePmHBox, 0, 8);

        twoPmHBox = new HBox();
        twoPmHBox.setPrefSize(200,50);
        Label twoPmLbl = new Label("2PM");
        twoPmLbl.setPadding(new Insets(0, 3, 0, 3));
        twoPmHBox.getChildren().addAll(twoPmLbl);
        this.add(twoPmHBox, 0, 9);

        threePmHBox = new HBox();
        threePmHBox.setPrefSize(200,50);
        Label threePmLbl = new Label("3PM");
        threePmLbl.setPadding(new Insets(0, 3, 0, 3));
        threePmHBox.getChildren().addAll(threePmLbl);
        this.add(threePmHBox, 0, 10);

        fourPmHBox = new HBox();
        fourPmHBox.setPrefSize(200,50);
        Label fourPmLbl = new Label("4PM");
        fourPmLbl.setPadding(new Insets(0, 3, 0, 3));
        fourPmHBox.getChildren().addAll(fourPmLbl);
        this.add(fourPmHBox, 0, 11);


        fivePmHBox = new HBox();
        fivePmHBox.setPrefSize(200,50);
        Label fivePmLbl = new Label("5PM");
        fivePmLbl.setPadding(new Insets(0, 3, 0, 3));
        fivePmHBox.getChildren().addAll(fivePmLbl);
        this.add(fivePmHBox, 0, 12);


        sixPmHBox = new HBox();
        sixPmHBox.setPrefSize(200,50);
        Label sixPmLbl = new Label("6PM");
        sixPmLbl.setPadding(new Insets(0, 3, 0, 3));
        sixPmHBox.getChildren().addAll(sixPmLbl);
        this.add(sixPmHBox, 0, 13);


        addEventsToGridPane();

    }

    private void addEventsToGridPane() {
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
            BackgroundFill backgroundFill = new BackgroundFill(paint, new CornerRadii(5), null);
            BorderStroke borderStroke = new BorderStroke(paint, BorderStrokeStyle.SOLID,
                    new CornerRadii(5), BorderWidths.DEFAULT);
            Label eventLabel = new Label(event.toString());
            eventLabel.setBorder(new Border(borderStroke));
            //eventLabel.setBackground(new Background(backgroundFill));
            if (app.getTypes()[event.getTag().getValue()].isVisible()) {
                switch (event.getStartingHour()) {
                    case 1:
                        onePmHBox.getChildren().add(eventLabel);
                        break;
                    case 2:
                        twoPmHBox.getChildren().add(eventLabel);
                        break;
                    case 3:
                        threePmHBox.getChildren().add(eventLabel);
                        break;
                    case 4:
                        fourPmHBox.getChildren().add(eventLabel);
                        break;
                    case 5:
                        fivePmHBox.getChildren().add(eventLabel);
                        break;
                    case 6:
                        if (event.getStartingAmOrPm().equals("AM"))
                            sixAmHBox.getChildren().add(eventLabel);
                        else
                            sixPmHBox.getChildren().add(eventLabel);
                        break;
                    case 7:
                        sevenAmHBox.getChildren().add(eventLabel);
                        break;
                    case 8:
                        eightAmHBox.getChildren().add(eventLabel);
                        break;
                    case 9:
                        nineAmHBox.getChildren().add(eventLabel);
                        break;
                    case 10:
                        tenAmHBox.getChildren().add(eventLabel);
                        break;
                    case 11:
                        elevenAmHBox.getChildren().add(eventLabel);
                        break;
                    case 12:
                        twelvePmHBox.getChildren().add(eventLabel);
                }
            }
        }
    }
}
