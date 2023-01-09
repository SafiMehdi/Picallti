package data;

import java.io.Serializable;

public class Vehicule implements Serializable {
    private int id;
    private String marque;
    private VehiculeType vehiculeType;



    public Vehicule(int id, String marque,VehiculeType vehiculeType) {
        this.id = id;
        this.marque = marque;
        this.vehiculeType = vehiculeType;
    }
    public Vehicule( String marque,VehiculeType vehiculeType) {
        this.marque = marque;
        this.vehiculeType = vehiculeType;
    }
    public Vehicule(String marque) {
        this.marque = marque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }


    public VehiculeType getVehiculeType() {
        return vehiculeType;
    }

    public void setVehiculeType(VehiculeType vehiculeType) {
        this.vehiculeType = vehiculeType;
    }
}
