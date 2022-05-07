/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.dao.travel;

import backend.ICRUD.ICRUD;
import backend.models.Travel;
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
public class TravelDAO implements ICRUD<Travel>{

    @Override
    public boolean add(Travel t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "INSERT INTO viajes ( hora_inicial, hora_final, fecha_inicial, fecha_final, kilometraje_salida, kilometraje_llegada, idTraspaso, idCamion, idPlataforma, idOperador )"
                    + " VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, t.getHora_inicial());
            ps.setString(2, t.getHora_final());
            ps.setString(3, t.getFecha_inicial());
            ps.setString(4, t.getFecha_final());
            ps.setDouble(5, t.getKilometraje_salida());
            ps.setDouble(6, t.getKilometraje_llegada());
            ps.setInt(7, t.getIdTraspaso());
            ps.setInt(8, t.getIdCamion());
            ps.setInt(9, t.getIdPlataforma());
            ps.setInt(10, t.getIdOperador());
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
            
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta INSERT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(), 
                    "Error al agregar un nuevo viaje",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    @Override
    public boolean update(Travel t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "UPDATE viajes "
                            + "SET "
                                + "hora_inicial = ?, "
                                + "hora_final = ?, "
                                + "fecha_inicial = ?, "
                                + "fecha_final = ?, "
                                + "kilometraje_salida = ?, "
                                + "kilometraje_llegada = ?, "
                                + "idTraspaso = ?, "
                                + "idCamion = ?, "
                                + "idPlataforma = ?, "
                                + "idOperador = ? "
                            + "WHERE idViaje= ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, t.getHora_inicial());
            ps.setString(2, t.getHora_final());
            ps.setString(3, t.getFecha_inicial());
            ps.setString(4, t.getFecha_final());
            ps.setDouble(5, t.getKilometraje_salida());
            ps.setDouble(6, t.getKilometraje_llegada());
            ps.setInt(7, t.getIdTraspaso());
            ps.setInt(8, t.getIdCamion());
            ps.setInt(9, t.getIdPlataforma());
            ps.setInt(10, t.getIdOperador());
            ps.setInt(11, t.getIdViaje());
            int records = ps.executeUpdate();
            return ( records > 0 ) ? true : false;
            
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta UPDATE:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al actualizar el viaje",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    @Override
    public boolean delete(Travel t) {
            try {
            Connection conn = DbConnection.getConnection();
            String query = "DELETE FROM viajes"
                            + "WHERE idViaje = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.getIdViaje());
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
            
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta DELETE:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al eliminar el viaje",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    @Override
    public Travel getOne(int id) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "SELECT * FROM viajes "
                            + "WHERE idViaje = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if( rs.next() ) {
                Travel t = new Travel( rs.getInt( "idViaje" ));
                t.setHora_inicial(rs.getString( "hora_inicial" ));
                t.setHora_final(rs.getString( "hora_final" ));
                t.setFecha_inicial(rs.getString( "fecha_inicial" ));
                t.setFecha_final(rs.getString( "fecha_final" ));
                t.setKilometraje_salida(rs.getDouble( "kilometraje_salida" ));
                t.setKilometraje_llegada(rs.getDouble( "kilometraje_llegada" ));
                t.setIdTraspaso(rs.getInt( "idTraspaso" ));
                t.setIdCamion(rs.getInt( "idCamion" ));
                t.setIdPlataforma(rs.getInt( "idPlataforma" ));
                t.setIdOperador(rs.getInt( "idOperador" ));
          
                return t;
            }
            else {
                JOptionPane.showMessageDialog(
                    null,
                    "Ningún elemento coincide con el parámetro dado",
                    "Error al obtener viaje",
                    JOptionPane.ERROR_MESSAGE
                );
                return null;
            }
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta SELECT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al obtener viaje",
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }
    }

    @Override
    public ArrayList<Travel> getAll() {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "SELECT * FROM viajes";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            ArrayList<Travel> list = new ArrayList<Travel>();
            while( rs.next() ) {
                Travel t = new Travel( rs.getInt( "idViaje" ));
                t.setHora_inicial(rs.getString( "hora_inicial" ));
                t.setHora_final(rs.getString( "hora_final" ));
                t.setFecha_inicial(rs.getString( "fecha_inicial" ));
                t.setFecha_final(rs.getString( "fecha_final" ));
                t.setKilometraje_salida(rs.getDouble( "kilometraje_salida" ));
                t.setKilometraje_llegada(rs.getDouble( "kilometraje_llegada" ));
                t.setIdTraspaso(rs.getInt( "idTraspaso" ));
                t.setIdCamion(rs.getInt( "idCamion" ));
                t.setIdPlataforma(rs.getInt( "idPlataforma" ));
                t.setIdOperador(rs.getInt( "idOperador" ));
                list.add(t);
            }
            return list;
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta SELECT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al obtener listado de viajes",
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }
    }

    @Override
    public ArrayList<Travel> query(Travel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
