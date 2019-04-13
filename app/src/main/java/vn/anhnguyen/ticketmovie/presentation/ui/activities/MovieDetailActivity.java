package vn.anhnguyen.ticketmovie.presentation.ui.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.domain.model.response.Category;
import vn.anhnguyen.ticketmovie.domain.model.response.MovieCategory;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterMovieDetail;
import vn.anhnguyen.ticketmovie.presentation.presenter.impl.PresenterInjection;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomButton;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomTextView;
import vn.anhnguyen.ticketmovie.util.common.CommonUtil;

public class MovieDetailActivity extends BaseActivity implements IPresenterMovieDetail.IViewMovieDetail{
    @BindView(R.id.text_movie_name)
    CustomTextView mTextMovieName;
    @BindView(R.id.button_booking)
    CustomButton mButtonBooking;
    @BindView(R.id.text_contain)
    CustomTextView mTextContain;
    @BindView(R.id.text_old)
    CustomTextView mTextOld;
    @BindView(R.id.text_time_start)
    CustomTextView mTextTimeStart;
    @BindView(R.id.text_cate)
    CustomTextView mTextCate;
    @BindView(R.id.text_director)
    CustomTextView mTextDirector;
    @BindView(R.id.text_actor)
    CustomTextView mTextAcor;
    @BindView(R.id.text_duration)
    CustomTextView mTextDuration;
    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.layout_youtube)
    RelativeLayout mLayoutYoutbe;

    private IPresenterMovieDetail mPresenter;

    private static final String ID = "id";

    public static Intent getIntetExtra(Context context,int id){
        Intent intent = new Intent(context,MovieDetailActivity.class);
        intent.putExtra(ID,id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        initToolbar();
        hideAvatar();
        showBack();
        hideMenuNavigation();
        setTitle(CommonUtil.getStringFromRes(R.string.movie,this));
        initView();
        int id = getIntent().getIntExtra(ID,-1);
        mPresenter.getMovieDetail(id);
    }

    private void initView() {
        mPresenter = PresenterInjection.getInjection().newPresenterMovieDetail(this);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setHapticFeedbackEnabled(false);
        mWebView.setWebChromeClient(new WebChromeClient(){

        });

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

    @SuppressLint("SetTextI18n")
    @Override
    public void showMovieDetail(final MovieCategory movieCategory) {
        if (movieCategory.getMovie().getName() != null) {
            mTextMovieName.setText(movieCategory.getMovie().getName());
        }
        if (movieCategory.getMovie().getContain() != null) {
            mTextContain.setText(movieCategory.getMovie().getContain());
        }
        if (movieCategory.getMovie().getOld() != null) {
            if (movieCategory.getMovie().getOld() == 0) {
                mTextOld.setText("P - Phổ biến rộng rãi đến mọi đối tượng");
            } else {
                mTextOld.setText("C" + movieCategory.getMovie().getOld()
                        + " - Không dành cho khán giả dưới "
                        + movieCategory.getMovie().getOld() + " tuổi");
            }
        }
        if (movieCategory.getMovie().getPartTime() != null) {
            mTextTimeStart.setText(CommonUtil.convertToDDMMYY(movieCategory.getMovie().getPartTime()));
        }

        if (movieCategory.getCategorys() != null && movieCategory.getCategorys().size() != 0) {
            String cate = "";
            for (Category temp : movieCategory.getCategorys()) {
                cate = cate + temp.getName() + ", ";
            }
            cate = cate.trim();
            cate = cate.substring(0, cate.length() - 1);
            mTextCate.setText(cate);
        }
        if (movieCategory.getMovie().getDirector() != null) {
            mTextDirector.setText(movieCategory.getMovie().getDirector());
        }
        if (movieCategory.getMovie().getActor() != null) {
            mTextAcor.setText(movieCategory.getMovie().getActor());
        }

        if (movieCategory.getMovie().getTime() != null) {
            int time = movieCategory.getMovie().getTime();
            int hour = time / 60;
            int minute = time - hour * 60;

            mTextDuration.setText(hour + "Giờ " + minute + "Phút");
        }
        String link = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"
                +movieCategory.getMovie().getTrailer()
                +"\"></iframe>";
        mWebView.loadData(link,"text/html","utf-8");
        mLayoutYoutbe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = YouTubePlayerActivity.getIntentExtra(MovieDetailActivity.this,movieCategory.getMovie().getTrailer());
                startActivity(i);
            }
        });

    }
}
