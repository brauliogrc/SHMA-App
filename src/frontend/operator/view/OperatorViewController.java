/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.operator.view;

import backend.dao.operator.OperatorDAO;
import backend.models.Employee;
import backend.models.Operator;
import frontend.operator.update.OperatorUpdateController;
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
import javafx.scene.control.CheckBox;
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
public class OperatorViewController implements Initializable {

    @FXML
    private CheckBox ckbActivo;
    @FXML
    private CheckBox ckbDescanso;
    @FXML
    private TextField txtIdEmpleado;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TableView<Operator> operatorsTable;
    @FXML
    private TableColumn<Operator, Integer> colId;
    @FXML
    private TableColumn<Operator, Boolean> colActivo;
    @FXML
    private TableColumn<Operator, Boolean> colDescanso;
    @FXML
    private TableColumn<Operator, Integer> colIdEmpleado;
    
    Operator operator;
    /**
     * Obtiene y muestra en una tabla todos los operadores registrados
     */
    public void showOperators() {
        this.operatorsTable.getItems().clear();
        OperatorDAO edao = new OperatorDAO();
        ArrayList<Operator> employeeList = edao.getAll();
        
        employeeList.forEach( (item) -> {
            this.colId.setCellValueFactory( new PropertyValueFactory<Operator, Integer>( "idOperador" ) );
            this.colActivo.setCellValueFactory( new PropertyValueFactory<Operator, Boolean>( "activo" ) );
            this.colDescanso.setCellValueFactory( new PropertyValueFactory<Operator, Boolean>( "descanso" ) );
            this.colIdEmpleado.setCellValueFactory( new PropertyValueFactory<Operator, Integer>( "idEmpleado" ) );
            
            ObservableList<Operator> dat = FXCollections.observableArrayList( item );
            // this.employeesTable.getColumns().addAll( this.colId, this.colName, this.colApellidoPat, this.colApellidoMat, this.colFuncion, this.colTel, this.colImg );
            this.operatorsTable.getItems().add( item );
            System.out.print( item.getIdOperador() + "asdasdasd" );
        } );
    }
    
    /**
     * Registra un nuevo operador en la DB
     */
    public void addOperator() {
        this.operator = new Operator();
        this.operator.setActivo(this.ckbActivo.isSelected());
        this.operator.setDescanso(this.ckbDescanso.isSelected());
        this.operator.setIdEmpleado(Integer.parseInt( this.txtIdEmpleado.getText() ) );
        
        OperatorDAO edao = new OperatorDAO();
        if ( edao.add(operator) ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Se ha registrado al empleado ",
                    "Registro exitoso",
                    JOptionPane.DEFAULT_OPTION
            );
            this.showOperators();
        }
    }
    
    /**
     * Muestra al popup de actualización del empleado
     */
    public void showUpdatePopUp() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation( getClass().getResource( "../update/OperatorUpdate.fxml" ) );
        
        Scene scene = new Scene( loader.load() );
        Stage stage = new Stage();
        stage.setTitle( "Update Operador" );
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.setScene(scene);
        
        OperatorUpdateController controller = (OperatorUpdateController) loader.getController();
        
        if ( this.operatorsTable.getSelectionModel().isEmpty() ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Primero debe seleccionar un empleado",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        else {
            int idOperator = this.operatorsTable.getSelectionModel().getSelectedItem().getIdOperador();
            controller.setIdOperator(idOperator);
            stage.showAndWait();
            this.showOperators();
        }
    }
    
    /**
     * Elimnación de un empleado de la DB
     */
    public void deleteOperator() {
        OperatorDAO edao = new OperatorDAO();
        if ( this.operatorsTable.getSelectionModel().isEmpty() ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Primero debe seleccionar un empleado",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        Operator e = this.operatorsTable.getSelectionModel().getSelectedItem();
        if ( edao.delete(e) ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Empleado eliminado",
                    "Eliminación exitosa",
                    JOptionPane.DEFAULT_OPTION
            );
            this.showOperators();
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.showOperators();
    }
    
}
