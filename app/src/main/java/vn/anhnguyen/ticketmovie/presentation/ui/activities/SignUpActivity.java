package vn.anhnguyen.ticketmovie.presentation.ui.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.auth.api.signin.internal.SignInHubActivity;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterRegister;
import vn.anhnguyen.ticketmovie.presentation.presenter.impl.PresenterInjection;
import vn.anhnguyen.ticketmovie.presentation.presenter.impl.PresenterRegister;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomButton;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomEditText;
import vn.anhnguyen.ticketmovie.util.common.CommonUtil;

public class SignUpActivity extends BaseActivity implements IPresenterRegister.IViewRegister {
    @BindView(R.id.edit_email)
    CustomEditText mEditEmail;
    @BindView(R.id.edit_password)
    CustomEditText mEditPassword;
    @BindView(R.id.edit_re_password)
    CustomEditText mEditRePassword;
    @BindView(R.id.edit_last_name)
    CustomEditText mEditLastName;
    @BindView(R.id.edit_name)
    CustomEditText mEditName;
    @BindView(R.id.edit_birthday)
    CustomEditText mEditBirthday;
    @BindView(R.id.rd_male)
    RadioButton rdMale;
    @BindView(R.id.rd_female)
    RadioButton rdFemale;
    @BindView(R.id.edit_phone)
    CustomEditText mEditPhone;
    @BindView(R.id.edit_address)
    CustomEditText mEditAddress;
    @BindView(R.id.button_create_account)
    CustomButton mButtonCreate;

    private IPresenterRegister mPresenter;

    private int mDay = 1;
    private int mMonth = 0;
    private int mYear = 1970;

    public static Intent getIntent(Context context){
        return new Intent(context,SignUpActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        CommonUtil.setupUI(findViewById(R.id.layout_register),SignUpActivity.this);
        initToolbar();
        hideMenuNavigation();
        showBack();
        hideAvatar();
        setTitle(CommonUtil.getStringFromRes(R.string.register_account,this));
        initView();
    }

    private void initView() {
        mPresenter = PresenterInjection.getInjection().newPresenterRegister(this);
        mEditBirthday.setText(mDay+"/"+(mMonth+1)+"/"+mYear);
    }

    @Override
    protected void onResume() {
        super.onResume();
        rdMale.setChecked(true);
        getmButtonBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mEditBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(SignUpActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mDay = dayOfMonth;
                        mMonth = month;
                        mYear = year;
                        mEditBirthday.setText(mDay+"/"+(mMonth+1)+"/"+mYear);
                    }
                },mYear,mMonth,mDay);
                dialog.show();
            }
        });

        mButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEditEmail.getText().toString();
                String password = mEditPassword.getText().toString();
                String rePassword = mEditRePassword.getText().toString();
                String last_name = mEditLastName.getText().toString();
                String name = mEditName.getText().toString();
                int gender = 0;
                if(rdMale.isChecked()){
                    gender = 1;
                }
                if(rdFemale.isChecked()){
                    gender = 2;
                }
                Date date = new Date(mYear-1900,mMonth,mDay);
                long birthday = date.getTime();
                String phone = mEditPhone.getText().toString();
                String address = mEditAddress.getText().toString();
                mPresenter.register(email,password,rePassword,last_name,name,birthday,gender,phone,address);
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
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
                SignUpActivity.super.onBackPressed();
            }
        });
        builder.create().show();
    }

    @Override
    public void showRegisterSuccess(String message) {
        showToast(message);
        finish();
    }
}
