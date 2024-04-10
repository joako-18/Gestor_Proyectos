package com.example.gestor.controller;

import com.example.gestor.App;
import com.example.gestor.models.Empleado;
import com.example.gestor.models.Tarea;
import com.example.gestor.models.TipoProyecto;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;

public class CrearEmpleadoController {

    private ArrayList<Empleado> empleadoArrayList;
    private ArrayList<TipoProyecto> tipoProyectoArrayList;

    private ArrayList<Tarea> tareaArrayList;

    @FXML
    private TextField areatrabajoTextField;

    @FXML
    private TextField IDTextField;

    @FXML
    private Button guardarButton;

    @FXML
    private TextField nombreempleadoTextField;
    @FXML
    private TextField apellidoempleadoTextField;

    private Empleado empleado;

    @FXML
    private ObservableList<Empleado> empleados;
    @FXML
    private ListView<Empleado> empleadoListView;


    public void initialize() {
        this.empleadoArrayList = empleadoArrayList;
        this.tipoProyectoArrayList = tipoProyectoArrayList;
        this.tareaArrayList = tareaArrayList;
    }

    public void initAttributtes(ObservableList<Empleado> empleados) {
        this.empleados = empleados;
    }

    public void initAttributtes(ObservableList<Empleado> empleados, Empleado p) {
        this.empleados = empleados;
        this.empleado = p;

        this.nombreempleadoTextField.setText(p.getNombreEmpleado());
        this.areatrabajoTextField.setText(p.getWorkArea());
        this.IDTextField.setText(p.getID() + "");
        this.apellidoempleadoTextField.setText(p.getApellidoEmpleado());
    }

    @FXML
    private void guardarempleado(ActionEvent event) {
        String nombre = this.nombreempleadoTextField.getText();
        String areatrabajo = this.areatrabajoTextField.getText();
        int ID = Integer.parseInt(this.IDTextField.getText());
        String apellido = this.apellidoempleadoTextField.getText();

        Empleado p = new Empleado(nombre, areatrabajo, ID, apellido);

        if (!empleados.contains(p)) {
            if (this.empleado != null) {
                this.empleado.setNombreEmpleado(nombre);
                this.empleado.setWorkArea(areatrabajo);
                this.empleado.setID(ID);
                this.empleado.setApellidoEmpleado(apellido);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Informacion");
                alert.setContentText("Se ha modificado correctamente");
                alert.showAndWait();
            } else {
                this.empleado = p;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Informacion");
                alert.setContentText("Se ha añadido correctamente");
                alert.showAndWait();
            }
            ver();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("vistasEmpleado.fxml"));
            try {
                Pane root = fxmlLoader.load();
                Scene scene = new Scene(root);
                EmpleadoVistasController controller = fxmlLoader.getController();
                controller.setEmpleadoArrayList(empleadoArrayList);
                controller.setTipoProyectoArrayList(tipoProyectoArrayList);
                controller.setTareaArrayList(tareaArrayList);
                controller.initialize();
                scene.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
                stage.setScene(scene);
                stage.setResizable(false);
                stage.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El empleado ya existe");
            alert.showAndWait();
        }
        ver();
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleadoArrayList(ArrayList<Empleado> empleadoArrayList) {
        this.empleadoArrayList = empleadoArrayList;
    }

    public void setTipoProyectoArrayList(ArrayList<TipoProyecto> tipoProyectoArrayList) {
        this.tipoProyectoArrayList = tipoProyectoArrayList;
    }

    public void setTareaArrayList(ArrayList<Tarea> tareaArrayList) {
        this.tareaArrayList = tareaArrayList;
    }

    public ObservableList<Empleado> getEmpleados() {
        return empleados;
    }

    public void ver() {
        for (Empleado empleado1 : empleadoArrayList) {
            System.out.println(empleado1.toString());
        }
    }
}

