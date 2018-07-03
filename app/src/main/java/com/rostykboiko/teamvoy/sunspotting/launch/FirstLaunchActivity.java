package com.rostykboiko.teamvoy.sunspotting.launch;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rostykboiko.teamvoy.sunspotting.R;
import com.rostykboiko.teamvoy.sunspotting.main.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class FirstLaunchActivity extends AppCompatActivity {
    public static final int PERMISSIONS_REQUEST_FINE_LOCATION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_launch);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.grant_permission_btn)
    public void onLocationPermissionClicked() {
        ActivityCompat.requestPermissions(FirstLaunchActivity.this,
                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSIONS_REQUEST_FINE_LOCATION);

    }

    @Override
    protected void onResume() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(this, MainActivity.class));
        }
        super.onResume();
    }
}
