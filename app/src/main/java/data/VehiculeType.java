package data;

import androidx.media.AudioAttributesCompat;

public class VehiculeType {
    private int id;
    private String nom;
    private String url;

    public VehiculeType(int id, String nom ,String url) {
        this.id = id;
        this.nom = nom;
        this.url = url;
    }

    public VehiculeType(String nom, String url) {
        this.nom = nom;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
