package edu.apsu.planner.handler;

import edu.apsu.planner.app.PlannerApplication;
import edu.apsu.planner.data.MonthInfo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;

import javafx.stage.FileChooser;

import java.io.*;

import java.time.Month;

public class FileEventHandler implements EventHandler<ActionEvent> {
    private final PlannerApplication app;

    public FileEventHandler(PlannerApplication app) {
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

    private void newPlanner() {
        MonthInfo[] months = new MonthInfo[12];
        for (int i = 0; i < months.length; i++) {
            months[i] = new MonthInfo(2024, Month.of(i + 1));
        }
        app.setMonths(months);
        app.updateUI();
    }

    private void save() {
        FileChooser fileChooser = setupFileChooser();
        fileChooser.setTitle("Save Planner");
        fileChooser.setInitialFileName("Untitled.pln");
        File selectedFile = fileChooser.showSaveDialog(app.getStage());
        try {
            if (selectedFile != null) {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(selectedFile));
                out.writeObject(app.getMonths());
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            displayAlert("File Save Error", "File could not be saved");
        }
    }

    private void loadPlanner() {
        FileChooser fileChooser = setupFileChooser();
        fileChooser.setTitle("Load Planner");
        File selectedFile = fileChooser.showOpenDialog(app.getStage());
        try {
            if (selectedFile != null) {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(selectedFile));
                MonthInfo[] months = (MonthInfo[]) in.readObject();
                app.setMonths(months);
                app.updateUI();
                in.close();
            }
        } catch (FileNotFoundException e) {
            displayAlert("Open Error", "The File was not found");
        } catch (IOException e) {
            displayAlert("Open Error", "File could not be opened");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private FileChooser setupFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Planner Files", "*.pln"));
        return fileChooser;
    }
    private void displayAlert(String title, String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(description);
        alert.showAndWait();
    }

    private void export() {

    }

    private void exit() {
        Platform.exit();
    }

    private class PdfBuilder {

    }
}
