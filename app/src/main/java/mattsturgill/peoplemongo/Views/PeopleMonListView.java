package mattsturgill.peoplemongo.Views;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import flow.Flow;
import flow.History;
import mattsturgill.peoplemongo.Network.RestClient;
import mattsturgill.peoplemongo.PeopleMonGoApplication;
import mattsturgill.peoplemongo.R;
import mattsturgill.peoplemongo.Stages.MapStage;

/**
 * Created by matthewsturgill on 11/7/16.
 */

public class PeopleMonListView extends RelativeLayout {
    private Context context;
    private RestClient restClient;

    @Bind(R.id.recycler)
    RecyclerView recyclerView;

    @Bind(R.id.go_to_map_button)
    FloatingActionButton goToMapButton;

    @Bind(R.id.PeopleMon_name)
    TextView peopleMonName;

    @Bind(R.id.Date_caught)
    TextView dateCaught;

    public PeopleMonListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);

        restClient = new RestClient();

        //Need to find a way to load list of users caught.
    }

    @OnClick(R.id.go_to_map_button)
    public void showMapView() {
        Flow flow = PeopleMonGoApplication.getMainFlow();
        History newHistory = flow.getHistory().buildUpon()
                .push(new MapStage())
                .build();
        flow.setHistory(newHistory, Flow.Direction.BACKWARD);
    }
}

