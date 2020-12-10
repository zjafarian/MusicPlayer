package com.example.musicplayer.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.data.entities.Music;
import com.example.musicplayer.databinding.ListRowMusicBinding;
import com.example.musicplayer.view.activity.MusicActivity;
import com.example.musicplayer.viewmodel.ListMusicsViewModel;

import java.util.List;

public class ListMusicsAdapter extends RecyclerView.Adapter<ListMusicsAdapter.ListMusicsHolder> {
    private ListMusicsViewModel mListMusicsViewModel;
    private Activity mActivity;
    private List<Music> mMusicList;
    private String mNameTab;
    private String mNameArtistOrAlbum;


    public ListMusicsAdapter(Activity activity, ListMusicsViewModel listMusicsViewModel,
                             List<Music> musicList, String nameTab, String nameArtistOrAlbum) {

        mListMusicsViewModel = listMusicsViewModel;
        mMusicList = musicList;
        mNameTab = nameTab;
        mNameArtistOrAlbum = nameArtistOrAlbum;
        mActivity = activity;
    }

    @NonNull
    @Override
    public ListMusicsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mListMusicsViewModel.getApplication());
        ListRowMusicBinding listRowMusicBinding = DataBindingUtil.inflate(inflater,
                R.layout.list_row_music,
                parent,
                false);
        return new ListMusicsHolder(listRowMusicBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMusicsHolder holder, int position) {
        holder.bindMusic(position);

    }

    @Override
    public int getItemCount() {
        return mMusicList.size();
    }

    class ListMusicsHolder extends RecyclerView.ViewHolder {
        ListRowMusicBinding mRowMusicBinding;
        int mPosition;

        public ListMusicsHolder(ListRowMusicBinding listRowMusicBinding) {
            super(listRowMusicBinding.getRoot());
            mRowMusicBinding = listRowMusicBinding;
            mRowMusicBinding.rowMusic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long idMusic = mMusicList.get(mPosition).getIdMusic();
                    Intent intent = MusicActivity.newIntent(mActivity, idMusic, mNameTab, mNameArtistOrAlbum);
                    mActivity.startActivity(intent);
                }
            });

        }

        public void bindMusic(int position) {
            mPosition = position;
            mRowMusicBinding.textViewMusic.setText(mMusicList.get(position).getMusicTitle());
        }
    }
}
