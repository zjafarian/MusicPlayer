package com.example.musicplayer.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;

import com.example.musicplayer.R;
import com.example.musicplayer.data.repository.MusicRepository;
import com.example.musicplayer.data.room.entities.User;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {
    public static final String EXTRA_CHECK_LOGIN = "com.example.musicplayer.checkLogin";
    public static final String EXTRA_USER_ID = "com.example.musicplayer.userId";
    private MusicRepository mRepository;
    private List<User> mUsers;
    private Integer mUserId;
    private boolean mCheckLogin = false;
    private String mUserNameLogin;
    private String mPassword;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public LoginViewModel(@NonNull Application application) {
        super(application);
        mRepository = MusicRepository.getInstance(application);
        mUsers = mRepository.getUsers().getValue();

    }

    public void onTextChangedUserName(CharSequence charSequence, int i, int i1, int i2) {
        mUserNameLogin = charSequence.toString();
    }

    public void onTextChangedPassword(CharSequence charSequence, int i, int i1, int i2) {
        mPassword = charSequence.toString();
    }

    public void onClickListener(Fragment fragment) {
        mUsers = mRepository.getUsers().getValue();
        if (checkUserNameAndPassword(mUserNameLogin, mPassword))
            setResult(fragment);
        else setToast(mUserNameLogin, mPassword);
    }

    public void setResult(Fragment fragment) {
        Fragment targetFragment = fragment.getTargetFragment();
        Intent intent = new Intent();
        intent.putExtra(EXTRA_USER_ID, mUserId);
        intent.putExtra(EXTRA_CHECK_LOGIN, mCheckLogin);
        targetFragment.onActivityResult(fragment.getTargetRequestCode(), Activity.RESULT_OK, intent);
        fragment.onDestroyView();
    }

    public boolean checkUserNameAndPassword(String userName, String password) {
        if (userName == null || password == null)
            return false;
        else {
            for (int i = 0; i <mUsers.size(); i++) {
                if (mUsers.get(i).getUserName().equals(userName) &&
                        mUsers.get(i).getPassword().equals(password)) {
                    mUserId = mUsers.get(i).getUserId();
                    mCheckLogin = true;
                    return true;
                }
            }
            return false;
        }
    }

    public void setToast(String username, String password) {
        if (username == null || password == null) {
            Toast toast = Toast.makeText(getApplication(),
                    R.string.message_null_username_or_password,
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getApplication(),
                    R.string.message_wrong_username_or_password,
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();
        }

    }

    public boolean isCheckLogin() {
        return mCheckLogin;
    }

    public void setCheckLogin(boolean checkLogin) {
        mCheckLogin = checkLogin;
    }
}
