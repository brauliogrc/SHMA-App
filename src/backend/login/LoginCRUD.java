/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.login;

import dbconnection.DbConnection;
import backend.login.UserData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author bruno
 */
public class LoginCRUD {
    
    /**
     * Obtiene la información del usuario en caso de ser encontrado
     * @param email Email del usuario
     * @param pass Password del usuario
     * @return UserData
     */
    public UserData login( String email, String pass ) {
        try {
            Connection conn = DbConnection.getConnection();
            String loginQuery = "SELECT e.idEmpleado, e.nombre, e.apellido_paterno, u.email, u.habilitado, e.rol "
                                + "FROM usuarios AS u "
                                    + "INNER JOIN empleados AS e "
                                        + "ON e.idEmpleado = u.idEmpleado "
                                + "WHERE email = ? AND password = MD5(?)";
            
            PreparedStatement ps =  conn.prepareStatement(loginQuery);
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                UserData ud = new UserData();
                ud.setId(rs.getInt(1));
                ud.setNombre(rs.getString(2));
                ud.setApellido_paterno(rs.getString(3));
                ud.setEmail(rs.getString(4));
                ud.setHabilitado(rs.getBoolean(5));
                ud.setRol(rs.getInt(6));
                return ud;
            }
            else return null;   
        }
        catch( SQLException sqlex ) {
            return null;
        }
    }
}
