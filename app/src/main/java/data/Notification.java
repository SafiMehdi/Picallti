package data;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Notification {
    private int id;
    private String title;
    private String text;
    private int icon;
    private User user;
    private LocalTime time;
    private LocalDate localDate;


    public Notification(int id,String title, String text, int icon, User user, LocalTime time, LocalDate localDate) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.icon = icon;
        this.user = user;
        this.time = time;
        this.localDate = localDate;
    }

    public Notification(String title, LocalTime time, LocalDate localDate, User user) {
        this.title = title;
        this.localDate = localDate;
        this.time = time;
        this.user = user;

    }

    public Notification(String title, String text, LocalTime time,LocalDate date,User user) {
        this.title = title;
        this.text = text;
        this.time = time;
        this.localDate = date;
        this.user = user;

    }
    public Notification(String title, int icon, LocalTime time,LocalDate date,User user) {
        this.title = title;
        this.icon = icon;
        this.time = time;
        this.localDate = date;
        this.user = user;

    }

    public Notification(String title, String text, int icon, LocalTime time, LocalDate date,User user) {
        this.title = title;
        this.text = text;
        this.icon = icon;
        this.time = time;
        this.localDate = date;
        this.user = user;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
