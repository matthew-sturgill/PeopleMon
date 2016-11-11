package mattsturgill.peoplemongo.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by matthewsturgill on 11/10/16.
 */

public class CheckIn {

    @SerializedName("Longitude")
    private Double longitude;

    @SerializedName("Latitude")
    private Double latitude;

    public CheckIn(){

    }

    public CheckIn(Double longitude, double latitude){
        this.latitude = latitude;
        this.longitude = longitude;
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

    public Double getLongitude() {

        return longitude;
    }
}
