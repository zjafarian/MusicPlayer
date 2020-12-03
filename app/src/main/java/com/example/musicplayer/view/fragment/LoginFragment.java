package com.example.musicplayer.view.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import com.example.musicplayer.R;
import com.example.musicplayer.databinding.FragmentLoginBinding;
import com.example.musicplayer.viewmodel.LoginViewModel;


public class LoginFragment extends DialogFragment {
    private FragmentLoginBinding mBinding;
    private LoginViewModel mLoginViewModel;


    public LoginFragment() {
        // Required empty public constructor
    }


    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);


    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_login,
                null,
                false);
        setVariableBinding();

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.login_dialog)
                .setView(mBinding.getRoot())
                .create();
    }

    private void setVariableBinding() {
        mBinding.setLoginViewModel(mLoginViewModel);
        mBinding.setLoginFragment(this);
    }
}