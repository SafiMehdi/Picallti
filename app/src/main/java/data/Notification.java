package data;

import java.sql.Date;
import java.sql.Time;

public class Notification {
    private String title;
    private String text;
    private int icon;
    private Date time;
    private String url;

    public Notification(String title, Date time, String url) {
        this.title = title;
        this.time = time;
        this.url = url;
    }

    public Notification(String title, String text, Date time, String url) {
        this.title = title;
        this.text = text;
        this.time = time;
        this.url = url;
    }
    public Notification(String title, int icon, Date time, String url) {
        this.title = title;
        this.icon = icon;
        this.time = time;
        this.url = url;
    }

    public Notification(String title, String text, int icon, Date time, String url) {
        this.title = title;
        this.text = text;
        this.icon = icon;
        this.time = time;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
