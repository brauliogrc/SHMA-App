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
    private String numero_economico_caja;
    private String numero_serie_caja;
    private String modelo;
    private boolean mantenimiento;
    private boolean activo;    

    public Box(int idCaja) {
        this.idCaja = idCaja;
    }

    public String getNumero_economico_caja() {
        return numero_economico_caja;
    }

    public void setNumero_economico_caja(String numero_economico_caja) {
        this.numero_economico_caja = numero_economico_caja;
    }

    public String getNumero_serie_caja() {
        return numero_serie_caja;
    }

    public void setNumero_serie_caja(String numero_serie_caja) {
        this.numero_serie_caja = numero_serie_caja;
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
        return "Box{" + "idCaja=" + idCaja + ", numero_economico_caja=" + numero_economico_caja + ", numero_serie_caja=" + numero_serie_caja + ", modelo=" + modelo + ", mantenimiento=" + mantenimiento + ", activo=" + activo + '}';
    }
    
    
}
