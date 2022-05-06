/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.dao.truck;

import backend.ICRUD.ICRUD;
import backend.models.Truck;
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
public class TruckDAO implements ICRUD<Truck>{

    @Override
    public boolean add(Truck t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "INSERT INTO camiones ( numero_economico_camion, numero_serie_camion, modelo, mantenimiento, activo )"
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
                    "Error al agregar un nuevo camion",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    @Override
    public boolean update(Truck t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "UPDATE camiones "
                            + "SET "
                                + "numero_economico_camion = ?, "
                                + "numero_serie_camion = ?, "
                                + "modelo = ?, "
                                + "mantenimiento = ?, "
                                + "activo = ? "
                            + "WHERE idCamion = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, t.getNumero_economico());
            ps.setString(2, t.getNumero_serie());
            ps.setString(3, t.getModelo());
            ps.setBoolean(4, t.isMantenimiento());
            ps.setBoolean(5, t.isActivo());
            ps.setInt(6, t.getIdCamion());
            int records = ps.executeUpdate();
            return ( records > 0 ) ? true : false;
            
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta UPDATE:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al actualizar el traspaso",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    @Override
    public boolean delete(Truck t) {
            try {
            Connection conn = DbConnection.getConnection();
            String query = "DELETE FROM camiones"
                            + "WHERE idCamion = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.getIdCamion());
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
            
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta DELETE:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al eliminar el traspaso",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    @Override
    public Truck getOne(int id) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "SELECT * FROM camiones "
                            + "WHERE idCamion = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if( rs.next() ) {
                Truck t = new Truck( rs.getInt( "idCamion" ));
                t.setNumero_economico(rs.getString( "numero_economico_camion" ));
                t.setNumero_serie(rs.getString( "numero_serie_camion" ));
                t.setModelo(rs.getString( "modelo" ));
                t.setMantenimiento( rs.getBoolean("mantenimiento"));
                t.setActivo( rs.getBoolean("activo"));

          
                return t;
            }
            else {
                JOptionPane.showMessageDialog(
                    null,
                    "Ningún elemento coincide con el parámetro dado",
                    "Error al obtener camion",
                    JOptionPane.ERROR_MESSAGE
                );
                return null;
            }
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta SELECT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al obtener camion",
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }
    }

    @Override
    public ArrayList<Truck> getAll() {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "SELECT * FROM camiones";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            ArrayList<Truck> list = new ArrayList<Truck>();
            while( rs.next() ) {
                Truck t = new Truck( rs.getInt( "idCamion" ));
                t.setNumero_economico(rs.getString( "numero_economico_camion" ));
                t.setNumero_serie(rs.getString( "numero_serie_camion" ));
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
                    "Error al obtener listado de camiones",
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }
    }

    @Override
    public ArrayList<Truck> query(Truck t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
