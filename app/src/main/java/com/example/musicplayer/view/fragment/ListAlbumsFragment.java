package com.example.musicplayer.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicplayer.R;
import com.example.musicplayer.adapters.ListAlbumsAdapter;
import com.example.musicplayer.databinding.FragmentListAlbumsBinding;
import com.example.musicplayer.viewmodel.ListAlbumsArtistsViewModel;


public class ListAlbumsFragment extends Fragment {
    private FragmentListAlbumsBinding mBinding;
    private ListAlbumsArtistsViewModel mListAlbumsArtistsViewModel;
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
        mListAlbumsArtistsViewModel = new ViewModelProvider
                (requireActivity()).get(ListAlbumsArtistsViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_list_albums,
                container,
                false);
        mBinding.recycleViewAlbums.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return mBinding.getRoot();
    }

    public void updateUI() {
        if (mAdapterAlbums == null) {
            mAdapterAlbums = new ListAlbumsAdapter(getActivity(), this,
                    mListAlbumsArtistsViewModel);

            mBinding.recycleViewAlbums.setAdapter(mAdapterAlbums);
        } else {
            mListAlbumsArtistsViewModel.updateUIAlbum();
        }
    }
}