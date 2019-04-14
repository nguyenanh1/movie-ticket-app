package vn.anhnguyen.ticketmovie.presentation.ui.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterChangePassword;
import vn.anhnguyen.ticketmovie.presentation.presenter.impl.PresenterInjection;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomButton;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomEditText;
import vn.anhnguyen.ticketmovie.util.common.CommonUtil;

public class ChangePasswordActivity extends BaseActivity implements IPresenterChangePassword.IViewChangePassword {
    @BindView(R.id.edit_old_password)
    CustomEditText mEditOld;
    @BindView(R.id.edit_new_password)
    CustomEditText mEditNew;
    @BindView(R.id.edit_re_new_password)
    CustomEditText mEditReNew;
    @BindView(R.id.button_change_password)
    CustomButton mButtonChangePassword;

    private IPresenterChangePassword mPresenter;

    public static Intent getIntent(Context context){
        return new Intent(context,ChangePasswordActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        initToolbar();
        hideAvatar();
        showBack();
        hideMenuNavigation();
        setTitle(CommonUtil.getStringFromRes(R.string.change_password,this));
        initView();
    }

    private void initView() {
        mPresenter = PresenterInjection.getInjection().newPresnterChangePassword(this);
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
        mButtonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = mEditOld.getText().toString();
                String newPass = mEditNew.getText().toString();
                String reNewPass = mEditReNew.getText().toString();
                mPresenter.changePassword(oldPass,newPass,reNewPass);
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ChangePasswordActivity.this);
        builder.setMessage("Bạn có muốn thoát khỏi?");
        builder.setNegativeButton("HỦY BỎ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("ĐỒNG Ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ChangePasswordActivity.super.onBackPressed();
            }
        });
        builder.create().show();
    }

    @Override
    public void showChangePasswordSuccess() {
        finish();
    }
}
