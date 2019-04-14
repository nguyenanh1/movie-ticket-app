package vn.anhnguyen.ticketmovie.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterLogin;
import vn.anhnguyen.ticketmovie.presentation.presenter.impl.PresenterInjection;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomButton;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomEditText;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomTextView;
import vn.anhnguyen.ticketmovie.util.SharePrefUtils;
import vn.anhnguyen.ticketmovie.util.common.CommonUtil;

public class LoginActivity extends BaseActivity implements IPresenterLogin.IViewLogin {
    @BindView(R.id.text_sign_up)
    CustomTextView mTextSignUp;
    @BindView(R.id.text_forget_password)
    CustomTextView mTextForget;
    @BindView(R.id.edit_email_login)
    CustomEditText mEditEmailLogin;
    @BindView(R.id.edit_password_login)
    CustomEditText mEditPassword;
    @BindView(R.id.button_login)
    CustomButton mButtonLogin;

    private IPresenterLogin mPresenter;

    private final static String GO_TO_HOME = "home";

    public static Intent getIntentExtra(Context context,boolean fromHome){
        Intent i = new Intent(context,LoginActivity.class);
        i.putExtra(GO_TO_HOME,fromHome);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initToolbar();
        hideAvatar();
        showBack();
        hideMenuNavigation();
        setTitle(CommonUtil.getStringFromRes(R.string.login,this));
        initView();
    }

    private void initView() {
        mPresenter = PresenterInjection.getInjection().newPresenterLogin(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getmButtonBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if(SharePrefUtils.instance().getUserName()!= null&&!SharePrefUtils.instance().getUserName().isEmpty()){
            mEditEmailLogin.setText(SharePrefUtils.instance().getUserName());
        }
        mTextSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SignUpActivity.getIntent(LoginActivity.this));
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEditEmailLogin.getText().toString();
                String pass = mEditPassword.getText().toString();
                mPresenter.login(email,pass);
            }
        });
    }

    @Override
    public void loginSuccess(String message) {
        showToast(message);
        boolean fromHome = getIntent().getBooleanExtra(GO_TO_HOME,false);
        if(fromHome){
            startActivity(ProfileActivity.getIntent(this));
            finish();
        }else {
            finish();
        }
    }
}
