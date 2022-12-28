package data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @Expose
    @SerializedName("email")
    private String Email;
    @Expose
    @SerializedName("password")
    private String Password;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public LoginRequest(String email, String password) {
        Email = email;
        Password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
