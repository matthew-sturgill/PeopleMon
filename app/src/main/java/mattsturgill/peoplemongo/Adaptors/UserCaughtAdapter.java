package mattsturgill.peoplemongo.Adaptors;

import android.content.Context;

import java.util.ArrayList;

import mattsturgill.peoplemongo.Models.OtherUsers;

/**
 * Created by matthewsturgill on 11/11/16.
 */

public class UserCaughtAdapter {

    public ArrayList<OtherUsers> otherUsersCaughtArray;
    private Context context;

    public UserCaughtAdapter(ArrayList<OtherUsers> otherUsersCaughtArrray, Context context) {
        this.otherUsersCaughtArray = otherUsersCaughtArrray;
        this.context = context;
    }
}
