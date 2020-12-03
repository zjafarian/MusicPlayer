package com.example.musicplayer.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.databinding.ListRowAlbumBinding;
import com.example.musicplayer.viewmodel.ListAlbumsViewModel;

public class ListAlbumsAdapter extends RecyclerView.Adapter<ListAlbumsAdapter.ListAlbumsHolder> {

    private ListAlbumsViewModel mListAlbumsViewModel;
    private LifecycleOwner mOwner;

    public ListAlbumsAdapter(LifecycleOwner owner,ListAlbumsViewModel listAlbumsViewModel) {
        mListAlbumsViewModel = listAlbumsViewModel;
        mOwner = owner;
    }

    @NonNull
    @Override
    public ListAlbumsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mListAlbumsViewModel.getApplication());
        ListRowAlbumBinding listRowAlbumBinding = DataBindingUtil.inflate(inflater,
                R.layout.list_row_album,
                parent,
                false);

        return new  ListAlbumsHolder(listRowAlbumBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAlbumsHolder holder, int position) {
        holder.bindAlbum(position);

    }

    @Override
    public int getItemCount() {
        return mListAlbumsViewModel.getMusicList().size();
    }





    class ListAlbumsHolder extends RecyclerView.ViewHolder{

        private ListRowAlbumBinding mRowAlbumBinding;

        public ListAlbumsHolder(ListRowAlbumBinding listRowAlbumBinding) {
            super(listRowAlbumBinding.getRoot());
            mRowAlbumBinding = listRowAlbumBinding;
            mRowAlbumBinding.setListAlbumsViewModel(mListAlbumsViewModel);
            mRowAlbumBinding.setLifecycleOwner(mOwner);

        }

        public void bindAlbum (int position){
            mRowAlbumBinding.setPosition(position);
            mRowAlbumBinding.executePendingBindings();
        }
    }



}
