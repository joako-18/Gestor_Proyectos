module com.example.gestor {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.gestor to javafx.fxml;
    exports com.example.gestor;
    exports com.example.gestor.models;
    exports com.example.gestor.controller;
    opens com.example.gestor.models to javafx.fxml;
    opens com.example.gestor.controller to javafx.fxml;
}