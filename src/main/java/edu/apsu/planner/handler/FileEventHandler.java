package edu.apsu.planner.handler;

import edu.apsu.planner.data.MonthInfo;
import edu.apsu.planner.view.monthViewUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

import java.time.Month;

public class FileEventHandler implements EventHandler<ActionEvent> {
    private MonthInfo[] months;
    private monthViewUI app;

    public FileEventHandler(monthViewUI app, MonthInfo[] months) {
        this.months = months;
        this.app = app;
    }
    @Override
    public void handle(ActionEvent event) {
        MenuItem eventSource = (MenuItem) event.getSource();
        switch (eventSource.getText()) {
            case "New":
                newPlanner();
                break;
            case "Save":
                save();
                break;
            case "Open":
                loadPlanner();
                break;
            case "Export to PDF":
                export();
                break;
            default:
                exit();
                break;
        }
    }

    public void newPlanner() {
        System.out.println("Calling newPlanner()");
        app.setMonths( new MonthInfo[12]);
        for (int i = 0; i < months.length; i++) {
            months[i] = new MonthInfo(2024, Month.of(i + 1));
        }
        app.setMonths(months);
        app.getRoot().setCenter(app.createCenterPane());
    }

    public void save() {

    }

    public void loadPlanner() {

    }

    public void export() {

    }

    public void exit() {
        Platform.exit();
    }

    private class PdfBuilder {

    }
}
