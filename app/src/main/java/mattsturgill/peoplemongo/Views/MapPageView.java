package mattsturgill.peoplemongo.Views;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.Toast;

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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import flow.Flow;
import flow.History;
import mattsturgill.peoplemongo.MainActivity;
import mattsturgill.peoplemongo.Models.OtherUsers;
import mattsturgill.peoplemongo.Models.User;
import mattsturgill.peoplemongo.Network.RestClient;
import mattsturgill.peoplemongo.PeopleMonGoApplication;
import mattsturgill.peoplemongo.R;
import mattsturgill.peoplemongo.Stages.EditProfileStage;
import mattsturgill.peoplemongo.Stages.PeopleMonListStage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private Handler checkNear;
    private Handler checkIn;
    private Double latitude;
    private Double longitude;
    private String otherUserId;
    private String othersUsername;
    private String otherUserAvatar;
    private Double nearByLatitude;
    private Double nearByLongitude;
    private String otherUserCreated;

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
        } catch (SecurityException e) {
            return;
        }
        mMap.clear();
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
      try {
          Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
          locale = new LatLng(location.getLatitude(), location.getLongitude());
          if (locale == null) {
              LatLng defaultLatLong = new LatLng(37.8145, -82.8071);
              MarkerOptions options = new MarkerOptions().position(defaultLatLong).title("Test");
              mMap.addMarker(options);
              mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(defaultLatLong, 200.0f));
              mMap.clear();
          } else {
              MarkerOptions options = new MarkerOptions().position(locale).title("Test");
              mMap.addMarker(options);
              mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(locale, 200.0f));
              mMap.clear();
          }

          checkNear = new Handler() {
              final Runnable r = new Runnable() {
                  public void run() {
                      checkNearby();
                      checkNear.postDelayed(this, 2000);
                  }
              };
          };

          checkIn = new Handler() {
              final Runnable yx = new Runnable() {
                  public void run() {
                      checkingIn();
                      checkIn.postDelayed(this, 2000);
                  }
              };
          };
      } catch (IllegalArgumentException x){
          return;
      }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(context, "button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    public void checkingIn() {
        User user = new User(latitude, longitude);
        RestClient restClient = new RestClient();
        restClient.getApiService().checkIn(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Check in successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Meow in on response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, "On failure message", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkNearby(){
        OtherUsers otherUsers = new OtherUsers (otherUserId, othersUsername, otherUserAvatar,
                nearByLatitude, nearByLongitude, otherUserCreated);
        RestClient restClient = new RestClient();
        restClient.getApiService().userNearbyQuery(otherUsers).enqueue(new Callback<OtherUsers>() {
            @Override
            public void onResponse(Call<OtherUsers> call, Response<OtherUsers> response) {


            }

            @Override
            public void onFailure(Call<OtherUsers> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.view_caught)
    public void viewCaught() {
        Flow flow = PeopleMonGoApplication.getMainFlow();
        History newHistory = History.single(new PeopleMonListStage());
        flow.setHistory(newHistory, Flow.Direction.FORWARD);
    }

    @OnClick(R.id.menu_button)
    public void menu() {
        Flow flow = PeopleMonGoApplication.getMainFlow();
        History newHistory = History.single(new EditProfileStage());
        flow.setHistory(newHistory, Flow.Direction.FORWARD);
    }

    @OnClick(R.id.check_in_button)
    public void checkIn() {
        User user = new User(latitude, longitude);
        RestClient restClient = new RestClient();
        restClient.getApiService().checkIn(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Check in successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Meow in on response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, "On failure message", Toast.LENGTH_SHORT).show();
            }
        });
    }
};

