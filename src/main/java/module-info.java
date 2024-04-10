module com.example.gestor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gestor to javafx.fxml;
    exports com.example.gestor;
}