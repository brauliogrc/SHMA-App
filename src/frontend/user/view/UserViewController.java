/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.user.view;

import backend.dao.user.UserDAO;
import backend.models.User;
import frontend.user.update.UpdateUserController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
public class UserViewController implements Initializable {

    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtIdEmpleado;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private CheckBox ckbActivo;
    @FXML
    private CheckBox ckbHabilitado;
    @FXML
    private CheckBox ckbRecuperación;
    @FXML
    private TableView<User> usersTable;
    @FXML
    private TableColumn<User, Integer> colId;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, Boolean> colActivo;
    @FXML
    private TableColumn<User, Boolean> colHabilitado;
    @FXML
    private TableColumn<User, Boolean> colRecuperacion;
    @FXML
    private TableColumn<User, String> colNombreEmpleado;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Label lblEmailError;
    @FXML
    private Label lblPassError;
    
    User user;
    /**
     * Obtiene y muestra en una tabla todos los empleados registrados
     */
    public void showUsers() {
        this.usersTable.getItems().clear();
        UserDAO edao = new UserDAO();
        ArrayList<User> employeeList = edao.getAll();
        
        employeeList.forEach( (item) -> {
            this.colId.setCellValueFactory( new PropertyValueFactory<User, Integer>( "idUsuario" ) );
            this.colEmail.setCellValueFactory( new PropertyValueFactory<User, String>( "email" ) );
            this.colActivo.setCellValueFactory( new PropertyValueFactory<User, Boolean>( "activo" ) );
            this.colHabilitado.setCellValueFactory( new PropertyValueFactory<User, Boolean>( "habilitado" ) );
            this.colRecuperacion.setCellValueFactory( new PropertyValueFactory<User, Boolean>( "recuperacion" ) );
            this.colNombreEmpleado.setCellValueFactory( new PropertyValueFactory<User, String>( "employeeName" ) );
            
            ObservableList<User> dat = FXCollections.observableArrayList( item );
            // this.employeesTable.getColumns().addAll( this.colId, this.colName, this.colApellidoPat, this.colApellidoMat, this.colFuncion, this.colTel, this.colImg );
            this.usersTable.getItems().add( item );
        } );
    }
    
    /**
     * Registra un nuevo empleado en la DB
     */
    public void addUser() {
        this.user = new User();
        this.user.setActivo( this.ckbActivo.isSelected() );
        this.user.setEmail( this.txtEmail.getText() );
        this.user.setHabilitado( this.ckbHabilitado.isSelected() );
        this.user.setIdEmpleado( Integer.parseInt( this.txtIdEmpleado.getText())  );
        this.user.setPassword( this.txtPassword.getText() );
        this.user.setRecuperacion( this.ckbRecuperación.isSelected() );
        
        UserDAO edao = new UserDAO();
        if ( this.isEmail( this.txtEmail.getText() ) && this.securityPass( this.txtPassword.getText() ) ) {
            if ( edao.add(user) ) {
                JOptionPane.showMessageDialog(
                        null,
                        "Se ha registrado al usuario",
                        "Registro exitoso",
                        JOptionPane.DEFAULT_OPTION
                );
                this.showUsers();
            }
        }
    }
    
    /**
     * Muestra al popup de actualización del empleado
     */
    public void showUpdatePopUp() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation( getClass().getResource( "../update/UpdateUser.fxml" ) );
        
        Scene scene = new Scene( loader.load() );
        Stage stage = new Stage();
        stage.setTitle( "Update Usuario" );
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.setScene(scene);
        
        UpdateUserController controller = (UpdateUserController) loader.getController();
        
        if ( this.usersTable.getSelectionModel().isEmpty() ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Primero debe seleccionar un empleado",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        else {
            int idUser = this.usersTable.getSelectionModel().getSelectedItem().getIdUsuario();
            controller.setIdUser(idUser);
            stage.showAndWait();
            this.showUsers();
        }
    }
    
    /**
     * Elimnación de un empleado de la DB
     */
    public void deleteuser() {
        UserDAO edao = new UserDAO();
        if ( this.usersTable.getSelectionModel().isEmpty() ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Primero debe seleccionar un empleado",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        User e = this.usersTable.getSelectionModel().getSelectedItem();
        if ( edao.delete(e) ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Empleado eliminado",
                    "Eliminación exitosa",
                    JOptionPane.DEFAULT_OPTION
            );
            this.showUsers();
        }
    }
    
    // Validación del correo electrónico
    public boolean isEmail( String correo ) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-Za-z0-9]+\\.)+[A-Za-z]{2,4}$");
        mat = pat.matcher(correo);
        if( mat.find() ) {
            this.lblEmailError.setText("");
            return true;
        }
        else {
            this.lblEmailError.setText( "Formato de correo electrónico inválido" );
            return false;
        }
    }
    
    // Comprobación de contraseña
    public boolean securityPass( String pass ) {
        if ( (pass.length() >= 8 && pass.length() <= 20) ) {
            Pattern pat = null;
            Matcher mat = null;
            pat = Pattern.compile( "^((?=.*[A-Za-z]+)(?=.*[!\"#$%&/()=?¡|°¬'¿+*~\\[\\]{}\\^`;:\\-_@]+)(?=.*[0-9]+))" );
            mat = pat.matcher(pass);
            if ( mat.find() ) {
                this.lblPassError.setText("");
                return true;
            }
            else  {
                this.lblPassError.setText( "La contraseña no cumple con los criterios de seguridad" );
                return false;
            }
        }
        else  {
                this.lblPassError.setText( "La contraseña no cumple con los criterios de seguridad" );
                return false;
            }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.showUsers();
    }    
}
