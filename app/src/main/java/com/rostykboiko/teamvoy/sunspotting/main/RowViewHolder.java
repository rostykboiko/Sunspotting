package com.rostykboiko.teamvoy.sunspotting.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rostykboiko.teamvoy.sunspotting.R;
import com.rostykboiko.teamvoy.sunspotting.utils.Locality;
import com.rostykboiko.teamvoy.sunspotting.utils.Utils;

public class RowViewHolder extends RecyclerView.ViewHolder{

    private Context context;
    private TextView locationTitle;
    private TextView sunRiseTime;
    private TextView sunSetTime;

    private LocalitiesCallback callback;

    RowViewHolder(final View view, @NonNull Context context, @NonNull LocalitiesCallback callback) {
        super(view);
        this.context = context;
        this.callback = callback;

        locationTitle = view.findViewById(R.id.title);
        sunRiseTime = view.findViewById(R.id.sunrise_time);
        sunSetTime = view.findViewById(R.id.sunset_time);

    }

    public void setLocation(@NonNull Locality locality){
        locationTitle.setText(locality.getTitle());
        sunRiseTime.setText(Utils.formatTime(locality.getSunrise().getTime()));
        sunSetTime.setText(Utils.formatTime(locality.getSunset().getTime()));
    }

    interface LocalitiesCallback {
        void onDeleteCard(int position);
    }
}
