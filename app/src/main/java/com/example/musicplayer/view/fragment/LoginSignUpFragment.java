package com.example.musicplayer.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicplayer.R;
import com.example.musicplayer.databinding.FragmentLoginSignUpBinding;
import com.example.musicplayer.viewmodel.LoginSignUpViewModel;
import com.example.musicplayer.viewmodel.LoginViewModel;

public class LoginSignUpFragment extends Fragment {
    public static final int REQUEST_CODE_LOGIN = 0;
    public static final int REQUEST_CODE_SIGN_UP = 1;
    public static final String TAG_LOGIN_PAGE = "loginPage";
    public static final String TAG_SIGN_UP_PAGE = "SignUpPage";

    private FragmentLoginSignUpBinding mBinding;
    private LoginSignUpViewModel mLoginSingUpViewModel;
    private LoginViewModel mLoginViewModel;


    public LoginSignUpFragment() {
        // Required empty public constructor
    }


    public static LoginSignUpFragment newInstance() {
        LoginSignUpFragment fragment = new LoginSignUpFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginSingUpViewModel = new
                ViewModelProvider(this).get(LoginSignUpViewModel.class);
         mLoginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_login_sign_up,
                container,
                false);


        setVariableBinding();


        return mBinding.getRoot();
    }

    private void setVariableBinding() {
        mBinding.setLoginSignUpViewModel(mLoginSingUpViewModel);
        mBinding.setLoginSignUpFragment(this);
        mBinding.setLoginViewModel(mLoginViewModel);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK && data == null)
            return;

        if (requestCode == REQUEST_CODE_LOGIN) {
            mLoginSingUpViewModel.setListMusics(data);
        } else if (requestCode == REQUEST_CODE_SIGN_UP) {
            mLoginSingUpViewModel.insertUserNew(data);
        }
    }
}