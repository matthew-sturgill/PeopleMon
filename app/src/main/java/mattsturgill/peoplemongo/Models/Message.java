package mattsturgill.peoplemongo.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by matthewsturgill on 11/11/16.
 */

public class Message {


    @SerializedName("RecipientId")
    private String recipientId;

    @SerializedName("Message")
    private String message;

    public Message(){

    }

    public Message(String recipientId, String message ){
        this.recipientId = recipientId;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRecipientId(String recipientId) {

        this.recipientId = recipientId;
    }

    public String getMessage() {

        return message;
    }

    public String getRecipientId() {

        return recipientId;
    }
}
