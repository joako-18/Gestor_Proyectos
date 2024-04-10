package com.example.gestor.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.gestor_proyecto.App;
import org.example.gestor_proyecto.models.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegistroController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button btn_admin;
    @FXML
    private Button btn_back;
    @FXML
    private ComboBox<String> combores;
    @FXML
    private PasswordField texin_newPass;
    @FXML
    private TextField texin_newUss;
    @FXML
    private PasswordField texin_pass2;

    @FXML
    void btn_admin(MouseEvent event) {
        String usuario = texin_newUss.getText();
        String contraseña = texin_newPass.getText();
        String repetir = texin_pass2.getText();
        if (!usuario.isEmpty() || !contraseña.isEmpty() || !repetir.isEmpty()){
            if (Objects.equals(contraseña, repetir)) {
                String fin = contraseña;
                if (fin.length() == 6) {
                        Usuario.agregarUsuario(usuario, fin);
                        texin_newUss.clear();
                        texin_newPass.clear();
                        texin_pass2.clear();
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("La contraseña tiene que ser de 6 digitos");
                    alert.showAndWait();
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Las contraseñas no coincide");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Campos vacios");
            alert.showAndWait();
        }
    }
    @FXML
    void btn_back(MouseEvent event) {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login-view.fxml"));
        try {
            Pane root = fxmlLoader.load();
            Scene scene= new Scene(root);
            LoginController controller= fxmlLoader.getController();
            controller.initialize();
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Node source = (Node) event.getSource();
        stage = (Stage) source.getScene().getWindow();stage.close();
    }
    @FXML
    void initialize() {
    }

    public void closeWindows() {
    }
}