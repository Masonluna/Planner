module edu.apsu.planner {
    requires javafx.controls;
    requires javafx.fxml;
    requires itextpdf;
    requires org.apache.commons.text;



    opens edu.apsu.planner to javafx.fxml;

    exports edu.apsu.planner.view;
    opens edu.apsu.planner.view to javafx.fxml;
    opens edu.apsu.planner.handler to javafx.fxml;


}