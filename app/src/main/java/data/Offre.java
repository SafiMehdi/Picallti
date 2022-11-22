package data;

public class Offre {
    private int id;
    private String titre;
    private String description;
    private String operation;
    private double prix;
    private User owner;
    private Vehicule vehicule;
    private String url;

    public Offre(int id, String titre, String description, String operation, double prix, String url, User owner, Vehicule vehicule) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.operation = operation;
        this.prix = prix;
        this.owner = owner;
        this.vehicule = vehicule;
        this.url = url;
    }

    public Offre(String titre, String description, String operation, double prix, String url, User owner, Vehicule vehicule) {
        this.titre = titre;
        this.description = description;
        this.operation = operation;
        this.prix = prix;
        this.owner = owner;
        this.vehicule = vehicule;
        this.url = url;
    }
    public Offre(String titre, String operation, double prix, String url, User owner, Vehicule vehicule) {
        this.titre = titre;
        this.description = description;
        this.operation = operation;
        this.prix = prix;
        this.owner = owner;
        this.vehicule = vehicule;
        this.url = url;
    }
    public Offre(String titre, String description, double prix, String url ){
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
