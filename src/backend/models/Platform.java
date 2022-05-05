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
public class Platform {
    
    private int idPlatform;
    private int idMainBox;
    private int idDolly;
    private int idSecondBox;

    public Platform(int idPlatform, int idMainBox) {
        this.idPlatform = idPlatform;
        this.idMainBox = idMainBox;
    }

    public Platform(int idPlatform, int idMainBox, int idDolly, int idSecondBox) {
        this.idPlatform = idPlatform;
        this.idMainBox = idMainBox;
        this.idDolly = idDolly;
        this.idSecondBox = idSecondBox;
    }

    public int getIdMainBox() {
        return idMainBox;
    }

    public void setIdMainBox(int idMainBox) {
        this.idMainBox = idMainBox;
    }

    public int getIdDolly() {
        return idDolly;
    }

    public void setIdDolly(int idDolly) {
        this.idDolly = idDolly;
    }

    public int getIdSecondBox() {
        return idSecondBox;
    }

    public void setIdSecondBox(int idSecondBox) {
        this.idSecondBox = idSecondBox;
    }

    public int getIdPlatform() {
        return idPlatform;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.idPlatform;
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
        final Platform other = (Platform) obj;
        return true;
    }
    
    
    
    
}
