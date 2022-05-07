/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.transfer.update;

import backend.dao.transfer.TransferDAO;
import backend.models.Transfer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bruno
 */
public class UpdateTransferController implements Initializable {

    @FXML
    private TextField txtNewNumTraspaso;
    @FXML
    private TextField txtNewIdEmpleado;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnDescartar;
    
    private int idTransfer;
    Transfer transfer;
    
    /**
     * Definimos el ID del campo a actualizar y obtenemos sus datos
     * @param idTransfer 
     */
    public void setIdTransfer( int idTransfer ) {
        this.idTransfer = idTransfer;
        this.getTranferData(idTransfer);
    }
    
    /**
     * Realiza la actualización del usuario
     */
    public void updateTransfer() {
        //this.employee = new Employee();
        
        this.transfer.setIdEmpleado( Integer.parseInt( this.txtNewIdEmpleado.getText() ) );
        this.transfer.setNumero_traspado(this.txtNewNumTraspaso.getText() );
        
        TransferDAO edao = new TransferDAO();
        if ( edao.update(transfer) ) {
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
    public void getTranferData( int idTransfer ) {
        TransferDAO edao = new TransferDAO();
        this.transfer = edao.getOne(idTransfer);
        
        this.txtNewIdEmpleado.setText( String.valueOf( transfer.getIdEmpleado() ) );
        this.txtNewNumTraspaso.setText( transfer.getNumero_traspado() );
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
