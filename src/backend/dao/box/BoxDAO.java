/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.dao.box;

import backend.ICRUD.ICRUD;
import backend.models.Box;
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
public class BoxDAO implements ICRUD<Box>{

    @Override
    public boolean add(Box t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "INSERT INTO cajas ( numero_economico_caja, numero_serie_caja, modelo, mantenimiento, activo )"
                    + " VALUES( ?, ?, ?, ?, ?)";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, t.getNumero_economico());
            ps.setString(2, t.getNumero_serie());
            ps.setString(3, t.getModelo());
            ps.setBoolean(4, t.isMantenimiento());
            ps.setBoolean(5, t.isActivo());
            ps.setInt(6, t.getIdCaja() );
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
            
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta INSERT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(), 
                    "Error al agregar una nueva caja",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    @Override
    public boolean update(Box t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "UPDATE cajas "
                            + "SET "
                                + "numero_economico_caja = ?, "
                                + "numero_serie_caja = ?, "
                                + "modelo = ?, "
                                + "mantenimiento = ?, "
                                + "activo = ? "
                            + "WHERE idCaja = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, t.getNumero_economico());
            ps.setString(2, t.getNumero_serie());
            ps.setString(3, t.getModelo());
            ps.setBoolean(4, t.isMantenimiento());
            ps.setBoolean(5, t.isActivo());
            ps.setInt(6, t.getIdCaja());
            int records = ps.executeUpdate();
            return ( records > 0 ) ? true : false;
            
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta UPDATE:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al actualizar la caja",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    @Override
    public boolean delete(Box t) {
            try {
            Connection conn = DbConnection.getConnection();
            String query = "DELETE FROM cajas"
                            + "WHERE idCaja= ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.getIdCaja());
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
            
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta DELETE:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al eliminar la caja",
                    JOptionPane.ERROR
            );
            return false;
        }
    }

    @Override
    public Box getOne(int id) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "SELECT * FROM cajas "
                            + "WHERE idCaja = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if( rs.next() ) {
                Box t = new Box( rs.getInt( "idCaja" ));
                t.setNumero_economico(rs.getString( "numero_economico_caja" ));
                t.setNumero_serie(rs.getString( "numero_serie_caja" ));
                t.setModelo(rs.getString( "modelo" ));
                t.setMantenimiento( rs.getBoolean("mantenimiento"));
                t.setActivo( rs.getBoolean("activo"));
          
                return t;
            }
            else {
                JOptionPane.showMessageDialog(
                    null,
                    "Ningún elemento coincide con el parámetro dado",
                    "Error al obtener caja",
                    JOptionPane.ERROR_MESSAGE
                );
                return null;
            }
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta SELECT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al obtener caja",
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }
    }

    @Override
    public ArrayList<Box> getAll() {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "SELECT * FROM cajas";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            ArrayList<Box> list = new ArrayList<Box>();
            while( rs.next() ) {
                Box t = new Box( rs.getInt( "idCaja" ));
                t.setNumero_economico(rs.getString( "numero_economico_caja" ));
                t.setNumero_serie(rs.getString( "numero_serie_caja" ));
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
                    "Error al obtener listado de cajas",
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }
    }

    @Override
    public ArrayList<Box> query(Box t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}