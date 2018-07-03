package com.rostykboiko.teamvoy.sunspotting.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rostykboiko.teamvoy.sunspotting.R;
import com.rostykboiko.teamvoy.sunspotting.utils.Locality;

import java.util.ArrayList;
import java.util.List;

class LocalitiesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @NonNull
    private LocalitiesCallback callback;

    private List<Locality> locationsList = new ArrayList<>();

    LocalitiesAdapter(@NonNull LocalitiesCallback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public RowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_location, parent, false);

        return new RowViewHolder(itemView, parent.getContext(), new RowViewHolder.LocalitiesCallback() {
            @Override
            public void onDeleteCard(int position) {
                callback.onCardDeleted(locationsList.get(position));
                locationsList.remove(position);
                notifyDataSetChanged();
            }
            });
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        Locality locality = locationsList.get(position);
        RowViewHolder viewHolder = (RowViewHolder) holder;
        viewHolder.setLocation(locality);
        System.out.println("Locality: " + locality.getTitle() + " " + locality.getSunrise());
    }

    void setLocalitiesList(@NonNull List<Locality> localitiesList) {
        this.locationsList = localitiesList;
    }

    @Override
    public int getItemCount() {
        return locationsList.size();
    }

    interface LocalitiesCallback {
        void onCardDeleted(@NonNull Locality locality);
    }
}