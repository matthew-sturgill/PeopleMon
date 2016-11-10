package mattsturgill.peoplemongo.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by matthewsturgill on 11/10/16.
 */

public class UserInfo {

    @SerializedName("Id")
    private String userID;

    @SerializedName("Email")
    private String userEmail;

    @SerializedName("HasRegistered")
    private Boolean userHasRegistered;

    @SerializedName("LoginProvider")
    private String userLoginProvider = "string";

    @SerializedName("FullName")
    private String userFullName;

    @SerializedName("AvatarBase64")
    private String userAvatarBase;

    @SerializedName("LastCheckInLongitude")
    private Double userLastCheckInLongitude;

    @SerializedName("LastCheckInLatitude")
    private Double userLastCheckInLatitude;

    @SerializedName("LastCheckInDateTime")
    private String userLastCheckInDateTime;

    public UserInfo(){

    }

    public UserInfo (String userID, String userEmail, Boolean userHasRegistered, String userLoginProvider,
                     String userFullName, String userAvatarBase, Double userLastCheckInLongitude,
                     Double userLastCheckInLatitude, String userLastCheckInDateTime){
        this.userID = userID;
        this.userEmail = userEmail;
        this.userHasRegistered = userHasRegistered;
        this.userLoginProvider = userLoginProvider;
        this.userFullName = userFullName;
        this.userAvatarBase = userAvatarBase;
        this.userLastCheckInLongitude = userLastCheckInLongitude;
        this.userLastCheckInLatitude = userLastCheckInLatitude;
        this.userLastCheckInDateTime = userLastCheckInDateTime;
    }

    public void setUserLastCheckInDateTime(String userLastCheckInDateTime) {
        this.userLastCheckInDateTime = userLastCheckInDateTime;
    }

    public void setUserLastCheckInLatitude(Double userLastCheckInLatitude) {

        this.userLastCheckInLatitude = userLastCheckInLatitude;
    }

    public void setUserLastCheckInLongitude(Double userLastCheckInLongitude) {

        this.userLastCheckInLongitude = userLastCheckInLongitude;
    }

    public void setUserAvatarBase(String userAvatarBase) {

        this.userAvatarBase = userAvatarBase;
    }

    public void setUserFullName(String userFullName) {

        this.userFullName = userFullName;
    }

    public void setUserLoginProvider(String userLoginProvider) {

        this.userLoginProvider = userLoginProvider;
    }

    public void setUserHasRegistered(Boolean userHasRegistered) {

        this.userHasRegistered = userHasRegistered;
    }

    public void setUserEmail(String userEmail) {

        this.userEmail = userEmail;
    }

    public void setUserID(String userID) {

        this.userID = userID;
    }

    public String getUserLastCheckInDateTime() {

        return userLastCheckInDateTime;
    }

    public Double getUserLastCheckInLatitude() {

        return userLastCheckInLatitude;
    }

    public Double getUserLastCheckInLongitude() {

        return userLastCheckInLongitude;
    }

    public String getUserAvatarBase() {

        return userAvatarBase;
    }

    public String getUserFullName() {

        return userFullName;
    }

    public String getUserLoginProvider() {

        return userLoginProvider;
    }

    public Boolean getUserHasRegistered() {

        return userHasRegistered;
    }

    public String getUserEmail() {

        return userEmail;
    }

    public String getUserID() {

        return userID;
    }
}
