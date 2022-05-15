/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author bruno
 */
public class DbConnection {
    // Parámetros de conexión
    private final static String host = "mysql-shmagui.alwaysdata.net";
    private final static String port = "3306";
    private final static String user = "shmagui";
    private final static String pass = "$hMAna9emen7";
    private final static String dbName = "shmagui_db";
    private static boolean isDriverLoaded = false;
    
    /**
     * Connection string
     */
    private static String url = "jdbc:mysql://" + host +"/" + dbName;
    
    /**
     * Establecimiento de la cionexión
     */
    static {
        try {

            Class.forName( "com.mysql.cj.jdbc.Driver" );
            Class.forName( "com.mysql.jdbc.Driver" );
            isDriverLoaded = true;
        }
        catch( ClassNotFoundException ex ) {
            JOptionPane.showMessageDialog(
                null,
                "Ha ocurrido un error al establecer la conexión con la DB: " + ex.getMessage(),
                "Error de conexión",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    /** Contador de conexiones a la db */
    private static int count = 0;
    
    /**
     * Obtiene una conexión a la DB y la retorna en un objeto java.sql.Connection
     * @return Instancia de conexión a la DB (Connection)
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        if ( isDriverLoaded ) {
            conn = DriverManager.getConnection( url, user, pass );
            System.out.println( "Conexión " + count++ + " estabblecida correctamente" );
        }
        return conn;
    }
    
    /**
     * Cierra la coneción a la DB de una instancai pasada como parámetro
     * @param conn Una instancia de cinexión a la DB
     * @throws SQLException 
     */
    public static void closeConnection( Connection conn ) throws SQLException {
        if ( conn != null ) conn.close();
    }
}