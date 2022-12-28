package data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Commentaire {
    private int id;
    private String commentaire;
    private User user;
    private Offre offre;
    private String time;
    private String LocalDateTime;


    public Commentaire(int id, String commentaire, User user, Offre offre,String LocalDateTime,String time) {
        this.id = id;
        this.commentaire = commentaire;
        this.user = user;
        this.offre = offre;
        this.LocalDateTime = LocalDateTime;
        this.time = time;
    }

    public Commentaire(String commentaire, User user, Offre offre,String LocalDateTime,String time) {
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

    public String getLocalDateTime() {
        return LocalDateTime;
    }

    public void setLocalDateTime(String LocalDateTime) {
        this.LocalDateTime = LocalDateTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
