package com.example.musicplayer.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.musicplayer.data.repository.MusicRepository;
import com.example.musicplayer.data.room.entities.Music;
import com.example.musicplayer.data.room.entities.User;
import com.example.musicplayer.view.fragment.LoginFragment;
import com.example.musicplayer.view.fragment.LoginSignUpFragment;
import com.example.musicplayer.view.fragment.SignUpFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginSignUpViewModel extends AndroidViewModel {

    private MutableLiveData<User> mUserMutableLiveData = new MutableLiveData<>();
    private MusicsPagerViewModel mMusicsPagerViewModel;
    private MusicRepository mRepository;
    private Activity mActivity;

    private boolean mCheckLogin=false;
    private int mUserId;




    @RequiresApi(api = Build.VERSION_CODES.O)
    public LoginSignUpViewModel(@NonNull Application application) {
        super(application);
        mRepository = MusicRepository.getInstance(application);
      }


    public void onClickLogin(Fragment fragment){
        LoginFragment loginFragment = LoginFragment.newInstance();
        loginFragment.setTargetFragment(fragment, LoginSignUpFragment.REQUEST_CODE_LOGIN);
        loginFragment.show(fragment.getParentFragmentManager(), LoginSignUpFragment.TAG_LOGIN_PAGE);
    }

    public void onClickSignUp(Fragment fragment) {
        SignUpFragment signUpFragment = SignUpFragment.newInstance();
        signUpFragment.setTargetFragment(fragment,LoginSignUpFragment.REQUEST_CODE_SIGN_UP);
        signUpFragment.show(fragment.getParentFragmentManager(),
                LoginSignUpFragment.TAG_SIGN_UP_PAGE);
    }

    public void insertUserNew(Intent data) {
        String userName = data.getStringExtra(SignUpViewModel.EXTRA_USER_NAME_SING_UP);
        String password = data.getStringExtra(SignUpViewModel.EXTRA_PASSWORD_SIGN_UP);
        User user = new User(userName,password);
        //mRepository.insertUser(user);

        mRepository.insertUser(user);
        List<User> users = mRepository.getUsers().getValue();
        List<Music> music = mRepository.getMusicsLiveData();
    }

    public void setListMusics(Intent data){
        mCheckLogin = data.getBooleanExtra(LoginViewModel.EXTRA_CHECK_LOGIN,false);
        mUserId = (int) data.getSerializableExtra(LoginViewModel.EXTRA_USER_ID);
        if (mCheckLogin){


        }

    }

    public boolean isCheckLogin() {
        return mCheckLogin;
    }

    public void setCheckLogin(boolean checkLogin) {
        mCheckLogin = checkLogin;
    }
}
