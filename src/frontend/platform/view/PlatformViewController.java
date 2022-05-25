/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.platform.view;

import backend.dao.dolly.DollyDAO;
import backend.dao.box.BoxDAO;
import backend.dao.platform.PlatformDAO;
import backend.models.Box;
import backend.models.Dolly;
import backend.models.Platform;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Manuel Soto
 */
public class PlatformViewController implements Initializable {


    @FXML
    private Label labelTitulo;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<Box> selectSegunda;

    @FXML
    private Button btnCancelar;

    @FXML
    private ComboBox<Dolly> selectDolly;

    @FXML
    private ComboBox<Box> selectPrimera;

    @FXML
    private TableView<Platform> table;

    
    private DollyDAO dollyDao = new DollyDAO();
    private BoxDAO boxDao = new BoxDAO();
    
    private ContextMenu cmOpciones;
    private PlatformDAO objDAO;
    private Platform selected;

    @FXML
    void btnGuardarOnAction(ActionEvent event) {
        if (selected == null) {
            Platform obj = new Platform();
            
            obj.setIdMainBox(selectPrimera.getValue().getIdCaja());
            obj.setIdDolly(selectDolly.getValue().getIdDolly());
            obj.setIdSecondBox(selectSegunda.getValue().getIdCaja());
            if(true){//Cajas no iguales
                if (this.objDAO.add(obj)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Exito");
                    alert.setHeaderText(null);
                    alert.setContentText("Registro guardado satisfactoriamente!!!");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();
                    cargar();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("No se guardo el registro");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No utilice la misma caja");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
            }
            
            limpiarCampos();
        } else {
            selected.setIdMainBox(selectPrimera.getValue().getIdCaja());
            selected.setIdDolly(selectDolly.getValue().getIdDolly());
            selected.setIdSecondBox(selectSegunda.getValue().getIdCaja());
            if (this.objDAO.update(selected)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Exito");
                alert.setHeaderText(null);
                alert.setContentText("Registro actualizado satisfactoriamente!!!");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
                cargar();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No se actualizo el registro");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
            }
            selected = null;
            btnCancelar.setDisable(true);
            limpiarCampos();
        }
    }

    @FXML
    void btnCancelarOnAction(ActionEvent event) {
        selected = null;
        btnCancelar.setDisable(true);
        limpiarCampos();
    }
    
    public void cargar() {
        table.getItems().clear();
        table.getColumns().clear();
        List<Platform> obj = this.objDAO.getAll();
        System.out.println(obj);
        ObservableList<Platform> data = FXCollections.observableArrayList(obj);
        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("idPlatform"));
        TableColumn box1Col = new TableColumn("Caja 1");
        box1Col.setCellValueFactory(new PropertyValueFactory("idMainBox"));
        TableColumn dollyCol = new TableColumn("Dolly");
        dollyCol.setCellValueFactory(new PropertyValueFactory("idDolly"));
        TableColumn box2Col = new TableColumn("Caja 2");
        box2Col.setCellValueFactory(new PropertyValueFactory("idSecondBox"));
        table.setItems(data);
        table.getColumns().addAll(idCol, box1Col, dollyCol, box2Col);
        
        
        selectPrimera.getItems().clear();
        selectDolly.getItems().clear();
        selectSegunda.getItems().clear();
        
        List<Dolly> dolly = this.dollyDao.getAll();
        ObservableList<Dolly> dollies= FXCollections.observableArrayList(dolly);
        selectDolly.getItems().addAll(dollies);
        List<Box> box = this.boxDao.getAll();
        ObservableList<Box> boxes1 = FXCollections.observableArrayList(box);
        ObservableList<Box> boxes2 = FXCollections.observableArrayList(box);
        selectPrimera.getItems().addAll(boxes1);
        selectSegunda.getItems().addAll(boxes2);
        
        
        selectPrimera.getSelectionModel().select(0);
        selectDolly.getSelectionModel().select(0);
        selectSegunda.getSelectionModel().select(0);
        
        
    } 
    
    private void limpiarCampos() {
        selectPrimera.setValue(null);
        selectDolly.setValue(null);
        selectSegunda.setValue(null);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.objDAO = new PlatformDAO();
        cargar();
        labelTitulo.setText("Plataformas");
        cmOpciones = new ContextMenu();

        MenuItem miEditar = new MenuItem("Editar");
        MenuItem miEliminar = new MenuItem("Eliminar");
        cmOpciones.getItems().addAll(miEditar, miEliminar);
        table.setContextMenu(cmOpciones);

        miEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = table.getSelectionModel().getSelectedIndex();
                selected = table.getItems().get(index);
                System.out.println(selected);
                selectPrimera.setValue(boxDao.getOne(selected.getIdMainBox()));
                selectSegunda.setValue(boxDao.getOne(selected.getIdSecondBox()));
                selectDolly.setValue(dollyDao.getOne(selected.getIdDolly()));
                btnCancelar.setDisable(false);
            }
        });
        
        miEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = table.getSelectionModel().getSelectedIndex();
                selected = table.getItems().get(index);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Eliminar");
                alert.setHeaderText("Estas seguro de eliminar el siguiente registro?");
                alert.setContentText(null);
                // option != null.
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == ButtonType.OK) {
                    objDAO.delete(selected);
                    cargar();
                    selected = null;
                }else{
                    cargar();
                    selected = null;
                }
            }
        });
    }
}
