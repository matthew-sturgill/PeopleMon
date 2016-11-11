package mattsturgill.peoplemongo.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by matthewsturgill on 11/11/16.
 */

public class UsersCaught {

    @SerializedName("Created")
    private String otherUserCreated;

    @SerializedName("AvatarBase64")
    private String otherUserAvatar;

    @SerializedName("UserName")
    private String othersUsername;


    @SerializedName("UserId")
    private String otherUserId;

    public UsersCaught(){

    }

    public UsersCaught (String otherUserId, String othersUsername,
                      String otherUserCreated, String otherUserAvatar){
        this.otherUserId = otherUserId;
        this.othersUsername = othersUsername;
        this.otherUserCreated = otherUserCreated;
        this.otherUserAvatar = otherUserAvatar;
    }

    public String getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(String otherUserId) {
        this.otherUserId = otherUserId;
    }

    public String getOthersUsername() {

        return othersUsername;
    }

    public void setOthersUsername(String othersUsername) {
        this.othersUsername = othersUsername;
    }

    public String getOtherUserAvatar() {

        return otherUserAvatar;
    }

    public void setOtherUserAvatar(String otherUserAvatar) {
        this.otherUserAvatar = otherUserAvatar;
    }

    public String getOtherUserCreated() {

        return otherUserCreated;
    }

    public void setOtherUserCreated(String otherUserCreated) {
        this.otherUserCreated = otherUserCreated;
    }
}
