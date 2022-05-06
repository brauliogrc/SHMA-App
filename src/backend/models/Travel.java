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
public class Travel {
    
    
    private int idViaje;
    private String hora_inicial;
    private String hora_final;
    private String fecha_inicial;
    private String fecha_final;
    private Double kilometraje_salida;
    private Double kilometraje_llegada;
    private int idTraspaso;
    private int idPlataforma;
    private int idCamion;
    private int idOperador;

    public Travel(int idViaje) {
        this.idViaje = idViaje;
    }

    public int getIdViaje() {
        return idViaje;
    }
    
    public String getHora_inicial() {
        return hora_inicial;
    }

    public void setHora_inicial(String hora_inicial) {
        this.hora_inicial = hora_inicial;
    }

    public String getHora_final() {
        return hora_final;
    }

    public void setHora_final(String hora_final) {
        this.hora_final = hora_final;
    }

    public String getFecha_inicial() {
        return fecha_inicial;
    }

    public void setFecha_inicial(String fecha_inicial) {
        this.fecha_inicial = fecha_inicial;
    }

    public String getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(String fecha_final) {
        this.fecha_final = fecha_final;
    }

    public Double getKilometraje_salida() {
        return kilometraje_salida;
    }

    public void setKilometraje_salida(Double kilometraje_salida) {
        this.kilometraje_salida = kilometraje_salida;
    }

    public Double getKilometraje_llegada() {
        return kilometraje_llegada;
    }

    public void setKilometraje_llegada(Double kilometraje_llegada) {
        this.kilometraje_llegada = kilometraje_llegada;
    }

    public int getIdTraspaso() {
        return idTraspaso;
    }

    public void setIdTraspaso(int idTraspaso) {
        this.idTraspaso = idTraspaso;
    }

    public int getIdPlataforma() {
        return idPlataforma;
    }

    public void setIdPlataforma(int idPlataforma) {
        this.idPlataforma = idPlataforma;
    }

    public int getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(int idCamion) {
        this.idCamion = idCamion;
    }

    public int getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(int idOperador) {
        this.idOperador = idOperador;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.idViaje;
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
        final Travel other = (Travel) obj;
        return true;
    }
    
    
    
}
