/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.models;

/**
 *
 * @author Manuel
 */
public class Box {
        
    private int idCaja;
    private String numero_economico;
    private String numero_serie;
    private String modelo;
    private boolean mantenimiento;
    private boolean activo;    

    public Box(int idCaja) {
        this.idCaja = idCaja;
    }

    public Box() {
    
    }

    public int getIdCaja() {
        return idCaja;
    }

    
    public String getNumero_economico() {
        return numero_economico;
    }

    public void setNumero_economico(String numero_economico) {
        this.numero_economico = numero_economico;
    }

    public String getNumero_serie() {
        return numero_serie;
    }

    public void setNumero_serie(String numero_serie) {
        this.numero_serie = numero_serie;
    }



    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public boolean isMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(boolean mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.idCaja;
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
        final Box other = (Box) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Box{" + "idCaja=" + idCaja + ", numero_economico=" + numero_economico + ", numero_serie=" + numero_serie + ", modelo=" + modelo + ", mantenimiento=" + mantenimiento + ", activo=" + activo + '}';
    }

    
}
