/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.user.update;

import backend.dao.user.UserDAO;
import backend.models.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bruno
 */
public class UpdateUserController implements Initializable {

    @FXML
    private TextField txtNewEmail;
    @FXML
    private TextField txtNewIdEmpleado;
    @FXML
    private PasswordField txtNewPassword;
    @FXML
    private CheckBox ckbNewActivo;
    @FXML
    private CheckBox ckbNewHabilitado;
    @FXML
    private CheckBox ckbNewRecuperación;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnDescartar;
    
    private int idUser;
    User user;
    
    /**
     * Definimos el id del campo a actualizar y obtenemos sus datos
     * @param idEmployee 
     */
    public void setIdUser( int idUser ) {
        this.idUser = idUser;
        this.getUserData(idUser);
    }
    
    /**
     * Realiza la actualización del usuario
     */
    public void updateUser() {
        //this.employee = new Employee();
        
        this.user.setEmail(this.txtNewEmail.getText() );
        this.user.setActivo(this.ckbNewActivo.isSelected() );
        this.user.setHabilitado(this.ckbNewHabilitado.isSelected() );
        this.user.setRecuperacion(this.ckbNewRecuperación.isSelected() );
        this.user.setIdEmpleado( Integer.parseInt( this.txtNewIdEmpleado.getText() ) );
        this.user.setPassword(this.txtNewPassword.getText() );
        
        UserDAO edao = new UserDAO();
        if ( edao.update(user) ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Se ha actualizado al empleado ",
                    "Actualizacipn exitosa",
                    JOptionPane.DEFAULT_OPTION
            );
        }
    }
    
    /**
     * Obtención de los datos del empleado
     * @param idEmployee 
     */
    public void getUserData( int idUser ) {
        UserDAO edao = new UserDAO();
        user = edao.getOne(idUser);
        
        this.txtNewEmail.setText( user.getEmail() );
        this.txtNewPassword.setText( user.getPassword() );
        this.txtNewIdEmpleado.setText( String.valueOf( user.getIdEmpleado() ) );
        this.ckbNewActivo.setSelected(user.isActivo() );
        this.ckbNewHabilitado.setSelected( user.isHabilitado() );
        this.ckbNewRecuperación.setSelected( user.isRecuperacion() );
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
