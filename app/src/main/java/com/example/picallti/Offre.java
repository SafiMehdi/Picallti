package com.example.picallti;

public class Offre {

    Integer id;
    Integer imageId;
    String titre;
    String description;
    String localisation;
    String prix;
    String time;

    public Offre(Integer imageId, String titre, String description, String localisation, String prix, String time) {
        this.imageId = imageId;
        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.prix = prix;
        this.time = time;
    }

    public Offre(String titre, Integer imageId, String description, String localisation, String prix, String time) {
        this.titre = titre;
        this.imageId = imageId;
        this.description = description;
        this.localisation = localisation;
        this.prix = prix;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public Integer getImageId() {
        return imageId;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getLocalisation() {
        return localisation;
    }

    public String getPrix() {
        return prix;
    }

    public String getTime() {
        return time;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Offre{" +
                "id=" + id +
                ", imageId=" + imageId +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", localisation='" + localisation + '\'' +
                ", prix='" + prix + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
