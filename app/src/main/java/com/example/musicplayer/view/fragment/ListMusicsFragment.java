package com.example.musicplayer.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicplayer.R;


public class ListMusicsFragment extends Fragment {


    private String mParam1;
    private String mParam2;

    public ListMusicsFragment() {
        // Required empty public constructor
    }

    public static ListMusicsFragment newInstance() {
        ListMusicsFragment fragment = new ListMusicsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_musics, container, false);
    }
}