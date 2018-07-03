package com.rostykboiko.teamvoy.sunspotting.main;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.rostykboiko.teamvoy.sunspotting.R;
import com.rostykboiko.teamvoy.sunspotting.utils.Locality;
import com.rostykboiko.teamvoy.sunspotting.utils.OneShotTask;
import com.rostykboiko.teamvoy.sunspotting.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks, SunDataCallback {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final int PERMISSIONS_REQUEST_FINE_LOCATION = 99;
    private static final int PLACE_PICKER_REQUEST = 1;

    private ArrayList<Locality> localities = new ArrayList<>();
    private RecyclerView localitiesRecyclerView;
    private LocalitiesAdapter localitiesAdapter;

    private Locality locality;
    private GoogleApiClient mGoogleApiClient;

    @BindView(R.id.sunrise_time)
    TextView sunriseTimeTV;
    @BindView(R.id.sunset_time)
    TextView sunsetTimeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        locality = new Locality();

        initRecyclerView();

        initGoogleApi();
        getCurrentLocation();
        initView();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK) {
            Place place = PlacePicker.getPlace(this, data);
            if (place == null) {
                Log.i(TAG, "No place selected");
                return;
            }
            locality = new Locality();
            locality.setTitle(place.getName().toString());
            locality.setLat(place.getLatLng().latitude);
            locality.setLng(place.getLatLng().longitude);
            getSunData(locality);
        }
    }

    private void getSunData(Locality locality) {
        Thread thread = new Thread(new OneShotTask(locality, this));
        thread.start();
    }

    public void updateUI() {
        if (locality != null && locality.getSunrise() != null) {

            localitiesAdapter.notifyDataSetChanged();

            System.out.println("Locality title updateUi: " + locality.getTitle());
            if (locality.getTitle().equals("Current")) {
                sunriseTimeTV.setText(Utils.formatTime(locality.getSunrise().getTime()));
                sunsetTimeTV.setText(Utils.formatTime(locality.getSunset().getTime()));
            }

        }
    }

    public void onAddPlaceButtonClicked(View view) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, getString(R.string.need_location_permission_message),
                    Toast.LENGTH_LONG).show();
            onLocationPermissionClicked(view);
            return;
        }
        try {
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            Intent i = builder.build(this);
            startActivityForResult(i, PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            Log.e(TAG, String.format("GooglePlayServices Not Available [%s]", e.getMessage()));
        } catch (Exception e) {
            Log.e(TAG, String.format("PlacePicker Exception: %s", e.getMessage()));
        }
    }

    public void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            LocationServices.getFusedLocationProviderClient(this)
                    .getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        locality = new Locality();
                        locality.setLat(location.getLatitude());
                        locality.setLng(location.getLongitude());
                        getSunData(locality);
                    }
                }
            });
        }
    }

    private void initGoogleApi() {
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(LocationServices.API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
    }

    private void initView() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddPlaceButtonClicked(view);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initRecyclerView() {
        localitiesAdapter = new LocalitiesAdapter(new LocalitiesAdapter.LocalitiesCallback() {
            @Override
            public void onCardDeleted(@NonNull Locality locality) {
                localities.remove(locality);
            }
        });

        localitiesRecyclerView = findViewById(R.id.locations_recycler);

        RecyclerView.LayoutManager mRowManager = new LinearLayoutManager(getApplicationContext());
        localitiesRecyclerView.setLayoutManager(mRowManager);
        localitiesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        localitiesRecyclerView.setAdapter(localitiesAdapter);
    }

    private void publishProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!locality.getTitle().equals("Current"))
                    localities.add(locality);
                for (Locality locality : localities) {
                    System.out.println("Locality title onUiThread: " + locality.getTitle() + " "
                            + locality.getSunrise());
                }

                localitiesAdapter.setLocalitiesList(localities);

                updateUI();
            }
        });
    }

    @Override
    public void sunInfo(@NonNull Locality locality) {
        this.locality = locality;
        publishProgress();
    }

    @OnClick(R.id.content_background)
    public void onBackgroundClick() {
        Log.e(TAG, "onBackgroundClick");
    }

    public void onLocationPermissionClicked(View view) {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSIONS_REQUEST_FINE_LOCATION);
        getCurrentLocation();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }
}

