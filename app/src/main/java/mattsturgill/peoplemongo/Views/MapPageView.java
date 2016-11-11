package mattsturgill.peoplemongo.Views;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.logging.Handler;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import flow.Flow;
import flow.History;
import mattsturgill.peoplemongo.MainActivity;
import mattsturgill.peoplemongo.PeopleMonGoApplication;
import mattsturgill.peoplemongo.R;
import mattsturgill.peoplemongo.Stages.PeopleMonListStage;

import static mattsturgill.peoplemongo.R.id.map;

/**
 * Created by matthewsturgill on 11/7/16.
 */

public class MapPageView extends RelativeLayout implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerClickListener {

    public Context context;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private GoogleMap mMap;
    private Handler handler;
    int STREET_LEVEL = 15;
    int BUILDING_LEVEL = 25;
    private LatLng locale;


    public MapPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Bind(R.id.view_caught)
    FloatingActionButton view_caught;

    @Bind(R.id.check_in_button)
    FloatingActionButton check_in_button;

    @Bind(R.id.menu_button)
    FloatingActionButton menu_button;

    @Bind(map)
    MapView mapView;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);

        mapView.onCreate(((MainActivity) getContext()).savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        googleApiClient.connect();

    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().isMyLocationButtonEnabled();
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        try {
            mMap.setMyLocationEnabled(true);
        } catch (SecurityException e){
            return;
        }
        mMap.clear();
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    //next
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                locale = new LatLng(location.getLatitude(), location.getLongitude());
                if (locale == null){
                    LatLng defaultLatLong = new LatLng(37.8145, -82.8071);
                    MarkerOptions options = new MarkerOptions().position(defaultLatLong).title("Test");
                    mMap.addMarker(options);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(defaultLatLong, 200.0f));
                    mMap.clear();
                } else {
                    MarkerOptions options = new MarkerOptions().position(locale).title("Test");
                    mMap.addMarker(options);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(locale, 20.0f));
                    mMap.clear();
                }
            }
        };
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
        flow.setHistory(newHistory, Flow.Direction.FORWARD);
    }
}
