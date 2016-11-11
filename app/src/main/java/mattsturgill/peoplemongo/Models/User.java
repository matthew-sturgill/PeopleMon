package mattsturgill.peoplemongo.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by matthewsturgill on 11/7/16.
 */

public class User {

    @SerializedName("Email")
    private String email;

    @SerializedName("FullName")
    private String fullname;

    @SerializedName("Password")
    private String password;

    @SerializedName("token")
    private String token;

    @SerializedName(".expires")
    private Date expires;

    @SerializedName("ApiKey")
    private String apiKey = "iOSandroid301november2016";

    @SerializedName("grant_type")
    private String grantType;

    @SerializedName("AvatarBase64")
    private String avatar = "string";

    @SerializedName("Longitude")
    private Double longitude;

    @SerializedName("Latitude")
    private Double latitude;

    public User(){

    }

    public User(String email, String fullname, String avatar, String api, String password) {
        this.email = email;
        this.fullname = fullname;
        this.avatar = "string";
        this.apiKey = "iOSandroid301november2016";
        this.password = password;

    }

    public User(String email, String password, String grantType) {
        this.email = email;
        this.password = password;
        this.grantType = "password";

    }

    public User (Double latitude, Double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String username) {
        this.fullname = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpires() {return expires;}

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setApiKey(String apiKey) {this.apiKey = apiKey; }

    public Double getLongitude() {
        return longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {

        this.longitude = longitude;
    }

    public Double getLatitude() {

        return latitude;
    }
}