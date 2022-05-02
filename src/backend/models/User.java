/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.models;

/**
 * Modelo de la tabla Usuarios en la db
 * @author bruno
 */
public class User {
    
    public User(){}
    
    public User( int id ) {
        this.idUsuario = id;
    }
    
    /**
     * Id del usuario
     */
    private int idUsuario;
    /**
     * Email del usuario
     */
    private String email;
    /**
     * Contraseña del usuario
     */
    private String password;
    /**
     * 
     */
    private boolean activo;
    /**
     * 
     */
    private boolean habilitado;
    /**
     * 
     */
    private boolean recuperacion;
    /**
     * FK de la tabla empleados. Representa el Id de un empleado
     */
    private int idEmpleado;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public boolean isRecuperacion() {
        return recuperacion;
    }

    public void setRecuperacion(boolean recuperacion) {
        this.recuperacion = recuperacion;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.idUsuario;
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
        final User other = (User) obj;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "idUsuario=" + idUsuario + ", email=" + email + ", password=" + password + ", activo=" + activo + ", habilitado=" + habilitado + ", recuperacion=" + recuperacion + ", idEmpleado=" + idEmpleado + '}';
    }
}
