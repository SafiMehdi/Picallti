package com.example.picallti;

import data.User;

public class Offre {

    private Integer id;
    private Integer imageId;
    private String titre;
    private String description;
    private String localisation;
    private float prix;
    private String time;
    private String operation;
    private User user;

    public Offre(Integer id, Integer imageId, String titre, String description, String localisation, float prix, String time, String operation, User user) {
        this.id = id;
        this.imageId = imageId;
        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.prix = prix;
        this.time = time;
        this.operation = operation;
        this.user = user;
    }
    public Offre( Integer imageId, String titre, String description, String localisation, float prix, String time, String operation, User user) {
        this.id = id;
        this.imageId = imageId;
        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.prix = prix;
        this.time = time;
        this.operation = operation;
        this.user = user;
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

    public float getPrix() {
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

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
