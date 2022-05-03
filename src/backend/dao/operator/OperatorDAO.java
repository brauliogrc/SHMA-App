/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.dao.operator;

import backend.ICRUD.ICRUD;
import backend.models.Operator;
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
public class OperatorDAO implements ICRUD<Operator> {

    /**
     * Inserta un nuevo operador en la DB
     * @param t Objeto de clase Operator
     * @return boolean
     */
    @Override
    public boolean add(Operator t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "INSERT INTO operadores( activo, descanso, idEmpleado )"
                    + " VALUES( ?, ?, ?, ? )";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, t.isActivo());
            ps.setBoolean(2, t.isDescanso());
            ps.setInt(3, t.getIdEmpleado());
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
            
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta INSERT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al agregar un nuevo operador",
                    JOptionPane.ERROR
            );
            return false;
        }
    }

    /**
     * Actualiza un operador en la DB
     * @param t Objeto de clase Operator
     * @return boolean
     */
    @Override
    public boolean update(Operator t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "UPDATE operadores"
                            + "SET "
                                + "activo = ?, "
                                + "descanso = ?, "
                                + "idEmpleado = ? "
                            + "WHERE idOperador = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, t.isActivo());
            ps.setBoolean(2, t.isDescanso());
            ps.setInt(3, t.getIdEmpleado());
            ps.setInt(4, t.getIdOperador());
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
            
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta UPDATE:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al actualizar operador",
                    JOptionPane.ERROR
            );
            return false;
        }
    }

    /**
     * Elimina un operador de la DB
     * @param t Objeto de tipo Operator
     * @return boolean
     */
    @Override
    public boolean delete(Operator t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "DELETE FROM operadores"
                            + "WHERE idOperador = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.getIdOperador());
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
            
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta DELETE:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al eliminar el operador",
                    JOptionPane.ERROR
            );
            return false;
        }
    }

    /**
     * Obtención de un operador según el parámetro especificado
     * @param id id del operador a buscar
     * @return Operator
     */
    @Override
    public Operator getOne(int id) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "SELECT * FROM operadores "
                        + "WHERE idOperador = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                Operator o = new Operator();
                o.setIdOperador( rs.getInt(( "idOperador" )) );
                o.setActivo( rs.getBoolean( "activo" ) );
                o.setDescanso( rs.getBoolean( "descanso" ) );
                o.setIdEmpleado( rs.getInt( "idEmpleado" ) );
                return o;
            }
            else {
                JOptionPane.showMessageDialog(
                        null,
                        "Ningún elemento coincide con el parámetro dado",
                        "Error al obtener operador",
                        JOptionPane.ERROR
                );
                return null;
            }
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta SELECT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al obtener operador",
                    JOptionPane.ERROR
            );
            return null;
        }
    }

    /**
     * Obtención del listado de todos los operadores
     * @return ArrayList<Operator>
     */
    @Override
    public ArrayList<Operator> getAll() {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "SELECT * FROM operadores";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            ArrayList<Operator> list = new ArrayList<Operator>();
            while ( rs.next() ) {
                Operator o = new Operator();
                o.setIdOperador( rs.getInt(( "idOperador" )) );
                o.setActivo( rs.getBoolean( "activo" ) );
                o.setDescanso( rs.getBoolean( "descanso" ) );
                o.setIdEmpleado( rs.getInt( "idEmpleado" ) );
            }
            return list;
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta SELECT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al obtener listado de operadores",
                    JOptionPane.ERROR
            );
            return null;
        }
    }

    @Override
    public ArrayList<Operator> query(Operator t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
