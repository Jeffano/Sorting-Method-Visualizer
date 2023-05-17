module assignment1.assignment1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens assignment1.assignment1 to javafx.fxml;
    exports assignment1.assignment1;
}