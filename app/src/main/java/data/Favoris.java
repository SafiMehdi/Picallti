package data;


import java.time.LocalDateTime;

public class Favoris {
    private int id;
    private User user;
    private Offre offre;
    private LocalDateTime localDateTime;

    public Favoris(int id, User user, Offre offre,LocalDateTime LocalLocalDateTime) {
        this.id = id;
        this.user = user;
        this.offre = offre;
        this.localDateTime = LocalLocalDateTime;
    }
    public Favoris( User user, Offre offre,LocalDateTime LocalLocalDateTime) {
        this.user = user;
        this.offre = offre;
        this.localDateTime = LocalLocalDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime LocalDateTime) {
        this.localDateTime = LocalDateTime;
    }
}
