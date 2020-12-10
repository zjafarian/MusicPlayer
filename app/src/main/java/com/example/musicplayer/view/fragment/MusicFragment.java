package com.example.musicplayer.view.fragment;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.os.Handler;

import com.bumptech.glide.Glide;
import com.example.musicplayer.R;
import com.example.musicplayer.data.entities.Music;
import com.example.musicplayer.databinding.FragmentMusicBinding;
import com.example.musicplayer.utilities.PictureUtils;
import com.example.musicplayer.viewmodel.MusicViewModel;
import com.squareup.picasso.Picasso;


public class MusicFragment extends Fragment {

    public static final String ARGS_ID_MUSIC = "com.example.musicplayer.idMusic";
    public static final String ARGS_SIZE_VALUES = "argsSizeValues";
    public static final String TAG = "MusicFragment";
    public static final String ARGS_NAME_TAB = "argsNameTab";
    public static final String ARGS_NAME_ARTIST_OR_ALBUM = "argsNameArtistOrAlbum";
    private long mIdMusic;
    private MusicViewModel mMusicViewModel;
    private FragmentMusicBinding mBinding;
    private Music mMusic;
    private Handler mHandler;
    private Runnable mRunnable;
    private boolean mIsPlaying = false;
    private boolean mIsShuffle = false;
    private boolean mIsFavorite = false;
    private boolean mIsRepeatAll = false;
    private boolean mIsRepeatOne = false;
    private String mNameTab;
    private String mNameArtistOrAlbum;


    public MusicFragment() {
        // Required empty public constructor
    }


    public static MusicFragment newInstance(long idMusic, String nameTab, String nameArtistOrAlbum) {
        MusicFragment fragment = new MusicFragment();
        Bundle args = new Bundle();
        args.putLong(ARGS_ID_MUSIC, idMusic);
        args.putString(ARGS_NAME_TAB, nameTab);
        args.putString(ARGS_NAME_ARTIST_OR_ALBUM, nameArtistOrAlbum);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mIdMusic = getArguments().getLong(ARGS_ID_MUSIC);
            mNameTab = getArguments().getString(ARGS_NAME_TAB);
            mNameArtistOrAlbum = getArguments().getString(ARGS_NAME_ARTIST_OR_ALBUM);
        }
        mMusicViewModel = new ViewModelProvider(this).get(MusicViewModel.class);
        mMusicViewModel.setActivity(getActivity());
        mMusic = mMusicViewModel.findMusic(mIdMusic, mNameTab, mNameArtistOrAlbum);

