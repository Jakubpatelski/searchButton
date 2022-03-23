module com.example.searchbutton {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.searchbutton to javafx.fxml;
    exports com.example.searchbutton;
}