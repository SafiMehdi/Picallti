package data;

import java.sql.Date;
import java.sql.Time;

public class Notification {
    private String title;
    private String text;
    private int icon;
    private Date time;

    public Notification(String title, Date time) {
        this.title = title;
        this.time = time;
    }

    public Notification(String title, String text, Date time) {
        this.title = title;
        this.text = text;
        this.time = time;
    }
    public Notification(String title, int icon, Date time) {
        this.title = title;
        this.icon = icon;
        this.time = time;
    }

    public Notification(String title, String text, int icon, Date time) {
        this.title = title;
        this.text = text;
        this.icon = icon;
        this.time = time;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
