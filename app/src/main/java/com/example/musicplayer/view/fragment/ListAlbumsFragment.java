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
import com.example.musicplayer.adapters.ListAlbumsAdapter;
import com.example.musicplayer.data.room.entities.Music;
import com.example.musicplayer.databinding.FragmentListAlbumsBinding;
import com.example.musicplayer.viewmodel.ListAlbumsViewModel;

import java.util.List;


public class ListAlbumsFragment extends Fragment {
    private FragmentListAlbumsBinding mBinding;
    private ListAlbumsViewModel mListAlbumsViewModel;
    private ListAlbumsAdapter mAdapterAlbums;


    public ListAlbumsFragment() {
        // Required empty public constructor
    }


    public static ListAlbumsFragment newInstance() {
        ListAlbumsFragment fragment = new ListAlbumsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAlbumsViewModel = new ViewModelProvider
                (requireActivity()).get(ListAlbumsViewModel.class);


     /*   mListAlbumsViewModel.getMusicsLiveData().observe(this, new Observer<List<Music>>() {
            @Override
            public void onChanged(List<Music> musicList) {
                mListAlbumsViewModel.setMusicList(musicList);
                updateUI();
            }
        });*/




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_list_albums,
                container,
                false);

        mBinding.recycleViewAlbums.setLayoutManager(new LinearLayoutManager(getActivity()));



        return mBinding.getRoot();
    }

    public void updateUI() {
        if (mAdapterAlbums == null){
            mAdapterAlbums = new ListAlbumsAdapter(this, mListAlbumsViewModel);
            mBinding.recycleViewAlbums.setAdapter(mAdapterAlbums);
        } else {
            mAdapterAlbums.notifyDataSetChanged();
        }
    }
}