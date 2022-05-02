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
public class Transfer {
    
    public Transfer(){}
    
    public Transfer( int id ) {
        this.idTraspaso = id;
    }
    
    /**
     * Id del traspaso
     */
    private int idTraspaso;
    /**
     * Número del traspaso
     */
    private String numero_traspado;
    /**
     * FK de la tabla empleados. Representa el Id de un empleado
     */
    private int idEmpleado;

    public int getIdTraspaso() {
        return idTraspaso;
    }

    public void setIdTraspaso(int idTraspaso) {
        this.idTraspaso = idTraspaso;
    }

    public String getNumero_traspado() {
        return numero_traspado;
    }

    public void setNumero_traspado(String numero_traspado) {
        this.numero_traspado = numero_traspado;
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
        hash = 29 * hash + this.idTraspaso;
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
        final Transfer other = (Transfer) obj;
        if (this.idTraspaso != other.idTraspaso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Transfer{" + "idTraspaso=" + idTraspaso + ", numero_traspado=" + numero_traspado + ", idEmpleado=" + idEmpleado + '}';
    }
    
    
}
