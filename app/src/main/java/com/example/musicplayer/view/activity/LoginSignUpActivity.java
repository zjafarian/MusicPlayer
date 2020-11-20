package com.example.musicplayer.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.musicplayer.R;
import com.example.musicplayer.view.fragment.LoginSignUpFragment;

public class LoginSignUpActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        LoginSignUpFragment loginSignUpFragment = LoginSignUpFragment.newInstance();
        return loginSignUpFragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_login_sign_up;
    }


}