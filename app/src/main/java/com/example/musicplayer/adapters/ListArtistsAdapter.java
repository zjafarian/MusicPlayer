package com.example.musicplayer.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.databinding.ListRowArtistBinding;
import com.example.musicplayer.viewmodel.ListAlbumsViewModel;

public class ListArtistsAdapter extends RecyclerView.Adapter<ListArtistsAdapter.ListArtistsHolder> {
    private ListAlbumsViewModel mListAlbumsViewModel;
    private LifecycleOwner mOwner;


    public ListArtistsAdapter( LifecycleOwner owner,ListAlbumsViewModel listAlbumsViewModel) {
        mListAlbumsViewModel = listAlbumsViewModel;
        mOwner = owner;
    }

    @NonNull
    @Override
    public ListArtistsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mListAlbumsViewModel.getApplication());
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
        return mListAlbumsViewModel.getMusicList().size();
    }

    class ListArtistsHolder extends RecyclerView.ViewHolder{

        private ListRowArtistBinding mRowArtistBinding;

        public ListArtistsHolder(ListRowArtistBinding listRowArtistBinding) {
            super(listRowArtistBinding.getRoot());
            mRowArtistBinding = listRowArtistBinding;
            mRowArtistBinding.setListAlbumsViewModel(mListAlbumsViewModel);
            mRowArtistBinding.setLifecycleOwner(mOwner);
        }

        public void bindArtist (int position){
            mRowArtistBinding.setPosition(position);
            mRowArtistBinding.executePendingBindings();
        }



    }
}
