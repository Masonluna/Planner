package edu.apsu.planner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class welcomepageUI extends Application {
    TextField usernameTextField;
    PasswordField passwordField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #B7B7B7;");
        root.setPadding(new Insets(10));
        root.setPrefSize(1000, 600);


        //topPane
        Label welcomeLabel = new Label("Welcome to the Planner Application! Enter Creditals.");
        Font font = new Font("Arial", 40);
        BorderPane.setMargin(welcomeLabel, new Insets(10));
        welcomeLabel.setFont(font);
        welcomeLabel.setPrefSize(1000, 50);
        root.setTop(welcomeLabel);

        //leftPane
        String imageResource = getClass().getResource("UntitledDesign.png").toString();
        ImageView displayImage = new ImageView(imageResource);
        Label imageView = new Label();
        BorderPane.setMargin(imageView, new Insets(10));
        imageView.setPrefSize(200, 200);
        imageView.setGraphic(displayImage);
        root.setLeft(imageView);
        displayImage.getImage();

        //centerPane
        VBox centerPane = new VBox();
        BorderPane.setMargin(centerPane, new Insets(10));
        centerPane.setSpacing(10);

        HBox usernameContainer = new HBox();
        usernameContainer.setAlignment(Pos.CENTER_RIGHT);
        usernameContainer.setSpacing(10);

        Label usernameLabel = new Label("Username:");
        Font font2 = new Font("Arial", 24);
        usernameLabel.setFont(font2);
        usernameTextField = new TextField();
        usernameTextField.setFont(font2);
        usernameTextField.setPromptText("Enter username");
        usernameContainer.getChildren().addAll(usernameLabel, usernameTextField);

        HBox passwordContainer = new HBox();
        passwordContainer.setAlignment(Pos.CENTER_RIGHT);
        passwordContainer.setSpacing(10);

        Label passwordLabel = new Label("Password: ");
        passwordLabel.setFont(font2);
         passwordField = new PasswordField();
        passwordField.setFont(font2);
        passwordField.setPromptText("Enter password");
        passwordContainer.getChildren().addAll(passwordLabel, passwordField);

        HBox buttonContainer = new HBox();
        buttonContainer.setAlignment(Pos.CENTER_RIGHT);
        buttonContainer.setSpacing(10);
        Button enterButton = new Button("Enter");
        enterButton.setOnAction(event->{
            String username = usernameTextField.getText() ;
            String password = passwordField.getText();
                    boolean foundUsername = checkForCorrectLogin("loginCreditals.txt", username);
                    boolean foundPassword = checkForCorrectLogin("loginCreditals.txt", password);

                    if (foundUsername && foundPassword){
                        Alert correctAlert = new Alert(Alert.AlertType.INFORMATION);
                        correctAlert.setTitle("Successful login in");
                        correctAlert.setHeaderText("Welcome");
                        correctAlert.showAndWait();
                    } else {
                        Alert incorrectAlert = new Alert(Alert.AlertType.ERROR);
                        incorrectAlert.setTitle("Failed login in attempt");
                        incorrectAlert.setHeaderText("Try again");
                        incorrectAlert.showAndWait();                      }
                    usernameTextField.clear();
                    passwordField.clear();
                }
                );
        enterButton.setFont(font2);
        Button newUserButton = new Button("New User");
        newUserButton.setOnAction(event ->
                {
                    saveLoginCreditals();
                    usernameTextField.clear();
                    passwordField.clear();

                }
        );
        newUserButton.setFont(font2);
        buttonContainer.getChildren().addAll(newUserButton, enterButton);


        centerPane.getChildren().addAll(usernameContainer, passwordContainer, buttonContainer);
        root.setCenter(centerPane);

        Scene scene = new Scene(root);
        stage.setTitle("Planer Application");
        stage.setScene(scene);
        stage.show();


    }

    public void saveLoginCreditals() {

        String filePath = "loginCreditals.txt";
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(usernameTextField.getText()+ "\n");
            writer.write(passwordField.getText()+ "\n\n");
            System.out.println("Successfully wrote to the file.");
            Alert saveAlert = new Alert(Alert.AlertType.INFORMATION);
            saveAlert.setTitle("Saved");
            saveAlert.setHeaderText("Your login creditals have been saved.");
            saveAlert.showAndWait();

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private boolean checkForCorrectLogin(String filePath, String searchText) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchText)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }}


