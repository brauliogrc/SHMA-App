/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.models;

import java.util.Objects;

/**
 *
 * @author Manuel
 */
public class Truck {
    
    private int idCamion;
    private String numero_economico_camion;
    private String numero_serie_camion;
    private String modelo;
    private boolean mantenimiento;
    private boolean activo;    

    public Truck(int idCamione) {
        this.idCamion = idCamione;
    }

    public String getNumero_economico_camion() {
        return numero_economico_camion;
    }

    public void setNumero_economico_camion(String numero_economico_camion) {
        this.numero_economico_camion = numero_economico_camion;
    }

    public String getNumero_serie_camion() {
        return numero_serie_camion;
    }

    public void setNumero_serie_camion(String numero_serie_camion) {
        this.numero_serie_camion = numero_serie_camion;
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
        hash = 73 * hash + this.idCamion;
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
        return true;
    }

    @Override
    public String toString() {
        return "Truck{" + "idCamione=" + idCamion + ", numero_economico_camion=" + numero_economico_camion + ", numero_serie_camion=" + numero_serie_camion + ", modelo=" + modelo + ", mantenimiento=" + mantenimiento + ", activo=" + activo + '}';
    }

    
    
    
}
