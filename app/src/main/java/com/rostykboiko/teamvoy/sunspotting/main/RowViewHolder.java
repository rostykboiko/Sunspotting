package com.rostykboiko.teamvoy.sunspotting.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rostykboiko.teamvoy.sunspotting.R;
import com.rostykboiko.teamvoy.sunspotting.utils.Locality;

public class RowViewHolder extends RecyclerView.ViewHolder{

    private Context context;
    private TextView locationTitle;
    private TextView sunRiseTime;
    private TextView sunSetTime;

    private QuestionCardCallback callback;

    RowViewHolder(final View view, @NonNull Context context, @NonNull QuestionCardCallback callback) {
        super(view);
        this.context = context;
        this.callback = callback;

        locationTitle = view.findViewById(R.id.title);
        sunRiseTime = view.findViewById(R.id.sunrise_time);
        sunSetTime = view.findViewById(R.id.sunset_time);

    }

    public void setLocation(@NonNull Locality locality){
        locationTitle.setText(locality.getTitle());
        sunRiseTime.setText(locality.getSunRise());
        sunSetTime.setText(locality.getSunSet());
    }

    interface QuestionCardCallback {
        void onDeleteCard(int position);

        void onEditClick(int position);

    }

}
