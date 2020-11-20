package com.example.musicplayer.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListArtistsAdapter extends RecyclerView.Adapter<ListArtistsAdapter.ListArtistsHolder> {


    @NonNull
    @Override
    public ListArtistsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ListArtistsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ListArtistsHolder extends RecyclerView.ViewHolder{
        public ListArtistsHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
