package mattsturgill.peoplemongo;

import android.app.Application;

import flow.Flow;
import flow.History;
import mattsturgill.peoplemongo.Stages.MapStage;

/**
 * Created by matthewsturgill on 11/7/16.
 */

public class PeopleMonGoApplication extends Application{
    private static PeopleMonGoApplication application;
    public final Flow mainFlow =
            new Flow(History.single(new MapStage()));

    public static final String API_BASE_URL = "https://efa-peoplemon-api.azurewebsites.net:443";

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;
    }

    public static PeopleMonGoApplication getInstance(){
        return application;
    }

    public static Flow getMainFlow(){
        return getInstance().mainFlow;
    }


}

