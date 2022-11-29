package data;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private String genre;
    private String email;
    private long phone;
    private String password;
    private String photo;
    private String bio;
    private String role;


    public User(int id, String nom, String prenom, String genre, String email, long phone, String password, String photo, String bio, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.photo = photo;
        this.bio = bio;
        this.role = role;
    }
    public User(String nom, String prenom, String genre, String email, long phone, String password, String photo, String bio, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.photo = photo;
        this.bio = bio;
        this.role = role;
    }

    public User(String nom, String prenom, String genre, String email, long phone, String password, String photo, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.photo = photo;
        this.role = role;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
