package data;

public class Setting {
    Integer imageID;
    String settingName;


    public Setting(Integer imageID, String settingName) {
        this.imageID = imageID;
        this.settingName = settingName;
    }

    public Integer getImageID() {
        return imageID;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setImageID(Integer imageID) {
        this.imageID = imageID;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

}
