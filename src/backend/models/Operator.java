/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.models;

/**
 *
 * @author bruno
 */
public class Operator {
    
    public Operator(){}
    
    public Operator( int id ) {
        this.idOperador = id;
    }
    
    /**
     * Id del operador
     */
    private int idOperador;
    /**
     * Estatus del operador
     */
    private boolean activo;
    /**
     * Indicador si em operador se encuentra en su descanso
     */
    private boolean descanso;
    /**
     * FK de la tabla empleados. Representa el Id de un empleado
     */
    private int idEmpleado;
    private String employeeName;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(int idOperador) {
        this.idOperador = idOperador;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isDescanso() {
        return descanso;
    }

    public void setDescanso(boolean descanso) {
        this.descanso = descanso;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.idOperador;
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
        final Operator other = (Operator) obj;
        if (this.idOperador != other.idOperador) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Operator{" + "idOperador=" + idOperador + ", activo=" + activo + ", descanso=" + descanso + ", idEmpleado=" + idEmpleado + '}';
    }
    
    
}
