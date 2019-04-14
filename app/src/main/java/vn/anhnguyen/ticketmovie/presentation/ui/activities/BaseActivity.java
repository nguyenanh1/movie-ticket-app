package vn.anhnguyen.ticketmovie.presentation.ui.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.presentation.ui.BaseView;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomTextView;
import vn.anhnguyen.ticketmovie.util.SharePrefUtils;
import vn.anhnguyen.ticketmovie.util.common.CommonUtil;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    private CircleImageView mImageAvatar;
    private LinearLayout mLayoutTitleMain;
    private CustomTextView mTextTitle;
    private ImageButton mButtonBack;
    private ImageButton mButtonMenu;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideKeyboard(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang xử lý, vui lòng đợi...");
        progressDialog.setCancelable(false);
    }

    protected void hideNavigation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window w = getWindow(); // in Activity's onCreate() for instance
//            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

            final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            getWindow().getDecorView().setSystemUiVisibility(flags);
            final View decorView = getWindow().getDecorView();
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                        decorView.setSystemUiVisibility(flags);
                    }
                }
            });
        }
    }

    protected void initToolbar(){
        mImageAvatar = findViewById(R.id.image_avatar);
        mTextTitle = findViewById(R.id.text_name_activity);
        mLayoutTitleMain = findViewById(R.id.layout_title_main);
        mButtonBack = findViewById(R.id.image_button_back);
        mButtonMenu = findViewById(R.id.button_menu_navigation);
    }

    protected ImageButton getmButtonMenu() {
        return mButtonMenu;
    }

    protected void showMenuNavigation(){
        mButtonMenu.setVisibility(View.VISIBLE);
    }

    protected void hideMenuNavigation(){
        mButtonMenu.setVisibility(View.GONE);
    }

    protected void setUpAvatar(){
        mImageAvatar.setVisibility(View.VISIBLE);
        String avatar = SharePrefUtils.instance().getAvatar();
        if(avatar !=null && !avatar.isEmpty()){
            Glide.with(this).load(avatar)
                    .centerCrop()
                    .into(mImageAvatar);
        }
        final Context context = this;
        mImageAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SharePrefUtils.instance().getLoginStatus()){
                    startActivity(ProfileActivity.getIntent(context));
                }else {
                    gotoLogin(true);
                }
            }
        });

    }

    protected void showBack(){
        mButtonBack.setVisibility(View.VISIBLE);
    }

    protected ImageButton getmButtonBack() {
        return mButtonBack;
    }

    protected void hideButtonBack(){
        mButtonBack.setVisibility(View.GONE);
    }

    protected void hideAvatar(){
        mImageAvatar.setVisibility(View.GONE);
    }

    protected void setUpHome(){
        if(mTextTitle.getVisibility() == View.VISIBLE){
            mTextTitle.setVisibility(View.GONE);
        }
        mLayoutTitleMain.setVisibility(View.VISIBLE);
    }

    protected void setTitle(String title){
        if(mLayoutTitleMain.getVisibility() == View.VISIBLE){
            mLayoutTitleMain.setVisibility(View.GONE);
        }
        mTextTitle.setVisibility(View.VISIBLE);
        mTextTitle.setText(title);
    }

    @Override
    public void showProgress() {
        if(progressDialog!=null){
            progressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showWarning(String message) {

    }

    public void showToast(String message){
        CommonUtil.showToast(message,this);
    }

    public void gotoHome() {
        startActivity(MainActivity.getIntent(this));
        this.finish();
    }

    private void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void showAlertDialog() {

    }

    @Override
    public void gotoLogin(boolean fromHome) {
        startActivity(LoginActivity.getIntentExtra(this,fromHome));
    }

    @Override
    public void gotoLogin(boolean fromHome,String message) {
        showToast(message);
        startActivity(LoginActivity.getIntentExtra(this,fromHome));
    }

    @Override
    public void showSnackBarToast(String message) {

    }

    @Override
    public void showNoInternetSnackBar(String message) {

    }
}
