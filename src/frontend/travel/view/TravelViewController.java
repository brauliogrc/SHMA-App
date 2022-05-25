/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.travel.view;

import backend.dao.operator.OperatorDAO;
import backend.dao.platform.PlatformDAO;
import backend.dao.transfer.TransferDAO;
import backend.dao.travel.TravelDAO;
import backend.dao.truck.TruckDAO;
import backend.models.Box;
import backend.models.Dolly;
import backend.models.Operator;
import backend.models.Platform;
import backend.models.Transfer;
import backend.models.Travel;
import backend.models.Truck;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;
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
 * @author Manuel Soto
 */
public class TravelViewController implements Initializable {


    @FXML
    private TextField txtKiloSalida;

    @FXML
    private ComboBox<Transfer> traspaso;

    @FXML
    private ComboBox<Truck> camion;

    @FXML
    private Label labelTitulo;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField txtKiloLlegada;

    @FXML
    private ComboBox<Platform> plataforma;

    @FXML
    private Button btnCancelar;

    @FXML
    private DatePicker dateFinal;

    @FXML
    private ComboBox<Operator> operador;

    @FXML
    private DatePicker dateInicio;

    @FXML
    private TableView<Travel> table;


    
    private TruckDAO truckDao = new TruckDAO();
    private PlatformDAO platformDao = new PlatformDAO();
    private TransferDAO transferDao = new TransferDAO();
    private OperatorDAO operatorDao = new OperatorDAO();
    
    private ContextMenu cmOpciones;
    private TravelDAO objDAO;
    private Travel selected;

    @FXML
    void btnGuardarOnAction(ActionEvent event) {
        if (selected == null) {
            Travel obj = new Travel();
            obj.setHora_inicial("");
            obj.setHora_final("");
            obj.setFecha_inicial(dateInicio.getValue().toString());
            obj.setFecha_final(dateFinal.getValue().toString());
            obj.setKilometraje_salida(Double.parseDouble(txtKiloSalida.getText()));
            obj.setKilometraje_llegada(Double.parseDouble(txtKiloLlegada.getText()));
            obj.setIdTraspaso(traspaso.getValue().getIdTraspaso());
            obj.setIdCamion(camion.getValue().getIdCamion());
            obj.setIdPlataforma(plataforma.getValue().getIdPlatform());
            obj.setIdOperador(operador.getValue().getIdOperador());
           
            
            
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
            selected.setHora_inicial("");
            selected.setHora_final("");
            selected.setFecha_inicial(dateInicio.getValue().toString());
            selected.setFecha_final(dateFinal.getValue().toString());
            selected.setKilometraje_salida(Double.parseDouble(txtKiloSalida.getText()));
            selected.setKilometraje_llegada(Double.parseDouble(txtKiloLlegada.getText()));
            selected.setIdTraspaso(traspaso.getValue().getIdTraspaso());
            selected.setIdCamion(camion.getValue().getIdCamion());
            selected.setIdPlataforma(plataforma.getValue().getIdPlatform());
            selected.setIdOperador(operador.getValue().getIdOperador());
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
        List<Travel> obj = this.objDAO.getAll();
        System.out.println(obj);
        ObservableList<Travel> data = FXCollections.observableArrayList(obj);
        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("idViaje"));
        TableColumn col1 = new TableColumn("Fecha Incial");
        col1.setCellValueFactory(new PropertyValueFactory("fecha_inicial"));
        TableColumn col2 = new TableColumn("Fecha Final");
        col2.setCellValueFactory(new PropertyValueFactory("fecha_final"));
        TableColumn col3 = new TableColumn("Kilometraje Inicial");
        col3.setCellValueFactory(new PropertyValueFactory("kilometraje_salida"));
        TableColumn col4 = new TableColumn("Kilometraje Final");
        col4.setCellValueFactory(new PropertyValueFactory("kilometraje_llegada"));
        TableColumn col5 = new TableColumn("Traspaso");
        col5.setCellValueFactory(new PropertyValueFactory("idTraspaso"));
        TableColumn col6 = new TableColumn("Operador");
        col6.setCellValueFactory(new PropertyValueFactory("idOperador"));
        TableColumn col7 = new TableColumn("Camion");
        col7.setCellValueFactory(new PropertyValueFactory("idCamion"));
        TableColumn col8 = new TableColumn("Plataforma");
        col8.setCellValueFactory(new PropertyValueFactory("idPlataforma"));
        table.setItems(data);
        table.getColumns().addAll(idCol, col1, col2, col3);
        
        
        traspaso.getItems().clear();
        plataforma.getItems().clear();
        operador.getItems().clear();
        camion.getItems().clear();
        
        List<Transfer> transfer = this.transferDao.getAll();
        ObservableList<Transfer> dataTransfer= FXCollections.observableArrayList(transfer);
        traspaso.getItems().addAll(dataTransfer);
        
        
        List<Operator> operator = this.operatorDao.getAll();
        ObservableList<Operator> dataOperator= FXCollections.observableArrayList(operator);
        operador.getItems().addAll(dataOperator);
        
        List<Platform> platform = this.platformDao.getAll();
        ObservableList<Platform> dataPlatform= FXCollections.observableArrayList(platform);
        plataforma.getItems().addAll(dataPlatform);
        
        List<Truck> truck = this.truckDao.getAll();
        ObservableList<Truck> dataTruck= FXCollections.observableArrayList(truck);
        camion.getItems().addAll(dataTruck);
        
        
    } 
    
    private void limpiarCampos() {
        camion.setValue(null);
        plataforma.setValue(null);
        operador.setValue(null);
        traspaso.setValue(null);
        dateInicio.setValue(null);
        dateFinal.setValue(null);
        txtKiloSalida.setText(null);
        txtKiloLlegada.setText(null);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.objDAO = new TravelDAO();
        cargar();
        labelTitulo.setText("Viajes");
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
        camion.setValue(truckDao.getOne(selected.getIdCamion()));
        plataforma.setValue(platformDao.getOne(selected.getIdPlataforma()));
        operador.setValue(operatorDao.getOne(selected.getIdOperador()));
        traspaso.setValue(transferDao.getOne(selected.getIdTraspaso()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String dateI = selected.getFecha_inicial();
        String dateF = selected.getFecha_final();
        dateInicio.setValue(LocalDate.parse(dateI, formatter) );
        dateFinal.setValue(LocalDate.parse(dateF, formatter));
        txtKiloSalida.setText(selected.getKilometraje_salida().toString());
        txtKiloLlegada.setText(selected.getKilometraje_llegada().toString());
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
