/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.dao.transfer;

import backend.ICRUD.ICRUD;
import backend.models.Transfer;
import dbconnection.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author bruno
 */
public class TransferDAO implements ICRUD<Transfer> {

    /**
     * Inserta un nuevo traspaso en la DB
     * @param t Objeto de clase Transfer
     * @return boolean
     */
    @Override
    public boolean add(Transfer t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "INSERT INTO traspasos( numero_traspado, idEmpleado )"
                    + " VALUES( ?, ? )";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, t.getNumero_traspado());
            ps.setInt(2, t.getIdEmpleado());
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
            
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta INSERT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(), 
                    "Error al agregar un nuevo traspaso",
                    JOptionPane.ERROR
            );
            return false;
        }
    }

    /**
     * Actualiza un traspaso en la DB
     * @param t Objeto de clase Transfer
     * @return boolean
     */
    @Override
    public boolean update(Transfer t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "UPDATE traspaso "
                            + "SET "
                                + "numero_traspado = ?, "
                                + "idEmpleado = ? "
                            + "WHERE idTraspaso = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, t.getNumero_traspado());
            ps.setInt(2, t.getIdEmpleado());
            ps.setInt(3, t.getIdTraspaso());
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
            
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta UPDATE:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al actualizar el traspaso",
                    JOptionPane.ERROR
            );
            return false;
        }
    }

    /**
     * Elimina un traspaso de la DB
     * @param t Objeto de clase Transfer
     * @return boolean
     */
    @Override
    public boolean delete(Transfer t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "DELETE FROM traspasos"
                            + "WHERE idTraspaso = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.getIdTraspaso());
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
            
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta DELETE:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al eliminar el traspaso",
                    JOptionPane.ERROR
            );
            return false;
        }
    }

    /**
     * Obtención de un traspaso según el parámetro especificado
     * @param id id del traspaso a buscar
     * @return Transfer
     */
    @Override
    public Transfer getOne(int id) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "SELECT * FROM traspasos "
                            + "WHERE idTraspaso = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if( rs.next() ) {
                Transfer t = new Transfer();
                t.setIdTraspaso( rs.getInt( "idTraspaso" ) );
                t.setNumero_traspado( rs.getString( "numero_traspado" ) );
                t.setIdEmpleado( rs.getInt( "idEmpleado" ) );
                
                return t;
            }
            else {
                JOptionPane.showMessageDialog(
                    null,
                    "Ningún elemento coincide con el parámetro dado",
                    "Error al obtener traspaso",
                    JOptionPane.ERROR
                );
                return null;
            }
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta SELECT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al obtener traspaso",
                    JOptionPane.ERROR
            );
            return null;
        }
    }

    /**
     * Obtención del listado de todos los traspasos
     * @return ArrayList<Transfer>
     */
    @Override
    public ArrayList<Transfer> getAll() {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "SELECT * FROM traspasos";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            ArrayList<Transfer> list = new ArrayList<Transfer>();
            while( rs.next() ) {
                Transfer t = new Transfer();
                t.setIdTraspaso( rs.getInt( "idTraspaso" ) );
                t.setNumero_traspado( rs.getString( "numero_traspado" ) );
                t.setIdEmpleado( rs.getInt( "idEmpleado" ) );
                
                list.add(t);
            }
            return list;
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta SELECT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al obtener listado de traspasos",
                    JOptionPane.ERROR
            );
            return null;
        }
    }

    @Override
    public ArrayList<Transfer> query(Transfer t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
