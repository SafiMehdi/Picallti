package data;

import android.widget.EditText;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private String genre;
    private String email;
    private int phone;
    private String password;
    private int photo;
    private String bio;
    private String role;


    public User(int id, String nom, String prenom, String genre, String email, int phone, String password, int photo, String bio, String role) {
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
    public User(String nom, String prenom, String genre, String email, int phone, String password, int photo, String bio, String role) {
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

    public User(String nom, String prenom, String genre, String email, int phone, String password, int photo, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.photo = photo;
        this.role = role;
    }
    public User(String nom, String prenom, String email, int phone, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
    public User(String nom, String prenom, String email, int phone, String password, int photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.photo = photo;
    }

   


    public User(EditText surname, EditText name, EditText email, EditText phoneNumber, EditText password) {
    }

    public User(EditText surname, EditText name, EditText email, EditText phoneNumber, EditText bio, int image) {
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
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
