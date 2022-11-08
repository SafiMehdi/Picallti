package data;

public class Notification {
    private String title;
    private String text;
    private int icon;

    public Notification(String title) {
        this.title = title;
    }

    public Notification(String title, String text) {
        this.title = title;
        this.text = text;
    }
    public Notification(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public Notification(String title, String text, int icon) {
        this.title = title;
        this.text = text;
        this.icon = icon;
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
}
