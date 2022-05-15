/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.operator.update;

import backend.dao.operator.OperatorDAO;
import backend.models.Operator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bruno
 */
public class OperatorUpdateController implements Initializable {
    private int idOperator;
    Operator operator;
    
    @FXML
    private CheckBox ckbNewActivo;
    @FXML
    private CheckBox ckbNewDescanso;
    @FXML
    private TextField txtNewIdEmpleado;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnDescartar;
    
    /**
     * Definimos el id del campo a actualizar y obtenemos sus datos
     * @param idEmployee 
     */
    public void setIdOperator( int idOperator ) {
        this.idOperator = idOperator;
        this.getOperatorData(idOperator);
    }
    
    /**
     * Realiza la actualización del operador
     */
    public void updateOperator() {
        //this.employee = new Employee();
        
        this.operator.setActivo(this.ckbNewActivo.isSelected() );
        this.operator.setDescanso(this.ckbNewDescanso.isSelected() );
        this.operator.setIdEmpleado( Integer.parseInt( this.txtNewIdEmpleado.getText() ) );
        
        OperatorDAO edao = new OperatorDAO();
        if ( edao.update(operator) ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Se ha actualizado al empleado ",
                    "Actualizacipn exitosa",
                    JOptionPane.DEFAULT_OPTION
            );
        }
    }
    
    /**
     * Obtención de los datos del operador
     * @param idOperator 
     */
    public void getOperatorData( int idOperator ) {
        OperatorDAO edao = new OperatorDAO();
        operator = edao.getOne(idOperator);
        
        this.ckbNewActivo.setSelected(operator.isActivo());
        this.ckbNewDescanso.setSelected(operator.isDescanso());
        this.txtNewIdEmpleado.setText( String.valueOf( operator.getIdEmpleado() ) );
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
