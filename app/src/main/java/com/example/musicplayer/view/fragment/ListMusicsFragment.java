package com.example.musicplayer.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicplayer.R;
import com.example.musicplayer.adapters.ListMusicsAdapter;
import com.example.musicplayer.data.entities.Music;
import com.example.musicplayer.databinding.FragmentListMusicsBinding;
import com.example.musicplayer.viewmodel.ListMusicsViewModel;

import java.util.ArrayList;
import java.util.List;


public class ListMusicsFragment extends Fragment {

    public static final String ARGS_NAME_TAB = "nameTab";
    public static final String ARGS_ALBUM_OR_ARTIST = "albumOrArtist";
    private String mNameTab;
    private String mAlbumOrArtist;
    private ListMusicsViewModel mListMusicsViewModel;
    private FragmentListMusicsBinding mBinding;
    private ListMusicsAdapter mMusicsAdapter;
    private List<Music> mMusicListArtist = new ArrayList<>();
    private List<Music> mMusicListAlbum = new ArrayList<>();
    private List<Music> mMusicList = new ArrayList<>();


    public ListMusicsFragment() {
        // Required empty public constructor
    }

    public static ListMusicsFragment newInstance
            (String nameTab, String nameAlbumOrArtist) {
        ListMusicsFragment fragment = new ListMusicsFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_NAME_TAB, nameTab);
        args.putString(ARGS_ALBUM_OR_ARTIST, nameAlbumOrArtist);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListMusicsViewModel = new ViewModelProvider
                (requireActivity()).get(ListMusicsViewModel.class);
        if (getArguments() != null) {
            mNameTab = getArguments().getString(ARGS_NAME_TAB);
            mAlbumOrArtist = getArguments().getString(ARGS_ALBUM_OR_ARTIST);
        }
        setMusicLists();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_list_musics,
                container,
                false);


        mBinding.recycleListMusic.setLayoutManager(new LinearLayoutManager(getActivity()));
        setMusicsAdapter();

        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    private void setMusicLists() {
        switch (mNameTab) {
            case "album":
                mMusicListAlbum = mListMusicsViewModel.getMusicsAlbumsByAlbumName(mAlbumOrArtist);
                break;
            case "artist":
                mMusicListArtist = mListMusicsViewModel.getMusicsArtistsByArtistName(mAlbumOrArtist);
                break;
            case "music":
                mMusicList = mListMusicsViewModel.getMusics();
                break;
        }
    }

    private void setMusicsAdapter() {
        switch (mNameTab) {
            case "album":
                updateUI(mMusicListAlbum);
                break;
            case "artist":
                updateUI(mMusicListArtist);
                break;
            case "music":
                if (mMusicList != null && mMusicList.size() != 0)
                    updateUI(mMusicList);
                break;
        }
    }

    private void updateUI(List<Music> musicList) {
        if (mMusicsAdapter == null) {
            mMusicsAdapter = new ListMusicsAdapter
                    (getActivity(), mListMusicsViewModel, musicList, mNameTab, mAlbumOrArtist);
            mListMusicsViewModel.setMusicsAdapter(mMusicsAdapter);
            mBinding.recycleListMusic.setAdapter(mMusicsAdapter);

        } else {
            mListMusicsViewModel.updateId(musicList);
        }
    }


}