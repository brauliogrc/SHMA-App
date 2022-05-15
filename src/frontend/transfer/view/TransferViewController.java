/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.transfer.view;

import backend.dao.transfer.TransferDAO;
import backend.models.Transfer;
import frontend.transfer.update.UpdateTransferController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bruno
 */
public class TransferViewController implements Initializable {

    @FXML
    private TextField txtNumTraspaso;
    @FXML
    private TextField txtIdEmpleado;
    @FXML
    private TableView<Transfer> transfersTable;
    @FXML
    private TableColumn<Transfer, Integer> colId;
    @FXML
    private TableColumn<Transfer, String> colNumTraspaso;
    @FXML
    private TableColumn<Transfer, Integer> colIdEmpleado;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;
    
    Transfer tranfer;
    
    /**
     * Obtiene y muestra en una tabla todos los empleados registrados
     */
    public void showTransfers() {
        this.transfersTable.getItems().clear();
        TransferDAO edao = new TransferDAO();
        ArrayList<Transfer> employeeList = edao.getAll();
        
        employeeList.forEach( (item) -> {
            this.colId.setCellValueFactory( new PropertyValueFactory<Transfer, Integer>( "idTraspaso" ) );
            this.colNumTraspaso.setCellValueFactory( new PropertyValueFactory<Transfer, String>( "numero_traspado" ) );
            this.colIdEmpleado.setCellValueFactory( new PropertyValueFactory<Transfer, Integer>( "idEmpleado" ) );
            
            ObservableList<Transfer> dat = FXCollections.observableArrayList( item );
            // this.employeesTable.getColumns().addAll( this.colId, this.colName, this.colApellidoPat, this.colApellidoMat, this.colFuncion, this.colTel, this.colImg );
            this.transfersTable.getItems().add( item );
        } );
    }
    
    /**
     * Registra un nuevo empleado en la DB
     */
    public void addTransfer() {
        this.tranfer = new Transfer();
        this.tranfer.setNumero_traspado(this.txtNumTraspaso.getText() );
        this.tranfer.setIdEmpleado( Integer.parseInt( this.txtIdEmpleado.getText() ) );
        
        TransferDAO edao = new TransferDAO();
        if ( edao.add(tranfer) ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Se ha registrado al empleado ",
                    "Registro exitoso",
                    JOptionPane.DEFAULT_OPTION
            );
            this.showTransfers();
        }
    }
    
    /**
     * Muestra al popup de actualización del empleado
     */
    public void showUpdatePopUp() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation( getClass().getResource( "../update/UpdateTransfer.fxml" ) );
        
        Scene scene = new Scene( loader.load() );
        Stage stage = new Stage();
        stage.setTitle( "Update Traspaso" );
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.setScene(scene);
        
        UpdateTransferController controller = (UpdateTransferController) loader.getController();
        
        if ( this.transfersTable.getSelectionModel().isEmpty() ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Primero debe seleccionar un empleado",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        else {
            int idTransfer = this.transfersTable.getSelectionModel().getSelectedItem().getIdTraspaso();
            controller.setIdTransfer(idTransfer);
            stage.showAndWait();
            this.showTransfers();
        }
    }
    
    /**
     * Elimnación de un empleado de la DB
     */
    public void deleteTransfer() {
        TransferDAO edao = new TransferDAO();
        if ( this.transfersTable.getSelectionModel().isEmpty() ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Primero debe seleccionar un empleado",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        Transfer e = this.transfersTable.getSelectionModel().getSelectedItem();
        if ( edao.delete(e) ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Empleado eliminado",
                    "Eliminación exitosa",
                    JOptionPane.DEFAULT_OPTION
            );
            this.showTransfers();
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.showTransfers();
    }    
    
}
