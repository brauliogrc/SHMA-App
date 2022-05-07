/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.firstview;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bruno
 */
public class FirstViewController implements Initializable {

    @FXML
    private Button btnEmployees;
    @FXML
    private Button btnUsers;
    @FXML
    private Button btnTransfers;
    @FXML
    private Button btnOperators;
    
    public void showEmployeesView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation( getClass().getResource( "../employee/view/EmployeesView.fxml" ) );
        
        Scene scene = new Scene( loader.load() ); 
        Stage stage = new Stage();
        stage.setTitle( "Empleados" );
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable( false );
        stage.setScene(scene);
        stage.showAndWait();
    }
    public void showUsersView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation( getClass().getResource( "../user/view/UserView.fxml" ) );
        
        Scene scene = new Scene( loader.load() ); 
        Stage stage = new Stage();
        stage.setTitle( "Empleados" );
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable( false );
        stage.setScene(scene);
        stage.showAndWait();
    }
    public void showTransfersView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation( getClass().getResource( "../transfer/view/TransferView.fxml" ) );
        
        Scene scene = new Scene( loader.load() ); 
        Stage stage = new Stage();
        stage.setTitle( "Empleados" );
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable( false );
        stage.setScene(scene);
        stage.showAndWait();
        
    }
    public void showOperatorsView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation( getClass().getResource( "../operator/view/OperatorView.fxml" ) );
        
        Scene scene = new Scene( loader.load() ); 
        Stage stage = new Stage();
        stage.setTitle( "Empleados" );
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable( false );
        stage.setScene(scene);
        stage.showAndWait();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
