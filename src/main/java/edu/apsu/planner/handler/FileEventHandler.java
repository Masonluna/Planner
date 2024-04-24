package edu.apsu.planner.handler;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import edu.apsu.planner.app.PlannerApplication;
import edu.apsu.planner.data.MonthInfo;
import edu.apsu.planner.data.User;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;

import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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

    public void newPlanner() {
        MonthInfo[] months = new MonthInfo[12];
        for (int i = 0; i < months.length; i++) {
            months[i] = new MonthInfo(2024, Month.of(i + 1));
        }
        app.setMonths(months);
        app.updateUI();
    }

    private void save() {
        FileChooser fileChooser = setupFileChooser("Planner Files", "*.pln");
        fileChooser.setTitle("Save Planner");
        fileChooser.setInitialFileName("Untitled.pln");
        File selectedFile = fileChooser.showSaveDialog(app.getStage());
        try {
            if (selectedFile != null) {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(selectedFile));
                out.writeObject(app.getCurrentUser());
                out.writeObject(app.getMonths());
                out.close();
            }
        } catch (IOException e) {
            displayAlert("File Save Error", "File could not be saved",
                    "Could not write Planner to file");
        }
    }

    private void loadPlanner() {
        FileChooser fileChooser = setupFileChooser("Planner Files", "*.pln");
        fileChooser.setTitle("Load Planner");
        File selectedFile = fileChooser.showOpenDialog(app.getStage());
        try {
            if (selectedFile != null) {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(selectedFile));
                User tempUser = (User) in.readObject();
                if (!app.getCurrentUser().equals(tempUser)) {
                    Alert openAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    openAlert.setTitle("Open Error");
                    openAlert.setHeaderText("Invalid User");
                    openAlert.setContentText("User credentials do not match. Would you like to change users?");
                    openAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
                    ButtonType result = openAlert.showAndWait().orElse(ButtonType.NO);
                    if (result.equals(ButtonType.YES)) {
                        newPlanner();
                        app.switchScene(app.getWelcomeScene());
                    }
                    in.close();
                    return;
                }

                MonthInfo[] months = (MonthInfo[]) in.readObject();

                app.setMonths(months);
                app.updateUI();
                in.close();
            }
        } catch (FileNotFoundException e) {
            displayAlert("Open Error", "The File was not found", "");
        } catch (IOException e) {
            displayAlert("Open Error", "Planner could not be opened", "Failed to read from file");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private FileChooser setupFileChooser(String fileType, String fileExtension) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(fileType, fileExtension));
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter(fileType, fileExtension));
        return fileChooser;
    }

    private void displayAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        if (!content.isBlank())
            alert.setContentText(content);
        alert.showAndWait();
    }

    private void export() {
        PdfBuilder builder = new PdfBuilder(app);
        try {
            builder.build();
        } catch (IOException | BadElementException e) {
            throw new RuntimeException(e);
        }
    }

    private void exit() {
        Platform.exit();
    }

    private class PdfBuilder {
        private final PlannerApplication app;

        public PdfBuilder(PlannerApplication app) {
            this.app = app;
        }

        public void build() throws IOException, BadElementException {
            Document document = new Document();
            WritableImage image;
            if (app.getStage().getScene().equals(app.getMonthViewScene())) {
                image = app.getMonthViewUI().getCenter().snapshot(null, null);
            } else {
                image = app.getWeekViewUI().getCenter().snapshot(null, null);
            }
            Rectangle pageSize = new Rectangle((float) image.getWidth() + 50, (float) image.getHeight() + 50);
            document.setPageSize(pageSize);

            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", out);

            com.itextpdf.text.Image pdfImage = com.itextpdf.text.Image.getInstance(bufferedImage, null);
            FileChooser fileChooser = setupFileChooser("Pdf Files", "*.pdf");
            fileChooser.setTitle("Export to PDF");
            fileChooser.setInitialFileName("Untitled.pdf");
            File selectedFile = fileChooser.showSaveDialog(app.getStage());

            if (selectedFile != null) {
                try {
                    PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
                    document.open();
                    document.add(pdfImage);
                    document.close();
                } catch (DocumentException e) {
                    displayAlert("Export Error", "File could not be created", "Failed to upload image");
                } catch (FileNotFoundException e) {
                    displayAlert("Export Error", "File was not found", "");
                }
            }
        }
    }
}
