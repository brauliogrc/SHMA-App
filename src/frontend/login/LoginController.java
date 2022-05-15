/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.login;

import backend.login.LoginCRUD;
import backend.login.UserData;
import frontend.firstview.FirstViewController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bruno
 */
public class LoginController implements Initializable {
    
    @FXML
    private Button btnIngresar;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPass;
    @FXML
    private ProgressBar progressBar;
    
    LoginCRUD lc;

    public void iniciarSecion() {
        lc = new LoginCRUD();
        UserData ud = lc.login( this.txtEmail.getText(), this.txtPass.getText() );
        if ( ud != null ) {
            if ( ud.isHabilitado() ) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource( "../firstview/FirstView.fxml" ) );

                    Scene scene = new Scene(loader.load());
                    Stage stage = new Stage();
                    stage.setTitle( "Panel principal" );
                    stage.initModality(Modality.NONE);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    
                    FirstViewController controller = (FirstViewController) loader.getController();
                    controller.setUserData(ud);
                    
                    stage.show();
                }
                catch( IOException ioex ) {
                    JOptionPane.showMessageDialog(
                            null, 
                            "Ocurrio un error al cargar la vista principal",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
            else {
                JOptionPane.showMessageDialog(
                        null,
                        "Su usuario se encuentra temporalmente deshabilitado, favor de contactar a soporte",
                        "Login error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
        else {
            JOptionPane.showMessageDialog(
                    null,
                    "Usuario o contraseña invalidos, favor de rectificarlos",
                    "Credenciales incorrectas",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
