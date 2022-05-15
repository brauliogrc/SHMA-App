/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.models;

/**
 * Modelo de la tabla empleados en la db
 * @author bruno
 */
public class Employee {
    
    public Employee(){}
    
    public Employee( int id ) {
        this.idEmpleado = id;
    }
    
    /**
     * Id del empleado
     */
    private int idEmpleado;
    /**
     * Función del empleado
     */
    private String funcion;
    /**
     * Nombre del empleado
     */
    private String nombre;
    /**
     * Primer apellido del empleado
     */
    private String apellido_paterno;
    /**
     * Segundo apellido del empleado
     */
    private String apellido_materno;
    /**
     * Telefono del empleado
     */
    private String telefono;
    /**
     * Imagen del empleado
     */
    private String imagen;
    private int rol;

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idEmpleado;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (this.idEmpleado != other.idEmpleado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "idEmpleado=" + idEmpleado + ", funcion=" + funcion + ", nombre=" + nombre + ", apellido_paterno=" + apellido_paterno + ", apellido_materno=" + apellido_materno + ", telefono=" + telefono + ", imagen=" + imagen + '}';
    }
    
    public boolean verifyContent() {
        boolean isValid = true;
        if ( nombre == null || nombre.length() <= 0 ) isValid = false;
        if ( apellido_paterno == null || apellido_paterno.length() <= 0 ) isValid = false;
        if ( apellido_materno == null || apellido_materno.length() <= 0 ) isValid = false;
        if ( funcion == null || funcion.length() <= 0 ) isValid = false;
        if ( telefono == null || telefono.length() <= 0 ) isValid = false;
        if ( imagen == null || imagen.length() <= 0 ) isValid = false;
        
        return isValid;
    }
}
