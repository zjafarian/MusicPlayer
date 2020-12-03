package com.example.musicplayer.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.musicplayer.R;
import com.example.musicplayer.view.fragment.LoginSignUpFragment;

public class LoginSignUpActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginSignUpActivity.class);
        return intent;
    }

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