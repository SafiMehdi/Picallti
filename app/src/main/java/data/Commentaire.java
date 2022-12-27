package data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Commentaire {
    private int id;
    private String commentaire;
    private User user;
    private Offre offre;
    private LocalTime time;
    private LocalDate LocalDateTime;


    public Commentaire(int id, String commentaire, User user, Offre offre,LocalDate LocalDateTime,LocalTime time) {
        this.id = id;
        this.commentaire = commentaire;
        this.user = user;
        this.offre = offre;
        this.LocalDateTime = LocalDateTime;
        this.time = time;
    }

    public Commentaire(String commentaire, User user, Offre offre,LocalDate LocalDateTime,LocalTime time) {
        this.commentaire = commentaire;
        this.user = user;
        this.offre = offre;
        this.LocalDateTime = LocalDateTime;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public LocalDate getLocalDateTime() {
        return LocalDateTime;
    }

    public void setLocalDateTime(LocalDate LocalDateTime) {
        this.LocalDateTime = LocalDateTime;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
