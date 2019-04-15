package vn.anhnguyen.ticketmovie.presentation.ui.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.domain.model.response.User;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterInformation;
import vn.anhnguyen.ticketmovie.presentation.presenter.impl.PresenterInjection;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomButton;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomEditText;
import vn.anhnguyen.ticketmovie.util.common.CommonUtil;

public class InformationActivity extends BaseActivity implements IPresenterInformation.IViewInformation {

    @BindView(R.id.edit_email)
    CustomEditText mEditEmail;
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
    @BindView(R.id.button_save)
    CustomButton mButtonSave;

    private final static String USER = "user";
    private final static String DATA = "data";

    private int mDay;
    private int mMonth;
    private int mYear;

    private IPresenterInformation mPresenter;

    public static Intent getIntent(Context context, User user){
        Intent i = new Intent(context,InformationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER,user);
        i.putExtra(DATA,bundle);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);
        ButterKnife.bind(this);
        initToolbar();
        hideAvatar();
        showBack();
        hideMenuNavigation();
        setTitle(CommonUtil.getStringFromRes(R.string.infomation_account,this));
        initView();
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
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(InformationActivity.this);
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
                InformationActivity.super.onBackPressed();
            }
        });
        builder.create().show();
    }

    private void initView() {
        Bundle bundle = getIntent().getBundleExtra(DATA);
        mPresenter = PresenterInjection.getInjection().newPresenterInformation(this);
        if(bundle!=null){
            User user = (User) bundle.getSerializable(USER);
            mEditEmail.setText(user.getEmail());
            mEditLastName.setText(user.getLastname());
            mEditName.setText(user.getName());
            if(user.getGender()==1){
                rdMale.setChecked(true);
            }else if(user.getGender()==2){
                rdFemale.setChecked(true);
            }
//            Date date = new Date(user.getBirthday());
            Calendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(user.getBirthday());
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
            mMonth = calendar.get(Calendar.MONTH);
            mYear = calendar.get(Calendar.YEAR);
            String dateS = mDay+"/"+(mMonth+1)+"/"+(mYear);
            mEditBirthday.setText(dateS);
            mEditBirthday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog dialog = new DatePickerDialog(InformationActivity.this, new DatePickerDialog.OnDateSetListener() {
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
            mEditPhone.setText(user.getPhone());
            mEditAddress.setText(user.getAddresss());
            mButtonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String lastname = mEditLastName.getText().toString();
                    String name = mEditName.getText().toString();
                    String phone = mEditPhone.getText().toString();
                    String address = mEditAddress.getText().toString();
                    int gender = 0;
                    if(rdMale.isChecked()){
                        gender = 1;
                    }
                    if(rdFemale.isChecked()){
                        gender = 2;
                    }
                    Date date = new Date(mYear-1900,mMonth,mDay);
                    long birthday = date.getTime();
                    mPresenter.changProfile(lastname,name,birthday,gender,phone,address);
                }
            });
        }else {
            showToast("Có lỗi xảy ra");
        }
    }

    @Override
    public void showChangProfileSuccess() {
        setResult(RESULT_OK);
        finish();
    }
}
