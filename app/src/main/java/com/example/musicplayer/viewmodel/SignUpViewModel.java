package com.example.musicplayer.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;

import com.example.musicplayer.R;

public class SignUpViewModel extends AndroidViewModel {
    public static final String EXTRA_USER_NAME_SING_UP = "com.example.musicplayer.userName";
    public static final String EXTRA_PASSWORD_SIGN_UP = "com.example.musicplayer.password";
    private String mUserName;
    private String mPassword;


    public SignUpViewModel(@NonNull Application application) {
        super(application);
    }

    public void onTextChangedUserName(CharSequence charSequence, int i, int i1, int i2) {
        mUserName = charSequence.toString();
    }

    public void onTextChangedPassword(CharSequence charSequence, int i, int i1, int i2) {
        mPassword = charSequence.toString();
    }

    public void onClickListener(Fragment fragment) {
        if (checkUserNameAndPassword(mUserName,mPassword))
            setResult(fragment,mUserName,mPassword);
        else setToast();
    }

    public void setResult(Fragment fragment, String userName, String password)  {
        Fragment targetFragment = fragment.getTargetFragment();
        Intent intent = new Intent();
        intent.putExtra(EXTRA_USER_NAME_SING_UP, userName);
        intent.putExtra(EXTRA_PASSWORD_SIGN_UP, password);
        targetFragment.onActivityResult(fragment.getTargetRequestCode(), Activity.RESULT_OK, intent);
        fragment.onDestroyView();
    }

    public boolean checkUserNameAndPassword(String userName, String password) {
        if (userName == null || password == null)
            return false;
        else
            return true;
    }

    public void setToast() {
        Toast toast = Toast.makeText(getApplication(),
                R.string.message_null_username_or_password,
                Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }



}
