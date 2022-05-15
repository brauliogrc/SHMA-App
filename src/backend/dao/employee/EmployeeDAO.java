/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.dao.employee;

import backend.ICRUD.ICRUD;
import backend.models.Employee;
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
public class EmployeeDAO implements ICRUD<Employee> {

    /**
     * Inserta un nuevo empleado en la DB
     * @param t Objeto de clase Employee
     * @return boolean
     */
    @Override
    public boolean add(Employee t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "INSERT INTO empleados( funcion, nombre, apellido_paterno, apellido_materno, telefono, imagen ) "
                    + "VALUES( ?, ?, ?, ?, ?, ? )";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, t.getFuncion());
            ps.setString(2, t.getNombre());
            ps.setString(3, t.getApellido_paterno());
            ps.setString(4, t.getApellido_materno());
            ps.setString(5, t.getTelefono());
            ps.setString(6, t.getImagen());
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta INSERT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al agregar nuevo empleado",
                    JOptionPane.ERROR
            );
            return false;
        }
    }

    /**
     * Actualiza un empleado en la DB
     * @param t Objeto de clase Employee
     * @return boolean
     */
    @Override
    public boolean update(Employee t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "UPDATE empleados "
                    + "SET "
                        + "funcion = ?, "
                        + "nombre = ?, "
                        + "apellido_paterno = ?, "
                        + "apellido_materno = ?, "
                        + "telefono = ?, "
                        + "imagen = ? "
                    + "WHERE idEmpleado = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, t.getFuncion());
            ps.setString(2, t.getNombre());
            ps.setString(3, t.getApellido_paterno());
            ps.setString(4, t.getApellido_materno());
            ps.setString(5, t.getTelefono());
            ps.setString(6, t.getImagen());
            ps.setInt(7, t.getIdEmpleado());
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta UPDATE:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al actualizar empleado",
                    JOptionPane.ERROR
            );
            return false;
        }
    }

    /**
     * Elimina un empleado de la DB
     * @param t Objeto de clase Employee
     * @return boolean
     */
    @Override
    public boolean delete(Employee t) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "DELETE FROM empleados "
                            + "WHERE idEmpleado = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.getIdEmpleado());
            int records = ps.executeUpdate();
            
            return ( records > 0 ) ? true : false;
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta DELETE:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al eliminar empleado",
                    JOptionPane.ERROR
            );
            return false;
        }
    }

    /**
     * Obtención de un empleado según el parámetro espcificado
     * @param id id del empleado a buscar
     * @return Employee
     */
    @Override
    public Employee getOne(int id) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "SELECT * FROM empleados "
                            + "WHERE idEmpleado = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                Employee e = new Employee();
                e.setIdEmpleado( rs.getInt( "idEmpleado" ) );
                e.setFuncion( rs.getString( "funcion" ) );
                e.setNombre( rs.getString( "nombre" ) );
                e.setApellido_paterno( rs.getString( "apellido_paterno" ) );
                e.setApellido_materno( rs.getString( "apellido_materno" ) );
                e.setTelefono( rs.getString( "telefono" ) );
                e.setImagen( rs.getString( "imagen" ) );
                
                return e;
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
                    "Error al obtener empleado",
                    JOptionPane.ERROR
            );
            return null;
        }
    }

    /**
     * Obtención del listado de todos los empleados
     * @return ArrayList<Employee>
     */
    @Override
    public ArrayList<Employee> getAll() {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "SELECT * FROM empleados";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Employee> list = new ArrayList<Employee>();
            while ( rs.next() ) {
                Employee e = new Employee();
                e.setIdEmpleado( rs.getInt( "idEmpleado" ) );
                e.setFuncion( rs.getString( "funcion" ) );
                e.setNombre( rs.getString( "nombre" ) );
                e.setApellido_paterno( rs.getString( "apellido_paterno" ) );
                e.setApellido_materno( rs.getString( "apellido_materno" ) );
                e.setTelefono(rs.getString( "telefono" ) );
                e.setImagen(rs.getString( "imagen" ) );
                
                list.add(e);
            }
            
            return list;
            
        }
        catch( SQLException sqlex ) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error con consulta SELECT:\n\t" + sqlex.getMessage() + "\n\n\t" + sqlex.getSQLState(),
                    "Error al obtener listado de empleados",
                    JOptionPane.ERROR
            );
            return null;
        }
    }

    @Override
    public ArrayList<Employee> query(Employee t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
