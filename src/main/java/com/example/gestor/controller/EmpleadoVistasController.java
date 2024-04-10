package com.example.gestor.controller;

import com.example.gestor.App;
import com.example.gestor.models.Empleado;
import com.example.gestor.models.Tarea;
import com.example.gestor.models.TipoProyecto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmpleadoVistasController implements Initializable {
        @FXML
        private TextField areatrabajoTextField;

        @FXML
        private TextField IDTextField;

        @FXML
        private TextField nombreempleadoTextField;

        @FXML
        private Button CrearEmpleButton;

        @FXML
        private Button EliminarEmpleButton;

        @FXML
        private Button ModEmpleButton;

        @FXML
        private TableColumn<Empleado, Integer> ID;

        @FXML
        private TableView<Empleado> EmpleadoTableView;

        @FXML
        private TableColumn<Empleado, String> areaTrabajo;

        @FXML
        private TableColumn<Empleado, String> desEmpleado;

        @FXML
        private TableColumn<Empleado, String> nomEmpleado;

        private ObservableList<Empleado> empleados;

        @FXML
        private ListView<Empleado> empleadoListView;

        @FXML
        private Button volverButton;
        private ArrayList<Empleado> empleadoArrayList;
        private ArrayList<TipoProyecto>tipoProyectoArrayList;

        private ArrayList<Tarea>tareaArrayList;

        private CrearEmpleadoController controller;

        @FXML
        private Button verButton;

        private Empleado empleado;


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                empleados = FXCollections.observableArrayList();
                this.EmpleadoTableView.setItems(empleados);
                this.nomEmpleado.setCellValueFactory(new PropertyValueFactory("nombreEmpleado"));
                this.areaTrabajo.setCellValueFactory(new PropertyValueFactory("workArea"));
                this.ID.setCellValueFactory(new PropertyValueFactory("ID"));
                this.desEmpleado.setCellValueFactory(new PropertyValueFactory("apellidoEmpleado"));


        }

        @FXML
        void crearButton(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/gestor_proyecto/crearempleado-view.fxml"));
                try {
                        Parent root = loader.load();

                        CrearEmpleadoController empleadoController = loader.getController();
                        empleadoController.initAttributtes(empleados);
                        empleadoController.setEmpleadoArrayList(empleadoArrayList);
                        empleadoController.setTipoProyectoArrayList(tipoProyectoArrayList);
                        empleadoController.setTareaArrayList(tareaArrayList);

                        setController(empleadoController);

                        Scene scene = new Scene(root);
                        scene.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(scene);
                        stage.showAndWait();


                        Empleado p = empleadoController.getEmpleado();
                        if (p != null) {
                                this.empleados.add(p);
                                empleadoArrayList.add(p);
                                this.EmpleadoTableView.refresh();
                        }

                } catch (IOException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Error");
                        alert.setContentText(ex.getMessage());
                        alert.showAndWait();
                }

        }
        @FXML
        void EliminarButton(ActionEvent event) {
                Empleado p = this.EmpleadoTableView.getSelectionModel().getSelectedItem();

                if (p == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Error");
                        alert.setContentText("Debes seleccionar un empleado");
                        alert.showAndWait();
                } else {

                        this.empleados.remove(p);
                        this.EmpleadoTableView.refresh();

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Info");
                        alert.setContentText("Se ha borrado el empleado");
                        alert.showAndWait();

                }
        }

        @FXML
        void verButton(ActionEvent event) {
                CrearEmpleadoController crearEmpleadoController = new CrearEmpleadoController();
                ObservableList<Empleado>empleados = crearEmpleadoController.getEmpleados();

                        nomEmpleado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellidoEmpleado()));
                        desEmpleado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreEmpleado()));
                        areaTrabajo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWorkArea()));
                        ID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getID()).asObject());

                        EmpleadoTableView.setItems(observableList());

                System.out.println("Lista de empleados obtenida: " + empleados);
                }
        @FXML
        void ModButton(ActionEvent event) {
                Empleado p = this.EmpleadoTableView.getSelectionModel().getSelectedItem();

                if (p == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Error");
                        alert.setContentText("Debe seleccionar empleado");
                        alert.showAndWait();
                } else {
                        try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/gestor_proyecto/crearempleado-view.fxml"));

                                Parent root = loader.load();

                                CrearEmpleadoController controller = loader.getController();
                                controller.initAttributtes(empleados, p);

                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.setScene(scene);
                                stage.showAndWait();

                                Empleado aux = controller.getEmpleado();
                                if (aux != null) {
                                        this.EmpleadoTableView.refresh();
                                }

                        } catch (IOException ex) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setHeaderText(null);
                                alert.setTitle("Error");
                                alert.setContentText(ex.getMessage());
                                alert.showAndWait();
                        }
                }
        }

        @FXML
        void volverButton(ActionEvent event) {
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Menu-view.fxml"));
                try {
                        Pane root = fxmlLoader.load();
                        Scene scene= new Scene(root);
                        HomeController homeController= fxmlLoader.getController();
                        homeController.setEmpleadoArrayList(empleadoArrayList);
                        homeController.setTipoProyectoArrayList(tipoProyectoArrayList);
                        homeController.setTareaArrayList(tareaArrayList);
                        homeController.initialize();
                        stage.setScene(scene);
                        scene.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
                        stage.setResizable(false);
                        stage.show();
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
                ver();
                Node source = (Node) event.getSource();
                stage = (Stage) source.getScene().getWindow();stage.close();
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

        public void initialize() {
                this.empleadoArrayList= empleadoArrayList;
                this.tipoProyectoArrayList = tipoProyectoArrayList;
                this.tareaArrayList= tareaArrayList;
        }

        public void ver(){
                for(Empleado empleado1 : empleadoArrayList){
                        System.out.println(empleado1.toString());
                }
        }

        public void setController(CrearEmpleadoController controller) {
                this.controller = controller;
        }

        private ObservableList<Empleado> observableList(){
                return FXCollections.observableArrayList(empleadoArrayList);
        }
}


