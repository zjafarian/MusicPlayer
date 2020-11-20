package com.example.musicplayer.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAlbumsAdapter extends RecyclerView.Adapter<ListAlbumsAdapter.ListAlbumsHolder> {


    @NonNull
    @Override
    public ListAlbumsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ListAlbumsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ListAlbumsHolder extends RecyclerView.ViewHolder{

        public ListAlbumsHolder(@NonNull View itemView) {
            super(itemView);
        }
    }



}
