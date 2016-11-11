package mattsturgill.peoplemongo.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by matthewsturgill on 11/10/16.
 */

public class CatchUser {

    @SerializedName("CaughtUserId")
    private String caughtUserId;

    @SerializedName("RadiusInMeters")
    private Double radiusInMeters;

    public CatchUser(){

    }
    public CatchUser (String caughtUserId, Double radiusInMeters){
        this.caughtUserId = caughtUserId;
        this.radiusInMeters = radiusInMeters;
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
}
