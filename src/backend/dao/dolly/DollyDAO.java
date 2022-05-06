/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.dao.dolly;

import backend.ICRUD.ICRUD;
import backend.models.Dolly;
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
public class DollyDAO implements ICRUD<Dolly>{

    @Override
    public boolean add(Dolly t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "INSERT INTO dollies ( numero_economico_dolly, numero_serie_dolly, modelo, mantenimiento, activo )"
                    + " VALUES( ?, ?, ?, ?, ?)";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, t.getNumero_economico());
            ps.setString(2, t.getNumero_serie());
            ps.setString(3, t.getModelo());
            ps.setBoolean(4, t.isMantenimiento());
            ps.setBoolean(5, t.isActivo());
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
            
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta INSERT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(), 
                    "Error al agregar un nuevo dolly",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    @Override
    public boolean update(Dolly t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "UPDATE dollies "
                            + "SET "
                                + "numero_economico_dolly = ?, "
                                + "numero_serie_dolly = ?, "
                                + "modelo = ?, "
                                + "mantenimiento = ?, "
                                + "activo = ? "
                            + "WHERE idDolly = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, t.getNumero_economico());
            ps.setString(2, t.getNumero_serie());
            ps.setString(3, t.getModelo());
            ps.setBoolean(4, t.isMantenimiento());
            ps.setBoolean(5, t.isActivo());
            ps.setInt(6, t.getIdDolly());
            int records = ps.executeUpdate();
            return ( records > 0 ) ? true : false;
            
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta UPDATE:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al actualizar el dolly",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    @Override
    public boolean delete(Dolly t) {
            try {
            Connection conn = DbConnection.getConnection();
            String query = "DELETE FROM dollies"
                            + "WHERE idDolly= ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.getIdDolly());
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
            
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta DELETE:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al eliminar el dolly",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    @Override
    public Dolly getOne(int id) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "SELECT * FROM dollies "
                            + "WHERE idDolly = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if( rs.next() ) {
                Dolly t = new Dolly( rs.getInt( "idDolly" ));
                t.setNumero_economico(rs.getString( "numero_economico_dolly" ));
                t.setNumero_serie(rs.getString( "numero_serie_dolly" ));
                t.setModelo(rs.getString( "modelo" ));
                t.setMantenimiento( rs.getBoolean("mantenimiento"));
                t.setActivo( rs.getBoolean("activo"));
                return t;
            }
            else {
                JOptionPane.showMessageDialog(
                    null,
                    "Ningún elemento coincide con el parámetro dado",
                    "Error al obtener dolly",
                    JOptionPane.ERROR_MESSAGE
                );
                return null;
            }
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta SELECT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al obtener dolly",
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }
    }

    @Override
    public ArrayList<Dolly> getAll() {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "SELECT * FROM dollies";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            ArrayList<Dolly> list = new ArrayList<Dolly>();
            while( rs.next() ) {
                Dolly t = new Dolly( rs.getInt( "idDolly" ));
                t.setNumero_economico(rs.getString( "numero_economico_dolly" ));
                t.setNumero_serie(rs.getString( "numero_serie_dolly" ));
                t.setModelo(rs.getString( "modelo" ));
                t.setMantenimiento( rs.getBoolean("mantenimiento"));
                t.setActivo( rs.getBoolean("activo"));
                list.add(t);
            }
            return list;
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta SELECT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al obtener listado de dollies",
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }
    }

    @Override
    public ArrayList<Dolly> query(Dolly t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

