package mattsturgill.peoplemongo.Views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import flow.Flow;
import flow.History;
import mattsturgill.peoplemongo.MainActivity;
import mattsturgill.peoplemongo.PeopleMonGoApplication;
import mattsturgill.peoplemongo.R;
import mattsturgill.peoplemongo.Stages.PeopleMonListStage;

/**
 * Created by matthewsturgill on 11/7/16.
 */

public class MapPageView extends RelativeLayout implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerClickListener {

    public Context context;
    public MapView mapView;

    @Bind(R.id.view_caught)
    FloatingActionButton view_caught;

    @Bind(R.id.check_in_button)
    FloatingActionButton check_in_button;

    @Bind(R.id.menu_button)
    FloatingActionButton menu_button;


    public MapPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        ButterKnife.bind(this);
        super.onFinishInflate();
        mapView.onCreate(((MainActivity) getContext()).savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

    }

    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @OnClick(R.id.check_in_button)
    public void checkIn(){

    }

    @OnClick(R.id.menu_button)
    public void menu(){

    }

    @OnClick(R.id.view_caught)
    public void viewCaught(){
        Flow flow = PeopleMonGoApplication.getMainFlow();
        History newHistory = History.single(new PeopleMonListStage());
        flow.setHistory(newHistory, Flow.Direction.REPLACE);
    }
}
