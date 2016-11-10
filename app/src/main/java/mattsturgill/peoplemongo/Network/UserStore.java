package mattsturgill.peoplemongo.Network;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

import mattsturgill.peoplemongo.Constants.Constants;
import mattsturgill.peoplemongo.PeopleMonGoApplication;

import static mattsturgill.peoplemongo.Constants.Constants.token;

/**
 * Created by matthewsturgill on 11/7/16.
 */

public class UserStore {

    private static UserStore ourInstance = new UserStore();

    public static UserStore getInstance() {
        return ourInstance;
    }

    private SharedPreferences sharedPrefs = PeopleMonGoApplication.getInstance().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

    public String getToken(){
       String theToken = sharedPrefs.getString(token, null);
       return theToken;
    }

    public void setToken(String token) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(token, token);
        editor.apply();
    }

    public Date getTokenExpiration() {
        Long expiration = sharedPrefs.getLong(Constants.tokenExpiration, 0);
        Date date = new Date(expiration);
        if (date.before(new Date())) {
            return null;
        }
        return date;
    }

    public void setTokenExpiration (Date expiration){
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putLong(Constants.tokenExpiration,expiration.getTime());
        editor.apply();
    }
}
