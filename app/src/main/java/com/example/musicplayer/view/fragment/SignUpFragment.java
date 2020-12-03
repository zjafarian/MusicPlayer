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
import com.example.musicplayer.databinding.FragmentSignUpBinding;
import com.example.musicplayer.viewmodel.SignUpViewModel;


public class SignUpFragment extends DialogFragment {
    private FragmentSignUpBinding mBinding;
    private SignUpViewModel mSignUpViewModel;


    public SignUpFragment() {
        // Required empty public constructor
    }


    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSignUpViewModel = new ViewModelProvider(requireActivity()).get(SignUpViewModel.class);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_sign_up,
                null,
                false);
        setVariableBinding();

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.sign_up_dialog)
                .setView(mBinding.getRoot())
                .create();
    }

    private void setVariableBinding() {
        mBinding.setSignUpViewModel(mSignUpViewModel);
        mBinding.setSinUpFragment(this);
    }
}