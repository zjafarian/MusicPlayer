package com.example.musicplayer.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.databinding.ListRowArtistBinding;
import com.example.musicplayer.view.activity.ListMusicsActivity;
import com.example.musicplayer.viewmodel.ListAlbumsArtistsViewModel;

public class ListArtistsAdapter extends RecyclerView.Adapter<ListArtistsAdapter.ListArtistsHolder> {
    private ListAlbumsArtistsViewModel mListAlbumsArtistsViewModel;
    private LifecycleOwner mOwner;
    private Activity mActivity;


    public ListArtistsAdapter(Activity activity,LifecycleOwner owner,
                              ListAlbumsArtistsViewModel listAlbumsArtistsViewModel) {
        mListAlbumsArtistsViewModel = listAlbumsArtistsViewModel;
        mOwner = owner;
        mActivity = activity;
    }

    @NonNull
    @Override
    public ListArtistsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mListAlbumsArtistsViewModel.getApplication());
        ListRowArtistBinding listRowArtistBinding = DataBindingUtil.inflate(inflater,
                R.layout.list_row_artist,
                parent,
                false);

        return new  ListArtistsHolder(listRowArtistBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListArtistsHolder holder, int position) {
        holder.bindArtist(position);

    }

    @Override
    public int getItemCount() {
        return mListAlbumsArtistsViewModel.getArtistsNames().size();
    }

    class ListArtistsHolder extends RecyclerView.ViewHolder{

        private ListRowArtistBinding mRowArtistBinding;

        public ListArtistsHolder(ListRowArtistBinding listRowArtistBinding) {
            super(listRowArtistBinding.getRoot());
            mRowArtistBinding = listRowArtistBinding;
            mRowArtistBinding.setListAlbumsArtistsViewModel(mListAlbumsArtistsViewModel);
            mRowArtistBinding.setNameTab("artist");
            mRowArtistBinding.setLifecycleOwner(mOwner);
            mRowArtistBinding.rowArtist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = mRowArtistBinding.getPosition();
                    String nameArtist = mListAlbumsArtistsViewModel.getArtistsNames().get(position);

                    Intent intentArtist = ListMusicsActivity.newIntent
                            (mActivity,"artist",nameArtist);
                    mActivity.startActivity(intentArtist);

                }
            });
        }

        public void bindArtist (int position){
            mRowArtistBinding.setPosition(position);
            mRowArtistBinding.executePendingBindings();
        }



    }
}
