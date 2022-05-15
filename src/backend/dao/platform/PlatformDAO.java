/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.dao.platform;

import backend.ICRUD.ICRUD;
import backend.models.Platform;
import dbconnection.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Manuel
 */
public class PlatformDAO implements ICRUD<Platform> {

    /**
     * Inserta un nuevo operador en la DB
     * @param t Objeto de clase Operator
     * @return boolean
     */
    @Override
    public boolean add(Platform t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "INSERT INTO plataformas( idDolly, idPrimera, idSegunda )"
                    + " VALUES( ?, ?, ? )";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.getIdDolly());
            ps.setInt(2, t.getIdMainBox());
            ps.setInt(3, t.getIdSecondBox());
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
            
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta INSERT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al agregar una nueva plataforma",
                    JOptionPane.ERROR_MESSAGE
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
    public boolean update(Platform t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "UPDATE plataformas"
                            + "SET "
                                + "idDolly = ?, "
                                + "idPrimera = ?, "
                                + "idSegunda = ? "
                            + "WHERE idPlataforma = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.getIdDolly());
            ps.setInt(2, t.getIdMainBox());
            ps.setInt(3, t.getIdSecondBox());
            ps.setInt(4, t.getIdPlatform());
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
            
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta UPDATE:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al actualizar plataforma",
                    JOptionPane.ERROR_MESSAGE
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
    public boolean delete(Platform t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "DELETE FROM plataformas"
                            + "WHERE idPlataforma = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.getIdPlatform());
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
            
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta DELETE:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al eliminar la plataforma",
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
    public Platform getOne(int id) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "SELECT * FROM plataformas "
                        + "WHERE idPlataforma = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                Platform o = new Platform(rs.getInt( "idPlataforma" ));
                o.setIdDolly( rs.getInt( "idDolly" ) );
                o.setIdMainBox(rs.getInt( "idPrimera" ));
                o.setIdSecondBox(rs.getInt( "idSegunda" ));
                return o;
            }
            else {
                JOptionPane.showMessageDialog(
                        null,
                        "Ningún elemento coincide con el parámetro dado",
                        "Error al obtener plataforma",
                        JOptionPane.ERROR_MESSAGE
                );
                return null;
            }
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta SELECT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al obtener plataforma",
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
    public ArrayList<Platform> getAll() {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "SELECT * FROM plataformas";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            ArrayList<Platform> list = new ArrayList<Platform>();
            while ( rs.next() ) {
                Platform o = new Platform(rs.getInt( "idPlataforma" ));
                o.setIdDolly( rs.getInt( "idDolly" ) );
                o.setIdMainBox(rs.getInt( "idPrimera" ));
                o.setIdSecondBox(rs.getInt( "idSegunda" ));
            }
            return list;
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta SELECT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al obtener listado de plataformas",
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }
    }

    @Override
    public ArrayList<Platform> query(Platform t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
