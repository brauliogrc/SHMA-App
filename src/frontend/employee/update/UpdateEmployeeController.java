/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.employee.update;

import backend.dao.employee.EmployeeDAO;
import backend.models.Employee;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bruno
 */
public class UpdateEmployeeController implements Initializable {
    
    @FXML
    private TextField txtNewNombre;
    @FXML
    private TextField txtNewApellidoPat;
    @FXML
    private TextField txtNewApellidoMat;
    @FXML
    private TextField txtNewFuncion;
    @FXML
    private TextField txtNewTelefono;
    @FXML
    private TextField txtNewImg;
    @FXML
    private TextField txtNewRol;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnDescartar;
    
    private int idEmployee;
    Employee employee;
    
    /**
     * Definimos el id del campo a actualizar y obtenemos sus datos
     * @param idEmployee 
     */
    public void setIdEmployee( int idEmployee ) {
        this.idEmployee = idEmployee;
        this.getEmployeeData(idEmployee);
    }
    
    /**
     * Realiza la actualización del usuario
     */
    public void updateEmployee() {
        //this.employee = new Employee();
        
        this.employee.setNombre( this.txtNewNombre.getText() );
        this.employee.setApellido_paterno(this.txtNewApellidoPat.getText() );
        this.employee.setApellido_materno(this.txtNewApellidoMat.getText() );
        this.employee.setFuncion(this.txtNewFuncion.getText() );
        this.employee.setTelefono(this.txtNewTelefono.getText() );
        this.employee.setImagen(this.txtNewImg.getText() );
        this.employee.setRol( Integer.parseInt( this.txtNewRol.getText() ) );
        
        EmployeeDAO edao = new EmployeeDAO();
        if ( edao.update(employee) ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Se ha actualizado al empleado " + employee.getNombre(),
                    "Actualizacipn exitosa",
                    JOptionPane.DEFAULT_OPTION
            );
        }
    }
    
    /**
     * Obtención de los datos del empleado
     * @param idEmployee 
     */
    public void getEmployeeData( int idEmployee ) {
        EmployeeDAO edao = new EmployeeDAO();
        employee = edao.getOne(idEmployee);
        
        this.txtNewNombre.setText( employee.getNombre());
        this.txtNewApellidoPat.setText( employee.getApellido_paterno());
        this.txtNewApellidoMat.setText( employee.getApellido_materno());
        this.txtNewFuncion.setText( employee.getFuncion());
        this.txtNewTelefono.setText( employee.getTelefono());
        this.txtNewImg.setText( employee.getImagen());
        this.txtNewRol.setText( String.valueOf(employee.getRol()));
    }
    
    public void close() {
        
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
