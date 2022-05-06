/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.employee.view;

import backend.dao.employee.EmployeeDAO;
import backend.models.Employee;
import frontend.employee.update.UpdateEmployeeController;
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
public class EmployeesViewController implements Initializable {

    @FXML
    private TextField idEmployee;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidoPat;
    @FXML
    private TextField txtApellidoMat;
    @FXML
    private TextField txtFuncion;
    @FXML
    private TextField txtTel;
    @FXML
    private TextField txtImg;
    @FXML
    private TableView<Employee> employeesTable;
    @FXML
    private TableColumn<Employee, Integer> colId;
    @FXML
    private TableColumn<Employee, String> colName;
    @FXML
    private TableColumn<Employee, String> colApellidoPat;
    @FXML
    private TableColumn<Employee, String> colApellidoMat;
    @FXML
    private TableColumn<Employee, String> colFuncion;
    @FXML
    private TableColumn<Employee, String> colTel;
    @FXML
    private TableColumn<Employee, String> colImg;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    
    Employee employee;
    /**
     * Obtiene y muestra en una tabla todos los empleados registrados
     */
    public void showEmployees() {
        this.employeesTable.getItems().clear();
        EmployeeDAO edao = new EmployeeDAO();
        ArrayList<Employee> employeeList = edao.getAll();
        
        employeeList.forEach( (item) -> {
            this.colId.setCellValueFactory( new PropertyValueFactory<Employee, Integer>( "idEmpleado" ) );
            this.colName.setCellValueFactory( new PropertyValueFactory<Employee, String>( "nombre" ) );
            this.colApellidoPat.setCellValueFactory( new PropertyValueFactory<Employee, String>( "apellido_paterno" ) );
            this.colApellidoMat.setCellValueFactory( new PropertyValueFactory<Employee, String>( "apellido_materno" ) );
            this.colFuncion.setCellValueFactory( new PropertyValueFactory<Employee, String>( "funcion" ) );
            this.colTel.setCellValueFactory( new PropertyValueFactory<Employee, String>( "telefono" ) );
            this.colImg.setCellValueFactory( new PropertyValueFactory<Employee, String>( "imagen" ) );
            
            ObservableList<Employee> dat = FXCollections.observableArrayList( item );
            // this.employeesTable.getColumns().addAll( this.colId, this.colName, this.colApellidoPat, this.colApellidoMat, this.colFuncion, this.colTel, this.colImg );
            this.employeesTable.getItems().add( item );
        } );
    }
    
    /**
     * Registra un nuevo usuario en la DB
     */
    public void addEmployee() {
        this.employee = new Employee();
        this.employee.setNombre( this.txtNombre.getText() );
        this.employee.setApellido_paterno( this.txtApellidoPat.getText() );
        this.employee.setApellido_materno( this.txtApellidoMat.getText() );
        this.employee.setFuncion( this.txtFuncion.getText() );
        this.employee.setTelefono( this.txtTel.getText() );
        this.employee.setImagen( this.txtImg.getText() );
        
        EmployeeDAO edao = new EmployeeDAO();
        if ( employee.verifyContent() && edao.add(employee) ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Se ha registrado al empleado " + employee.getNombre(),
                    "Registro exitoso",
                    JOptionPane.DEFAULT_OPTION
            );
            this.showEmployees();
        }
    }
    
    /**
     * Muestra al popup de actualización del empleado
     */
    public void showUpdatePopUp() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation( getClass().getResource( "../update/UpdateEmployee.fxml" ) );
        
        Scene scene = new Scene( loader.load() );
        Stage stage = new Stage();
        stage.setTitle( "Update Employee" );
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.setScene(scene);
        
        UpdateEmployeeController controller = (UpdateEmployeeController) loader.getController();
        
        if ( this.employeesTable.getSelectionModel().isEmpty() ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Primero debe seleccionar un empleado",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        else {
            int idEmployee = this.employeesTable.getSelectionModel().getSelectedItem().getIdEmpleado();
            controller.setIdEmployee(idEmployee);
            stage.showAndWait();
            this.showEmployees();
        }
    }
    
    /**
     * Elimnación de un empleado de la DB
     */
    public void deleteEmployee() {
        EmployeeDAO edao = new EmployeeDAO();
        if ( this.employeesTable.getSelectionModel().isEmpty() ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Primero debe seleccionar un empleado",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        Employee e = this.employeesTable.getSelectionModel().getSelectedItem();
        if ( edao.delete(e) ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Empleado eliminado",
                    "Eliminación exitosa",
                    JOptionPane.DEFAULT_OPTION
            );
            this.showEmployees();
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.showEmployees();
    }    
    
}
