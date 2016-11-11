package mattsturgill.peoplemongo.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by matthewsturgill on 11/10/16.
 */

public class OtherUsers {

    @SerializedName("CaughtUserId")
    private String caughtUserId;

    @SerializedName("RadiusInMeters")
    private Double radiusInMeters;

    @SerializedName("UserId")
    private String otherUserId;

    @SerializedName("UserName")
    private String othersUsername;

    @SerializedName("AvatarBase64")
    private String otherUserAvatar;

    @SerializedName("Latitude")
    private Double nearByLatitude;

    @SerializedName("Longitude")
    private Double nearByLongitude;

    @SerializedName("Created")
    private String otherUserCreated;

    public OtherUsers(){

    }
    public OtherUsers(String caughtUserId, Double radiusInMeters){
        this.caughtUserId = caughtUserId;
        this.radiusInMeters = radiusInMeters;
    }

    public OtherUsers(String otherUserId, String othersUsername, String otherUserAvatar,
                      Double nearByLatitude, Double nearByLongitude, String otherUserCreated){
        this.otherUserId = otherUserId;
        this.othersUsername = othersUsername;
        this.otherUserAvatar = otherUserAvatar;
        this.nearByLatitude = nearByLatitude;
        this.nearByLongitude = nearByLongitude;
        this.otherUserCreated = otherUserCreated;

    }

    public void setRadiusInMeters(Double radiusInMeters) {
        this.radiusInMeters = radiusInMeters;
    }

    public void setCaughtUserId(String caughtUserId) {

        this.caughtUserId = caughtUserId;
    }

    public Double getRadiusInMeters() {

        return radiusInMeters;
    }

    public String getCaughtUserId() {

        return caughtUserId;
    }

    public String getOtherUserCreated() {
        return otherUserCreated;
    }

    public void setOtherUserCreated(String otherUserCreated) {
        this.otherUserCreated = otherUserCreated;
    }

    public Double getNearByLongitude() {

        return nearByLongitude;
    }

    public void setNearByLongitude(Double nearByLongitude) {
        this.nearByLongitude = nearByLongitude;
    }

    public Double getNearByLatitude() {

        return nearByLatitude;
    }

    public void setNearByLatitude(Double nearByLatitude) {
        this.nearByLatitude = nearByLatitude;
    }

    public String getOtherUserAvatar() {

        return otherUserAvatar;
    }

    public void setOtherUserAvatar(String otherUserAvatar) {
        this.otherUserAvatar = otherUserAvatar;
    }

    public String getOthersUsername() {

        return othersUsername;
    }

    public void setOthersUsername(String othersUsername) {
        this.othersUsername = othersUsername;
    }

    public String getOtherUserId() {

        return otherUserId;
    }

    public void setOtherUserId(String otherUserId) {
        this.otherUserId = otherUserId;
    }
}
