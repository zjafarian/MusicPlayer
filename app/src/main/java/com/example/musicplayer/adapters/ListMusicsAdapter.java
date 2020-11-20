package com.example.musicplayer.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListMusicsAdapter extends RecyclerView.Adapter<ListMusicsAdapter.ListMusicsHolder> {


    @NonNull
    @Override
    public ListMusicsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ListMusicsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ListMusicsHolder extends RecyclerView.ViewHolder{

        public ListMusicsHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
