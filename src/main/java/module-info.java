module edu.apsu.planner {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.apsu.planner to javafx.fxml;
    exports edu.apsu.planner;
}