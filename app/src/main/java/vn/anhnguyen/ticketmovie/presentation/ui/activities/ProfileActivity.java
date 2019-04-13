package vn.anhnguyen.ticketmovie.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.util.SharePrefUtils;
import vn.anhnguyen.ticketmovie.util.common.CommonUtil;

public class ProfileActivity extends BaseActivity {
    @BindView()

    public static Intent getIntent(Context context){
        return new Intent(context,ProfileActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        initToolbar();
        hideAvatar();
        showBack();
        hideMenuNavigation();
        setTitle(CommonUtil.getStringFromRes(R.string.login,this));
        initView();
    }

    private void initView() {
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
