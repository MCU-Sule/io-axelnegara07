module com.example.pt07_2072029 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.pt07_2072029 to javafx.fxml;
    exports com.example.pt07_2072029;
    exports com.example.pt07_2072029.model;
    opens com.example.pt07_2072029.model to com.google.gson;
}