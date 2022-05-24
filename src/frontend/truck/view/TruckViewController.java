/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.truck.view;

import backend.dao.truck.TruckDAO;
import backend.models.Truck;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author bruno
 */
public class TruckViewController implements Initializable {


    @FXML
    private TextField txtNumSerie;

    @FXML
    private Label labelTitulo;

    @FXML
    private TextField txtModelo;

    @FXML
    private Button btnGuardar;

    @FXML
    private CheckBox isActivo;

    @FXML
    private CheckBox isMantenimiento;

    @FXML
    private Button btnCancelar;

    @FXML
    private TableView<Truck> table;

    @FXML
    private TextField txtNumEconomico;
    
    private TruckDAO objDAO;

    private ContextMenu cmOpciones;

    private Truck selected;

    @FXML
    void btnGuardarOnAction(ActionEvent event) {
        if (selected == null) {
            Truck obj = new Truck();
            obj.setNumero_economico(txtNumEconomico.getText());
            obj.setNumero_serie(txtNumSerie.getText());
            obj.setModelo(txtModelo.getText());
            obj.setActivo(isActivo.isSelected());
            obj.setMantenimiento(isMantenimiento.isSelected());

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
            limpiarCampos();
        } else {
            selected.setNumero_economico(txtNumEconomico.getText());
            selected.setNumero_serie(txtNumSerie.getText());
            selected.setModelo(txtModelo.getText());
            selected.setActivo(isActivo.isSelected());
            selected.setMantenimiento(isMantenimiento.isSelected());
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
        List<Truck> obj = this.objDAO.getAll();
        ObservableList<Truck> data = FXCollections.observableArrayList(obj);
        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("idCamion"));
        TableColumn neconoCol = new TableColumn("Numero Economico");
        neconoCol.setCellValueFactory(new PropertyValueFactory("numero_economico"));
        TableColumn nserieCol = new TableColumn("Numero Serie");
        nserieCol.setCellValueFactory(new PropertyValueFactory("numero_serie"));
        TableColumn modeloCol = new TableColumn("Modelo");
        modeloCol.setCellValueFactory(new PropertyValueFactory("modelo"));
        TableColumn activoCol = new TableColumn("Activo");
        activoCol.setCellValueFactory(new PropertyValueFactory("activo"));
        TableColumn mantenimientoCol = new TableColumn("Mantenimiento");
        mantenimientoCol.setCellValueFactory(new PropertyValueFactory("mantenimiento"));

        table.setItems(data);
        table.getColumns().addAll(idCol, neconoCol, nserieCol, modeloCol, activoCol, mantenimientoCol);
    } 
    
    private void limpiarCampos() {
        txtNumEconomico.setText("");
        txtNumSerie.setText("");
        txtModelo.setText("");
        isActivo.setSelected(false);
        isMantenimiento.setSelected(false);
    }

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        this.objDAO = new TruckDAO();
        cargar();
        labelTitulo.setText("Camiones");
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
                txtNumEconomico.setText(selected.getNumero_economico());
                txtNumSerie.setText(selected.getNumero_serie());
                txtModelo.setText(selected.getModelo());
                isActivo.setSelected(selected.isActivo());
                isMantenimiento.setSelected(selected.isMantenimiento());
                btnCancelar.setDisable(false);
            }
        });
        
        miEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = table.getSelectionModel().getSelectedIndex();
                selected = table.getItems().get(index);
                System.out.println(selected);
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
