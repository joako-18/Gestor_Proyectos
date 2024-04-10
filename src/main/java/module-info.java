module com.example.gestor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gestor to javafx.fxml;
    exports com.example.gestor;
    exports com.example.gestor.models;
    opens com.example.gestor.models to javafx.fxml;
}