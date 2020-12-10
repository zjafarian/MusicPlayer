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
import com.example.musicplayer.adapters.ListArtistsAdapter;
import com.example.musicplayer.databinding.FragmentListArtistsBinding;
import com.example.musicplayer.viewmodel.ListAlbumsArtistsViewModel;


public class ListArtistsFragment extends Fragment {
    private FragmentListArtistsBinding mBinding;
    private ListAlbumsArtistsViewModel mListAlbumsArtistsViewModel;
    private ListArtistsAdapter mArtistsAdapter;



    public ListArtistsFragment() {
        // Required empty public constructor
    }


    public static ListArtistsFragment newInstance() {
        ListArtistsFragment fragment = new ListArtistsFragment();
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
                R.layout.fragment_list_artists,
                container,
                false);

        mBinding.recycleViewArtists.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

        return mBinding.getRoot();
    }

    public void updateUI() {
        if (mArtistsAdapter == null){
            mArtistsAdapter = new ListArtistsAdapter(getActivity(),this,
                    mListAlbumsArtistsViewModel);
            mBinding.recycleViewArtists.setAdapter(mArtistsAdapter);
        } else {
            mListAlbumsArtistsViewModel.updateUIArtist();
        }

    }
}