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
import com.example.musicplayer.databinding.ListRowAlbumBinding;
import com.example.musicplayer.view.activity.ListAlbumsActivity;
import com.example.musicplayer.view.activity.ListMusicsActivity;
import com.example.musicplayer.viewmodel.ListAlbumsArtistsViewModel;

public class ListAlbumsAdapter extends RecyclerView.Adapter<ListAlbumsAdapter.ListAlbumsHolder> {

    private ListAlbumsArtistsViewModel mListAlbumsArtistsViewModel;
    private LifecycleOwner mOwner;
    private Activity mActivity;

    public ListAlbumsAdapter(Activity activity, LifecycleOwner owner,
                             ListAlbumsArtistsViewModel listAlbumsArtistsViewModel) {
        mListAlbumsArtistsViewModel = listAlbumsArtistsViewModel;
        mOwner = owner;
        mActivity = activity;
    }

    @NonNull
    @Override
    public ListAlbumsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mListAlbumsArtistsViewModel.getApplication());
        ListRowAlbumBinding listRowAlbumBinding = DataBindingUtil.inflate(inflater,
                R.layout.list_row_album,
                parent,
                false);

        return new ListAlbumsHolder(listRowAlbumBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAlbumsHolder holder, int position) {
        holder.bindAlbum(position);

    }

    @Override
    public int getItemCount() {
        return mListAlbumsArtistsViewModel.getAlbumsNames().size();
    }


    class ListAlbumsHolder extends RecyclerView.ViewHolder {

        private ListRowAlbumBinding mRowAlbumBinding;

        public ListAlbumsHolder(ListRowAlbumBinding listRowAlbumBinding) {
            super(listRowAlbumBinding.getRoot());
            mRowAlbumBinding = listRowAlbumBinding;
            mRowAlbumBinding.setListAlbumsArtistsViewModel(mListAlbumsArtistsViewModel);
            mRowAlbumBinding.setLifecycleOwner(mOwner);
            mRowAlbumBinding.rowAlbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = mRowAlbumBinding.getPosition();

                    String nameAlbum = mListAlbumsArtistsViewModel.getAlbumsNames().get(position);
                    Intent intent = ListMusicsActivity.newIntent
                            (mActivity,"album", nameAlbum);
                    mActivity.startActivity(intent);

                }
            });


        }

        public void bindAlbum(int position) {
            mRowAlbumBinding.setPosition(position);
            mRowAlbumBinding.executePendingBindings();
        }
    }


}
