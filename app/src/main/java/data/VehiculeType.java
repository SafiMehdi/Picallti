package data;


import java.io.Serializable;

public class VehiculeType implements Serializable {
    private int id;
    private String nom;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public VehiculeType(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    public VehiculeType(int id, String nom,String url) {
        this.id = id;
        this.nom = nom;
    }
    public VehiculeType(String nom) {
        this.nom = nom;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
