package data;

public class Commentaire {
    private int id;
    private String commentaire;
    private User user;
    private Offre offre;

    public Commentaire(int id, String commentaire, User user, Offre offre) {
        this.id = id;
        this.commentaire = commentaire;
        this.user = user;
        this.offre = offre;
    }

    public Commentaire( String commentaire, User user, Offre offre) {
        this.commentaire = commentaire;
        this.user = user;
        this.offre = offre;
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
}
