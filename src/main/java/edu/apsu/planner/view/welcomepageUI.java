package edu.apsu.planner.view;

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

import java.io.*;

public class welcomepageUI extends Application {
    TextField usernameTextField;
    PasswordField passwordField;
    private final String LOGIN_FILE_PATH = "loginCredentials.txt";

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
        Label welcomeLabel = new Label("Welcome to the Planner Application! Enter Credentials.");
        Font font = new Font("Arial", 35);
        BorderPane.setMargin(welcomeLabel, new Insets(10));
        welcomeLabel.setFont(font);
        welcomeLabel.setPrefSize(1000, 50);
        root.setTop(welcomeLabel);

        //leftPane
        String imageResource = getClass().getResource("/edu/apsu/planner/logoImage.png").toString();
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
                    boolean foundUsername = checkForCorrectLogin(username);
                    boolean foundPassword = checkForCorrectLogin(password);

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
                    saveLoginCredentials();
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

    public void saveLoginCredentials() {


        try (FileWriter writer = new FileWriter(LOGIN_FILE_PATH, true)) {
            String username = usernameTextField.getText();
            String password = passwordField.getText();
            boolean userExists = checkIfUserExists(username);
            Alert saveAlert = new Alert(Alert.AlertType.INFORMATION);
            if (!username.isBlank() && !password.isBlank() && !userExists) {
                writer.write("U:" + username + "\n");
                writer.write("P:" + password + "\n\n");
                System.out.println("Successfully wrote to the file.");
                saveAlert.setAlertType(Alert.AlertType.INFORMATION);
                saveAlert.setTitle("Saved");
                saveAlert.setHeaderText("Your login credentials have been saved.");
            } else {
                saveAlert.setAlertType(Alert.AlertType.WARNING);
                saveAlert.setTitle("Failed to save");
                if (userExists) {
                    saveAlert.setHeaderText("User already exists");
                } else {
                    saveAlert.setHeaderText("Cannot save empty login credentials.");
                }
            }
            saveAlert.showAndWait();

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean checkIfUserExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(LOGIN_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("U:" + username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }


    private boolean checkForCorrectLogin(String searchText) {
        try (BufferedReader reader = new BufferedReader(new FileReader(LOGIN_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    if (line.substring(2).equals(searchText)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }}


