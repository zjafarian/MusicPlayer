package com.example.musicplayer.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.musicplayer.R;
import com.example.musicplayer.adapters.MusicPagerAdapter;
import com.example.musicplayer.data.room.entities.TabsMusics;
import com.example.musicplayer.databinding.ActivityMusicPagerBinding;
import com.example.musicplayer.viewmodel.ListAlbumsViewModel;
import com.example.musicplayer.viewmodel.ListArtistsViewModel;
import com.example.musicplayer.viewmodel.ListMusicsViewModel;
import com.example.musicplayer.viewmodel.LoginSignUpViewModel;
import com.example.musicplayer.viewmodel.MusicsPagerViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class MusicPagerActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_LOGIN_SIGN_UP = 0;
    private ActivityMusicPagerBinding mBinding;
    private MusicsPagerViewModel mMusicsPagerViewModel;


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MusicPagerActivity.class);
        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMusicsPagerViewModel = new ViewModelProvider(this).get(MusicsPagerViewModel.class);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_music_pager);
        List<TabsMusics> tabsMusics = Arrays.asList(TabsMusics.values());
        MusicPagerAdapter musicPagerAdapter =
                new MusicPagerAdapter(this);

        mBinding.musicPager.setAdapter(musicPagerAdapter);


        new TabLayoutMediator(mBinding.tabsMusicPager, mBinding.musicPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                    String nameTab = String.valueOf(tabsMusics.get(position));
                    tab.setText(nameTab);
                    mBinding.musicPager.setCurrentItem(position);

            }
        }).attach();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.music_player_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_menu_user:
                mMusicsPagerViewModel.createIntentForLoginSignUpPage(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}