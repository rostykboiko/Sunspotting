package com.rostykboiko.teamvoy.sunspotting.main;

import android.support.annotation.NonNull;

import com.rostykboiko.teamvoy.sunspotting.utils.Locality;

public interface SunDataCallback {
    void sunInfo(@NonNull Locality locality);

}
