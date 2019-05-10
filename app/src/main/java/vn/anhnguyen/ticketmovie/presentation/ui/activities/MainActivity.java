package vn.anhnguyen.ticketmovie.presentation.ui.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.tmall.ultraviewpager.UltraViewPager;
import com.tmall.ultraviewpager.transformer.UltraDepthScaleTransformer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.domain.model.response.MovieCategory;
import vn.anhnguyen.ticketmovie.domain.model.response.NewsModel;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterMain;
import vn.anhnguyen.ticketmovie.presentation.presenter.impl.PresenterInjection;
import vn.anhnguyen.ticketmovie.presentation.ui.adapter.AdapterNew;
import vn.anhnguyen.ticketmovie.presentation.ui.adapter.MoviePagerAdapter;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomButton;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomTextView;
import vn.anhnguyen.ticketmovie.util.SharePrefUtils;
import vn.anhnguyen.ticketmovie.util.common.CommonUtil;

public class MainActivity extends BaseActivity implements IPresenterMain.IViewMain,
        RadioGroup.OnCheckedChangeListener, AdapterNew.INewEvent,
        MoviePagerAdapter.IEventClick {
    @BindView(R.id.drawer_layout_main)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.view_pager_movie)
    UltraViewPager mPager;
    @BindView(R.id.text_name)
    CustomTextView mTextName;
    @BindView(R.id.text_duration)
    CustomTextView mTextDuration;
    @BindView(R.id.radio_group_tab)
    RadioGroup mRadioGroup;
    @BindView(R.id.radio_dang_chieu)
    RadioButton mRadioShowing;
    @BindView(R.id.radio_dac_biet)
    RadioButton mRadioSpecical;
    @BindView(R.id.radio_sap_chieu)
    RadioButton mRadioWillShow;
    @BindView(R.id.text_time)
    CustomTextView mTextTime;
    @BindView(R.id.text_old)
    CustomTextView mTextOld;
    @BindView(R.id.button_booking)
    CustomButton mButtonBoooking;

    //MenuNavigation
    @BindView(R.id.layout_name)
    LinearLayout mLayoutName;
    @BindView(R.id.layout_info)
    LinearLayout mLayoutInfo;
    @BindView(R.id.layout_button_logout)
    LinearLayout mLayoutLogout;
    @BindView(R.id.text_login)
    CustomTextView mTextLogin;
    @BindView(R.id.image_avatar)
    CircleImageView mImageAvatar;
    @BindView(R.id.text_display_name)
    CustomTextView mTextDisplayName;
    @BindView(R.id.text_type)
    CustomTextView mTextType;
    @BindView(R.id.text_id)
    CustomTextView mTextId;
    @BindView(R.id.text_balance)
    CustomTextView mTextBalance;
    @BindView(R.id.text_point)
    CustomTextView mTextPoint;

    @BindView(R.id.rcl_new)
    RecyclerView mRclNew;

    private final static int START = 0;
    private final static int LIMIT = 10;

    private AdapterNew mAdpaterr;

    private List<NewsModel> mListMews;

    private IPresenterMain mPresenter;

    public static Intent getIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        hideButtonBack();
        showMenuNavigation();
        setUpAvatar();
        setUpHome();
        setUpRclNew();
        initView();
        mRadioShowing.setChecked(true);
        mPresenter.getMovieIsShowing(START, LIMIT);
    }

    private void setUpRclNew() {
        mListMews = new ArrayList<>();
        mListMews.add(new NewsModel(1,"https://www.cgv.vn/media/wysiwyg/2019/MAY19/thang-em-ly-tuong-350x495.jpg","Tặng STICKER phim \"thằng em lý tưởng\"","","AnhNguyenAndroid"));
        mListMews.add(new NewsModel(2,"https://www.cgv.vn/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/s/a/samsung_04_240x201.jpg","QUà tặng galaxy tặng vé xem phim 2d","","AnhNguyenAndroid"));
        mListMews.add(new NewsModel(3,"https://www.cgv.vn/media/wysiwyg/2019/MAR19/350x495_2.jpg","Săn điểm khủng tại Venus","","AnhNguyenAndroid"));
        mAdpaterr = new AdapterNew(mListMews,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRclNew.setLayoutManager(layoutManager);
        mRclNew.setAdapter(mAdpaterr);
        mAdpaterr.notifyDataSetChanged();
    }

    @SuppressLint("SetTextI18n")
    private void setUpNavigation() {
        if(SharePrefUtils.instance().getLoginStatus()){
            mTextLogin.setVisibility(View.GONE);
            mLayoutLogout.setVisibility(View.VISIBLE);
            mLayoutInfo.setVisibility(View.VISIBLE);
            mLayoutName.setVisibility(View.VISIBLE);
            if(!SharePrefUtils.instance().getAvatar().isEmpty()){
                Glide.with(this).load(SharePrefUtils.instance().getAvatar()).centerCrop().into(mImageAvatar);
            }
            String displayName = SharePrefUtils.instance().getLastname()+" "+SharePrefUtils.instance().getName();
            mTextDisplayName.setText(displayName);
            mTextDisplayName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDrawerLayout.closeDrawer(Gravity.END);
                }
            });
            mTextId.setText(String.valueOf(SharePrefUtils.instance().getUserId()));
            switch (SharePrefUtils.instance().getAccountType()){
                case 1:
                    mTextType.setText("MEMBER");
                    break;
                case 2:
                    mTextType.setText("VIP 1");
                    break;
                case 3:
                    mTextType.setText("VIP 2");
                    break;
                default:
                    mTextType.setText("MEMBER");
                    break;
            }
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            mTextBalance.setText(decimalFormat.format(SharePrefUtils.instance().getBalance())+"đ");
            mTextPoint.setText(SharePrefUtils.instance().getPoint()+"");
            mLayoutLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDrawerLayout.closeDrawer(Gravity.END);
                    mPresenter.logout();
                }
            });

        }else {
            mTextLogin.setVisibility(View.VISIBLE);
            mLayoutLogout.setVisibility(View.GONE);
            mLayoutInfo.setVisibility(View.GONE);
            mLayoutName.setVisibility(View.GONE);
            mTextLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDrawerLayout.closeDrawer(Gravity.END);
                    gotoLogin(false);
                }
            });
        }
    }

    private void initView() {
        mPresenter = PresenterInjection.getInjection().newPresenterMain(this);
    }

    private void setUpPagerMovie(final List<MovieCategory> list) {
        PagerAdapter mAdapter = new MoviePagerAdapter(list, this);
        mPager.setAdapter(mAdapter);
        mPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        mPager.setMultiScreen(0.65f);
        mPager.setItemRatio(1.8f);
        mPager.setRatio(1.0f);
        mPager.setMaxHeight(1000);
        mPager.setPageTransformer(false, new PagerTransform());
        mPager.setAutoMeasureHeight(true);
        mPager.setInfiniteLoop(true);
        mPager.setCurrentItem(0);
        int position = mPager.getCurrentItem();
        setUpMovie(list.get(position));
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                int position = i % list.size();
                setUpMovie(list.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setUpMovie(final MovieCategory movie) {
        if(movie.getMovie().getName().length()>35){
            String name = movie.getMovie().getName().substring(0,35);
            mTextName.setText(name+"...");
        }else {
            mTextName.setText(movie.getMovie().getName());
        }

        if (movie.getMovie().getTime() != null) {
            int time = movie.getMovie().getTime();
            int hour = time / 60;
            int minute = time - hour * 60;

            mTextDuration.setText(hour + "Giờ " + minute + "Phút");
        }else {
            mTextDuration.setText("");
        }
        mTextTime.setText(CommonUtil.convertToDDMMYY(movie.getMovie().getPartTime()));
        if (movie.getMovie().getOld() != null) {
            switch (movie.getMovie().getOld()) {
                case 18:
                    mTextOld.setVisibility(View.VISIBLE);
                    mTextOld.setText("C18");
                    mTextOld.setBackgroundDrawable(CommonUtil.getDrawableFromRes(R.drawable.drawable_c_red, MainActivity.this));
                    mTextOld.setTextColor(CommonUtil.getColorFromRes(R.color.color_c_red, MainActivity.this));
                    break;
                case 16:
                    mTextOld.setVisibility(View.VISIBLE);
                    mTextOld.setText("C16");
                    mTextOld.setBackgroundDrawable(CommonUtil.getDrawableFromRes(R.drawable.drawable_c_orangel, MainActivity.this));
                    mTextOld.setTextColor(CommonUtil.getColorFromRes(R.color.color_c_organe, MainActivity.this));
                    break;
                case 13:
                    mTextOld.setVisibility(View.VISIBLE);
                    mTextOld.setText("C13");
                    mTextOld.setBackgroundDrawable(CommonUtil.getDrawableFromRes(R.drawable.drawable_c_yellow, MainActivity.this));
                    mTextOld.setTextColor(CommonUtil.getColorFromRes(R.color.color_c_yellow, MainActivity.this));
                    break;
                case 0:
                    mTextOld.setVisibility(View.VISIBLE);
                    mTextOld.setText(" P ");
                    mTextOld.setBackgroundDrawable(CommonUtil.getDrawableFromRes(R.drawable.drawable_c_p, MainActivity.this));
                    mTextOld.setTextColor(CommonUtil.getColorFromRes(R.color.color_c_p, MainActivity.this));
                    break;
                default:
                    mTextOld.setVisibility(View.GONE);
                    break;

            }
        } else {
            mTextOld.setVisibility(View.GONE);
        }
        mButtonBoooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = MovieTimeActivity.getIntentExtra(MainActivity.this,movie.getMovie().getId(),movie.getMovie().getName());
                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpNavigation();
        mButtonBoooking.setVisibility(View.VISIBLE);
        getmButtonMenu().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.END);
            }
        });
        mRadioGroup.setOnCheckedChangeListener(this);

    }

    @Override
    public void onBackPressed() {
        mDrawerLayout.closeDrawer(Gravity.END);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
                MainActivity.super.onBackPressed();
            }
        });
        builder.create().show();
    }

    @Override
    public void onClick(int id) {
        Intent i = MovieDetailActivity.getIntetExtra(this,id);
        startActivity(i);
    }

    @Override
    public void showMovie(List<MovieCategory> movieCategori) {
        setUpPagerMovie(movieCategori);
    }

    @Override
    public void showLogoutSuccess() {
        setUpNavigation();
        showToast("Đăng xuất thành công");
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_dang_chieu:
                mPresenter.getMovieIsShowing(START, LIMIT);
                mButtonBoooking.setVisibility(View.VISIBLE);
                break;
            case R.id.radio_dac_biet:
                mPresenter.getTopMovie();
                mButtonBoooking.setVisibility(View.VISIBLE);
                break;
            case R.id.radio_sap_chieu:
                mPresenter.getMovieCommingSoon(START, LIMIT);
                mButtonBoooking.setVisibility(View.GONE);
                break;
        }
    }

    class PagerTransform extends UltraDepthScaleTransformer {
        private static final float MIN_SCALE = 0.5f;

        @Override
        public void transformPage(View view, float position) {
            final float scale = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            if (position <= 0f) {
                view.setTranslationX(view.getWidth() * -position * 0.12f);
                view.setPivotY(0.5f * view.getHeight());
                view.setPivotX(0.5f * view.getWidth());
                view.setScaleX(scale);
                view.setScaleY(scale);
            } else if (position <= 1f) {
                view.setTranslationX(view.getWidth() * -position * 0.12f);
                view.setPivotY(0.5f * view.getHeight());
                view.setPivotX(0.5f * view.getWidth());
                view.setScaleX(scale);
                view.setScaleY(scale);
            }

        }
    }
}
