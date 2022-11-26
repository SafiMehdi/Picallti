package data;

public class Vehicule {
    private int id;
    private String nom;
    private String marque;
    private String description;
    private VehiculeType vehiculeType;



    public Vehicule(int id, String nom, String marque, String description,VehiculeType vehiculeType) {
        this.id = id;
        this.nom = nom;
        this.marque = marque;
        this.description = description;
        this.vehiculeType = vehiculeType;
    }
    public Vehicule(String nom, String marque, String description,VehiculeType vehiculeType) {
        this.nom = nom;
        this.marque = marque;
        this.description = description;
        this.vehiculeType = vehiculeType;
    }
    public Vehicule(String nom, String marque) {
        this.nom = nom;
        this.marque = marque;
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

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VehiculeType getVehiculeType() {
        return vehiculeType;
    }

    public void setVehiculeType(VehiculeType vehiculeType) {
        this.vehiculeType = vehiculeType;
    }
}
