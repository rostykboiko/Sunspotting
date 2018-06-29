package com.rostykboiko.teamvoy.sunspotting.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rostykboiko.teamvoy.sunspotting.R;
import com.rostykboiko.teamvoy.sunspotting.utils.Locality;

import java.util.ArrayList;

class LocalitiesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @NonNull
    private QuestionsCardCallback callback;

    private ArrayList<Locality> locationsList = new ArrayList<>();

    LocalitiesAdapter(@NonNull QuestionsCardCallback callback) {
        this.callback = callback;
    }

    @Override
    public RowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.places_row, parent, false);

        return new RowViewHolder(itemView, parent.getContext(), new RowViewHolder.QuestionCardCallback() {
            @Override
            public void onDeleteCard(int position) {
                callback.onCardDeleted(locationsList.get(position));
                locationsList.remove(position);
                notifyDataSetChanged();
            }

            @Override
            public void onEditClick(int position) {
                callback.onEditClick(locationsList.get(position));
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Locality locality = locationsList.get(position);
        RowViewHolder viewHolder = (RowViewHolder) holder;
        viewHolder.setLocation(locality);

    }

    @Override
    public int getItemCount() {
        return locationsList.size();
    }

    interface QuestionsCardCallback {
        void onCardDeleted(@NonNull Locality locality);

        void onEditClick(@NonNull Locality locality);
    }
}