package vn.anhnguyen.ticketmovie.presentation.ui.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.domain.model.response.User;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterProfile;
import vn.anhnguyen.ticketmovie.presentation.presenter.impl.PresenterInjection;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomTextView;
import vn.anhnguyen.ticketmovie.util.SharePrefUtils;
import vn.anhnguyen.ticketmovie.util.common.CommonUtil;

public class ProfileActivity extends BaseActivity implements IPresenterProfile.IViewProfile {
    @BindView(R.id.image_avatar)
    CircleImageView mImageAvatar;
    @BindView(R.id.text_account_type)
    CustomTextView mTextAccountType;
    @BindView(R.id.text_display_name)
    CustomTextView mTextDisplayName;
    @BindView(R.id.text_id_account)
    CustomTextView mTextId;
    @BindView(R.id.text_balance)
    CustomTextView mTextBalance;
    @BindView(R.id.text_point)
    CustomTextView mTextPoing;
    @BindView(R.id.layout_information)
    RelativeLayout mLayoutInfor;
    @BindView(R.id.layout_password)
    RelativeLayout mLayoutPassword;
    @BindView(R.id.layout_history_transaction)
    RelativeLayout mLayoutHistory;
    @BindView(R.id.layout_movie_saw)
    RelativeLayout mLayoutMovieSaw;
    @BindView(R.id.layout_logout)
    RelativeLayout mLayoutLogout;

    private IPresenterProfile mPresenter;

    private User user;

    public static Intent getIntent(Context context) {
        return new Intent(context, ProfileActivity.class);
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
        setTitle(CommonUtil.getStringFromRes(R.string.member,this));
        initView();
    }

    private void initView() {
        mPresenter = PresenterInjection.getInjection().newPresenterProfile(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.resume();
        getmButtonBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mLayoutInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = InformationActivity.getIntent(ProfileActivity.this,user);
                startActivity(i);
            }
        });
        mLayoutPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = ChangePasswordActivity.getIntent(ProfileActivity.this);
                startActivity(i);
            }
        });
        mLayoutHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mLayoutMovieSaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mLayoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showProfile(User user) {
        this.user = user;
        if (user.getAvatar() != null && !user.getAvatar().isEmpty()) {
            Glide.with(this).load(user.getAvatar()).centerCrop().into(mImageAvatar);
        }
        switch (user.getType()) {
            case 1:
                mTextAccountType.setText("MEMBER");
                break;
            case 2:
                mTextAccountType.setText("VIP 1");
                break;
            case 3:
                mTextAccountType.setText("VIP 2");
                break;
            default:
                mTextAccountType.setText("MEMBER");
                break;
        }
        String displayName = user.getLastname()+" "+user.getName();
        mTextDisplayName.setText(displayName);
        mTextBalance.setText(user.getBalace()+"đ");
        mTextPoing.setText(String.valueOf(user.getPoint()));
        mTextId.setText(String.valueOf(user.getId()));
    }
}
