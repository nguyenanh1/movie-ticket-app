package vn.anhnguyen.ticketmovie.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.util.common.CommonUtil;

public class LoginActivity extends BaseActivity {

    public static Intent getIntent(Context context){
        return new Intent(context,LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initToolbar();
        hideAvatar();
        setTitle(CommonUtil.getStringFromRes(R.string.login,this));
    }
}
