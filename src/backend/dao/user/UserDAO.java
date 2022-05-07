/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.dao.user;

import backend.ICRUD.ICRUD;
import backend.models.User;
import dbconnection.DbConnection;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author bruno
 */
public class UserDAO implements ICRUD<User> {

    /**
     * Inserta un nuevo usuario en la DB
     * @param t Objeto de clase User
     * @return boolean
     */
    @Override
    public boolean add(User t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "INSERT INTO usuarios( email, password, activo, habilitado, recuperacion, idEmpleado ) "
                    + "VALUES( ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, t.getEmail());
            ps.setString(2, t.getPassword());
            ps.setBoolean(3, t.isActivo());
            ps.setBoolean(4, t.isHabilitado());
            ps.setBoolean(5, t.isRecuperacion());
            ps.setInt(6, t.getIdEmpleado());
            
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta INSERT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al agregar nuevo usuario",
                    JOptionPane.ERROR
            );
            return false;
        }
    }

    /**
     * Actualiza un usuario en la DB
     * @param t Objeto de clase User
     * @return 
     */
    @Override
    public boolean update(User t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "UPDATE usuarios "
                            + "SET "
                                + "email = ?, "
                                + "password = ?, "
                                + "activo = ?, "
                                + "habilitado = ?, "
                                + "recuperacion = ?, "
                                + "idEmpleado = ? "
                            + "WHERE idUsuario = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, t.getEmail());
            ps.setString(2, t.getPassword());
            ps.setBoolean(3, t.isActivo());
            ps.setBoolean(4, t.isHabilitado());
            ps.setBoolean(5, t.isRecuperacion());
            ps.setInt(6, t.getIdEmpleado());
            ps.setInt(7, t.getIdUsuario());
            
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta UPDATE:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al actualizar usuario",
                    JOptionPane.ERROR
            );
            return false;
        }
    }

    /**
     * Elimina un usuario de la DB
     * @param t Objeto de clase User
     * @return boolean
     */
    @Override
    public boolean delete(User t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "DELETE FROM usuarios "
                            + "WHERE idUsuario = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.getIdUsuario());
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta DELETE:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al eliminar usuario",
                    JOptionPane.ERROR
            );
            return false;
        }
    }

    /**
     * Obtención de un usuario según el parámetro especificado
     * @param id id del usuario a buscar
     * @return User
     */
    @Override
    public User getOne(int id) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "SELECT * FROM usuarios "
                            + "WHERE idUsuario = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                User u = new User();
                u.setIdUsuario( rs.getInt( "idUsuario" ) );
                u.setEmail( rs.getString( "email" ) );
                u.setPassword( rs.getString( "password" ) );
                u.setActivo( rs.getBoolean( "activo" ) );
                u.setHabilitado( rs.getBoolean( "habilitado" ) );
                u.setRecuperacion( rs.getBoolean( "recuperacion" ) );
                u.setIdEmpleado( rs.getInt( "idEmpleado" ) );
                return u;
            }
            else {
                JOptionPane.showMessageDialog(
                    null,
                    "Ningún elemento coincide con el parámetro dado",
                    "Error al obtener usuario",
                    JOptionPane.ERROR
            );
                return null;
            }
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta SELECT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al obtener usuario",
                    JOptionPane.ERROR
            );
            return null;
        }
    }

    /**
     * Obtención de un listado de todos los usuarios
     * @return ArrayList<User>
     */
    @Override
    public ArrayList<User> getAll() {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "SELECT * FROM usuarios";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            
            ArrayList<User> list = new ArrayList<User>();
            
            while ( rs.next() ) {
                User u = new User();
                u.setIdUsuario( rs.getInt( "idUsuario" ) );
                u.setEmail( rs.getString( "email" ) );
                u.setPassword( rs.getString( "password" ) );
                u.setActivo( rs.getBoolean( "activo" ) );
                u.setHabilitado( rs.getBoolean( "habilitado" ) );
                u.setRecuperacion( rs.getBoolean( "recuperacion" ) );
                u.setIdEmpleado( rs.getInt( "idEmpleado" ) );
                
                list.add(u);
            }
            return list;
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta SELECT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al obtener listado de usuarios",
                    JOptionPane.ERROR
            );
            return null;
        }
    }

    @Override
    public ArrayList<User> query(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
