package mattsturgill.peoplemongo;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import android.Manifest;
import com.davidstemmer.flow.plugin.screenplay.ScreenplayDispatcher;

import butterknife.Bind;
import butterknife.ButterKnife;
import flow.Flow;
import flow.History;
import mattsturgill.peoplemongo.Network.UserStore;
import mattsturgill.peoplemongo.Stages.LoginStage;
import mattsturgill.peoplemongo.Stages.MapStage;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private Flow flow;
    private ScreenplayDispatcher dispatcher;
    public Bundle savedInstanceState;

    @Bind(R.id.container)
    RelativeLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.savedInstanceState = savedInstanceState;

        ButterKnife.bind(this);

        flow = PeopleMonGoApplication.getMainFlow();
        dispatcher = new ScreenplayDispatcher(this, container);
        dispatcher.setUp(flow);


        if (UserStore.getInstance().getToken() == null ||
                UserStore.getInstance().getTokenExpiration() == null) {
            History newhistory = History.single(new LoginStage());
            flow.setHistory(newhistory, Flow.Direction.REPLACE);
        }

        //COMMENT OUT THIS FILE!
        if (Build.VERSION.SDK_INT >= 23) {
            if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            if (!(ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {

            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!flow.goBack()) {
            flow.removeDispatcher(dispatcher);
            flow.setHistory(History.single(new MapStage() {
                    }),
                    Flow.Direction.BACKWARD);
            super.onBackPressed();
        }
    }
}

