package com.example.musicplayer.view.activity;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.musicplayer.R;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    public static final String FRAGMENT_TAG = "FragmentActivity";

    public ViewDataBinding mBinding;

    public abstract Fragment createFragment();

    @LayoutRes
    public abstract int getLayoutResId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayoutResId());

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, createFragment(), FRAGMENT_TAG)
                    .commit();
        }
    }
}