        mHandler = new Handler();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_music,
                container,
                false);
        mBinding.setMusicViewModel(mMusicViewModel);

        initViews();
        setListener();
        return mBinding.getRoot();

    }

    private void initViews() {
        //setImageMusic();
    }

    private void setImageMusic() {
        Uri uri = mMusicViewModel.getBitmapImage();
        if (uri!=null){
            Picasso.get()
                    .load(uri)
                    .placeholder(R.drawable.ic_music_holder)
                    .into(mBinding.imageViewMusicHolder);
        }
    }

    private void setListener() {

        mBinding.imgBtnPlayOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsPlaying = !mIsPlaying;


                if (mIsPlaying) {
                    mBinding.imgBtnPlayOrPause.setImageResource(R.drawable.ic_pause);

                    play();
                    mMusicViewModel.getMediaPlayer().start();


                    mBinding.seekBarMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                            if (input) {
                                mMusicViewModel.getMediaPlayer().seekTo(progress);
                            }

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });

                } else {
                    mMusic = mMusicViewModel.getMusic();
                    mBinding.imgBtnPlayOrPause.setImageResource(R.drawable.ic_play);
                    mMusicViewModel.getMediaPlayer().pause();
                }

            }
        });

        mBinding.imgBtnShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsShuffle = !mIsShuffle;
                if (mIsShuffle) {
                    mBinding.imgBtnShuffle.setImageResource(R.drawable.ic_shuffle);
                    mMusicViewModel.setPlayList(mIsShuffle);

                } else {
                    mBinding.imgBtnShuffle.setImageResource(R.drawable.ic_shuffle_off);
                }
            }
        });

        mBinding.imgBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMusicViewModel.goForward();
                mMusic = mMusicViewModel.getMusic();
                initViews();
                mBinding.textViewTitleMusic.setText(mMusicViewModel.getTitleCurrentMusic());
                play();
            }
        });

        mBinding.imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMusicViewModel.goBackward();
                mMusic = mMusicViewModel.getMusic();
                initViews();
                mBinding.textViewTitleMusic.setText(mMusicViewModel.getTitleCurrentMusic());
                play();
            }
        });

        mBinding.imgBtnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsRepeatAll = !mIsRepeatAll;
                if (mIsRepeatAll) {
                    mBinding.imgBtnRepeat.setImageResource(R.drawable.ic_repeat_all);
                    mMusicViewModel.setLoop(mIsRepeatAll);
                    mIsRepeatOne = false;
                } else {
                    mIsRepeatOne = !mIsRepeatOne;
                    if (mIsRepeatOne) {
                        mBinding.imgBtnRepeat.setImageResource(R.drawable.ic_repeat_one);
                        mMusicViewModel.getMediaPlayer().setLooping(mIsRepeatOne);
                        mIsRepeatAll = true;
                    } else {
                        mIsRepeatOne = true;
                        mIsRepeatAll = false;
                        mBinding.imgBtnRepeat.setImageResource(R.drawable.ic_repeat_off);
                        mMusicViewModel.getMediaPlayer().setLooping(false);
                        mMusicViewModel.setLoop(false);
                        mIsPlaying = false;

                    }
                }
            }
        });
    }


    public void play() {
        mRunnable = new Runnable() {
            @Override
            public void run() {
                mBinding.seekBarMusic.setProgress
                        (mMusicViewModel.getMediaPlayer().getCurrentPosition());
                mBinding.timeTotalMusic.setText(mMusicViewModel.getTotalTimeText());
                mBinding.textViewTitleMusic.setText(mMusicViewModel.getMusic().getMusicTitle());

            /*    if (mIsRepeatAll) {
                    mBinding.timeTotalMusic.setText(mMusicViewModel.getTotalTimeText());
                    mBinding.textViewTitleMusic.setText(mMusicViewModel.getMusic().getMusicTitle());
                } else {
                    mMusicViewModel.goForward();
                    mBinding.textViewTitleMusic.setText
                            (mMusicViewModel.getMusic().getMusicTitle());
                    mBinding.timeTotalMusic.setText(mMusicViewModel.getTotalTimeText());
                    mBinding.imgBtnPlayOrPause.setImageResource(R.drawable.ic_pause);
                    mMusicViewModel.getMediaPlayer().stop();
                    mMusicViewModel.getMediaPlayer().release();
                }*/
                mHandler.postDelayed(this, 130);
            }
        };
        mHandler.post(mRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mIsPlaying) {
            mMusicViewModel.getMediaPlayer().start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        try {
            mMusicViewModel.getMediaPlayer().stop();
            mMusicViewModel.getMediaPlayer().release();
            mHandler.removeCallbacks(mRunnable);
            mBinding.seekBarMusic.removeCallbacks(mRunnable);
        } catch (Exception e) {
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            mMusicViewModel.getMediaPlayer().stop();
            mMusicViewModel.getMediaPlayer().release();
            mHandler.removeCallbacks(mRunnable);
            mBinding.seekBarMusic.removeCallbacks(mRunnable);
        } catch (Exception e) {
            mBinding.seekBarMusic.removeCallbacks(mRunnable);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        try {
            mMusicViewModel.getMediaPlayer().stop();
            mMusicViewModel.getMediaPlayer().release();
            mHandler.removeCallbacks(mRunnable);
            mBinding.seekBarMusic.removeCallbacks(mRunnable);


        } catch (Exception e) {
            mBinding.seekBarMusic.removeCallbacks(mRunnable);
        }
    }
}