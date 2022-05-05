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
public class Dolly {
    
    private int idDolly;
    private String numero_economico_dolly;
    private String numero_serie_dolly;
    private String modelo;
    private boolean mantenimiento;
    private boolean activo;    

    public Dolly(int idDolly) {
        this.idDolly = idDolly;
    }

    public String getNumero_economico_dolly() {
        return numero_economico_dolly;
    }

    public void setNumero_economico_dolly(String numero_economico_dolly) {
        this.numero_economico_dolly = numero_economico_dolly;
    }

    public String getNumero_serie_dolly() {
        return numero_serie_dolly;
    }

    public void setNumero_serie_dolly(String numero_serie_dolly) {
        this.numero_serie_dolly = numero_serie_dolly;
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
        hash = 79 * hash + this.idDolly;
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
        final Dolly other = (Dolly) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Dolly{" + "idDolly=" + idDolly + ", numero_economico_dolly=" + numero_economico_dolly + ", numero_serie_dolly=" + numero_serie_dolly + ", modelo=" + modelo + ", mantenimiento=" + mantenimiento + ", activo=" + activo + '}';
    }
    
    
}
